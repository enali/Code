package example.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local[4]")
    val sc = new SparkContext(conf)

    val path = "README.md"
    val file = sc.textFile(path)
    val words = file.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_ + _)
    words.collect.foreach(println)

    sc.stop()
  }
}
