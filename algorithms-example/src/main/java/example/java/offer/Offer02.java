package example.java.offer;

/**
 * 题目：设计一个类，我们只能生成该类的一个实例
 */
public class Offer02 {
    /**
     * 单例模式，类在初始化时即实例化instance, 无论是否调用getInstance
     */
    public static class Singleton1 {
        private final static Singleton1 instance = new Singleton1();
        private Singleton1() {}
        public static Singleton1 getInstance() {
            return instance;
        }
    }

    /**
     * 内部类模式, 只有在调用getInstance时才会实例化instance
     */
    public static class Singleton2 {
        private final static class SingletonHandler {
            private final static Singleton2 instance = new Singleton2();
        }
        private Singleton2() {}
        public static Singleton2 getInstance() {
            return SingletonHandler.instance;
        }
    }

    /**
     * 双检锁模式
     */
    public static class Singleton3 {
        private static Singleton3 instance = null;
        private Singleton3() {}
        public static Singleton3 getInstance() {
            if (instance == null) {
                synchronized (Singleton3.class) {
                    if (instance == null) {
                        instance = new Singleton3();
                    }
                }
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        System.out.println(Singleton1.getInstance() == Singleton1.getInstance());
        System.out.println(Singleton2.getInstance() == Singleton2.getInstance());
        System.out.println(Singleton3.getInstance() == Singleton3.getInstance());
    }
}
