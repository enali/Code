package example.extralib.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Meter度量一系列事件发生的速率(rate)，例如TPS。Meters会统计最近1分钟，5分钟，15分钟，还有全部时间的速率。
 *
 * 重点是要对meter进行mark
 */
public class MeterExample {
    public static Random random = new Random();

    public static void request(Meter meter, int n){
        for (int i = 0; i < n; i++) {
            System.out.println("request");
        }
        meter.mark(n);  // 标记n个事件发生
    }

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);  // 1s报告一次
        Meter meterTps = registry.meter(
                MetricRegistry.name(MeterExample.class,"request","tps"));

        // 每1s产生n < 5 个请求
        while (true){
            request(meterTps,random.nextInt(5));
            Thread.sleep(1000);
        }
    }
}
