package example.decorator;

/**
 * Decorator Patttern
 *
 * 目标： 为CodingTask实现某种修饰功能, 即在调用run前， 先输出个什么
 */
public class LoggingRunnable implements Runnable {
    private final Runnable runnable;

    public LoggingRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        System.out.println("log start at " + startTime);
        runnable.run();
        System.out.println("log end. Elapsed time: " + (System.currentTimeMillis() - startTime));
    }
}
