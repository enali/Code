package example.jvm;

/**
 * 通过无限创建线程来制造栈的OOM.
 *
 * 在Windows系统中, 每个Java线程对应到内核线程, 此处无限创建线程, 每个线程还无限循环, 会导致所有的内核线程被占用, 导致操作系统死机
 *
 * VMargs: -Xss2m, 每个线程的栈越大, 在总栈大小相同的情况下, 容纳的线程数就小
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {}  // 每个线程都不会停止
    }

    public void stackLeakByThread() {
        while (true) {  // 无限创建线程
            new Thread(() -> dontStop()).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
