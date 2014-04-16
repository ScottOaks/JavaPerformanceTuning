/*
 * Copyright (c) 2013,2014 Scott Oaks. All rights reserved.
 */

package net.sdo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public abstract class  AbstractParsingTest {
    public static String ITEM_ID = "ItemID";
    protected boolean isItemID = false;
    protected byte[] data;
    protected int targetItemCount;
    private ArrayList<String> itemIDs;

    protected void engineInit(RunParams rp) throws IOException {}
    protected abstract void engineRun(InputStream is) throws IOException;

    protected boolean addItemId(String s) {
        itemIDs.add(s);
        if (itemIDs.size() == targetItemCount) {
            return true;
        }
        return false;
    }
    
    public void init(RunParams rp) throws IOException {
        targetItemCount = rp.getTargetItemCount();    
        data = rp.getInput();
        engineInit(rp);
    }

    public void run() throws IOException {
        itemIDs = new ArrayList<String>((targetItemCount == Integer.MAX_VALUE) ? 128 : targetItemCount);
        isItemID = false;
        engineRun(new ByteArrayInputStream(data));
        if (targetItemCount != Integer.MAX_VALUE && targetItemCount != itemIDs.size()) {
            throw new IOException("Unexpected count in document, found " + itemIDs.size());
        }
        if (System.getProperty("debugParse") != null) {
            System.out.println(itemIDs);
        }
    }
}
