package example.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations {
    /**
     * 以递归的方式进行排列
     *
     * C_n^m = C_n-1^m-1 + C_n-1^m, 即选第1个元素和不选第1个元素, 递归解法
     */
    // 意为， 从data中选出n个元素进行排列, selected为已选择的元素
    public void combinations(List<Integer> selected, List<Integer> data, int n) {
        // 当n=0时， 表示元素选择完毕， 则可以输出
        if (n == 0) {
            for (Integer i: selected) {
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println();
            return;
        }

        if (data.isEmpty())  return;

        // select element 0
        selected.add(data.get(0));
        combinations(selected, data.subList(1, data.size()), n-1);

        // un-select element 0
        selected.remove(selected.size() - 1);
        combinations(selected, data.subList(1, data.size()), n);
    }

    public static void main(String[] args) {
        Combinations comb = new Combinations();
        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
        comb.combinations(new ArrayList<>(), new ArrayList<>(), 2);
        comb.combinations(new ArrayList<>(), new ArrayList<>(), 0);
        comb.combinations(new ArrayList<>(), Arrays.asList(1, 2, 3, 4), 2);
    }
}
