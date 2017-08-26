package edu.cs.scu.analysis

import edu.cs.scu.scalautils.InitUtil
import org.apache.spark.streaming.StreamingContext


/**
  * 分析程序入口
  *
  * Created by Wang Han on 2017/3/29 14:42.
  * E-mail address is wanghan0501@vip.qq.com.
  * Copyright © Wang Han SCU. All Rights Reserved.
  *
  * @author Wang Han
  */
object Main {
  def main(args: Array[String]): Unit = {

    val spark = InitUtil.initSparkSession()
    // 流环境
    val streamingContext: StreamingContext = InitUtil.getStreamingContext(spark.sparkContext)

    //    val kafkaData = InitUtil.getDStreamFromKafka(streamingContext).map(_._2)
    //    kafkaData.print()

    // 获取原始数据
    val originData = InitUtil.getDStream(streamingContext)
    // 如果读入的数据不为空
    if (originData != null) {
      RealTimeAnalysis.analysis(spark, streamingContext, originData)
    }
    // 启动流环境
    streamingContext.start()
    streamingContext.awaitTermination()

  }
}