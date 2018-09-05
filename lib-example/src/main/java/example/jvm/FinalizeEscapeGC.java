package example.jvm;

/**
 * 特点: 减慢GC速度
 *
 * 1, 回收对象时, 对对象进行第1次标记并进行筛选, 条件是此对象是否有必要执行finalize方法
 * 2, 有必要执行的则加入F-Queue队列, 并在新的Finalizer线程中, 执行finalize方法
 * 3, 若在finalize方法中, 对象重新跟GC roots产生关联, 则将不被回收
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;  // 这里是关键, 重新跟GC roots产生关联
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第1次拯救自己, 成功
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) SAVE_HOOK.isAlive();
        else System.out.println("no, i am dead :(");

        // 对象第2次拯救自己, 失败, 每个对象的finalize只能调用一次
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) SAVE_HOOK.isAlive();
        else System.out.println("no, i am dead :(");
    }
}
