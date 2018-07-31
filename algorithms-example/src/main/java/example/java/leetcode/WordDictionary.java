package example.java.leetcode;

class WordDictionary {
    Node root;

    private class Node {
        boolean val = false;
        Node[] next = new Node[26];
    }

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null) return;
        if (word.equals("")) {
            root.val = true;
            return;
        }

        Node cur = root;
        int len = word.length();
        for (int i=0; i<len; i++) {
            int loc = word.charAt(i) - 'a';
            if (cur.next[loc] == null) {
                cur.next[loc] = new Node();
            }
            cur = cur.next[loc];
        }
        cur.val = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word.equals("")) return root.val;
        return search(word, 0, root);
    }

    private boolean search(String word, int idx, Node p) {
        System.out.println(word + " " + idx + " ");
        int N = word.length();
        if (idx == N && p.val) return true;
        char c = word.charAt(idx);
        if (c != '.') {
            if (p.next[c-'a'] == null) return false;
            return search(word, idx+1, p.next[c-'a']);
        } else {
            for (int i=0; i<26; i++) {
                if (p.next[i] == null) continue;
                if (search(word, idx+1, p.next[i])) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] ary = {"bad", "dad", "mad", ""};
        WordDictionary wd = new WordDictionary();
        for (int i=0; i<ary.length; i++) wd.addWord(ary[i]);
        System.out.println(wd.search("b"));
    }
}