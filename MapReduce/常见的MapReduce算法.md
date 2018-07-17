# 常见的MapReduce算法

## 一、Shuffle

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/MapReduce/img/Shuffle.png"/></div>


* 每个map有一个环形内存缓冲区，用于存储任务的输出。默认大小100MB（io.sort.mb属性），一旦达到阀值0.8（io.sort.spill.percent）,一个后台线程把内容写到(spill)磁盘的指定目录（mapred.local.dir）下的新建的一个溢出写文件。

* 写磁盘前，要partition,sort。如果有combiner，combine排序后数据。

* 等最后记录写完，合并全部溢出写文件为一个分区且排序的文件。

* Reducer通过Http方式得到输出文件的分区。

* TaskTracker为分区文件运行Reduce任务。复制阶段把Map输出复制到Reducer的内存或磁盘。一个Map任务完成，Reduce就开始复制输出。

* 排序阶段合并map输出。然后走Reduce阶段。


## 二、Codec为压缩，解压缩的算法实现。 

* 在Hadoop中，codec由CompressionCode的实现来表示。下面是一些实现：

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/MapReduce/img/Codec_压缩%EF%BC%8C解压缩的算法实现.png"/></div>

* 输出的压缩属性：

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/MapReduce/img/输出的压缩属性.png"/></div>

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/MapReduce/img/输出的压缩属性代码实现.png"/></div>

## 三、MapReduce常见算法

* 单词计数

* 数据去重

* 排序

* Top K

* 选择

* 投影

* 分组

* 多表连接

* 单表关联

## 四、思考题

* 如何使用计数器

* Combiner的作用是什么，应用场景是什么

* Partitioner的作用是什么，应用场景是什么

* Shuffler的过程是什么

