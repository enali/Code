import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String DNA = scanner.next().toUpperCase();
        int n = DNA.length();
        while (n >= 4) {
            List<String> res = findRepeatedDnaSequences(DNA, n);
            if (res.size() > 0) {
                System.out.println(res.get(0) + " " + res.get(0).length());
                break;
            }
            n--;
        }

        if (n < 4) System.out.println(" " + 0);
    }

    public static List<String> findRepeatedDnaSequences(String s, int length) {
        Set<String> result = new HashSet<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i <= s.length() - length; i++) {
            if (result.size() > 0) {
                break;
            }
            if (!set.contains(s.substring(i, i + length)))
                set.add(s.substring(i, i + length));
            else result.add(s.substring(i, i + length));
        }
        return new ArrayList<>(result);
    }
}
