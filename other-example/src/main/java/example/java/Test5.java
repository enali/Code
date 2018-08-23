package example.java;

import java.util.*;

/**
 * @author OovEver
 * 2018/8/22 19:06
 */
public class Test5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String DNA = scanner.next();
        int n = DNA.length() / 2;
        String resString = "";
        while (n >= 4) {
            List<String> res = findRepeatedDnaSequences(DNA, n);
            if (res.size() > 0) {
                if (resString == "") {
                    resString = res.get(0);
                } else if (resString.contains(res.get(0))) {
                    resString = res.get(0);
                }
            }
            n--;
        }
        if (n >= 3 && resString != " ") {
            System.out.println(resString + " " + resString.length());
        } else
            System.out.println(" " + 0);
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