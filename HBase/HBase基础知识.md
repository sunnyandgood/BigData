# HBase基础知识

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/TheHadoopEcosytem.png"/></div>

### 一、HBase简介

* HBase – Hadoop Database，是一个高可靠性、高性能、面向列、可伸缩的分布式存储系统，利用HBase技术可在廉价PC Server上搭建起大规模结构化存储集群。HBase利用Hadoop HDFS作为其文件存储系统，利用Hadoop MapReduce来处理HBase中的海量数据，利用Zookeeper作为协调工具。

* 思考：如何存储物流信息

      运单号：728311858891

      2018-01-07 10:09:25	上海			已发货
      2018-01-07 18:27:32	上海航空部		已收件
      2018-01-07 20:25:38	快件离开上海航空部	已发往北京
      2018-01-09 08:27:14	北京回龙观		派件中
      2018-01-09 12:37:37	北京回龙观		已签收
