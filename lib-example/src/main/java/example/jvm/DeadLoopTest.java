package example.jvm;

/**
 * 当多个线程在初始化一个类时, 只有一个线程会去初始化, 其余线程会阻塞
 */
public class DeadLoopTest {
    static class DeadLoopClass {
        static {
            // 加上if, 来避免检测到死循环
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                while (true) {}
            }
        }
    }

    public static void main(String[] args) {
        Runnable script = () -> {
            System.out.println(Thread.currentThread() + "start");
            DeadLoopClass dlc = new DeadLoopClass();  // 在这里会进行类的初始化
            System.out.println(Thread.currentThread() + "run over");
        };
        new Thread(script).start();  // 在类的初始化中, 会在死循环那阻塞, 只有一个线程会执行类的初始化
        new Thread(script).start();
    }
}
