/*
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package extra166y;
import java.lang.ref.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import sun.misc.Unsafe;

/**
 * A {@link java.util.ConcurrentMap} supporting user-defined
 * equivalence comparisons, soft, weak, or strong keys and values, and
 * user-supplied computational methods for setting and updating
 * values. In particular: <ul>
 *
 *   <li> Identity-based, Equality-based or User-definable {@link
 *        Equivalence}-based comparisons controlling membership.
 *
 *   <li> {@linkplain SoftReference Soft}, {@linkplain
 *        WeakReference weak} or strong (regular) keys and values.
 *
 *   <li> User-definable {@code MappingFunctions} that may be
 *        used in method {@link
 *        CustomConcurrentHashMap#computeIfAbsent} to atomically
 *        establish a computed value, along with
 *        {@code RemappingFunctions} that can be used in method
 *        {@link CustomConcurrentHashMap#compute} to atomically
 *        replace values.
 *
 *    <li>Factory methods returning specialized forms for {@code int}
 *        keys and/or values, that may be more space-efficient
 *
 * </ul>
 *
 * Per-map settings are established in constructors, as in the
 * following usages (that assume static imports to simplify expression
 * of configuration parameters):
 *
 * <pre>
 * {@code
 * identityMap = new CustomConcurrentHashMap<Person,Salary>
 *     (STRONG, IDENTITY, STRONG, EQUALS, 0);
 * weakKeyMap = new CustomConcurrentHashMap<Person,Salary>
 *     (WEAK, IDENTITY, STRONG, EQUALS, 0);
 *     .weakKeys());
 * byNameMap = new CustomConcurrentHashMap<Person,Salary>
 *     (STRONG,
 *      new Equivalence<Person>() {
 *          public boolean equal(Person k, Object x) {
 *            return x instanceof Person && k.name.equals(((Person)x).name);
 *          }
 *          public int hash(Object x) {
 *             return (x instanceof Person) ? ((Person)x).name.hashCode() : 0;
 *          }
 *        },
 *      STRONG, EQUALS, 0);
 * }
 * </pre>
 *
 * The first usage above provides a replacement for {@link
 * java.util.IdentityHashMap}, and the second a replacement for {@link
 * java.util.WeakHashMap}, adding concurrency, asynchronous cleanup,
 * and identity-based equality for keys. The third usage
 * illustrates a map with a custom Equivalence that looks only at the
 * name field of a (fictional) Person class.
 *
 * <p>This class also includes nested class {@link KeySet}
 * that provides space-efficient Set views of maps, also supporting
 * method {@code intern}, which may be of use in canonicalizing
 * elements.
 *
 * <p>When used with (Weak or Soft) Reference keys and/or values,
 * elements that have asynchronously become {@code null} are
 * treated as absent from the map and (eventually) removed from maps
 * via a background thread common across all maps. Because of the
 * potential for asynchronous clearing of References, methods such as
 * {@code containsValue} have weaker guarantees than you might
 * expect even in the absence of other explicitly concurrent
 * operations. For example {@code containsValue(value)} may
 * return true even if {@code value} is no longer available upon
 * return from the method.
 *
 * <p>When Equivalences other than equality are used, the returned
 * collections may violate the specifications of {@code Map} and/or
 * {@code Set} interfaces, which mandate the use of the
 * {@code equals} method when comparing objects.  The methods of this
 * class otherwise have properties similar to those of {@link
 * java.util.ConcurrentHashMap} under its default settings.  To
 * adaptively maintain semantics and performance under varying
 * conditions, this class does <em>not</em> support load factor or
 * concurrency level parameters.  This class does not permit null keys
 * or values. This class is serializable; however, serializing a map
 * that uses soft or weak references can give unpredictable results.
 * This class supports all optional operations of the {@code
 * ConcurrentMap} interface.  It supports have <i>weakly consistent
 * iteration</i>: an iterator over one of the map's view collections
 * may reflect some, all or none of the changes made to the collection
 * after the iterator was created.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class CustomConcurrentHashMap<K,V> extends AbstractMap<K,V>
    implements ConcurrentMap<K,V>, Serializable {
    private static final long serialVersionUID = 7249069246764182397L;

    /*
     * This class uses a similar approach as ConcurrentHashMap, but
     * makes different internal tradeoffs, mainly (1) We use more
     * segments, but lazily initialize them; and (2) Links connecting
     * nodes are not immutable, enabling unsplicing.  These two
     * adjustments help improve concurrency in the face of heavier
     * per-element mechanics and the increased load due to reference
     * removal, while still keeping footprint etc reasonable.
     *
     * Additionally, because Reference keys/values may become null
     * asynchronously, we cannot ensure snapshot integrity in methods
     * such as containsValue, so do not try to obtain them (so, no
     * modCounts etc).
     *
     * Also, the volatility of Segment count vs table fields are
     * swapped, enabled by ensuring fences on new node assignments.
     */


    /**
     * The strength of keys and values that may be held by
     * maps. strong denotes ordinary objects. weak and soft denote the
     * corresponding {@link java.lang.ref.Reference} types.
     */
    public enum Strength {
        strong("Strong"), weak("Weak"), soft("Soft");
        private final String name;
        Strength(String name) { this.name = name; }
        String getName() { return name; }
    };


    /** The strength of ordinary references */
    public static final Strength STRONG = Strength.strong;

    /** The strength of weak references */
    public static final Strength WEAK   = Strength.weak;

    /** The strength of soft references */
    public static final Strength SOFT   = Strength.soft;

    /** Config string for self-map (Set view) refs */
    private static final String SELF_STRING = "Self";

    /** Config string for int maps */
    private static final String INT_STRING = "Int";

    /**
     * An object performing equality comparisons, along with a hash
     * function consistent with this comparison.  The type signatures
     * of the methods of this interface reflect those of {@link
     * java.util.Map}: While only elements of {@code K} may be
     * entered into a Map, any {@code Object} may be tested for
     * membership. Note that the performance of hash maps is heavily
     * dependent on the quality of hash functions.
     */
    public static interface Equivalence<K> {
        /**
         * Returns true if the given objects are considered equal.
         * This function must obey an equivalence relation:
         * equal(a, a) is always true, equal(a, b) implies equal(b, a),
         * and (equal(a, b) &amp;&amp; equal(b, c) implies equal(a, c).
         * Note that the second argument need not be known to have
         * the same declared type as the first.
         * @param key a key in, or being placed in, the map
         * @param x an object queried for membership
         * @return true if considered equal
         */
        boolean equal(K key, Object x);
        /**
         * Returns a hash value such that equal(a, b) implies
         * hash(a)==hash(b).
         * @param x an object queried for membership
         * @return a hash value
         */
        int hash(Object x);
    }

    // builtin equivalences

    static final class EquivalenceUsingIdentity
        implements Equivalence<Object>, Serializable {
        private static final long serialVersionUID = 7259069246764182397L;
        public final boolean equal(Object a, Object b) { return a == b; }
        public final int hash(Object a) { return System.identityHashCode(a); }
    }

    static final class EquivalenceUsingEquals
        implements Equivalence<Object>, Serializable {
        private static final long serialVersionUID = 7259069247764182397L;
        public final boolean equal(Object a, Object b) { return a.equals(b); }
        public final int hash(Object a) { return a.hashCode(); }
    }

    /**
     * An Equivalence object performing identity-based comparisons
     * and using {@link System#identityHashCode} for hashing
     */
    public static final Equivalence<Object> IDENTITY =
        new EquivalenceUsingIdentity();

    /**
     * An Equivalence object performing {@link Object#equals} based comparisons
     * and using {@link Object#hashCode} hashing
     */
    public static final Equivalence<Object> EQUALS =
        new EquivalenceUsingEquals();

    /**
     * A function computing a mapping from the given key to a value,
     * or {@code null} if there is no mapping.
     */
    public static interface MappingFunction<K,V> {
        /**
         * Returns a value for the given key, or null if there is no
         * mapping. If this function throws an (unchecked) exception,
         * the exception is rethrown to its caller, and no mapping is
         * recorded.  Because this function is invoked within
         * atomicity control, the computation should be short and
         * simple. The most common usage is to construct a new object
         * serving as an initial mapped value.
         *
         * @param key the (non-null) key
         * @return a value, or null if none
         */
        V map(K key);
    }

    /**
     * A function computing a new mapping from the given key and its
     * current value to a new value, or {@code null} if there is
     * no mapping.
     */
    public static interface RemappingFunction<K,V> {
        /**
         * Returns a new value for the given key and its current, or
         * null if there is no mapping.
         * @param key the key
         * @param value the current value, or null if none
         * @return a value, or null if none
         */
        V remap(K key, V value);
    }

    /**
     * An object that may be subject to cleanup operations when
     * removed from a {@link java.lang.ref.ReferenceQueue}
     */
    static interface Reclaimable {
        /**
         * The action taken upon removal of this object
         * from a ReferenceQueue.
         */
        void onReclamation();
    }

    /**
     * A factory for Nodes.
     */
    static interface NodeFactory extends Serializable {
        /**
         * Creates and returns a Node using the given parameters.
         *
         * @param locator an opaque immutable locator for this node
         * @param key the (non-null) immutable key
         * @param value the (non-null) volatile value
         * @param cchm the table creating this node
         * @param linkage an opaque volatile linkage for maintaining this node
         */
        Node newNode(int locator, Object key, Object value,
                     CustomConcurrentHashMap cchm, Node linkage);
    }

    /**
     * An object maintaining a key-value mapping. Nodes provide
     * methods that are intended to used <em>only</em> by the map
     * creating the node. This includes methods used solely for
     * internal bookkeeping by maps, that must be treating opaquely by
     * implementation classes. (This requirement stems from the fact
     * that concrete implementations may be required to subclass
     * {@link java.lang.ref.Reference} or other classes, so a base
     * class cannot be established.)
     *
     * This interface uses raw types as the lesser of evils.
     * Otherwise we'd encounter almost as many unchecked casts when
     * nodes are used across sets, etc.
     */
    static interface Node extends Reclaimable {
        /**
         * Returns the key established during the creation of this node.
         * Note: This method is named "get" rather than "getKey"
         * to simplify usage of Reference keys.
         * @return the key
         */
        Object get();

        /**
         * Returns the locator established during the creation of this node.
         * @return the locator
         */
        int getLocator();

        /**
         * Returns the value established during the creation of this
         * node or, if since updated, the value set by the most
         * recent call to setValue, or throws an exception if
         * value could not be computed.
         * @return the value
         * @throws RuntimeException or Error if computeValue failed
         */
        Object getValue();

        /**
         * Nodes the value to be returned by the next call to getValue.
         * @param value the value
         */
        void setValue(Object value);

        /**
         * Returns the linkage established during the creation of this
         * node or, if since updated, the linkage set by the most
         * recent call to setLinkage.
         * @return the linkage
         */
        Node getLinkage();

        /**
         * Records the linkage to be returned by the next call to getLinkage.
         * @param linkage the linkage
         */
        void setLinkage(Node linkage);
    }

    /**
     * Each Segment holds a count and table corresponding to a segment
     * of the table. This class contains only those methods for
     * directly assigning these fields, which must only be called
     * while holding locks.
     */
    static final class Segment extends ReentrantLock {
        volatile Node[] table;
        int count;

        final void decrementCount() {
            if (--count == 0)
                table = null;
        }

        final void clearCount() {
            count = 0;
            table = null;
        }

        final void incrementCount() {
            ++count;
        }

        final Node[] getTableForTraversal() {
            return table;
        }

        final Node[] getTableForAdd(CustomConcurrentHashMap cchm) {
            int len;
            Node[] tab = table;
            if (tab == null ||  // 3/4 threshold
                ((len = tab.length) - (len >>> 2)) < count)
                return resizeTable(cchm);
            else
                return tab;
        }

        /**
         * See the similar code in ConcurrentHashMap for explanation.
         */
        final Node[] resizeTable(CustomConcurrentHashMap cchm) {
            Node[] oldTable = table;
            if (oldTable == null)
                return table = new Node[cchm.initialSegmentCapacity];

            int oldCapacity = oldTable.length;
            if (oldCapacity >= MAX_SEGMENT_CAPACITY)
                return oldTable;
            Node[] newTable = new Node[oldCapacity<<1];
            int sizeMask = newTable.length - 1;
            NodeFactory fac = cchm.factory;
            for (int i = 0; i < oldCapacity ; i++) {
                Node e = oldTable[i];

                if (e != null) {
                    Node next = e.getLinkage();
                    int idx = e.getLocator() & sizeMask;

                    //  Single node on list
                    if (next == null)
                        newTable[idx] = e;

                    else {
                        // Reuse trailing consecutive sequence at same slot
                        Node lastRun = e;
                        int lastIdx = idx;
                        for (Node last = next;
                             last != null;
                             last = last.getLinkage()) {
                            int k = last.getLocator() & sizeMask;
                            if (k != lastIdx) {
                                lastIdx = k;
                                lastRun = last;
                            }
                        }
                        newTable[lastIdx] = lastRun;

                        // Clone all remaining nodes
                        for (Node p = e; p != lastRun; p = p.getLinkage()) {
                            int ph = p.getLocator();
                            int k = ph & sizeMask;
                            Object pk = p.get();
                            Object pv;
                            if (pk == null ||
                                (pv = p.getValue()) == null)
                                --count;
                            else
                                newTable[k] =
                                    fac.newNode(ph, pk, pv, cchm, newTable[k]);
                        }
                    }
                }
            }
            return table = newTable;
        }
    }

    // Hardwire 64 segments

    static final int SEGMENT_BITS         = 6;
    static final int NSEGMENTS            = 1 << SEGMENT_BITS;
    static final int SEGMENT_MASK         = NSEGMENTS - 1;
    static final int SEGMENT_SHIFT        = 32 - SEGMENT_BITS;
    static final int MIN_SEGMENT_CAPACITY = 4;
    static final int MAX_SEGMENT_CAPACITY = 1 << (32 - SEGMENT_BITS);

    /**
     * Applies a supplemental hash function to a given hashCode, which
     * defends against poor quality hash functions.  This is critical
     * because we use power-of-two length hash tables, that otherwise
     * encounter collisions for hashCodes that do not differ in lower
     * or upper bits.
     */
    static int spreadHash(int h) {
        // Spread bits to regularize both segment and index locations,
        // using variant of single-word Wang/Jenkins hash.
        h += (h <<  15) ^ 0xffffcd7d;
        h ^= (h >>> 10);
        h += (h <<   3);
        h ^= (h >>>  6);
        h += (h <<   2) + (h << 14);
        return h ^ (h >>> 16);
    }

    /**
     * The segments, each of which acts as a hash table
     */
    transient volatile Segment[] segments;

    /**
     * The factory for this map
     */
    final NodeFactory factory;

    /**
     * Equivalence object for keys
     */
    final Equivalence<? super K> keyEquivalence;

    /**
     * Equivalence object for values
     */
    final Equivalence<? super V> valueEquivalence;

    /**
     * The initial size of Segment tables when they are first constructed
     */
    final int initialSegmentCapacity;

    // Cached view objects
    transient Set<K> keySet;
    transient Set<Map.Entry<K,V>> entrySet;
    transient Collection<V> values;

    /**
     * Internal constructor to set factory, equivalences and segment
     * capacities, and to create segments array.
     */
    CustomConcurrentHashMap(String ks, Equivalence<? super K> keq,
                            String vs, Equivalence<? super V> veq,
                            int expectedSize) {
        if (keq == null || veq == null)
            throw new NullPointerException();
        this.keyEquivalence = keq;
        this.valueEquivalence = veq;
        // Reflectively assemble factory name
        String factoryName =
            CustomConcurrentHashMap.class.getName() + "$" +
            ks + "Key" +
            vs + "ValueNodeFactory";
        try {
            this.factory = (NodeFactory)
                (Class.forName(factoryName).newInstance());
        } catch (Exception ex) {
            throw new Error("Cannot instantiate " + factoryName);
        }
        int es = expectedSize;
        if (es == 0)
            this.initialSegmentCapacity = MIN_SEGMENT_CAPACITY;
        else {
            int sc = (int)((1L + (4L * es) / 3) >>> SEGMENT_BITS);
            if (sc < MIN_SEGMENT_CAPACITY)
                sc = MIN_SEGMENT_CAPACITY;
            int capacity = MIN_SEGMENT_CAPACITY; // ensure power of two
            while (capacity < sc)
                capacity <<= 1;
            if (capacity > MAX_SEGMENT_CAPACITY)
                capacity = MAX_SEGMENT_CAPACITY;
            this.initialSegmentCapacity = capacity;
        }
        this.segments = new Segment[NSEGMENTS];
    }

    /**
     * Creates a new CustomConcurrentHashMap with the given parameters.
     * @param keyStrength the strength for keys
     * @param keyEquivalence the Equivalence to use for keys
     * @param valueStrength the strength for values
     * @param valueEquivalence the Equivalence to use for values
     * @param expectedSize an estimate of the number of elements
     * that will be held in the map. If no estimate is known,
     * zero is an acceptable value.
     */
    public CustomConcurrentHashMap(Strength keyStrength,
                                   Equivalence<? super K> keyEquivalence,
                                   Strength valueStrength,
                                   Equivalence<? super V> valueEquivalence,
                                   int expectedSize) {
        this(keyStrength.getName(), keyEquivalence,
             valueStrength.getName(), valueEquivalence,
             expectedSize);
    }

    /**
     * Creates a new CustomConcurrentHashMap with strong keys and
     * values, and equality-based equivalence.
     */
    public CustomConcurrentHashMap() {
        this(STRONG, EQUALS, STRONG, EQUALS, 0);
    }

    /**
     * Returns a new map using Integer keys and the given value
     * parameters.
     * @param valueStrength the strength for values
     * @param valueEquivalence the Equivalence to use for values
     * @param expectedSize an estimate of the number of elements
     * that will be held in the map. If no estimate is known,
     * zero is an acceptable value.
     * @return the map
     */
    public static <ValueType> CustomConcurrentHashMap<Integer, ValueType>
        newIntKeyMap(Strength valueStrength,
                     Equivalence<? super ValueType> valueEquivalence,
                     int expectedSize) {
        return new CustomConcurrentHashMap<Integer, ValueType>
            (INT_STRING, EQUALS, valueStrength.getName(), valueEquivalence,
             expectedSize);
    }

    /**
     * Returns a new map using the given key parameters and Integer values.
     * @param keyStrength the strength for keys
     * @param keyEquivalence the Equivalence to use for keys
     * @param expectedSize an estimate of the number of elements
     * that will be held in the map. If no estimate is known,
     * zero is an acceptable value.
     * @return the map
     */
    public static <KeyType> CustomConcurrentHashMap<KeyType, Integer>
        newIntValueMap(Strength keyStrength,
                       Equivalence<? super KeyType> keyEquivalence,
                       int expectedSize) {
        return new CustomConcurrentHashMap<KeyType, Integer>
            (keyStrength.getName(), keyEquivalence, INT_STRING, EQUALS,
             expectedSize);
    }

    /**
     * Returns a new map using Integer keys and values.
     * @param expectedSize an estimate of the number of elements
     * that will be held in the map. If no estimate is known,
     * zero is an acceptable value.
     * @return the map
     */
    public static CustomConcurrentHashMap<Integer, Integer>
        newIntKeyIntValueMap(int expectedSize) {
        return new CustomConcurrentHashMap<Integer, Integer>
            (INT_STRING, EQUALS, INT_STRING, EQUALS,
             expectedSize);
    }

    /**
     * Returns the segment for traversing table for key with given hash.
     * @param hash the hash code for the key
     * @return the segment, or null if not yet initialized
     */
    final Segment getSegmentForTraversal(int hash) {
        return segments[(hash >>> SEGMENT_SHIFT) & SEGMENT_MASK];
    }

    /**
     * Returns the segment for possibly inserting into the table
     * associated with given hash, constructing it if necessary.
     * @param hash the hash code for the key
     * @return the segment
     */
    final Segment getSegmentForAdd(int hash) {
        Segment[] segs = segments;
        int index = (hash >>> SEGMENT_SHIFT) & SEGMENT_MASK;
        Segment seg = segs[index];
        if (seg == null) {
            synchronized (segs) {
                seg = segs[index];
                if (seg == null) {
                    seg = new Segment();
                    // Fences.preStoreFence(seg);
                    // segs[index] = seg;
                    storeSegment(segs, index, seg);
                }
            }
        }
        return seg;
    }

    /**
     * Returns node for key, or null if none.
     */
    final Node findNode(Object key, int hash, Segment seg) {
        if (seg != null) {
            Node[] tab = seg.getTableForTraversal();
            if (tab != null) {
                Node p = tab[hash & (tab.length - 1)];
                while (p != null) {
                    Object k = p.get();
                    if (k == key ||
                        (k != null &&
                         p.getLocator() == hash &&
                         keyEquivalence.equal((K)k, key)))
                        return p;
                    p = p.getLinkage();
                }
            }
        }
        return null;
    }

    /**
     * Returns {@code true} if this map contains a key equivalent to
     * the given key with respect to this map's key Equivalence.
     *
     * @param  key possible key
     * @return {@code true} if this map contains the specified key
     * @throws NullPointerException if the specified key is null
     */
    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException();
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        Node r = findNode(key, hash, seg);
        return r != null && r.getValue() != null;
    }

    /**
     * Returns the value associated with a key equivalent to the given
     * key with respect to this map's key Equivalence, or {@code null}
     * if no such mapping exists.
     *
     * @param  key possible key
     * @return the value associated with the key, or {@code null} if
     * there is no mapping
     * @throws NullPointerException if the specified key is null
     */
    public V get(Object key) {
        if (key == null)
            throw new NullPointerException();
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        Node r = findNode(key, hash, seg);
        if (r == null)
            return null;
        return (V)(r.getValue());
    }

    /**
     * Shared implementation for put, putIfAbsent
     */
    final V doPut(K key, V value, boolean onlyIfNull) {
        if (key == null || value == null)
            throw new NullPointerException();
        V oldValue = null;
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForAdd(hash);
        seg.lock();
        try {
            Node r = findNode(key, hash, seg);
            if (r != null) {
                oldValue = (V)(r.getValue());
                if (!onlyIfNull || oldValue == null)
                    r.setValue(value);
            }
            else {
                Node[] tab = seg.getTableForAdd(this);
                int i = hash & (tab.length - 1);
                r = factory.newNode(hash, key, value, this, tab[i]);
                // Fences.preStoreFence(r);
                // tab[i] = r;
                storeNode(tab, i, r);
                seg.incrementCount();
            }
        } finally {
            seg.unlock();
        }
        return oldValue;
    }

    /**
     * Maps the specified key to the specified value in this map.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}
     * @throws NullPointerException if the specified key or value is null
     */
    public V put(K key, V value) {
        return doPut(key, value, false);
    }

    /**
     * {@inheritDoc}
     *
     * @return the previous value associated with the specified key,
     *         or {@code null} if there was no mapping for the key
     * @throws NullPointerException if the specified key or value is null
     */
    public V putIfAbsent(K key, V value) {
        return doPut(key, value, true);
    }

    /**
     * Copies all of the mappings from the specified map to this one.
     * These mappings replace any mappings that this map had for any
     * of the keys currently in the specified map.
     *
     * @param m mappings to be stored in this map
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet())
            put(e.getKey(), e.getValue());
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if any of the arguments are null
     */
    public V replace(K key, V value) {
        if (key == null || value == null)
            throw new NullPointerException();
        V oldValue = null;
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        if (seg != null) {
            seg.lock();
            try {
                Node r = findNode(key, hash, seg);
                if (r != null) {
                    oldValue = (V)(r.getValue());
                    r.setValue(value);
                }
            } finally {
                seg.unlock();
            }
        }
        return oldValue;
    }

    /**
     * {@inheritDoc}
     *
     * @return the previous value associated with the specified key,
     *         or {@code null} if there was no mapping for the key
     * @throws NullPointerException if the specified key or value is null
     */
    public boolean replace(K key, V oldValue, V newValue) {
        if (key == null || oldValue == null || newValue == null)
            throw new NullPointerException();
        boolean replaced = false;
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        if (seg != null) {
            seg.lock();
            try {
                Node r = findNode(key, hash, seg);
                if (r != null) {
                    V v = (V)(r.getValue());
                    if (v == oldValue ||
                        (v != null && valueEquivalence.equal(v, oldValue))) {
                        r.setValue(newValue);
                        replaced = true;
                    }
                }
            } finally {
                seg.unlock();
            }
        }
        return replaced;
    }

    /**
     * Removes the mapping for the specified key.
     *
     * @param  key the key to remove
     * @return the previous value associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}
     * @throws NullPointerException if the specified key is null
     */
    public V remove(Object key) {
        if (key == null)
            throw new NullPointerException();
        V oldValue = null;
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        if (seg != null) {
            seg.lock();
            try {
                Node[] tab = seg.getTableForTraversal();
                if (tab != null) {
                    int i = hash & (tab.length - 1);
                    Node pred = null;
                    Node p = tab[i];
                    while (p != null) {
                        Node n = p.getLinkage();
                        Object k = p.get();
                        if (k == key ||
                            (k != null &&
                             p.getLocator() == hash &&
                             keyEquivalence.equal((K)k, key))) {
                            oldValue = (V)(p.getValue());
                            if (pred == null)
                                tab[i] = n;
                            else
                                pred.setLinkage(n);
                            seg.decrementCount();
                            break;
                        }
                        pred = p;
                        p = n;
                    }
                }
            } finally {
                seg.unlock();
            }
        }
        return oldValue;
    }

    /**
     * {@inheritDoc}
     *
     * @throws NullPointerException if the specified key is null
     */
    public boolean remove(Object key, Object value) {
        if (key == null)
            throw new NullPointerException();
        if (value == null)
            return false;
        boolean removed = false;
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        if (seg != null) {
            seg.lock();
            try {
                Node[] tab = seg.getTableForTraversal();
                if (tab != null) {
                    int i = hash & (tab.length - 1);
                    Node pred = null;
                    Node p = tab[i];
                    while (p != null) {
                        Node n = p.getLinkage();
                        Object k = p.get();
                        if (k == key ||
                            (k != null &&
                             p.getLocator() == hash &&
                             keyEquivalence.equal((K)k, key))) {
                            V v = (V)(p.getValue());
                            if (v == value ||
                                (v != null &&
                                 valueEquivalence.equal(v, value))) {
                                if (pred == null)
                                    tab[i] = n;
                                else
                                    pred.setLinkage(n);
                                seg.decrementCount();
                                removed = true;
                            }
                            break;
                        }
                        pred = p;
                        p = n;
                    }
                }
            } finally {
                seg.unlock();
            }
        }
        return removed;
    }

    /**
     * Removes node if its key or value are null.
     */
    final void removeIfReclaimed(Node r) {
        int hash = r.getLocator();
        Segment seg = getSegmentForTraversal(hash);
        if (seg != null) {
            seg.lock();
            try {
                Node[] tab = seg.getTableForTraversal();
                if (tab != null) {
                    // remove all reclaimed in list
                    int i = hash & (tab.length - 1);
                    Node pred = null;
                    Node p = tab[i];
                    while (p != null) {
                        Node n = p.getLinkage();
                        if (p.get() != null && p.getValue() != null) {
                            pred = p;
                            p = n;
                        }
                        else {
                            if (pred == null)
                                tab[i] = n;
                            else
                                pred.setLinkage(n);
                            seg.decrementCount();
                            p = n;
                        }
                    }
                }
            } finally {
                seg.unlock();
            }
        }
    }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public final boolean isEmpty() {
        final Segment[] segs = this.segments;
        for (int i = 0; i < segs.length; ++i) {
            Segment seg = segs[i];
            if (seg != null &&
                seg.getTableForTraversal() != null &&
                seg.count != 0)
                return false;
        }
        return true;
    }

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    public final int size() {
        long sum = 0;
        final Segment[] segs = this.segments;
        for (int i = 0; i < segs.length; ++i) {
            Segment seg = segs[i];
            if (seg != null && seg.getTableForTraversal() != null)
                sum += seg.count;
        }
        return (sum >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) sum;
    }

    /**
     * Returns {@code true} if this map maps one or more keys to a
     * value equivalent to the given value with respect to this map's
     * value Equivalence.  Note: This method requires a full internal
     * traversal of the hash table, and so is much slower than method
     * {@code containsKey}.
     *
     * @param value value whose presence in this map is to be tested
     * @return {@code true} if this map maps one or more keys to the
     *         specified value
     * @throws NullPointerException if the specified value is null
     */
    public final boolean containsValue(Object value) {
        if (value == null)
            throw new NullPointerException();
        Segment[] segs = this.segments;
        for (int i = 0; i < segs.length; ++i) {
            Segment seg = segs[i];
            Node[] tab;
            if (seg != null && (tab = seg.getTableForTraversal()) != null) {
                for (int j = 0; j < tab.length; ++j) {
                    for (Node p = tab[j];
                         p != null;
                         p = p.getLinkage()) {
                        V v = (V)(p.getValue());
                        if (v == value ||
                            (v != null && valueEquivalence.equal(v, value)))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Removes all of the mappings from this map.
     */
    public final void clear() {
        Segment[] segs = this.segments;
        for (int i = 0; i < segs.length; ++i) {
            Segment seg = segs[i];
            if (seg != null) {
                seg.lock();
                try {
                    seg.clearCount();
                } finally {
                    seg.unlock();
                }
            }
        }
    }

    /**
     * If the specified key is not already associated with a value,
     * computes its value using the given mappingFunction, and if
     * non-null, enters it into the map.  This is equivalent to
     *
     * <pre>
     *   if (map.containsKey(key))
     *       return map.get(key);
     *   value = mappingFunction.map(key);
     *   if (value != null)
     *      return map.put(key, value);
     *   else
     *      return null;
     * </pre>
     *
     * except that the action is performed atomically.  Some
     * attempted operations on this map by other threads may be
     * blocked while computation is in progress. Because this function
     * is invoked within atomicity control, the computation should be
     * short and simple. The most common usage is to construct a new
     * object serving as an initial mapped value, or memoized result.
     *
     * @param key key with which the specified value is to be associated
     * @param mappingFunction the function to compute a value
     * @return the current (existing or computed) value associated with
     *         the specified key, or {@code null} if the computation
     *         returned {@code null}
     * @throws NullPointerException if the specified key or mappingFunction
     *         is null
     * @throws RuntimeException or Error if the mappingFunction does so,
     *         in which case the mapping is left unestablished
     */
    public V computeIfAbsent(K key, MappingFunction<? super K, ? extends V> mappingFunction) {
        if (key == null || mappingFunction == null)
            throw new NullPointerException();
        int hash = spreadHash(keyEquivalence.hash(key));
        Segment seg = getSegmentForTraversal(hash);
        Node r = findNode(key, hash, seg);
        V v = null;
        if (r == null) {
            if (seg == null)
                seg = getSegmentForAdd(hash);
            seg.lock();
            try {
                r = findNode(key, hash, seg);
                if (r == null || (v = (V)(r.getValue())) == null) {
                    // Map is OK if function throws exception
                    v = mappingFunction.map(key);
                    if (v != null) {
                        if (r != null)
                            r.setValue(v);
                        else {
                            Node[] tab = seg.getTableForAdd(this);
                            int i = hash & (tab.length - 1);
                            r = factory.newNode(hash, key, v, this, tab[i]);
                            // Fences.preStoreFence(r);
                            // tab[i] = r;
                            storeNode(tab, i, r);
                            seg.incrementCount();
                        }
                    }
                }
            } finally {
                seg.unlock();
            }
        }
        if (r != null && v == null)
            removeIfReclaimed(r);
        return v;
    }

    /**
     * Updates the mapping for the given key with the result of the
     * given remappingFunction.  This is equivalent to
     *
     * <pre>
     *   value = remappingFunction.remap(key, get(key));
     *   if (value != null)
     *     return put(key, value):
     *   else
     *     return remove(key);
     * </pre>
     *
     * except that the action is performed atomically. Some attempted
     * operations on this map by other threads may be blocked while
     * computation is in progress.
     *
     * <p>Sample Usage. A remapping function can be used to
     * perform frequency counting of words using code such as:
     * <pre>
     * map.compute(word, new RemappingFunction&lt;String,Integer&gt;() {
     *   public Integer remap(String k, Integer v) {
     *     return (v == null) ? 1 : v + 1;
     *   }});
     * </pre>
     *
     * @param key key with which the specified value is to be associated
     * @param remappingFunction the function to compute a value
     * @return the updated value or
     *         {@code null} if the computation returned {@code null}
     * @throws NullPointerException if the specified key or remappingFunction
     *         is null
     * @throws RuntimeException or Error if the remappingFunction does so,
     *         in which case the mapping is left in its previous state
     */
    public V compute(K key, RemappingFunction<? super K, V> remappingFunction) {
        if (key == null || remappingFunction == null)
            throw new NullPointerException();
        int hash = spreadHash(keyEquivalence.hash(key));
        V value = null;
        Segment seg = getSegmentForAdd(hash);
        seg.lock();
        try {
            Node[] tab = seg.getTableForAdd(this);
            int i = hash & (tab.length - 1);
            Node pred = null;
            Node p = tab[i];
            while (p != null) {
                K k = (K)(p.get());
                if (k == key ||
                    (k != null &&
                     p.getLocator() == hash &&
                     keyEquivalence.equal(k, key))) {
                    value = (V)(p.getValue());
                    break;
                }
                pred = p;
                p = p.getLinkage();
            }
            value = remappingFunction.remap(key, value);
            if (p != null) {
                if (value != null)
                    p.setValue(value);
                else {
                    Node n = p.getLinkage();
                    if (pred == null)
                        tab[i] = n;
                    else
                        pred.setLinkage(n);
                    seg.decrementCount();
                }
            }
            else if (value != null) {
                Node r =
                    factory.newNode(hash, key, value, this, tab[i]);
                // Fences.preStoreFence(r);
                // tab[i] = r;
                storeNode(tab, i, r);
                seg.incrementCount();
            }
        } finally {
            seg.unlock();
        }
        return value;
    }

    abstract class HashIterator {
        int nextSegmentIndex;
        int nextTableIndex;
        Node[] currentTable;
        Node nextNode;
        Object nextKey;           // key of nextNode
        Object nextValue;         // value of nextNode
        Object lastKey;           // for remove()

        HashIterator() {
            nextSegmentIndex = segments.length - 1;
            nextTableIndex = -1;
            advance();
        }

        public final boolean hasNext() { return nextNode != null; }

        final void advance() {
            lastKey = nextKey;
            if (nextNode != null)
                nextNode = nextNode.getLinkage();
            for (;;) {
                if (nextNode != null) {
                    if ((nextKey = nextNode.get()) != null &&
                        (nextValue = nextNode.getValue()) != null)
                        return;
                    Node n = nextNode.getLinkage();
                    removeIfReclaimed(nextNode);
                    nextNode = n;
                }
                else if (nextTableIndex >= 0) {
                    nextNode = currentTable[nextTableIndex--];
                }
                else if (nextSegmentIndex >= 0) {
                    Segment seg = segments[nextSegmentIndex--];
                    Node[] t;
                    if (seg != null &&
                        (t = seg.getTableForTraversal()) != null) {
                        currentTable = t;
                        nextTableIndex = t.length - 1;
                    }
                }
                else {
                    nextKey = null;
                    nextValue = null;
                    return;
                }
            }
        }

        final K nextKey() {
            if (nextNode == null)
                throw new NoSuchElementException();
            Object k = nextKey;
            advance();
            return (K)k;
        }

        final V nextValue() {
            if (nextNode == null)
                throw new NoSuchElementException();
            Object v = nextValue;
            advance();
            return (V)v;
        }

        final Map.Entry<K,V> nextEntry() {
            if (nextNode == null)
                throw new NoSuchElementException();
            WriteThroughEntry e = new WriteThroughEntry((K)nextKey,
                                                        (V)nextValue);
            advance();
            return e;
        }

        public void remove() {
            if (lastKey == null)
                throw new IllegalStateException();
            CustomConcurrentHashMap.this.remove(lastKey);
            lastKey = null;
        }
    }

    final class WriteThroughEntry implements Map.Entry<K,V>, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        final K key;
        V value;
        WriteThroughEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public V setValue(V value) {
            if (value == null) throw new NullPointerException();
            V v = this.value;
            this.value = value;
            CustomConcurrentHashMap.this.doPut(key, value, false);
            return v;
        }
        public int hashCode() {
            return keyEquivalence.hash(key) ^ valueEquivalence.hash(value);
        }
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            return (keyEquivalence.equal(key, e.getKey()) &&
                    valueEquivalence.equal(value, e.getValue()));
        }
    }

    final class KeyIterator extends HashIterator
        implements Iterator<K> {
        public K next() {
            return super.nextKey();
        }
    }

    final KeyIterator keyIterator() { // needed by Set
        return new KeyIterator();
    }

    final class ValueIterator extends HashIterator
        implements Iterator<V> {
        public V next() {
            return super.nextValue();
        }
    }

    final class EntryIterator extends HashIterator
        implements Iterator<Map.Entry<K,V>> {
        public Map.Entry<K,V> next() {
            return super.nextEntry();
        }
    }

    final class KeySetView extends AbstractSet<K> {
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
        public int size() {
            return CustomConcurrentHashMap.this.size();
        }
        public boolean isEmpty() {
            return CustomConcurrentHashMap.this.isEmpty();
        }
        public boolean contains(Object o) {
            return CustomConcurrentHashMap.this.containsKey(o);
        }
        public boolean remove(Object o) {
            return CustomConcurrentHashMap.this.remove(o) != null;
        }
        public void clear() {
            CustomConcurrentHashMap.this.clear();
        }
    }

    final class Values extends AbstractCollection<V> {
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
        public int size() {
            return CustomConcurrentHashMap.this.size();
        }
        public boolean isEmpty() {
            return CustomConcurrentHashMap.this.isEmpty();
        }
        public boolean contains(Object o) {
            return CustomConcurrentHashMap.this.containsValue(o);
        }
        public void clear() {
            CustomConcurrentHashMap.this.clear();
        }
    }

    final class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        public Iterator<Map.Entry<K,V>> iterator() {
            return new EntryIterator();
        }
        public boolean contains(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            V v = CustomConcurrentHashMap.this.get(e.getKey());
            return v != null &&
                valueEquivalence.equal(v, e.getValue());
        }
        public boolean remove(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            return CustomConcurrentHashMap.this.remove(e.getKey(),
                                                       e.getValue());
        }
        public int size() {
            return CustomConcurrentHashMap.this.size();
        }
        public boolean isEmpty() {
            return CustomConcurrentHashMap.this.isEmpty();
        }
        public void clear() {
            CustomConcurrentHashMap.this.clear();
        }
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  The set supports element
     * removal, which removes the corresponding mapping from this map,
     * via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear}
     * operations.  It does not support the {@code add} or
     * {@code addAll} operations.
     *
     * <p>The view's {@code iterator} is a "weakly consistent" iterator
     * that will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     */
    public Set<K> keySet() {
        Set<K> ks = keySet;
        return (ks != null) ? ks : (keySet = new KeySetView());
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  The collection
     * supports element removal, which removes the corresponding
     * mapping from this map, via the {@code Iterator.remove},
     * {@code Collection.remove}, {@code removeAll},
     * {@code retainAll}, and {@code clear} operations.  It does not
     * support the {@code add} or {@code addAll} operations.
     *
     * <p>The view's {@code iterator} is a "weakly consistent" iterator
     * that will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     */
    public Collection<V> values() {
        Collection<V> vs = values;
        return (vs != null) ? vs : (values = new Values());
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  The set supports element
     * removal, which removes the corresponding mapping from the map,
     * via the {@code Iterator.remove}, {@code Set.remove},
     * {@code removeAll}, {@code retainAll}, and {@code clear}
     * operations.  It does not support the {@code add} or
     * {@code addAll} operations.
     *
     * <p>The view's {@code iterator} is a "weakly consistent" iterator
     * that will never throw {@link ConcurrentModificationException},
     * and guarantees to traverse elements as they existed upon
     * construction of the iterator, and may (but is not guaranteed to)
     * reflect any modifications subsequent to construction.
     */
    public Set<Map.Entry<K,V>> entrySet() {
        Set<Map.Entry<K,V>> es = entrySet;
        return (es != null) ? es : (entrySet = new EntrySet());
    }

    // overridden AbstractMap methods

    /**
     * Compares the specified object with this map for equality.
     * Returns {@code true} if the given object is also a map of the
     * same size, holding keys that are equal using this Map's key
     * Equivalence, and which map to values that are equal according
     * to this Map's value equivalence.
     *
     * @param o object to be compared for equality with this map
     * @return {@code true} if the specified object is equal to this map
     */
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof Map))
            return false;
        Map<K,V> m = (Map<K,V>) o;
        if (m.size() != size())
            return false;

        try {
            Iterator<Entry<K,V>> i = entrySet().iterator();
            while (i.hasNext()) {
                Entry<K,V> e = i.next();
                K key = e.getKey();
                V value = e.getValue();
                if (value != null) {
                    V mv = m.get(key);
                    if (mv == null)
                        return false;
                    if (!valueEquivalence.equal(mv, value))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }

    /**
     * Returns the sum of the hash codes of each entry in this map's
     * {@code entrySet()} view, which in turn are the hash codes
     * computed using key and value Equivalences for this Map.
     * @return the hash code
     */
    public int hashCode() {
        int h = 0;
        Iterator<Entry<K,V>> i = entrySet().iterator();
        while (i.hasNext())
            h += i.next().hashCode();
        return h;
    }

    /**
     * Saves the state of the instance to a stream (i.e., serializes it).
     *
     * @param s the stream
     * @serialData
     * the key (Object) and value (Object)
     * for each key-value mapping, followed by a null pair.
     * The key-value mappings are emitted in no particular order.
     */
    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        for (Map.Entry<K,V> e : entrySet()) {
            s.writeObject(e.getKey());
            s.writeObject(e.getValue());
        }
        s.writeObject(null);
        s.writeObject(null);
    }

    /**
     * Reconstitutes the instance from a stream (that is, deserializes it).
     * @param s the stream
     */
    private void readObject(java.io.ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        this.segments = new Segment[NSEGMENTS];
        for (;;) {
            K key = (K) s.readObject();
            V value = (V) s.readObject();
            if (key == null)
                break;
            put(key, value);
        }
    }

    /**
     * A hash-based set with properties identical to those of
     * {@code Collections.newSetFromMap} applied to a
     * {@code CustomConcurrentHashMap}, but possibly more
     * space-efficient.  The set does not permit null elements. The
     * set is serializable; however, serializing a set that uses soft
     * or weak references can give unpredictable results.
     */
    public static class KeySet<K> extends AbstractSet<K>
        implements Set<K>, Serializable {

        final CustomConcurrentHashMap<K,K> cchm;

        /**
         * Creates a set with the given parameters.
         * @param strength the strength of elements
         * @param equivalence the Equivalence to use
         * @param expectedSize an estimate of the number of elements
         * that will be held in the set. If no estimate is known, zero
         * is an acceptable value.
         */
        public KeySet(Strength strength,
                      Equivalence<? super K> equivalence,
                      int expectedSize) {
            this.cchm = new CustomConcurrentHashMap<K,K>
                (strength.getName(), equivalence,
                 SELF_STRING, equivalence, expectedSize);
        }

        /**
         * Returns an element equivalent to the given element with
         * respect to this set's Equivalence, if such an element
         * exists, else adds and returns the given element.
         *
         * @param e the element
         * @return e, or an element equivalent to e
         */
        public K intern(K e) {
            K oldElement = cchm.doPut(e, e, true);
            return (oldElement != null) ? oldElement : e;
        }

        /**
         * Returns {@code true} if this set contains an
         * element equivalent to the given element with respect
         * to this set's Equivalence.
         * @param o element whose presence in this set is to be tested
         * @return {@code true} if this set contains the specified element
         */
        public boolean contains(Object o) {
            return cchm.containsKey(o);
        }

        /**
         * Returns a <i>weakly consistent iterator</i> over the
         * elements in this set, that may reflect some, all or none of
         * the changes made to the set after the iterator was created.
         *
         * @return an Iterator over the elements in this set
         */
        public Iterator<K> iterator() {
            return cchm.keyIterator();
        }

        /**
         * Adds the specified element to this set if there is not
         * already an element equivalent to the given element with
         * respect to this set's Equivalence.
         *
         * @param e element to be added to this set
         * @return {@code true} if this set did not already contain
         * the specified element
         */
        public boolean add(K e) {
            return cchm.doPut(e, e, true) != null;
        }

        /**
         * Removes an element equivalent to the given element with
         * respect to this set's Equivalence, if one is present.
         *
         * @param o object to be removed from this set, if present
         * @return {@code true} if the set contained the specified element
         */
        public boolean remove(Object o) {
            return cchm.remove(o) != null;
        }

        /**
         * Returns {@code true} if this set contains no elements.
         *
         * @return {@code true} if this set contains no elements
         */
        public boolean isEmpty() {
            return cchm.isEmpty();
        }

        /**
         * Returns the number of elements in this set (its cardinality).
         *
         * @return the number of elements in this set (its cardinality)
         */
        public int size() {
            return cchm.size();
        }

        /**
         * Removes all of the elements from this set.
         */
        public void clear() {
            cchm.clear();
        }

        /**
         * Returns the sum of the hash codes of each element, as
         * computed by this set's Equivalence.
         * @return the hash code
         */
        public int hashCode() {
            int h = 0;
            Iterator<K> i = iterator();
            while (i.hasNext())
                h += cchm.keyEquivalence.hash(i.next());
            return h;
        }
    }

    // Reference queue mechanics for reclaimable nodes

    static volatile ReferenceQueue<Object> refQueue;

    /**
     * Returns a queue that may be used as the ReferenceQueue argument
     * to {@link java.lang.ref.Reference} constructors to arrange
     * removal of reclaimed nodes from maps via a background thread.
     * @return the reference queue associated with the background
     * cleanup thread
     */
    static ReferenceQueue<Object> getReclamationQueue() {
        ReferenceQueue<Object> q = refQueue;
        if (q != null)
            return q;
        else
            return startReclamation();
    }

    static synchronized ReferenceQueue<Object> startReclamation() {
            ReferenceQueue<Object> q = refQueue;
            if (q == null) {
                refQueue = q = new ReferenceQueue<Object>();
                new ReclamationThread(q).start();
            }
            return q;
        }

    static final class ReclamationThread extends Thread {
        final ReferenceQueue<Object> queue;
        ReclamationThread(ReferenceQueue<Object> q) {
            this.queue = q;
            setDaemon(true);
        }
        public void run() {
            ReferenceQueue<Object> q = queue;
            for (;;) {
                try {
                    Reference<?> r = q.remove();
                    if (r instanceof Reclaimable)
                        ((Reclaimable)r).onReclamation();
                } catch (InterruptedException e) {
                    /* ignore */
                }
            }
        }
    }

    // classes extending Weak/soft refs embedded in reclaimable nodes

    static class EmbeddedWeakReference extends WeakReference
        implements Reclaimable {
        final Reclaimable outer;
        EmbeddedWeakReference(Object x, Reclaimable outer) {
            super(x, getReclamationQueue());
            this.outer = outer;
        }
        public final void onReclamation() {
            clear();
            outer.onReclamation();
        }
    }

    static class EmbeddedSoftReference extends SoftReference
        implements Reclaimable {
        final Reclaimable outer;
        EmbeddedSoftReference(Object x, Reclaimable outer) {
            super(x, getReclamationQueue());
            this.outer = outer;
        }
        public final void onReclamation() {
            clear();
            outer.onReclamation();
        }
    }

    // builtin mapping node classes

    // Strong Keys

    abstract static class StrongKeyNode implements Node {
        final Object key;
        final int locator;
        StrongKeyNode(int locator, Object key) {
            this.locator = locator;
            this.key = key;
        }
        public final Object get() { return key; }
        public final int getLocator() { return locator; }
    }


    abstract static class StrongKeySelfValueNode
        extends StrongKeyNode {
        StrongKeySelfValueNode(int locator, Object key) {
            super(locator, key);
        }
        public final Object getValue() { return key; }
        public final void setValue(Object value) { }
        public final void onReclamation() { }
    }

    static final class TerminalStrongKeySelfValueNode
        extends StrongKeySelfValueNode {
        TerminalStrongKeySelfValueNode(int locator, Object key) {
            super(locator, key);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedStrongKeySelfValueNode
        extends StrongKeySelfValueNode {
        volatile Node linkage;
        LinkedStrongKeySelfValueNode(int locator, Object key,
                                     Node linkage) {
            super(locator, key);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class StrongKeySelfValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalStrongKeySelfValueNode
                    (locator, key);
            else
                return new LinkedStrongKeySelfValueNode
                    (locator, key, linkage);
        }
    }

    abstract static class StrongKeyStrongValueNode
        extends StrongKeyNode {
        volatile Object value;
        StrongKeyStrongValueNode(int locator, Object key, Object value) {
            super(locator, key);
            this.value = value;
        }
        public final Object getValue() { return value; }
        public final void setValue(Object value) { this.value = value; }
        public final void onReclamation() { }
    }

    static final class TerminalStrongKeyStrongValueNode
        extends StrongKeyStrongValueNode {
        TerminalStrongKeyStrongValueNode(int locator,
                                         Object key, Object value) {
            super(locator, key, value);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedStrongKeyStrongValueNode
        extends StrongKeyStrongValueNode {
        volatile Node linkage;
        LinkedStrongKeyStrongValueNode(int locator,
                                       Object key, Object value,
                                       Node linkage) {
            super(locator, key, value);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class StrongKeyStrongValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalStrongKeyStrongValueNode
                    (locator, key, value);
            else
                return new LinkedStrongKeyStrongValueNode
                    (locator, key, value, linkage);
        }
    }

    // ...

    abstract static class StrongKeyIntValueNode
        extends StrongKeyNode {
        volatile int value;
        StrongKeyIntValueNode(int locator, Object key, Object value) {
            super(locator, key);
            this.value = ((Integer)value).intValue();
        }
        public final Object getValue() { return Integer.valueOf(value); }
        public final void setValue(Object value) {
            this.value = ((Integer)value).intValue();
        }
        public final void onReclamation() { }
    }

    static final class TerminalStrongKeyIntValueNode
        extends StrongKeyIntValueNode {
        TerminalStrongKeyIntValueNode(int locator,
                                         Object key, Object value) {
            super(locator, key, value);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedStrongKeyIntValueNode
        extends StrongKeyIntValueNode {
        volatile Node linkage;
        LinkedStrongKeyIntValueNode(int locator,
                                       Object key, Object value,
                                       Node linkage) {
            super(locator, key, value);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class StrongKeyIntValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalStrongKeyIntValueNode
                    (locator, key, value);
            else
                return new LinkedStrongKeyIntValueNode
                    (locator, key, value, linkage);
        }
    }

    // ...

    abstract static class StrongKeyWeakValueNode
        extends StrongKeyNode {
        volatile EmbeddedWeakReference valueRef;
        final CustomConcurrentHashMap cchm;
        StrongKeyWeakValueNode(int locator, Object key, Object value,
                               CustomConcurrentHashMap cchm) {
            super(locator, key);
            this.cchm = cchm;
            if (value != null)
                this.valueRef = new EmbeddedWeakReference(value, this);
        }
        public final void onReclamation() {
            cchm.removeIfReclaimed(this);
        }
        public final Object getValue() {
            EmbeddedWeakReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedWeakReference(value, this);
        }
    }

    static final class TerminalStrongKeyWeakValueNode
        extends StrongKeyWeakValueNode {
        TerminalStrongKeyWeakValueNode(int locator,
                                       Object key, Object value,
                                       CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedStrongKeyWeakValueNode
        extends StrongKeyWeakValueNode {
        volatile Node linkage;
        LinkedStrongKeyWeakValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm,
                                     Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class StrongKeyWeakValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalStrongKeyWeakValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedStrongKeyWeakValueNode
                    (locator, key, value, cchm, linkage);
        }
    }


    abstract static class StrongKeySoftValueNode
        extends StrongKeyNode {
        volatile EmbeddedSoftReference valueRef;
        final CustomConcurrentHashMap cchm;
        StrongKeySoftValueNode(int locator, Object key, Object value,
                               CustomConcurrentHashMap cchm) {
            super(locator, key);
            this.cchm = cchm;
            if (value != null)
                this.valueRef = new EmbeddedSoftReference(value, this);
        }
        public final void onReclamation() {
            cchm.removeIfReclaimed(this);
        }
        public final Object getValue() {
            EmbeddedSoftReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedSoftReference(value, this);
        }
    }

    static final class TerminalStrongKeySoftValueNode
        extends StrongKeySoftValueNode {
        TerminalStrongKeySoftValueNode(int locator,
                                       Object key, Object value,
                                       CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedStrongKeySoftValueNode
        extends StrongKeySoftValueNode {
        volatile Node linkage;
        LinkedStrongKeySoftValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm,
                                     Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class StrongKeySoftValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalStrongKeySoftValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedStrongKeySoftValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    // Weak keys

    abstract static class WeakKeyNode extends WeakReference
        implements Node {
        final int locator;
        final CustomConcurrentHashMap cchm;
        WeakKeyNode(int locator, Object key, CustomConcurrentHashMap cchm) {
            super(key, getReclamationQueue());
            this.locator = locator;
            this.cchm = cchm;
        }
        public final int getLocator() { return locator; }
        public final void onReclamation() {
            clear();
            cchm.removeIfReclaimed(this);
        }
    }

    abstract static class WeakKeySelfValueNode
        extends WeakKeyNode {
        WeakKeySelfValueNode(int locator, Object key,
                             CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
        }
        public final Object getValue() { return get(); }
        public final void setValue(Object value) { }
    }

    static final class TerminalWeakKeySelfValueNode
        extends WeakKeySelfValueNode {
        TerminalWeakKeySelfValueNode(int locator, Object key,
                                     CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedWeakKeySelfValueNode
        extends WeakKeySelfValueNode {
        volatile Node linkage;
        LinkedWeakKeySelfValueNode(int locator, Object key,
                                   CustomConcurrentHashMap cchm,
                                   Node linkage) {
            super(locator, key, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class WeakKeySelfValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalWeakKeySelfValueNode
                    (locator, key, cchm);
            else
                return new LinkedWeakKeySelfValueNode
                    (locator, key, cchm, linkage);
        }
    }


    abstract static class WeakKeyStrongValueNode
        extends WeakKeyNode {
        volatile Object value;
        WeakKeyStrongValueNode(int locator, Object key, Object value,
                               CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            this.value = value;
        }
        public final Object getValue() { return value; }
        public final void setValue(Object value) { this.value = value; }
    }

    static final class TerminalWeakKeyStrongValueNode
        extends WeakKeyStrongValueNode {
        TerminalWeakKeyStrongValueNode(int locator,
                                       Object key, Object value,
                                       CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedWeakKeyStrongValueNode
        extends WeakKeyStrongValueNode {
        volatile Node linkage;
        LinkedWeakKeyStrongValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm,
                                     Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class WeakKeyStrongValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalWeakKeyStrongValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedWeakKeyStrongValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    abstract static class WeakKeyIntValueNode
        extends WeakKeyNode {
        volatile int value;
        WeakKeyIntValueNode(int locator, Object key, Object value,
                            CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            this.value = ((Integer)value).intValue();
        }
        public final Object getValue() { return Integer.valueOf(value); }
        public final void setValue(Object value) {
            this.value = ((Integer)value).intValue();
        }
    }

    static final class TerminalWeakKeyIntValueNode
        extends WeakKeyIntValueNode {
        TerminalWeakKeyIntValueNode(int locator,
                                    Object key, Object value,
                                    CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedWeakKeyIntValueNode
        extends WeakKeyIntValueNode {
        volatile Node linkage;
        LinkedWeakKeyIntValueNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class WeakKeyIntValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalWeakKeyIntValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedWeakKeyIntValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    abstract static class WeakKeyWeakValueNode
        extends WeakKeyNode {
        volatile EmbeddedWeakReference valueRef;
        WeakKeyWeakValueNode(int locator, Object key, Object value,
                             CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            if (value != null)
                this.valueRef = new EmbeddedWeakReference(value, this);
        }
        public final Object getValue() {
            EmbeddedWeakReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedWeakReference(value, this);
        }
    }

    static final class TerminalWeakKeyWeakValueNode
        extends WeakKeyWeakValueNode {
        TerminalWeakKeyWeakValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedWeakKeyWeakValueNode
        extends WeakKeyWeakValueNode {
        volatile Node linkage;
        LinkedWeakKeyWeakValueNode(int locator,
                                   Object key, Object value,
                                   CustomConcurrentHashMap cchm,
                                   Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class WeakKeyWeakValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalWeakKeyWeakValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedWeakKeyWeakValueNode
                    (locator, key, value, cchm, linkage);
        }
    }


    abstract static class WeakKeySoftValueNode
        extends WeakKeyNode {
        volatile EmbeddedSoftReference valueRef;
        WeakKeySoftValueNode(int locator, Object key, Object value,
                             CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            if (value != null)
                this.valueRef = new EmbeddedSoftReference(value, this);
        }
        public final Object getValue() {
            EmbeddedSoftReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedSoftReference(value, this);
        }
    }

    static final class TerminalWeakKeySoftValueNode
        extends WeakKeySoftValueNode {
        TerminalWeakKeySoftValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedWeakKeySoftValueNode
        extends WeakKeySoftValueNode {
        volatile Node linkage;
        LinkedWeakKeySoftValueNode(int locator,
                                   Object key, Object value,
                                   CustomConcurrentHashMap cchm,
                                   Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class WeakKeySoftValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalWeakKeySoftValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedWeakKeySoftValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    // Soft keys

    abstract static class SoftKeyNode extends SoftReference
        implements Node {
        final int locator;
        final CustomConcurrentHashMap cchm;
        SoftKeyNode(int locator, Object key, CustomConcurrentHashMap cchm) {
            super(key, getReclamationQueue());
            this.locator = locator;
            this.cchm = cchm;
        }
        public final int getLocator() { return locator; }
        public final void onReclamation() {
            clear();
            cchm.removeIfReclaimed(this);
        }
    }

    abstract static class SoftKeySelfValueNode
        extends SoftKeyNode {
        SoftKeySelfValueNode(int locator, Object key,
                             CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
        }
        public final Object getValue() { return get(); }
        public final void setValue(Object value) { }
    }

    static final class TerminalSoftKeySelfValueNode
        extends SoftKeySelfValueNode {
        TerminalSoftKeySelfValueNode(int locator, Object key,
                                     CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedSoftKeySelfValueNode
        extends SoftKeySelfValueNode {
        volatile Node linkage;
        LinkedSoftKeySelfValueNode(int locator, Object key,
                                   CustomConcurrentHashMap cchm,
                                   Node linkage) {
            super(locator, key, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class SoftKeySelfValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalSoftKeySelfValueNode
                    (locator, key, cchm);
            else
                return new LinkedSoftKeySelfValueNode
                    (locator, key, cchm, linkage);
        }
    }


    abstract static class SoftKeyStrongValueNode
        extends SoftKeyNode {
        volatile Object value;
        SoftKeyStrongValueNode(int locator, Object key, Object value,
                               CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            this.value = value;
        }
        public final Object getValue() { return value; }
        public final void setValue(Object value) { this.value = value; }
    }

    static final class TerminalSoftKeyStrongValueNode
        extends SoftKeyStrongValueNode {
        TerminalSoftKeyStrongValueNode(int locator,
                                       Object key, Object value,
                                       CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedSoftKeyStrongValueNode
        extends SoftKeyStrongValueNode {
        volatile Node linkage;
        LinkedSoftKeyStrongValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm,
                                     Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class SoftKeyStrongValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalSoftKeyStrongValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedSoftKeyStrongValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    abstract static class SoftKeyIntValueNode
        extends SoftKeyNode {
        volatile int value;
        SoftKeyIntValueNode(int locator, Object key, Object value,
                            CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            this.value = ((Integer)value).intValue();
        }
        public final Object getValue() { return Integer.valueOf(value); }
        public final void setValue(Object value) {
            this.value = ((Integer)value).intValue();
        }
    }

    static final class TerminalSoftKeyIntValueNode
        extends SoftKeyIntValueNode {
        TerminalSoftKeyIntValueNode(int locator,
                                    Object key, Object value,
                            CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedSoftKeyIntValueNode
        extends SoftKeyIntValueNode {
        volatile Node linkage;
        LinkedSoftKeyIntValueNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class SoftKeyIntValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalSoftKeyIntValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedSoftKeyIntValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    abstract static class SoftKeyWeakValueNode
        extends SoftKeyNode {
        volatile EmbeddedWeakReference valueRef;
        SoftKeyWeakValueNode(int locator, Object key, Object value,
                             CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            if (value != null)
                this.valueRef = new EmbeddedWeakReference(value, this);
        }
        public final Object getValue() {
            EmbeddedWeakReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedWeakReference(value, this);
        }
    }

    static final class TerminalSoftKeyWeakValueNode
        extends SoftKeyWeakValueNode {
        TerminalSoftKeyWeakValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedSoftKeyWeakValueNode
        extends SoftKeyWeakValueNode {
        volatile Node linkage;
        LinkedSoftKeyWeakValueNode(int locator,
                                   Object key, Object value,
                                   CustomConcurrentHashMap cchm,
                                   Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class SoftKeyWeakValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalSoftKeyWeakValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedSoftKeyWeakValueNode
                    (locator, key, value, cchm, linkage);
        }
    }


    abstract static class SoftKeySoftValueNode
        extends SoftKeyNode {
        volatile EmbeddedSoftReference valueRef;
        SoftKeySoftValueNode(int locator, Object key, Object value,
                             CustomConcurrentHashMap cchm) {
            super(locator, key, cchm);
            if (value != null)
                this.valueRef = new EmbeddedSoftReference(value, this);
        }
        public final Object getValue() {
            EmbeddedSoftReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedSoftReference(value, this);
        }
    }

    static final class TerminalSoftKeySoftValueNode
        extends SoftKeySoftValueNode {
        TerminalSoftKeySoftValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedSoftKeySoftValueNode
        extends SoftKeySoftValueNode {
        volatile Node linkage;
        LinkedSoftKeySoftValueNode(int locator,
                                   Object key, Object value,
                                   CustomConcurrentHashMap cchm,
                                   Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class SoftKeySoftValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalSoftKeySoftValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedSoftKeySoftValueNode
                    (locator, key, value, cchm, linkage);
        }
    }

    abstract static class IntKeyNode implements Node {
        final int key;
        IntKeyNode(int locator, Object key) {
            this.key = ((Integer)key).intValue();
        }
        public final Object get() { return Integer.valueOf(key); }
        public final int getLocator() { return spreadHash(key); }
    }


    abstract static class IntKeySelfValueNode
        extends IntKeyNode {
        IntKeySelfValueNode(int locator, Object key) {
            super(locator, key);
        }
        public final Object getValue() { return Integer.valueOf(key); }
        public final void setValue(Object value) { }
        public final void onReclamation() { }
    }

    static final class TerminalIntKeySelfValueNode
        extends IntKeySelfValueNode {
        TerminalIntKeySelfValueNode(int locator, Object key) {
            super(locator, key);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedIntKeySelfValueNode
        extends IntKeySelfValueNode {
        volatile Node linkage;
        LinkedIntKeySelfValueNode(int locator, Object key,
                                     Node linkage) {
            super(locator, key);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class IntKeySelfValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalIntKeySelfValueNode
                    (locator, key);
            else
                return new LinkedIntKeySelfValueNode
                    (locator, key, linkage);
        }
    }

    abstract static class IntKeyStrongValueNode
        extends IntKeyNode {
        volatile Object value;
        IntKeyStrongValueNode(int locator, Object key, Object value) {
            super(locator, key);
            this.value = value;
        }
        public final Object getValue() { return value; }
        public final void setValue(Object value) { this.value = value; }
        public final void onReclamation() { }
    }

    static final class TerminalIntKeyStrongValueNode
        extends IntKeyStrongValueNode {
        TerminalIntKeyStrongValueNode(int locator,
                                         Object key, Object value) {
            super(locator, key, value);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedIntKeyStrongValueNode
        extends IntKeyStrongValueNode {
        volatile Node linkage;
        LinkedIntKeyStrongValueNode(int locator,
                                       Object key, Object value,
                                       Node linkage) {
            super(locator, key, value);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class IntKeyStrongValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalIntKeyStrongValueNode
                    (locator, key, value);
            else
                return new LinkedIntKeyStrongValueNode
                    (locator, key, value, linkage);
        }
    }

    abstract static class IntKeyIntValueNode
        extends IntKeyNode {
        volatile int value;
        IntKeyIntValueNode(int locator, Object key, Object value) {
            super(locator, key);
            this.value = ((Integer)value).intValue();
        }
        public final Object getValue() { return Integer.valueOf(value); }
        public final void setValue(Object value) {
            this.value = ((Integer)value).intValue();
        }
        public final void onReclamation() { }
    }

    static final class TerminalIntKeyIntValueNode
        extends IntKeyIntValueNode {
        TerminalIntKeyIntValueNode(int locator,
                                         Object key, Object value) {
            super(locator, key, value);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedIntKeyIntValueNode
        extends IntKeyIntValueNode {
        volatile Node linkage;
        LinkedIntKeyIntValueNode(int locator,
                                       Object key, Object value,
                                       Node linkage) {
            super(locator, key, value);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class IntKeyIntValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalIntKeyIntValueNode
                    (locator, key, value);
            else
                return new LinkedIntKeyIntValueNode
                    (locator, key, value, linkage);
        }
    }

    abstract static class IntKeyWeakValueNode
        extends IntKeyNode {
        volatile EmbeddedWeakReference valueRef;
        final CustomConcurrentHashMap cchm;
        IntKeyWeakValueNode(int locator, Object key, Object value,
                               CustomConcurrentHashMap cchm) {
            super(locator, key);
            this.cchm = cchm;
            if (value != null)
                this.valueRef = new EmbeddedWeakReference(value, this);
        }
        public final void onReclamation() {
            cchm.removeIfReclaimed(this);
        }
        public final Object getValue() {
            EmbeddedWeakReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedWeakReference(value, this);
        }
    }

    static final class TerminalIntKeyWeakValueNode
        extends IntKeyWeakValueNode {
        TerminalIntKeyWeakValueNode(int locator,
                                       Object key, Object value,
                                       CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedIntKeyWeakValueNode
        extends IntKeyWeakValueNode {
        volatile Node linkage;
        LinkedIntKeyWeakValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm,
                                     Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class IntKeyWeakValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalIntKeyWeakValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedIntKeyWeakValueNode
                    (locator, key, value, cchm, linkage);
        }
    }


    abstract static class IntKeySoftValueNode
        extends IntKeyNode {
        volatile EmbeddedSoftReference valueRef;
        final CustomConcurrentHashMap cchm;
        IntKeySoftValueNode(int locator, Object key, Object value,
                            CustomConcurrentHashMap cchm) {
            super(locator, key);
            this.cchm = cchm;
            if (value != null)
                this.valueRef = new EmbeddedSoftReference(value, this);
        }
        public final void onReclamation() {
            cchm.removeIfReclaimed(this);
        }
        public final Object getValue() {
            EmbeddedSoftReference vr = valueRef;
            return (vr == null) ? null : vr.get();
        }
        public final void setValue(Object value) {
            if (value == null)
                valueRef = null;
            else
                valueRef = new EmbeddedSoftReference(value, this);
        }
    }

    static final class TerminalIntKeySoftValueNode
        extends IntKeySoftValueNode {
        TerminalIntKeySoftValueNode(int locator,
                                       Object key, Object value,
                                       CustomConcurrentHashMap cchm) {
            super(locator, key, value, cchm);
        }
        public final Node getLinkage() { return null; }
        public final void setLinkage(Node linkage) { }
    }

    static final class LinkedIntKeySoftValueNode
        extends IntKeySoftValueNode {
        volatile Node linkage;
        LinkedIntKeySoftValueNode(int locator,
                                     Object key, Object value,
                                     CustomConcurrentHashMap cchm,
                                     Node linkage) {
            super(locator, key, value, cchm);
            this.linkage = linkage;
        }
        public final Node getLinkage() { return linkage; }
        public final void setLinkage(Node linkage) { this.linkage = linkage; }
    }

    static final class IntKeySoftValueNodeFactory
        implements NodeFactory, Serializable {
        private static final long serialVersionUID = 7249069346764182397L;
        public final Node newNode(int locator,
                                  Object key, Object value,
                                  CustomConcurrentHashMap cchm,
                                  Node linkage) {
            if (linkage == null)
                return new TerminalIntKeySoftValueNode
                    (locator, key, value, cchm);
            else
                return new LinkedIntKeySoftValueNode
                    (locator, key, value, cchm, linkage);
        }
    }



    // Temporary Unsafe mechanics for preliminary release

    static final Unsafe UNSAFE;
    static final long tableBase;
    static final int tableShift;
    static final long segmentsBase;
    static final int segmentsShift;

    static {
        try {
            UNSAFE = getUnsafe();
            tableBase = UNSAFE.arrayBaseOffset(Node[].class);
            int scale = UNSAFE.arrayIndexScale(Node[].class);
            if ((scale & (scale - 1)) != 0)
                throw new Error("data type scale not a power of two");
            tableShift = 31 - Integer.numberOfLeadingZeros(scale);
            segmentsBase = UNSAFE.arrayBaseOffset(Segment[].class);
            scale = UNSAFE.arrayIndexScale(Segment[].class);
            if ((scale & (scale - 1)) != 0)
                throw new Error("data type scale not a power of two");
            segmentsShift = 31 - Integer.numberOfLeadingZeros(scale);
        } catch (Throwable e) {
            throw new RuntimeException("Could not initialize intrinsics", e);
        }
    }

    // Fenced store into segment table array. Unneeded when we have Fences
    static final void storeNode(Node[] table,
                                int i, Node r) {
        long nodeOffset = ((long) i << tableShift) + tableBase;
        UNSAFE.putOrderedObject(table, nodeOffset, r);
    }

    static final void storeSegment(Segment[] segs,
                                   int i, Segment s) {
        long segmentOffset = ((long) i << segmentsShift) + segmentsBase;
        UNSAFE.putOrderedObject(segs, segmentOffset, s);
    }

    /**
     * Returns a sun.misc.Unsafe.  Suitable for use in a 3rd party package.
     * Replace with a simple call to Unsafe.getUnsafe when integrating
     * into a jdk.
     *
     * @return a sun.misc.Unsafe
     */
    private static sun.misc.Unsafe getUnsafe() {
        try {
            return sun.misc.Unsafe.getUnsafe();
        } catch (SecurityException tryReflectionInstead) {}
        try {
            return java.security.AccessController.doPrivileged
            (new java.security.PrivilegedExceptionAction<sun.misc.Unsafe>() {
                public sun.misc.Unsafe run() throws Exception {
                    Class<sun.misc.Unsafe> k = sun.misc.Unsafe.class;
                    for (java.lang.reflect.Field f : k.getDeclaredFields()) {
                        f.setAccessible(true);
                        Object x = f.get(null);
                        if (k.isInstance(x))
                            return k.cast(x);
                    }
                    throw new NoSuchFieldError("the Unsafe");
                }});
        } catch (java.security.PrivilegedActionException e) {
            throw new RuntimeException("Could not initialize intrinsics",
                                       e.getCause());
        }
    }
}