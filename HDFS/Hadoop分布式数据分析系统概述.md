## Hadoop分布式数据分析系统概述

### 一、Hadoop能为我们做什么！

* 1、什么是大数据 

  大数据（big data），指无法在一定时间范围内用常规软件工具进行捕捉、管理和处理的数据集合，是需要新处理模式才能具有更强的决策力、洞察发现力和流程优化能力的海量、高增长率和多样化的信息资产。
* 2、大数据能用来做什么(如何挖掘企业需要的信息！)
    * 1>销售行业
        * 什么类别的商品销售好，什么时段销售业绩高，根据分析淘汰销售不佳的商品或增加销售好的商品的进货。
    * 2>生产行业
        * 生产来源于市场需求，研发新商品淘汰滞销商品。
    * 3>高校
        * 就业率可以过滤专业或减少招生量。
    * 4>医药行业
        * 改变药品的种类和数量，决定药品的上架或下架。
    * 5>医院
        * 一种病的患病率上升还是下降，制定策略。


* 3、通常的关系数据库和大数据的联系和瓶颈 
    * 1>数据的存储介质
        * 龟甲、竹片、纸张、Word、Excel、关系数据库。
    * 2>关系数据库对于大量数据的读写
        * 在数据库服务器上配置RAID磁盘阵列，这样能提高磁盘的访问性能，并能够实现容错/容灾。
    * 3>RAID磁盘阵列可以提供数据库的读写速度，但是通常是在同一台服务器上配置，如果采用服务器集群，读写的速度也会受到影响。
    * 4>存储的数据中有企业需要的数据信息
    * 5>对于数据信息的数量，通过数据库的检索功能则需要很长的时间，效率和数据量成正比。

* 4、海量数据的产生是大数据分析系统产生的必然 
    * 1>计算机和数据库的使用积累了大量的数据。
    * 2>关系数据库对大量数据的处理瓶颈。 
    * 3>数据库中的数据只能保存到历史表中，几乎成了垃圾数据。
    * 4>但是大量的历史信息中隐藏着重要的信息确很难发现。
    * 5>企业急需这些信息改变经营策略，以至于不被市场淘汰。
    * 6>谁先对这些垃圾数据进行分析，谁将首先抢占市场或称为行业领导者。
    * 7>网络的发展渗透到各个行业，通常的策略已经称为公开的秘密。
    * 8>沉睡在历史表中的信息将被唤醒。

### 二、Hadoop的起源 
* Hadoop是一个由Apache基金会开发的一个开源项目，从2005 年开始，源于开源的收索引擎项目Nutch的一部分正式引入。Nutch项目的负责人是Doug Cutting，也是开发组的核心人员。

* Hadoop系统结构受到Google开发的Map/Reduce和Google File System (GFS) 的启发。

* 2006年，Map/Reduce和Nutch Distributed File System (NDFS)分别被纳入称为Hadoop的项目中。 

* 最初Hadoop 是在 Internet 上对搜索关键字进行内容分类的工具，但是Hadoop对海量数据处理的能力备受关注。例如，如果您要对一个TB级别的文件进行关键字的出现次数进行统计，会出现什么情况！在传统的关系数据库系统上，这将需要很长的时间。但是 Hadoop 在设计时就考虑到这些问题，**采用分布并行执行机制**，因此能大大提高效率。 

### 三、Hadoop框架的组成
>HDFS(Hadoop Distributed File System)，Hadoop分布式文件系统。

>Map/Reduce分布式计算系统


<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop%E6%A1%86%E6%9E%B6%E7%9A%84%E7%BB%84%E6%88%90.png"/></div>

* 1、什么是HDFS分布式文件系统
    * HDFS是一个的文件系统，可以创建、移动、删除和重命名文件。
    
    * 是一个基于网络的分布式文件系统。
    
    * 由多个机器联网组成，其中一台为NameNode，其他的是DataNode。
    
    * NameNode为HDFS提供元数据服务，管理保存在不同节点上的数据信息。
    
    * DataNode管理本节点上的数据。
    
    * NameNode将一个大的数据文件分成多个数据块保存到不同的DataNode节点上，并记录所有数据块所在节点的信息，DataNode管理本节点上的数据块，数据块的大小默认为128M。
    
    * HDFS上NameNode和DataNode通信基于TCP/IP协议
    
* 2、HDFS(分布式文件系统)

    * HDFS分布式文件系统是基于网络的。
    
    * 是由多台机器组成的。
    
    * 是由一个NameNode和多个DataNode组成，NameNode运行在主服务器上，管理文件系统的命名空间和客户端对文件系统的访问操作。DataNode运行在其他的多台机器上，主要管理数据的存储，包括要分析的数据，历史数据等。
    
    * 在主服务器上的NameNode是HDFS分布式文件系统的主守护进程，分布在其他机器上的DataNode从进程定时向主进程NameNode发送报告，报告节点数据和磁盘的情况。NameNode进程向不同的DataNode节点发送指令，要求节点下载数据并管理数据。
    
    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/HDFS%E7%BB%93%E6%9E%84%E5%9B%BE.png"/></div>
    
 * 3、什么是MapReduce(分布式计算系统)
 
    * MapReduce是处理数据的编程模型。
    
    * 关系数据库可以采用SQL语句对数据进行操作查询。
    
    * MapReduce可以通过Java、C等其他语言对数据进行查询分析。
    
    * MapReduce分为两个阶段，map阶段和reduce阶段，map阶段将原始数据进行过滤操作，以键/值对的方式输出，map阶段的输出是reduce阶段的输入，reduce阶段对数据处理后输出最终的结果。
    
    * MapReduce处理的数据文件保存在HDFS上，并且最终的计算结果同样会保存到HDFS上。
    
    * MapReduce和HDFS相对独立由相互联系。
    
