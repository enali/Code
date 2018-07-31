package example.stdlib;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorTester {
    private static class CodingTask implements Runnable {
        private int idx;
        public CodingTask(int idx) { this.idx = idx; }

        @Override
        public void run() {
            System.out.println("coding " + idx + " ...");
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Future<?>> taskResults = new LinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            taskResults.add(executor.submit(new CodingTask(i)));
        }
        System.out.println("10 tasks dispatched successfully.");

        // 等待作业执行结束
//        for (Future<?> f : taskResults) {
//            f.get();
//        }
//
//        System.out.println("All tasks finished.");
        // 先前提交的任务会被执行， 但不会接受新的任务
        executor.shutdown();
    }
}
