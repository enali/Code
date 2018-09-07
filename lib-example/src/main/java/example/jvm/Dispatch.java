package example.jvm;

/**
 * 参考: {@link StaticDispatch}, {@link DynamicDispatch}
 * 可大概知道, 静态分派跟方法的参数相关, 动态分派跟方法的接收者相关
 */
public class Dispatch {
    static class QQ {}
    static class _360 {}

    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father {
        @Override
        public void hardChoice(QQ arg) {  // 跟下面的方法是重载, 跟Father的方法是重写
            System.out.println("son choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());  // 静态多分派, 根据接收者father和参数360决定方法
        son.hardChoice(new QQ());  // 动态单分派, 在编译期, 由参数限定只能是QQ, 区别是调用son还是father
    }
}
