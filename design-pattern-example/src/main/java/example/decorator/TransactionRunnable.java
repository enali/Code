package example.decorator;

public class TransactionRunnable implements Runnable {
    private final Runnable runnable;

    public TransactionRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        boolean shouldRoolback = false;
        try {
            beginTransaction();
            runnable.run();
        } catch (Exception e) {
            shouldRoolback = true;
            throw e;
        } finally {
            if (shouldRoolback) {
                rollback();
            } else {
                commit();
            }
        }
    }

    private void commit() {
        System.out.println("commit!");
    }

    private void rollback() {
        System.out.println("roll back");
    }

    private void beginTransaction() {
        System.out.println("begin transaction");
    }

    public static void main(String[] args) {
        Runnable r = new TransactionRunnable(new CodingTask(0));
        r.run();
    }
}
