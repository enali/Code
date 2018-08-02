package example.extralib.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Counter 就是计数器，Counter 只是用 Gauge 封装了 AtomicLong. 即线程安全
 *
 * 重点是, 要对counter进行inc/dec
 */
public class CounterExample {
    public static Queue<String> q = new LinkedBlockingQueue<>();
    public static Counter pendingJobs;
    public static Random random = new Random();

    public static void addJob(String job) {
        pendingJobs.inc();  // 计数加1
        q.offer(job);  // 添加, 若满则false
    }

    public static String takeJob() {
        pendingJobs.dec();  // 计数减1
        return q.poll();  // 移除, 若空则null
    }

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        pendingJobs = registry.counter(  // 如果metric name已经注册了Counter, 则取, 否则创建并注册
                MetricRegistry.name(Queue.class,"pending-jobs","size"));

        int num = 1;
        while(true){  // 每200ms, 0.7的概率减job, 0.3的概率加job
            Thread.sleep(200);
            if (random.nextDouble() > 0.7){
                String job = takeJob();
                System.out.println("take job : "+job);
            }else{
                String job = "Job-"+num;
                addJob(job);
                System.out.println("add job : "+job);
            }
            num++;
        }
    }
}
