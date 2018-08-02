package example.extralib.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 最简单的度量指标，只有一个简单的返回值，例如，我们想衡量一个待处理队列中任务的个数
 *
 * 可以这样理解: 你把想测量的值注册到registry, 再设定reporter, 在程序运行的过程中就会自动reporter
 */
public class GaugeExample {
    public static Queue<String> q = new LinkedList<>();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();  // 存放所有metrics的容器
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();  // 使用console来展示数据
        reporter.start(1, TimeUnit.SECONDS);  // 设定数据展示的频率
        registry.register(
                MetricRegistry.name(GaugeExample.class, "queue", "size"),  // 生成metric的名字
                (Gauge<Integer>) () -> q.size());  // 要注册的Metric, 一个接口, 具体的实现类为Gauge

        // 每一秒添加一个字串到队列
        while(true){
            Thread.sleep(1000);
            q.add("Job-xxx");
        }
    }
}
