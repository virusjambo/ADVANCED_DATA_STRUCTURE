package anagram;


import java.util.*;
import java.util.Arrays;

 class EntryKey {
    String value;
    int hash;

    public EntryKey(String value) {
        this.value = value;
        hash = hashKey();
    }


    public int hashKey() {
        int h = hash;
        int len = value.length();
        if (h == 0 && len > 0) {
            int off = 0;
            char[] val = value.toCharArray();
            Arrays.sort(val);
            for (int i = 0; i < len; i++) {
                h = 31 * h + val[off++];
            }
            hash = h;
        }
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

}
public class Anagram {
    List<EntryKey> entryKeyList = new ArrayList<EntryKey>();

    private void anagramUtil(List<String> anagramList) {
        for (String key : anagramList) {
            entryKeyList.add(new EntryKey(key));
        }

        Collections.sort(entryKeyList, SORT);

        for (EntryKey entryKey : entryKeyList) {
            System.out.println(entryKey.value);
        }
    }

    public static void main(String a[]) {
        List<String> anagram = new ArrayList<String>();
        anagram.add("231");
        anagram.add("213");
        anagram.add("cat");
        anagram.add("dog");
        anagram.add("tac");
        anagram.add("act");
        anagram.add("god");
        anagram.add("odg");
        anagram.add("123");
        anagram.add("321");
        anagram.add("ab");
        anagram.add("c");

        Anagram anagram1 = new Anagram();
        anagram1.anagramUtil(anagram);

    }

    public static final Comparator<EntryKey> SORT =
            new Comparator<EntryKey>() {
                public int compare(EntryKey obj1, EntryKey obj2) {
                    Integer h1 = obj1.hash;
                    return (h1.compareTo(obj2.hash));
                }
            };

}