* 4、MapReduce(分布式计算系统)

    * Hadoop1.0版本的MapReduce
    
        * 由一个JobTracker和多个TaskTracker组成。 JobTracker运行在主服务器上， TaskTracker运行在集群中的从节点上。
        
        * JobTracker进程负责调度一个作业的所有任务的执行，这些任务会分配到不同的节点上。JobTracker主进程监控所有从节点的TaskTracker进程的作业执行情况，重新执行失败的任务。TaskTracker执行JobTracker指定的任务。当一个Job(数据分析作业)提交时，JobTracker接收到提交的任作业后，将作业执行需要的配置信息和其他数据信息分发给相应的TaskTracker。同时要调度任务并监控TaskTracker的执行。
        
    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop1.0%E7%89%88%E6%9C%AC%E7%9A%84MapReduce%E7%BB%93%E6%9E%84%E5%9B%BE.png"/></div>

* 5、YARN(分布式计算系统)

    * Hadoop2.0版本的MapReduceV2(YARN)
    
    * YARN ( Yet Another Resource Negotiator )
    
        * 由一个ResourceManager和多个NodeManager组成。 ResourceManager运行在主服务器上， NodeManager运行在集群中的从节点上。
        
        * ResourceManager是集群所有可用资源的仲裁者。是一个单纯的资源控制器和调度器。主要职责是接收应用程序的资源请求并严格控制系统的可用资源。动态的分配资源。 
        
        * NodeManager是集群中每个节点上的管理进程，职责是对节点中的资源进行管理并与ResourceManager保持通信，报告节点的各种状态信息。与ResourceManager共同管理整个集群资源，资源包括内存、CPU等。

    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop2.0%E7%89%88%E6%9C%AC%E7%9A%84MapReduceV2(YARN)%E7%BB%93%E6%9E%84%E5%9B%BE.png"/></div>

* 6、Hadoop2.0在Hadoop1.0上的改变

    * YARN将JobTracker分为两个组件：ResourceManager和ApplicationMaster。ResourceManager作为资源管理和调度不需要关心应用程序的执行和监控。而与应用程序相关的是ApplicationMaster。
    
    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop2.0%E5%9C%A8Hadoop1.0%E4%B8%8A%E7%9A%84%E6%94%B9%E5%8F%98.png"/></div>
    
    * ResourceManager进程运行在主节点上，作为集群资源的管理者和总调度，不在需要关心应用程序的执行和监控。ApplicationMaster进程运行在从节点上，主要管理应用程序的执行和应用程序的生命周期，当应用程序执行结束，ApplicationMaster的生命周期结束。

    * 客户端发出应用程序执行请求，ResourceManager会创建与应用程序对应的ApplicationMaster实例。

    * 好处是，分工明确，粒度细化和多应用程序的同步执行。
    
    * 不同的应用对应不同的ApplicationMaster。MapReduce计算框架对应相应的ApplicationMaster。不同的计算框架对应不同的ApplicationMaster实现。

    * 好处是：职责的分离带来的是扩展性的提升。如：程序在运行阶段发生异常带来的节点任务失败是需要重新启动作业的，这些相关的容错机制在Hadoop1.0是由JobTracker负责的。使得JobTracker成为重量级，代码的维护相当困难，并且框架的耦合度高，功能模块粒度粗。在YARN中这些容错职责由ApplicationMaster担任，实现了容错机制局部化，而不是以往的全局管理。此外需要注意的是应用程序和ResourceManager的分离使ResourceManager轻量化。某个应用的执行不会影响到整个集群。

    * Hadoop2.0和Hadoop1.0的结构图
    
    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop2.0%E5%92%8CHadoop1.0%E7%9A%84%E7%BB%93%E6%9E%84%E5%9B%BE1.png"/></div>
    
    * 不同应用对应不同的ApplicationMaster。只需要增加不同计算框架的ApplicationMaster实现即可实现多种框架使用YARN，如MapReduce、MPI和图计算等。实现上是把所有与应用程序相关的代码都放到ApplicationMaster类中。并提供不同的接口满足不同用户的需求。 

        <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop2.0%E5%92%8CHadoop1.0%E7%9A%84%E7%BB%93%E6%9E%84%E5%9B%BE2.png"/></div>
  

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/Hadoop%E7%94%9F%E6%80%81%E7%B3%BB%E7%BB%9F%E5%9B%BE.png"/></div>
