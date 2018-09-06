package example;

/**
 * 用于研究Java字节码的示例类
 */
public class HelloWorld {
    private String name;

    public HelloWorld(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("hello, " + name);
    }

    public void world() {
        System.out.println("world, " + name);
    }

    public static void main(String[] args) {
        HelloWorld hw = new HelloWorld("lizp");
        hw.hello();
        hw.world();
    }
}
