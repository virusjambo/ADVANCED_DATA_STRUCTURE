package trie;

import java.util.*;

public class AnagramUsingMap {

   //Root for  trie
    Trie_Node root = new Trie_Node();

    class Trie_Node {
        int value;//To store length of String to define end .This can be replaced with boolean if end of string make true
        Map<Character, Trie_Node> alpha = new HashMap<Character, Trie_Node>();//To create tri tree
        List<Integer> index = new ArrayList<Integer>();//To store the index of  current String will need this to print anagram
    }

//Insert in to trie
    void insert(String data, int index) {
        char[] chars = data.toCharArray();
        Arrays.sort(chars);//Need this so we can maintain anagram Ex.if act tca here all are act act only.NlogM
        Trie_Node root1 = root;
        for (int i = 0; i < chars.length; i++) {


            if (!root1.alpha.containsKey(chars[i]))//check if has key
                root1.alpha.put(chars[i], new Trie_Node());//else put new entry
            root1 = root1.alpha.get(chars[i]);//if key exist then assign value to root

        }
        root1.value = chars.length;//to last root add length of the string
        root1.index.add(index);//Here we will store indexes of all anagrams which will be needed during traverse to crate a set of each anagram

    }

    void search(Trie_Node trie_node, List<String> dataString) {
        if (trie_node.value != 0) { //check if end of string reached then print all anagram associated with it
            for (int i : trie_node.index) {
                System.out.println(dataString.get(i));
            }
        }
        if (trie_node.alpha.keySet().size() == 0)//Base condition for recurse
            return;
        Iterator<Character> trie_nodeIterator = trie_node.alpha.keySet().iterator();
        while (trie_nodeIterator.hasNext()) {
            search(trie_node.alpha.get(trie_nodeIterator.next()), dataString);
        }

    }
//Total complexity can be around NMlogM to sort all N String
 //To insert in to Trie for N words N*O(M) where M is length of String
 //To traverse a Trie for N words N*O(M) where M is length of String
    //Total NMlogM+N*O(M)+N*O(M)
    //we can say NMlogM+N*O(M)

    public static void main(String a[]) {
        AnagramUsingMap trie = new AnagramUsingMap();
        List<String> dataString = new ArrayList<String>();
        dataString.add("act");
        dataString.add("cta");
        dataString.add("tca");
        dataString.add("god");
        dataString.add("dog");
        dataString.add("atc");
        dataString.add("a");
        dataString.add("ac");

        int i = 0;
        for (String key : dataString) {
            trie.insert(key, i++);
        }
        trie.search(trie.root, dataString);


    }

}
