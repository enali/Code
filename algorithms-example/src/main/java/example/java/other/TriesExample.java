package example.java.other;

/**
 * 字典树的简单实现.
 */
public class TriesExample {
    static class TriesNode {
        TriesNode[] next = new TriesNode[26];

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<next.length; i++) {
                if (next[i] != null) {
                    char c = (char)('a' + i);
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

    TriesNode root = new TriesNode();

    public void insert(String str) {
        TriesNode tmp = root;
        for (int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (tmp.next[idx] == null) {
                tmp.next[idx] = new TriesNode();
            }
            tmp = tmp.next[idx];
        }
    }

    public boolean isExist(String str) {
        TriesNode tmp = root;
        for (int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if (tmp.next[idx] == null) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        TriesExample te = new TriesExample();
        te.insert("a");
        te.insert("b");
        te.insert("ab");
        te.insert("abc");
        System.out.println(te.isExist("a"));
        System.out.println(te.isExist("b"));
        System.out.println(te.isExist("ab"));
        System.out.println(te.isExist("abd"));
        System.out.println(te.isExist("abc"));
    }

}
