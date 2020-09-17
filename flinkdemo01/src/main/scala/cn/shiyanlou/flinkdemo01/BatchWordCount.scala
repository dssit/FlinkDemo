package cn.shiyanlou.flinkdemo01

import java.net.URL

import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}

/**
 * Flink的批计算案例
 */
object BatchWordCount {

  def main(args: Array[String]): Unit = {
    //初始化Flink批处理环境
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    import org.apache.flink.streaming.api.scala._
    var filepath =args(0);
    val dataPath: URL = getClass.getResource(filepath) //使用相对路径来得到完整的文件路径

    //读数据
    val data: DataSet[String] = env.readTextFile(dataPath.getPath) //DataSet ==> spark RDD

    //计算并且打印结果
    data.flatMap(_.split(" "))
      .map((_,1))
      .groupBy(0)
      .sum(1)
      .print()
  }
}
