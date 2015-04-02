public class Trie {

    Trie_Node root = new Trie_Node();

    class Trie_Node {
        int value;
        Trie_Node alpha[] = new Trie_Node[26];
    }




    int convertToInt(char c) {
        return ((int) c - (int) 'a');

    }

    void insert(String data) {
        char[] chars = data.toCharArray();
        Trie_Node root1 = root;
        for (int i = 0; i < chars.length; i++) {
            int charIntValue = convertToInt(chars[i]);

            if (root1.alpha[charIntValue] == null)
                root1.alpha[charIntValue] = new Trie_Node();
            root1 = root1.alpha[charIntValue];

        }
        root1.value = chars.length;

    }

    boolean search(String data) {
        char[] chars = data.toCharArray();
        Trie_Node root1 = root;
        for (int i = 0; i < chars.length; i++) {
            int charIntValue = convertToInt(chars[i]);
            if (root1.alpha[charIntValue] == null)
                return false;
            root1 = root1.alpha[charIntValue];
        }
        if (root1.value == chars.length) {
            System.out.println("Available");
            return true;
        } else {
            return false;
        }

    }

    Trie_Node delete(char[] data,int length,Trie_Node root){
       if(length==data.length)
           return null;
        int charIntValue = convertToInt(data[length]);
        root=  root.alpha[charIntValue];
        delete(data,length+1,root);

        System.out.println((char)(charIntValue+(int)'a'));


       return  root;
    }


    public static void main(String a[]) {
        Trie trie = new Trie();
        trie.insert("there");
        trie.insert("their");
        trie.insert("hope");
        trie.insert("death");

        trie.search("there");
        trie.search("death");


    }


}
