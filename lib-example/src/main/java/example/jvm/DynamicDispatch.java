package example.jvm;

// 动态分派的典型应用是方法重写
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }
    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }
    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();  // 调用对象的方法时, 由实际类型决定, 同样的, 对象的类型是不可能改变的
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
