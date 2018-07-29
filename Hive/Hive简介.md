# Hive简介


* Hive是什么？其体系结构简介

* Hive的安装与管理

* HiveQL数据类型，表以及表的操作

* HiveQL查询数据

* Hive的Java客户端

---------------------------加深拓展----------------------

* Hive的自定义函数UDF

### 一、什么是Hive

* Hive 是建立在 Hadoop  上的数据仓库基础构架。它提供了一系列的工具，可以用来进行数据提取转化加载（ETL ），这是一种可以存储、查询和分析存储在 Hadoop  中的大规模数据的机制。Hive 定义了简单的类 SQL  查询语言，称为 QL ，它允许熟悉 SQL  的用户查询数据。同时，这个语言也允许熟悉 MapReduce  开发者的开发自定义的 mapper  和 reducer  来处理内建的 mapper 和 reducer  无法完成的复杂的分析工作。

* Hive是SQL解析引擎，它将SQL语句转译成M/R Job然后在Hadoop执行。

* Hive的表其实就是HDFS的目录/文件夹，按表名把文件夹分开。如果是分区表，则分区值是子文件夹，可以直接在M/R Job里使用这些数据。

* 总结：

### 二、Hive的系统架构

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/Hive/img/Hive的系统架构.png"/></div>

* 用户接口，包括 CLI，JDBC/ODBC，WebUI

* 元数据存储，通常是存储在关系数据库如 mysql, derby 中

* 解释器、编译器、优化器、执行器

* Hadoop：用 HDFS 进行存储，利用 MapReduce 进行计算

















