# Zookeeper概述

### 一、什么是Zookeeper？
* Zookeeper 是 Google 的 Chubby一个开源的实现，是 Hadoop 的分布式协调服务

* 它包含一个简单的原语集，分布式应用程序可以基于它实现**同步服务**，配置维护和命名服务等

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/Zookeeper/img/zookeeper1.png"/></div>

>* **注**：Zookeeper 服务必须得有一半以上的服务器运行，只有一个leader服务器

### 二、为什么使用Zookeeper？

* 大部分分布式应用需要一个主控、协调器或控制器来管理物理分布的子进程（如资源、任务分配等）

* 目前，大部分应用需要开发私有的协调程序，缺乏一个通用的机制

* 协调程序的反复编写浪费，且难以形成通用、伸缩性好的协调器

* ZooKeeper：提供通用的分布式锁服务，用以协调分布式应用

### 三、Zookeeper能帮我们做什么？

* Hadoop2.0,使用Zookeeper的事件处理确保整个集群只有一个活跃的NameNode,存储配置信息等.

* HBase,使用Zookeeper的事件处理确保整个集群只有一个HMaster,察觉HRegionServer联机和宕机,存储访问控制列表等.

### 四、Zookeeper的特性

* Zookeeper是简单的

* Zookeeper是富有表现力的

* Zookeeper具有高可用性

* Zookeeper采用松耦合交互方式

* Zookeeper是一个资源库
