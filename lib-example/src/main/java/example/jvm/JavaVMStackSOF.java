package example.jvm;

/**
 * 通过无限递归制造SOF
 *
 * VMargs: -Xss128k, 设定每个线程的栈大小, 栈是线程的
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();  // 无限递归调用
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {  // 这里捕获的是Throwable, 而SOF也是可捕获的.
            System.out.println("stack length: " + oom.stackLength);
            throw e;
        }
    }
}
