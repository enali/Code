package example.spark

import org.apache.spark.{SparkConf, SparkContext}

class WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
    val sc = new SparkContext(conf)

    val file = sc.textFile("hdfs:///user/enali/README.md")
    val words = file.flatMap(_.split("\\s+")).map((_, 1)).reduceByKey(_ + _)
    words.collect.foreach(println)

    sc.stop()
  }
}
