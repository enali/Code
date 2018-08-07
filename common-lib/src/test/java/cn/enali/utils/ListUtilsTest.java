package cn.enali.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListUtilsTest {
    @Test
    void createLinkedList() {
        List<Integer> data = Arrays.asList(1,2,3,4,5);
        ListNode<Integer> list = ListUtils.createLinkedList(data);
        System.out.println(ListUtils.mkString(list, ","));
        assert ListUtils.mkString(list, ",").equals("1,2,3,4,5");
    }

    // TODO: 添加更多的测试
}
