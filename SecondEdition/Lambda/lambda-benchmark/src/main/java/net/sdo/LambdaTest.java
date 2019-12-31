/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package net.sdo;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
public class LambdaTest {

    private static interface CalcInterface { int calc(); }
    private static interface IntegerInterface { int getInt(); }

    private static class LambdaIntTest1024 implements CalcInterface {
        public int calc() {
           IntegerInterface a1024 = () -> { return 1024; }; 
           IntegerInterface a1023 = () -> { return 1023; }; 
           IntegerInterface a1022 = () -> { return 1022; }; 
           IntegerInterface a1021 = () -> { return 1021; }; 
           IntegerInterface a1020 = () -> { return 1020; }; 
           IntegerInterface a1019 = () -> { return 1019; }; 
           IntegerInterface a1018 = () -> { return 1018; }; 
           IntegerInterface a1017 = () -> { return 1017; }; 
           IntegerInterface a1016 = () -> { return 1016; }; 
           IntegerInterface a1015 = () -> { return 1015; }; 
           IntegerInterface a1014 = () -> { return 1014; }; 
           IntegerInterface a1013 = () -> { return 1013; }; 
           IntegerInterface a1012 = () -> { return 1012; }; 
           IntegerInterface a1011 = () -> { return 1011; }; 
           IntegerInterface a1010 = () -> { return 1010; }; 
           IntegerInterface a1009 = () -> { return 1009; }; 
           IntegerInterface a1008 = () -> { return 1008; }; 
           IntegerInterface a1007 = () -> { return 1007; }; 
           IntegerInterface a1006 = () -> { return 1006; }; 
           IntegerInterface a1005 = () -> { return 1005; }; 
           IntegerInterface a1004 = () -> { return 1004; }; 
           IntegerInterface a1003 = () -> { return 1003; }; 
           IntegerInterface a1002 = () -> { return 1002; }; 
           IntegerInterface a1001 = () -> { return 1001; }; 
           IntegerInterface a1000 = () -> { return 1000; }; 
           IntegerInterface a999 = () -> { return 999; }; 
           IntegerInterface a998 = () -> { return 998; }; 
           IntegerInterface a997 = () -> { return 997; }; 
           IntegerInterface a996 = () -> { return 996; }; 
           IntegerInterface a995 = () -> { return 995; }; 
           IntegerInterface a994 = () -> { return 994; }; 
           IntegerInterface a993 = () -> { return 993; }; 
           IntegerInterface a992 = () -> { return 992; }; 
           IntegerInterface a991 = () -> { return 991; }; 
           IntegerInterface a990 = () -> { return 990; }; 
           IntegerInterface a989 = () -> { return 989; }; 
           IntegerInterface a988 = () -> { return 988; }; 
           IntegerInterface a987 = () -> { return 987; }; 
           IntegerInterface a986 = () -> { return 986; }; 
           IntegerInterface a985 = () -> { return 985; }; 
           IntegerInterface a984 = () -> { return 984; }; 
           IntegerInterface a983 = () -> { return 983; }; 
           IntegerInterface a982 = () -> { return 982; }; 
           IntegerInterface a981 = () -> { return 981; }; 
           IntegerInterface a980 = () -> { return 980; }; 
           IntegerInterface a979 = () -> { return 979; }; 
           IntegerInterface a978 = () -> { return 978; }; 
           IntegerInterface a977 = () -> { return 977; }; 
           IntegerInterface a976 = () -> { return 976; }; 
           IntegerInterface a975 = () -> { return 975; }; 
           IntegerInterface a974 = () -> { return 974; }; 
           IntegerInterface a973 = () -> { return 973; }; 
           IntegerInterface a972 = () -> { return 972; }; 
           IntegerInterface a971 = () -> { return 971; }; 
           IntegerInterface a970 = () -> { return 970; }; 
           IntegerInterface a969 = () -> { return 969; }; 
           IntegerInterface a968 = () -> { return 968; }; 
           IntegerInterface a967 = () -> { return 967; }; 
           IntegerInterface a966 = () -> { return 966; }; 
           IntegerInterface a965 = () -> { return 965; }; 
           IntegerInterface a964 = () -> { return 964; }; 
           IntegerInterface a963 = () -> { return 963; }; 
           IntegerInterface a962 = () -> { return 962; }; 
           IntegerInterface a961 = () -> { return 961; }; 
           IntegerInterface a960 = () -> { return 960; }; 
           IntegerInterface a959 = () -> { return 959; }; 
           IntegerInterface a958 = () -> { return 958; }; 
           IntegerInterface a957 = () -> { return 957; }; 
           IntegerInterface a956 = () -> { return 956; }; 
           IntegerInterface a955 = () -> { return 955; }; 
           IntegerInterface a954 = () -> { return 954; }; 
           IntegerInterface a953 = () -> { return 953; }; 
           IntegerInterface a952 = () -> { return 952; }; 
           IntegerInterface a951 = () -> { return 951; }; 
           IntegerInterface a950 = () -> { return 950; }; 
           IntegerInterface a949 = () -> { return 949; }; 
           IntegerInterface a948 = () -> { return 948; }; 
           IntegerInterface a947 = () -> { return 947; }; 
           IntegerInterface a946 = () -> { return 946; }; 
           IntegerInterface a945 = () -> { return 945; }; 
           IntegerInterface a944 = () -> { return 944; }; 
           IntegerInterface a943 = () -> { return 943; }; 
           IntegerInterface a942 = () -> { return 942; }; 
           IntegerInterface a941 = () -> { return 941; }; 
           IntegerInterface a940 = () -> { return 940; }; 
           IntegerInterface a939 = () -> { return 939; }; 
           IntegerInterface a938 = () -> { return 938; }; 
           IntegerInterface a937 = () -> { return 937; }; 
           IntegerInterface a936 = () -> { return 936; }; 
           IntegerInterface a935 = () -> { return 935; }; 
           IntegerInterface a934 = () -> { return 934; }; 
           IntegerInterface a933 = () -> { return 933; }; 
           IntegerInterface a932 = () -> { return 932; }; 
           IntegerInterface a931 = () -> { return 931; }; 
           IntegerInterface a930 = () -> { return 930; }; 
           IntegerInterface a929 = () -> { return 929; }; 
           IntegerInterface a928 = () -> { return 928; }; 
           IntegerInterface a927 = () -> { return 927; }; 
           IntegerInterface a926 = () -> { return 926; }; 
           IntegerInterface a925 = () -> { return 925; }; 
           IntegerInterface a924 = () -> { return 924; }; 
           IntegerInterface a923 = () -> { return 923; }; 
           IntegerInterface a922 = () -> { return 922; }; 
           IntegerInterface a921 = () -> { return 921; }; 
           IntegerInterface a920 = () -> { return 920; }; 
           IntegerInterface a919 = () -> { return 919; }; 
           IntegerInterface a918 = () -> { return 918; }; 
           IntegerInterface a917 = () -> { return 917; }; 
           IntegerInterface a916 = () -> { return 916; }; 
           IntegerInterface a915 = () -> { return 915; }; 
           IntegerInterface a914 = () -> { return 914; }; 
           IntegerInterface a913 = () -> { return 913; }; 
           IntegerInterface a912 = () -> { return 912; }; 
           IntegerInterface a911 = () -> { return 911; }; 
           IntegerInterface a910 = () -> { return 910; }; 
           IntegerInterface a909 = () -> { return 909; }; 
           IntegerInterface a908 = () -> { return 908; }; 
           IntegerInterface a907 = () -> { return 907; }; 
           IntegerInterface a906 = () -> { return 906; }; 
           IntegerInterface a905 = () -> { return 905; }; 
           IntegerInterface a904 = () -> { return 904; }; 
           IntegerInterface a903 = () -> { return 903; }; 
           IntegerInterface a902 = () -> { return 902; }; 
           IntegerInterface a901 = () -> { return 901; }; 
           IntegerInterface a900 = () -> { return 900; }; 
           IntegerInterface a899 = () -> { return 899; }; 
           IntegerInterface a898 = () -> { return 898; }; 
           IntegerInterface a897 = () -> { return 897; }; 
           IntegerInterface a896 = () -> { return 896; }; 
           IntegerInterface a895 = () -> { return 895; }; 
           IntegerInterface a894 = () -> { return 894; }; 
           IntegerInterface a893 = () -> { return 893; }; 
           IntegerInterface a892 = () -> { return 892; }; 
           IntegerInterface a891 = () -> { return 891; }; 
           IntegerInterface a890 = () -> { return 890; }; 
           IntegerInterface a889 = () -> { return 889; }; 
           IntegerInterface a888 = () -> { return 888; }; 
           IntegerInterface a887 = () -> { return 887; }; 
           IntegerInterface a886 = () -> { return 886; }; 
           IntegerInterface a885 = () -> { return 885; }; 
           IntegerInterface a884 = () -> { return 884; }; 
           IntegerInterface a883 = () -> { return 883; }; 
           IntegerInterface a882 = () -> { return 882; }; 
           IntegerInterface a881 = () -> { return 881; }; 
           IntegerInterface a880 = () -> { return 880; }; 
           IntegerInterface a879 = () -> { return 879; }; 
           IntegerInterface a878 = () -> { return 878; }; 
           IntegerInterface a877 = () -> { return 877; }; 
           IntegerInterface a876 = () -> { return 876; }; 
           IntegerInterface a875 = () -> { return 875; }; 
           IntegerInterface a874 = () -> { return 874; }; 
           IntegerInterface a873 = () -> { return 873; }; 
           IntegerInterface a872 = () -> { return 872; }; 
           IntegerInterface a871 = () -> { return 871; }; 
           IntegerInterface a870 = () -> { return 870; }; 
           IntegerInterface a869 = () -> { return 869; }; 
           IntegerInterface a868 = () -> { return 868; }; 
           IntegerInterface a867 = () -> { return 867; }; 
           IntegerInterface a866 = () -> { return 866; }; 
           IntegerInterface a865 = () -> { return 865; }; 
           IntegerInterface a864 = () -> { return 864; }; 
           IntegerInterface a863 = () -> { return 863; }; 
           IntegerInterface a862 = () -> { return 862; }; 
           IntegerInterface a861 = () -> { return 861; }; 
           IntegerInterface a860 = () -> { return 860; }; 
           IntegerInterface a859 = () -> { return 859; }; 
           IntegerInterface a858 = () -> { return 858; }; 
           IntegerInterface a857 = () -> { return 857; }; 
           IntegerInterface a856 = () -> { return 856; }; 
           IntegerInterface a855 = () -> { return 855; }; 
           IntegerInterface a854 = () -> { return 854; }; 
           IntegerInterface a853 = () -> { return 853; }; 
           IntegerInterface a852 = () -> { return 852; }; 
           IntegerInterface a851 = () -> { return 851; }; 
           IntegerInterface a850 = () -> { return 850; }; 
           IntegerInterface a849 = () -> { return 849; }; 
           IntegerInterface a848 = () -> { return 848; }; 
           IntegerInterface a847 = () -> { return 847; }; 
           IntegerInterface a846 = () -> { return 846; }; 
           IntegerInterface a845 = () -> { return 845; }; 
           IntegerInterface a844 = () -> { return 844; }; 
           IntegerInterface a843 = () -> { return 843; }; 
           IntegerInterface a842 = () -> { return 842; }; 
           IntegerInterface a841 = () -> { return 841; }; 
           IntegerInterface a840 = () -> { return 840; }; 
           IntegerInterface a839 = () -> { return 839; }; 
           IntegerInterface a838 = () -> { return 838; }; 
           IntegerInterface a837 = () -> { return 837; }; 
           IntegerInterface a836 = () -> { return 836; }; 
           IntegerInterface a835 = () -> { return 835; }; 
           IntegerInterface a834 = () -> { return 834; }; 
           IntegerInterface a833 = () -> { return 833; }; 
           IntegerInterface a832 = () -> { return 832; }; 
           IntegerInterface a831 = () -> { return 831; }; 
           IntegerInterface a830 = () -> { return 830; }; 
           IntegerInterface a829 = () -> { return 829; }; 
           IntegerInterface a828 = () -> { return 828; }; 
           IntegerInterface a827 = () -> { return 827; }; 
           IntegerInterface a826 = () -> { return 826; }; 
           IntegerInterface a825 = () -> { return 825; }; 
           IntegerInterface a824 = () -> { return 824; }; 
           IntegerInterface a823 = () -> { return 823; }; 
           IntegerInterface a822 = () -> { return 822; }; 
           IntegerInterface a821 = () -> { return 821; }; 
           IntegerInterface a820 = () -> { return 820; }; 
           IntegerInterface a819 = () -> { return 819; }; 
           IntegerInterface a818 = () -> { return 818; }; 
           IntegerInterface a817 = () -> { return 817; }; 
           IntegerInterface a816 = () -> { return 816; }; 
           IntegerInterface a815 = () -> { return 815; }; 
           IntegerInterface a814 = () -> { return 814; }; 
           IntegerInterface a813 = () -> { return 813; }; 
           IntegerInterface a812 = () -> { return 812; }; 
           IntegerInterface a811 = () -> { return 811; }; 
           IntegerInterface a810 = () -> { return 810; }; 
           IntegerInterface a809 = () -> { return 809; }; 
           IntegerInterface a808 = () -> { return 808; }; 
           IntegerInterface a807 = () -> { return 807; }; 
           IntegerInterface a806 = () -> { return 806; }; 
           IntegerInterface a805 = () -> { return 805; }; 
           IntegerInterface a804 = () -> { return 804; }; 
           IntegerInterface a803 = () -> { return 803; }; 
           IntegerInterface a802 = () -> { return 802; }; 
           IntegerInterface a801 = () -> { return 801; }; 
           IntegerInterface a800 = () -> { return 800; }; 
           IntegerInterface a799 = () -> { return 799; }; 
           IntegerInterface a798 = () -> { return 798; }; 
           IntegerInterface a797 = () -> { return 797; }; 
           IntegerInterface a796 = () -> { return 796; }; 
           IntegerInterface a795 = () -> { return 795; }; 
           IntegerInterface a794 = () -> { return 794; }; 
           IntegerInterface a793 = () -> { return 793; }; 
           IntegerInterface a792 = () -> { return 792; }; 
           IntegerInterface a791 = () -> { return 791; }; 
           IntegerInterface a790 = () -> { return 790; }; 
           IntegerInterface a789 = () -> { return 789; }; 
           IntegerInterface a788 = () -> { return 788; }; 
           IntegerInterface a787 = () -> { return 787; }; 
           IntegerInterface a786 = () -> { return 786; }; 
           IntegerInterface a785 = () -> { return 785; }; 
           IntegerInterface a784 = () -> { return 784; }; 
           IntegerInterface a783 = () -> { return 783; }; 
           IntegerInterface a782 = () -> { return 782; }; 
           IntegerInterface a781 = () -> { return 781; }; 
           IntegerInterface a780 = () -> { return 780; }; 
           IntegerInterface a779 = () -> { return 779; }; 
           IntegerInterface a778 = () -> { return 778; }; 
           IntegerInterface a777 = () -> { return 777; }; 
           IntegerInterface a776 = () -> { return 776; }; 
           IntegerInterface a775 = () -> { return 775; }; 
           IntegerInterface a774 = () -> { return 774; }; 
           IntegerInterface a773 = () -> { return 773; }; 
           IntegerInterface a772 = () -> { return 772; }; 
           IntegerInterface a771 = () -> { return 771; }; 
           IntegerInterface a770 = () -> { return 770; }; 
           IntegerInterface a769 = () -> { return 769; }; 
           IntegerInterface a768 = () -> { return 768; }; 
           IntegerInterface a767 = () -> { return 767; }; 
           IntegerInterface a766 = () -> { return 766; }; 
           IntegerInterface a765 = () -> { return 765; }; 
           IntegerInterface a764 = () -> { return 764; }; 
           IntegerInterface a763 = () -> { return 763; }; 
           IntegerInterface a762 = () -> { return 762; }; 
           IntegerInterface a761 = () -> { return 761; }; 
           IntegerInterface a760 = () -> { return 760; }; 
           IntegerInterface a759 = () -> { return 759; }; 
           IntegerInterface a758 = () -> { return 758; }; 
           IntegerInterface a757 = () -> { return 757; }; 
           IntegerInterface a756 = () -> { return 756; }; 
           IntegerInterface a755 = () -> { return 755; }; 
           IntegerInterface a754 = () -> { return 754; }; 
           IntegerInterface a753 = () -> { return 753; }; 
           IntegerInterface a752 = () -> { return 752; }; 
           IntegerInterface a751 = () -> { return 751; }; 
           IntegerInterface a750 = () -> { return 750; }; 
           IntegerInterface a749 = () -> { return 749; }; 
           IntegerInterface a748 = () -> { return 748; }; 
           IntegerInterface a747 = () -> { return 747; }; 
           IntegerInterface a746 = () -> { return 746; }; 
           IntegerInterface a745 = () -> { return 745; }; 
           IntegerInterface a744 = () -> { return 744; }; 
           IntegerInterface a743 = () -> { return 743; }; 
           IntegerInterface a742 = () -> { return 742; }; 
           IntegerInterface a741 = () -> { return 741; }; 
           IntegerInterface a740 = () -> { return 740; }; 
           IntegerInterface a739 = () -> { return 739; }; 
           IntegerInterface a738 = () -> { return 738; }; 
           IntegerInterface a737 = () -> { return 737; }; 
           IntegerInterface a736 = () -> { return 736; }; 
           IntegerInterface a735 = () -> { return 735; }; 
           IntegerInterface a734 = () -> { return 734; }; 
           IntegerInterface a733 = () -> { return 733; }; 
           IntegerInterface a732 = () -> { return 732; }; 
           IntegerInterface a731 = () -> { return 731; }; 
           IntegerInterface a730 = () -> { return 730; }; 
           IntegerInterface a729 = () -> { return 729; }; 
           IntegerInterface a728 = () -> { return 728; }; 
           IntegerInterface a727 = () -> { return 727; }; 
           IntegerInterface a726 = () -> { return 726; }; 
           IntegerInterface a725 = () -> { return 725; }; 
           IntegerInterface a724 = () -> { return 724; }; 
           IntegerInterface a723 = () -> { return 723; }; 
           IntegerInterface a722 = () -> { return 722; }; 
           IntegerInterface a721 = () -> { return 721; }; 
           IntegerInterface a720 = () -> { return 720; }; 
           IntegerInterface a719 = () -> { return 719; }; 
           IntegerInterface a718 = () -> { return 718; }; 
           IntegerInterface a717 = () -> { return 717; }; 
           IntegerInterface a716 = () -> { return 716; }; 
           IntegerInterface a715 = () -> { return 715; }; 
           IntegerInterface a714 = () -> { return 714; }; 
           IntegerInterface a713 = () -> { return 713; }; 
           IntegerInterface a712 = () -> { return 712; }; 
           IntegerInterface a711 = () -> { return 711; }; 
           IntegerInterface a710 = () -> { return 710; }; 
           IntegerInterface a709 = () -> { return 709; }; 
           IntegerInterface a708 = () -> { return 708; }; 
           IntegerInterface a707 = () -> { return 707; }; 
           IntegerInterface a706 = () -> { return 706; }; 
           IntegerInterface a705 = () -> { return 705; }; 
           IntegerInterface a704 = () -> { return 704; }; 
           IntegerInterface a703 = () -> { return 703; }; 
           IntegerInterface a702 = () -> { return 702; }; 
           IntegerInterface a701 = () -> { return 701; }; 
           IntegerInterface a700 = () -> { return 700; }; 
           IntegerInterface a699 = () -> { return 699; }; 
           IntegerInterface a698 = () -> { return 698; }; 
           IntegerInterface a697 = () -> { return 697; }; 
           IntegerInterface a696 = () -> { return 696; }; 
           IntegerInterface a695 = () -> { return 695; }; 
           IntegerInterface a694 = () -> { return 694; }; 
           IntegerInterface a693 = () -> { return 693; }; 
           IntegerInterface a692 = () -> { return 692; }; 
           IntegerInterface a691 = () -> { return 691; }; 
           IntegerInterface a690 = () -> { return 690; }; 
           IntegerInterface a689 = () -> { return 689; }; 
           IntegerInterface a688 = () -> { return 688; }; 
           IntegerInterface a687 = () -> { return 687; }; 
           IntegerInterface a686 = () -> { return 686; }; 
           IntegerInterface a685 = () -> { return 685; }; 
           IntegerInterface a684 = () -> { return 684; }; 
           IntegerInterface a683 = () -> { return 683; }; 
           IntegerInterface a682 = () -> { return 682; }; 
           IntegerInterface a681 = () -> { return 681; }; 
           IntegerInterface a680 = () -> { return 680; }; 
           IntegerInterface a679 = () -> { return 679; }; 
           IntegerInterface a678 = () -> { return 678; }; 
           IntegerInterface a677 = () -> { return 677; }; 
           IntegerInterface a676 = () -> { return 676; }; 
           IntegerInterface a675 = () -> { return 675; }; 
           IntegerInterface a674 = () -> { return 674; }; 
           IntegerInterface a673 = () -> { return 673; }; 
           IntegerInterface a672 = () -> { return 672; }; 
           IntegerInterface a671 = () -> { return 671; }; 
           IntegerInterface a670 = () -> { return 670; }; 
           IntegerInterface a669 = () -> { return 669; }; 
           IntegerInterface a668 = () -> { return 668; }; 
           IntegerInterface a667 = () -> { return 667; }; 
           IntegerInterface a666 = () -> { return 666; }; 
           IntegerInterface a665 = () -> { return 665; }; 
           IntegerInterface a664 = () -> { return 664; }; 
           IntegerInterface a663 = () -> { return 663; }; 
           IntegerInterface a662 = () -> { return 662; }; 
           IntegerInterface a661 = () -> { return 661; }; 
           IntegerInterface a660 = () -> { return 660; }; 
           IntegerInterface a659 = () -> { return 659; }; 
           IntegerInterface a658 = () -> { return 658; }; 
           IntegerInterface a657 = () -> { return 657; }; 
           IntegerInterface a656 = () -> { return 656; }; 
           IntegerInterface a655 = () -> { return 655; }; 
           IntegerInterface a654 = () -> { return 654; }; 
           IntegerInterface a653 = () -> { return 653; }; 
           IntegerInterface a652 = () -> { return 652; }; 
           IntegerInterface a651 = () -> { return 651; }; 
           IntegerInterface a650 = () -> { return 650; }; 
           IntegerInterface a649 = () -> { return 649; }; 
           IntegerInterface a648 = () -> { return 648; }; 
           IntegerInterface a647 = () -> { return 647; }; 
           IntegerInterface a646 = () -> { return 646; }; 
           IntegerInterface a645 = () -> { return 645; }; 
           IntegerInterface a644 = () -> { return 644; }; 
           IntegerInterface a643 = () -> { return 643; }; 
           IntegerInterface a642 = () -> { return 642; }; 
           IntegerInterface a641 = () -> { return 641; }; 
           IntegerInterface a640 = () -> { return 640; }; 
           IntegerInterface a639 = () -> { return 639; }; 
           IntegerInterface a638 = () -> { return 638; }; 
           IntegerInterface a637 = () -> { return 637; }; 
           IntegerInterface a636 = () -> { return 636; }; 
           IntegerInterface a635 = () -> { return 635; }; 
           IntegerInterface a634 = () -> { return 634; }; 
           IntegerInterface a633 = () -> { return 633; }; 
           IntegerInterface a632 = () -> { return 632; }; 
           IntegerInterface a631 = () -> { return 631; }; 
           IntegerInterface a630 = () -> { return 630; }; 
           IntegerInterface a629 = () -> { return 629; }; 
           IntegerInterface a628 = () -> { return 628; }; 
           IntegerInterface a627 = () -> { return 627; }; 
           IntegerInterface a626 = () -> { return 626; }; 
           IntegerInterface a625 = () -> { return 625; }; 
           IntegerInterface a624 = () -> { return 624; }; 
           IntegerInterface a623 = () -> { return 623; }; 
           IntegerInterface a622 = () -> { return 622; }; 
           IntegerInterface a621 = () -> { return 621; }; 
           IntegerInterface a620 = () -> { return 620; }; 
           IntegerInterface a619 = () -> { return 619; }; 
           IntegerInterface a618 = () -> { return 618; }; 
           IntegerInterface a617 = () -> { return 617; }; 
           IntegerInterface a616 = () -> { return 616; }; 
           IntegerInterface a615 = () -> { return 615; }; 
           IntegerInterface a614 = () -> { return 614; }; 
           IntegerInterface a613 = () -> { return 613; }; 
           IntegerInterface a612 = () -> { return 612; }; 
           IntegerInterface a611 = () -> { return 611; }; 
           IntegerInterface a610 = () -> { return 610; }; 
           IntegerInterface a609 = () -> { return 609; }; 
           IntegerInterface a608 = () -> { return 608; }; 
           IntegerInterface a607 = () -> { return 607; }; 
           IntegerInterface a606 = () -> { return 606; }; 
           IntegerInterface a605 = () -> { return 605; }; 
           IntegerInterface a604 = () -> { return 604; }; 
           IntegerInterface a603 = () -> { return 603; }; 
           IntegerInterface a602 = () -> { return 602; }; 
           IntegerInterface a601 = () -> { return 601; }; 
           IntegerInterface a600 = () -> { return 600; }; 
           IntegerInterface a599 = () -> { return 599; }; 
           IntegerInterface a598 = () -> { return 598; }; 
           IntegerInterface a597 = () -> { return 597; }; 
           IntegerInterface a596 = () -> { return 596; }; 
           IntegerInterface a595 = () -> { return 595; }; 
           IntegerInterface a594 = () -> { return 594; }; 
           IntegerInterface a593 = () -> { return 593; }; 
           IntegerInterface a592 = () -> { return 592; }; 
           IntegerInterface a591 = () -> { return 591; }; 
           IntegerInterface a590 = () -> { return 590; }; 
           IntegerInterface a589 = () -> { return 589; }; 
           IntegerInterface a588 = () -> { return 588; }; 
           IntegerInterface a587 = () -> { return 587; }; 
           IntegerInterface a586 = () -> { return 586; }; 
           IntegerInterface a585 = () -> { return 585; }; 
           IntegerInterface a584 = () -> { return 584; }; 
           IntegerInterface a583 = () -> { return 583; }; 
           IntegerInterface a582 = () -> { return 582; }; 
           IntegerInterface a581 = () -> { return 581; }; 
           IntegerInterface a580 = () -> { return 580; }; 
           IntegerInterface a579 = () -> { return 579; }; 
           IntegerInterface a578 = () -> { return 578; }; 
           IntegerInterface a577 = () -> { return 577; }; 
           IntegerInterface a576 = () -> { return 576; }; 
           IntegerInterface a575 = () -> { return 575; }; 
           IntegerInterface a574 = () -> { return 574; }; 
           IntegerInterface a573 = () -> { return 573; }; 
           IntegerInterface a572 = () -> { return 572; }; 
           IntegerInterface a571 = () -> { return 571; }; 
           IntegerInterface a570 = () -> { return 570; }; 
           IntegerInterface a569 = () -> { return 569; }; 
           IntegerInterface a568 = () -> { return 568; }; 
           IntegerInterface a567 = () -> { return 567; }; 
           IntegerInterface a566 = () -> { return 566; }; 
           IntegerInterface a565 = () -> { return 565; }; 
           IntegerInterface a564 = () -> { return 564; }; 
           IntegerInterface a563 = () -> { return 563; }; 
           IntegerInterface a562 = () -> { return 562; }; 
           IntegerInterface a561 = () -> { return 561; }; 
           IntegerInterface a560 = () -> { return 560; }; 
           IntegerInterface a559 = () -> { return 559; }; 
           IntegerInterface a558 = () -> { return 558; }; 
           IntegerInterface a557 = () -> { return 557; }; 
           IntegerInterface a556 = () -> { return 556; }; 
           IntegerInterface a555 = () -> { return 555; }; 
           IntegerInterface a554 = () -> { return 554; }; 
           IntegerInterface a553 = () -> { return 553; }; 
           IntegerInterface a552 = () -> { return 552; }; 
           IntegerInterface a551 = () -> { return 551; }; 
           IntegerInterface a550 = () -> { return 550; }; 
           IntegerInterface a549 = () -> { return 549; }; 
           IntegerInterface a548 = () -> { return 548; }; 
           IntegerInterface a547 = () -> { return 547; }; 
           IntegerInterface a546 = () -> { return 546; }; 
           IntegerInterface a545 = () -> { return 545; }; 
           IntegerInterface a544 = () -> { return 544; }; 
           IntegerInterface a543 = () -> { return 543; }; 
           IntegerInterface a542 = () -> { return 542; }; 
           IntegerInterface a541 = () -> { return 541; }; 
           IntegerInterface a540 = () -> { return 540; }; 
           IntegerInterface a539 = () -> { return 539; }; 
           IntegerInterface a538 = () -> { return 538; }; 
           IntegerInterface a537 = () -> { return 537; }; 
           IntegerInterface a536 = () -> { return 536; }; 
           IntegerInterface a535 = () -> { return 535; }; 
           IntegerInterface a534 = () -> { return 534; }; 
           IntegerInterface a533 = () -> { return 533; }; 
           IntegerInterface a532 = () -> { return 532; }; 
           IntegerInterface a531 = () -> { return 531; }; 
           IntegerInterface a530 = () -> { return 530; }; 
           IntegerInterface a529 = () -> { return 529; }; 
           IntegerInterface a528 = () -> { return 528; }; 
           IntegerInterface a527 = () -> { return 527; }; 
           IntegerInterface a526 = () -> { return 526; }; 
           IntegerInterface a525 = () -> { return 525; }; 
           IntegerInterface a524 = () -> { return 524; }; 
           IntegerInterface a523 = () -> { return 523; }; 
           IntegerInterface a522 = () -> { return 522; }; 
           IntegerInterface a521 = () -> { return 521; }; 
           IntegerInterface a520 = () -> { return 520; }; 
           IntegerInterface a519 = () -> { return 519; }; 
           IntegerInterface a518 = () -> { return 518; }; 
           IntegerInterface a517 = () -> { return 517; }; 
           IntegerInterface a516 = () -> { return 516; }; 
           IntegerInterface a515 = () -> { return 515; }; 
           IntegerInterface a514 = () -> { return 514; }; 
           IntegerInterface a513 = () -> { return 513; }; 
           IntegerInterface a512 = () -> { return 512; }; 
           IntegerInterface a511 = () -> { return 511; }; 
           IntegerInterface a510 = () -> { return 510; }; 
           IntegerInterface a509 = () -> { return 509; }; 
           IntegerInterface a508 = () -> { return 508; }; 
           IntegerInterface a507 = () -> { return 507; }; 
           IntegerInterface a506 = () -> { return 506; }; 
           IntegerInterface a505 = () -> { return 505; }; 
           IntegerInterface a504 = () -> { return 504; }; 
           IntegerInterface a503 = () -> { return 503; }; 
           IntegerInterface a502 = () -> { return 502; }; 
           IntegerInterface a501 = () -> { return 501; }; 
           IntegerInterface a500 = () -> { return 500; }; 
           IntegerInterface a499 = () -> { return 499; }; 
           IntegerInterface a498 = () -> { return 498; }; 
           IntegerInterface a497 = () -> { return 497; }; 
           IntegerInterface a496 = () -> { return 496; }; 
           IntegerInterface a495 = () -> { return 495; }; 
           IntegerInterface a494 = () -> { return 494; }; 
           IntegerInterface a493 = () -> { return 493; }; 
           IntegerInterface a492 = () -> { return 492; }; 
           IntegerInterface a491 = () -> { return 491; }; 
           IntegerInterface a490 = () -> { return 490; }; 
           IntegerInterface a489 = () -> { return 489; }; 
           IntegerInterface a488 = () -> { return 488; }; 
           IntegerInterface a487 = () -> { return 487; }; 
           IntegerInterface a486 = () -> { return 486; }; 
           IntegerInterface a485 = () -> { return 485; }; 
           IntegerInterface a484 = () -> { return 484; }; 
           IntegerInterface a483 = () -> { return 483; }; 
           IntegerInterface a482 = () -> { return 482; }; 
           IntegerInterface a481 = () -> { return 481; }; 
           IntegerInterface a480 = () -> { return 480; }; 
           IntegerInterface a479 = () -> { return 479; }; 
           IntegerInterface a478 = () -> { return 478; }; 
           IntegerInterface a477 = () -> { return 477; }; 
           IntegerInterface a476 = () -> { return 476; }; 
           IntegerInterface a475 = () -> { return 475; }; 
           IntegerInterface a474 = () -> { return 474; }; 
           IntegerInterface a473 = () -> { return 473; }; 
           IntegerInterface a472 = () -> { return 472; }; 
           IntegerInterface a471 = () -> { return 471; }; 
           IntegerInterface a470 = () -> { return 470; }; 
           IntegerInterface a469 = () -> { return 469; }; 
           IntegerInterface a468 = () -> { return 468; }; 
           IntegerInterface a467 = () -> { return 467; }; 
           IntegerInterface a466 = () -> { return 466; }; 
           IntegerInterface a465 = () -> { return 465; }; 
           IntegerInterface a464 = () -> { return 464; }; 
           IntegerInterface a463 = () -> { return 463; }; 
           IntegerInterface a462 = () -> { return 462; }; 
           IntegerInterface a461 = () -> { return 461; }; 
           IntegerInterface a460 = () -> { return 460; }; 
           IntegerInterface a459 = () -> { return 459; }; 
           IntegerInterface a458 = () -> { return 458; }; 
           IntegerInterface a457 = () -> { return 457; }; 
           IntegerInterface a456 = () -> { return 456; }; 
           IntegerInterface a455 = () -> { return 455; }; 
           IntegerInterface a454 = () -> { return 454; }; 
           IntegerInterface a453 = () -> { return 453; }; 
           IntegerInterface a452 = () -> { return 452; }; 
           IntegerInterface a451 = () -> { return 451; }; 
           IntegerInterface a450 = () -> { return 450; }; 
           IntegerInterface a449 = () -> { return 449; }; 
           IntegerInterface a448 = () -> { return 448; }; 
           IntegerInterface a447 = () -> { return 447; }; 
           IntegerInterface a446 = () -> { return 446; }; 
           IntegerInterface a445 = () -> { return 445; }; 
           IntegerInterface a444 = () -> { return 444; }; 
           IntegerInterface a443 = () -> { return 443; }; 
           IntegerInterface a442 = () -> { return 442; }; 
           IntegerInterface a441 = () -> { return 441; }; 
           IntegerInterface a440 = () -> { return 440; }; 
           IntegerInterface a439 = () -> { return 439; }; 
           IntegerInterface a438 = () -> { return 438; }; 
           IntegerInterface a437 = () -> { return 437; }; 
           IntegerInterface a436 = () -> { return 436; }; 
           IntegerInterface a435 = () -> { return 435; }; 
           IntegerInterface a434 = () -> { return 434; }; 
           IntegerInterface a433 = () -> { return 433; }; 
           IntegerInterface a432 = () -> { return 432; }; 
           IntegerInterface a431 = () -> { return 431; }; 
           IntegerInterface a430 = () -> { return 430; }; 
           IntegerInterface a429 = () -> { return 429; }; 
           IntegerInterface a428 = () -> { return 428; }; 
           IntegerInterface a427 = () -> { return 427; }; 
           IntegerInterface a426 = () -> { return 426; }; 
           IntegerInterface a425 = () -> { return 425; }; 
           IntegerInterface a424 = () -> { return 424; }; 
           IntegerInterface a423 = () -> { return 423; }; 
           IntegerInterface a422 = () -> { return 422; }; 
           IntegerInterface a421 = () -> { return 421; }; 
           IntegerInterface a420 = () -> { return 420; }; 
           IntegerInterface a419 = () -> { return 419; }; 
           IntegerInterface a418 = () -> { return 418; }; 
           IntegerInterface a417 = () -> { return 417; }; 
           IntegerInterface a416 = () -> { return 416; }; 
           IntegerInterface a415 = () -> { return 415; }; 
           IntegerInterface a414 = () -> { return 414; }; 
           IntegerInterface a413 = () -> { return 413; }; 
           IntegerInterface a412 = () -> { return 412; }; 
           IntegerInterface a411 = () -> { return 411; }; 
           IntegerInterface a410 = () -> { return 410; }; 
           IntegerInterface a409 = () -> { return 409; }; 
           IntegerInterface a408 = () -> { return 408; }; 
           IntegerInterface a407 = () -> { return 407; }; 
           IntegerInterface a406 = () -> { return 406; }; 
           IntegerInterface a405 = () -> { return 405; }; 
           IntegerInterface a404 = () -> { return 404; }; 
           IntegerInterface a403 = () -> { return 403; }; 
           IntegerInterface a402 = () -> { return 402; }; 
           IntegerInterface a401 = () -> { return 401; }; 
           IntegerInterface a400 = () -> { return 400; }; 
           IntegerInterface a399 = () -> { return 399; }; 
           IntegerInterface a398 = () -> { return 398; }; 
           IntegerInterface a397 = () -> { return 397; }; 
           IntegerInterface a396 = () -> { return 396; }; 
           IntegerInterface a395 = () -> { return 395; }; 
           IntegerInterface a394 = () -> { return 394; }; 
           IntegerInterface a393 = () -> { return 393; }; 
           IntegerInterface a392 = () -> { return 392; }; 
           IntegerInterface a391 = () -> { return 391; }; 
           IntegerInterface a390 = () -> { return 390; }; 
           IntegerInterface a389 = () -> { return 389; }; 
           IntegerInterface a388 = () -> { return 388; }; 
           IntegerInterface a387 = () -> { return 387; }; 
           IntegerInterface a386 = () -> { return 386; }; 
           IntegerInterface a385 = () -> { return 385; }; 
           IntegerInterface a384 = () -> { return 384; }; 
           IntegerInterface a383 = () -> { return 383; }; 
           IntegerInterface a382 = () -> { return 382; }; 
           IntegerInterface a381 = () -> { return 381; }; 
           IntegerInterface a380 = () -> { return 380; }; 
           IntegerInterface a379 = () -> { return 379; }; 
           IntegerInterface a378 = () -> { return 378; }; 
           IntegerInterface a377 = () -> { return 377; }; 
           IntegerInterface a376 = () -> { return 376; }; 
           IntegerInterface a375 = () -> { return 375; }; 
           IntegerInterface a374 = () -> { return 374; }; 
           IntegerInterface a373 = () -> { return 373; }; 
           IntegerInterface a372 = () -> { return 372; }; 
           IntegerInterface a371 = () -> { return 371; }; 
           IntegerInterface a370 = () -> { return 370; }; 
           IntegerInterface a369 = () -> { return 369; }; 
           IntegerInterface a368 = () -> { return 368; }; 
           IntegerInterface a367 = () -> { return 367; }; 
           IntegerInterface a366 = () -> { return 366; }; 
           IntegerInterface a365 = () -> { return 365; }; 
           IntegerInterface a364 = () -> { return 364; }; 
           IntegerInterface a363 = () -> { return 363; }; 
           IntegerInterface a362 = () -> { return 362; }; 
           IntegerInterface a361 = () -> { return 361; }; 
           IntegerInterface a360 = () -> { return 360; }; 
           IntegerInterface a359 = () -> { return 359; }; 
           IntegerInterface a358 = () -> { return 358; }; 
           IntegerInterface a357 = () -> { return 357; }; 
           IntegerInterface a356 = () -> { return 356; }; 
           IntegerInterface a355 = () -> { return 355; }; 
           IntegerInterface a354 = () -> { return 354; }; 
           IntegerInterface a353 = () -> { return 353; }; 
           IntegerInterface a352 = () -> { return 352; }; 
           IntegerInterface a351 = () -> { return 351; }; 
           IntegerInterface a350 = () -> { return 350; }; 
           IntegerInterface a349 = () -> { return 349; }; 
           IntegerInterface a348 = () -> { return 348; }; 
           IntegerInterface a347 = () -> { return 347; }; 
           IntegerInterface a346 = () -> { return 346; }; 
           IntegerInterface a345 = () -> { return 345; }; 
           IntegerInterface a344 = () -> { return 344; }; 
           IntegerInterface a343 = () -> { return 343; }; 
           IntegerInterface a342 = () -> { return 342; }; 
           IntegerInterface a341 = () -> { return 341; }; 
           IntegerInterface a340 = () -> { return 340; }; 
           IntegerInterface a339 = () -> { return 339; }; 
           IntegerInterface a338 = () -> { return 338; }; 
           IntegerInterface a337 = () -> { return 337; }; 
           IntegerInterface a336 = () -> { return 336; }; 
           IntegerInterface a335 = () -> { return 335; }; 
           IntegerInterface a334 = () -> { return 334; }; 
           IntegerInterface a333 = () -> { return 333; }; 
           IntegerInterface a332 = () -> { return 332; }; 
           IntegerInterface a331 = () -> { return 331; }; 
           IntegerInterface a330 = () -> { return 330; }; 
           IntegerInterface a329 = () -> { return 329; }; 
           IntegerInterface a328 = () -> { return 328; }; 
           IntegerInterface a327 = () -> { return 327; }; 
           IntegerInterface a326 = () -> { return 326; }; 
           IntegerInterface a325 = () -> { return 325; }; 
           IntegerInterface a324 = () -> { return 324; }; 
           IntegerInterface a323 = () -> { return 323; }; 
           IntegerInterface a322 = () -> { return 322; }; 
           IntegerInterface a321 = () -> { return 321; }; 
           IntegerInterface a320 = () -> { return 320; }; 
           IntegerInterface a319 = () -> { return 319; }; 
           IntegerInterface a318 = () -> { return 318; }; 
           IntegerInterface a317 = () -> { return 317; }; 
           IntegerInterface a316 = () -> { return 316; }; 
           IntegerInterface a315 = () -> { return 315; }; 
           IntegerInterface a314 = () -> { return 314; }; 
           IntegerInterface a313 = () -> { return 313; }; 
           IntegerInterface a312 = () -> { return 312; }; 
           IntegerInterface a311 = () -> { return 311; }; 
           IntegerInterface a310 = () -> { return 310; }; 
           IntegerInterface a309 = () -> { return 309; }; 
           IntegerInterface a308 = () -> { return 308; }; 
           IntegerInterface a307 = () -> { return 307; }; 
           IntegerInterface a306 = () -> { return 306; }; 
           IntegerInterface a305 = () -> { return 305; }; 
           IntegerInterface a304 = () -> { return 304; }; 
           IntegerInterface a303 = () -> { return 303; }; 
           IntegerInterface a302 = () -> { return 302; }; 
           IntegerInterface a301 = () -> { return 301; }; 
           IntegerInterface a300 = () -> { return 300; }; 
           IntegerInterface a299 = () -> { return 299; }; 
           IntegerInterface a298 = () -> { return 298; }; 
           IntegerInterface a297 = () -> { return 297; }; 
           IntegerInterface a296 = () -> { return 296; }; 
           IntegerInterface a295 = () -> { return 295; }; 
           IntegerInterface a294 = () -> { return 294; }; 
           IntegerInterface a293 = () -> { return 293; }; 
           IntegerInterface a292 = () -> { return 292; }; 
           IntegerInterface a291 = () -> { return 291; }; 
           IntegerInterface a290 = () -> { return 290; }; 
           IntegerInterface a289 = () -> { return 289; }; 
           IntegerInterface a288 = () -> { return 288; }; 
           IntegerInterface a287 = () -> { return 287; }; 
           IntegerInterface a286 = () -> { return 286; }; 
           IntegerInterface a285 = () -> { return 285; }; 
           IntegerInterface a284 = () -> { return 284; }; 
           IntegerInterface a283 = () -> { return 283; }; 
           IntegerInterface a282 = () -> { return 282; }; 
           IntegerInterface a281 = () -> { return 281; }; 
           IntegerInterface a280 = () -> { return 280; }; 
           IntegerInterface a279 = () -> { return 279; }; 
           IntegerInterface a278 = () -> { return 278; }; 
           IntegerInterface a277 = () -> { return 277; }; 
           IntegerInterface a276 = () -> { return 276; }; 
           IntegerInterface a275 = () -> { return 275; }; 
           IntegerInterface a274 = () -> { return 274; }; 
           IntegerInterface a273 = () -> { return 273; }; 
           IntegerInterface a272 = () -> { return 272; }; 
           IntegerInterface a271 = () -> { return 271; }; 
           IntegerInterface a270 = () -> { return 270; }; 
           IntegerInterface a269 = () -> { return 269; }; 
           IntegerInterface a268 = () -> { return 268; }; 
           IntegerInterface a267 = () -> { return 267; }; 
           IntegerInterface a266 = () -> { return 266; }; 
           IntegerInterface a265 = () -> { return 265; }; 
           IntegerInterface a264 = () -> { return 264; }; 
           IntegerInterface a263 = () -> { return 263; }; 
           IntegerInterface a262 = () -> { return 262; }; 
           IntegerInterface a261 = () -> { return 261; }; 
           IntegerInterface a260 = () -> { return 260; }; 
           IntegerInterface a259 = () -> { return 259; }; 
           IntegerInterface a258 = () -> { return 258; }; 
           IntegerInterface a257 = () -> { return 257; }; 
           IntegerInterface a256 = () -> { return 256; }; 
           IntegerInterface a255 = () -> { return 255; }; 
           IntegerInterface a254 = () -> { return 254; }; 
           IntegerInterface a253 = () -> { return 253; }; 
           IntegerInterface a252 = () -> { return 252; }; 
           IntegerInterface a251 = () -> { return 251; }; 
           IntegerInterface a250 = () -> { return 250; }; 
           IntegerInterface a249 = () -> { return 249; }; 
           IntegerInterface a248 = () -> { return 248; }; 
           IntegerInterface a247 = () -> { return 247; }; 
           IntegerInterface a246 = () -> { return 246; }; 
           IntegerInterface a245 = () -> { return 245; }; 
           IntegerInterface a244 = () -> { return 244; }; 
           IntegerInterface a243 = () -> { return 243; }; 
           IntegerInterface a242 = () -> { return 242; }; 
           IntegerInterface a241 = () -> { return 241; }; 
           IntegerInterface a240 = () -> { return 240; }; 
           IntegerInterface a239 = () -> { return 239; }; 
           IntegerInterface a238 = () -> { return 238; }; 
           IntegerInterface a237 = () -> { return 237; }; 
           IntegerInterface a236 = () -> { return 236; }; 
           IntegerInterface a235 = () -> { return 235; }; 
           IntegerInterface a234 = () -> { return 234; }; 
           IntegerInterface a233 = () -> { return 233; }; 
           IntegerInterface a232 = () -> { return 232; }; 
           IntegerInterface a231 = () -> { return 231; }; 
           IntegerInterface a230 = () -> { return 230; }; 
           IntegerInterface a229 = () -> { return 229; }; 
           IntegerInterface a228 = () -> { return 228; }; 
           IntegerInterface a227 = () -> { return 227; }; 
           IntegerInterface a226 = () -> { return 226; }; 
           IntegerInterface a225 = () -> { return 225; }; 
           IntegerInterface a224 = () -> { return 224; }; 
           IntegerInterface a223 = () -> { return 223; }; 
           IntegerInterface a222 = () -> { return 222; }; 
           IntegerInterface a221 = () -> { return 221; }; 
           IntegerInterface a220 = () -> { return 220; }; 
           IntegerInterface a219 = () -> { return 219; }; 
           IntegerInterface a218 = () -> { return 218; }; 
           IntegerInterface a217 = () -> { return 217; }; 
           IntegerInterface a216 = () -> { return 216; }; 
           IntegerInterface a215 = () -> { return 215; }; 
           IntegerInterface a214 = () -> { return 214; }; 
           IntegerInterface a213 = () -> { return 213; }; 
           IntegerInterface a212 = () -> { return 212; }; 
           IntegerInterface a211 = () -> { return 211; }; 
           IntegerInterface a210 = () -> { return 210; }; 
           IntegerInterface a209 = () -> { return 209; }; 
           IntegerInterface a208 = () -> { return 208; }; 
           IntegerInterface a207 = () -> { return 207; }; 
           IntegerInterface a206 = () -> { return 206; }; 
           IntegerInterface a205 = () -> { return 205; }; 
           IntegerInterface a204 = () -> { return 204; }; 
           IntegerInterface a203 = () -> { return 203; }; 
           IntegerInterface a202 = () -> { return 202; }; 
           IntegerInterface a201 = () -> { return 201; }; 
           IntegerInterface a200 = () -> { return 200; }; 
           IntegerInterface a199 = () -> { return 199; }; 
           IntegerInterface a198 = () -> { return 198; }; 
           IntegerInterface a197 = () -> { return 197; }; 
           IntegerInterface a196 = () -> { return 196; }; 
           IntegerInterface a195 = () -> { return 195; }; 
           IntegerInterface a194 = () -> { return 194; }; 
           IntegerInterface a193 = () -> { return 193; }; 
           IntegerInterface a192 = () -> { return 192; }; 
           IntegerInterface a191 = () -> { return 191; }; 
           IntegerInterface a190 = () -> { return 190; }; 
           IntegerInterface a189 = () -> { return 189; }; 
           IntegerInterface a188 = () -> { return 188; }; 
           IntegerInterface a187 = () -> { return 187; }; 
           IntegerInterface a186 = () -> { return 186; }; 
           IntegerInterface a185 = () -> { return 185; }; 
           IntegerInterface a184 = () -> { return 184; }; 
           IntegerInterface a183 = () -> { return 183; }; 
           IntegerInterface a182 = () -> { return 182; }; 
           IntegerInterface a181 = () -> { return 181; }; 
           IntegerInterface a180 = () -> { return 180; }; 
           IntegerInterface a179 = () -> { return 179; }; 
           IntegerInterface a178 = () -> { return 178; }; 
           IntegerInterface a177 = () -> { return 177; }; 
           IntegerInterface a176 = () -> { return 176; }; 
           IntegerInterface a175 = () -> { return 175; }; 
           IntegerInterface a174 = () -> { return 174; }; 
           IntegerInterface a173 = () -> { return 173; }; 
           IntegerInterface a172 = () -> { return 172; }; 
           IntegerInterface a171 = () -> { return 171; }; 
           IntegerInterface a170 = () -> { return 170; }; 
           IntegerInterface a169 = () -> { return 169; }; 
           IntegerInterface a168 = () -> { return 168; }; 
           IntegerInterface a167 = () -> { return 167; }; 
           IntegerInterface a166 = () -> { return 166; }; 
           IntegerInterface a165 = () -> { return 165; }; 
           IntegerInterface a164 = () -> { return 164; }; 
           IntegerInterface a163 = () -> { return 163; }; 
           IntegerInterface a162 = () -> { return 162; }; 
           IntegerInterface a161 = () -> { return 161; }; 
           IntegerInterface a160 = () -> { return 160; }; 
           IntegerInterface a159 = () -> { return 159; }; 
           IntegerInterface a158 = () -> { return 158; }; 
           IntegerInterface a157 = () -> { return 157; }; 
           IntegerInterface a156 = () -> { return 156; }; 
           IntegerInterface a155 = () -> { return 155; }; 
           IntegerInterface a154 = () -> { return 154; }; 
           IntegerInterface a153 = () -> { return 153; }; 
           IntegerInterface a152 = () -> { return 152; }; 
           IntegerInterface a151 = () -> { return 151; }; 
           IntegerInterface a150 = () -> { return 150; }; 
           IntegerInterface a149 = () -> { return 149; }; 
           IntegerInterface a148 = () -> { return 148; }; 
           IntegerInterface a147 = () -> { return 147; }; 
           IntegerInterface a146 = () -> { return 146; }; 
           IntegerInterface a145 = () -> { return 145; }; 
           IntegerInterface a144 = () -> { return 144; }; 
           IntegerInterface a143 = () -> { return 143; }; 
           IntegerInterface a142 = () -> { return 142; }; 
           IntegerInterface a141 = () -> { return 141; }; 
           IntegerInterface a140 = () -> { return 140; }; 
           IntegerInterface a139 = () -> { return 139; }; 
           IntegerInterface a138 = () -> { return 138; }; 
           IntegerInterface a137 = () -> { return 137; }; 
           IntegerInterface a136 = () -> { return 136; }; 
           IntegerInterface a135 = () -> { return 135; }; 
           IntegerInterface a134 = () -> { return 134; }; 
           IntegerInterface a133 = () -> { return 133; }; 
           IntegerInterface a132 = () -> { return 132; }; 
           IntegerInterface a131 = () -> { return 131; }; 
           IntegerInterface a130 = () -> { return 130; }; 
           IntegerInterface a129 = () -> { return 129; }; 
           IntegerInterface a128 = () -> { return 128; }; 
           IntegerInterface a127 = () -> { return 127; }; 
           IntegerInterface a126 = () -> { return 126; }; 
           IntegerInterface a125 = () -> { return 125; }; 
           IntegerInterface a124 = () -> { return 124; }; 
           IntegerInterface a123 = () -> { return 123; }; 
           IntegerInterface a122 = () -> { return 122; }; 
           IntegerInterface a121 = () -> { return 121; }; 
           IntegerInterface a120 = () -> { return 120; }; 
           IntegerInterface a119 = () -> { return 119; }; 
           IntegerInterface a118 = () -> { return 118; }; 
           IntegerInterface a117 = () -> { return 117; }; 
           IntegerInterface a116 = () -> { return 116; }; 
           IntegerInterface a115 = () -> { return 115; }; 
           IntegerInterface a114 = () -> { return 114; }; 
           IntegerInterface a113 = () -> { return 113; }; 
           IntegerInterface a112 = () -> { return 112; }; 
           IntegerInterface a111 = () -> { return 111; }; 
           IntegerInterface a110 = () -> { return 110; }; 
           IntegerInterface a109 = () -> { return 109; }; 
           IntegerInterface a108 = () -> { return 108; }; 
           IntegerInterface a107 = () -> { return 107; }; 
           IntegerInterface a106 = () -> { return 106; }; 
           IntegerInterface a105 = () -> { return 105; }; 
           IntegerInterface a104 = () -> { return 104; }; 
           IntegerInterface a103 = () -> { return 103; }; 
           IntegerInterface a102 = () -> { return 102; }; 
           IntegerInterface a101 = () -> { return 101; }; 
           IntegerInterface a100 = () -> { return 100; }; 
           IntegerInterface a99 = () -> { return 99; }; 
           IntegerInterface a98 = () -> { return 98; }; 
           IntegerInterface a97 = () -> { return 97; }; 
           IntegerInterface a96 = () -> { return 96; }; 
           IntegerInterface a95 = () -> { return 95; }; 
           IntegerInterface a94 = () -> { return 94; }; 
           IntegerInterface a93 = () -> { return 93; }; 
           IntegerInterface a92 = () -> { return 92; }; 
           IntegerInterface a91 = () -> { return 91; }; 
           IntegerInterface a90 = () -> { return 90; }; 
           IntegerInterface a89 = () -> { return 89; }; 
           IntegerInterface a88 = () -> { return 88; }; 
           IntegerInterface a87 = () -> { return 87; }; 
           IntegerInterface a86 = () -> { return 86; }; 
           IntegerInterface a85 = () -> { return 85; }; 
           IntegerInterface a84 = () -> { return 84; }; 
           IntegerInterface a83 = () -> { return 83; }; 
           IntegerInterface a82 = () -> { return 82; }; 
           IntegerInterface a81 = () -> { return 81; }; 
           IntegerInterface a80 = () -> { return 80; }; 
           IntegerInterface a79 = () -> { return 79; }; 
           IntegerInterface a78 = () -> { return 78; }; 
           IntegerInterface a77 = () -> { return 77; }; 
           IntegerInterface a76 = () -> { return 76; }; 
           IntegerInterface a75 = () -> { return 75; }; 
           IntegerInterface a74 = () -> { return 74; }; 
           IntegerInterface a73 = () -> { return 73; }; 
           IntegerInterface a72 = () -> { return 72; }; 
           IntegerInterface a71 = () -> { return 71; }; 
           IntegerInterface a70 = () -> { return 70; }; 
           IntegerInterface a69 = () -> { return 69; }; 
           IntegerInterface a68 = () -> { return 68; }; 
           IntegerInterface a67 = () -> { return 67; }; 
           IntegerInterface a66 = () -> { return 66; }; 
           IntegerInterface a65 = () -> { return 65; }; 
           IntegerInterface a64 = () -> { return 64; }; 
           IntegerInterface a63 = () -> { return 63; }; 
           IntegerInterface a62 = () -> { return 62; }; 
           IntegerInterface a61 = () -> { return 61; }; 
           IntegerInterface a60 = () -> { return 60; }; 
           IntegerInterface a59 = () -> { return 59; }; 
           IntegerInterface a58 = () -> { return 58; }; 
           IntegerInterface a57 = () -> { return 57; }; 
           IntegerInterface a56 = () -> { return 56; }; 
           IntegerInterface a55 = () -> { return 55; }; 
           IntegerInterface a54 = () -> { return 54; }; 
           IntegerInterface a53 = () -> { return 53; }; 
           IntegerInterface a52 = () -> { return 52; }; 
           IntegerInterface a51 = () -> { return 51; }; 
           IntegerInterface a50 = () -> { return 50; }; 
           IntegerInterface a49 = () -> { return 49; }; 
           IntegerInterface a48 = () -> { return 48; }; 
           IntegerInterface a47 = () -> { return 47; }; 
           IntegerInterface a46 = () -> { return 46; }; 
           IntegerInterface a45 = () -> { return 45; }; 
           IntegerInterface a44 = () -> { return 44; }; 
           IntegerInterface a43 = () -> { return 43; }; 
           IntegerInterface a42 = () -> { return 42; }; 
           IntegerInterface a41 = () -> { return 41; }; 
           IntegerInterface a40 = () -> { return 40; }; 
           IntegerInterface a39 = () -> { return 39; }; 
           IntegerInterface a38 = () -> { return 38; }; 
           IntegerInterface a37 = () -> { return 37; }; 
           IntegerInterface a36 = () -> { return 36; }; 
           IntegerInterface a35 = () -> { return 35; }; 
           IntegerInterface a34 = () -> { return 34; }; 
           IntegerInterface a33 = () -> { return 33; }; 
           IntegerInterface a32 = () -> { return 32; }; 
           IntegerInterface a31 = () -> { return 31; }; 
           IntegerInterface a30 = () -> { return 30; }; 
           IntegerInterface a29 = () -> { return 29; }; 
           IntegerInterface a28 = () -> { return 28; }; 
           IntegerInterface a27 = () -> { return 27; }; 
           IntegerInterface a26 = () -> { return 26; }; 
           IntegerInterface a25 = () -> { return 25; }; 
           IntegerInterface a24 = () -> { return 24; }; 
           IntegerInterface a23 = () -> { return 23; }; 
           IntegerInterface a22 = () -> { return 22; }; 
           IntegerInterface a21 = () -> { return 21; }; 
           IntegerInterface a20 = () -> { return 20; }; 
           IntegerInterface a19 = () -> { return 19; }; 
           IntegerInterface a18 = () -> { return 18; }; 
           IntegerInterface a17 = () -> { return 17; }; 
           IntegerInterface a16 = () -> { return 16; }; 
           IntegerInterface a15 = () -> { return 15; }; 
           IntegerInterface a14 = () -> { return 14; }; 
           IntegerInterface a13 = () -> { return 13; }; 
           IntegerInterface a12 = () -> { return 12; }; 
           IntegerInterface a11 = () -> { return 11; }; 
           IntegerInterface a10 = () -> { return 10; }; 
           IntegerInterface a9 = () -> { return 9; }; 
           IntegerInterface a8 = () -> { return 8; }; 
           IntegerInterface a7 = () -> { return 7; }; 
           IntegerInterface a6 = () -> { return 6; }; 
           IntegerInterface a5 = () -> { return 5; }; 
           IntegerInterface a4 = () -> { return 4; }; 
           IntegerInterface a3 = () -> { return 3; }; 
           IntegerInterface a2 = () -> { return 2; }; 
           IntegerInterface a1 = () -> { return 1; }; 
            return 
                a1024.getInt() +
                a1023.getInt() +
                a1022.getInt() +
                a1021.getInt() +
                a1020.getInt() +
                a1019.getInt() +
                a1018.getInt() +
                a1017.getInt() +
                a1016.getInt() +
                a1015.getInt() +
                a1014.getInt() +
                a1013.getInt() +
                a1012.getInt() +
                a1011.getInt() +
                a1010.getInt() +
                a1009.getInt() +
                a1008.getInt() +
                a1007.getInt() +
                a1006.getInt() +
                a1005.getInt() +
                a1004.getInt() +
                a1003.getInt() +
                a1002.getInt() +
                a1001.getInt() +
                a1000.getInt() +
                a999.getInt() +
                a998.getInt() +
                a997.getInt() +
                a996.getInt() +
                a995.getInt() +
                a994.getInt() +
                a993.getInt() +
                a992.getInt() +
                a991.getInt() +
                a990.getInt() +
                a989.getInt() +
                a988.getInt() +
                a987.getInt() +
                a986.getInt() +
                a985.getInt() +
                a984.getInt() +
                a983.getInt() +
                a982.getInt() +
                a981.getInt() +
                a980.getInt() +
                a979.getInt() +
                a978.getInt() +
                a977.getInt() +
                a976.getInt() +
                a975.getInt() +
                a974.getInt() +
                a973.getInt() +
                a972.getInt() +
                a971.getInt() +
                a970.getInt() +
                a969.getInt() +
                a968.getInt() +
                a967.getInt() +
                a966.getInt() +
                a965.getInt() +
                a964.getInt() +
                a963.getInt() +
                a962.getInt() +
                a961.getInt() +
                a960.getInt() +
                a959.getInt() +
                a958.getInt() +
                a957.getInt() +
                a956.getInt() +
                a955.getInt() +
                a954.getInt() +
                a953.getInt() +
                a952.getInt() +
                a951.getInt() +
                a950.getInt() +
                a949.getInt() +
                a948.getInt() +
                a947.getInt() +
                a946.getInt() +
                a945.getInt() +
                a944.getInt() +
                a943.getInt() +
                a942.getInt() +
                a941.getInt() +
                a940.getInt() +
                a939.getInt() +
                a938.getInt() +
                a937.getInt() +
                a936.getInt() +
                a935.getInt() +
                a934.getInt() +
                a933.getInt() +
                a932.getInt() +
                a931.getInt() +
                a930.getInt() +
                a929.getInt() +
                a928.getInt() +
                a927.getInt() +
                a926.getInt() +
                a925.getInt() +
                a924.getInt() +
                a923.getInt() +
                a922.getInt() +
                a921.getInt() +
                a920.getInt() +
                a919.getInt() +
                a918.getInt() +
                a917.getInt() +
                a916.getInt() +
                a915.getInt() +
                a914.getInt() +
                a913.getInt() +
                a912.getInt() +
                a911.getInt() +
                a910.getInt() +
                a909.getInt() +
                a908.getInt() +
                a907.getInt() +
                a906.getInt() +
                a905.getInt() +
                a904.getInt() +
                a903.getInt() +
                a902.getInt() +
                a901.getInt() +
                a900.getInt() +
                a899.getInt() +
                a898.getInt() +
                a897.getInt() +
                a896.getInt() +
                a895.getInt() +
                a894.getInt() +
                a893.getInt() +
                a892.getInt() +
                a891.getInt() +
                a890.getInt() +
                a889.getInt() +
                a888.getInt() +
                a887.getInt() +
                a886.getInt() +
                a885.getInt() +
                a884.getInt() +
                a883.getInt() +
                a882.getInt() +
                a881.getInt() +
                a880.getInt() +
                a879.getInt() +
                a878.getInt() +
                a877.getInt() +
                a876.getInt() +
                a875.getInt() +
                a874.getInt() +
                a873.getInt() +
                a872.getInt() +
                a871.getInt() +
                a870.getInt() +
                a869.getInt() +
                a868.getInt() +
                a867.getInt() +
                a866.getInt() +
                a865.getInt() +
                a864.getInt() +
                a863.getInt() +
                a862.getInt() +
                a861.getInt() +
                a860.getInt() +
                a859.getInt() +
                a858.getInt() +
                a857.getInt() +
                a856.getInt() +
                a855.getInt() +
                a854.getInt() +
                a853.getInt() +
                a852.getInt() +
                a851.getInt() +
                a850.getInt() +
                a849.getInt() +
                a848.getInt() +
                a847.getInt() +
                a846.getInt() +
                a845.getInt() +
                a844.getInt() +
                a843.getInt() +
                a842.getInt() +
                a841.getInt() +
                a840.getInt() +
                a839.getInt() +
                a838.getInt() +
                a837.getInt() +
                a836.getInt() +
                a835.getInt() +
                a834.getInt() +
                a833.getInt() +
                a832.getInt() +
                a831.getInt() +
                a830.getInt() +
                a829.getInt() +
                a828.getInt() +
                a827.getInt() +
                a826.getInt() +
                a825.getInt() +
                a824.getInt() +
                a823.getInt() +
                a822.getInt() +
                a821.getInt() +
                a820.getInt() +
                a819.getInt() +
                a818.getInt() +
                a817.getInt() +
                a816.getInt() +
                a815.getInt() +
                a814.getInt() +
                a813.getInt() +
                a812.getInt() +
                a811.getInt() +
                a810.getInt() +
                a809.getInt() +
                a808.getInt() +
                a807.getInt() +
                a806.getInt() +
                a805.getInt() +
                a804.getInt() +
                a803.getInt() +
                a802.getInt() +
                a801.getInt() +
                a800.getInt() +
                a799.getInt() +
                a798.getInt() +
                a797.getInt() +
                a796.getInt() +
                a795.getInt() +
                a794.getInt() +
                a793.getInt() +
                a792.getInt() +
                a791.getInt() +
                a790.getInt() +
                a789.getInt() +
                a788.getInt() +
                a787.getInt() +
                a786.getInt() +
                a785.getInt() +
                a784.getInt() +
                a783.getInt() +
                a782.getInt() +
                a781.getInt() +
                a780.getInt() +
                a779.getInt() +
                a778.getInt() +
                a777.getInt() +
                a776.getInt() +
                a775.getInt() +
                a774.getInt() +
                a773.getInt() +
                a772.getInt() +
                a771.getInt() +
                a770.getInt() +
                a769.getInt() +
                a768.getInt() +
                a767.getInt() +
                a766.getInt() +
                a765.getInt() +
                a764.getInt() +
                a763.getInt() +
                a762.getInt() +
                a761.getInt() +
                a760.getInt() +
                a759.getInt() +
                a758.getInt() +
                a757.getInt() +
                a756.getInt() +
                a755.getInt() +
                a754.getInt() +
                a753.getInt() +
                a752.getInt() +
                a751.getInt() +
                a750.getInt() +
                a749.getInt() +
                a748.getInt() +
                a747.getInt() +
                a746.getInt() +
                a745.getInt() +
                a744.getInt() +
                a743.getInt() +
                a742.getInt() +
                a741.getInt() +
                a740.getInt() +
                a739.getInt() +
                a738.getInt() +
                a737.getInt() +
                a736.getInt() +
                a735.getInt() +
                a734.getInt() +
                a733.getInt() +
                a732.getInt() +
                a731.getInt() +
                a730.getInt() +
                a729.getInt() +
                a728.getInt() +
                a727.getInt() +
                a726.getInt() +
                a725.getInt() +
                a724.getInt() +
                a723.getInt() +
                a722.getInt() +
                a721.getInt() +
                a720.getInt() +
                a719.getInt() +
                a718.getInt() +
                a717.getInt() +
                a716.getInt() +
                a715.getInt() +
                a714.getInt() +
                a713.getInt() +
                a712.getInt() +
                a711.getInt() +
                a710.getInt() +
                a709.getInt() +
                a708.getInt() +
                a707.getInt() +
                a706.getInt() +
                a705.getInt() +
                a704.getInt() +
                a703.getInt() +
                a702.getInt() +
                a701.getInt() +
                a700.getInt() +
                a699.getInt() +
                a698.getInt() +
                a697.getInt() +
                a696.getInt() +
                a695.getInt() +
                a694.getInt() +
                a693.getInt() +
                a692.getInt() +
                a691.getInt() +
                a690.getInt() +
                a689.getInt() +
                a688.getInt() +
                a687.getInt() +
                a686.getInt() +
                a685.getInt() +
                a684.getInt() +
                a683.getInt() +
                a682.getInt() +
                a681.getInt() +
                a680.getInt() +
                a679.getInt() +
                a678.getInt() +
                a677.getInt() +
                a676.getInt() +
                a675.getInt() +
                a674.getInt() +
                a673.getInt() +
                a672.getInt() +
                a671.getInt() +
                a670.getInt() +
                a669.getInt() +
                a668.getInt() +
                a667.getInt() +
                a666.getInt() +
                a665.getInt() +
                a664.getInt() +
                a663.getInt() +
                a662.getInt() +
                a661.getInt() +
                a660.getInt() +
                a659.getInt() +
                a658.getInt() +
                a657.getInt() +
                a656.getInt() +
                a655.getInt() +
                a654.getInt() +
                a653.getInt() +
                a652.getInt() +
                a651.getInt() +
                a650.getInt() +
                a649.getInt() +
                a648.getInt() +
                a647.getInt() +
                a646.getInt() +
                a645.getInt() +
                a644.getInt() +
                a643.getInt() +
                a642.getInt() +
                a641.getInt() +
                a640.getInt() +
                a639.getInt() +
                a638.getInt() +
                a637.getInt() +
                a636.getInt() +
                a635.getInt() +
                a634.getInt() +
                a633.getInt() +
                a632.getInt() +
                a631.getInt() +
                a630.getInt() +
                a629.getInt() +
                a628.getInt() +
                a627.getInt() +
                a626.getInt() +
                a625.getInt() +
                a624.getInt() +
                a623.getInt() +
                a622.getInt() +
                a621.getInt() +
                a620.getInt() +
                a619.getInt() +
                a618.getInt() +
                a617.getInt() +
                a616.getInt() +
                a615.getInt() +
                a614.getInt() +
                a613.getInt() +
                a612.getInt() +
                a611.getInt() +
                a610.getInt() +
                a609.getInt() +
                a608.getInt() +
                a607.getInt() +
                a606.getInt() +
                a605.getInt() +
                a604.getInt() +
                a603.getInt() +
                a602.getInt() +
                a601.getInt() +
                a600.getInt() +
                a599.getInt() +
                a598.getInt() +
                a597.getInt() +
                a596.getInt() +
                a595.getInt() +
                a594.getInt() +
                a593.getInt() +
                a592.getInt() +
                a591.getInt() +
                a590.getInt() +
                a589.getInt() +
                a588.getInt() +
                a587.getInt() +
                a586.getInt() +
                a585.getInt() +
                a584.getInt() +
                a583.getInt() +
                a582.getInt() +
                a581.getInt() +
                a580.getInt() +
                a579.getInt() +
                a578.getInt() +
                a577.getInt() +
                a576.getInt() +
                a575.getInt() +
                a574.getInt() +
                a573.getInt() +
                a572.getInt() +
                a571.getInt() +
                a570.getInt() +
                a569.getInt() +
                a568.getInt() +
                a567.getInt() +
                a566.getInt() +
                a565.getInt() +
                a564.getInt() +
                a563.getInt() +
                a562.getInt() +
                a561.getInt() +
                a560.getInt() +
                a559.getInt() +
                a558.getInt() +
                a557.getInt() +
                a556.getInt() +
                a555.getInt() +
                a554.getInt() +
                a553.getInt() +
                a552.getInt() +
                a551.getInt() +
                a550.getInt() +
                a549.getInt() +
                a548.getInt() +
                a547.getInt() +
                a546.getInt() +
                a545.getInt() +
                a544.getInt() +
                a543.getInt() +
                a542.getInt() +
                a541.getInt() +
                a540.getInt() +
                a539.getInt() +
                a538.getInt() +
                a537.getInt() +
                a536.getInt() +
                a535.getInt() +
                a534.getInt() +
                a533.getInt() +
                a532.getInt() +
                a531.getInt() +
                a530.getInt() +
                a529.getInt() +
                a528.getInt() +
                a527.getInt() +
                a526.getInt() +
                a525.getInt() +
                a524.getInt() +
                a523.getInt() +
                a522.getInt() +
                a521.getInt() +
                a520.getInt() +
                a519.getInt() +
                a518.getInt() +
                a517.getInt() +
                a516.getInt() +
                a515.getInt() +
                a514.getInt() +
                a513.getInt() +
                a512.getInt() +
                a511.getInt() +
                a510.getInt() +
                a509.getInt() +
                a508.getInt() +
                a507.getInt() +
                a506.getInt() +
                a505.getInt() +
                a504.getInt() +
                a503.getInt() +
                a502.getInt() +
                a501.getInt() +
                a500.getInt() +
                a499.getInt() +
                a498.getInt() +
                a497.getInt() +
                a496.getInt() +
                a495.getInt() +
                a494.getInt() +
                a493.getInt() +
                a492.getInt() +
                a491.getInt() +
                a490.getInt() +
                a489.getInt() +
                a488.getInt() +
                a487.getInt() +
                a486.getInt() +
                a485.getInt() +
                a484.getInt() +
                a483.getInt() +
                a482.getInt() +
                a481.getInt() +
                a480.getInt() +
                a479.getInt() +
                a478.getInt() +
                a477.getInt() +
                a476.getInt() +
                a475.getInt() +
                a474.getInt() +
                a473.getInt() +
                a472.getInt() +
                a471.getInt() +
                a470.getInt() +
                a469.getInt() +
                a468.getInt() +
                a467.getInt() +
                a466.getInt() +
                a465.getInt() +
                a464.getInt() +
                a463.getInt() +
                a462.getInt() +
                a461.getInt() +
                a460.getInt() +
                a459.getInt() +
                a458.getInt() +
                a457.getInt() +
                a456.getInt() +
                a455.getInt() +
                a454.getInt() +
                a453.getInt() +
                a452.getInt() +
                a451.getInt() +
                a450.getInt() +
                a449.getInt() +
                a448.getInt() +
                a447.getInt() +
                a446.getInt() +
                a445.getInt() +
                a444.getInt() +
                a443.getInt() +
                a442.getInt() +
                a441.getInt() +
                a440.getInt() +
                a439.getInt() +
                a438.getInt() +
                a437.getInt() +
                a436.getInt() +
                a435.getInt() +
                a434.getInt() +
                a433.getInt() +
                a432.getInt() +
                a431.getInt() +
                a430.getInt() +
                a429.getInt() +
                a428.getInt() +
                a427.getInt() +
                a426.getInt() +
                a425.getInt() +
                a424.getInt() +
                a423.getInt() +
                a422.getInt() +
                a421.getInt() +
                a420.getInt() +
                a419.getInt() +
                a418.getInt() +
                a417.getInt() +
                a416.getInt() +
                a415.getInt() +
                a414.getInt() +
                a413.getInt() +
                a412.getInt() +
                a411.getInt() +
                a410.getInt() +
                a409.getInt() +
                a408.getInt() +
                a407.getInt() +
                a406.getInt() +
                a405.getInt() +
                a404.getInt() +
                a403.getInt() +
                a402.getInt() +
                a401.getInt() +
                a400.getInt() +
                a399.getInt() +
                a398.getInt() +
                a397.getInt() +
                a396.getInt() +
                a395.getInt() +
                a394.getInt() +
                a393.getInt() +
                a392.getInt() +
                a391.getInt() +
                a390.getInt() +
                a389.getInt() +
                a388.getInt() +
                a387.getInt() +
                a386.getInt() +
                a385.getInt() +
                a384.getInt() +
                a383.getInt() +
                a382.getInt() +
                a381.getInt() +
                a380.getInt() +
                a379.getInt() +
                a378.getInt() +
                a377.getInt() +
                a376.getInt() +
                a375.getInt() +
                a374.getInt() +
                a373.getInt() +
                a372.getInt() +
                a371.getInt() +
                a370.getInt() +
                a369.getInt() +
                a368.getInt() +
                a367.getInt() +
                a366.getInt() +
                a365.getInt() +
                a364.getInt() +
                a363.getInt() +
                a362.getInt() +
                a361.getInt() +
                a360.getInt() +
                a359.getInt() +
                a358.getInt() +
                a357.getInt() +
                a356.getInt() +
                a355.getInt() +
                a354.getInt() +
                a353.getInt() +
                a352.getInt() +
                a351.getInt() +
                a350.getInt() +
                a349.getInt() +
                a348.getInt() +
                a347.getInt() +
                a346.getInt() +
                a345.getInt() +
                a344.getInt() +
                a343.getInt() +
                a342.getInt() +
                a341.getInt() +
                a340.getInt() +
                a339.getInt() +
                a338.getInt() +
                a337.getInt() +
                a336.getInt() +
                a335.getInt() +
                a334.getInt() +
                a333.getInt() +
                a332.getInt() +
                a331.getInt() +
                a330.getInt() +
                a329.getInt() +
                a328.getInt() +
                a327.getInt() +
                a326.getInt() +
                a325.getInt() +
                a324.getInt() +
                a323.getInt() +
                a322.getInt() +
                a321.getInt() +
                a320.getInt() +
                a319.getInt() +
                a318.getInt() +
                a317.getInt() +
                a316.getInt() +
                a315.getInt() +
                a314.getInt() +
                a313.getInt() +
                a312.getInt() +
                a311.getInt() +
                a310.getInt() +
                a309.getInt() +
                a308.getInt() +
                a307.getInt() +
                a306.getInt() +
                a305.getInt() +
                a304.getInt() +
                a303.getInt() +
                a302.getInt() +
                a301.getInt() +
                a300.getInt() +
                a299.getInt() +
                a298.getInt() +
                a297.getInt() +
                a296.getInt() +
                a295.getInt() +
                a294.getInt() +
                a293.getInt() +
                a292.getInt() +
                a291.getInt() +
                a290.getInt() +
                a289.getInt() +
                a288.getInt() +
                a287.getInt() +
                a286.getInt() +
                a285.getInt() +
                a284.getInt() +
                a283.getInt() +
                a282.getInt() +
                a281.getInt() +
                a280.getInt() +
                a279.getInt() +
                a278.getInt() +
                a277.getInt() +
                a276.getInt() +
                a275.getInt() +
                a274.getInt() +
                a273.getInt() +
                a272.getInt() +
                a271.getInt() +
                a270.getInt() +
                a269.getInt() +
                a268.getInt() +
                a267.getInt() +
                a266.getInt() +
                a265.getInt() +
                a264.getInt() +
                a263.getInt() +
                a262.getInt() +
                a261.getInt() +
                a260.getInt() +
                a259.getInt() +
                a258.getInt() +
                a257.getInt() +
                a256.getInt() +
                a255.getInt() +
                a254.getInt() +
                a253.getInt() +
                a252.getInt() +
                a251.getInt() +
                a250.getInt() +
                a249.getInt() +
                a248.getInt() +
                a247.getInt() +
                a246.getInt() +
                a245.getInt() +
                a244.getInt() +
                a243.getInt() +
                a242.getInt() +
                a241.getInt() +
                a240.getInt() +
                a239.getInt() +
                a238.getInt() +
                a237.getInt() +
                a236.getInt() +
                a235.getInt() +
                a234.getInt() +
                a233.getInt() +
                a232.getInt() +
                a231.getInt() +
                a230.getInt() +
                a229.getInt() +
                a228.getInt() +
                a227.getInt() +
                a226.getInt() +
                a225.getInt() +
                a224.getInt() +
                a223.getInt() +
                a222.getInt() +
                a221.getInt() +
                a220.getInt() +
                a219.getInt() +
                a218.getInt() +
                a217.getInt() +
                a216.getInt() +
                a215.getInt() +
                a214.getInt() +
                a213.getInt() +
                a212.getInt() +
                a211.getInt() +
                a210.getInt() +
                a209.getInt() +
                a208.getInt() +
                a207.getInt() +
                a206.getInt() +
                a205.getInt() +
                a204.getInt() +
                a203.getInt() +
                a202.getInt() +
                a201.getInt() +
                a200.getInt() +
                a199.getInt() +
                a198.getInt() +
                a197.getInt() +
                a196.getInt() +
                a195.getInt() +
                a194.getInt() +
                a193.getInt() +
                a192.getInt() +
                a191.getInt() +
                a190.getInt() +
                a189.getInt() +
                a188.getInt() +
                a187.getInt() +
                a186.getInt() +
                a185.getInt() +
                a184.getInt() +
                a183.getInt() +
                a182.getInt() +
                a181.getInt() +
                a180.getInt() +
                a179.getInt() +
                a178.getInt() +
                a177.getInt() +
                a176.getInt() +
                a175.getInt() +
                a174.getInt() +
                a173.getInt() +
                a172.getInt() +
                a171.getInt() +
                a170.getInt() +
                a169.getInt() +
                a168.getInt() +
                a167.getInt() +
                a166.getInt() +
                a165.getInt() +
                a164.getInt() +
                a163.getInt() +
                a162.getInt() +
                a161.getInt() +
                a160.getInt() +
                a159.getInt() +
                a158.getInt() +
                a157.getInt() +
                a156.getInt() +
                a155.getInt() +
                a154.getInt() +
                a153.getInt() +
                a152.getInt() +
                a151.getInt() +
                a150.getInt() +
                a149.getInt() +
                a148.getInt() +
                a147.getInt() +
                a146.getInt() +
                a145.getInt() +
                a144.getInt() +
                a143.getInt() +
                a142.getInt() +
                a141.getInt() +
                a140.getInt() +
                a139.getInt() +
                a138.getInt() +
                a137.getInt() +
                a136.getInt() +
                a135.getInt() +
                a134.getInt() +
                a133.getInt() +
                a132.getInt() +
                a131.getInt() +
                a130.getInt() +
                a129.getInt() +
                a128.getInt() +
                a127.getInt() +
                a126.getInt() +
                a125.getInt() +
                a124.getInt() +
                a123.getInt() +
                a122.getInt() +
                a121.getInt() +
                a120.getInt() +
                a119.getInt() +
                a118.getInt() +
                a117.getInt() +
                a116.getInt() +
                a115.getInt() +
                a114.getInt() +
                a113.getInt() +
                a112.getInt() +
                a111.getInt() +
                a110.getInt() +
                a109.getInt() +
                a108.getInt() +
                a107.getInt() +
                a106.getInt() +
                a105.getInt() +
                a104.getInt() +
                a103.getInt() +
                a102.getInt() +
                a101.getInt() +
                a100.getInt() +
                a99.getInt() +
                a98.getInt() +
                a97.getInt() +
                a96.getInt() +
                a95.getInt() +
                a94.getInt() +
                a93.getInt() +
                a92.getInt() +
                a91.getInt() +
                a90.getInt() +
                a89.getInt() +
                a88.getInt() +
                a87.getInt() +
                a86.getInt() +
                a85.getInt() +
                a84.getInt() +
                a83.getInt() +
                a82.getInt() +
                a81.getInt() +
                a80.getInt() +
                a79.getInt() +
                a78.getInt() +
                a77.getInt() +
                a76.getInt() +
                a75.getInt() +
                a74.getInt() +
                a73.getInt() +
                a72.getInt() +
                a71.getInt() +
                a70.getInt() +
                a69.getInt() +
                a68.getInt() +
                a67.getInt() +
                a66.getInt() +
                a65.getInt() +
                a64.getInt() +
                a63.getInt() +
                a62.getInt() +
                a61.getInt() +
                a60.getInt() +
                a59.getInt() +
                a58.getInt() +
                a57.getInt() +
                a56.getInt() +
                a55.getInt() +
                a54.getInt() +
                a53.getInt() +
                a52.getInt() +
                a51.getInt() +
                a50.getInt() +
                a49.getInt() +
                a48.getInt() +
                a47.getInt() +
                a46.getInt() +
                a45.getInt() +
                a44.getInt() +
                a43.getInt() +
                a42.getInt() +
                a41.getInt() +
                a40.getInt() +
                a39.getInt() +
                a38.getInt() +
                a37.getInt() +
                a36.getInt() +
                a35.getInt() +
                a34.getInt() +
                a33.getInt() +
                a32.getInt() +
                a31.getInt() +
                a30.getInt() +
                a29.getInt() +
                a28.getInt() +
                a27.getInt() +
                a26.getInt() +
                a25.getInt() +
                a24.getInt() +
                a23.getInt() +
                a22.getInt() +
                a21.getInt() +
                a20.getInt() +
                a19.getInt() +
                a18.getInt() +
                a17.getInt() +
                a16.getInt() +
                a15.getInt() +
                a14.getInt() +
                a13.getInt() +
                a12.getInt() +
                a11.getInt() +
                a10.getInt() +
                a9.getInt() +
                a8.getInt() +
                a7.getInt() +
                a6.getInt() +
                a5.getInt() +
                a4.getInt() +
                a3.getInt() +
                a2.getInt() +
                a1.getInt();
        }
    }

    private static class LambdaIntTest3 implements CalcInterface {
        public int calc() {
           IntegerInterface a3 = () -> { return 3; }; 
           IntegerInterface a2 = () -> { return 2; }; 
           IntegerInterface a1 = () -> { return 1; }; 
            return a3.getInt() + a2.getInt() + a1.getInt();
        }
    }

    private static class AnonymousIntTest1024 implements CalcInterface {
        public int calc() {
           IntegerInterface a1024 = new IntegerInterface() {
                public int getInt() {
                    return 1024;
                }
            };
           IntegerInterface a1023 = new IntegerInterface() {
                public int getInt() {
                    return 1023;
                }
            };
           IntegerInterface a1022 = new IntegerInterface() {
                public int getInt() {
                    return 1022;
                }
            };
           IntegerInterface a1021 = new IntegerInterface() {
                public int getInt() {
                    return 1021;
                }
            };
           IntegerInterface a1020 = new IntegerInterface() {
                public int getInt() {
                    return 1020;
                }
            };
           IntegerInterface a1019 = new IntegerInterface() {
                public int getInt() {
                    return 1019;
                }
            };
           IntegerInterface a1018 = new IntegerInterface() {
                public int getInt() {
                    return 1018;
                }
            };
           IntegerInterface a1017 = new IntegerInterface() {
                public int getInt() {
                    return 1017;
                }
            };
           IntegerInterface a1016 = new IntegerInterface() {
                public int getInt() {
                    return 1016;
                }
            };
           IntegerInterface a1015 = new IntegerInterface() {
                public int getInt() {
                    return 1015;
                }
            };
           IntegerInterface a1014 = new IntegerInterface() {
                public int getInt() {
                    return 1014;
                }
            };
           IntegerInterface a1013 = new IntegerInterface() {
                public int getInt() {
                    return 1013;
                }
            };
           IntegerInterface a1012 = new IntegerInterface() {
                public int getInt() {
                    return 1012;
                }
            };
           IntegerInterface a1011 = new IntegerInterface() {
                public int getInt() {
                    return 1011;
                }
            };
           IntegerInterface a1010 = new IntegerInterface() {
                public int getInt() {
                    return 1010;
                }
            };
           IntegerInterface a1009 = new IntegerInterface() {
                public int getInt() {
                    return 1009;
                }
            };
           IntegerInterface a1008 = new IntegerInterface() {
                public int getInt() {
                    return 1008;
                }
            };
           IntegerInterface a1007 = new IntegerInterface() {
                public int getInt() {
                    return 1007;
                }
            };
           IntegerInterface a1006 = new IntegerInterface() {
                public int getInt() {
                    return 1006;
                }
            };
           IntegerInterface a1005 = new IntegerInterface() {
                public int getInt() {
                    return 1005;
                }
            };
           IntegerInterface a1004 = new IntegerInterface() {
                public int getInt() {
                    return 1004;
                }
            };
           IntegerInterface a1003 = new IntegerInterface() {
                public int getInt() {
                    return 1003;
                }
            };
           IntegerInterface a1002 = new IntegerInterface() {
                public int getInt() {
                    return 1002;
                }
            };
           IntegerInterface a1001 = new IntegerInterface() {
                public int getInt() {
                    return 1001;
                }
            };
           IntegerInterface a1000 = new IntegerInterface() {
                public int getInt() {
                    return 1000;
                }
            };
           IntegerInterface a999 = new IntegerInterface() {
                public int getInt() {
                    return 999;
                }
            };
           IntegerInterface a998 = new IntegerInterface() {
                public int getInt() {
                    return 998;
                }
            };
           IntegerInterface a997 = new IntegerInterface() {
                public int getInt() {
                    return 997;
                }
            };
           IntegerInterface a996 = new IntegerInterface() {
                public int getInt() {
                    return 996;
                }
            };
           IntegerInterface a995 = new IntegerInterface() {
                public int getInt() {
                    return 995;
                }
            };
           IntegerInterface a994 = new IntegerInterface() {
                public int getInt() {
                    return 994;
                }
            };
           IntegerInterface a993 = new IntegerInterface() {
                public int getInt() {
                    return 993;
                }
            };
           IntegerInterface a992 = new IntegerInterface() {
                public int getInt() {
                    return 992;
                }
            };
           IntegerInterface a991 = new IntegerInterface() {
                public int getInt() {
                    return 991;
                }
            };
           IntegerInterface a990 = new IntegerInterface() {
                public int getInt() {
                    return 990;
                }
            };
           IntegerInterface a989 = new IntegerInterface() {
                public int getInt() {
                    return 989;
                }
            };
           IntegerInterface a988 = new IntegerInterface() {
                public int getInt() {
                    return 988;
                }
            };
           IntegerInterface a987 = new IntegerInterface() {
                public int getInt() {
                    return 987;
                }
            };
           IntegerInterface a986 = new IntegerInterface() {
                public int getInt() {
                    return 986;
                }
            };
           IntegerInterface a985 = new IntegerInterface() {
                public int getInt() {
                    return 985;
                }
            };
           IntegerInterface a984 = new IntegerInterface() {
                public int getInt() {
                    return 984;
                }
            };
           IntegerInterface a983 = new IntegerInterface() {
                public int getInt() {
                    return 983;
                }
            };
           IntegerInterface a982 = new IntegerInterface() {
                public int getInt() {
                    return 982;
                }
            };
           IntegerInterface a981 = new IntegerInterface() {
                public int getInt() {
                    return 981;
                }
            };
           IntegerInterface a980 = new IntegerInterface() {
                public int getInt() {
                    return 980;
                }
            };
           IntegerInterface a979 = new IntegerInterface() {
                public int getInt() {
                    return 979;
                }
            };
           IntegerInterface a978 = new IntegerInterface() {
                public int getInt() {
                    return 978;
                }
            };
           IntegerInterface a977 = new IntegerInterface() {
                public int getInt() {
                    return 977;
                }
            };
           IntegerInterface a976 = new IntegerInterface() {
                public int getInt() {
                    return 976;
                }
            };
           IntegerInterface a975 = new IntegerInterface() {
                public int getInt() {
                    return 975;
                }
            };
           IntegerInterface a974 = new IntegerInterface() {
                public int getInt() {
                    return 974;
                }
            };
           IntegerInterface a973 = new IntegerInterface() {
                public int getInt() {
                    return 973;
                }
            };
           IntegerInterface a972 = new IntegerInterface() {
                public int getInt() {
                    return 972;
                }
            };
           IntegerInterface a971 = new IntegerInterface() {
                public int getInt() {
                    return 971;
                }
            };
           IntegerInterface a970 = new IntegerInterface() {
                public int getInt() {
                    return 970;
                }
            };
           IntegerInterface a969 = new IntegerInterface() {
                public int getInt() {
                    return 969;
                }
            };
           IntegerInterface a968 = new IntegerInterface() {
                public int getInt() {
                    return 968;
                }
            };
           IntegerInterface a967 = new IntegerInterface() {
                public int getInt() {
                    return 967;
                }
            };
           IntegerInterface a966 = new IntegerInterface() {
                public int getInt() {
                    return 966;
                }
            };
           IntegerInterface a965 = new IntegerInterface() {
                public int getInt() {
                    return 965;
                }
            };
           IntegerInterface a964 = new IntegerInterface() {
                public int getInt() {
                    return 964;
                }
            };
           IntegerInterface a963 = new IntegerInterface() {
                public int getInt() {
                    return 963;
                }
            };
           IntegerInterface a962 = new IntegerInterface() {
                public int getInt() {
                    return 962;
                }
            };
           IntegerInterface a961 = new IntegerInterface() {
                public int getInt() {
                    return 961;
                }
            };
           IntegerInterface a960 = new IntegerInterface() {
                public int getInt() {
                    return 960;
                }
            };
           IntegerInterface a959 = new IntegerInterface() {
                public int getInt() {
                    return 959;
                }
            };
           IntegerInterface a958 = new IntegerInterface() {
                public int getInt() {
                    return 958;
                }
            };
           IntegerInterface a957 = new IntegerInterface() {
                public int getInt() {
                    return 957;
                }
            };
           IntegerInterface a956 = new IntegerInterface() {
                public int getInt() {
                    return 956;
                }
            };
           IntegerInterface a955 = new IntegerInterface() {
                public int getInt() {
                    return 955;
                }
            };
           IntegerInterface a954 = new IntegerInterface() {
                public int getInt() {
                    return 954;
                }
            };
           IntegerInterface a953 = new IntegerInterface() {
                public int getInt() {
                    return 953;
                }
            };
           IntegerInterface a952 = new IntegerInterface() {
                public int getInt() {
                    return 952;
                }
            };
           IntegerInterface a951 = new IntegerInterface() {
                public int getInt() {
                    return 951;
                }
            };
           IntegerInterface a950 = new IntegerInterface() {
                public int getInt() {
                    return 950;
                }
            };
           IntegerInterface a949 = new IntegerInterface() {
                public int getInt() {
                    return 949;
                }
            };
           IntegerInterface a948 = new IntegerInterface() {
                public int getInt() {
                    return 948;
                }
            };
           IntegerInterface a947 = new IntegerInterface() {
                public int getInt() {
                    return 947;
                }
            };
           IntegerInterface a946 = new IntegerInterface() {
                public int getInt() {
                    return 946;
                }
            };
           IntegerInterface a945 = new IntegerInterface() {
                public int getInt() {
                    return 945;
                }
            };
           IntegerInterface a944 = new IntegerInterface() {
                public int getInt() {
                    return 944;
                }
            };
           IntegerInterface a943 = new IntegerInterface() {
                public int getInt() {
                    return 943;
                }
            };
           IntegerInterface a942 = new IntegerInterface() {
                public int getInt() {
                    return 942;
                }
            };
           IntegerInterface a941 = new IntegerInterface() {
                public int getInt() {
                    return 941;
                }
            };
           IntegerInterface a940 = new IntegerInterface() {
                public int getInt() {
                    return 940;
                }
            };
           IntegerInterface a939 = new IntegerInterface() {
                public int getInt() {
                    return 939;
                }
            };
           IntegerInterface a938 = new IntegerInterface() {
                public int getInt() {
                    return 938;
                }
            };
           IntegerInterface a937 = new IntegerInterface() {
                public int getInt() {
                    return 937;
                }
            };
           IntegerInterface a936 = new IntegerInterface() {
                public int getInt() {
                    return 936;
                }
            };
           IntegerInterface a935 = new IntegerInterface() {
                public int getInt() {
                    return 935;
                }
            };
           IntegerInterface a934 = new IntegerInterface() {
                public int getInt() {
                    return 934;
                }
            };
           IntegerInterface a933 = new IntegerInterface() {
                public int getInt() {
                    return 933;
                }
            };
           IntegerInterface a932 = new IntegerInterface() {
                public int getInt() {
                    return 932;
                }
            };
           IntegerInterface a931 = new IntegerInterface() {
                public int getInt() {
                    return 931;
                }
            };
           IntegerInterface a930 = new IntegerInterface() {
                public int getInt() {
                    return 930;
                }
            };
           IntegerInterface a929 = new IntegerInterface() {
                public int getInt() {
                    return 929;
                }
            };
           IntegerInterface a928 = new IntegerInterface() {
                public int getInt() {
                    return 928;
                }
            };
           IntegerInterface a927 = new IntegerInterface() {
                public int getInt() {
                    return 927;
                }
            };
           IntegerInterface a926 = new IntegerInterface() {
                public int getInt() {
                    return 926;
                }
            };
           IntegerInterface a925 = new IntegerInterface() {
                public int getInt() {
                    return 925;
                }
            };
           IntegerInterface a924 = new IntegerInterface() {
                public int getInt() {
                    return 924;
                }
            };
           IntegerInterface a923 = new IntegerInterface() {
                public int getInt() {
                    return 923;
                }
            };
           IntegerInterface a922 = new IntegerInterface() {
                public int getInt() {
                    return 922;
                }
            };
           IntegerInterface a921 = new IntegerInterface() {
                public int getInt() {
                    return 921;
                }
            };
           IntegerInterface a920 = new IntegerInterface() {
                public int getInt() {
                    return 920;
                }
            };
           IntegerInterface a919 = new IntegerInterface() {
                public int getInt() {
                    return 919;
                }
            };
           IntegerInterface a918 = new IntegerInterface() {
                public int getInt() {
                    return 918;
                }
            };
           IntegerInterface a917 = new IntegerInterface() {
                public int getInt() {
                    return 917;
                }
            };
           IntegerInterface a916 = new IntegerInterface() {
                public int getInt() {
                    return 916;
                }
            };
           IntegerInterface a915 = new IntegerInterface() {
                public int getInt() {
                    return 915;
                }
            };
           IntegerInterface a914 = new IntegerInterface() {
                public int getInt() {
                    return 914;
                }
            };
           IntegerInterface a913 = new IntegerInterface() {
                public int getInt() {
                    return 913;
                }
            };
           IntegerInterface a912 = new IntegerInterface() {
                public int getInt() {
                    return 912;
                }
            };
           IntegerInterface a911 = new IntegerInterface() {
                public int getInt() {
                    return 911;
                }
            };
           IntegerInterface a910 = new IntegerInterface() {
                public int getInt() {
                    return 910;
                }
            };
           IntegerInterface a909 = new IntegerInterface() {
                public int getInt() {
                    return 909;
                }
            };
           IntegerInterface a908 = new IntegerInterface() {
                public int getInt() {
                    return 908;
                }
            };
           IntegerInterface a907 = new IntegerInterface() {
                public int getInt() {
                    return 907;
                }
            };
           IntegerInterface a906 = new IntegerInterface() {
                public int getInt() {
                    return 906;
                }
            };
           IntegerInterface a905 = new IntegerInterface() {
                public int getInt() {
                    return 905;
                }
            };
           IntegerInterface a904 = new IntegerInterface() {
                public int getInt() {
                    return 904;
                }
            };
           IntegerInterface a903 = new IntegerInterface() {
                public int getInt() {
                    return 903;
                }
            };
           IntegerInterface a902 = new IntegerInterface() {
                public int getInt() {
                    return 902;
                }
            };
           IntegerInterface a901 = new IntegerInterface() {
                public int getInt() {
                    return 901;
                }
            };
           IntegerInterface a900 = new IntegerInterface() {
                public int getInt() {
                    return 900;
                }
            };
           IntegerInterface a899 = new IntegerInterface() {
                public int getInt() {
                    return 899;
                }
            };
           IntegerInterface a898 = new IntegerInterface() {
                public int getInt() {
                    return 898;
                }
            };
           IntegerInterface a897 = new IntegerInterface() {
                public int getInt() {
                    return 897;
                }
            };
           IntegerInterface a896 = new IntegerInterface() {
                public int getInt() {
                    return 896;
                }
            };
           IntegerInterface a895 = new IntegerInterface() {
                public int getInt() {
                    return 895;
                }
            };
           IntegerInterface a894 = new IntegerInterface() {
                public int getInt() {
                    return 894;
                }
            };
           IntegerInterface a893 = new IntegerInterface() {
                public int getInt() {
                    return 893;
                }
            };
           IntegerInterface a892 = new IntegerInterface() {
                public int getInt() {
                    return 892;
                }
            };
           IntegerInterface a891 = new IntegerInterface() {
                public int getInt() {
                    return 891;
                }
            };
           IntegerInterface a890 = new IntegerInterface() {
                public int getInt() {
                    return 890;
                }
            };
           IntegerInterface a889 = new IntegerInterface() {
                public int getInt() {
                    return 889;
                }
            };
           IntegerInterface a888 = new IntegerInterface() {
                public int getInt() {
                    return 888;
                }
            };
           IntegerInterface a887 = new IntegerInterface() {
                public int getInt() {
                    return 887;
                }
            };
           IntegerInterface a886 = new IntegerInterface() {
                public int getInt() {
                    return 886;
                }
            };
           IntegerInterface a885 = new IntegerInterface() {
                public int getInt() {
                    return 885;
                }
            };
           IntegerInterface a884 = new IntegerInterface() {
                public int getInt() {
                    return 884;
                }
            };
           IntegerInterface a883 = new IntegerInterface() {
                public int getInt() {
                    return 883;
                }
            };
           IntegerInterface a882 = new IntegerInterface() {
                public int getInt() {
                    return 882;
                }
            };
           IntegerInterface a881 = new IntegerInterface() {
                public int getInt() {
                    return 881;
                }
            };
           IntegerInterface a880 = new IntegerInterface() {
                public int getInt() {
                    return 880;
                }
            };
           IntegerInterface a879 = new IntegerInterface() {
                public int getInt() {
                    return 879;
                }
            };
           IntegerInterface a878 = new IntegerInterface() {
                public int getInt() {
                    return 878;
                }
            };
           IntegerInterface a877 = new IntegerInterface() {
                public int getInt() {
                    return 877;
                }
            };
           IntegerInterface a876 = new IntegerInterface() {
                public int getInt() {
                    return 876;
                }
            };
           IntegerInterface a875 = new IntegerInterface() {
                public int getInt() {
                    return 875;
                }
            };
           IntegerInterface a874 = new IntegerInterface() {
                public int getInt() {
                    return 874;
                }
            };
           IntegerInterface a873 = new IntegerInterface() {
                public int getInt() {
                    return 873;
                }
            };
           IntegerInterface a872 = new IntegerInterface() {
                public int getInt() {
                    return 872;
                }
            };
           IntegerInterface a871 = new IntegerInterface() {
                public int getInt() {
                    return 871;
                }
            };
           IntegerInterface a870 = new IntegerInterface() {
                public int getInt() {
                    return 870;
                }
            };
           IntegerInterface a869 = new IntegerInterface() {
                public int getInt() {
                    return 869;
                }
            };
           IntegerInterface a868 = new IntegerInterface() {
                public int getInt() {
                    return 868;
                }
            };
           IntegerInterface a867 = new IntegerInterface() {
                public int getInt() {
                    return 867;
                }
            };
           IntegerInterface a866 = new IntegerInterface() {
                public int getInt() {
                    return 866;
                }
            };
           IntegerInterface a865 = new IntegerInterface() {
                public int getInt() {
                    return 865;
                }
            };
           IntegerInterface a864 = new IntegerInterface() {
                public int getInt() {
                    return 864;
                }
            };
           IntegerInterface a863 = new IntegerInterface() {
                public int getInt() {
                    return 863;
                }
            };
           IntegerInterface a862 = new IntegerInterface() {
                public int getInt() {
                    return 862;
                }
            };
           IntegerInterface a861 = new IntegerInterface() {
                public int getInt() {
                    return 861;
                }
            };
           IntegerInterface a860 = new IntegerInterface() {
                public int getInt() {
                    return 860;
                }
            };
           IntegerInterface a859 = new IntegerInterface() {
                public int getInt() {
                    return 859;
                }
            };
           IntegerInterface a858 = new IntegerInterface() {
                public int getInt() {
                    return 858;
                }
            };
           IntegerInterface a857 = new IntegerInterface() {
                public int getInt() {
                    return 857;
                }
            };
           IntegerInterface a856 = new IntegerInterface() {
                public int getInt() {
                    return 856;
                }
            };
           IntegerInterface a855 = new IntegerInterface() {
                public int getInt() {
                    return 855;
                }
            };
           IntegerInterface a854 = new IntegerInterface() {
                public int getInt() {
                    return 854;
                }
            };
           IntegerInterface a853 = new IntegerInterface() {
                public int getInt() {
                    return 853;
                }
            };
           IntegerInterface a852 = new IntegerInterface() {
                public int getInt() {
                    return 852;
                }
            };
           IntegerInterface a851 = new IntegerInterface() {
                public int getInt() {
                    return 851;
                }
            };
           IntegerInterface a850 = new IntegerInterface() {
                public int getInt() {
                    return 850;
                }
            };
           IntegerInterface a849 = new IntegerInterface() {
                public int getInt() {
                    return 849;
                }
            };
           IntegerInterface a848 = new IntegerInterface() {
                public int getInt() {
                    return 848;
                }
            };
           IntegerInterface a847 = new IntegerInterface() {
                public int getInt() {
                    return 847;
                }
            };
           IntegerInterface a846 = new IntegerInterface() {
                public int getInt() {
                    return 846;
                }
            };
           IntegerInterface a845 = new IntegerInterface() {
                public int getInt() {
                    return 845;
                }
            };
           IntegerInterface a844 = new IntegerInterface() {
                public int getInt() {
                    return 844;
                }
            };
           IntegerInterface a843 = new IntegerInterface() {
                public int getInt() {
                    return 843;
                }
            };
           IntegerInterface a842 = new IntegerInterface() {
                public int getInt() {
                    return 842;
                }
            };
           IntegerInterface a841 = new IntegerInterface() {
                public int getInt() {
                    return 841;
                }
            };
           IntegerInterface a840 = new IntegerInterface() {
                public int getInt() {
                    return 840;
                }
            };
           IntegerInterface a839 = new IntegerInterface() {
                public int getInt() {
                    return 839;
                }
            };
           IntegerInterface a838 = new IntegerInterface() {
                public int getInt() {
                    return 838;
                }
            };
           IntegerInterface a837 = new IntegerInterface() {
                public int getInt() {
                    return 837;
                }
            };
           IntegerInterface a836 = new IntegerInterface() {
                public int getInt() {
                    return 836;
                }
            };
           IntegerInterface a835 = new IntegerInterface() {
                public int getInt() {
                    return 835;
                }
            };
           IntegerInterface a834 = new IntegerInterface() {
                public int getInt() {
                    return 834;
                }
            };
           IntegerInterface a833 = new IntegerInterface() {
                public int getInt() {
                    return 833;
                }
            };
           IntegerInterface a832 = new IntegerInterface() {
                public int getInt() {
                    return 832;
                }
            };
           IntegerInterface a831 = new IntegerInterface() {
                public int getInt() {
                    return 831;
                }
            };
           IntegerInterface a830 = new IntegerInterface() {
                public int getInt() {
                    return 830;
                }
            };
           IntegerInterface a829 = new IntegerInterface() {
                public int getInt() {
                    return 829;
                }
            };
           IntegerInterface a828 = new IntegerInterface() {
                public int getInt() {
                    return 828;
                }
            };
           IntegerInterface a827 = new IntegerInterface() {
                public int getInt() {
                    return 827;
                }
            };
           IntegerInterface a826 = new IntegerInterface() {
                public int getInt() {
                    return 826;
                }
            };
           IntegerInterface a825 = new IntegerInterface() {
                public int getInt() {
                    return 825;
                }
            };
           IntegerInterface a824 = new IntegerInterface() {
                public int getInt() {
                    return 824;
                }
            };
           IntegerInterface a823 = new IntegerInterface() {
                public int getInt() {
                    return 823;
                }
            };
           IntegerInterface a822 = new IntegerInterface() {
                public int getInt() {
                    return 822;
                }
            };
           IntegerInterface a821 = new IntegerInterface() {
                public int getInt() {
                    return 821;
                }
            };
           IntegerInterface a820 = new IntegerInterface() {
                public int getInt() {
                    return 820;
                }
            };
           IntegerInterface a819 = new IntegerInterface() {
                public int getInt() {
                    return 819;
                }
            };
           IntegerInterface a818 = new IntegerInterface() {
                public int getInt() {
                    return 818;
                }
            };
           IntegerInterface a817 = new IntegerInterface() {
                public int getInt() {
                    return 817;
                }
            };
           IntegerInterface a816 = new IntegerInterface() {
                public int getInt() {
                    return 816;
                }
            };
           IntegerInterface a815 = new IntegerInterface() {
                public int getInt() {
                    return 815;
                }
            };
           IntegerInterface a814 = new IntegerInterface() {
                public int getInt() {
                    return 814;
                }
            };
           IntegerInterface a813 = new IntegerInterface() {
                public int getInt() {
                    return 813;
                }
            };
           IntegerInterface a812 = new IntegerInterface() {
                public int getInt() {
                    return 812;
                }
            };
           IntegerInterface a811 = new IntegerInterface() {
                public int getInt() {
                    return 811;
                }
            };
           IntegerInterface a810 = new IntegerInterface() {
                public int getInt() {
                    return 810;
                }
            };
           IntegerInterface a809 = new IntegerInterface() {
                public int getInt() {
                    return 809;
                }
            };
           IntegerInterface a808 = new IntegerInterface() {
                public int getInt() {
                    return 808;
                }
            };
           IntegerInterface a807 = new IntegerInterface() {
                public int getInt() {
                    return 807;
                }
            };
           IntegerInterface a806 = new IntegerInterface() {
                public int getInt() {
                    return 806;
                }
            };
           IntegerInterface a805 = new IntegerInterface() {
                public int getInt() {
                    return 805;
                }
            };
           IntegerInterface a804 = new IntegerInterface() {
                public int getInt() {
                    return 804;
                }
            };
           IntegerInterface a803 = new IntegerInterface() {
                public int getInt() {
                    return 803;
                }
            };
           IntegerInterface a802 = new IntegerInterface() {
                public int getInt() {
                    return 802;
                }
            };
           IntegerInterface a801 = new IntegerInterface() {
                public int getInt() {
                    return 801;
                }
            };
           IntegerInterface a800 = new IntegerInterface() {
                public int getInt() {
                    return 800;
                }
            };
           IntegerInterface a799 = new IntegerInterface() {
                public int getInt() {
                    return 799;
                }
            };
           IntegerInterface a798 = new IntegerInterface() {
                public int getInt() {
                    return 798;
                }
            };
           IntegerInterface a797 = new IntegerInterface() {
                public int getInt() {
                    return 797;
                }
            };
           IntegerInterface a796 = new IntegerInterface() {
                public int getInt() {
                    return 796;
                }
            };
           IntegerInterface a795 = new IntegerInterface() {
                public int getInt() {
                    return 795;
                }
            };
           IntegerInterface a794 = new IntegerInterface() {
                public int getInt() {
                    return 794;
                }
            };
           IntegerInterface a793 = new IntegerInterface() {
                public int getInt() {
                    return 793;
                }
            };
           IntegerInterface a792 = new IntegerInterface() {
                public int getInt() {
                    return 792;
                }
            };
           IntegerInterface a791 = new IntegerInterface() {
                public int getInt() {
                    return 791;
                }
            };
           IntegerInterface a790 = new IntegerInterface() {
                public int getInt() {
                    return 790;
                }
            };
           IntegerInterface a789 = new IntegerInterface() {
                public int getInt() {
                    return 789;
                }
            };
           IntegerInterface a788 = new IntegerInterface() {
                public int getInt() {
                    return 788;
                }
            };
           IntegerInterface a787 = new IntegerInterface() {
                public int getInt() {
                    return 787;
                }
            };
           IntegerInterface a786 = new IntegerInterface() {
                public int getInt() {
                    return 786;
                }
            };
           IntegerInterface a785 = new IntegerInterface() {
                public int getInt() {
                    return 785;
                }
            };
           IntegerInterface a784 = new IntegerInterface() {
                public int getInt() {
                    return 784;
                }
            };
           IntegerInterface a783 = new IntegerInterface() {
                public int getInt() {
                    return 783;
                }
            };
           IntegerInterface a782 = new IntegerInterface() {
                public int getInt() {
                    return 782;
                }
            };
           IntegerInterface a781 = new IntegerInterface() {
                public int getInt() {
                    return 781;
                }
            };
           IntegerInterface a780 = new IntegerInterface() {
                public int getInt() {
                    return 780;
                }
            };
           IntegerInterface a779 = new IntegerInterface() {
                public int getInt() {
                    return 779;
                }
            };
           IntegerInterface a778 = new IntegerInterface() {
                public int getInt() {
                    return 778;
                }
            };
           IntegerInterface a777 = new IntegerInterface() {
                public int getInt() {
                    return 777;
                }
            };
           IntegerInterface a776 = new IntegerInterface() {
                public int getInt() {
                    return 776;
                }
            };
           IntegerInterface a775 = new IntegerInterface() {
                public int getInt() {
                    return 775;
                }
            };
           IntegerInterface a774 = new IntegerInterface() {
                public int getInt() {
                    return 774;
                }
            };
           IntegerInterface a773 = new IntegerInterface() {
                public int getInt() {
                    return 773;
                }
            };
           IntegerInterface a772 = new IntegerInterface() {
                public int getInt() {
                    return 772;
                }
            };
           IntegerInterface a771 = new IntegerInterface() {
                public int getInt() {
                    return 771;
                }
            };
           IntegerInterface a770 = new IntegerInterface() {
                public int getInt() {
                    return 770;
                }
            };
           IntegerInterface a769 = new IntegerInterface() {
                public int getInt() {
                    return 769;
                }
            };
           IntegerInterface a768 = new IntegerInterface() {
                public int getInt() {
                    return 768;
                }
            };
           IntegerInterface a767 = new IntegerInterface() {
                public int getInt() {
                    return 767;
                }
            };
           IntegerInterface a766 = new IntegerInterface() {
                public int getInt() {
                    return 766;
                }
            };
           IntegerInterface a765 = new IntegerInterface() {
                public int getInt() {
                    return 765;
                }
            };
           IntegerInterface a764 = new IntegerInterface() {
                public int getInt() {
                    return 764;
                }
            };
           IntegerInterface a763 = new IntegerInterface() {
                public int getInt() {
                    return 763;
                }
            };
           IntegerInterface a762 = new IntegerInterface() {
                public int getInt() {
                    return 762;
                }
            };
           IntegerInterface a761 = new IntegerInterface() {
                public int getInt() {
                    return 761;
                }
            };
           IntegerInterface a760 = new IntegerInterface() {
                public int getInt() {
                    return 760;
                }
            };
           IntegerInterface a759 = new IntegerInterface() {
                public int getInt() {
                    return 759;
                }
            };
           IntegerInterface a758 = new IntegerInterface() {
                public int getInt() {
                    return 758;
                }
            };
           IntegerInterface a757 = new IntegerInterface() {
                public int getInt() {
                    return 757;
                }
            };
           IntegerInterface a756 = new IntegerInterface() {
                public int getInt() {
                    return 756;
                }
            };
           IntegerInterface a755 = new IntegerInterface() {
                public int getInt() {
                    return 755;
                }
            };
           IntegerInterface a754 = new IntegerInterface() {
                public int getInt() {
                    return 754;
                }
            };
           IntegerInterface a753 = new IntegerInterface() {
                public int getInt() {
                    return 753;
                }
            };
           IntegerInterface a752 = new IntegerInterface() {
                public int getInt() {
                    return 752;
                }
            };
           IntegerInterface a751 = new IntegerInterface() {
                public int getInt() {
                    return 751;
                }
            };
           IntegerInterface a750 = new IntegerInterface() {
                public int getInt() {
                    return 750;
                }
            };
           IntegerInterface a749 = new IntegerInterface() {
                public int getInt() {
                    return 749;
                }
            };
           IntegerInterface a748 = new IntegerInterface() {
                public int getInt() {
                    return 748;
                }
            };
           IntegerInterface a747 = new IntegerInterface() {
                public int getInt() {
                    return 747;
                }
            };
           IntegerInterface a746 = new IntegerInterface() {
                public int getInt() {
                    return 746;
                }
            };
           IntegerInterface a745 = new IntegerInterface() {
                public int getInt() {
                    return 745;
                }
            };
           IntegerInterface a744 = new IntegerInterface() {
                public int getInt() {
                    return 744;
                }
            };
           IntegerInterface a743 = new IntegerInterface() {
                public int getInt() {
                    return 743;
                }
            };
           IntegerInterface a742 = new IntegerInterface() {
                public int getInt() {
                    return 742;
                }
            };
           IntegerInterface a741 = new IntegerInterface() {
                public int getInt() {
                    return 741;
                }
            };
           IntegerInterface a740 = new IntegerInterface() {
                public int getInt() {
                    return 740;
                }
            };
           IntegerInterface a739 = new IntegerInterface() {
                public int getInt() {
                    return 739;
                }
            };
           IntegerInterface a738 = new IntegerInterface() {
                public int getInt() {
                    return 738;
                }
            };
           IntegerInterface a737 = new IntegerInterface() {
                public int getInt() {
                    return 737;
                }
            };
           IntegerInterface a736 = new IntegerInterface() {
                public int getInt() {
                    return 736;
                }
            };
           IntegerInterface a735 = new IntegerInterface() {
                public int getInt() {
                    return 735;
                }
            };
           IntegerInterface a734 = new IntegerInterface() {
                public int getInt() {
                    return 734;
                }
            };
           IntegerInterface a733 = new IntegerInterface() {
                public int getInt() {
                    return 733;
                }
            };
           IntegerInterface a732 = new IntegerInterface() {
                public int getInt() {
                    return 732;
                }
            };
           IntegerInterface a731 = new IntegerInterface() {
                public int getInt() {
                    return 731;
                }
            };
           IntegerInterface a730 = new IntegerInterface() {
                public int getInt() {
                    return 730;
                }
            };
           IntegerInterface a729 = new IntegerInterface() {
                public int getInt() {
                    return 729;
                }
            };
           IntegerInterface a728 = new IntegerInterface() {
                public int getInt() {
                    return 728;
                }
            };
           IntegerInterface a727 = new IntegerInterface() {
                public int getInt() {
                    return 727;
                }
            };
           IntegerInterface a726 = new IntegerInterface() {
                public int getInt() {
                    return 726;
                }
            };
           IntegerInterface a725 = new IntegerInterface() {
                public int getInt() {
                    return 725;
                }
            };
           IntegerInterface a724 = new IntegerInterface() {
                public int getInt() {
                    return 724;
                }
            };
           IntegerInterface a723 = new IntegerInterface() {
                public int getInt() {
                    return 723;
                }
            };
           IntegerInterface a722 = new IntegerInterface() {
                public int getInt() {
                    return 722;
                }
            };
           IntegerInterface a721 = new IntegerInterface() {
                public int getInt() {
                    return 721;
                }
            };
           IntegerInterface a720 = new IntegerInterface() {
                public int getInt() {
                    return 720;
                }
            };
           IntegerInterface a719 = new IntegerInterface() {
                public int getInt() {
                    return 719;
                }
            };
           IntegerInterface a718 = new IntegerInterface() {
                public int getInt() {
                    return 718;
                }
            };
           IntegerInterface a717 = new IntegerInterface() {
                public int getInt() {
                    return 717;
                }
            };
           IntegerInterface a716 = new IntegerInterface() {
                public int getInt() {
                    return 716;
                }
            };
           IntegerInterface a715 = new IntegerInterface() {
                public int getInt() {
                    return 715;
                }
            };
           IntegerInterface a714 = new IntegerInterface() {
                public int getInt() {
                    return 714;
                }
            };
           IntegerInterface a713 = new IntegerInterface() {
                public int getInt() {
                    return 713;
                }
            };
           IntegerInterface a712 = new IntegerInterface() {
                public int getInt() {
                    return 712;
                }
            };
           IntegerInterface a711 = new IntegerInterface() {
                public int getInt() {
                    return 711;
                }
            };
           IntegerInterface a710 = new IntegerInterface() {
                public int getInt() {
                    return 710;
                }
            };
           IntegerInterface a709 = new IntegerInterface() {
                public int getInt() {
                    return 709;
                }
            };
           IntegerInterface a708 = new IntegerInterface() {
                public int getInt() {
                    return 708;
                }
            };
           IntegerInterface a707 = new IntegerInterface() {
                public int getInt() {
                    return 707;
                }
            };
           IntegerInterface a706 = new IntegerInterface() {
                public int getInt() {
                    return 706;
                }
            };
           IntegerInterface a705 = new IntegerInterface() {
                public int getInt() {
                    return 705;
                }
            };
           IntegerInterface a704 = new IntegerInterface() {
                public int getInt() {
                    return 704;
                }
            };
           IntegerInterface a703 = new IntegerInterface() {
                public int getInt() {
                    return 703;
                }
            };
           IntegerInterface a702 = new IntegerInterface() {
                public int getInt() {
                    return 702;
                }
            };
           IntegerInterface a701 = new IntegerInterface() {
                public int getInt() {
                    return 701;
                }
            };
           IntegerInterface a700 = new IntegerInterface() {
                public int getInt() {
                    return 700;
                }
            };
           IntegerInterface a699 = new IntegerInterface() {
                public int getInt() {
                    return 699;
                }
            };
           IntegerInterface a698 = new IntegerInterface() {
                public int getInt() {
                    return 698;
                }
            };
           IntegerInterface a697 = new IntegerInterface() {
                public int getInt() {
                    return 697;
                }
            };
           IntegerInterface a696 = new IntegerInterface() {
                public int getInt() {
                    return 696;
                }
            };
           IntegerInterface a695 = new IntegerInterface() {
                public int getInt() {
                    return 695;
                }
            };
           IntegerInterface a694 = new IntegerInterface() {
                public int getInt() {
                    return 694;
                }
            };
           IntegerInterface a693 = new IntegerInterface() {
                public int getInt() {
                    return 693;
                }
            };
           IntegerInterface a692 = new IntegerInterface() {
                public int getInt() {
                    return 692;
                }
            };
           IntegerInterface a691 = new IntegerInterface() {
                public int getInt() {
                    return 691;
                }
            };
           IntegerInterface a690 = new IntegerInterface() {
                public int getInt() {
                    return 690;
                }
            };
           IntegerInterface a689 = new IntegerInterface() {
                public int getInt() {
                    return 689;
                }
            };
           IntegerInterface a688 = new IntegerInterface() {
                public int getInt() {
                    return 688;
                }
            };
           IntegerInterface a687 = new IntegerInterface() {
                public int getInt() {
                    return 687;
                }
            };
           IntegerInterface a686 = new IntegerInterface() {
                public int getInt() {
                    return 686;
                }
            };
           IntegerInterface a685 = new IntegerInterface() {
                public int getInt() {
                    return 685;
                }
            };
           IntegerInterface a684 = new IntegerInterface() {
                public int getInt() {
                    return 684;
                }
            };
           IntegerInterface a683 = new IntegerInterface() {
                public int getInt() {
                    return 683;
                }
            };
           IntegerInterface a682 = new IntegerInterface() {
                public int getInt() {
                    return 682;
                }
            };
           IntegerInterface a681 = new IntegerInterface() {
                public int getInt() {
                    return 681;
                }
            };
           IntegerInterface a680 = new IntegerInterface() {
                public int getInt() {
                    return 680;
                }
            };
           IntegerInterface a679 = new IntegerInterface() {
                public int getInt() {
                    return 679;
                }
            };
           IntegerInterface a678 = new IntegerInterface() {
                public int getInt() {
                    return 678;
                }
            };
           IntegerInterface a677 = new IntegerInterface() {
                public int getInt() {
                    return 677;
                }
            };
           IntegerInterface a676 = new IntegerInterface() {
                public int getInt() {
                    return 676;
                }
            };
           IntegerInterface a675 = new IntegerInterface() {
                public int getInt() {
                    return 675;
                }
            };
           IntegerInterface a674 = new IntegerInterface() {
                public int getInt() {
                    return 674;
                }
            };
           IntegerInterface a673 = new IntegerInterface() {
                public int getInt() {
                    return 673;
                }
            };
           IntegerInterface a672 = new IntegerInterface() {
                public int getInt() {
                    return 672;
                }
            };
           IntegerInterface a671 = new IntegerInterface() {
                public int getInt() {
                    return 671;
                }
            };
           IntegerInterface a670 = new IntegerInterface() {
                public int getInt() {
                    return 670;
                }
            };
           IntegerInterface a669 = new IntegerInterface() {
                public int getInt() {
                    return 669;
                }
            };
           IntegerInterface a668 = new IntegerInterface() {
                public int getInt() {
                    return 668;
                }
            };
           IntegerInterface a667 = new IntegerInterface() {
                public int getInt() {
                    return 667;
                }
            };
           IntegerInterface a666 = new IntegerInterface() {
                public int getInt() {
                    return 666;
                }
            };
           IntegerInterface a665 = new IntegerInterface() {
                public int getInt() {
                    return 665;
                }
            };
           IntegerInterface a664 = new IntegerInterface() {
                public int getInt() {
                    return 664;
                }
            };
           IntegerInterface a663 = new IntegerInterface() {
                public int getInt() {
                    return 663;
                }
            };
           IntegerInterface a662 = new IntegerInterface() {
                public int getInt() {
                    return 662;
                }
            };
           IntegerInterface a661 = new IntegerInterface() {
                public int getInt() {
                    return 661;
                }
            };
           IntegerInterface a660 = new IntegerInterface() {
                public int getInt() {
                    return 660;
                }
            };
           IntegerInterface a659 = new IntegerInterface() {
                public int getInt() {
                    return 659;
                }
            };
           IntegerInterface a658 = new IntegerInterface() {
                public int getInt() {
                    return 658;
                }
            };
           IntegerInterface a657 = new IntegerInterface() {
                public int getInt() {
                    return 657;
                }
            };
           IntegerInterface a656 = new IntegerInterface() {
                public int getInt() {
                    return 656;
                }
            };
           IntegerInterface a655 = new IntegerInterface() {
                public int getInt() {
                    return 655;
                }
            };
           IntegerInterface a654 = new IntegerInterface() {
                public int getInt() {
                    return 654;
                }
            };
           IntegerInterface a653 = new IntegerInterface() {
                public int getInt() {
                    return 653;
                }
            };
           IntegerInterface a652 = new IntegerInterface() {
                public int getInt() {
                    return 652;
                }
            };
           IntegerInterface a651 = new IntegerInterface() {
                public int getInt() {
                    return 651;
                }
            };
           IntegerInterface a650 = new IntegerInterface() {
                public int getInt() {
                    return 650;
                }
            };
           IntegerInterface a649 = new IntegerInterface() {
                public int getInt() {
                    return 649;
                }
            };
           IntegerInterface a648 = new IntegerInterface() {
                public int getInt() {
                    return 648;
                }
            };
           IntegerInterface a647 = new IntegerInterface() {
                public int getInt() {
                    return 647;
                }
            };
           IntegerInterface a646 = new IntegerInterface() {
                public int getInt() {
                    return 646;
                }
            };
           IntegerInterface a645 = new IntegerInterface() {
                public int getInt() {
                    return 645;
                }
            };
           IntegerInterface a644 = new IntegerInterface() {
                public int getInt() {
                    return 644;
                }
            };
           IntegerInterface a643 = new IntegerInterface() {
                public int getInt() {
                    return 643;
                }
            };
           IntegerInterface a642 = new IntegerInterface() {
                public int getInt() {
                    return 642;
                }
            };
           IntegerInterface a641 = new IntegerInterface() {
                public int getInt() {
                    return 641;
                }
            };
           IntegerInterface a640 = new IntegerInterface() {
                public int getInt() {
                    return 640;
                }
            };
           IntegerInterface a639 = new IntegerInterface() {
                public int getInt() {
                    return 639;
                }
            };
           IntegerInterface a638 = new IntegerInterface() {
                public int getInt() {
                    return 638;
                }
            };
           IntegerInterface a637 = new IntegerInterface() {
                public int getInt() {
                    return 637;
                }
            };
           IntegerInterface a636 = new IntegerInterface() {
                public int getInt() {
                    return 636;
                }
            };
           IntegerInterface a635 = new IntegerInterface() {
                public int getInt() {
                    return 635;
                }
            };
           IntegerInterface a634 = new IntegerInterface() {
                public int getInt() {
                    return 634;
                }
            };
           IntegerInterface a633 = new IntegerInterface() {
                public int getInt() {
                    return 633;
                }
            };
           IntegerInterface a632 = new IntegerInterface() {
                public int getInt() {
                    return 632;
                }
            };
           IntegerInterface a631 = new IntegerInterface() {
                public int getInt() {
                    return 631;
                }
            };
           IntegerInterface a630 = new IntegerInterface() {
                public int getInt() {
                    return 630;
                }
            };
           IntegerInterface a629 = new IntegerInterface() {
                public int getInt() {
                    return 629;
                }
            };
           IntegerInterface a628 = new IntegerInterface() {
                public int getInt() {
                    return 628;
                }
            };
           IntegerInterface a627 = new IntegerInterface() {
                public int getInt() {
                    return 627;
                }
            };
           IntegerInterface a626 = new IntegerInterface() {
                public int getInt() {
                    return 626;
                }
            };
           IntegerInterface a625 = new IntegerInterface() {
                public int getInt() {
                    return 625;
                }
            };
           IntegerInterface a624 = new IntegerInterface() {
                public int getInt() {
                    return 624;
                }
            };
           IntegerInterface a623 = new IntegerInterface() {
                public int getInt() {
                    return 623;
                }
            };
           IntegerInterface a622 = new IntegerInterface() {
                public int getInt() {
                    return 622;
                }
            };
           IntegerInterface a621 = new IntegerInterface() {
                public int getInt() {
                    return 621;
                }
            };
           IntegerInterface a620 = new IntegerInterface() {
                public int getInt() {
                    return 620;
                }
            };
           IntegerInterface a619 = new IntegerInterface() {
                public int getInt() {
                    return 619;
                }
            };
           IntegerInterface a618 = new IntegerInterface() {
                public int getInt() {
                    return 618;
                }
            };
           IntegerInterface a617 = new IntegerInterface() {
                public int getInt() {
                    return 617;
                }
            };
           IntegerInterface a616 = new IntegerInterface() {
                public int getInt() {
                    return 616;
                }
            };
           IntegerInterface a615 = new IntegerInterface() {
                public int getInt() {
                    return 615;
                }
            };
           IntegerInterface a614 = new IntegerInterface() {
                public int getInt() {
                    return 614;
                }
            };
           IntegerInterface a613 = new IntegerInterface() {
                public int getInt() {
                    return 613;
                }
            };
           IntegerInterface a612 = new IntegerInterface() {
                public int getInt() {
                    return 612;
                }
            };
           IntegerInterface a611 = new IntegerInterface() {
                public int getInt() {
                    return 611;
                }
            };
           IntegerInterface a610 = new IntegerInterface() {
                public int getInt() {
                    return 610;
                }
            };
           IntegerInterface a609 = new IntegerInterface() {
                public int getInt() {
                    return 609;
                }
            };
           IntegerInterface a608 = new IntegerInterface() {
                public int getInt() {
                    return 608;
                }
            };
           IntegerInterface a607 = new IntegerInterface() {
                public int getInt() {
                    return 607;
                }
            };
           IntegerInterface a606 = new IntegerInterface() {
                public int getInt() {
                    return 606;
                }
            };
           IntegerInterface a605 = new IntegerInterface() {
                public int getInt() {
                    return 605;
                }
            };
           IntegerInterface a604 = new IntegerInterface() {
                public int getInt() {
                    return 604;
                }
            };
           IntegerInterface a603 = new IntegerInterface() {
                public int getInt() {
                    return 603;
                }
            };
           IntegerInterface a602 = new IntegerInterface() {
                public int getInt() {
                    return 602;
                }
            };
           IntegerInterface a601 = new IntegerInterface() {
                public int getInt() {
                    return 601;
                }
            };
           IntegerInterface a600 = new IntegerInterface() {
                public int getInt() {
                    return 600;
                }
            };
           IntegerInterface a599 = new IntegerInterface() {
                public int getInt() {
                    return 599;
                }
            };
           IntegerInterface a598 = new IntegerInterface() {
                public int getInt() {
                    return 598;
                }
            };
           IntegerInterface a597 = new IntegerInterface() {
                public int getInt() {
                    return 597;
                }
            };
           IntegerInterface a596 = new IntegerInterface() {
                public int getInt() {
                    return 596;
                }
            };
           IntegerInterface a595 = new IntegerInterface() {
                public int getInt() {
                    return 595;
                }
            };
           IntegerInterface a594 = new IntegerInterface() {
                public int getInt() {
                    return 594;
                }
            };
           IntegerInterface a593 = new IntegerInterface() {
                public int getInt() {
                    return 593;
                }
            };
           IntegerInterface a592 = new IntegerInterface() {
                public int getInt() {
                    return 592;
                }
            };
           IntegerInterface a591 = new IntegerInterface() {
                public int getInt() {
                    return 591;
                }
            };
           IntegerInterface a590 = new IntegerInterface() {
                public int getInt() {
                    return 590;
                }
            };
           IntegerInterface a589 = new IntegerInterface() {
                public int getInt() {
                    return 589;
                }
            };
           IntegerInterface a588 = new IntegerInterface() {
                public int getInt() {
                    return 588;
                }
            };
           IntegerInterface a587 = new IntegerInterface() {
                public int getInt() {
                    return 587;
                }
            };
           IntegerInterface a586 = new IntegerInterface() {
                public int getInt() {
                    return 586;
                }
            };
           IntegerInterface a585 = new IntegerInterface() {
                public int getInt() {
                    return 585;
                }
            };
           IntegerInterface a584 = new IntegerInterface() {
                public int getInt() {
                    return 584;
                }
            };
           IntegerInterface a583 = new IntegerInterface() {
                public int getInt() {
                    return 583;
                }
            };
           IntegerInterface a582 = new IntegerInterface() {
                public int getInt() {
                    return 582;
                }
            };
           IntegerInterface a581 = new IntegerInterface() {
                public int getInt() {
                    return 581;
                }
            };
           IntegerInterface a580 = new IntegerInterface() {
                public int getInt() {
                    return 580;
                }
            };
           IntegerInterface a579 = new IntegerInterface() {
                public int getInt() {
                    return 579;
                }
            };
           IntegerInterface a578 = new IntegerInterface() {
                public int getInt() {
                    return 578;
                }
            };
           IntegerInterface a577 = new IntegerInterface() {
                public int getInt() {
                    return 577;
                }
            };
           IntegerInterface a576 = new IntegerInterface() {
                public int getInt() {
                    return 576;
                }
            };
           IntegerInterface a575 = new IntegerInterface() {
                public int getInt() {
                    return 575;
                }
            };
           IntegerInterface a574 = new IntegerInterface() {
                public int getInt() {
                    return 574;
                }
            };
           IntegerInterface a573 = new IntegerInterface() {
                public int getInt() {
                    return 573;
                }
            };
           IntegerInterface a572 = new IntegerInterface() {
                public int getInt() {
                    return 572;
                }
            };
           IntegerInterface a571 = new IntegerInterface() {
                public int getInt() {
                    return 571;
                }
            };
           IntegerInterface a570 = new IntegerInterface() {
                public int getInt() {
                    return 570;
                }
            };
           IntegerInterface a569 = new IntegerInterface() {
                public int getInt() {
                    return 569;
                }
            };
           IntegerInterface a568 = new IntegerInterface() {
                public int getInt() {
                    return 568;
                }
            };
           IntegerInterface a567 = new IntegerInterface() {
                public int getInt() {
                    return 567;
                }
            };
           IntegerInterface a566 = new IntegerInterface() {
                public int getInt() {
                    return 566;
                }
            };
           IntegerInterface a565 = new IntegerInterface() {
                public int getInt() {
                    return 565;
                }
            };
           IntegerInterface a564 = new IntegerInterface() {
                public int getInt() {
                    return 564;
                }
            };
           IntegerInterface a563 = new IntegerInterface() {
                public int getInt() {
                    return 563;
                }
            };
           IntegerInterface a562 = new IntegerInterface() {
                public int getInt() {
                    return 562;
                }
            };
           IntegerInterface a561 = new IntegerInterface() {
                public int getInt() {
                    return 561;
                }
            };
           IntegerInterface a560 = new IntegerInterface() {
                public int getInt() {
                    return 560;
                }
            };
           IntegerInterface a559 = new IntegerInterface() {
                public int getInt() {
                    return 559;
                }
            };
           IntegerInterface a558 = new IntegerInterface() {
                public int getInt() {
                    return 558;
                }
            };
           IntegerInterface a557 = new IntegerInterface() {
                public int getInt() {
                    return 557;
                }
            };
           IntegerInterface a556 = new IntegerInterface() {
                public int getInt() {
                    return 556;
                }
            };
           IntegerInterface a555 = new IntegerInterface() {
                public int getInt() {
                    return 555;
                }
            };
           IntegerInterface a554 = new IntegerInterface() {
                public int getInt() {
                    return 554;
                }
            };
           IntegerInterface a553 = new IntegerInterface() {
                public int getInt() {
                    return 553;
                }
            };
           IntegerInterface a552 = new IntegerInterface() {
                public int getInt() {
                    return 552;
                }
            };
           IntegerInterface a551 = new IntegerInterface() {
                public int getInt() {
                    return 551;
                }
            };
           IntegerInterface a550 = new IntegerInterface() {
                public int getInt() {
                    return 550;
                }
            };
           IntegerInterface a549 = new IntegerInterface() {
                public int getInt() {
                    return 549;
                }
            };
           IntegerInterface a548 = new IntegerInterface() {
                public int getInt() {
                    return 548;
                }
            };
           IntegerInterface a547 = new IntegerInterface() {
                public int getInt() {
                    return 547;
                }
            };
           IntegerInterface a546 = new IntegerInterface() {
                public int getInt() {
                    return 546;
                }
            };
           IntegerInterface a545 = new IntegerInterface() {
                public int getInt() {
                    return 545;
                }
            };
           IntegerInterface a544 = new IntegerInterface() {
                public int getInt() {
                    return 544;
                }
            };
           IntegerInterface a543 = new IntegerInterface() {
                public int getInt() {
                    return 543;
                }
            };
           IntegerInterface a542 = new IntegerInterface() {
                public int getInt() {
                    return 542;
                }
            };
           IntegerInterface a541 = new IntegerInterface() {
                public int getInt() {
                    return 541;
                }
            };
           IntegerInterface a540 = new IntegerInterface() {
                public int getInt() {
                    return 540;
                }
            };
           IntegerInterface a539 = new IntegerInterface() {
                public int getInt() {
                    return 539;
                }
            };
           IntegerInterface a538 = new IntegerInterface() {
                public int getInt() {
                    return 538;
                }
            };
           IntegerInterface a537 = new IntegerInterface() {
                public int getInt() {
                    return 537;
                }
            };
           IntegerInterface a536 = new IntegerInterface() {
                public int getInt() {
                    return 536;
                }
            };
           IntegerInterface a535 = new IntegerInterface() {
                public int getInt() {
                    return 535;
                }
            };
           IntegerInterface a534 = new IntegerInterface() {
                public int getInt() {
                    return 534;
                }
            };
           IntegerInterface a533 = new IntegerInterface() {
                public int getInt() {
                    return 533;
                }
            };
           IntegerInterface a532 = new IntegerInterface() {
                public int getInt() {
                    return 532;
                }
            };
           IntegerInterface a531 = new IntegerInterface() {
                public int getInt() {
                    return 531;
                }
            };
           IntegerInterface a530 = new IntegerInterface() {
                public int getInt() {
                    return 530;
                }
            };
           IntegerInterface a529 = new IntegerInterface() {
                public int getInt() {
                    return 529;
                }
            };
           IntegerInterface a528 = new IntegerInterface() {
                public int getInt() {
                    return 528;
                }
            };
           IntegerInterface a527 = new IntegerInterface() {
                public int getInt() {
                    return 527;
                }
            };
           IntegerInterface a526 = new IntegerInterface() {
                public int getInt() {
                    return 526;
                }
            };
           IntegerInterface a525 = new IntegerInterface() {
                public int getInt() {
                    return 525;
                }
            };
           IntegerInterface a524 = new IntegerInterface() {
                public int getInt() {
                    return 524;
                }
            };
           IntegerInterface a523 = new IntegerInterface() {
                public int getInt() {
                    return 523;
                }
            };
           IntegerInterface a522 = new IntegerInterface() {
                public int getInt() {
                    return 522;
                }
            };
           IntegerInterface a521 = new IntegerInterface() {
                public int getInt() {
                    return 521;
                }
            };
           IntegerInterface a520 = new IntegerInterface() {
                public int getInt() {
                    return 520;
                }
            };
           IntegerInterface a519 = new IntegerInterface() {
                public int getInt() {
                    return 519;
                }
            };
           IntegerInterface a518 = new IntegerInterface() {
                public int getInt() {
                    return 518;
                }
            };
           IntegerInterface a517 = new IntegerInterface() {
                public int getInt() {
                    return 517;
                }
            };
           IntegerInterface a516 = new IntegerInterface() {
                public int getInt() {
                    return 516;
                }
            };
           IntegerInterface a515 = new IntegerInterface() {
                public int getInt() {
                    return 515;
                }
            };
           IntegerInterface a514 = new IntegerInterface() {
                public int getInt() {
                    return 514;
                }
            };
           IntegerInterface a513 = new IntegerInterface() {
                public int getInt() {
                    return 513;
                }
            };
           IntegerInterface a512 = new IntegerInterface() {
                public int getInt() {
                    return 512;
                }
            };
           IntegerInterface a511 = new IntegerInterface() {
                public int getInt() {
                    return 511;
                }
            };
           IntegerInterface a510 = new IntegerInterface() {
                public int getInt() {
                    return 510;
                }
            };
           IntegerInterface a509 = new IntegerInterface() {
                public int getInt() {
                    return 509;
                }
            };
           IntegerInterface a508 = new IntegerInterface() {
                public int getInt() {
                    return 508;
                }
            };
           IntegerInterface a507 = new IntegerInterface() {
                public int getInt() {
                    return 507;
                }
            };
           IntegerInterface a506 = new IntegerInterface() {
                public int getInt() {
                    return 506;
                }
            };
           IntegerInterface a505 = new IntegerInterface() {
                public int getInt() {
                    return 505;
                }
            };
           IntegerInterface a504 = new IntegerInterface() {
                public int getInt() {
                    return 504;
                }
            };
           IntegerInterface a503 = new IntegerInterface() {
                public int getInt() {
                    return 503;
                }
            };
           IntegerInterface a502 = new IntegerInterface() {
                public int getInt() {
                    return 502;
                }
            };
           IntegerInterface a501 = new IntegerInterface() {
                public int getInt() {
                    return 501;
                }
            };
           IntegerInterface a500 = new IntegerInterface() {
                public int getInt() {
                    return 500;
                }
            };
           IntegerInterface a499 = new IntegerInterface() {
                public int getInt() {
                    return 499;
                }
            };
           IntegerInterface a498 = new IntegerInterface() {
                public int getInt() {
                    return 498;
                }
            };
           IntegerInterface a497 = new IntegerInterface() {
                public int getInt() {
                    return 497;
                }
            };
           IntegerInterface a496 = new IntegerInterface() {
                public int getInt() {
                    return 496;
                }
            };
           IntegerInterface a495 = new IntegerInterface() {
                public int getInt() {
                    return 495;
                }
            };
           IntegerInterface a494 = new IntegerInterface() {
                public int getInt() {
                    return 494;
                }
            };
           IntegerInterface a493 = new IntegerInterface() {
                public int getInt() {
                    return 493;
                }
            };
           IntegerInterface a492 = new IntegerInterface() {
                public int getInt() {
                    return 492;
                }
            };
           IntegerInterface a491 = new IntegerInterface() {
                public int getInt() {
                    return 491;
                }
            };
           IntegerInterface a490 = new IntegerInterface() {
                public int getInt() {
                    return 490;
                }
            };
           IntegerInterface a489 = new IntegerInterface() {
                public int getInt() {
                    return 489;
                }
            };
           IntegerInterface a488 = new IntegerInterface() {
                public int getInt() {
                    return 488;
                }
            };
           IntegerInterface a487 = new IntegerInterface() {
                public int getInt() {
                    return 487;
                }
            };
           IntegerInterface a486 = new IntegerInterface() {
                public int getInt() {
                    return 486;
                }
            };
           IntegerInterface a485 = new IntegerInterface() {
                public int getInt() {
                    return 485;
                }
            };
           IntegerInterface a484 = new IntegerInterface() {
                public int getInt() {
                    return 484;
                }
            };
           IntegerInterface a483 = new IntegerInterface() {
                public int getInt() {
                    return 483;
                }
            };
           IntegerInterface a482 = new IntegerInterface() {
                public int getInt() {
                    return 482;
                }
            };
           IntegerInterface a481 = new IntegerInterface() {
                public int getInt() {
                    return 481;
                }
            };
           IntegerInterface a480 = new IntegerInterface() {
                public int getInt() {
                    return 480;
                }
            };
           IntegerInterface a479 = new IntegerInterface() {
                public int getInt() {
                    return 479;
                }
            };
           IntegerInterface a478 = new IntegerInterface() {
                public int getInt() {
                    return 478;
                }
            };
           IntegerInterface a477 = new IntegerInterface() {
                public int getInt() {
                    return 477;
                }
            };
           IntegerInterface a476 = new IntegerInterface() {
                public int getInt() {
                    return 476;
                }
            };
           IntegerInterface a475 = new IntegerInterface() {
                public int getInt() {
                    return 475;
                }
            };
           IntegerInterface a474 = new IntegerInterface() {
                public int getInt() {
                    return 474;
                }
            };
           IntegerInterface a473 = new IntegerInterface() {
                public int getInt() {
                    return 473;
                }
            };
           IntegerInterface a472 = new IntegerInterface() {
                public int getInt() {
                    return 472;
                }
            };
           IntegerInterface a471 = new IntegerInterface() {
                public int getInt() {
                    return 471;
                }
            };
           IntegerInterface a470 = new IntegerInterface() {
                public int getInt() {
                    return 470;
                }
            };
           IntegerInterface a469 = new IntegerInterface() {
                public int getInt() {
                    return 469;
                }
            };
           IntegerInterface a468 = new IntegerInterface() {
                public int getInt() {
                    return 468;
                }
            };
           IntegerInterface a467 = new IntegerInterface() {
                public int getInt() {
                    return 467;
                }
            };
           IntegerInterface a466 = new IntegerInterface() {
                public int getInt() {
                    return 466;
                }
            };
           IntegerInterface a465 = new IntegerInterface() {
                public int getInt() {
                    return 465;
                }
            };
           IntegerInterface a464 = new IntegerInterface() {
                public int getInt() {
                    return 464;
                }
            };
           IntegerInterface a463 = new IntegerInterface() {
                public int getInt() {
                    return 463;
                }
            };
           IntegerInterface a462 = new IntegerInterface() {
                public int getInt() {
                    return 462;
                }
            };
           IntegerInterface a461 = new IntegerInterface() {
                public int getInt() {
                    return 461;
                }
            };
           IntegerInterface a460 = new IntegerInterface() {
                public int getInt() {
                    return 460;
                }
            };
           IntegerInterface a459 = new IntegerInterface() {
                public int getInt() {
                    return 459;
                }
            };
           IntegerInterface a458 = new IntegerInterface() {
                public int getInt() {
                    return 458;
                }
            };
           IntegerInterface a457 = new IntegerInterface() {
                public int getInt() {
                    return 457;
                }
            };
           IntegerInterface a456 = new IntegerInterface() {
                public int getInt() {
                    return 456;
                }
            };
           IntegerInterface a455 = new IntegerInterface() {
                public int getInt() {
                    return 455;
                }
            };
           IntegerInterface a454 = new IntegerInterface() {
                public int getInt() {
                    return 454;
                }
            };
           IntegerInterface a453 = new IntegerInterface() {
                public int getInt() {
                    return 453;
                }
            };
           IntegerInterface a452 = new IntegerInterface() {
                public int getInt() {
                    return 452;
                }
            };
           IntegerInterface a451 = new IntegerInterface() {
                public int getInt() {
                    return 451;
                }
            };
           IntegerInterface a450 = new IntegerInterface() {
                public int getInt() {
                    return 450;
                }
            };
           IntegerInterface a449 = new IntegerInterface() {
                public int getInt() {
                    return 449;
                }
            };
           IntegerInterface a448 = new IntegerInterface() {
                public int getInt() {
                    return 448;
                }
            };
           IntegerInterface a447 = new IntegerInterface() {
                public int getInt() {
                    return 447;
                }
            };
           IntegerInterface a446 = new IntegerInterface() {
                public int getInt() {
                    return 446;
                }
            };
           IntegerInterface a445 = new IntegerInterface() {
                public int getInt() {
                    return 445;
                }
            };
           IntegerInterface a444 = new IntegerInterface() {
                public int getInt() {
                    return 444;
                }
            };
           IntegerInterface a443 = new IntegerInterface() {
                public int getInt() {
                    return 443;
                }
            };
           IntegerInterface a442 = new IntegerInterface() {
                public int getInt() {
                    return 442;
                }
            };
           IntegerInterface a441 = new IntegerInterface() {
                public int getInt() {
                    return 441;
                }
            };
           IntegerInterface a440 = new IntegerInterface() {
                public int getInt() {
                    return 440;
                }
            };
           IntegerInterface a439 = new IntegerInterface() {
                public int getInt() {
                    return 439;
                }
            };
           IntegerInterface a438 = new IntegerInterface() {
                public int getInt() {
                    return 438;
                }
            };
           IntegerInterface a437 = new IntegerInterface() {
                public int getInt() {
                    return 437;
                }
            };
           IntegerInterface a436 = new IntegerInterface() {
                public int getInt() {
                    return 436;
                }
            };
           IntegerInterface a435 = new IntegerInterface() {
                public int getInt() {
                    return 435;
                }
            };
           IntegerInterface a434 = new IntegerInterface() {
                public int getInt() {
                    return 434;
                }
            };
           IntegerInterface a433 = new IntegerInterface() {
                public int getInt() {
                    return 433;
                }
            };
           IntegerInterface a432 = new IntegerInterface() {
                public int getInt() {
                    return 432;
                }
            };
           IntegerInterface a431 = new IntegerInterface() {
                public int getInt() {
                    return 431;
                }
            };
           IntegerInterface a430 = new IntegerInterface() {
                public int getInt() {
                    return 430;
                }
            };
           IntegerInterface a429 = new IntegerInterface() {
                public int getInt() {
                    return 429;
                }
            };
           IntegerInterface a428 = new IntegerInterface() {
                public int getInt() {
                    return 428;
                }
            };
           IntegerInterface a427 = new IntegerInterface() {
                public int getInt() {
                    return 427;
                }
            };
           IntegerInterface a426 = new IntegerInterface() {
                public int getInt() {
                    return 426;
                }
            };
           IntegerInterface a425 = new IntegerInterface() {
                public int getInt() {
                    return 425;
                }
            };
           IntegerInterface a424 = new IntegerInterface() {
                public int getInt() {
                    return 424;
                }
            };
           IntegerInterface a423 = new IntegerInterface() {
                public int getInt() {
                    return 423;
                }
            };
           IntegerInterface a422 = new IntegerInterface() {
                public int getInt() {
                    return 422;
                }
            };
           IntegerInterface a421 = new IntegerInterface() {
                public int getInt() {
                    return 421;
                }
            };
           IntegerInterface a420 = new IntegerInterface() {
                public int getInt() {
                    return 420;
                }
            };
           IntegerInterface a419 = new IntegerInterface() {
                public int getInt() {
                    return 419;
                }
            };
           IntegerInterface a418 = new IntegerInterface() {
                public int getInt() {
                    return 418;
                }
            };
           IntegerInterface a417 = new IntegerInterface() {
                public int getInt() {
                    return 417;
                }
            };
           IntegerInterface a416 = new IntegerInterface() {
                public int getInt() {
                    return 416;
                }
            };
           IntegerInterface a415 = new IntegerInterface() {
                public int getInt() {
                    return 415;
                }
            };
           IntegerInterface a414 = new IntegerInterface() {
                public int getInt() {
                    return 414;
                }
            };
           IntegerInterface a413 = new IntegerInterface() {
                public int getInt() {
                    return 413;
                }
            };
           IntegerInterface a412 = new IntegerInterface() {
                public int getInt() {
                    return 412;
                }
            };
           IntegerInterface a411 = new IntegerInterface() {
                public int getInt() {
                    return 411;
                }
            };
           IntegerInterface a410 = new IntegerInterface() {
                public int getInt() {
                    return 410;
                }
            };
           IntegerInterface a409 = new IntegerInterface() {
                public int getInt() {
                    return 409;
                }
            };
           IntegerInterface a408 = new IntegerInterface() {
                public int getInt() {
                    return 408;
                }
            };
           IntegerInterface a407 = new IntegerInterface() {
                public int getInt() {
                    return 407;
                }
            };
           IntegerInterface a406 = new IntegerInterface() {
                public int getInt() {
                    return 406;
                }
            };
           IntegerInterface a405 = new IntegerInterface() {
                public int getInt() {
                    return 405;
                }
            };
           IntegerInterface a404 = new IntegerInterface() {
                public int getInt() {
                    return 404;
                }
            };
           IntegerInterface a403 = new IntegerInterface() {
                public int getInt() {
                    return 403;
                }
            };
           IntegerInterface a402 = new IntegerInterface() {
                public int getInt() {
                    return 402;
                }
            };
           IntegerInterface a401 = new IntegerInterface() {
                public int getInt() {
                    return 401;
                }
            };
           IntegerInterface a400 = new IntegerInterface() {
                public int getInt() {
                    return 400;
                }
            };
           IntegerInterface a399 = new IntegerInterface() {
                public int getInt() {
                    return 399;
                }
            };
           IntegerInterface a398 = new IntegerInterface() {
                public int getInt() {
                    return 398;
                }
            };
           IntegerInterface a397 = new IntegerInterface() {
                public int getInt() {
                    return 397;
                }
            };
           IntegerInterface a396 = new IntegerInterface() {
                public int getInt() {
                    return 396;
                }
            };
           IntegerInterface a395 = new IntegerInterface() {
                public int getInt() {
                    return 395;
                }
            };
           IntegerInterface a394 = new IntegerInterface() {
                public int getInt() {
                    return 394;
                }
            };
           IntegerInterface a393 = new IntegerInterface() {
                public int getInt() {
                    return 393;
                }
            };
           IntegerInterface a392 = new IntegerInterface() {
                public int getInt() {
                    return 392;
                }
            };
           IntegerInterface a391 = new IntegerInterface() {
                public int getInt() {
                    return 391;
                }
            };
           IntegerInterface a390 = new IntegerInterface() {
                public int getInt() {
                    return 390;
                }
            };
           IntegerInterface a389 = new IntegerInterface() {
                public int getInt() {
                    return 389;
                }
            };
           IntegerInterface a388 = new IntegerInterface() {
                public int getInt() {
                    return 388;
                }
            };
           IntegerInterface a387 = new IntegerInterface() {
                public int getInt() {
                    return 387;
                }
            };
           IntegerInterface a386 = new IntegerInterface() {
                public int getInt() {
                    return 386;
                }
            };
           IntegerInterface a385 = new IntegerInterface() {
                public int getInt() {
                    return 385;
                }
            };
           IntegerInterface a384 = new IntegerInterface() {
                public int getInt() {
                    return 384;
                }
            };
           IntegerInterface a383 = new IntegerInterface() {
                public int getInt() {
                    return 383;
                }
            };
           IntegerInterface a382 = new IntegerInterface() {
                public int getInt() {
                    return 382;
                }
            };
           IntegerInterface a381 = new IntegerInterface() {
                public int getInt() {
                    return 381;
                }
            };
           IntegerInterface a380 = new IntegerInterface() {
                public int getInt() {
                    return 380;
                }
            };
           IntegerInterface a379 = new IntegerInterface() {
                public int getInt() {
                    return 379;
                }
            };
           IntegerInterface a378 = new IntegerInterface() {
                public int getInt() {
                    return 378;
                }
            };
           IntegerInterface a377 = new IntegerInterface() {
                public int getInt() {
                    return 377;
                }
            };
           IntegerInterface a376 = new IntegerInterface() {
                public int getInt() {
                    return 376;
                }
            };
           IntegerInterface a375 = new IntegerInterface() {
                public int getInt() {
                    return 375;
                }
            };
           IntegerInterface a374 = new IntegerInterface() {
                public int getInt() {
                    return 374;
                }
            };
           IntegerInterface a373 = new IntegerInterface() {
                public int getInt() {
                    return 373;
                }
            };
           IntegerInterface a372 = new IntegerInterface() {
                public int getInt() {
                    return 372;
                }
            };
           IntegerInterface a371 = new IntegerInterface() {
                public int getInt() {
                    return 371;
                }
            };
           IntegerInterface a370 = new IntegerInterface() {
                public int getInt() {
                    return 370;
                }
            };
           IntegerInterface a369 = new IntegerInterface() {
                public int getInt() {
                    return 369;
                }
            };
           IntegerInterface a368 = new IntegerInterface() {
                public int getInt() {
                    return 368;
                }
            };
           IntegerInterface a367 = new IntegerInterface() {
                public int getInt() {
                    return 367;
                }
            };
           IntegerInterface a366 = new IntegerInterface() {
                public int getInt() {
                    return 366;
                }
            };
           IntegerInterface a365 = new IntegerInterface() {
                public int getInt() {
                    return 365;
                }
            };
           IntegerInterface a364 = new IntegerInterface() {
                public int getInt() {
                    return 364;
                }
            };
           IntegerInterface a363 = new IntegerInterface() {
                public int getInt() {
                    return 363;
                }
            };
           IntegerInterface a362 = new IntegerInterface() {
                public int getInt() {
                    return 362;
                }
            };
           IntegerInterface a361 = new IntegerInterface() {
                public int getInt() {
                    return 361;
                }
            };
           IntegerInterface a360 = new IntegerInterface() {
                public int getInt() {
                    return 360;
                }
            };
           IntegerInterface a359 = new IntegerInterface() {
                public int getInt() {
                    return 359;
                }
            };
           IntegerInterface a358 = new IntegerInterface() {
                public int getInt() {
                    return 358;
                }
            };
           IntegerInterface a357 = new IntegerInterface() {
                public int getInt() {
                    return 357;
                }
            };
           IntegerInterface a356 = new IntegerInterface() {
                public int getInt() {
                    return 356;
                }
            };
           IntegerInterface a355 = new IntegerInterface() {
                public int getInt() {
                    return 355;
                }
            };
           IntegerInterface a354 = new IntegerInterface() {
                public int getInt() {
                    return 354;
                }
            };
           IntegerInterface a353 = new IntegerInterface() {
                public int getInt() {
                    return 353;
                }
            };
           IntegerInterface a352 = new IntegerInterface() {
                public int getInt() {
                    return 352;
                }
            };
           IntegerInterface a351 = new IntegerInterface() {
                public int getInt() {
                    return 351;
                }
            };
           IntegerInterface a350 = new IntegerInterface() {
                public int getInt() {
                    return 350;
                }
            };
           IntegerInterface a349 = new IntegerInterface() {
                public int getInt() {
                    return 349;
                }
            };
           IntegerInterface a348 = new IntegerInterface() {
                public int getInt() {
                    return 348;
                }
            };
           IntegerInterface a347 = new IntegerInterface() {
                public int getInt() {
                    return 347;
                }
            };
           IntegerInterface a346 = new IntegerInterface() {
                public int getInt() {
                    return 346;
                }
            };
           IntegerInterface a345 = new IntegerInterface() {
                public int getInt() {
                    return 345;
                }
            };
           IntegerInterface a344 = new IntegerInterface() {
                public int getInt() {
                    return 344;
                }
            };
           IntegerInterface a343 = new IntegerInterface() {
                public int getInt() {
                    return 343;
                }
            };
           IntegerInterface a342 = new IntegerInterface() {
                public int getInt() {
                    return 342;
                }
            };
           IntegerInterface a341 = new IntegerInterface() {
                public int getInt() {
                    return 341;
                }
            };
           IntegerInterface a340 = new IntegerInterface() {
                public int getInt() {
                    return 340;
                }
            };
           IntegerInterface a339 = new IntegerInterface() {
                public int getInt() {
                    return 339;
                }
            };
           IntegerInterface a338 = new IntegerInterface() {
                public int getInt() {
                    return 338;
                }
            };
           IntegerInterface a337 = new IntegerInterface() {
                public int getInt() {
                    return 337;
                }
            };
           IntegerInterface a336 = new IntegerInterface() {
                public int getInt() {
                    return 336;
                }
            };
           IntegerInterface a335 = new IntegerInterface() {
                public int getInt() {
                    return 335;
                }
            };
           IntegerInterface a334 = new IntegerInterface() {
                public int getInt() {
                    return 334;
                }
            };
           IntegerInterface a333 = new IntegerInterface() {
                public int getInt() {
                    return 333;
                }
            };
           IntegerInterface a332 = new IntegerInterface() {
                public int getInt() {
                    return 332;
                }
            };
           IntegerInterface a331 = new IntegerInterface() {
                public int getInt() {
                    return 331;
                }
            };
           IntegerInterface a330 = new IntegerInterface() {
                public int getInt() {
                    return 330;
                }
            };
           IntegerInterface a329 = new IntegerInterface() {
                public int getInt() {
                    return 329;
                }
            };
           IntegerInterface a328 = new IntegerInterface() {
                public int getInt() {
                    return 328;
                }
            };
           IntegerInterface a327 = new IntegerInterface() {
                public int getInt() {
                    return 327;
                }
            };
           IntegerInterface a326 = new IntegerInterface() {
                public int getInt() {
                    return 326;
                }
            };
           IntegerInterface a325 = new IntegerInterface() {
                public int getInt() {
                    return 325;
                }
            };
           IntegerInterface a324 = new IntegerInterface() {
                public int getInt() {
                    return 324;
                }
            };
           IntegerInterface a323 = new IntegerInterface() {
                public int getInt() {
                    return 323;
                }
            };
           IntegerInterface a322 = new IntegerInterface() {
                public int getInt() {
                    return 322;
                }
            };
           IntegerInterface a321 = new IntegerInterface() {
                public int getInt() {
                    return 321;
                }
            };
           IntegerInterface a320 = new IntegerInterface() {
                public int getInt() {
                    return 320;
                }
            };
           IntegerInterface a319 = new IntegerInterface() {
                public int getInt() {
                    return 319;
                }
            };
           IntegerInterface a318 = new IntegerInterface() {
                public int getInt() {
                    return 318;
                }
            };
           IntegerInterface a317 = new IntegerInterface() {
                public int getInt() {
                    return 317;
                }
            };
           IntegerInterface a316 = new IntegerInterface() {
                public int getInt() {
                    return 316;
                }
            };
           IntegerInterface a315 = new IntegerInterface() {
                public int getInt() {
                    return 315;
                }
            };
           IntegerInterface a314 = new IntegerInterface() {
                public int getInt() {
                    return 314;
                }
            };
           IntegerInterface a313 = new IntegerInterface() {
                public int getInt() {
                    return 313;
                }
            };
           IntegerInterface a312 = new IntegerInterface() {
                public int getInt() {
                    return 312;
                }
            };
           IntegerInterface a311 = new IntegerInterface() {
                public int getInt() {
                    return 311;
                }
            };
           IntegerInterface a310 = new IntegerInterface() {
                public int getInt() {
                    return 310;
                }
            };
           IntegerInterface a309 = new IntegerInterface() {
                public int getInt() {
                    return 309;
                }
            };
           IntegerInterface a308 = new IntegerInterface() {
                public int getInt() {
                    return 308;
                }
            };
           IntegerInterface a307 = new IntegerInterface() {
                public int getInt() {
                    return 307;
                }
            };
           IntegerInterface a306 = new IntegerInterface() {
                public int getInt() {
                    return 306;
                }
            };
           IntegerInterface a305 = new IntegerInterface() {
                public int getInt() {
                    return 305;
                }
            };
           IntegerInterface a304 = new IntegerInterface() {
                public int getInt() {
                    return 304;
                }
            };
           IntegerInterface a303 = new IntegerInterface() {
                public int getInt() {
                    return 303;
                }
            };
           IntegerInterface a302 = new IntegerInterface() {
                public int getInt() {
                    return 302;
                }
            };
           IntegerInterface a301 = new IntegerInterface() {
                public int getInt() {
                    return 301;
                }
            };
           IntegerInterface a300 = new IntegerInterface() {
                public int getInt() {
                    return 300;
                }
            };
           IntegerInterface a299 = new IntegerInterface() {
                public int getInt() {
                    return 299;
                }
            };
           IntegerInterface a298 = new IntegerInterface() {
                public int getInt() {
                    return 298;
                }
            };
           IntegerInterface a297 = new IntegerInterface() {
                public int getInt() {
                    return 297;
                }
            };
           IntegerInterface a296 = new IntegerInterface() {
                public int getInt() {
                    return 296;
                }
            };
           IntegerInterface a295 = new IntegerInterface() {
                public int getInt() {
                    return 295;
                }
            };
           IntegerInterface a294 = new IntegerInterface() {
                public int getInt() {
                    return 294;
                }
            };
           IntegerInterface a293 = new IntegerInterface() {
                public int getInt() {
                    return 293;
                }
            };
           IntegerInterface a292 = new IntegerInterface() {
                public int getInt() {
                    return 292;
                }
            };
           IntegerInterface a291 = new IntegerInterface() {
                public int getInt() {
                    return 291;
                }
            };
           IntegerInterface a290 = new IntegerInterface() {
                public int getInt() {
                    return 290;
                }
            };
           IntegerInterface a289 = new IntegerInterface() {
                public int getInt() {
                    return 289;
                }
            };
           IntegerInterface a288 = new IntegerInterface() {
                public int getInt() {
                    return 288;
                }
            };
           IntegerInterface a287 = new IntegerInterface() {
                public int getInt() {
                    return 287;
                }
            };
           IntegerInterface a286 = new IntegerInterface() {
                public int getInt() {
                    return 286;
                }
            };
           IntegerInterface a285 = new IntegerInterface() {
                public int getInt() {
                    return 285;
                }
            };
           IntegerInterface a284 = new IntegerInterface() {
                public int getInt() {
                    return 284;
                }
            };
           IntegerInterface a283 = new IntegerInterface() {
                public int getInt() {
                    return 283;
                }
            };
           IntegerInterface a282 = new IntegerInterface() {
                public int getInt() {
                    return 282;
                }
            };
           IntegerInterface a281 = new IntegerInterface() {
                public int getInt() {
                    return 281;
                }
            };
           IntegerInterface a280 = new IntegerInterface() {
                public int getInt() {
                    return 280;
                }
            };
           IntegerInterface a279 = new IntegerInterface() {
                public int getInt() {
                    return 279;
                }
            };
           IntegerInterface a278 = new IntegerInterface() {
                public int getInt() {
                    return 278;
                }
            };
           IntegerInterface a277 = new IntegerInterface() {
                public int getInt() {
                    return 277;
                }
            };
           IntegerInterface a276 = new IntegerInterface() {
                public int getInt() {
                    return 276;
                }
            };
           IntegerInterface a275 = new IntegerInterface() {
                public int getInt() {
                    return 275;
                }
            };
           IntegerInterface a274 = new IntegerInterface() {
                public int getInt() {
                    return 274;
                }
            };
           IntegerInterface a273 = new IntegerInterface() {
                public int getInt() {
                    return 273;
                }
            };
           IntegerInterface a272 = new IntegerInterface() {
                public int getInt() {
                    return 272;
                }
            };
           IntegerInterface a271 = new IntegerInterface() {
                public int getInt() {
                    return 271;
                }
            };
           IntegerInterface a270 = new IntegerInterface() {
                public int getInt() {
                    return 270;
                }
            };
           IntegerInterface a269 = new IntegerInterface() {
                public int getInt() {
                    return 269;
                }
            };
           IntegerInterface a268 = new IntegerInterface() {
                public int getInt() {
                    return 268;
                }
            };
           IntegerInterface a267 = new IntegerInterface() {
                public int getInt() {
                    return 267;
                }
            };
           IntegerInterface a266 = new IntegerInterface() {
                public int getInt() {
                    return 266;
                }
            };
           IntegerInterface a265 = new IntegerInterface() {
                public int getInt() {
                    return 265;
                }
            };
           IntegerInterface a264 = new IntegerInterface() {
                public int getInt() {
                    return 264;
                }
            };
           IntegerInterface a263 = new IntegerInterface() {
                public int getInt() {
                    return 263;
                }
            };
           IntegerInterface a262 = new IntegerInterface() {
                public int getInt() {
                    return 262;
                }
            };
           IntegerInterface a261 = new IntegerInterface() {
                public int getInt() {
                    return 261;
                }
            };
           IntegerInterface a260 = new IntegerInterface() {
                public int getInt() {
                    return 260;
                }
            };
           IntegerInterface a259 = new IntegerInterface() {
                public int getInt() {
                    return 259;
                }
            };
           IntegerInterface a258 = new IntegerInterface() {
                public int getInt() {
                    return 258;
                }
            };
           IntegerInterface a257 = new IntegerInterface() {
                public int getInt() {
                    return 257;
                }
            };
           IntegerInterface a256 = new IntegerInterface() {
                public int getInt() {
                    return 256;
                }
            };
           IntegerInterface a255 = new IntegerInterface() {
                public int getInt() {
                    return 255;
                }
            };
           IntegerInterface a254 = new IntegerInterface() {
                public int getInt() {
                    return 254;
                }
            };
           IntegerInterface a253 = new IntegerInterface() {
                public int getInt() {
                    return 253;
                }
            };
           IntegerInterface a252 = new IntegerInterface() {
                public int getInt() {
                    return 252;
                }
            };
           IntegerInterface a251 = new IntegerInterface() {
                public int getInt() {
                    return 251;
                }
            };
           IntegerInterface a250 = new IntegerInterface() {
                public int getInt() {
                    return 250;
                }
            };
           IntegerInterface a249 = new IntegerInterface() {
                public int getInt() {
                    return 249;
                }
            };
           IntegerInterface a248 = new IntegerInterface() {
                public int getInt() {
                    return 248;
                }
            };
           IntegerInterface a247 = new IntegerInterface() {
                public int getInt() {
                    return 247;
                }
            };
           IntegerInterface a246 = new IntegerInterface() {
                public int getInt() {
                    return 246;
                }
            };
           IntegerInterface a245 = new IntegerInterface() {
                public int getInt() {
                    return 245;
                }
            };
           IntegerInterface a244 = new IntegerInterface() {
                public int getInt() {
                    return 244;
                }
            };
           IntegerInterface a243 = new IntegerInterface() {
                public int getInt() {
                    return 243;
                }
            };
           IntegerInterface a242 = new IntegerInterface() {
                public int getInt() {
                    return 242;
                }
            };
           IntegerInterface a241 = new IntegerInterface() {
                public int getInt() {
                    return 241;
                }
            };
           IntegerInterface a240 = new IntegerInterface() {
                public int getInt() {
                    return 240;
                }
            };
           IntegerInterface a239 = new IntegerInterface() {
                public int getInt() {
                    return 239;
                }
            };
           IntegerInterface a238 = new IntegerInterface() {
                public int getInt() {
                    return 238;
                }
            };
           IntegerInterface a237 = new IntegerInterface() {
                public int getInt() {
                    return 237;
                }
            };
           IntegerInterface a236 = new IntegerInterface() {
                public int getInt() {
                    return 236;
                }
            };
           IntegerInterface a235 = new IntegerInterface() {
                public int getInt() {
                    return 235;
                }
            };
           IntegerInterface a234 = new IntegerInterface() {
                public int getInt() {
                    return 234;
                }
            };
           IntegerInterface a233 = new IntegerInterface() {
                public int getInt() {
                    return 233;
                }
            };
           IntegerInterface a232 = new IntegerInterface() {
                public int getInt() {
                    return 232;
                }
            };
           IntegerInterface a231 = new IntegerInterface() {
                public int getInt() {
                    return 231;
                }
            };
           IntegerInterface a230 = new IntegerInterface() {
                public int getInt() {
                    return 230;
                }
            };
           IntegerInterface a229 = new IntegerInterface() {
                public int getInt() {
                    return 229;
                }
            };
           IntegerInterface a228 = new IntegerInterface() {
                public int getInt() {
                    return 228;
                }
            };
           IntegerInterface a227 = new IntegerInterface() {
                public int getInt() {
                    return 227;
                }
            };
           IntegerInterface a226 = new IntegerInterface() {
                public int getInt() {
                    return 226;
                }
            };
           IntegerInterface a225 = new IntegerInterface() {
                public int getInt() {
                    return 225;
                }
            };
           IntegerInterface a224 = new IntegerInterface() {
                public int getInt() {
                    return 224;
                }
            };
           IntegerInterface a223 = new IntegerInterface() {
                public int getInt() {
                    return 223;
                }
            };
           IntegerInterface a222 = new IntegerInterface() {
                public int getInt() {
                    return 222;
                }
            };
           IntegerInterface a221 = new IntegerInterface() {
                public int getInt() {
                    return 221;
                }
            };
           IntegerInterface a220 = new IntegerInterface() {
                public int getInt() {
                    return 220;
                }
            };
           IntegerInterface a219 = new IntegerInterface() {
                public int getInt() {
                    return 219;
                }
            };
           IntegerInterface a218 = new IntegerInterface() {
                public int getInt() {
                    return 218;
                }
            };
           IntegerInterface a217 = new IntegerInterface() {
                public int getInt() {
                    return 217;
                }
            };
           IntegerInterface a216 = new IntegerInterface() {
                public int getInt() {
                    return 216;
                }
            };
           IntegerInterface a215 = new IntegerInterface() {
                public int getInt() {
                    return 215;
                }
            };
           IntegerInterface a214 = new IntegerInterface() {
                public int getInt() {
                    return 214;
                }
            };
           IntegerInterface a213 = new IntegerInterface() {
                public int getInt() {
                    return 213;
                }
            };
           IntegerInterface a212 = new IntegerInterface() {
                public int getInt() {
                    return 212;
                }
            };
           IntegerInterface a211 = new IntegerInterface() {
                public int getInt() {
                    return 211;
                }
            };
           IntegerInterface a210 = new IntegerInterface() {
                public int getInt() {
                    return 210;
                }
            };
           IntegerInterface a209 = new IntegerInterface() {
                public int getInt() {
                    return 209;
                }
            };
           IntegerInterface a208 = new IntegerInterface() {
                public int getInt() {
                    return 208;
                }
            };
           IntegerInterface a207 = new IntegerInterface() {
                public int getInt() {
                    return 207;
                }
            };
           IntegerInterface a206 = new IntegerInterface() {
                public int getInt() {
                    return 206;
                }
            };
           IntegerInterface a205 = new IntegerInterface() {
                public int getInt() {
                    return 205;
                }
            };
           IntegerInterface a204 = new IntegerInterface() {
                public int getInt() {
                    return 204;
                }
            };
           IntegerInterface a203 = new IntegerInterface() {
                public int getInt() {
                    return 203;
                }
            };
           IntegerInterface a202 = new IntegerInterface() {
                public int getInt() {
                    return 202;
                }
            };
           IntegerInterface a201 = new IntegerInterface() {
                public int getInt() {
                    return 201;
                }
            };
           IntegerInterface a200 = new IntegerInterface() {
                public int getInt() {
                    return 200;
                }
            };
           IntegerInterface a199 = new IntegerInterface() {
                public int getInt() {
                    return 199;
                }
            };
           IntegerInterface a198 = new IntegerInterface() {
                public int getInt() {
                    return 198;
                }
            };
           IntegerInterface a197 = new IntegerInterface() {
                public int getInt() {
                    return 197;
                }
            };
           IntegerInterface a196 = new IntegerInterface() {
                public int getInt() {
                    return 196;
                }
            };
           IntegerInterface a195 = new IntegerInterface() {
                public int getInt() {
                    return 195;
                }
            };
           IntegerInterface a194 = new IntegerInterface() {
                public int getInt() {
                    return 194;
                }
            };
           IntegerInterface a193 = new IntegerInterface() {
                public int getInt() {
                    return 193;
                }
            };
           IntegerInterface a192 = new IntegerInterface() {
                public int getInt() {
                    return 192;
                }
            };
           IntegerInterface a191 = new IntegerInterface() {
                public int getInt() {
                    return 191;
                }
            };
           IntegerInterface a190 = new IntegerInterface() {
                public int getInt() {
                    return 190;
                }
            };
           IntegerInterface a189 = new IntegerInterface() {
                public int getInt() {
                    return 189;
                }
            };
           IntegerInterface a188 = new IntegerInterface() {
                public int getInt() {
                    return 188;
                }
            };
           IntegerInterface a187 = new IntegerInterface() {
                public int getInt() {
                    return 187;
                }
            };
           IntegerInterface a186 = new IntegerInterface() {
                public int getInt() {
                    return 186;
                }
            };
           IntegerInterface a185 = new IntegerInterface() {
                public int getInt() {
                    return 185;
                }
            };
           IntegerInterface a184 = new IntegerInterface() {
                public int getInt() {
                    return 184;
                }
            };
           IntegerInterface a183 = new IntegerInterface() {
                public int getInt() {
                    return 183;
                }
            };
           IntegerInterface a182 = new IntegerInterface() {
                public int getInt() {
                    return 182;
                }
            };
           IntegerInterface a181 = new IntegerInterface() {
                public int getInt() {
                    return 181;
                }
            };
           IntegerInterface a180 = new IntegerInterface() {
                public int getInt() {
                    return 180;
                }
            };
           IntegerInterface a179 = new IntegerInterface() {
                public int getInt() {
                    return 179;
                }
            };
           IntegerInterface a178 = new IntegerInterface() {
                public int getInt() {
                    return 178;
                }
            };
           IntegerInterface a177 = new IntegerInterface() {
                public int getInt() {
                    return 177;
                }
            };
           IntegerInterface a176 = new IntegerInterface() {
                public int getInt() {
                    return 176;
                }
            };
           IntegerInterface a175 = new IntegerInterface() {
                public int getInt() {
                    return 175;
                }
            };
           IntegerInterface a174 = new IntegerInterface() {
                public int getInt() {
                    return 174;
                }
            };
           IntegerInterface a173 = new IntegerInterface() {
                public int getInt() {
                    return 173;
                }
            };
           IntegerInterface a172 = new IntegerInterface() {
                public int getInt() {
                    return 172;
                }
            };
           IntegerInterface a171 = new IntegerInterface() {
                public int getInt() {
                    return 171;
                }
            };
           IntegerInterface a170 = new IntegerInterface() {
                public int getInt() {
                    return 170;
                }
            };
           IntegerInterface a169 = new IntegerInterface() {
                public int getInt() {
                    return 169;
                }
            };
           IntegerInterface a168 = new IntegerInterface() {
                public int getInt() {
                    return 168;
                }
            };
           IntegerInterface a167 = new IntegerInterface() {
                public int getInt() {
                    return 167;
                }
            };
           IntegerInterface a166 = new IntegerInterface() {
                public int getInt() {
                    return 166;
                }
            };
           IntegerInterface a165 = new IntegerInterface() {
                public int getInt() {
                    return 165;
                }
            };
           IntegerInterface a164 = new IntegerInterface() {
                public int getInt() {
                    return 164;
                }
            };
           IntegerInterface a163 = new IntegerInterface() {
                public int getInt() {
                    return 163;
                }
            };
           IntegerInterface a162 = new IntegerInterface() {
                public int getInt() {
                    return 162;
                }
            };
           IntegerInterface a161 = new IntegerInterface() {
                public int getInt() {
                    return 161;
                }
            };
           IntegerInterface a160 = new IntegerInterface() {
                public int getInt() {
                    return 160;
                }
            };
           IntegerInterface a159 = new IntegerInterface() {
                public int getInt() {
                    return 159;
                }
            };
           IntegerInterface a158 = new IntegerInterface() {
                public int getInt() {
                    return 158;
                }
            };
           IntegerInterface a157 = new IntegerInterface() {
                public int getInt() {
                    return 157;
                }
            };
           IntegerInterface a156 = new IntegerInterface() {
                public int getInt() {
                    return 156;
                }
            };
           IntegerInterface a155 = new IntegerInterface() {
                public int getInt() {
                    return 155;
                }
            };
           IntegerInterface a154 = new IntegerInterface() {
                public int getInt() {
                    return 154;
                }
            };
           IntegerInterface a153 = new IntegerInterface() {
                public int getInt() {
                    return 153;
                }
            };
           IntegerInterface a152 = new IntegerInterface() {
                public int getInt() {
                    return 152;
                }
            };
           IntegerInterface a151 = new IntegerInterface() {
                public int getInt() {
                    return 151;
                }
            };
           IntegerInterface a150 = new IntegerInterface() {
                public int getInt() {
                    return 150;
                }
            };
           IntegerInterface a149 = new IntegerInterface() {
                public int getInt() {
                    return 149;
                }
            };
           IntegerInterface a148 = new IntegerInterface() {
                public int getInt() {
                    return 148;
                }
            };
           IntegerInterface a147 = new IntegerInterface() {
                public int getInt() {
                    return 147;
                }
            };
           IntegerInterface a146 = new IntegerInterface() {
                public int getInt() {
                    return 146;
                }
            };
           IntegerInterface a145 = new IntegerInterface() {
                public int getInt() {
                    return 145;
                }
            };
           IntegerInterface a144 = new IntegerInterface() {
                public int getInt() {
                    return 144;
                }
            };
           IntegerInterface a143 = new IntegerInterface() {
                public int getInt() {
                    return 143;
                }
            };
           IntegerInterface a142 = new IntegerInterface() {
                public int getInt() {
                    return 142;
                }
            };
           IntegerInterface a141 = new IntegerInterface() {
                public int getInt() {
                    return 141;
                }
            };
           IntegerInterface a140 = new IntegerInterface() {
                public int getInt() {
                    return 140;
                }
            };
           IntegerInterface a139 = new IntegerInterface() {
                public int getInt() {
                    return 139;
                }
            };
           IntegerInterface a138 = new IntegerInterface() {
                public int getInt() {
                    return 138;
                }
            };
           IntegerInterface a137 = new IntegerInterface() {
                public int getInt() {
                    return 137;
                }
            };
           IntegerInterface a136 = new IntegerInterface() {
                public int getInt() {
                    return 136;
                }
            };
           IntegerInterface a135 = new IntegerInterface() {
                public int getInt() {
                    return 135;
                }
            };
           IntegerInterface a134 = new IntegerInterface() {
                public int getInt() {
                    return 134;
                }
            };
           IntegerInterface a133 = new IntegerInterface() {
                public int getInt() {
                    return 133;
                }
            };
           IntegerInterface a132 = new IntegerInterface() {
                public int getInt() {
                    return 132;
                }
            };
           IntegerInterface a131 = new IntegerInterface() {
                public int getInt() {
                    return 131;
                }
            };
           IntegerInterface a130 = new IntegerInterface() {
                public int getInt() {
                    return 130;
                }
            };
           IntegerInterface a129 = new IntegerInterface() {
                public int getInt() {
                    return 129;
                }
            };
           IntegerInterface a128 = new IntegerInterface() {
                public int getInt() {
                    return 128;
                }
            };
           IntegerInterface a127 = new IntegerInterface() {
                public int getInt() {
                    return 127;
                }
            };
           IntegerInterface a126 = new IntegerInterface() {
                public int getInt() {
                    return 126;
                }
            };
           IntegerInterface a125 = new IntegerInterface() {
                public int getInt() {
                    return 125;
                }
            };
           IntegerInterface a124 = new IntegerInterface() {
                public int getInt() {
                    return 124;
                }
            };
           IntegerInterface a123 = new IntegerInterface() {
                public int getInt() {
                    return 123;
                }
            };
           IntegerInterface a122 = new IntegerInterface() {
                public int getInt() {
                    return 122;
                }
            };
           IntegerInterface a121 = new IntegerInterface() {
                public int getInt() {
                    return 121;
                }
            };
           IntegerInterface a120 = new IntegerInterface() {
                public int getInt() {
                    return 120;
                }
            };
           IntegerInterface a119 = new IntegerInterface() {
                public int getInt() {
                    return 119;
                }
            };
           IntegerInterface a118 = new IntegerInterface() {
                public int getInt() {
                    return 118;
                }
            };
           IntegerInterface a117 = new IntegerInterface() {
                public int getInt() {
                    return 117;
                }
            };
           IntegerInterface a116 = new IntegerInterface() {
                public int getInt() {
                    return 116;
                }
            };
           IntegerInterface a115 = new IntegerInterface() {
                public int getInt() {
                    return 115;
                }
            };
           IntegerInterface a114 = new IntegerInterface() {
                public int getInt() {
                    return 114;
                }
            };
           IntegerInterface a113 = new IntegerInterface() {
                public int getInt() {
                    return 113;
                }
            };
           IntegerInterface a112 = new IntegerInterface() {
                public int getInt() {
                    return 112;
                }
            };
           IntegerInterface a111 = new IntegerInterface() {
                public int getInt() {
                    return 111;
                }
            };
           IntegerInterface a110 = new IntegerInterface() {
                public int getInt() {
                    return 110;
                }
            };
           IntegerInterface a109 = new IntegerInterface() {
                public int getInt() {
                    return 109;
                }
            };
           IntegerInterface a108 = new IntegerInterface() {
                public int getInt() {
                    return 108;
                }
            };
           IntegerInterface a107 = new IntegerInterface() {
                public int getInt() {
                    return 107;
                }
            };
           IntegerInterface a106 = new IntegerInterface() {
                public int getInt() {
                    return 106;
                }
            };
           IntegerInterface a105 = new IntegerInterface() {
                public int getInt() {
                    return 105;
                }
            };
           IntegerInterface a104 = new IntegerInterface() {
                public int getInt() {
                    return 104;
                }
            };
           IntegerInterface a103 = new IntegerInterface() {
                public int getInt() {
                    return 103;
                }
            };
           IntegerInterface a102 = new IntegerInterface() {
                public int getInt() {
                    return 102;
                }
            };
           IntegerInterface a101 = new IntegerInterface() {
                public int getInt() {
                    return 101;
                }
            };
           IntegerInterface a100 = new IntegerInterface() {
                public int getInt() {
                    return 100;
                }
            };
           IntegerInterface a99 = new IntegerInterface() {
                public int getInt() {
                    return 99;
                }
            };
           IntegerInterface a98 = new IntegerInterface() {
                public int getInt() {
                    return 98;
                }
            };
           IntegerInterface a97 = new IntegerInterface() {
                public int getInt() {
                    return 97;
                }
            };
           IntegerInterface a96 = new IntegerInterface() {
                public int getInt() {
                    return 96;
                }
            };
           IntegerInterface a95 = new IntegerInterface() {
                public int getInt() {
                    return 95;
                }
            };
           IntegerInterface a94 = new IntegerInterface() {
                public int getInt() {
                    return 94;
                }
            };
           IntegerInterface a93 = new IntegerInterface() {
                public int getInt() {
                    return 93;
                }
            };
           IntegerInterface a92 = new IntegerInterface() {
                public int getInt() {
                    return 92;
                }
            };
           IntegerInterface a91 = new IntegerInterface() {
                public int getInt() {
                    return 91;
                }
            };
           IntegerInterface a90 = new IntegerInterface() {
                public int getInt() {
                    return 90;
                }
            };
           IntegerInterface a89 = new IntegerInterface() {
                public int getInt() {
                    return 89;
                }
            };
           IntegerInterface a88 = new IntegerInterface() {
                public int getInt() {
                    return 88;
                }
            };
           IntegerInterface a87 = new IntegerInterface() {
                public int getInt() {
                    return 87;
                }
            };
           IntegerInterface a86 = new IntegerInterface() {
                public int getInt() {
                    return 86;
                }
            };
           IntegerInterface a85 = new IntegerInterface() {
                public int getInt() {
                    return 85;
                }
            };
           IntegerInterface a84 = new IntegerInterface() {
                public int getInt() {
                    return 84;
                }
            };
           IntegerInterface a83 = new IntegerInterface() {
                public int getInt() {
                    return 83;
                }
            };
           IntegerInterface a82 = new IntegerInterface() {
                public int getInt() {
                    return 82;
                }
            };
           IntegerInterface a81 = new IntegerInterface() {
                public int getInt() {
                    return 81;
                }
            };
           IntegerInterface a80 = new IntegerInterface() {
                public int getInt() {
                    return 80;
                }
            };
           IntegerInterface a79 = new IntegerInterface() {
                public int getInt() {
                    return 79;
                }
            };
           IntegerInterface a78 = new IntegerInterface() {
                public int getInt() {
                    return 78;
                }
            };
           IntegerInterface a77 = new IntegerInterface() {
                public int getInt() {
                    return 77;
                }
            };
           IntegerInterface a76 = new IntegerInterface() {
                public int getInt() {
                    return 76;
                }
            };
           IntegerInterface a75 = new IntegerInterface() {
                public int getInt() {
                    return 75;
                }
            };
           IntegerInterface a74 = new IntegerInterface() {
                public int getInt() {
                    return 74;
                }
            };
           IntegerInterface a73 = new IntegerInterface() {
                public int getInt() {
                    return 73;
                }
            };
           IntegerInterface a72 = new IntegerInterface() {
                public int getInt() {
                    return 72;
                }
            };
           IntegerInterface a71 = new IntegerInterface() {
                public int getInt() {
                    return 71;
                }
            };
           IntegerInterface a70 = new IntegerInterface() {
                public int getInt() {
                    return 70;
                }
            };
           IntegerInterface a69 = new IntegerInterface() {
                public int getInt() {
                    return 69;
                }
            };
           IntegerInterface a68 = new IntegerInterface() {
                public int getInt() {
                    return 68;
                }
            };
           IntegerInterface a67 = new IntegerInterface() {
                public int getInt() {
                    return 67;
                }
            };
           IntegerInterface a66 = new IntegerInterface() {
                public int getInt() {
                    return 66;
                }
            };
           IntegerInterface a65 = new IntegerInterface() {
                public int getInt() {
                    return 65;
                }
            };
           IntegerInterface a64 = new IntegerInterface() {
                public int getInt() {
                    return 64;
                }
            };
           IntegerInterface a63 = new IntegerInterface() {
                public int getInt() {
                    return 63;
                }
            };
           IntegerInterface a62 = new IntegerInterface() {
                public int getInt() {
                    return 62;
                }
            };
           IntegerInterface a61 = new IntegerInterface() {
                public int getInt() {
                    return 61;
                }
            };
           IntegerInterface a60 = new IntegerInterface() {
                public int getInt() {
                    return 60;
                }
            };
           IntegerInterface a59 = new IntegerInterface() {
                public int getInt() {
                    return 59;
                }
            };
           IntegerInterface a58 = new IntegerInterface() {
                public int getInt() {
                    return 58;
                }
            };
           IntegerInterface a57 = new IntegerInterface() {
                public int getInt() {
                    return 57;
                }
            };
           IntegerInterface a56 = new IntegerInterface() {
                public int getInt() {
                    return 56;
                }
            };
           IntegerInterface a55 = new IntegerInterface() {
                public int getInt() {
                    return 55;
                }
            };
           IntegerInterface a54 = new IntegerInterface() {
                public int getInt() {
                    return 54;
                }
            };
           IntegerInterface a53 = new IntegerInterface() {
                public int getInt() {
                    return 53;
                }
            };
           IntegerInterface a52 = new IntegerInterface() {
                public int getInt() {
                    return 52;
                }
            };
           IntegerInterface a51 = new IntegerInterface() {
                public int getInt() {
                    return 51;
                }
            };
           IntegerInterface a50 = new IntegerInterface() {
                public int getInt() {
                    return 50;
                }
            };
           IntegerInterface a49 = new IntegerInterface() {
                public int getInt() {
                    return 49;
                }
            };
           IntegerInterface a48 = new IntegerInterface() {
                public int getInt() {
                    return 48;
                }
            };
           IntegerInterface a47 = new IntegerInterface() {
                public int getInt() {
                    return 47;
                }
            };
           IntegerInterface a46 = new IntegerInterface() {
                public int getInt() {
                    return 46;
                }
            };
           IntegerInterface a45 = new IntegerInterface() {
                public int getInt() {
                    return 45;
                }
            };
           IntegerInterface a44 = new IntegerInterface() {
                public int getInt() {
                    return 44;
                }
            };
           IntegerInterface a43 = new IntegerInterface() {
                public int getInt() {
                    return 43;
                }
            };
           IntegerInterface a42 = new IntegerInterface() {
                public int getInt() {
                    return 42;
                }
            };
           IntegerInterface a41 = new IntegerInterface() {
                public int getInt() {
                    return 41;
                }
            };
           IntegerInterface a40 = new IntegerInterface() {
                public int getInt() {
                    return 40;
                }
            };
           IntegerInterface a39 = new IntegerInterface() {
                public int getInt() {
                    return 39;
                }
            };
           IntegerInterface a38 = new IntegerInterface() {
                public int getInt() {
                    return 38;
                }
            };
           IntegerInterface a37 = new IntegerInterface() {
                public int getInt() {
                    return 37;
                }
            };
           IntegerInterface a36 = new IntegerInterface() {
                public int getInt() {
                    return 36;
                }
            };
           IntegerInterface a35 = new IntegerInterface() {
                public int getInt() {
                    return 35;
                }
            };
           IntegerInterface a34 = new IntegerInterface() {
                public int getInt() {
                    return 34;
                }
            };
           IntegerInterface a33 = new IntegerInterface() {
                public int getInt() {
                    return 33;
                }
            };
           IntegerInterface a32 = new IntegerInterface() {
                public int getInt() {
                    return 32;
                }
            };
           IntegerInterface a31 = new IntegerInterface() {
                public int getInt() {
                    return 31;
                }
            };
           IntegerInterface a30 = new IntegerInterface() {
                public int getInt() {
                    return 30;
                }
            };
           IntegerInterface a29 = new IntegerInterface() {
                public int getInt() {
                    return 29;
                }
            };
           IntegerInterface a28 = new IntegerInterface() {
                public int getInt() {
                    return 28;
                }
            };
           IntegerInterface a27 = new IntegerInterface() {
                public int getInt() {
                    return 27;
                }
            };
           IntegerInterface a26 = new IntegerInterface() {
                public int getInt() {
                    return 26;
                }
            };
           IntegerInterface a25 = new IntegerInterface() {
                public int getInt() {
                    return 25;
                }
            };
           IntegerInterface a24 = new IntegerInterface() {
                public int getInt() {
                    return 24;
                }
            };
           IntegerInterface a23 = new IntegerInterface() {
                public int getInt() {
                    return 23;
                }
            };
           IntegerInterface a22 = new IntegerInterface() {
                public int getInt() {
                    return 22;
                }
            };
           IntegerInterface a21 = new IntegerInterface() {
                public int getInt() {
                    return 21;
                }
            };
           IntegerInterface a20 = new IntegerInterface() {
                public int getInt() {
                    return 20;
                }
            };
           IntegerInterface a19 = new IntegerInterface() {
                public int getInt() {
                    return 19;
                }
            };
           IntegerInterface a18 = new IntegerInterface() {
                public int getInt() {
                    return 18;
                }
            };
           IntegerInterface a17 = new IntegerInterface() {
                public int getInt() {
                    return 17;
                }
            };
           IntegerInterface a16 = new IntegerInterface() {
                public int getInt() {
                    return 16;
                }
            };
           IntegerInterface a15 = new IntegerInterface() {
                public int getInt() {
                    return 15;
                }
            };
           IntegerInterface a14 = new IntegerInterface() {
                public int getInt() {
                    return 14;
                }
            };
           IntegerInterface a13 = new IntegerInterface() {
                public int getInt() {
                    return 13;
                }
            };
           IntegerInterface a12 = new IntegerInterface() {
                public int getInt() {
                    return 12;
                }
            };
           IntegerInterface a11 = new IntegerInterface() {
                public int getInt() {
                    return 11;
                }
            };
           IntegerInterface a10 = new IntegerInterface() {
                public int getInt() {
                    return 10;
                }
            };
           IntegerInterface a9 = new IntegerInterface() {
                public int getInt() {
                    return 9;
                }
            };
           IntegerInterface a8 = new IntegerInterface() {
                public int getInt() {
                    return 8;
                }
            };
           IntegerInterface a7 = new IntegerInterface() {
                public int getInt() {
                    return 7;
                }
            };
           IntegerInterface a6 = new IntegerInterface() {
                public int getInt() {
                    return 6;
                }
            };
           IntegerInterface a5 = new IntegerInterface() {
                public int getInt() {
                    return 5;
                }
            };
           IntegerInterface a4 = new IntegerInterface() {
                public int getInt() {
                    return 4;
                }
            };
           IntegerInterface a3 = new IntegerInterface() {
                public int getInt() {
                    return 3;
                }
            };
           IntegerInterface a2 = new IntegerInterface() {
                public int getInt() {
                    return 2;
                }
            };
           IntegerInterface a1 = new IntegerInterface() {
                public int getInt() {
                    return 1;
                }
            };
            return 
                a1024.getInt() +
                a1023.getInt() +
                a1022.getInt() +
                a1021.getInt() +
                a1020.getInt() +
                a1019.getInt() +
                a1018.getInt() +
                a1017.getInt() +
                a1016.getInt() +
                a1015.getInt() +
                a1014.getInt() +
                a1013.getInt() +
                a1012.getInt() +
                a1011.getInt() +
                a1010.getInt() +
                a1009.getInt() +
                a1008.getInt() +
                a1007.getInt() +
                a1006.getInt() +
                a1005.getInt() +
                a1004.getInt() +
                a1003.getInt() +
                a1002.getInt() +
                a1001.getInt() +
                a1000.getInt() +
                a999.getInt() +
                a998.getInt() +
                a997.getInt() +
                a996.getInt() +
                a995.getInt() +
                a994.getInt() +
                a993.getInt() +
                a992.getInt() +
                a991.getInt() +
                a990.getInt() +
                a989.getInt() +
                a988.getInt() +
                a987.getInt() +
                a986.getInt() +
                a985.getInt() +
                a984.getInt() +
                a983.getInt() +
                a982.getInt() +
                a981.getInt() +
                a980.getInt() +
                a979.getInt() +
                a978.getInt() +
                a977.getInt() +
                a976.getInt() +
                a975.getInt() +
                a974.getInt() +
                a973.getInt() +
                a972.getInt() +
                a971.getInt() +
                a970.getInt() +
                a969.getInt() +
                a968.getInt() +
                a967.getInt() +
                a966.getInt() +
                a965.getInt() +
                a964.getInt() +
                a963.getInt() +
                a962.getInt() +
                a961.getInt() +
                a960.getInt() +
                a959.getInt() +
                a958.getInt() +
                a957.getInt() +
                a956.getInt() +
                a955.getInt() +
                a954.getInt() +
                a953.getInt() +
                a952.getInt() +
                a951.getInt() +
                a950.getInt() +
                a949.getInt() +
                a948.getInt() +
                a947.getInt() +
                a946.getInt() +
                a945.getInt() +
                a944.getInt() +
                a943.getInt() +
                a942.getInt() +
                a941.getInt() +
                a940.getInt() +
                a939.getInt() +
                a938.getInt() +
                a937.getInt() +
                a936.getInt() +
                a935.getInt() +
                a934.getInt() +
                a933.getInt() +
                a932.getInt() +
                a931.getInt() +
                a930.getInt() +
                a929.getInt() +
                a928.getInt() +
                a927.getInt() +
                a926.getInt() +
                a925.getInt() +
                a924.getInt() +
                a923.getInt() +
                a922.getInt() +
                a921.getInt() +
                a920.getInt() +
                a919.getInt() +
                a918.getInt() +
                a917.getInt() +
                a916.getInt() +
                a915.getInt() +
                a914.getInt() +
                a913.getInt() +
                a912.getInt() +
                a911.getInt() +
                a910.getInt() +
                a909.getInt() +
                a908.getInt() +
                a907.getInt() +
                a906.getInt() +
                a905.getInt() +
                a904.getInt() +
                a903.getInt() +
                a902.getInt() +
                a901.getInt() +
                a900.getInt() +
                a899.getInt() +
                a898.getInt() +
                a897.getInt() +
                a896.getInt() +
                a895.getInt() +
                a894.getInt() +
                a893.getInt() +
                a892.getInt() +
                a891.getInt() +
                a890.getInt() +
                a889.getInt() +
                a888.getInt() +
                a887.getInt() +
                a886.getInt() +
                a885.getInt() +
                a884.getInt() +
                a883.getInt() +
                a882.getInt() +
                a881.getInt() +
                a880.getInt() +
                a879.getInt() +
                a878.getInt() +
                a877.getInt() +
                a876.getInt() +
                a875.getInt() +
                a874.getInt() +
                a873.getInt() +
                a872.getInt() +
                a871.getInt() +
                a870.getInt() +
                a869.getInt() +
                a868.getInt() +
                a867.getInt() +
                a866.getInt() +
                a865.getInt() +
                a864.getInt() +
                a863.getInt() +
                a862.getInt() +
                a861.getInt() +
                a860.getInt() +
                a859.getInt() +
                a858.getInt() +
                a857.getInt() +
                a856.getInt() +
                a855.getInt() +
                a854.getInt() +
                a853.getInt() +
                a852.getInt() +
                a851.getInt() +
                a850.getInt() +
                a849.getInt() +
                a848.getInt() +
                a847.getInt() +
                a846.getInt() +
                a845.getInt() +
                a844.getInt() +
                a843.getInt() +
                a842.getInt() +
                a841.getInt() +
                a840.getInt() +
                a839.getInt() +
                a838.getInt() +
                a837.getInt() +
                a836.getInt() +
                a835.getInt() +
                a834.getInt() +
                a833.getInt() +
                a832.getInt() +
                a831.getInt() +
                a830.getInt() +
                a829.getInt() +
                a828.getInt() +
                a827.getInt() +
                a826.getInt() +
                a825.getInt() +
                a824.getInt() +
                a823.getInt() +
                a822.getInt() +
                a821.getInt() +
                a820.getInt() +
                a819.getInt() +
                a818.getInt() +
                a817.getInt() +
                a816.getInt() +
                a815.getInt() +
                a814.getInt() +
                a813.getInt() +
                a812.getInt() +
                a811.getInt() +
                a810.getInt() +
                a809.getInt() +
                a808.getInt() +
                a807.getInt() +
                a806.getInt() +
                a805.getInt() +
                a804.getInt() +
                a803.getInt() +
                a802.getInt() +
                a801.getInt() +
                a800.getInt() +
                a799.getInt() +
                a798.getInt() +
                a797.getInt() +
                a796.getInt() +
                a795.getInt() +
                a794.getInt() +
                a793.getInt() +
                a792.getInt() +
                a791.getInt() +
                a790.getInt() +
                a789.getInt() +
                a788.getInt() +
                a787.getInt() +
                a786.getInt() +
                a785.getInt() +
                a784.getInt() +
                a783.getInt() +
                a782.getInt() +
                a781.getInt() +
                a780.getInt() +
                a779.getInt() +
                a778.getInt() +
                a777.getInt() +
                a776.getInt() +
                a775.getInt() +
                a774.getInt() +
                a773.getInt() +
                a772.getInt() +
                a771.getInt() +
                a770.getInt() +
                a769.getInt() +
                a768.getInt() +
                a767.getInt() +
                a766.getInt() +
                a765.getInt() +
                a764.getInt() +
                a763.getInt() +
                a762.getInt() +
                a761.getInt() +
                a760.getInt() +
                a759.getInt() +
                a758.getInt() +
                a757.getInt() +
                a756.getInt() +
                a755.getInt() +
                a754.getInt() +
                a753.getInt() +
                a752.getInt() +
                a751.getInt() +
                a750.getInt() +
                a749.getInt() +
                a748.getInt() +
                a747.getInt() +
                a746.getInt() +
                a745.getInt() +
                a744.getInt() +
                a743.getInt() +
                a742.getInt() +
                a741.getInt() +
                a740.getInt() +
                a739.getInt() +
                a738.getInt() +
                a737.getInt() +
                a736.getInt() +
                a735.getInt() +
                a734.getInt() +
                a733.getInt() +
                a732.getInt() +
                a731.getInt() +
                a730.getInt() +
                a729.getInt() +
                a728.getInt() +
                a727.getInt() +
                a726.getInt() +
                a725.getInt() +
                a724.getInt() +
                a723.getInt() +
                a722.getInt() +
                a721.getInt() +
                a720.getInt() +
                a719.getInt() +
                a718.getInt() +
                a717.getInt() +
                a716.getInt() +
                a715.getInt() +
                a714.getInt() +
                a713.getInt() +
                a712.getInt() +
                a711.getInt() +
                a710.getInt() +
                a709.getInt() +
                a708.getInt() +
                a707.getInt() +
                a706.getInt() +
                a705.getInt() +
                a704.getInt() +
                a703.getInt() +
                a702.getInt() +
                a701.getInt() +
                a700.getInt() +
                a699.getInt() +
                a698.getInt() +
                a697.getInt() +
                a696.getInt() +
                a695.getInt() +
                a694.getInt() +
                a693.getInt() +
                a692.getInt() +
                a691.getInt() +
                a690.getInt() +
                a689.getInt() +
                a688.getInt() +
                a687.getInt() +
                a686.getInt() +
                a685.getInt() +
                a684.getInt() +
                a683.getInt() +
                a682.getInt() +
                a681.getInt() +
                a680.getInt() +
                a679.getInt() +
                a678.getInt() +
                a677.getInt() +
                a676.getInt() +
                a675.getInt() +
                a674.getInt() +
                a673.getInt() +
                a672.getInt() +
                a671.getInt() +
                a670.getInt() +
                a669.getInt() +
                a668.getInt() +
                a667.getInt() +
                a666.getInt() +
                a665.getInt() +
                a664.getInt() +
                a663.getInt() +
                a662.getInt() +
                a661.getInt() +
                a660.getInt() +
                a659.getInt() +
                a658.getInt() +
                a657.getInt() +
                a656.getInt() +
                a655.getInt() +
                a654.getInt() +
                a653.getInt() +
                a652.getInt() +
                a651.getInt() +
                a650.getInt() +
                a649.getInt() +
                a648.getInt() +
                a647.getInt() +
                a646.getInt() +
                a645.getInt() +
                a644.getInt() +
                a643.getInt() +
                a642.getInt() +
                a641.getInt() +
                a640.getInt() +
                a639.getInt() +
                a638.getInt() +
                a637.getInt() +
                a636.getInt() +
                a635.getInt() +
                a634.getInt() +
                a633.getInt() +
                a632.getInt() +
                a631.getInt() +
                a630.getInt() +
                a629.getInt() +
                a628.getInt() +
                a627.getInt() +
                a626.getInt() +
                a625.getInt() +
                a624.getInt() +
                a623.getInt() +
                a622.getInt() +
                a621.getInt() +
                a620.getInt() +
                a619.getInt() +
                a618.getInt() +
                a617.getInt() +
                a616.getInt() +
                a615.getInt() +
                a614.getInt() +
                a613.getInt() +
                a612.getInt() +
                a611.getInt() +
                a610.getInt() +
                a609.getInt() +
                a608.getInt() +
                a607.getInt() +
                a606.getInt() +
                a605.getInt() +
                a604.getInt() +
                a603.getInt() +
                a602.getInt() +
                a601.getInt() +
                a600.getInt() +
                a599.getInt() +
                a598.getInt() +
                a597.getInt() +
                a596.getInt() +
                a595.getInt() +
                a594.getInt() +
                a593.getInt() +
                a592.getInt() +
                a591.getInt() +
                a590.getInt() +
                a589.getInt() +
                a588.getInt() +
                a587.getInt() +
                a586.getInt() +
                a585.getInt() +
                a584.getInt() +
                a583.getInt() +
                a582.getInt() +
                a581.getInt() +
                a580.getInt() +
                a579.getInt() +
                a578.getInt() +
                a577.getInt() +
                a576.getInt() +
                a575.getInt() +
                a574.getInt() +
                a573.getInt() +
                a572.getInt() +
                a571.getInt() +
                a570.getInt() +
                a569.getInt() +
                a568.getInt() +
                a567.getInt() +
                a566.getInt() +
                a565.getInt() +
                a564.getInt() +
                a563.getInt() +
                a562.getInt() +
                a561.getInt() +
                a560.getInt() +
                a559.getInt() +
                a558.getInt() +
                a557.getInt() +
                a556.getInt() +
                a555.getInt() +
                a554.getInt() +
                a553.getInt() +
                a552.getInt() +
                a551.getInt() +
                a550.getInt() +
                a549.getInt() +
                a548.getInt() +
                a547.getInt() +
                a546.getInt() +
                a545.getInt() +
                a544.getInt() +
                a543.getInt() +
                a542.getInt() +
                a541.getInt() +
                a540.getInt() +
                a539.getInt() +
                a538.getInt() +
                a537.getInt() +
                a536.getInt() +
                a535.getInt() +
                a534.getInt() +
                a533.getInt() +
                a532.getInt() +
                a531.getInt() +
                a530.getInt() +
                a529.getInt() +
                a528.getInt() +
                a527.getInt() +
                a526.getInt() +
                a525.getInt() +
                a524.getInt() +
                a523.getInt() +
                a522.getInt() +
                a521.getInt() +
                a520.getInt() +
                a519.getInt() +
                a518.getInt() +
                a517.getInt() +
                a516.getInt() +
                a515.getInt() +
                a514.getInt() +
                a513.getInt() +
                a512.getInt() +
                a511.getInt() +
                a510.getInt() +
                a509.getInt() +
                a508.getInt() +
                a507.getInt() +
                a506.getInt() +
                a505.getInt() +
                a504.getInt() +
                a503.getInt() +
                a502.getInt() +
                a501.getInt() +
                a500.getInt() +
                a499.getInt() +
                a498.getInt() +
                a497.getInt() +
                a496.getInt() +
                a495.getInt() +
                a494.getInt() +
                a493.getInt() +
                a492.getInt() +
                a491.getInt() +
                a490.getInt() +
                a489.getInt() +
                a488.getInt() +
                a487.getInt() +
                a486.getInt() +
                a485.getInt() +
                a484.getInt() +
                a483.getInt() +
                a482.getInt() +
                a481.getInt() +
                a480.getInt() +
                a479.getInt() +
                a478.getInt() +
                a477.getInt() +
                a476.getInt() +
                a475.getInt() +
                a474.getInt() +
                a473.getInt() +
                a472.getInt() +
                a471.getInt() +
                a470.getInt() +
                a469.getInt() +
                a468.getInt() +
                a467.getInt() +
                a466.getInt() +
                a465.getInt() +
                a464.getInt() +
                a463.getInt() +
                a462.getInt() +
                a461.getInt() +
                a460.getInt() +
                a459.getInt() +
                a458.getInt() +
                a457.getInt() +
                a456.getInt() +
                a455.getInt() +
                a454.getInt() +
                a453.getInt() +
                a452.getInt() +
                a451.getInt() +
                a450.getInt() +
                a449.getInt() +
                a448.getInt() +
                a447.getInt() +
                a446.getInt() +
                a445.getInt() +
                a444.getInt() +
                a443.getInt() +
                a442.getInt() +
                a441.getInt() +
                a440.getInt() +
                a439.getInt() +
                a438.getInt() +
                a437.getInt() +
                a436.getInt() +
                a435.getInt() +
                a434.getInt() +
                a433.getInt() +
                a432.getInt() +
                a431.getInt() +
                a430.getInt() +
                a429.getInt() +
                a428.getInt() +
                a427.getInt() +
                a426.getInt() +
                a425.getInt() +
                a424.getInt() +
                a423.getInt() +
                a422.getInt() +
                a421.getInt() +
                a420.getInt() +
                a419.getInt() +
                a418.getInt() +
                a417.getInt() +
                a416.getInt() +
                a415.getInt() +
                a414.getInt() +
                a413.getInt() +
                a412.getInt() +
                a411.getInt() +
                a410.getInt() +
                a409.getInt() +
                a408.getInt() +
                a407.getInt() +
                a406.getInt() +
                a405.getInt() +
                a404.getInt() +
                a403.getInt() +
                a402.getInt() +
                a401.getInt() +
                a400.getInt() +
                a399.getInt() +
                a398.getInt() +
                a397.getInt() +
                a396.getInt() +
                a395.getInt() +
                a394.getInt() +
                a393.getInt() +
                a392.getInt() +
                a391.getInt() +
                a390.getInt() +
                a389.getInt() +
                a388.getInt() +
                a387.getInt() +
                a386.getInt() +
                a385.getInt() +
                a384.getInt() +
                a383.getInt() +
                a382.getInt() +
                a381.getInt() +
                a380.getInt() +
                a379.getInt() +
                a378.getInt() +
                a377.getInt() +
                a376.getInt() +
                a375.getInt() +
                a374.getInt() +
                a373.getInt() +
                a372.getInt() +
                a371.getInt() +
                a370.getInt() +
                a369.getInt() +
                a368.getInt() +
                a367.getInt() +
                a366.getInt() +
                a365.getInt() +
                a364.getInt() +
                a363.getInt() +
                a362.getInt() +
                a361.getInt() +
                a360.getInt() +
                a359.getInt() +
                a358.getInt() +
                a357.getInt() +
                a356.getInt() +
                a355.getInt() +
                a354.getInt() +
                a353.getInt() +
                a352.getInt() +
                a351.getInt() +
                a350.getInt() +
                a349.getInt() +
                a348.getInt() +
                a347.getInt() +
                a346.getInt() +
                a345.getInt() +
                a344.getInt() +
                a343.getInt() +
                a342.getInt() +
                a341.getInt() +
                a340.getInt() +
                a339.getInt() +
                a338.getInt() +
                a337.getInt() +
                a336.getInt() +
                a335.getInt() +
                a334.getInt() +
                a333.getInt() +
                a332.getInt() +
                a331.getInt() +
                a330.getInt() +
                a329.getInt() +
                a328.getInt() +
                a327.getInt() +
                a326.getInt() +
                a325.getInt() +
                a324.getInt() +
                a323.getInt() +
                a322.getInt() +
                a321.getInt() +
                a320.getInt() +
                a319.getInt() +
                a318.getInt() +
                a317.getInt() +
                a316.getInt() +
                a315.getInt() +
                a314.getInt() +
                a313.getInt() +
                a312.getInt() +
                a311.getInt() +
                a310.getInt() +
                a309.getInt() +
                a308.getInt() +
                a307.getInt() +
                a306.getInt() +
                a305.getInt() +
                a304.getInt() +
                a303.getInt() +
                a302.getInt() +
                a301.getInt() +
                a300.getInt() +
                a299.getInt() +
                a298.getInt() +
                a297.getInt() +
                a296.getInt() +
                a295.getInt() +
                a294.getInt() +
                a293.getInt() +
                a292.getInt() +
                a291.getInt() +
                a290.getInt() +
                a289.getInt() +
                a288.getInt() +
                a287.getInt() +
                a286.getInt() +
                a285.getInt() +
                a284.getInt() +
                a283.getInt() +
                a282.getInt() +
                a281.getInt() +
                a280.getInt() +
                a279.getInt() +
                a278.getInt() +
                a277.getInt() +
                a276.getInt() +
                a275.getInt() +
                a274.getInt() +
                a273.getInt() +
                a272.getInt() +
                a271.getInt() +
                a270.getInt() +
                a269.getInt() +
                a268.getInt() +
                a267.getInt() +
                a266.getInt() +
                a265.getInt() +
                a264.getInt() +
                a263.getInt() +
                a262.getInt() +
                a261.getInt() +
                a260.getInt() +
                a259.getInt() +
                a258.getInt() +
                a257.getInt() +
                a256.getInt() +
                a255.getInt() +
                a254.getInt() +
                a253.getInt() +
                a252.getInt() +
                a251.getInt() +
                a250.getInt() +
                a249.getInt() +
                a248.getInt() +
                a247.getInt() +
                a246.getInt() +
                a245.getInt() +
                a244.getInt() +
                a243.getInt() +
                a242.getInt() +
                a241.getInt() +
                a240.getInt() +
                a239.getInt() +
                a238.getInt() +
                a237.getInt() +
                a236.getInt() +
                a235.getInt() +
                a234.getInt() +
                a233.getInt() +
                a232.getInt() +
                a231.getInt() +
                a230.getInt() +
                a229.getInt() +
                a228.getInt() +
                a227.getInt() +
                a226.getInt() +
                a225.getInt() +
                a224.getInt() +
                a223.getInt() +
                a222.getInt() +
                a221.getInt() +
                a220.getInt() +
                a219.getInt() +
                a218.getInt() +
                a217.getInt() +
                a216.getInt() +
                a215.getInt() +
                a214.getInt() +
                a213.getInt() +
                a212.getInt() +
                a211.getInt() +
                a210.getInt() +
                a209.getInt() +
                a208.getInt() +
                a207.getInt() +
                a206.getInt() +
                a205.getInt() +
                a204.getInt() +
                a203.getInt() +
                a202.getInt() +
                a201.getInt() +
                a200.getInt() +
                a199.getInt() +
                a198.getInt() +
                a197.getInt() +
                a196.getInt() +
                a195.getInt() +
                a194.getInt() +
                a193.getInt() +
                a192.getInt() +
                a191.getInt() +
                a190.getInt() +
                a189.getInt() +
                a188.getInt() +
                a187.getInt() +
                a186.getInt() +
                a185.getInt() +
                a184.getInt() +
                a183.getInt() +
                a182.getInt() +
                a181.getInt() +
                a180.getInt() +
                a179.getInt() +
                a178.getInt() +
                a177.getInt() +
                a176.getInt() +
                a175.getInt() +
                a174.getInt() +
                a173.getInt() +
                a172.getInt() +
                a171.getInt() +
                a170.getInt() +
                a169.getInt() +
                a168.getInt() +
                a167.getInt() +
                a166.getInt() +
                a165.getInt() +
                a164.getInt() +
                a163.getInt() +
                a162.getInt() +
                a161.getInt() +
                a160.getInt() +
                a159.getInt() +
                a158.getInt() +
                a157.getInt() +
                a156.getInt() +
                a155.getInt() +
                a154.getInt() +
                a153.getInt() +
                a152.getInt() +
                a151.getInt() +
                a150.getInt() +
                a149.getInt() +
                a148.getInt() +
                a147.getInt() +
                a146.getInt() +
                a145.getInt() +
                a144.getInt() +
                a143.getInt() +
                a142.getInt() +
                a141.getInt() +
                a140.getInt() +
                a139.getInt() +
                a138.getInt() +
                a137.getInt() +
                a136.getInt() +
                a135.getInt() +
                a134.getInt() +
                a133.getInt() +
                a132.getInt() +
                a131.getInt() +
                a130.getInt() +
                a129.getInt() +
                a128.getInt() +
                a127.getInt() +
                a126.getInt() +
                a125.getInt() +
                a124.getInt() +
                a123.getInt() +
                a122.getInt() +
                a121.getInt() +
                a120.getInt() +
                a119.getInt() +
                a118.getInt() +
                a117.getInt() +
                a116.getInt() +
                a115.getInt() +
                a114.getInt() +
                a113.getInt() +
                a112.getInt() +
                a111.getInt() +
                a110.getInt() +
                a109.getInt() +
                a108.getInt() +
                a107.getInt() +
                a106.getInt() +
                a105.getInt() +
                a104.getInt() +
                a103.getInt() +
                a102.getInt() +
                a101.getInt() +
                a100.getInt() +
                a99.getInt() +
                a98.getInt() +
                a97.getInt() +
                a96.getInt() +
                a95.getInt() +
                a94.getInt() +
                a93.getInt() +
                a92.getInt() +
                a91.getInt() +
                a90.getInt() +
                a89.getInt() +
                a88.getInt() +
                a87.getInt() +
                a86.getInt() +
                a85.getInt() +
                a84.getInt() +
                a83.getInt() +
                a82.getInt() +
                a81.getInt() +
                a80.getInt() +
                a79.getInt() +
                a78.getInt() +
                a77.getInt() +
                a76.getInt() +
                a75.getInt() +
                a74.getInt() +
                a73.getInt() +
                a72.getInt() +
                a71.getInt() +
                a70.getInt() +
                a69.getInt() +
                a68.getInt() +
                a67.getInt() +
                a66.getInt() +
                a65.getInt() +
                a64.getInt() +
                a63.getInt() +
                a62.getInt() +
                a61.getInt() +
                a60.getInt() +
                a59.getInt() +
                a58.getInt() +
                a57.getInt() +
                a56.getInt() +
                a55.getInt() +
                a54.getInt() +
                a53.getInt() +
                a52.getInt() +
                a51.getInt() +
                a50.getInt() +
                a49.getInt() +
                a48.getInt() +
                a47.getInt() +
                a46.getInt() +
                a45.getInt() +
                a44.getInt() +
                a43.getInt() +
                a42.getInt() +
                a41.getInt() +
                a40.getInt() +
                a39.getInt() +
                a38.getInt() +
                a37.getInt() +
                a36.getInt() +
                a35.getInt() +
                a34.getInt() +
                a33.getInt() +
                a32.getInt() +
                a31.getInt() +
                a30.getInt() +
                a29.getInt() +
                a28.getInt() +
                a27.getInt() +
                a26.getInt() +
                a25.getInt() +
                a24.getInt() +
                a23.getInt() +
                a22.getInt() +
                a21.getInt() +
                a20.getInt() +
                a19.getInt() +
                a18.getInt() +
                a17.getInt() +
                a16.getInt() +
                a15.getInt() +
                a14.getInt() +
                a13.getInt() +
                a12.getInt() +
                a11.getInt() +
                a10.getInt() +
                a9.getInt() +
                a8.getInt() +
                a7.getInt() +
                a6.getInt() +
                a5.getInt() +
                a4.getInt() +
                a3.getInt() +
                a2.getInt() +
                a1.getInt();
        }
    }

    private static class AnonymousIntTest3 implements CalcInterface {
        public int calc() {
           IntegerInterface a3 = new IntegerInterface() {
                public int getInt() {
                    return 3;
                }
            };
           IntegerInterface a2 = new IntegerInterface() {
                public int getInt() {
                    return 2;
                }
            };
           IntegerInterface a1 = new IntegerInterface() {
                public int getInt() {
                    return 1;
                }
            };
            return a3.getInt() + a2.getInt() + a1.getInt();
        }
    }

    private static IntegerInterface ii1 = new IntegerInterface() { 
        public int getInt() {
            return 1;
        }
    };
    private static IntegerInterface ii2 = new IntegerInterface() { 
        public int getInt() {
            return 2;
        }
    };
    private static IntegerInterface ii3 = new IntegerInterface() { 
        public int getInt() {
            return 3;
        }
    };
    private static IntegerInterface ii4 = new IntegerInterface() { 
        public int getInt() {
            return 4;
        }
    };
    private static IntegerInterface ii5 = new IntegerInterface() { 
        public int getInt() {
            return 5;
        }
    };
    private static IntegerInterface ii6 = new IntegerInterface() { 
        public int getInt() {
            return 6;
        }
    };
    private static IntegerInterface ii7 = new IntegerInterface() { 
        public int getInt() {
            return 7;
        }
    };
    private static IntegerInterface ii8 = new IntegerInterface() { 
        public int getInt() {
            return 8;
        }
    };
    private static IntegerInterface ii9 = new IntegerInterface() { 
        public int getInt() {
            return 9;
        }
    };
    private static IntegerInterface ii10 = new IntegerInterface() { 
        public int getInt() {
            return 10;
        }
    };
    private static IntegerInterface ii11 = new IntegerInterface() { 
        public int getInt() {
            return 11;
        }
    };
    private static IntegerInterface ii12 = new IntegerInterface() { 
        public int getInt() {
            return 12;
        }
    };
    private static IntegerInterface ii13 = new IntegerInterface() { 
        public int getInt() {
            return 13;
        }
    };
    private static IntegerInterface ii14 = new IntegerInterface() { 
        public int getInt() {
            return 14;
        }
    };
    private static IntegerInterface ii15 = new IntegerInterface() { 
        public int getInt() {
            return 15;
        }
    };
    private static IntegerInterface ii16 = new IntegerInterface() { 
        public int getInt() {
            return 16;
        }
    };
    private static IntegerInterface ii17 = new IntegerInterface() { 
        public int getInt() {
            return 17;
        }
    };
    private static IntegerInterface ii18 = new IntegerInterface() { 
        public int getInt() {
            return 18;
        }
    };
    private static IntegerInterface ii19 = new IntegerInterface() { 
        public int getInt() {
            return 19;
        }
    };
    private static IntegerInterface ii20 = new IntegerInterface() { 
        public int getInt() {
            return 20;
        }
    };
    private static IntegerInterface ii21 = new IntegerInterface() { 
        public int getInt() {
            return 21;
        }
    };
    private static IntegerInterface ii22 = new IntegerInterface() { 
        public int getInt() {
            return 22;
        }
    };
    private static IntegerInterface ii23 = new IntegerInterface() { 
        public int getInt() {
            return 23;
        }
    };
    private static IntegerInterface ii24 = new IntegerInterface() { 
        public int getInt() {
            return 24;
        }
    };
    private static IntegerInterface ii25 = new IntegerInterface() { 
        public int getInt() {
            return 25;
        }
    };
    private static IntegerInterface ii26 = new IntegerInterface() { 
        public int getInt() {
            return 26;
        }
    };
    private static IntegerInterface ii27 = new IntegerInterface() { 
        public int getInt() {
            return 27;
        }
    };
    private static IntegerInterface ii28 = new IntegerInterface() { 
        public int getInt() {
            return 28;
        }
    };
    private static IntegerInterface ii29 = new IntegerInterface() { 
        public int getInt() {
            return 29;
        }
    };
    private static IntegerInterface ii30 = new IntegerInterface() { 
        public int getInt() {
            return 30;
        }
    };
    private static IntegerInterface ii31 = new IntegerInterface() { 
        public int getInt() {
            return 31;
        }
    };
    private static IntegerInterface ii32 = new IntegerInterface() { 
        public int getInt() {
            return 32;
        }
    };
    private static IntegerInterface ii33 = new IntegerInterface() { 
        public int getInt() {
            return 33;
        }
    };
    private static IntegerInterface ii34 = new IntegerInterface() { 
        public int getInt() {
            return 34;
        }
    };
    private static IntegerInterface ii35 = new IntegerInterface() { 
        public int getInt() {
            return 35;
        }
    };
    private static IntegerInterface ii36 = new IntegerInterface() { 
        public int getInt() {
            return 36;
        }
    };
    private static IntegerInterface ii37 = new IntegerInterface() { 
        public int getInt() {
            return 37;
        }
    };
    private static IntegerInterface ii38 = new IntegerInterface() { 
        public int getInt() {
            return 38;
        }
    };
    private static IntegerInterface ii39 = new IntegerInterface() { 
        public int getInt() {
            return 39;
        }
    };
    private static IntegerInterface ii40 = new IntegerInterface() { 
        public int getInt() {
            return 40;
        }
    };
    private static IntegerInterface ii41 = new IntegerInterface() { 
        public int getInt() {
            return 41;
        }
    };
    private static IntegerInterface ii42 = new IntegerInterface() { 
        public int getInt() {
            return 42;
        }
    };
    private static IntegerInterface ii43 = new IntegerInterface() { 
        public int getInt() {
            return 43;
        }
    };
    private static IntegerInterface ii44 = new IntegerInterface() { 
        public int getInt() {
            return 44;
        }
    };
    private static IntegerInterface ii45 = new IntegerInterface() { 
        public int getInt() {
            return 45;
        }
    };
    private static IntegerInterface ii46 = new IntegerInterface() { 
        public int getInt() {
            return 46;
        }
    };
    private static IntegerInterface ii47 = new IntegerInterface() { 
        public int getInt() {
            return 47;
        }
    };
    private static IntegerInterface ii48 = new IntegerInterface() { 
        public int getInt() {
            return 48;
        }
    };
    private static IntegerInterface ii49 = new IntegerInterface() { 
        public int getInt() {
            return 49;
        }
    };
    private static IntegerInterface ii50 = new IntegerInterface() { 
        public int getInt() {
            return 50;
        }
    };
    private static IntegerInterface ii51 = new IntegerInterface() { 
        public int getInt() {
            return 51;
        }
    };
    private static IntegerInterface ii52 = new IntegerInterface() { 
        public int getInt() {
            return 52;
        }
    };
    private static IntegerInterface ii53 = new IntegerInterface() { 
        public int getInt() {
            return 53;
        }
    };
    private static IntegerInterface ii54 = new IntegerInterface() { 
        public int getInt() {
            return 54;
        }
    };
    private static IntegerInterface ii55 = new IntegerInterface() { 
        public int getInt() {
            return 55;
        }
    };
    private static IntegerInterface ii56 = new IntegerInterface() { 
        public int getInt() {
            return 56;
        }
    };
    private static IntegerInterface ii57 = new IntegerInterface() { 
        public int getInt() {
            return 57;
        }
    };
    private static IntegerInterface ii58 = new IntegerInterface() { 
        public int getInt() {
            return 58;
        }
    };
    private static IntegerInterface ii59 = new IntegerInterface() { 
        public int getInt() {
            return 59;
        }
    };
    private static IntegerInterface ii60 = new IntegerInterface() { 
        public int getInt() {
            return 60;
        }
    };
    private static IntegerInterface ii61 = new IntegerInterface() { 
        public int getInt() {
            return 61;
        }
    };
    private static IntegerInterface ii62 = new IntegerInterface() { 
        public int getInt() {
            return 62;
        }
    };
    private static IntegerInterface ii63 = new IntegerInterface() { 
        public int getInt() {
            return 63;
        }
    };
    private static IntegerInterface ii64 = new IntegerInterface() { 
        public int getInt() {
            return 64;
        }
    };
    private static IntegerInterface ii65 = new IntegerInterface() { 
        public int getInt() {
            return 65;
        }
    };
    private static IntegerInterface ii66 = new IntegerInterface() { 
        public int getInt() {
            return 66;
        }
    };
    private static IntegerInterface ii67 = new IntegerInterface() { 
        public int getInt() {
            return 67;
        }
    };
    private static IntegerInterface ii68 = new IntegerInterface() { 
        public int getInt() {
            return 68;
        }
    };
    private static IntegerInterface ii69 = new IntegerInterface() { 
        public int getInt() {
            return 69;
        }
    };
    private static IntegerInterface ii70 = new IntegerInterface() { 
        public int getInt() {
            return 70;
        }
    };
    private static IntegerInterface ii71 = new IntegerInterface() { 
        public int getInt() {
            return 71;
        }
    };
    private static IntegerInterface ii72 = new IntegerInterface() { 
        public int getInt() {
            return 72;
        }
    };
    private static IntegerInterface ii73 = new IntegerInterface() { 
        public int getInt() {
            return 73;
        }
    };
    private static IntegerInterface ii74 = new IntegerInterface() { 
        public int getInt() {
            return 74;
        }
    };
    private static IntegerInterface ii75 = new IntegerInterface() { 
        public int getInt() {
            return 75;
        }
    };
    private static IntegerInterface ii76 = new IntegerInterface() { 
        public int getInt() {
            return 76;
        }
    };
    private static IntegerInterface ii77 = new IntegerInterface() { 
        public int getInt() {
            return 77;
        }
    };
    private static IntegerInterface ii78 = new IntegerInterface() { 
        public int getInt() {
            return 78;
        }
    };
    private static IntegerInterface ii79 = new IntegerInterface() { 
        public int getInt() {
            return 79;
        }
    };
    private static IntegerInterface ii80 = new IntegerInterface() { 
        public int getInt() {
            return 80;
        }
    };
    private static IntegerInterface ii81 = new IntegerInterface() { 
        public int getInt() {
            return 81;
        }
    };
    private static IntegerInterface ii82 = new IntegerInterface() { 
        public int getInt() {
            return 82;
        }
    };
    private static IntegerInterface ii83 = new IntegerInterface() { 
        public int getInt() {
            return 83;
        }
    };
    private static IntegerInterface ii84 = new IntegerInterface() { 
        public int getInt() {
            return 84;
        }
    };
    private static IntegerInterface ii85 = new IntegerInterface() { 
        public int getInt() {
            return 85;
        }
    };
    private static IntegerInterface ii86 = new IntegerInterface() { 
        public int getInt() {
            return 86;
        }
    };
    private static IntegerInterface ii87 = new IntegerInterface() { 
        public int getInt() {
            return 87;
        }
    };
    private static IntegerInterface ii88 = new IntegerInterface() { 
        public int getInt() {
            return 88;
        }
    };
    private static IntegerInterface ii89 = new IntegerInterface() { 
        public int getInt() {
            return 89;
        }
    };
    private static IntegerInterface ii90 = new IntegerInterface() { 
        public int getInt() {
            return 90;
        }
    };
    private static IntegerInterface ii91 = new IntegerInterface() { 
        public int getInt() {
            return 91;
        }
    };
    private static IntegerInterface ii92 = new IntegerInterface() { 
        public int getInt() {
            return 92;
        }
    };
    private static IntegerInterface ii93 = new IntegerInterface() { 
        public int getInt() {
            return 93;
        }
    };
    private static IntegerInterface ii94 = new IntegerInterface() { 
        public int getInt() {
            return 94;
        }
    };
    private static IntegerInterface ii95 = new IntegerInterface() { 
        public int getInt() {
            return 95;
        }
    };
    private static IntegerInterface ii96 = new IntegerInterface() { 
        public int getInt() {
            return 96;
        }
    };
    private static IntegerInterface ii97 = new IntegerInterface() { 
        public int getInt() {
            return 97;
        }
    };
    private static IntegerInterface ii98 = new IntegerInterface() { 
        public int getInt() {
            return 98;
        }
    };
    private static IntegerInterface ii99 = new IntegerInterface() { 
        public int getInt() {
            return 99;
        }
    };
    private static IntegerInterface ii100 = new IntegerInterface() { 
        public int getInt() {
            return 100;
        }
    };
    private static IntegerInterface ii101 = new IntegerInterface() { 
        public int getInt() {
            return 101;
        }
    };
    private static IntegerInterface ii102 = new IntegerInterface() { 
        public int getInt() {
            return 102;
        }
    };
    private static IntegerInterface ii103 = new IntegerInterface() { 
        public int getInt() {
            return 103;
        }
    };
    private static IntegerInterface ii104 = new IntegerInterface() { 
        public int getInt() {
            return 104;
        }
    };
    private static IntegerInterface ii105 = new IntegerInterface() { 
        public int getInt() {
            return 105;
        }
    };
    private static IntegerInterface ii106 = new IntegerInterface() { 
        public int getInt() {
            return 106;
        }
    };
    private static IntegerInterface ii107 = new IntegerInterface() { 
        public int getInt() {
            return 107;
        }
    };
    private static IntegerInterface ii108 = new IntegerInterface() { 
        public int getInt() {
            return 108;
        }
    };
    private static IntegerInterface ii109 = new IntegerInterface() { 
        public int getInt() {
            return 109;
        }
    };
    private static IntegerInterface ii110 = new IntegerInterface() { 
        public int getInt() {
            return 110;
        }
    };
    private static IntegerInterface ii111 = new IntegerInterface() { 
        public int getInt() {
            return 111;
        }
    };
    private static IntegerInterface ii112 = new IntegerInterface() { 
        public int getInt() {
            return 112;
        }
    };
    private static IntegerInterface ii113 = new IntegerInterface() { 
        public int getInt() {
            return 113;
        }
    };
    private static IntegerInterface ii114 = new IntegerInterface() { 
        public int getInt() {
            return 114;
        }
    };
    private static IntegerInterface ii115 = new IntegerInterface() { 
        public int getInt() {
            return 115;
        }
    };
    private static IntegerInterface ii116 = new IntegerInterface() { 
        public int getInt() {
            return 116;
        }
    };
    private static IntegerInterface ii117 = new IntegerInterface() { 
        public int getInt() {
            return 117;
        }
    };
    private static IntegerInterface ii118 = new IntegerInterface() { 
        public int getInt() {
            return 118;
        }
    };
    private static IntegerInterface ii119 = new IntegerInterface() { 
        public int getInt() {
            return 119;
        }
    };
    private static IntegerInterface ii120 = new IntegerInterface() { 
        public int getInt() {
            return 120;
        }
    };
    private static IntegerInterface ii121 = new IntegerInterface() { 
        public int getInt() {
            return 121;
        }
    };
    private static IntegerInterface ii122 = new IntegerInterface() { 
        public int getInt() {
            return 122;
        }
    };
    private static IntegerInterface ii123 = new IntegerInterface() { 
        public int getInt() {
            return 123;
        }
    };
    private static IntegerInterface ii124 = new IntegerInterface() { 
        public int getInt() {
            return 124;
        }
    };
    private static IntegerInterface ii125 = new IntegerInterface() { 
        public int getInt() {
            return 125;
        }
    };
    private static IntegerInterface ii126 = new IntegerInterface() { 
        public int getInt() {
            return 126;
        }
    };
    private static IntegerInterface ii127 = new IntegerInterface() { 
        public int getInt() {
            return 127;
        }
    };
    private static IntegerInterface ii128 = new IntegerInterface() { 
        public int getInt() {
            return 128;
        }
    };
    private static IntegerInterface ii129 = new IntegerInterface() { 
        public int getInt() {
            return 129;
        }
    };
    private static IntegerInterface ii130 = new IntegerInterface() { 
        public int getInt() {
            return 130;
        }
    };
    private static IntegerInterface ii131 = new IntegerInterface() { 
        public int getInt() {
            return 131;
        }
    };
    private static IntegerInterface ii132 = new IntegerInterface() { 
        public int getInt() {
            return 132;
        }
    };
    private static IntegerInterface ii133 = new IntegerInterface() { 
        public int getInt() {
            return 133;
        }
    };
    private static IntegerInterface ii134 = new IntegerInterface() { 
        public int getInt() {
            return 134;
        }
    };
    private static IntegerInterface ii135 = new IntegerInterface() { 
        public int getInt() {
            return 135;
        }
    };
    private static IntegerInterface ii136 = new IntegerInterface() { 
        public int getInt() {
            return 136;
        }
    };
    private static IntegerInterface ii137 = new IntegerInterface() { 
        public int getInt() {
            return 137;
        }
    };
    private static IntegerInterface ii138 = new IntegerInterface() { 
        public int getInt() {
            return 138;
        }
    };
    private static IntegerInterface ii139 = new IntegerInterface() { 
        public int getInt() {
            return 139;
        }
    };
    private static IntegerInterface ii140 = new IntegerInterface() { 
        public int getInt() {
            return 140;
        }
    };
    private static IntegerInterface ii141 = new IntegerInterface() { 
        public int getInt() {
            return 141;
        }
    };
    private static IntegerInterface ii142 = new IntegerInterface() { 
        public int getInt() {
            return 142;
        }
    };
    private static IntegerInterface ii143 = new IntegerInterface() { 
        public int getInt() {
            return 143;
        }
    };
    private static IntegerInterface ii144 = new IntegerInterface() { 
        public int getInt() {
            return 144;
        }
    };
    private static IntegerInterface ii145 = new IntegerInterface() { 
        public int getInt() {
            return 145;
        }
    };
    private static IntegerInterface ii146 = new IntegerInterface() { 
        public int getInt() {
            return 146;
        }
    };
    private static IntegerInterface ii147 = new IntegerInterface() { 
        public int getInt() {
            return 147;
        }
    };
    private static IntegerInterface ii148 = new IntegerInterface() { 
        public int getInt() {
            return 148;
        }
    };
    private static IntegerInterface ii149 = new IntegerInterface() { 
        public int getInt() {
            return 149;
        }
    };
    private static IntegerInterface ii150 = new IntegerInterface() { 
        public int getInt() {
            return 150;
        }
    };
    private static IntegerInterface ii151 = new IntegerInterface() { 
        public int getInt() {
            return 151;
        }
    };
    private static IntegerInterface ii152 = new IntegerInterface() { 
        public int getInt() {
            return 152;
        }
    };
    private static IntegerInterface ii153 = new IntegerInterface() { 
        public int getInt() {
            return 153;
        }
    };
    private static IntegerInterface ii154 = new IntegerInterface() { 
        public int getInt() {
            return 154;
        }
    };
    private static IntegerInterface ii155 = new IntegerInterface() { 
        public int getInt() {
            return 155;
        }
    };
    private static IntegerInterface ii156 = new IntegerInterface() { 
        public int getInt() {
            return 156;
        }
    };
    private static IntegerInterface ii157 = new IntegerInterface() { 
        public int getInt() {
            return 157;
        }
    };
    private static IntegerInterface ii158 = new IntegerInterface() { 
        public int getInt() {
            return 158;
        }
    };
    private static IntegerInterface ii159 = new IntegerInterface() { 
        public int getInt() {
            return 159;
        }
    };
    private static IntegerInterface ii160 = new IntegerInterface() { 
        public int getInt() {
            return 160;
        }
    };
    private static IntegerInterface ii161 = new IntegerInterface() { 
        public int getInt() {
            return 161;
        }
    };
    private static IntegerInterface ii162 = new IntegerInterface() { 
        public int getInt() {
            return 162;
        }
    };
    private static IntegerInterface ii163 = new IntegerInterface() { 
        public int getInt() {
            return 163;
        }
    };
    private static IntegerInterface ii164 = new IntegerInterface() { 
        public int getInt() {
            return 164;
        }
    };
    private static IntegerInterface ii165 = new IntegerInterface() { 
        public int getInt() {
            return 165;
        }
    };
    private static IntegerInterface ii166 = new IntegerInterface() { 
        public int getInt() {
            return 166;
        }
    };
    private static IntegerInterface ii167 = new IntegerInterface() { 
        public int getInt() {
            return 167;
        }
    };
    private static IntegerInterface ii168 = new IntegerInterface() { 
        public int getInt() {
            return 168;
        }
    };
    private static IntegerInterface ii169 = new IntegerInterface() { 
        public int getInt() {
            return 169;
        }
    };
    private static IntegerInterface ii170 = new IntegerInterface() { 
        public int getInt() {
            return 170;
        }
    };
    private static IntegerInterface ii171 = new IntegerInterface() { 
        public int getInt() {
            return 171;
        }
    };
    private static IntegerInterface ii172 = new IntegerInterface() { 
        public int getInt() {
            return 172;
        }
    };
    private static IntegerInterface ii173 = new IntegerInterface() { 
        public int getInt() {
            return 173;
        }
    };
    private static IntegerInterface ii174 = new IntegerInterface() { 
        public int getInt() {
            return 174;
        }
    };
    private static IntegerInterface ii175 = new IntegerInterface() { 
        public int getInt() {
            return 175;
        }
    };
    private static IntegerInterface ii176 = new IntegerInterface() { 
        public int getInt() {
            return 176;
        }
    };
    private static IntegerInterface ii177 = new IntegerInterface() { 
        public int getInt() {
            return 177;
        }
    };
    private static IntegerInterface ii178 = new IntegerInterface() { 
        public int getInt() {
            return 178;
        }
    };
    private static IntegerInterface ii179 = new IntegerInterface() { 
        public int getInt() {
            return 179;
        }
    };
    private static IntegerInterface ii180 = new IntegerInterface() { 
        public int getInt() {
            return 180;
        }
    };
    private static IntegerInterface ii181 = new IntegerInterface() { 
        public int getInt() {
            return 181;
        }
    };
    private static IntegerInterface ii182 = new IntegerInterface() { 
        public int getInt() {
            return 182;
        }
    };
    private static IntegerInterface ii183 = new IntegerInterface() { 
        public int getInt() {
            return 183;
        }
    };
    private static IntegerInterface ii184 = new IntegerInterface() { 
        public int getInt() {
            return 184;
        }
    };
    private static IntegerInterface ii185 = new IntegerInterface() { 
        public int getInt() {
            return 185;
        }
    };
    private static IntegerInterface ii186 = new IntegerInterface() { 
        public int getInt() {
            return 186;
        }
    };
    private static IntegerInterface ii187 = new IntegerInterface() { 
        public int getInt() {
            return 187;
        }
    };
    private static IntegerInterface ii188 = new IntegerInterface() { 
        public int getInt() {
            return 188;
        }
    };
    private static IntegerInterface ii189 = new IntegerInterface() { 
        public int getInt() {
            return 189;
        }
    };
    private static IntegerInterface ii190 = new IntegerInterface() { 
        public int getInt() {
            return 190;
        }
    };
    private static IntegerInterface ii191 = new IntegerInterface() { 
        public int getInt() {
            return 191;
        }
    };
    private static IntegerInterface ii192 = new IntegerInterface() { 
        public int getInt() {
            return 192;
        }
    };
    private static IntegerInterface ii193 = new IntegerInterface() { 
        public int getInt() {
            return 193;
        }
    };
    private static IntegerInterface ii194 = new IntegerInterface() { 
        public int getInt() {
            return 194;
        }
    };
    private static IntegerInterface ii195 = new IntegerInterface() { 
        public int getInt() {
            return 195;
        }
    };
    private static IntegerInterface ii196 = new IntegerInterface() { 
        public int getInt() {
            return 196;
        }
    };
    private static IntegerInterface ii197 = new IntegerInterface() { 
        public int getInt() {
            return 197;
        }
    };
    private static IntegerInterface ii198 = new IntegerInterface() { 
        public int getInt() {
            return 198;
        }
    };
    private static IntegerInterface ii199 = new IntegerInterface() { 
        public int getInt() {
            return 199;
        }
    };
    private static IntegerInterface ii200 = new IntegerInterface() { 
        public int getInt() {
            return 200;
        }
    };
    private static IntegerInterface ii201 = new IntegerInterface() { 
        public int getInt() {
            return 201;
        }
    };
    private static IntegerInterface ii202 = new IntegerInterface() { 
        public int getInt() {
            return 202;
        }
    };
    private static IntegerInterface ii203 = new IntegerInterface() { 
        public int getInt() {
            return 203;
        }
    };
    private static IntegerInterface ii204 = new IntegerInterface() { 
        public int getInt() {
            return 204;
        }
    };
    private static IntegerInterface ii205 = new IntegerInterface() { 
        public int getInt() {
            return 205;
        }
    };
    private static IntegerInterface ii206 = new IntegerInterface() { 
        public int getInt() {
            return 206;
        }
    };
    private static IntegerInterface ii207 = new IntegerInterface() { 
        public int getInt() {
            return 207;
        }
    };
    private static IntegerInterface ii208 = new IntegerInterface() { 
        public int getInt() {
            return 208;
        }
    };
    private static IntegerInterface ii209 = new IntegerInterface() { 
        public int getInt() {
            return 209;
        }
    };
    private static IntegerInterface ii210 = new IntegerInterface() { 
        public int getInt() {
            return 210;
        }
    };
    private static IntegerInterface ii211 = new IntegerInterface() { 
        public int getInt() {
            return 211;
        }
    };
    private static IntegerInterface ii212 = new IntegerInterface() { 
        public int getInt() {
            return 212;
        }
    };
    private static IntegerInterface ii213 = new IntegerInterface() { 
        public int getInt() {
            return 213;
        }
    };
    private static IntegerInterface ii214 = new IntegerInterface() { 
        public int getInt() {
            return 214;
        }
    };
    private static IntegerInterface ii215 = new IntegerInterface() { 
        public int getInt() {
            return 215;
        }
    };
    private static IntegerInterface ii216 = new IntegerInterface() { 
        public int getInt() {
            return 216;
        }
    };
    private static IntegerInterface ii217 = new IntegerInterface() { 
        public int getInt() {
            return 217;
        }
    };
    private static IntegerInterface ii218 = new IntegerInterface() { 
        public int getInt() {
            return 218;
        }
    };
    private static IntegerInterface ii219 = new IntegerInterface() { 
        public int getInt() {
            return 219;
        }
    };
    private static IntegerInterface ii220 = new IntegerInterface() { 
        public int getInt() {
            return 220;
        }
    };
    private static IntegerInterface ii221 = new IntegerInterface() { 
        public int getInt() {
            return 221;
        }
    };
    private static IntegerInterface ii222 = new IntegerInterface() { 
        public int getInt() {
            return 222;
        }
    };
    private static IntegerInterface ii223 = new IntegerInterface() { 
        public int getInt() {
            return 223;
        }
    };
    private static IntegerInterface ii224 = new IntegerInterface() { 
        public int getInt() {
            return 224;
        }
    };
    private static IntegerInterface ii225 = new IntegerInterface() { 
        public int getInt() {
            return 225;
        }
    };
    private static IntegerInterface ii226 = new IntegerInterface() { 
        public int getInt() {
            return 226;
        }
    };
    private static IntegerInterface ii227 = new IntegerInterface() { 
        public int getInt() {
            return 227;
        }
    };
    private static IntegerInterface ii228 = new IntegerInterface() { 
        public int getInt() {
            return 228;
        }
    };
    private static IntegerInterface ii229 = new IntegerInterface() { 
        public int getInt() {
            return 229;
        }
    };
    private static IntegerInterface ii230 = new IntegerInterface() { 
        public int getInt() {
            return 230;
        }
    };
    private static IntegerInterface ii231 = new IntegerInterface() { 
        public int getInt() {
            return 231;
        }
    };
    private static IntegerInterface ii232 = new IntegerInterface() { 
        public int getInt() {
            return 232;
        }
    };
    private static IntegerInterface ii233 = new IntegerInterface() { 
        public int getInt() {
            return 233;
        }
    };
    private static IntegerInterface ii234 = new IntegerInterface() { 
        public int getInt() {
            return 234;
        }
    };
    private static IntegerInterface ii235 = new IntegerInterface() { 
        public int getInt() {
            return 235;
        }
    };
    private static IntegerInterface ii236 = new IntegerInterface() { 
        public int getInt() {
            return 236;
        }
    };
    private static IntegerInterface ii237 = new IntegerInterface() { 
        public int getInt() {
            return 237;
        }
    };
    private static IntegerInterface ii238 = new IntegerInterface() { 
        public int getInt() {
            return 238;
        }
    };
    private static IntegerInterface ii239 = new IntegerInterface() { 
        public int getInt() {
            return 239;
        }
    };
    private static IntegerInterface ii240 = new IntegerInterface() { 
        public int getInt() {
            return 240;
        }
    };
    private static IntegerInterface ii241 = new IntegerInterface() { 
        public int getInt() {
            return 241;
        }
    };
    private static IntegerInterface ii242 = new IntegerInterface() { 
        public int getInt() {
            return 242;
        }
    };
    private static IntegerInterface ii243 = new IntegerInterface() { 
        public int getInt() {
            return 243;
        }
    };
    private static IntegerInterface ii244 = new IntegerInterface() { 
        public int getInt() {
            return 244;
        }
    };
    private static IntegerInterface ii245 = new IntegerInterface() { 
        public int getInt() {
            return 245;
        }
    };
    private static IntegerInterface ii246 = new IntegerInterface() { 
        public int getInt() {
            return 246;
        }
    };
    private static IntegerInterface ii247 = new IntegerInterface() { 
        public int getInt() {
            return 247;
        }
    };
    private static IntegerInterface ii248 = new IntegerInterface() { 
        public int getInt() {
            return 248;
        }
    };
    private static IntegerInterface ii249 = new IntegerInterface() { 
        public int getInt() {
            return 249;
        }
    };
    private static IntegerInterface ii250 = new IntegerInterface() { 
        public int getInt() {
            return 250;
        }
    };
    private static IntegerInterface ii251 = new IntegerInterface() { 
        public int getInt() {
            return 251;
        }
    };
    private static IntegerInterface ii252 = new IntegerInterface() { 
        public int getInt() {
            return 252;
        }
    };
    private static IntegerInterface ii253 = new IntegerInterface() { 
        public int getInt() {
            return 253;
        }
    };
    private static IntegerInterface ii254 = new IntegerInterface() { 
        public int getInt() {
            return 254;
        }
    };
    private static IntegerInterface ii255 = new IntegerInterface() { 
        public int getInt() {
            return 255;
        }
    };
    private static IntegerInterface ii256 = new IntegerInterface() { 
        public int getInt() {
            return 256;
        }
    };
    private static IntegerInterface ii257 = new IntegerInterface() { 
        public int getInt() {
            return 257;
        }
    };
    private static IntegerInterface ii258 = new IntegerInterface() { 
        public int getInt() {
            return 258;
        }
    };
    private static IntegerInterface ii259 = new IntegerInterface() { 
        public int getInt() {
            return 259;
        }
    };
    private static IntegerInterface ii260 = new IntegerInterface() { 
        public int getInt() {
            return 260;
        }
    };
    private static IntegerInterface ii261 = new IntegerInterface() { 
        public int getInt() {
            return 261;
        }
    };
    private static IntegerInterface ii262 = new IntegerInterface() { 
        public int getInt() {
            return 262;
        }
    };
    private static IntegerInterface ii263 = new IntegerInterface() { 
        public int getInt() {
            return 263;
        }
    };
    private static IntegerInterface ii264 = new IntegerInterface() { 
        public int getInt() {
            return 264;
        }
    };
    private static IntegerInterface ii265 = new IntegerInterface() { 
        public int getInt() {
            return 265;
        }
    };
    private static IntegerInterface ii266 = new IntegerInterface() { 
        public int getInt() {
            return 266;
        }
    };
    private static IntegerInterface ii267 = new IntegerInterface() { 
        public int getInt() {
            return 267;
        }
    };
    private static IntegerInterface ii268 = new IntegerInterface() { 
        public int getInt() {
            return 268;
        }
    };
    private static IntegerInterface ii269 = new IntegerInterface() { 
        public int getInt() {
            return 269;
        }
    };
    private static IntegerInterface ii270 = new IntegerInterface() { 
        public int getInt() {
            return 270;
        }
    };
    private static IntegerInterface ii271 = new IntegerInterface() { 
        public int getInt() {
            return 271;
        }
    };
    private static IntegerInterface ii272 = new IntegerInterface() { 
        public int getInt() {
            return 272;
        }
    };
    private static IntegerInterface ii273 = new IntegerInterface() { 
        public int getInt() {
            return 273;
        }
    };
    private static IntegerInterface ii274 = new IntegerInterface() { 
        public int getInt() {
            return 274;
        }
    };
    private static IntegerInterface ii275 = new IntegerInterface() { 
        public int getInt() {
            return 275;
        }
    };
    private static IntegerInterface ii276 = new IntegerInterface() { 
        public int getInt() {
            return 276;
        }
    };
    private static IntegerInterface ii277 = new IntegerInterface() { 
        public int getInt() {
            return 277;
        }
    };
    private static IntegerInterface ii278 = new IntegerInterface() { 
        public int getInt() {
            return 278;
        }
    };
    private static IntegerInterface ii279 = new IntegerInterface() { 
        public int getInt() {
            return 279;
        }
    };
    private static IntegerInterface ii280 = new IntegerInterface() { 
        public int getInt() {
            return 280;
        }
    };
    private static IntegerInterface ii281 = new IntegerInterface() { 
        public int getInt() {
            return 281;
        }
    };
    private static IntegerInterface ii282 = new IntegerInterface() { 
        public int getInt() {
            return 282;
        }
    };
    private static IntegerInterface ii283 = new IntegerInterface() { 
        public int getInt() {
            return 283;
        }
    };
    private static IntegerInterface ii284 = new IntegerInterface() { 
        public int getInt() {
            return 284;
        }
    };
    private static IntegerInterface ii285 = new IntegerInterface() { 
        public int getInt() {
            return 285;
        }
    };
    private static IntegerInterface ii286 = new IntegerInterface() { 
        public int getInt() {
            return 286;
        }
    };
    private static IntegerInterface ii287 = new IntegerInterface() { 
        public int getInt() {
            return 287;
        }
    };
    private static IntegerInterface ii288 = new IntegerInterface() { 
        public int getInt() {
            return 288;
        }
    };
    private static IntegerInterface ii289 = new IntegerInterface() { 
        public int getInt() {
            return 289;
        }
    };
    private static IntegerInterface ii290 = new IntegerInterface() { 
        public int getInt() {
            return 290;
        }
    };
    private static IntegerInterface ii291 = new IntegerInterface() { 
        public int getInt() {
            return 291;
        }
    };
    private static IntegerInterface ii292 = new IntegerInterface() { 
        public int getInt() {
            return 292;
        }
    };
    private static IntegerInterface ii293 = new IntegerInterface() { 
        public int getInt() {
            return 293;
        }
    };
    private static IntegerInterface ii294 = new IntegerInterface() { 
        public int getInt() {
            return 294;
        }
    };
    private static IntegerInterface ii295 = new IntegerInterface() { 
        public int getInt() {
            return 295;
        }
    };
    private static IntegerInterface ii296 = new IntegerInterface() { 
        public int getInt() {
            return 296;
        }
    };
    private static IntegerInterface ii297 = new IntegerInterface() { 
        public int getInt() {
            return 297;
        }
    };
    private static IntegerInterface ii298 = new IntegerInterface() { 
        public int getInt() {
            return 298;
        }
    };
    private static IntegerInterface ii299 = new IntegerInterface() { 
        public int getInt() {
            return 299;
        }
    };
    private static IntegerInterface ii300 = new IntegerInterface() { 
        public int getInt() {
            return 300;
        }
    };
    private static IntegerInterface ii301 = new IntegerInterface() { 
        public int getInt() {
            return 301;
        }
    };
    private static IntegerInterface ii302 = new IntegerInterface() { 
        public int getInt() {
            return 302;
        }
    };
    private static IntegerInterface ii303 = new IntegerInterface() { 
        public int getInt() {
            return 303;
        }
    };
    private static IntegerInterface ii304 = new IntegerInterface() { 
        public int getInt() {
            return 304;
        }
    };
    private static IntegerInterface ii305 = new IntegerInterface() { 
        public int getInt() {
            return 305;
        }
    };
    private static IntegerInterface ii306 = new IntegerInterface() { 
        public int getInt() {
            return 306;
        }
    };
    private static IntegerInterface ii307 = new IntegerInterface() { 
        public int getInt() {
            return 307;
        }
    };
    private static IntegerInterface ii308 = new IntegerInterface() { 
        public int getInt() {
            return 308;
        }
    };
    private static IntegerInterface ii309 = new IntegerInterface() { 
        public int getInt() {
            return 309;
        }
    };
    private static IntegerInterface ii310 = new IntegerInterface() { 
        public int getInt() {
            return 310;
        }
    };
    private static IntegerInterface ii311 = new IntegerInterface() { 
        public int getInt() {
            return 311;
        }
    };
    private static IntegerInterface ii312 = new IntegerInterface() { 
        public int getInt() {
            return 312;
        }
    };
    private static IntegerInterface ii313 = new IntegerInterface() { 
        public int getInt() {
            return 313;
        }
    };
    private static IntegerInterface ii314 = new IntegerInterface() { 
        public int getInt() {
            return 314;
        }
    };
    private static IntegerInterface ii315 = new IntegerInterface() { 
        public int getInt() {
            return 315;
        }
    };
    private static IntegerInterface ii316 = new IntegerInterface() { 
        public int getInt() {
            return 316;
        }
    };
    private static IntegerInterface ii317 = new IntegerInterface() { 
        public int getInt() {
            return 317;
        }
    };
    private static IntegerInterface ii318 = new IntegerInterface() { 
        public int getInt() {
            return 318;
        }
    };
    private static IntegerInterface ii319 = new IntegerInterface() { 
        public int getInt() {
            return 319;
        }
    };
    private static IntegerInterface ii320 = new IntegerInterface() { 
        public int getInt() {
            return 320;
        }
    };
    private static IntegerInterface ii321 = new IntegerInterface() { 
        public int getInt() {
            return 321;
        }
    };
    private static IntegerInterface ii322 = new IntegerInterface() { 
        public int getInt() {
            return 322;
        }
    };
    private static IntegerInterface ii323 = new IntegerInterface() { 
        public int getInt() {
            return 323;
        }
    };
    private static IntegerInterface ii324 = new IntegerInterface() { 
        public int getInt() {
            return 324;
        }
    };
    private static IntegerInterface ii325 = new IntegerInterface() { 
        public int getInt() {
            return 325;
        }
    };
    private static IntegerInterface ii326 = new IntegerInterface() { 
        public int getInt() {
            return 326;
        }
    };
    private static IntegerInterface ii327 = new IntegerInterface() { 
        public int getInt() {
            return 327;
        }
    };
    private static IntegerInterface ii328 = new IntegerInterface() { 
        public int getInt() {
            return 328;
        }
    };
    private static IntegerInterface ii329 = new IntegerInterface() { 
        public int getInt() {
            return 329;
        }
    };
    private static IntegerInterface ii330 = new IntegerInterface() { 
        public int getInt() {
            return 330;
        }
    };
    private static IntegerInterface ii331 = new IntegerInterface() { 
        public int getInt() {
            return 331;
        }
    };
    private static IntegerInterface ii332 = new IntegerInterface() { 
        public int getInt() {
            return 332;
        }
    };
    private static IntegerInterface ii333 = new IntegerInterface() { 
        public int getInt() {
            return 333;
        }
    };
    private static IntegerInterface ii334 = new IntegerInterface() { 
        public int getInt() {
            return 334;
        }
    };
    private static IntegerInterface ii335 = new IntegerInterface() { 
        public int getInt() {
            return 335;
        }
    };
    private static IntegerInterface ii336 = new IntegerInterface() { 
        public int getInt() {
            return 336;
        }
    };
    private static IntegerInterface ii337 = new IntegerInterface() { 
        public int getInt() {
            return 337;
        }
    };
    private static IntegerInterface ii338 = new IntegerInterface() { 
        public int getInt() {
            return 338;
        }
    };
    private static IntegerInterface ii339 = new IntegerInterface() { 
        public int getInt() {
            return 339;
        }
    };
    private static IntegerInterface ii340 = new IntegerInterface() { 
        public int getInt() {
            return 340;
        }
    };
    private static IntegerInterface ii341 = new IntegerInterface() { 
        public int getInt() {
            return 341;
        }
    };
    private static IntegerInterface ii342 = new IntegerInterface() { 
        public int getInt() {
            return 342;
        }
    };
    private static IntegerInterface ii343 = new IntegerInterface() { 
        public int getInt() {
            return 343;
        }
    };
    private static IntegerInterface ii344 = new IntegerInterface() { 
        public int getInt() {
            return 344;
        }
    };
    private static IntegerInterface ii345 = new IntegerInterface() { 
        public int getInt() {
            return 345;
        }
    };
    private static IntegerInterface ii346 = new IntegerInterface() { 
        public int getInt() {
            return 346;
        }
    };
    private static IntegerInterface ii347 = new IntegerInterface() { 
        public int getInt() {
            return 347;
        }
    };
    private static IntegerInterface ii348 = new IntegerInterface() { 
        public int getInt() {
            return 348;
        }
    };
    private static IntegerInterface ii349 = new IntegerInterface() { 
        public int getInt() {
            return 349;
        }
    };
    private static IntegerInterface ii350 = new IntegerInterface() { 
        public int getInt() {
            return 350;
        }
    };
    private static IntegerInterface ii351 = new IntegerInterface() { 
        public int getInt() {
            return 351;
        }
    };
    private static IntegerInterface ii352 = new IntegerInterface() { 
        public int getInt() {
            return 352;
        }
    };
    private static IntegerInterface ii353 = new IntegerInterface() { 
        public int getInt() {
            return 353;
        }
    };
    private static IntegerInterface ii354 = new IntegerInterface() { 
        public int getInt() {
            return 354;
        }
    };
    private static IntegerInterface ii355 = new IntegerInterface() { 
        public int getInt() {
            return 355;
        }
    };
    private static IntegerInterface ii356 = new IntegerInterface() { 
        public int getInt() {
            return 356;
        }
    };
    private static IntegerInterface ii357 = new IntegerInterface() { 
        public int getInt() {
            return 357;
        }
    };
    private static IntegerInterface ii358 = new IntegerInterface() { 
        public int getInt() {
            return 358;
        }
    };
    private static IntegerInterface ii359 = new IntegerInterface() { 
        public int getInt() {
            return 359;
        }
    };
    private static IntegerInterface ii360 = new IntegerInterface() { 
        public int getInt() {
            return 360;
        }
    };
    private static IntegerInterface ii361 = new IntegerInterface() { 
        public int getInt() {
            return 361;
        }
    };
    private static IntegerInterface ii362 = new IntegerInterface() { 
        public int getInt() {
            return 362;
        }
    };
    private static IntegerInterface ii363 = new IntegerInterface() { 
        public int getInt() {
            return 363;
        }
    };
    private static IntegerInterface ii364 = new IntegerInterface() { 
        public int getInt() {
            return 364;
        }
    };
    private static IntegerInterface ii365 = new IntegerInterface() { 
        public int getInt() {
            return 365;
        }
    };
    private static IntegerInterface ii366 = new IntegerInterface() { 
        public int getInt() {
            return 366;
        }
    };
    private static IntegerInterface ii367 = new IntegerInterface() { 
        public int getInt() {
            return 367;
        }
    };
    private static IntegerInterface ii368 = new IntegerInterface() { 
        public int getInt() {
            return 368;
        }
    };
    private static IntegerInterface ii369 = new IntegerInterface() { 
        public int getInt() {
            return 369;
        }
    };
    private static IntegerInterface ii370 = new IntegerInterface() { 
        public int getInt() {
            return 370;
        }
    };
    private static IntegerInterface ii371 = new IntegerInterface() { 
        public int getInt() {
            return 371;
        }
    };
    private static IntegerInterface ii372 = new IntegerInterface() { 
        public int getInt() {
            return 372;
        }
    };
    private static IntegerInterface ii373 = new IntegerInterface() { 
        public int getInt() {
            return 373;
        }
    };
    private static IntegerInterface ii374 = new IntegerInterface() { 
        public int getInt() {
            return 374;
        }
    };
    private static IntegerInterface ii375 = new IntegerInterface() { 
        public int getInt() {
            return 375;
        }
    };
    private static IntegerInterface ii376 = new IntegerInterface() { 
        public int getInt() {
            return 376;
        }
    };
    private static IntegerInterface ii377 = new IntegerInterface() { 
        public int getInt() {
            return 377;
        }
    };
    private static IntegerInterface ii378 = new IntegerInterface() { 
        public int getInt() {
            return 378;
        }
    };
    private static IntegerInterface ii379 = new IntegerInterface() { 
        public int getInt() {
            return 379;
        }
    };
    private static IntegerInterface ii380 = new IntegerInterface() { 
        public int getInt() {
            return 380;
        }
    };
    private static IntegerInterface ii381 = new IntegerInterface() { 
        public int getInt() {
            return 381;
        }
    };
    private static IntegerInterface ii382 = new IntegerInterface() { 
        public int getInt() {
            return 382;
        }
    };
    private static IntegerInterface ii383 = new IntegerInterface() { 
        public int getInt() {
            return 383;
        }
    };
    private static IntegerInterface ii384 = new IntegerInterface() { 
        public int getInt() {
            return 384;
        }
    };
    private static IntegerInterface ii385 = new IntegerInterface() { 
        public int getInt() {
            return 385;
        }
    };
    private static IntegerInterface ii386 = new IntegerInterface() { 
        public int getInt() {
            return 386;
        }
    };
    private static IntegerInterface ii387 = new IntegerInterface() { 
        public int getInt() {
            return 387;
        }
    };
    private static IntegerInterface ii388 = new IntegerInterface() { 
        public int getInt() {
            return 388;
        }
    };
    private static IntegerInterface ii389 = new IntegerInterface() { 
        public int getInt() {
            return 389;
        }
    };
    private static IntegerInterface ii390 = new IntegerInterface() { 
        public int getInt() {
            return 390;
        }
    };
    private static IntegerInterface ii391 = new IntegerInterface() { 
        public int getInt() {
            return 391;
        }
    };
    private static IntegerInterface ii392 = new IntegerInterface() { 
        public int getInt() {
            return 392;
        }
    };
    private static IntegerInterface ii393 = new IntegerInterface() { 
        public int getInt() {
            return 393;
        }
    };
    private static IntegerInterface ii394 = new IntegerInterface() { 
        public int getInt() {
            return 394;
        }
    };
    private static IntegerInterface ii395 = new IntegerInterface() { 
        public int getInt() {
            return 395;
        }
    };
    private static IntegerInterface ii396 = new IntegerInterface() { 
        public int getInt() {
            return 396;
        }
    };
    private static IntegerInterface ii397 = new IntegerInterface() { 
        public int getInt() {
            return 397;
        }
    };
    private static IntegerInterface ii398 = new IntegerInterface() { 
        public int getInt() {
            return 398;
        }
    };
    private static IntegerInterface ii399 = new IntegerInterface() { 
        public int getInt() {
            return 399;
        }
    };
    private static IntegerInterface ii400 = new IntegerInterface() { 
        public int getInt() {
            return 400;
        }
    };
    private static IntegerInterface ii401 = new IntegerInterface() { 
        public int getInt() {
            return 401;
        }
    };
    private static IntegerInterface ii402 = new IntegerInterface() { 
        public int getInt() {
            return 402;
        }
    };
    private static IntegerInterface ii403 = new IntegerInterface() { 
        public int getInt() {
            return 403;
        }
    };
    private static IntegerInterface ii404 = new IntegerInterface() { 
        public int getInt() {
            return 404;
        }
    };
    private static IntegerInterface ii405 = new IntegerInterface() { 
        public int getInt() {
            return 405;
        }
    };
    private static IntegerInterface ii406 = new IntegerInterface() { 
        public int getInt() {
            return 406;
        }
    };
    private static IntegerInterface ii407 = new IntegerInterface() { 
        public int getInt() {
            return 407;
        }
    };
    private static IntegerInterface ii408 = new IntegerInterface() { 
        public int getInt() {
            return 408;
        }
    };
    private static IntegerInterface ii409 = new IntegerInterface() { 
        public int getInt() {
            return 409;
        }
    };
    private static IntegerInterface ii410 = new IntegerInterface() { 
        public int getInt() {
            return 410;
        }
    };
    private static IntegerInterface ii411 = new IntegerInterface() { 
        public int getInt() {
            return 411;
        }
    };
    private static IntegerInterface ii412 = new IntegerInterface() { 
        public int getInt() {
            return 412;
        }
    };
    private static IntegerInterface ii413 = new IntegerInterface() { 
        public int getInt() {
            return 413;
        }
    };
    private static IntegerInterface ii414 = new IntegerInterface() { 
        public int getInt() {
            return 414;
        }
    };
    private static IntegerInterface ii415 = new IntegerInterface() { 
        public int getInt() {
            return 415;
        }
    };
    private static IntegerInterface ii416 = new IntegerInterface() { 
        public int getInt() {
            return 416;
        }
    };
    private static IntegerInterface ii417 = new IntegerInterface() { 
        public int getInt() {
            return 417;
        }
    };
    private static IntegerInterface ii418 = new IntegerInterface() { 
        public int getInt() {
            return 418;
        }
    };
    private static IntegerInterface ii419 = new IntegerInterface() { 
        public int getInt() {
            return 419;
        }
    };
    private static IntegerInterface ii420 = new IntegerInterface() { 
        public int getInt() {
            return 420;
        }
    };
    private static IntegerInterface ii421 = new IntegerInterface() { 
        public int getInt() {
            return 421;
        }
    };
    private static IntegerInterface ii422 = new IntegerInterface() { 
        public int getInt() {
            return 422;
        }
    };
    private static IntegerInterface ii423 = new IntegerInterface() { 
        public int getInt() {
            return 423;
        }
    };
    private static IntegerInterface ii424 = new IntegerInterface() { 
        public int getInt() {
            return 424;
        }
    };
    private static IntegerInterface ii425 = new IntegerInterface() { 
        public int getInt() {
            return 425;
        }
    };
    private static IntegerInterface ii426 = new IntegerInterface() { 
        public int getInt() {
            return 426;
        }
    };
    private static IntegerInterface ii427 = new IntegerInterface() { 
        public int getInt() {
            return 427;
        }
    };
    private static IntegerInterface ii428 = new IntegerInterface() { 
        public int getInt() {
            return 428;
        }
    };
    private static IntegerInterface ii429 = new IntegerInterface() { 
        public int getInt() {
            return 429;
        }
    };
    private static IntegerInterface ii430 = new IntegerInterface() { 
        public int getInt() {
            return 430;
        }
    };
    private static IntegerInterface ii431 = new IntegerInterface() { 
        public int getInt() {
            return 431;
        }
    };
    private static IntegerInterface ii432 = new IntegerInterface() { 
        public int getInt() {
            return 432;
        }
    };
    private static IntegerInterface ii433 = new IntegerInterface() { 
        public int getInt() {
            return 433;
        }
    };
    private static IntegerInterface ii434 = new IntegerInterface() { 
        public int getInt() {
            return 434;
        }
    };
    private static IntegerInterface ii435 = new IntegerInterface() { 
        public int getInt() {
            return 435;
        }
    };
    private static IntegerInterface ii436 = new IntegerInterface() { 
        public int getInt() {
            return 436;
        }
    };
    private static IntegerInterface ii437 = new IntegerInterface() { 
        public int getInt() {
            return 437;
        }
    };
    private static IntegerInterface ii438 = new IntegerInterface() { 
        public int getInt() {
            return 438;
        }
    };
    private static IntegerInterface ii439 = new IntegerInterface() { 
        public int getInt() {
            return 439;
        }
    };
    private static IntegerInterface ii440 = new IntegerInterface() { 
        public int getInt() {
            return 440;
        }
    };
    private static IntegerInterface ii441 = new IntegerInterface() { 
        public int getInt() {
            return 441;
        }
    };
    private static IntegerInterface ii442 = new IntegerInterface() { 
        public int getInt() {
            return 442;
        }
    };
    private static IntegerInterface ii443 = new IntegerInterface() { 
        public int getInt() {
            return 443;
        }
    };
    private static IntegerInterface ii444 = new IntegerInterface() { 
        public int getInt() {
            return 444;
        }
    };
    private static IntegerInterface ii445 = new IntegerInterface() { 
        public int getInt() {
            return 445;
        }
    };
    private static IntegerInterface ii446 = new IntegerInterface() { 
        public int getInt() {
            return 446;
        }
    };
    private static IntegerInterface ii447 = new IntegerInterface() { 
        public int getInt() {
            return 447;
        }
    };
    private static IntegerInterface ii448 = new IntegerInterface() { 
        public int getInt() {
            return 448;
        }
    };
    private static IntegerInterface ii449 = new IntegerInterface() { 
        public int getInt() {
            return 449;
        }
    };
    private static IntegerInterface ii450 = new IntegerInterface() { 
        public int getInt() {
            return 450;
        }
    };
    private static IntegerInterface ii451 = new IntegerInterface() { 
        public int getInt() {
            return 451;
        }
    };
    private static IntegerInterface ii452 = new IntegerInterface() { 
        public int getInt() {
            return 452;
        }
    };
    private static IntegerInterface ii453 = new IntegerInterface() { 
        public int getInt() {
            return 453;
        }
    };
    private static IntegerInterface ii454 = new IntegerInterface() { 
        public int getInt() {
            return 454;
        }
    };
    private static IntegerInterface ii455 = new IntegerInterface() { 
        public int getInt() {
            return 455;
        }
    };
    private static IntegerInterface ii456 = new IntegerInterface() { 
        public int getInt() {
            return 456;
        }
    };
    private static IntegerInterface ii457 = new IntegerInterface() { 
        public int getInt() {
            return 457;
        }
    };
    private static IntegerInterface ii458 = new IntegerInterface() { 
        public int getInt() {
            return 458;
        }
    };
    private static IntegerInterface ii459 = new IntegerInterface() { 
        public int getInt() {
            return 459;
        }
    };
    private static IntegerInterface ii460 = new IntegerInterface() { 
        public int getInt() {
            return 460;
        }
    };
    private static IntegerInterface ii461 = new IntegerInterface() { 
        public int getInt() {
            return 461;
        }
    };
    private static IntegerInterface ii462 = new IntegerInterface() { 
        public int getInt() {
            return 462;
        }
    };
    private static IntegerInterface ii463 = new IntegerInterface() { 
        public int getInt() {
            return 463;
        }
    };
    private static IntegerInterface ii464 = new IntegerInterface() { 
        public int getInt() {
            return 464;
        }
    };
    private static IntegerInterface ii465 = new IntegerInterface() { 
        public int getInt() {
            return 465;
        }
    };
    private static IntegerInterface ii466 = new IntegerInterface() { 
        public int getInt() {
            return 466;
        }
    };
    private static IntegerInterface ii467 = new IntegerInterface() { 
        public int getInt() {
            return 467;
        }
    };
    private static IntegerInterface ii468 = new IntegerInterface() { 
        public int getInt() {
            return 468;
        }
    };
    private static IntegerInterface ii469 = new IntegerInterface() { 
        public int getInt() {
            return 469;
        }
    };
    private static IntegerInterface ii470 = new IntegerInterface() { 
        public int getInt() {
            return 470;
        }
    };
    private static IntegerInterface ii471 = new IntegerInterface() { 
        public int getInt() {
            return 471;
        }
    };
    private static IntegerInterface ii472 = new IntegerInterface() { 
        public int getInt() {
            return 472;
        }
    };
    private static IntegerInterface ii473 = new IntegerInterface() { 
        public int getInt() {
            return 473;
        }
    };
    private static IntegerInterface ii474 = new IntegerInterface() { 
        public int getInt() {
            return 474;
        }
    };
    private static IntegerInterface ii475 = new IntegerInterface() { 
        public int getInt() {
            return 475;
        }
    };
    private static IntegerInterface ii476 = new IntegerInterface() { 
        public int getInt() {
            return 476;
        }
    };
    private static IntegerInterface ii477 = new IntegerInterface() { 
        public int getInt() {
            return 477;
        }
    };
    private static IntegerInterface ii478 = new IntegerInterface() { 
        public int getInt() {
            return 478;
        }
    };
    private static IntegerInterface ii479 = new IntegerInterface() { 
        public int getInt() {
            return 479;
        }
    };
    private static IntegerInterface ii480 = new IntegerInterface() { 
        public int getInt() {
            return 480;
        }
    };
    private static IntegerInterface ii481 = new IntegerInterface() { 
        public int getInt() {
            return 481;
        }
    };
    private static IntegerInterface ii482 = new IntegerInterface() { 
        public int getInt() {
            return 482;
        }
    };
    private static IntegerInterface ii483 = new IntegerInterface() { 
        public int getInt() {
            return 483;
        }
    };
    private static IntegerInterface ii484 = new IntegerInterface() { 
        public int getInt() {
            return 484;
        }
    };
    private static IntegerInterface ii485 = new IntegerInterface() { 
        public int getInt() {
            return 485;
        }
    };
    private static IntegerInterface ii486 = new IntegerInterface() { 
        public int getInt() {
            return 486;
        }
    };
    private static IntegerInterface ii487 = new IntegerInterface() { 
        public int getInt() {
            return 487;
        }
    };
    private static IntegerInterface ii488 = new IntegerInterface() { 
        public int getInt() {
            return 488;
        }
    };
    private static IntegerInterface ii489 = new IntegerInterface() { 
        public int getInt() {
            return 489;
        }
    };
    private static IntegerInterface ii490 = new IntegerInterface() { 
        public int getInt() {
            return 490;
        }
    };
    private static IntegerInterface ii491 = new IntegerInterface() { 
        public int getInt() {
            return 491;
        }
    };
    private static IntegerInterface ii492 = new IntegerInterface() { 
        public int getInt() {
            return 492;
        }
    };
    private static IntegerInterface ii493 = new IntegerInterface() { 
        public int getInt() {
            return 493;
        }
    };
    private static IntegerInterface ii494 = new IntegerInterface() { 
        public int getInt() {
            return 494;
        }
    };
    private static IntegerInterface ii495 = new IntegerInterface() { 
        public int getInt() {
            return 495;
        }
    };
    private static IntegerInterface ii496 = new IntegerInterface() { 
        public int getInt() {
            return 496;
        }
    };
    private static IntegerInterface ii497 = new IntegerInterface() { 
        public int getInt() {
            return 497;
        }
    };
    private static IntegerInterface ii498 = new IntegerInterface() { 
        public int getInt() {
            return 498;
        }
    };
    private static IntegerInterface ii499 = new IntegerInterface() { 
        public int getInt() {
            return 499;
        }
    };
    private static IntegerInterface ii500 = new IntegerInterface() { 
        public int getInt() {
            return 500;
        }
    };
    private static IntegerInterface ii501 = new IntegerInterface() { 
        public int getInt() {
            return 501;
        }
    };
    private static IntegerInterface ii502 = new IntegerInterface() { 
        public int getInt() {
            return 502;
        }
    };
    private static IntegerInterface ii503 = new IntegerInterface() { 
        public int getInt() {
            return 503;
        }
    };
    private static IntegerInterface ii504 = new IntegerInterface() { 
        public int getInt() {
            return 504;
        }
    };
    private static IntegerInterface ii505 = new IntegerInterface() { 
        public int getInt() {
            return 505;
        }
    };
    private static IntegerInterface ii506 = new IntegerInterface() { 
        public int getInt() {
            return 506;
        }
    };
    private static IntegerInterface ii507 = new IntegerInterface() { 
        public int getInt() {
            return 507;
        }
    };
    private static IntegerInterface ii508 = new IntegerInterface() { 
        public int getInt() {
            return 508;
        }
    };
    private static IntegerInterface ii509 = new IntegerInterface() { 
        public int getInt() {
            return 509;
        }
    };
    private static IntegerInterface ii510 = new IntegerInterface() { 
        public int getInt() {
            return 510;
        }
    };
    private static IntegerInterface ii511 = new IntegerInterface() { 
        public int getInt() {
            return 511;
        }
    };
    private static IntegerInterface ii512 = new IntegerInterface() { 
        public int getInt() {
            return 512;
        }
    };
    private static IntegerInterface ii513 = new IntegerInterface() { 
        public int getInt() {
            return 513;
        }
    };
    private static IntegerInterface ii514 = new IntegerInterface() { 
        public int getInt() {
            return 514;
        }
    };
    private static IntegerInterface ii515 = new IntegerInterface() { 
        public int getInt() {
            return 515;
        }
    };
    private static IntegerInterface ii516 = new IntegerInterface() { 
        public int getInt() {
            return 516;
        }
    };
    private static IntegerInterface ii517 = new IntegerInterface() { 
        public int getInt() {
            return 517;
        }
    };
    private static IntegerInterface ii518 = new IntegerInterface() { 
        public int getInt() {
            return 518;
        }
    };
    private static IntegerInterface ii519 = new IntegerInterface() { 
        public int getInt() {
            return 519;
        }
    };
    private static IntegerInterface ii520 = new IntegerInterface() { 
        public int getInt() {
            return 520;
        }
    };
    private static IntegerInterface ii521 = new IntegerInterface() { 
        public int getInt() {
            return 521;
        }
    };
    private static IntegerInterface ii522 = new IntegerInterface() { 
        public int getInt() {
            return 522;
        }
    };
    private static IntegerInterface ii523 = new IntegerInterface() { 
        public int getInt() {
            return 523;
        }
    };
    private static IntegerInterface ii524 = new IntegerInterface() { 
        public int getInt() {
            return 524;
        }
    };
    private static IntegerInterface ii525 = new IntegerInterface() { 
        public int getInt() {
            return 525;
        }
    };
    private static IntegerInterface ii526 = new IntegerInterface() { 
        public int getInt() {
            return 526;
        }
    };
    private static IntegerInterface ii527 = new IntegerInterface() { 
        public int getInt() {
            return 527;
        }
    };
    private static IntegerInterface ii528 = new IntegerInterface() { 
        public int getInt() {
            return 528;
        }
    };
    private static IntegerInterface ii529 = new IntegerInterface() { 
        public int getInt() {
            return 529;
        }
    };
    private static IntegerInterface ii530 = new IntegerInterface() { 
        public int getInt() {
            return 530;
        }
    };
    private static IntegerInterface ii531 = new IntegerInterface() { 
        public int getInt() {
            return 531;
        }
    };
    private static IntegerInterface ii532 = new IntegerInterface() { 
        public int getInt() {
            return 532;
        }
    };
    private static IntegerInterface ii533 = new IntegerInterface() { 
        public int getInt() {
            return 533;
        }
    };
    private static IntegerInterface ii534 = new IntegerInterface() { 
        public int getInt() {
            return 534;
        }
    };
    private static IntegerInterface ii535 = new IntegerInterface() { 
        public int getInt() {
            return 535;
        }
    };
    private static IntegerInterface ii536 = new IntegerInterface() { 
        public int getInt() {
            return 536;
        }
    };
    private static IntegerInterface ii537 = new IntegerInterface() { 
        public int getInt() {
            return 537;
        }
    };
    private static IntegerInterface ii538 = new IntegerInterface() { 
        public int getInt() {
            return 538;
        }
    };
    private static IntegerInterface ii539 = new IntegerInterface() { 
        public int getInt() {
            return 539;
        }
    };
    private static IntegerInterface ii540 = new IntegerInterface() { 
        public int getInt() {
            return 540;
        }
    };
    private static IntegerInterface ii541 = new IntegerInterface() { 
        public int getInt() {
            return 541;
        }
    };
    private static IntegerInterface ii542 = new IntegerInterface() { 
        public int getInt() {
            return 542;
        }
    };
    private static IntegerInterface ii543 = new IntegerInterface() { 
        public int getInt() {
            return 543;
        }
    };
    private static IntegerInterface ii544 = new IntegerInterface() { 
        public int getInt() {
            return 544;
        }
    };
    private static IntegerInterface ii545 = new IntegerInterface() { 
        public int getInt() {
            return 545;
        }
    };
    private static IntegerInterface ii546 = new IntegerInterface() { 
        public int getInt() {
            return 546;
        }
    };
    private static IntegerInterface ii547 = new IntegerInterface() { 
        public int getInt() {
            return 547;
        }
    };
    private static IntegerInterface ii548 = new IntegerInterface() { 
        public int getInt() {
            return 548;
        }
    };
    private static IntegerInterface ii549 = new IntegerInterface() { 
        public int getInt() {
            return 549;
        }
    };
    private static IntegerInterface ii550 = new IntegerInterface() { 
        public int getInt() {
            return 550;
        }
    };
    private static IntegerInterface ii551 = new IntegerInterface() { 
        public int getInt() {
            return 551;
        }
    };
    private static IntegerInterface ii552 = new IntegerInterface() { 
        public int getInt() {
            return 552;
        }
    };
    private static IntegerInterface ii553 = new IntegerInterface() { 
        public int getInt() {
            return 553;
        }
    };
    private static IntegerInterface ii554 = new IntegerInterface() { 
        public int getInt() {
            return 554;
        }
    };
    private static IntegerInterface ii555 = new IntegerInterface() { 
        public int getInt() {
            return 555;
        }
    };
    private static IntegerInterface ii556 = new IntegerInterface() { 
        public int getInt() {
            return 556;
        }
    };
    private static IntegerInterface ii557 = new IntegerInterface() { 
        public int getInt() {
            return 557;
        }
    };
    private static IntegerInterface ii558 = new IntegerInterface() { 
        public int getInt() {
            return 558;
        }
    };
    private static IntegerInterface ii559 = new IntegerInterface() { 
        public int getInt() {
            return 559;
        }
    };
    private static IntegerInterface ii560 = new IntegerInterface() { 
        public int getInt() {
            return 560;
        }
    };
    private static IntegerInterface ii561 = new IntegerInterface() { 
        public int getInt() {
            return 561;
        }
    };
    private static IntegerInterface ii562 = new IntegerInterface() { 
        public int getInt() {
            return 562;
        }
    };
    private static IntegerInterface ii563 = new IntegerInterface() { 
        public int getInt() {
            return 563;
        }
    };
    private static IntegerInterface ii564 = new IntegerInterface() { 
        public int getInt() {
            return 564;
        }
    };
    private static IntegerInterface ii565 = new IntegerInterface() { 
        public int getInt() {
            return 565;
        }
    };
    private static IntegerInterface ii566 = new IntegerInterface() { 
        public int getInt() {
            return 566;
        }
    };
    private static IntegerInterface ii567 = new IntegerInterface() { 
        public int getInt() {
            return 567;
        }
    };
    private static IntegerInterface ii568 = new IntegerInterface() { 
        public int getInt() {
            return 568;
        }
    };
    private static IntegerInterface ii569 = new IntegerInterface() { 
        public int getInt() {
            return 569;
        }
    };
    private static IntegerInterface ii570 = new IntegerInterface() { 
        public int getInt() {
            return 570;
        }
    };
    private static IntegerInterface ii571 = new IntegerInterface() { 
        public int getInt() {
            return 571;
        }
    };
    private static IntegerInterface ii572 = new IntegerInterface() { 
        public int getInt() {
            return 572;
        }
    };
    private static IntegerInterface ii573 = new IntegerInterface() { 
        public int getInt() {
            return 573;
        }
    };
    private static IntegerInterface ii574 = new IntegerInterface() { 
        public int getInt() {
            return 574;
        }
    };
    private static IntegerInterface ii575 = new IntegerInterface() { 
        public int getInt() {
            return 575;
        }
    };
    private static IntegerInterface ii576 = new IntegerInterface() { 
        public int getInt() {
            return 576;
        }
    };
    private static IntegerInterface ii577 = new IntegerInterface() { 
        public int getInt() {
            return 577;
        }
    };
    private static IntegerInterface ii578 = new IntegerInterface() { 
        public int getInt() {
            return 578;
        }
    };
    private static IntegerInterface ii579 = new IntegerInterface() { 
        public int getInt() {
            return 579;
        }
    };
    private static IntegerInterface ii580 = new IntegerInterface() { 
        public int getInt() {
            return 580;
        }
    };
    private static IntegerInterface ii581 = new IntegerInterface() { 
        public int getInt() {
            return 581;
        }
    };
    private static IntegerInterface ii582 = new IntegerInterface() { 
        public int getInt() {
            return 582;
        }
    };
    private static IntegerInterface ii583 = new IntegerInterface() { 
        public int getInt() {
            return 583;
        }
    };
    private static IntegerInterface ii584 = new IntegerInterface() { 
        public int getInt() {
            return 584;
        }
    };
    private static IntegerInterface ii585 = new IntegerInterface() { 
        public int getInt() {
            return 585;
        }
    };
    private static IntegerInterface ii586 = new IntegerInterface() { 
        public int getInt() {
            return 586;
        }
    };
    private static IntegerInterface ii587 = new IntegerInterface() { 
        public int getInt() {
            return 587;
        }
    };
    private static IntegerInterface ii588 = new IntegerInterface() { 
        public int getInt() {
            return 588;
        }
    };
    private static IntegerInterface ii589 = new IntegerInterface() { 
        public int getInt() {
            return 589;
        }
    };
    private static IntegerInterface ii590 = new IntegerInterface() { 
        public int getInt() {
            return 590;
        }
    };
    private static IntegerInterface ii591 = new IntegerInterface() { 
        public int getInt() {
            return 591;
        }
    };
    private static IntegerInterface ii592 = new IntegerInterface() { 
        public int getInt() {
            return 592;
        }
    };
    private static IntegerInterface ii593 = new IntegerInterface() { 
        public int getInt() {
            return 593;
        }
    };
    private static IntegerInterface ii594 = new IntegerInterface() { 
        public int getInt() {
            return 594;
        }
    };
    private static IntegerInterface ii595 = new IntegerInterface() { 
        public int getInt() {
            return 595;
        }
    };
    private static IntegerInterface ii596 = new IntegerInterface() { 
        public int getInt() {
            return 596;
        }
    };
    private static IntegerInterface ii597 = new IntegerInterface() { 
        public int getInt() {
            return 597;
        }
    };
    private static IntegerInterface ii598 = new IntegerInterface() { 
        public int getInt() {
            return 598;
        }
    };
    private static IntegerInterface ii599 = new IntegerInterface() { 
        public int getInt() {
            return 599;
        }
    };
    private static IntegerInterface ii600 = new IntegerInterface() { 
        public int getInt() {
            return 600;
        }
    };
    private static IntegerInterface ii601 = new IntegerInterface() { 
        public int getInt() {
            return 601;
        }
    };
    private static IntegerInterface ii602 = new IntegerInterface() { 
        public int getInt() {
            return 602;
        }
    };
    private static IntegerInterface ii603 = new IntegerInterface() { 
        public int getInt() {
            return 603;
        }
    };
    private static IntegerInterface ii604 = new IntegerInterface() { 
        public int getInt() {
            return 604;
        }
    };
    private static IntegerInterface ii605 = new IntegerInterface() { 
        public int getInt() {
            return 605;
        }
    };
    private static IntegerInterface ii606 = new IntegerInterface() { 
        public int getInt() {
            return 606;
        }
    };
    private static IntegerInterface ii607 = new IntegerInterface() { 
        public int getInt() {
            return 607;
        }
    };
    private static IntegerInterface ii608 = new IntegerInterface() { 
        public int getInt() {
            return 608;
        }
    };
    private static IntegerInterface ii609 = new IntegerInterface() { 
        public int getInt() {
            return 609;
        }
    };
    private static IntegerInterface ii610 = new IntegerInterface() { 
        public int getInt() {
            return 610;
        }
    };
    private static IntegerInterface ii611 = new IntegerInterface() { 
        public int getInt() {
            return 611;
        }
    };
    private static IntegerInterface ii612 = new IntegerInterface() { 
        public int getInt() {
            return 612;
        }
    };
    private static IntegerInterface ii613 = new IntegerInterface() { 
        public int getInt() {
            return 613;
        }
    };
    private static IntegerInterface ii614 = new IntegerInterface() { 
        public int getInt() {
            return 614;
        }
    };
    private static IntegerInterface ii615 = new IntegerInterface() { 
        public int getInt() {
            return 615;
        }
    };
    private static IntegerInterface ii616 = new IntegerInterface() { 
        public int getInt() {
            return 616;
        }
    };
    private static IntegerInterface ii617 = new IntegerInterface() { 
        public int getInt() {
            return 617;
        }
    };
    private static IntegerInterface ii618 = new IntegerInterface() { 
        public int getInt() {
            return 618;
        }
    };
    private static IntegerInterface ii619 = new IntegerInterface() { 
        public int getInt() {
            return 619;
        }
    };
    private static IntegerInterface ii620 = new IntegerInterface() { 
        public int getInt() {
            return 620;
        }
    };
    private static IntegerInterface ii621 = new IntegerInterface() { 
        public int getInt() {
            return 621;
        }
    };
    private static IntegerInterface ii622 = new IntegerInterface() { 
        public int getInt() {
            return 622;
        }
    };
    private static IntegerInterface ii623 = new IntegerInterface() { 
        public int getInt() {
            return 623;
        }
    };
    private static IntegerInterface ii624 = new IntegerInterface() { 
        public int getInt() {
            return 624;
        }
    };
    private static IntegerInterface ii625 = new IntegerInterface() { 
        public int getInt() {
            return 625;
        }
    };
    private static IntegerInterface ii626 = new IntegerInterface() { 
        public int getInt() {
            return 626;
        }
    };
    private static IntegerInterface ii627 = new IntegerInterface() { 
        public int getInt() {
            return 627;
        }
    };
    private static IntegerInterface ii628 = new IntegerInterface() { 
        public int getInt() {
            return 628;
        }
    };
    private static IntegerInterface ii629 = new IntegerInterface() { 
        public int getInt() {
            return 629;
        }
    };
    private static IntegerInterface ii630 = new IntegerInterface() { 
        public int getInt() {
            return 630;
        }
    };
    private static IntegerInterface ii631 = new IntegerInterface() { 
        public int getInt() {
            return 631;
        }
    };
    private static IntegerInterface ii632 = new IntegerInterface() { 
        public int getInt() {
            return 632;
        }
    };
    private static IntegerInterface ii633 = new IntegerInterface() { 
        public int getInt() {
            return 633;
        }
    };
    private static IntegerInterface ii634 = new IntegerInterface() { 
        public int getInt() {
            return 634;
        }
    };
    private static IntegerInterface ii635 = new IntegerInterface() { 
        public int getInt() {
            return 635;
        }
    };
    private static IntegerInterface ii636 = new IntegerInterface() { 
        public int getInt() {
            return 636;
        }
    };
    private static IntegerInterface ii637 = new IntegerInterface() { 
        public int getInt() {
            return 637;
        }
    };
    private static IntegerInterface ii638 = new IntegerInterface() { 
        public int getInt() {
            return 638;
        }
    };
    private static IntegerInterface ii639 = new IntegerInterface() { 
        public int getInt() {
            return 639;
        }
    };
    private static IntegerInterface ii640 = new IntegerInterface() { 
        public int getInt() {
            return 640;
        }
    };
    private static IntegerInterface ii641 = new IntegerInterface() { 
        public int getInt() {
            return 641;
        }
    };
    private static IntegerInterface ii642 = new IntegerInterface() { 
        public int getInt() {
            return 642;
        }
    };
    private static IntegerInterface ii643 = new IntegerInterface() { 
        public int getInt() {
            return 643;
        }
    };
    private static IntegerInterface ii644 = new IntegerInterface() { 
        public int getInt() {
            return 644;
        }
    };
    private static IntegerInterface ii645 = new IntegerInterface() { 
        public int getInt() {
            return 645;
        }
    };
    private static IntegerInterface ii646 = new IntegerInterface() { 
        public int getInt() {
            return 646;
        }
    };
    private static IntegerInterface ii647 = new IntegerInterface() { 
        public int getInt() {
            return 647;
        }
    };
    private static IntegerInterface ii648 = new IntegerInterface() { 
        public int getInt() {
            return 648;
        }
    };
    private static IntegerInterface ii649 = new IntegerInterface() { 
        public int getInt() {
            return 649;
        }
    };
    private static IntegerInterface ii650 = new IntegerInterface() { 
        public int getInt() {
            return 650;
        }
    };
    private static IntegerInterface ii651 = new IntegerInterface() { 
        public int getInt() {
            return 651;
        }
    };
    private static IntegerInterface ii652 = new IntegerInterface() { 
        public int getInt() {
            return 652;
        }
    };
    private static IntegerInterface ii653 = new IntegerInterface() { 
        public int getInt() {
            return 653;
        }
    };
    private static IntegerInterface ii654 = new IntegerInterface() { 
        public int getInt() {
            return 654;
        }
    };
    private static IntegerInterface ii655 = new IntegerInterface() { 
        public int getInt() {
            return 655;
        }
    };
    private static IntegerInterface ii656 = new IntegerInterface() { 
        public int getInt() {
            return 656;
        }
    };
    private static IntegerInterface ii657 = new IntegerInterface() { 
        public int getInt() {
            return 657;
        }
    };
    private static IntegerInterface ii658 = new IntegerInterface() { 
        public int getInt() {
            return 658;
        }
    };
    private static IntegerInterface ii659 = new IntegerInterface() { 
        public int getInt() {
            return 659;
        }
    };
    private static IntegerInterface ii660 = new IntegerInterface() { 
        public int getInt() {
            return 660;
        }
    };
    private static IntegerInterface ii661 = new IntegerInterface() { 
        public int getInt() {
            return 661;
        }
    };
    private static IntegerInterface ii662 = new IntegerInterface() { 
        public int getInt() {
            return 662;
        }
    };
    private static IntegerInterface ii663 = new IntegerInterface() { 
        public int getInt() {
            return 663;
        }
    };
    private static IntegerInterface ii664 = new IntegerInterface() { 
        public int getInt() {
            return 664;
        }
    };
    private static IntegerInterface ii665 = new IntegerInterface() { 
        public int getInt() {
            return 665;
        }
    };
    private static IntegerInterface ii666 = new IntegerInterface() { 
        public int getInt() {
            return 666;
        }
    };
    private static IntegerInterface ii667 = new IntegerInterface() { 
        public int getInt() {
            return 667;
        }
    };
    private static IntegerInterface ii668 = new IntegerInterface() { 
        public int getInt() {
            return 668;
        }
    };
    private static IntegerInterface ii669 = new IntegerInterface() { 
        public int getInt() {
            return 669;
        }
    };
    private static IntegerInterface ii670 = new IntegerInterface() { 
        public int getInt() {
            return 670;
        }
    };
    private static IntegerInterface ii671 = new IntegerInterface() { 
        public int getInt() {
            return 671;
        }
    };
    private static IntegerInterface ii672 = new IntegerInterface() { 
        public int getInt() {
            return 672;
        }
    };
    private static IntegerInterface ii673 = new IntegerInterface() { 
        public int getInt() {
            return 673;
        }
    };
    private static IntegerInterface ii674 = new IntegerInterface() { 
        public int getInt() {
            return 674;
        }
    };
    private static IntegerInterface ii675 = new IntegerInterface() { 
        public int getInt() {
            return 675;
        }
    };
    private static IntegerInterface ii676 = new IntegerInterface() { 
        public int getInt() {
            return 676;
        }
    };
    private static IntegerInterface ii677 = new IntegerInterface() { 
        public int getInt() {
            return 677;
        }
    };
    private static IntegerInterface ii678 = new IntegerInterface() { 
        public int getInt() {
            return 678;
        }
    };
    private static IntegerInterface ii679 = new IntegerInterface() { 
        public int getInt() {
            return 679;
        }
    };
    private static IntegerInterface ii680 = new IntegerInterface() { 
        public int getInt() {
            return 680;
        }
    };
    private static IntegerInterface ii681 = new IntegerInterface() { 
        public int getInt() {
            return 681;
        }
    };
    private static IntegerInterface ii682 = new IntegerInterface() { 
        public int getInt() {
            return 682;
        }
    };
    private static IntegerInterface ii683 = new IntegerInterface() { 
        public int getInt() {
            return 683;
        }
    };
    private static IntegerInterface ii684 = new IntegerInterface() { 
        public int getInt() {
            return 684;
        }
    };
    private static IntegerInterface ii685 = new IntegerInterface() { 
        public int getInt() {
            return 685;
        }
    };
    private static IntegerInterface ii686 = new IntegerInterface() { 
        public int getInt() {
            return 686;
        }
    };
    private static IntegerInterface ii687 = new IntegerInterface() { 
        public int getInt() {
            return 687;
        }
    };
    private static IntegerInterface ii688 = new IntegerInterface() { 
        public int getInt() {
            return 688;
        }
    };
    private static IntegerInterface ii689 = new IntegerInterface() { 
        public int getInt() {
            return 689;
        }
    };
    private static IntegerInterface ii690 = new IntegerInterface() { 
        public int getInt() {
            return 690;
        }
    };
    private static IntegerInterface ii691 = new IntegerInterface() { 
        public int getInt() {
            return 691;
        }
    };
    private static IntegerInterface ii692 = new IntegerInterface() { 
        public int getInt() {
            return 692;
        }
    };
    private static IntegerInterface ii693 = new IntegerInterface() { 
        public int getInt() {
            return 693;
        }
    };
    private static IntegerInterface ii694 = new IntegerInterface() { 
        public int getInt() {
            return 694;
        }
    };
    private static IntegerInterface ii695 = new IntegerInterface() { 
        public int getInt() {
            return 695;
        }
    };
    private static IntegerInterface ii696 = new IntegerInterface() { 
        public int getInt() {
            return 696;
        }
    };
    private static IntegerInterface ii697 = new IntegerInterface() { 
        public int getInt() {
            return 697;
        }
    };
    private static IntegerInterface ii698 = new IntegerInterface() { 
        public int getInt() {
            return 698;
        }
    };
    private static IntegerInterface ii699 = new IntegerInterface() { 
        public int getInt() {
            return 699;
        }
    };
    private static IntegerInterface ii700 = new IntegerInterface() { 
        public int getInt() {
            return 700;
        }
    };
    private static IntegerInterface ii701 = new IntegerInterface() { 
        public int getInt() {
            return 701;
        }
    };
    private static IntegerInterface ii702 = new IntegerInterface() { 
        public int getInt() {
            return 702;
        }
    };
    private static IntegerInterface ii703 = new IntegerInterface() { 
        public int getInt() {
            return 703;
        }
    };
    private static IntegerInterface ii704 = new IntegerInterface() { 
        public int getInt() {
            return 704;
        }
    };
    private static IntegerInterface ii705 = new IntegerInterface() { 
        public int getInt() {
            return 705;
        }
    };
    private static IntegerInterface ii706 = new IntegerInterface() { 
        public int getInt() {
            return 706;
        }
    };
    private static IntegerInterface ii707 = new IntegerInterface() { 
        public int getInt() {
            return 707;
        }
    };
    private static IntegerInterface ii708 = new IntegerInterface() { 
        public int getInt() {
            return 708;
        }
    };
    private static IntegerInterface ii709 = new IntegerInterface() { 
        public int getInt() {
            return 709;
        }
    };
    private static IntegerInterface ii710 = new IntegerInterface() { 
        public int getInt() {
            return 710;
        }
    };
    private static IntegerInterface ii711 = new IntegerInterface() { 
        public int getInt() {
            return 711;
        }
    };
    private static IntegerInterface ii712 = new IntegerInterface() { 
        public int getInt() {
            return 712;
        }
    };
    private static IntegerInterface ii713 = new IntegerInterface() { 
        public int getInt() {
            return 713;
        }
    };
    private static IntegerInterface ii714 = new IntegerInterface() { 
        public int getInt() {
            return 714;
        }
    };
    private static IntegerInterface ii715 = new IntegerInterface() { 
        public int getInt() {
            return 715;
        }
    };
    private static IntegerInterface ii716 = new IntegerInterface() { 
        public int getInt() {
            return 716;
        }
    };
    private static IntegerInterface ii717 = new IntegerInterface() { 
        public int getInt() {
            return 717;
        }
    };
    private static IntegerInterface ii718 = new IntegerInterface() { 
        public int getInt() {
            return 718;
        }
    };
    private static IntegerInterface ii719 = new IntegerInterface() { 
        public int getInt() {
            return 719;
        }
    };
    private static IntegerInterface ii720 = new IntegerInterface() { 
        public int getInt() {
            return 720;
        }
    };
    private static IntegerInterface ii721 = new IntegerInterface() { 
        public int getInt() {
            return 721;
        }
    };
    private static IntegerInterface ii722 = new IntegerInterface() { 
        public int getInt() {
            return 722;
        }
    };
    private static IntegerInterface ii723 = new IntegerInterface() { 
        public int getInt() {
            return 723;
        }
    };
    private static IntegerInterface ii724 = new IntegerInterface() { 
        public int getInt() {
            return 724;
        }
    };
    private static IntegerInterface ii725 = new IntegerInterface() { 
        public int getInt() {
            return 725;
        }
    };
    private static IntegerInterface ii726 = new IntegerInterface() { 
        public int getInt() {
            return 726;
        }
    };
    private static IntegerInterface ii727 = new IntegerInterface() { 
        public int getInt() {
            return 727;
        }
    };
    private static IntegerInterface ii728 = new IntegerInterface() { 
        public int getInt() {
            return 728;
        }
    };
    private static IntegerInterface ii729 = new IntegerInterface() { 
        public int getInt() {
            return 729;
        }
    };
    private static IntegerInterface ii730 = new IntegerInterface() { 
        public int getInt() {
            return 730;
        }
    };
    private static IntegerInterface ii731 = new IntegerInterface() { 
        public int getInt() {
            return 731;
        }
    };
    private static IntegerInterface ii732 = new IntegerInterface() { 
        public int getInt() {
            return 732;
        }
    };
    private static IntegerInterface ii733 = new IntegerInterface() { 
        public int getInt() {
            return 733;
        }
    };
    private static IntegerInterface ii734 = new IntegerInterface() { 
        public int getInt() {
            return 734;
        }
    };
    private static IntegerInterface ii735 = new IntegerInterface() { 
        public int getInt() {
            return 735;
        }
    };
    private static IntegerInterface ii736 = new IntegerInterface() { 
        public int getInt() {
            return 736;
        }
    };
    private static IntegerInterface ii737 = new IntegerInterface() { 
        public int getInt() {
            return 737;
        }
    };
    private static IntegerInterface ii738 = new IntegerInterface() { 
        public int getInt() {
            return 738;
        }
    };
    private static IntegerInterface ii739 = new IntegerInterface() { 
        public int getInt() {
            return 739;
        }
    };
    private static IntegerInterface ii740 = new IntegerInterface() { 
        public int getInt() {
            return 740;
        }
    };
    private static IntegerInterface ii741 = new IntegerInterface() { 
        public int getInt() {
            return 741;
        }
    };
    private static IntegerInterface ii742 = new IntegerInterface() { 
        public int getInt() {
            return 742;
        }
    };
    private static IntegerInterface ii743 = new IntegerInterface() { 
        public int getInt() {
            return 743;
        }
    };
    private static IntegerInterface ii744 = new IntegerInterface() { 
        public int getInt() {
            return 744;
        }
    };
    private static IntegerInterface ii745 = new IntegerInterface() { 
        public int getInt() {
            return 745;
        }
    };
    private static IntegerInterface ii746 = new IntegerInterface() { 
        public int getInt() {
            return 746;
        }
    };
    private static IntegerInterface ii747 = new IntegerInterface() { 
        public int getInt() {
            return 747;
        }
    };
    private static IntegerInterface ii748 = new IntegerInterface() { 
        public int getInt() {
            return 748;
        }
    };
    private static IntegerInterface ii749 = new IntegerInterface() { 
        public int getInt() {
            return 749;
        }
    };
    private static IntegerInterface ii750 = new IntegerInterface() { 
        public int getInt() {
            return 750;
        }
    };
    private static IntegerInterface ii751 = new IntegerInterface() { 
        public int getInt() {
            return 751;
        }
    };
    private static IntegerInterface ii752 = new IntegerInterface() { 
        public int getInt() {
            return 752;
        }
    };
    private static IntegerInterface ii753 = new IntegerInterface() { 
        public int getInt() {
            return 753;
        }
    };
    private static IntegerInterface ii754 = new IntegerInterface() { 
        public int getInt() {
            return 754;
        }
    };
    private static IntegerInterface ii755 = new IntegerInterface() { 
        public int getInt() {
            return 755;
        }
    };
    private static IntegerInterface ii756 = new IntegerInterface() { 
        public int getInt() {
            return 756;
        }
    };
    private static IntegerInterface ii757 = new IntegerInterface() { 
        public int getInt() {
            return 757;
        }
    };
    private static IntegerInterface ii758 = new IntegerInterface() { 
        public int getInt() {
            return 758;
        }
    };
    private static IntegerInterface ii759 = new IntegerInterface() { 
        public int getInt() {
            return 759;
        }
    };
    private static IntegerInterface ii760 = new IntegerInterface() { 
        public int getInt() {
            return 760;
        }
    };
    private static IntegerInterface ii761 = new IntegerInterface() { 
        public int getInt() {
            return 761;
        }
    };
    private static IntegerInterface ii762 = new IntegerInterface() { 
        public int getInt() {
            return 762;
        }
    };
    private static IntegerInterface ii763 = new IntegerInterface() { 
        public int getInt() {
            return 763;
        }
    };
    private static IntegerInterface ii764 = new IntegerInterface() { 
        public int getInt() {
            return 764;
        }
    };
    private static IntegerInterface ii765 = new IntegerInterface() { 
        public int getInt() {
            return 765;
        }
    };
    private static IntegerInterface ii766 = new IntegerInterface() { 
        public int getInt() {
            return 766;
        }
    };
    private static IntegerInterface ii767 = new IntegerInterface() { 
        public int getInt() {
            return 767;
        }
    };
    private static IntegerInterface ii768 = new IntegerInterface() { 
        public int getInt() {
            return 768;
        }
    };
    private static IntegerInterface ii769 = new IntegerInterface() { 
        public int getInt() {
            return 769;
        }
    };
    private static IntegerInterface ii770 = new IntegerInterface() { 
        public int getInt() {
            return 770;
        }
    };
    private static IntegerInterface ii771 = new IntegerInterface() { 
        public int getInt() {
            return 771;
        }
    };
    private static IntegerInterface ii772 = new IntegerInterface() { 
        public int getInt() {
            return 772;
        }
    };
    private static IntegerInterface ii773 = new IntegerInterface() { 
        public int getInt() {
            return 773;
        }
    };
    private static IntegerInterface ii774 = new IntegerInterface() { 
        public int getInt() {
            return 774;
        }
    };
    private static IntegerInterface ii775 = new IntegerInterface() { 
        public int getInt() {
            return 775;
        }
    };
    private static IntegerInterface ii776 = new IntegerInterface() { 
        public int getInt() {
            return 776;
        }
    };
    private static IntegerInterface ii777 = new IntegerInterface() { 
        public int getInt() {
            return 777;
        }
    };
    private static IntegerInterface ii778 = new IntegerInterface() { 
        public int getInt() {
            return 778;
        }
    };
    private static IntegerInterface ii779 = new IntegerInterface() { 
        public int getInt() {
            return 779;
        }
    };
    private static IntegerInterface ii780 = new IntegerInterface() { 
        public int getInt() {
            return 780;
        }
    };
    private static IntegerInterface ii781 = new IntegerInterface() { 
        public int getInt() {
            return 781;
        }
    };
    private static IntegerInterface ii782 = new IntegerInterface() { 
        public int getInt() {
            return 782;
        }
    };
    private static IntegerInterface ii783 = new IntegerInterface() { 
        public int getInt() {
            return 783;
        }
    };
    private static IntegerInterface ii784 = new IntegerInterface() { 
        public int getInt() {
            return 784;
        }
    };
    private static IntegerInterface ii785 = new IntegerInterface() { 
        public int getInt() {
            return 785;
        }
    };
    private static IntegerInterface ii786 = new IntegerInterface() { 
        public int getInt() {
            return 786;
        }
    };
    private static IntegerInterface ii787 = new IntegerInterface() { 
        public int getInt() {
            return 787;
        }
    };
    private static IntegerInterface ii788 = new IntegerInterface() { 
        public int getInt() {
            return 788;
        }
    };
    private static IntegerInterface ii789 = new IntegerInterface() { 
        public int getInt() {
            return 789;
        }
    };
    private static IntegerInterface ii790 = new IntegerInterface() { 
        public int getInt() {
            return 790;
        }
    };
    private static IntegerInterface ii791 = new IntegerInterface() { 
        public int getInt() {
            return 791;
        }
    };
    private static IntegerInterface ii792 = new IntegerInterface() { 
        public int getInt() {
            return 792;
        }
    };
    private static IntegerInterface ii793 = new IntegerInterface() { 
        public int getInt() {
            return 793;
        }
    };
    private static IntegerInterface ii794 = new IntegerInterface() { 
        public int getInt() {
            return 794;
        }
    };
    private static IntegerInterface ii795 = new IntegerInterface() { 
        public int getInt() {
            return 795;
        }
    };
    private static IntegerInterface ii796 = new IntegerInterface() { 
        public int getInt() {
            return 796;
        }
    };
    private static IntegerInterface ii797 = new IntegerInterface() { 
        public int getInt() {
            return 797;
        }
    };
    private static IntegerInterface ii798 = new IntegerInterface() { 
        public int getInt() {
            return 798;
        }
    };
    private static IntegerInterface ii799 = new IntegerInterface() { 
        public int getInt() {
            return 799;
        }
    };
    private static IntegerInterface ii800 = new IntegerInterface() { 
        public int getInt() {
            return 800;
        }
    };
    private static IntegerInterface ii801 = new IntegerInterface() { 
        public int getInt() {
            return 801;
        }
    };
    private static IntegerInterface ii802 = new IntegerInterface() { 
        public int getInt() {
            return 802;
        }
    };
    private static IntegerInterface ii803 = new IntegerInterface() { 
        public int getInt() {
            return 803;
        }
    };
    private static IntegerInterface ii804 = new IntegerInterface() { 
        public int getInt() {
            return 804;
        }
    };
    private static IntegerInterface ii805 = new IntegerInterface() { 
        public int getInt() {
            return 805;
        }
    };
    private static IntegerInterface ii806 = new IntegerInterface() { 
        public int getInt() {
            return 806;
        }
    };
    private static IntegerInterface ii807 = new IntegerInterface() { 
        public int getInt() {
            return 807;
        }
    };
    private static IntegerInterface ii808 = new IntegerInterface() { 
        public int getInt() {
            return 808;
        }
    };
    private static IntegerInterface ii809 = new IntegerInterface() { 
        public int getInt() {
            return 809;
        }
    };
    private static IntegerInterface ii810 = new IntegerInterface() { 
        public int getInt() {
            return 810;
        }
    };
    private static IntegerInterface ii811 = new IntegerInterface() { 
        public int getInt() {
            return 811;
        }
    };
    private static IntegerInterface ii812 = new IntegerInterface() { 
        public int getInt() {
            return 812;
        }
    };
    private static IntegerInterface ii813 = new IntegerInterface() { 
        public int getInt() {
            return 813;
        }
    };
    private static IntegerInterface ii814 = new IntegerInterface() { 
        public int getInt() {
            return 814;
        }
    };
    private static IntegerInterface ii815 = new IntegerInterface() { 
        public int getInt() {
            return 815;
        }
    };
    private static IntegerInterface ii816 = new IntegerInterface() { 
        public int getInt() {
            return 816;
        }
    };
    private static IntegerInterface ii817 = new IntegerInterface() { 
        public int getInt() {
            return 817;
        }
    };
    private static IntegerInterface ii818 = new IntegerInterface() { 
        public int getInt() {
            return 818;
        }
    };
    private static IntegerInterface ii819 = new IntegerInterface() { 
        public int getInt() {
            return 819;
        }
    };
    private static IntegerInterface ii820 = new IntegerInterface() { 
        public int getInt() {
            return 820;
        }
    };
    private static IntegerInterface ii821 = new IntegerInterface() { 
        public int getInt() {
            return 821;
        }
    };
    private static IntegerInterface ii822 = new IntegerInterface() { 
        public int getInt() {
            return 822;
        }
    };
    private static IntegerInterface ii823 = new IntegerInterface() { 
        public int getInt() {
            return 823;
        }
    };
    private static IntegerInterface ii824 = new IntegerInterface() { 
        public int getInt() {
            return 824;
        }
    };
    private static IntegerInterface ii825 = new IntegerInterface() { 
        public int getInt() {
            return 825;
        }
    };
    private static IntegerInterface ii826 = new IntegerInterface() { 
        public int getInt() {
            return 826;
        }
    };
    private static IntegerInterface ii827 = new IntegerInterface() { 
        public int getInt() {
            return 827;
        }
    };
    private static IntegerInterface ii828 = new IntegerInterface() { 
        public int getInt() {
            return 828;
        }
    };
    private static IntegerInterface ii829 = new IntegerInterface() { 
        public int getInt() {
            return 829;
        }
    };
    private static IntegerInterface ii830 = new IntegerInterface() { 
        public int getInt() {
            return 830;
        }
    };
    private static IntegerInterface ii831 = new IntegerInterface() { 
        public int getInt() {
            return 831;
        }
    };
    private static IntegerInterface ii832 = new IntegerInterface() { 
        public int getInt() {
            return 832;
        }
    };
    private static IntegerInterface ii833 = new IntegerInterface() { 
        public int getInt() {
            return 833;
        }
    };
    private static IntegerInterface ii834 = new IntegerInterface() { 
        public int getInt() {
            return 834;
        }
    };
    private static IntegerInterface ii835 = new IntegerInterface() { 
        public int getInt() {
            return 835;
        }
    };
    private static IntegerInterface ii836 = new IntegerInterface() { 
        public int getInt() {
            return 836;
        }
    };
    private static IntegerInterface ii837 = new IntegerInterface() { 
        public int getInt() {
            return 837;
        }
    };
    private static IntegerInterface ii838 = new IntegerInterface() { 
        public int getInt() {
            return 838;
        }
    };
    private static IntegerInterface ii839 = new IntegerInterface() { 
        public int getInt() {
            return 839;
        }
    };
    private static IntegerInterface ii840 = new IntegerInterface() { 
        public int getInt() {
            return 840;
        }
    };
    private static IntegerInterface ii841 = new IntegerInterface() { 
        public int getInt() {
            return 841;
        }
    };
    private static IntegerInterface ii842 = new IntegerInterface() { 
        public int getInt() {
            return 842;
        }
    };
    private static IntegerInterface ii843 = new IntegerInterface() { 
        public int getInt() {
            return 843;
        }
    };
    private static IntegerInterface ii844 = new IntegerInterface() { 
        public int getInt() {
            return 844;
        }
    };
    private static IntegerInterface ii845 = new IntegerInterface() { 
        public int getInt() {
            return 845;
        }
    };
    private static IntegerInterface ii846 = new IntegerInterface() { 
        public int getInt() {
            return 846;
        }
    };
    private static IntegerInterface ii847 = new IntegerInterface() { 
        public int getInt() {
            return 847;
        }
    };
    private static IntegerInterface ii848 = new IntegerInterface() { 
        public int getInt() {
            return 848;
        }
    };
    private static IntegerInterface ii849 = new IntegerInterface() { 
        public int getInt() {
            return 849;
        }
    };
    private static IntegerInterface ii850 = new IntegerInterface() { 
        public int getInt() {
            return 850;
        }
    };
    private static IntegerInterface ii851 = new IntegerInterface() { 
        public int getInt() {
            return 851;
        }
    };
    private static IntegerInterface ii852 = new IntegerInterface() { 
        public int getInt() {
            return 852;
        }
    };
    private static IntegerInterface ii853 = new IntegerInterface() { 
        public int getInt() {
            return 853;
        }
    };
    private static IntegerInterface ii854 = new IntegerInterface() { 
        public int getInt() {
            return 854;
        }
    };
    private static IntegerInterface ii855 = new IntegerInterface() { 
        public int getInt() {
            return 855;
        }
    };
    private static IntegerInterface ii856 = new IntegerInterface() { 
        public int getInt() {
            return 856;
        }
    };
    private static IntegerInterface ii857 = new IntegerInterface() { 
        public int getInt() {
            return 857;
        }
    };
    private static IntegerInterface ii858 = new IntegerInterface() { 
        public int getInt() {
            return 858;
        }
    };
    private static IntegerInterface ii859 = new IntegerInterface() { 
        public int getInt() {
            return 859;
        }
    };
    private static IntegerInterface ii860 = new IntegerInterface() { 
        public int getInt() {
            return 860;
        }
    };
    private static IntegerInterface ii861 = new IntegerInterface() { 
        public int getInt() {
            return 861;
        }
    };
    private static IntegerInterface ii862 = new IntegerInterface() { 
        public int getInt() {
            return 862;
        }
    };
    private static IntegerInterface ii863 = new IntegerInterface() { 
        public int getInt() {
            return 863;
        }
    };
    private static IntegerInterface ii864 = new IntegerInterface() { 
        public int getInt() {
            return 864;
        }
    };
    private static IntegerInterface ii865 = new IntegerInterface() { 
        public int getInt() {
            return 865;
        }
    };
    private static IntegerInterface ii866 = new IntegerInterface() { 
        public int getInt() {
            return 866;
        }
    };
    private static IntegerInterface ii867 = new IntegerInterface() { 
        public int getInt() {
            return 867;
        }
    };
    private static IntegerInterface ii868 = new IntegerInterface() { 
        public int getInt() {
            return 868;
        }
    };
    private static IntegerInterface ii869 = new IntegerInterface() { 
        public int getInt() {
            return 869;
        }
    };
    private static IntegerInterface ii870 = new IntegerInterface() { 
        public int getInt() {
            return 870;
        }
    };
    private static IntegerInterface ii871 = new IntegerInterface() { 
        public int getInt() {
            return 871;
        }
    };
    private static IntegerInterface ii872 = new IntegerInterface() { 
        public int getInt() {
            return 872;
        }
    };
    private static IntegerInterface ii873 = new IntegerInterface() { 
        public int getInt() {
            return 873;
        }
    };
    private static IntegerInterface ii874 = new IntegerInterface() { 
        public int getInt() {
            return 874;
        }
    };
    private static IntegerInterface ii875 = new IntegerInterface() { 
        public int getInt() {
            return 875;
        }
    };
    private static IntegerInterface ii876 = new IntegerInterface() { 
        public int getInt() {
            return 876;
        }
    };
    private static IntegerInterface ii877 = new IntegerInterface() { 
        public int getInt() {
            return 877;
        }
    };
    private static IntegerInterface ii878 = new IntegerInterface() { 
        public int getInt() {
            return 878;
        }
    };
    private static IntegerInterface ii879 = new IntegerInterface() { 
        public int getInt() {
            return 879;
        }
    };
    private static IntegerInterface ii880 = new IntegerInterface() { 
        public int getInt() {
            return 880;
        }
    };
    private static IntegerInterface ii881 = new IntegerInterface() { 
        public int getInt() {
            return 881;
        }
    };
    private static IntegerInterface ii882 = new IntegerInterface() { 
        public int getInt() {
            return 882;
        }
    };
    private static IntegerInterface ii883 = new IntegerInterface() { 
        public int getInt() {
            return 883;
        }
    };
    private static IntegerInterface ii884 = new IntegerInterface() { 
        public int getInt() {
            return 884;
        }
    };
    private static IntegerInterface ii885 = new IntegerInterface() { 
        public int getInt() {
            return 885;
        }
    };
    private static IntegerInterface ii886 = new IntegerInterface() { 
        public int getInt() {
            return 886;
        }
    };
    private static IntegerInterface ii887 = new IntegerInterface() { 
        public int getInt() {
            return 887;
        }
    };
    private static IntegerInterface ii888 = new IntegerInterface() { 
        public int getInt() {
            return 888;
        }
    };
    private static IntegerInterface ii889 = new IntegerInterface() { 
        public int getInt() {
            return 889;
        }
    };
    private static IntegerInterface ii890 = new IntegerInterface() { 
        public int getInt() {
            return 890;
        }
    };
    private static IntegerInterface ii891 = new IntegerInterface() { 
        public int getInt() {
            return 891;
        }
    };
    private static IntegerInterface ii892 = new IntegerInterface() { 
        public int getInt() {
            return 892;
        }
    };
    private static IntegerInterface ii893 = new IntegerInterface() { 
        public int getInt() {
            return 893;
        }
    };
    private static IntegerInterface ii894 = new IntegerInterface() { 
        public int getInt() {
            return 894;
        }
    };
    private static IntegerInterface ii895 = new IntegerInterface() { 
        public int getInt() {
            return 895;
        }
    };
    private static IntegerInterface ii896 = new IntegerInterface() { 
        public int getInt() {
            return 896;
        }
    };
    private static IntegerInterface ii897 = new IntegerInterface() { 
        public int getInt() {
            return 897;
        }
    };
    private static IntegerInterface ii898 = new IntegerInterface() { 
        public int getInt() {
            return 898;
        }
    };
    private static IntegerInterface ii899 = new IntegerInterface() { 
        public int getInt() {
            return 899;
        }
    };
    private static IntegerInterface ii900 = new IntegerInterface() { 
        public int getInt() {
            return 900;
        }
    };
    private static IntegerInterface ii901 = new IntegerInterface() { 
        public int getInt() {
            return 901;
        }
    };
    private static IntegerInterface ii902 = new IntegerInterface() { 
        public int getInt() {
            return 902;
        }
    };
    private static IntegerInterface ii903 = new IntegerInterface() { 
        public int getInt() {
            return 903;
        }
    };
    private static IntegerInterface ii904 = new IntegerInterface() { 
        public int getInt() {
            return 904;
        }
    };
    private static IntegerInterface ii905 = new IntegerInterface() { 
        public int getInt() {
            return 905;
        }
    };
    private static IntegerInterface ii906 = new IntegerInterface() { 
        public int getInt() {
            return 906;
        }
    };
    private static IntegerInterface ii907 = new IntegerInterface() { 
        public int getInt() {
            return 907;
        }
    };
    private static IntegerInterface ii908 = new IntegerInterface() { 
        public int getInt() {
            return 908;
        }
    };
    private static IntegerInterface ii909 = new IntegerInterface() { 
        public int getInt() {
            return 909;
        }
    };
    private static IntegerInterface ii910 = new IntegerInterface() { 
        public int getInt() {
            return 910;
        }
    };
    private static IntegerInterface ii911 = new IntegerInterface() { 
        public int getInt() {
            return 911;
        }
    };
    private static IntegerInterface ii912 = new IntegerInterface() { 
        public int getInt() {
            return 912;
        }
    };
    private static IntegerInterface ii913 = new IntegerInterface() { 
        public int getInt() {
            return 913;
        }
    };
    private static IntegerInterface ii914 = new IntegerInterface() { 
        public int getInt() {
            return 914;
        }
    };
    private static IntegerInterface ii915 = new IntegerInterface() { 
        public int getInt() {
            return 915;
        }
    };
    private static IntegerInterface ii916 = new IntegerInterface() { 
        public int getInt() {
            return 916;
        }
    };
    private static IntegerInterface ii917 = new IntegerInterface() { 
        public int getInt() {
            return 917;
        }
    };
    private static IntegerInterface ii918 = new IntegerInterface() { 
        public int getInt() {
            return 918;
        }
    };
    private static IntegerInterface ii919 = new IntegerInterface() { 
        public int getInt() {
            return 919;
        }
    };
    private static IntegerInterface ii920 = new IntegerInterface() { 
        public int getInt() {
            return 920;
        }
    };
    private static IntegerInterface ii921 = new IntegerInterface() { 
        public int getInt() {
            return 921;
        }
    };
    private static IntegerInterface ii922 = new IntegerInterface() { 
        public int getInt() {
            return 922;
        }
    };
    private static IntegerInterface ii923 = new IntegerInterface() { 
        public int getInt() {
            return 923;
        }
    };
    private static IntegerInterface ii924 = new IntegerInterface() { 
        public int getInt() {
            return 924;
        }
    };
    private static IntegerInterface ii925 = new IntegerInterface() { 
        public int getInt() {
            return 925;
        }
    };
    private static IntegerInterface ii926 = new IntegerInterface() { 
        public int getInt() {
            return 926;
        }
    };
    private static IntegerInterface ii927 = new IntegerInterface() { 
        public int getInt() {
            return 927;
        }
    };
    private static IntegerInterface ii928 = new IntegerInterface() { 
        public int getInt() {
            return 928;
        }
    };
    private static IntegerInterface ii929 = new IntegerInterface() { 
        public int getInt() {
            return 929;
        }
    };
    private static IntegerInterface ii930 = new IntegerInterface() { 
        public int getInt() {
            return 930;
        }
    };
    private static IntegerInterface ii931 = new IntegerInterface() { 
        public int getInt() {
            return 931;
        }
    };
    private static IntegerInterface ii932 = new IntegerInterface() { 
        public int getInt() {
            return 932;
        }
    };
    private static IntegerInterface ii933 = new IntegerInterface() { 
        public int getInt() {
            return 933;
        }
    };
    private static IntegerInterface ii934 = new IntegerInterface() { 
        public int getInt() {
            return 934;
        }
    };
    private static IntegerInterface ii935 = new IntegerInterface() { 
        public int getInt() {
            return 935;
        }
    };
    private static IntegerInterface ii936 = new IntegerInterface() { 
        public int getInt() {
            return 936;
        }
    };
    private static IntegerInterface ii937 = new IntegerInterface() { 
        public int getInt() {
            return 937;
        }
    };
    private static IntegerInterface ii938 = new IntegerInterface() { 
        public int getInt() {
            return 938;
        }
    };
    private static IntegerInterface ii939 = new IntegerInterface() { 
        public int getInt() {
            return 939;
        }
    };
    private static IntegerInterface ii940 = new IntegerInterface() { 
        public int getInt() {
            return 940;
        }
    };
    private static IntegerInterface ii941 = new IntegerInterface() { 
        public int getInt() {
            return 941;
        }
    };
    private static IntegerInterface ii942 = new IntegerInterface() { 
        public int getInt() {
            return 942;
        }
    };
    private static IntegerInterface ii943 = new IntegerInterface() { 
        public int getInt() {
            return 943;
        }
    };
    private static IntegerInterface ii944 = new IntegerInterface() { 
        public int getInt() {
            return 944;
        }
    };
    private static IntegerInterface ii945 = new IntegerInterface() { 
        public int getInt() {
            return 945;
        }
    };
    private static IntegerInterface ii946 = new IntegerInterface() { 
        public int getInt() {
            return 946;
        }
    };
    private static IntegerInterface ii947 = new IntegerInterface() { 
        public int getInt() {
            return 947;
        }
    };
    private static IntegerInterface ii948 = new IntegerInterface() { 
        public int getInt() {
            return 948;
        }
    };
    private static IntegerInterface ii949 = new IntegerInterface() { 
        public int getInt() {
            return 949;
        }
    };
    private static IntegerInterface ii950 = new IntegerInterface() { 
        public int getInt() {
            return 950;
        }
    };
    private static IntegerInterface ii951 = new IntegerInterface() { 
        public int getInt() {
            return 951;
        }
    };
    private static IntegerInterface ii952 = new IntegerInterface() { 
        public int getInt() {
            return 952;
        }
    };
    private static IntegerInterface ii953 = new IntegerInterface() { 
        public int getInt() {
            return 953;
        }
    };
    private static IntegerInterface ii954 = new IntegerInterface() { 
        public int getInt() {
            return 954;
        }
    };
    private static IntegerInterface ii955 = new IntegerInterface() { 
        public int getInt() {
            return 955;
        }
    };
    private static IntegerInterface ii956 = new IntegerInterface() { 
        public int getInt() {
            return 956;
        }
    };
    private static IntegerInterface ii957 = new IntegerInterface() { 
        public int getInt() {
            return 957;
        }
    };
    private static IntegerInterface ii958 = new IntegerInterface() { 
        public int getInt() {
            return 958;
        }
    };
    private static IntegerInterface ii959 = new IntegerInterface() { 
        public int getInt() {
            return 959;
        }
    };
    private static IntegerInterface ii960 = new IntegerInterface() { 
        public int getInt() {
            return 960;
        }
    };
    private static IntegerInterface ii961 = new IntegerInterface() { 
        public int getInt() {
            return 961;
        }
    };
    private static IntegerInterface ii962 = new IntegerInterface() { 
        public int getInt() {
            return 962;
        }
    };
    private static IntegerInterface ii963 = new IntegerInterface() { 
        public int getInt() {
            return 963;
        }
    };
    private static IntegerInterface ii964 = new IntegerInterface() { 
        public int getInt() {
            return 964;
        }
    };
    private static IntegerInterface ii965 = new IntegerInterface() { 
        public int getInt() {
            return 965;
        }
    };
    private static IntegerInterface ii966 = new IntegerInterface() { 
        public int getInt() {
            return 966;
        }
    };
    private static IntegerInterface ii967 = new IntegerInterface() { 
        public int getInt() {
            return 967;
        }
    };
    private static IntegerInterface ii968 = new IntegerInterface() { 
        public int getInt() {
            return 968;
        }
    };
    private static IntegerInterface ii969 = new IntegerInterface() { 
        public int getInt() {
            return 969;
        }
    };
    private static IntegerInterface ii970 = new IntegerInterface() { 
        public int getInt() {
            return 970;
        }
    };
    private static IntegerInterface ii971 = new IntegerInterface() { 
        public int getInt() {
            return 971;
        }
    };
    private static IntegerInterface ii972 = new IntegerInterface() { 
        public int getInt() {
            return 972;
        }
    };
    private static IntegerInterface ii973 = new IntegerInterface() { 
        public int getInt() {
            return 973;
        }
    };
    private static IntegerInterface ii974 = new IntegerInterface() { 
        public int getInt() {
            return 974;
        }
    };
    private static IntegerInterface ii975 = new IntegerInterface() { 
        public int getInt() {
            return 975;
        }
    };
    private static IntegerInterface ii976 = new IntegerInterface() { 
        public int getInt() {
            return 976;
        }
    };
    private static IntegerInterface ii977 = new IntegerInterface() { 
        public int getInt() {
            return 977;
        }
    };
    private static IntegerInterface ii978 = new IntegerInterface() { 
        public int getInt() {
            return 978;
        }
    };
    private static IntegerInterface ii979 = new IntegerInterface() { 
        public int getInt() {
            return 979;
        }
    };
    private static IntegerInterface ii980 = new IntegerInterface() { 
        public int getInt() {
            return 980;
        }
    };
    private static IntegerInterface ii981 = new IntegerInterface() { 
        public int getInt() {
            return 981;
        }
    };
    private static IntegerInterface ii982 = new IntegerInterface() { 
        public int getInt() {
            return 982;
        }
    };
    private static IntegerInterface ii983 = new IntegerInterface() { 
        public int getInt() {
            return 983;
        }
    };
    private static IntegerInterface ii984 = new IntegerInterface() { 
        public int getInt() {
            return 984;
        }
    };
    private static IntegerInterface ii985 = new IntegerInterface() { 
        public int getInt() {
            return 985;
        }
    };
    private static IntegerInterface ii986 = new IntegerInterface() { 
        public int getInt() {
            return 986;
        }
    };
    private static IntegerInterface ii987 = new IntegerInterface() { 
        public int getInt() {
            return 987;
        }
    };
    private static IntegerInterface ii988 = new IntegerInterface() { 
        public int getInt() {
            return 988;
        }
    };
    private static IntegerInterface ii989 = new IntegerInterface() { 
        public int getInt() {
            return 989;
        }
    };
    private static IntegerInterface ii990 = new IntegerInterface() { 
        public int getInt() {
            return 990;
        }
    };
    private static IntegerInterface ii991 = new IntegerInterface() { 
        public int getInt() {
            return 991;
        }
    };
    private static IntegerInterface ii992 = new IntegerInterface() { 
        public int getInt() {
            return 992;
        }
    };
    private static IntegerInterface ii993 = new IntegerInterface() { 
        public int getInt() {
            return 993;
        }
    };
    private static IntegerInterface ii994 = new IntegerInterface() { 
        public int getInt() {
            return 994;
        }
    };
    private static IntegerInterface ii995 = new IntegerInterface() { 
        public int getInt() {
            return 995;
        }
    };
    private static IntegerInterface ii996 = new IntegerInterface() { 
        public int getInt() {
            return 996;
        }
    };
    private static IntegerInterface ii997 = new IntegerInterface() { 
        public int getInt() {
            return 997;
        }
    };
    private static IntegerInterface ii998 = new IntegerInterface() { 
        public int getInt() {
            return 998;
        }
    };
    private static IntegerInterface ii999 = new IntegerInterface() { 
        public int getInt() {
            return 999;
        }
    };
    private static IntegerInterface ii1000 = new IntegerInterface() { 
        public int getInt() {
            return 1000;
        }
    };
    private static IntegerInterface ii1001 = new IntegerInterface() { 
        public int getInt() {
            return 1001;
        }
    };
    private static IntegerInterface ii1002 = new IntegerInterface() { 
        public int getInt() {
            return 1002;
        }
    };
    private static IntegerInterface ii1003 = new IntegerInterface() { 
        public int getInt() {
            return 1003;
        }
    };
    private static IntegerInterface ii1004 = new IntegerInterface() { 
        public int getInt() {
            return 1004;
        }
    };
    private static IntegerInterface ii1005 = new IntegerInterface() { 
        public int getInt() {
            return 1005;
        }
    };
    private static IntegerInterface ii1006 = new IntegerInterface() { 
        public int getInt() {
            return 1006;
        }
    };
    private static IntegerInterface ii1007 = new IntegerInterface() { 
        public int getInt() {
            return 1007;
        }
    };
    private static IntegerInterface ii1008 = new IntegerInterface() { 
        public int getInt() {
            return 1008;
        }
    };
    private static IntegerInterface ii1009 = new IntegerInterface() { 
        public int getInt() {
            return 1009;
        }
    };
    private static IntegerInterface ii1010 = new IntegerInterface() { 
        public int getInt() {
            return 1010;
        }
    };
    private static IntegerInterface ii1011 = new IntegerInterface() { 
        public int getInt() {
            return 1011;
        }
    };
    private static IntegerInterface ii1012 = new IntegerInterface() { 
        public int getInt() {
            return 1012;
        }
    };
    private static IntegerInterface ii1013 = new IntegerInterface() { 
        public int getInt() {
            return 1013;
        }
    };
    private static IntegerInterface ii1014 = new IntegerInterface() { 
        public int getInt() {
            return 1014;
        }
    };
    private static IntegerInterface ii1015 = new IntegerInterface() { 
        public int getInt() {
            return 1015;
        }
    };
    private static IntegerInterface ii1016 = new IntegerInterface() { 
        public int getInt() {
            return 1016;
        }
    };
    private static IntegerInterface ii1017 = new IntegerInterface() { 
        public int getInt() {
            return 1017;
        }
    };
    private static IntegerInterface ii1018 = new IntegerInterface() { 
        public int getInt() {
            return 1018;
        }
    };
    private static IntegerInterface ii1019 = new IntegerInterface() { 
        public int getInt() {
            return 1019;
        }
    };
    private static IntegerInterface ii1020 = new IntegerInterface() { 
        public int getInt() {
            return 1020;
        }
    };
    private static IntegerInterface ii1021 = new IntegerInterface() { 
        public int getInt() {
            return 1021;
        }
    };
    private static IntegerInterface ii1022 = new IntegerInterface() { 
        public int getInt() {
            return 1022;
        }
    };
    private static IntegerInterface ii1023 = new IntegerInterface() { 
        public int getInt() {
            return 1023;
        }
    };
    private static IntegerInterface ii1024 = new IntegerInterface() { 
        public int getInt() {
            return 1024;
        }
    };

    private static class StaticIntTest1024 implements CalcInterface {
        public int calc() {
            return ii1.getInt() + 
            ii2.getInt() + 
            ii3.getInt() + 
            ii4.getInt() + 
            ii5.getInt() + 
            ii6.getInt() + 
            ii7.getInt() + 
            ii8.getInt() + 
            ii9.getInt() + 
            ii10.getInt() + 
            ii11.getInt() + 
            ii12.getInt() + 
            ii13.getInt() + 
            ii14.getInt() + 
            ii15.getInt() + 
            ii16.getInt() + 
            ii17.getInt() + 
            ii18.getInt() + 
            ii19.getInt() + 
            ii20.getInt() + 
            ii21.getInt() + 
            ii22.getInt() + 
            ii23.getInt() + 
            ii24.getInt() + 
            ii25.getInt() + 
            ii26.getInt() + 
            ii27.getInt() + 
            ii28.getInt() + 
            ii29.getInt() + 
            ii30.getInt() + 
            ii31.getInt() + 
            ii32.getInt() + 
            ii33.getInt() + 
            ii34.getInt() + 
            ii35.getInt() + 
            ii36.getInt() + 
            ii37.getInt() + 
            ii38.getInt() + 
            ii39.getInt() + 
            ii40.getInt() + 
            ii41.getInt() + 
            ii42.getInt() + 
            ii43.getInt() + 
            ii44.getInt() + 
            ii45.getInt() + 
            ii46.getInt() + 
            ii47.getInt() + 
            ii48.getInt() + 
            ii49.getInt() + 
            ii50.getInt() + 
            ii51.getInt() + 
            ii52.getInt() + 
            ii53.getInt() + 
            ii54.getInt() + 
            ii55.getInt() + 
            ii56.getInt() + 
            ii57.getInt() + 
            ii58.getInt() + 
            ii59.getInt() + 
            ii60.getInt() + 
            ii61.getInt() + 
            ii62.getInt() + 
            ii63.getInt() + 
            ii64.getInt() + 
            ii65.getInt() + 
            ii66.getInt() + 
            ii67.getInt() + 
            ii68.getInt() + 
            ii69.getInt() + 
            ii70.getInt() + 
            ii71.getInt() + 
            ii72.getInt() + 
            ii73.getInt() + 
            ii74.getInt() + 
            ii75.getInt() + 
            ii76.getInt() + 
            ii77.getInt() + 
            ii78.getInt() + 
            ii79.getInt() + 
            ii80.getInt() + 
            ii81.getInt() + 
            ii82.getInt() + 
            ii83.getInt() + 
            ii84.getInt() + 
            ii85.getInt() + 
            ii86.getInt() + 
            ii87.getInt() + 
            ii88.getInt() + 
            ii89.getInt() + 
            ii90.getInt() + 
            ii91.getInt() + 
            ii92.getInt() + 
            ii93.getInt() + 
            ii94.getInt() + 
            ii95.getInt() + 
            ii96.getInt() + 
            ii97.getInt() + 
            ii98.getInt() + 
            ii99.getInt() + 
            ii100.getInt() + 
            ii101.getInt() + 
            ii102.getInt() + 
            ii103.getInt() + 
            ii104.getInt() + 
            ii105.getInt() + 
            ii106.getInt() + 
            ii107.getInt() + 
            ii108.getInt() + 
            ii109.getInt() + 
            ii110.getInt() + 
            ii111.getInt() + 
            ii112.getInt() + 
            ii113.getInt() + 
            ii114.getInt() + 
            ii115.getInt() + 
            ii116.getInt() + 
            ii117.getInt() + 
            ii118.getInt() + 
            ii119.getInt() + 
            ii120.getInt() + 
            ii121.getInt() + 
            ii122.getInt() + 
            ii123.getInt() + 
            ii124.getInt() + 
            ii125.getInt() + 
            ii126.getInt() + 
            ii127.getInt() + 
            ii128.getInt() + 
            ii129.getInt() + 
            ii130.getInt() + 
            ii131.getInt() + 
            ii132.getInt() + 
            ii133.getInt() + 
            ii134.getInt() + 
            ii135.getInt() + 
            ii136.getInt() + 
            ii137.getInt() + 
            ii138.getInt() + 
            ii139.getInt() + 
            ii140.getInt() + 
            ii141.getInt() + 
            ii142.getInt() + 
            ii143.getInt() + 
            ii144.getInt() + 
            ii145.getInt() + 
            ii146.getInt() + 
            ii147.getInt() + 
            ii148.getInt() + 
            ii149.getInt() + 
            ii150.getInt() + 
            ii151.getInt() + 
            ii152.getInt() + 
            ii153.getInt() + 
            ii154.getInt() + 
            ii155.getInt() + 
            ii156.getInt() + 
            ii157.getInt() + 
            ii158.getInt() + 
            ii159.getInt() + 
            ii160.getInt() + 
            ii161.getInt() + 
            ii162.getInt() + 
            ii163.getInt() + 
            ii164.getInt() + 
            ii165.getInt() + 
            ii166.getInt() + 
            ii167.getInt() + 
            ii168.getInt() + 
            ii169.getInt() + 
            ii170.getInt() + 
            ii171.getInt() + 
            ii172.getInt() + 
            ii173.getInt() + 
            ii174.getInt() + 
            ii175.getInt() + 
            ii176.getInt() + 
            ii177.getInt() + 
            ii178.getInt() + 
            ii179.getInt() + 
            ii180.getInt() + 
            ii181.getInt() + 
            ii182.getInt() + 
            ii183.getInt() + 
            ii184.getInt() + 
            ii185.getInt() + 
            ii186.getInt() + 
            ii187.getInt() + 
            ii188.getInt() + 
            ii189.getInt() + 
            ii190.getInt() + 
            ii191.getInt() + 
            ii192.getInt() + 
            ii193.getInt() + 
            ii194.getInt() + 
            ii195.getInt() + 
            ii196.getInt() + 
            ii197.getInt() + 
            ii198.getInt() + 
            ii199.getInt() + 
            ii200.getInt() + 
            ii201.getInt() + 
            ii202.getInt() + 
            ii203.getInt() + 
            ii204.getInt() + 
            ii205.getInt() + 
            ii206.getInt() + 
            ii207.getInt() + 
            ii208.getInt() + 
            ii209.getInt() + 
            ii210.getInt() + 
            ii211.getInt() + 
            ii212.getInt() + 
            ii213.getInt() + 
            ii214.getInt() + 
            ii215.getInt() + 
            ii216.getInt() + 
            ii217.getInt() + 
            ii218.getInt() + 
            ii219.getInt() + 
            ii220.getInt() + 
            ii221.getInt() + 
            ii222.getInt() + 
            ii223.getInt() + 
            ii224.getInt() + 
            ii225.getInt() + 
            ii226.getInt() + 
            ii227.getInt() + 
            ii228.getInt() + 
            ii229.getInt() + 
            ii230.getInt() + 
            ii231.getInt() + 
            ii232.getInt() + 
            ii233.getInt() + 
            ii234.getInt() + 
            ii235.getInt() + 
            ii236.getInt() + 
            ii237.getInt() + 
            ii238.getInt() + 
            ii239.getInt() + 
            ii240.getInt() + 
            ii241.getInt() + 
            ii242.getInt() + 
            ii243.getInt() + 
            ii244.getInt() + 
            ii245.getInt() + 
            ii246.getInt() + 
            ii247.getInt() + 
            ii248.getInt() + 
            ii249.getInt() + 
            ii250.getInt() + 
            ii251.getInt() + 
            ii252.getInt() + 
            ii253.getInt() + 
            ii254.getInt() + 
            ii255.getInt() + 
            ii256.getInt() + 
            ii257.getInt() + 
            ii258.getInt() + 
            ii259.getInt() + 
            ii260.getInt() + 
            ii261.getInt() + 
            ii262.getInt() + 
            ii263.getInt() + 
            ii264.getInt() + 
            ii265.getInt() + 
            ii266.getInt() + 
            ii267.getInt() + 
            ii268.getInt() + 
            ii269.getInt() + 
            ii270.getInt() + 
            ii271.getInt() + 
            ii272.getInt() + 
            ii273.getInt() + 
            ii274.getInt() + 
            ii275.getInt() + 
            ii276.getInt() + 
            ii277.getInt() + 
            ii278.getInt() + 
            ii279.getInt() + 
            ii280.getInt() + 
            ii281.getInt() + 
            ii282.getInt() + 
            ii283.getInt() + 
            ii284.getInt() + 
            ii285.getInt() + 
            ii286.getInt() + 
            ii287.getInt() + 
            ii288.getInt() + 
            ii289.getInt() + 
            ii290.getInt() + 
            ii291.getInt() + 
            ii292.getInt() + 
            ii293.getInt() + 
            ii294.getInt() + 
            ii295.getInt() + 
            ii296.getInt() + 
            ii297.getInt() + 
            ii298.getInt() + 
            ii299.getInt() + 
            ii300.getInt() + 
            ii301.getInt() + 
            ii302.getInt() + 
            ii303.getInt() + 
            ii304.getInt() + 
            ii305.getInt() + 
            ii306.getInt() + 
            ii307.getInt() + 
            ii308.getInt() + 
            ii309.getInt() + 
            ii310.getInt() + 
            ii311.getInt() + 
            ii312.getInt() + 
            ii313.getInt() + 
            ii314.getInt() + 
            ii315.getInt() + 
            ii316.getInt() + 
            ii317.getInt() + 
            ii318.getInt() + 
            ii319.getInt() + 
            ii320.getInt() + 
            ii321.getInt() + 
            ii322.getInt() + 
            ii323.getInt() + 
            ii324.getInt() + 
            ii325.getInt() + 
            ii326.getInt() + 
            ii327.getInt() + 
            ii328.getInt() + 
            ii329.getInt() + 
            ii330.getInt() + 
            ii331.getInt() + 
            ii332.getInt() + 
            ii333.getInt() + 
            ii334.getInt() + 
            ii335.getInt() + 
            ii336.getInt() + 
            ii337.getInt() + 
            ii338.getInt() + 
            ii339.getInt() + 
            ii340.getInt() + 
            ii341.getInt() + 
            ii342.getInt() + 
            ii343.getInt() + 
            ii344.getInt() + 
            ii345.getInt() + 
            ii346.getInt() + 
            ii347.getInt() + 
            ii348.getInt() + 
            ii349.getInt() + 
            ii350.getInt() + 
            ii351.getInt() + 
            ii352.getInt() + 
            ii353.getInt() + 
            ii354.getInt() + 
            ii355.getInt() + 
            ii356.getInt() + 
            ii357.getInt() + 
            ii358.getInt() + 
            ii359.getInt() + 
            ii360.getInt() + 
            ii361.getInt() + 
            ii362.getInt() + 
            ii363.getInt() + 
            ii364.getInt() + 
            ii365.getInt() + 
            ii366.getInt() + 
            ii367.getInt() + 
            ii368.getInt() + 
            ii369.getInt() + 
            ii370.getInt() + 
            ii371.getInt() + 
            ii372.getInt() + 
            ii373.getInt() + 
            ii374.getInt() + 
            ii375.getInt() + 
            ii376.getInt() + 
            ii377.getInt() + 
            ii378.getInt() + 
            ii379.getInt() + 
            ii380.getInt() + 
            ii381.getInt() + 
            ii382.getInt() + 
            ii383.getInt() + 
            ii384.getInt() + 
            ii385.getInt() + 
            ii386.getInt() + 
            ii387.getInt() + 
            ii388.getInt() + 
            ii389.getInt() + 
            ii390.getInt() + 
            ii391.getInt() + 
            ii392.getInt() + 
            ii393.getInt() + 
            ii394.getInt() + 
            ii395.getInt() + 
            ii396.getInt() + 
            ii397.getInt() + 
            ii398.getInt() + 
            ii399.getInt() + 
            ii400.getInt() + 
            ii401.getInt() + 
            ii402.getInt() + 
            ii403.getInt() + 
            ii404.getInt() + 
            ii405.getInt() + 
            ii406.getInt() + 
            ii407.getInt() + 
            ii408.getInt() + 
            ii409.getInt() + 
            ii410.getInt() + 
            ii411.getInt() + 
            ii412.getInt() + 
            ii413.getInt() + 
            ii414.getInt() + 
            ii415.getInt() + 
            ii416.getInt() + 
            ii417.getInt() + 
            ii418.getInt() + 
            ii419.getInt() + 
            ii420.getInt() + 
            ii421.getInt() + 
            ii422.getInt() + 
            ii423.getInt() + 
            ii424.getInt() + 
            ii425.getInt() + 
            ii426.getInt() + 
            ii427.getInt() + 
            ii428.getInt() + 
            ii429.getInt() + 
            ii430.getInt() + 
            ii431.getInt() + 
            ii432.getInt() + 
            ii433.getInt() + 
            ii434.getInt() + 
            ii435.getInt() + 
            ii436.getInt() + 
            ii437.getInt() + 
            ii438.getInt() + 
            ii439.getInt() + 
            ii440.getInt() + 
            ii441.getInt() + 
            ii442.getInt() + 
            ii443.getInt() + 
            ii444.getInt() + 
            ii445.getInt() + 
            ii446.getInt() + 
            ii447.getInt() + 
            ii448.getInt() + 
            ii449.getInt() + 
            ii450.getInt() + 
            ii451.getInt() + 
            ii452.getInt() + 
            ii453.getInt() + 
            ii454.getInt() + 
            ii455.getInt() + 
            ii456.getInt() + 
            ii457.getInt() + 
            ii458.getInt() + 
            ii459.getInt() + 
            ii460.getInt() + 
            ii461.getInt() + 
            ii462.getInt() + 
            ii463.getInt() + 
            ii464.getInt() + 
            ii465.getInt() + 
            ii466.getInt() + 
            ii467.getInt() + 
            ii468.getInt() + 
            ii469.getInt() + 
            ii470.getInt() + 
            ii471.getInt() + 
            ii472.getInt() + 
            ii473.getInt() + 
            ii474.getInt() + 
            ii475.getInt() + 
            ii476.getInt() + 
            ii477.getInt() + 
            ii478.getInt() + 
            ii479.getInt() + 
            ii480.getInt() + 
            ii481.getInt() + 
            ii482.getInt() + 
            ii483.getInt() + 
            ii484.getInt() + 
            ii485.getInt() + 
            ii486.getInt() + 
            ii487.getInt() + 
            ii488.getInt() + 
            ii489.getInt() + 
            ii490.getInt() + 
            ii491.getInt() + 
            ii492.getInt() + 
            ii493.getInt() + 
            ii494.getInt() + 
            ii495.getInt() + 
            ii496.getInt() + 
            ii497.getInt() + 
            ii498.getInt() + 
            ii499.getInt() + 
            ii500.getInt() + 
            ii501.getInt() + 
            ii502.getInt() + 
            ii503.getInt() + 
            ii504.getInt() + 
            ii505.getInt() + 
            ii506.getInt() + 
            ii507.getInt() + 
            ii508.getInt() + 
            ii509.getInt() + 
            ii510.getInt() + 
            ii511.getInt() + 
            ii512.getInt() + 
            ii513.getInt() + 
            ii514.getInt() + 
            ii515.getInt() + 
            ii516.getInt() + 
            ii517.getInt() + 
            ii518.getInt() + 
            ii519.getInt() + 
            ii520.getInt() + 
            ii521.getInt() + 
            ii522.getInt() + 
            ii523.getInt() + 
            ii524.getInt() + 
            ii525.getInt() + 
            ii526.getInt() + 
            ii527.getInt() + 
            ii528.getInt() + 
            ii529.getInt() + 
            ii530.getInt() + 
            ii531.getInt() + 
            ii532.getInt() + 
            ii533.getInt() + 
            ii534.getInt() + 
            ii535.getInt() + 
            ii536.getInt() + 
            ii537.getInt() + 
            ii538.getInt() + 
            ii539.getInt() + 
            ii540.getInt() + 
            ii541.getInt() + 
            ii542.getInt() + 
            ii543.getInt() + 
            ii544.getInt() + 
            ii545.getInt() + 
            ii546.getInt() + 
            ii547.getInt() + 
            ii548.getInt() + 
            ii549.getInt() + 
            ii550.getInt() + 
            ii551.getInt() + 
            ii552.getInt() + 
            ii553.getInt() + 
            ii554.getInt() + 
            ii555.getInt() + 
            ii556.getInt() + 
            ii557.getInt() + 
            ii558.getInt() + 
            ii559.getInt() + 
            ii560.getInt() + 
            ii561.getInt() + 
            ii562.getInt() + 
            ii563.getInt() + 
            ii564.getInt() + 
            ii565.getInt() + 
            ii566.getInt() + 
            ii567.getInt() + 
            ii568.getInt() + 
            ii569.getInt() + 
            ii570.getInt() + 
            ii571.getInt() + 
            ii572.getInt() + 
            ii573.getInt() + 
            ii574.getInt() + 
            ii575.getInt() + 
            ii576.getInt() + 
            ii577.getInt() + 
            ii578.getInt() + 
            ii579.getInt() + 
            ii580.getInt() + 
            ii581.getInt() + 
            ii582.getInt() + 
            ii583.getInt() + 
            ii584.getInt() + 
            ii585.getInt() + 
            ii586.getInt() + 
            ii587.getInt() + 
            ii588.getInt() + 
            ii589.getInt() + 
            ii590.getInt() + 
            ii591.getInt() + 
            ii592.getInt() + 
            ii593.getInt() + 
            ii594.getInt() + 
            ii595.getInt() + 
            ii596.getInt() + 
            ii597.getInt() + 
            ii598.getInt() + 
            ii599.getInt() + 
            ii600.getInt() + 
            ii601.getInt() + 
            ii602.getInt() + 
            ii603.getInt() + 
            ii604.getInt() + 
            ii605.getInt() + 
            ii606.getInt() + 
            ii607.getInt() + 
            ii608.getInt() + 
            ii609.getInt() + 
            ii610.getInt() + 
            ii611.getInt() + 
            ii612.getInt() + 
            ii613.getInt() + 
            ii614.getInt() + 
            ii615.getInt() + 
            ii616.getInt() + 
            ii617.getInt() + 
            ii618.getInt() + 
            ii619.getInt() + 
            ii620.getInt() + 
            ii621.getInt() + 
            ii622.getInt() + 
            ii623.getInt() + 
            ii624.getInt() + 
            ii625.getInt() + 
            ii626.getInt() + 
            ii627.getInt() + 
            ii628.getInt() + 
            ii629.getInt() + 
            ii630.getInt() + 
            ii631.getInt() + 
            ii632.getInt() + 
            ii633.getInt() + 
            ii634.getInt() + 
            ii635.getInt() + 
            ii636.getInt() + 
            ii637.getInt() + 
            ii638.getInt() + 
            ii639.getInt() + 
            ii640.getInt() + 
            ii641.getInt() + 
            ii642.getInt() + 
            ii643.getInt() + 
            ii644.getInt() + 
            ii645.getInt() + 
            ii646.getInt() + 
            ii647.getInt() + 
            ii648.getInt() + 
            ii649.getInt() + 
            ii650.getInt() + 
            ii651.getInt() + 
            ii652.getInt() + 
            ii653.getInt() + 
            ii654.getInt() + 
            ii655.getInt() + 
            ii656.getInt() + 
            ii657.getInt() + 
            ii658.getInt() + 
            ii659.getInt() + 
            ii660.getInt() + 
            ii661.getInt() + 
            ii662.getInt() + 
            ii663.getInt() + 
            ii664.getInt() + 
            ii665.getInt() + 
            ii666.getInt() + 
            ii667.getInt() + 
            ii668.getInt() + 
            ii669.getInt() + 
            ii670.getInt() + 
            ii671.getInt() + 
            ii672.getInt() + 
            ii673.getInt() + 
            ii674.getInt() + 
            ii675.getInt() + 
            ii676.getInt() + 
            ii677.getInt() + 
            ii678.getInt() + 
            ii679.getInt() + 
            ii680.getInt() + 
            ii681.getInt() + 
            ii682.getInt() + 
            ii683.getInt() + 
            ii684.getInt() + 
            ii685.getInt() + 
            ii686.getInt() + 
            ii687.getInt() + 
            ii688.getInt() + 
            ii689.getInt() + 
            ii690.getInt() + 
            ii691.getInt() + 
            ii692.getInt() + 
            ii693.getInt() + 
            ii694.getInt() + 
            ii695.getInt() + 
            ii696.getInt() + 
            ii697.getInt() + 
            ii698.getInt() + 
            ii699.getInt() + 
            ii700.getInt() + 
            ii701.getInt() + 
            ii702.getInt() + 
            ii703.getInt() + 
            ii704.getInt() + 
            ii705.getInt() + 
            ii706.getInt() + 
            ii707.getInt() + 
            ii708.getInt() + 
            ii709.getInt() + 
            ii710.getInt() + 
            ii711.getInt() + 
            ii712.getInt() + 
            ii713.getInt() + 
            ii714.getInt() + 
            ii715.getInt() + 
            ii716.getInt() + 
            ii717.getInt() + 
            ii718.getInt() + 
            ii719.getInt() + 
            ii720.getInt() + 
            ii721.getInt() + 
            ii722.getInt() + 
            ii723.getInt() + 
            ii724.getInt() + 
            ii725.getInt() + 
            ii726.getInt() + 
            ii727.getInt() + 
            ii728.getInt() + 
            ii729.getInt() + 
            ii730.getInt() + 
            ii731.getInt() + 
            ii732.getInt() + 
            ii733.getInt() + 
            ii734.getInt() + 
            ii735.getInt() + 
            ii736.getInt() + 
            ii737.getInt() + 
            ii738.getInt() + 
            ii739.getInt() + 
            ii740.getInt() + 
            ii741.getInt() + 
            ii742.getInt() + 
            ii743.getInt() + 
            ii744.getInt() + 
            ii745.getInt() + 
            ii746.getInt() + 
            ii747.getInt() + 
            ii748.getInt() + 
            ii749.getInt() + 
            ii750.getInt() + 
            ii751.getInt() + 
            ii752.getInt() + 
            ii753.getInt() + 
            ii754.getInt() + 
            ii755.getInt() + 
            ii756.getInt() + 
            ii757.getInt() + 
            ii758.getInt() + 
            ii759.getInt() + 
            ii760.getInt() + 
            ii761.getInt() + 
            ii762.getInt() + 
            ii763.getInt() + 
            ii764.getInt() + 
            ii765.getInt() + 
            ii766.getInt() + 
            ii767.getInt() + 
            ii768.getInt() + 
            ii769.getInt() + 
            ii770.getInt() + 
            ii771.getInt() + 
            ii772.getInt() + 
            ii773.getInt() + 
            ii774.getInt() + 
            ii775.getInt() + 
            ii776.getInt() + 
            ii777.getInt() + 
            ii778.getInt() + 
            ii779.getInt() + 
            ii780.getInt() + 
            ii781.getInt() + 
            ii782.getInt() + 
            ii783.getInt() + 
            ii784.getInt() + 
            ii785.getInt() + 
            ii786.getInt() + 
            ii787.getInt() + 
            ii788.getInt() + 
            ii789.getInt() + 
            ii790.getInt() + 
            ii791.getInt() + 
            ii792.getInt() + 
            ii793.getInt() + 
            ii794.getInt() + 
            ii795.getInt() + 
            ii796.getInt() + 
            ii797.getInt() + 
            ii798.getInt() + 
            ii799.getInt() + 
            ii800.getInt() + 
            ii801.getInt() + 
            ii802.getInt() + 
            ii803.getInt() + 
            ii804.getInt() + 
            ii805.getInt() + 
            ii806.getInt() + 
            ii807.getInt() + 
            ii808.getInt() + 
            ii809.getInt() + 
            ii810.getInt() + 
            ii811.getInt() + 
            ii812.getInt() + 
            ii813.getInt() + 
            ii814.getInt() + 
            ii815.getInt() + 
            ii816.getInt() + 
            ii817.getInt() + 
            ii818.getInt() + 
            ii819.getInt() + 
            ii820.getInt() + 
            ii821.getInt() + 
            ii822.getInt() + 
            ii823.getInt() + 
            ii824.getInt() + 
            ii825.getInt() + 
            ii826.getInt() + 
            ii827.getInt() + 
            ii828.getInt() + 
            ii829.getInt() + 
            ii830.getInt() + 
            ii831.getInt() + 
            ii832.getInt() + 
            ii833.getInt() + 
            ii834.getInt() + 
            ii835.getInt() + 
            ii836.getInt() + 
            ii837.getInt() + 
            ii838.getInt() + 
            ii839.getInt() + 
            ii840.getInt() + 
            ii841.getInt() + 
            ii842.getInt() + 
            ii843.getInt() + 
            ii844.getInt() + 
            ii845.getInt() + 
            ii846.getInt() + 
            ii847.getInt() + 
            ii848.getInt() + 
            ii849.getInt() + 
            ii850.getInt() + 
            ii851.getInt() + 
            ii852.getInt() + 
            ii853.getInt() + 
            ii854.getInt() + 
            ii855.getInt() + 
            ii856.getInt() + 
            ii857.getInt() + 
            ii858.getInt() + 
            ii859.getInt() + 
            ii860.getInt() + 
            ii861.getInt() + 
            ii862.getInt() + 
            ii863.getInt() + 
            ii864.getInt() + 
            ii865.getInt() + 
            ii866.getInt() + 
            ii867.getInt() + 
            ii868.getInt() + 
            ii869.getInt() + 
            ii870.getInt() + 
            ii871.getInt() + 
            ii872.getInt() + 
            ii873.getInt() + 
            ii874.getInt() + 
            ii875.getInt() + 
            ii876.getInt() + 
            ii877.getInt() + 
            ii878.getInt() + 
            ii879.getInt() + 
            ii880.getInt() + 
            ii881.getInt() + 
            ii882.getInt() + 
            ii883.getInt() + 
            ii884.getInt() + 
            ii885.getInt() + 
            ii886.getInt() + 
            ii887.getInt() + 
            ii888.getInt() + 
            ii889.getInt() + 
            ii890.getInt() + 
            ii891.getInt() + 
            ii892.getInt() + 
            ii893.getInt() + 
            ii894.getInt() + 
            ii895.getInt() + 
            ii896.getInt() + 
            ii897.getInt() + 
            ii898.getInt() + 
            ii899.getInt() + 
            ii900.getInt() + 
            ii901.getInt() + 
            ii902.getInt() + 
            ii903.getInt() + 
            ii904.getInt() + 
            ii905.getInt() + 
            ii906.getInt() + 
            ii907.getInt() + 
            ii908.getInt() + 
            ii909.getInt() + 
            ii910.getInt() + 
            ii911.getInt() + 
            ii912.getInt() + 
            ii913.getInt() + 
            ii914.getInt() + 
            ii915.getInt() + 
            ii916.getInt() + 
            ii917.getInt() + 
            ii918.getInt() + 
            ii919.getInt() + 
            ii920.getInt() + 
            ii921.getInt() + 
            ii922.getInt() + 
            ii923.getInt() + 
            ii924.getInt() + 
            ii925.getInt() + 
            ii926.getInt() + 
            ii927.getInt() + 
            ii928.getInt() + 
            ii929.getInt() + 
            ii930.getInt() + 
            ii931.getInt() + 
            ii932.getInt() + 
            ii933.getInt() + 
            ii934.getInt() + 
            ii935.getInt() + 
            ii936.getInt() + 
            ii937.getInt() + 
            ii938.getInt() + 
            ii939.getInt() + 
            ii940.getInt() + 
            ii941.getInt() + 
            ii942.getInt() + 
            ii943.getInt() + 
            ii944.getInt() + 
            ii945.getInt() + 
            ii946.getInt() + 
            ii947.getInt() + 
            ii948.getInt() + 
            ii949.getInt() + 
            ii950.getInt() + 
            ii951.getInt() + 
            ii952.getInt() + 
            ii953.getInt() + 
            ii954.getInt() + 
            ii955.getInt() + 
            ii956.getInt() + 
            ii957.getInt() + 
            ii958.getInt() + 
            ii959.getInt() + 
            ii960.getInt() + 
            ii961.getInt() + 
            ii962.getInt() + 
            ii963.getInt() + 
            ii964.getInt() + 
            ii965.getInt() + 
            ii966.getInt() + 
            ii967.getInt() + 
            ii968.getInt() + 
            ii969.getInt() + 
            ii970.getInt() + 
            ii971.getInt() + 
            ii972.getInt() + 
            ii973.getInt() + 
            ii974.getInt() + 
            ii975.getInt() + 
            ii976.getInt() + 
            ii977.getInt() + 
            ii978.getInt() + 
            ii979.getInt() + 
            ii980.getInt() + 
            ii981.getInt() + 
            ii982.getInt() + 
            ii983.getInt() + 
            ii984.getInt() + 
            ii985.getInt() + 
            ii986.getInt() + 
            ii987.getInt() + 
            ii988.getInt() + 
            ii989.getInt() + 
            ii990.getInt() + 
            ii991.getInt() + 
            ii992.getInt() + 
            ii993.getInt() + 
            ii994.getInt() + 
            ii995.getInt() + 
            ii996.getInt() + 
            ii997.getInt() + 
            ii998.getInt() + 
            ii999.getInt() + 
            ii1000.getInt() + 
            ii1001.getInt() + 
            ii1002.getInt() + 
            ii1003.getInt() + 
            ii1004.getInt() + 
            ii1005.getInt() + 
            ii1006.getInt() + 
            ii1007.getInt() + 
            ii1008.getInt() + 
            ii1009.getInt() + 
            ii1010.getInt() + 
            ii1011.getInt() + 
            ii1012.getInt() + 
            ii1013.getInt() + 
            ii1014.getInt() + 
            ii1015.getInt() + 
            ii1016.getInt() + 
            ii1017.getInt() + 
            ii1018.getInt() + 
            ii1019.getInt() + 
            ii1020.getInt() + 
            ii1021.getInt() + 
            ii1022.getInt() + 
            ii1023.getInt() + 
            ii1024.getInt();
        }
    }

    private static class StaticIntTest3 implements CalcInterface {
        public int calc() {
            return ii1.getInt() + ii2.getInt() + ii3.getInt();
	}
    }

    CalcInterface lambda1024;
    CalcInterface lambda3;
    CalcInterface anonymous1024;
    CalcInterface anonymous3;
    CalcInterface static1024;
    CalcInterface static3;
    @Setup
    public void setup() {
        lambda1024 = new LambdaIntTest1024();
        lambda3 = new LambdaIntTest3();
        anonymous1024 = new AnonymousIntTest1024();
        anonymous3 = new AnonymousIntTest3();
        static1024 = new StaticIntTest1024();
        static3 = new StaticIntTest3();
    }

    @Benchmark
    public void testLambda1024(Blackhole bh) {
	bh.consume(lambda1024.calc());
    }

    @Benchmark
    public void testLambda3(Blackhole bh) {
	bh.consume(lambda3.calc());
    }

    @Benchmark
    public void testAnonymous1024(Blackhole bh) {
	bh.consume(anonymous1024.calc());
    }

    @Benchmark
    public void testAnonymous3(Blackhole bh) {
	bh.consume(anonymous3.calc());
    }

    @Benchmark
    public void testStatic1024(Blackhole bh) {
	bh.consume(static1024.calc());
    }

    @Benchmark
    public void testStatic3(Blackhole bh) {
	bh.consume(static3.calc());
    }
}
