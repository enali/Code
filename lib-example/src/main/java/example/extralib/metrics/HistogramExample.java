package example.extralib.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Histogram统计数据的分布情况。比如最小值，最大值，中间值，还有中位数，75百分位, 90百分位, 95百分位,
 * 98百分位, 99百分位, 和 99.9百分位的值(percentiles)。
 *
 * 要统计值, 某种程度上自然要存储之前的所有数据, 当然, 某种程度上可以通过前一个统计值更新后一个统计值
 */
public class HistogramExample {
    public static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Histogram histogram = new Histogram(
                new ExponentiallyDecayingReservoir());  // 指数衰减的随机储层
        registry.register(
                MetricRegistry.name(HistogramExample.class, "request", "histogram"),
                histogram);

        while (true) {
            Thread.sleep(1000);  // 每1s添加一次
            histogram.update(random.nextInt(100000));  // 添加一个记录值
        }
    }
}
