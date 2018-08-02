package example.extralib;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * 分别生成getter/setter/toString/hashCode/equals方法
 */
@Getter  // add getter for var
@Setter  // add setter for var
@ToString  // add toString for class
@EqualsAndHashCode  // add equals/hashCode for class
@Slf4j  // use "log" to log msg
public class LombokExample {
    private String name;
    private int age;

    public static void main(String[] args) {
        LombokExample le = new LombokExample();
        le.setName("enali");
        le.setAge(12);
        System.out.println(le);

        // 有@Slf4j的注解, 可以直接使用log来记日志
        log.info(le.toString());
    }
}
