# HBase基础知识

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/TheHadoopEcosytem.png"/></div>

### 一、HBase简介

* HBase – Hadoop Database，是一个高可靠性、高性能、面向列、可伸缩的分布式存储系统，利用HBase技术可在廉价PC Server上搭建起大规模结构化存储集群。HBase利用Hadoop HDFS作为其文件存储系统，利用Hadoop MapReduce来处理HBase中的海量数据，利用Zookeeper作为协调工具。

* 思考：如何存储物流信息

      运单号：625311858890

      2018-01-07 10:09:25	上海			已发货
      2018-01-07 18:27:32	上海航空部		已收件
      2018-01-07 20:25:38	快件离开上海航空部	已发往北京
      2018-01-09 08:27:14	北京回龙观		派件中
      2018-01-09 12:37:37	北京回龙观		已签收

### 二、HBase三要素

* 1、主键：Row Key （主键是用来检索记录的主键，访问hbase table中的行，只有三种方式：）

     * 通过单个row key访问

     * 通过row key的range

     * 全表扫描（scan）

* 2、列族：Column Family

     * 列族在创建表的时候声明，一个列族可以包含多个列，列中的数据都是以二进制形式存在，没有数据类型。

* 3、时间戳：timestamp

     * HBase中通过row和columns确定的为一个存贮单元称为cell。每个 cell都保存着同一份数据的多个版本。版本通过时间戳来索引

### 三、HBASE基础知识

* 1、物理存储
	 
     * able 在行的方向上分割为多个HRegion，一个region由[startkey,endkey)表示，每个HRegion分散在不同的RegionServer中

     <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/物理存储1.png"></div> 

     <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/物理存储2.png"></div>

* 2、



























