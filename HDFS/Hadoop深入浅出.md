## Hadoop深入浅出

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/HDFS/img/%E8%87%AA%E5%B7%B1%E8%AE%BE%E8%AE%A1%E4%B8%80%E5%88%86%E5%B8%83%E5%BC%8F%E6%96%87%E4%BB%B6%E7%B3%BB%E7%BB%9F.png"/></div>

### 一、Distributed File System(分布式文件系统)
* 数据量越来越多，在一个操作系统管辖的范围存不下了，那么就分配到更多的操作系统管理的磁盘中，但是不方便管理和维护，因此迫切需要一种系统来管理多台机器上的文件，这就是分布式文件管理系统 。
* 是一种允许文件通过网络在多台主机上分享的文件系统，可让多机器上的多用户分享文件和存储空间。
* **通透性**。让实际上是通过网络来访问文件的动作，由程序与用户看来，就像是访问本地的磁盘一般。
* **容错**。即使系统中有某些节点脱机，整体来说系统仍然可以持续运作而不会有数据损失。
* 分布式文件管理系统很多，hdfs只是其中一种。**适用于一次写入多次查询的情况，不支持并发写情况，小文件不合适**。

### 二、HDFS的Shell
* 调用文件系统(FS)Shell命令应使用 bin/hadoop fs 的形式。
* 所有的FS shell命令使用**URI路径**作为参数。
  * URI格式是`scheme://authority/path`。HDFS的scheme(方案)是hdfs，对本地文件系统，scheme是file。其中scheme和authority(权威)参数都是可选的，如果未加指定，就会使用配置中指定的默认scheme。
  
        例如：/parent/child可以表示成hdfs://namenode:namenodePort/parent/child，
                                或者更简单的/parent/child（假设配置文件是namenode:namenodePort）
 
* 大多数FS Shell命令的行为和对应的Unix Shell命令类似。
