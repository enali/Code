package example.flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object SocketWindowWordCount {
  def main(args: Array[String]): Unit = {
    val port: Int = try {
      ParameterTool.fromArgs(args).getInt("port")
    } catch {
      case e: Exception => {
        System.err.println("No port specified")
        return
      }
    }

    // 获取执行环境
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // 数据源, 以\n为分隔符
    val text = env.socketTextStream("localhost", port, '\n')

    val windowCounts = text
      .flatMap(_.split("\\s+"))  // 对每行以空格切分单词
      .map(w => WordWithCount(w, 1))  // 构造case实例
      .keyBy("word")  // 以word域为key
      .timeWindow(Time.seconds(5))  // 以5秒为窗口捕获数据
      .sum("count")  // 求count域的和

    windowCounts.print().setParallelism(1)

    env.execute("Socket Window WordCount")
  }

  case class WordWithCount(word: String, count: Long)
}
