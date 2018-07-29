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

* 2、架构体系

* Client  包含访问hbase 的接口，client 维护着一些cache 来加快对hbase 的访问，比如regione 的位置信息

* Zookeeper
 
 	* 保证任何时候，集群中只有一个running master
 
 	* 存贮所有Region 的寻址入口	
 
 	* 实时监控Region Server 的状态，将Region server 的上线和下线信息，实时通知给Master
 
 	* 存储Hbase 的schema,包括有哪些table，每个table 有哪些column family

* Master 可以启动多个HMaster，通过Zookeeper的Master Election机制保证总有一个Master运行

	* 为Region server 分配region

	* 负责region server 的负载均衡

	* 发现失效的region server 并重新分配其上的region

* 3、HBase的逻辑梳理

   * HBase中有两张特殊的Table，-ROOT-和.META.
	
     * -ROOT- ：记录了.META.表的Region信息，-ROOT-只有一个region
		
     * .META. ：记录了用户创建的表的Region信息，.META.可以有多个regoin

    * Zookeeper中记录了-ROOT-表的location

    * Client访问用户数据之前需要首先访问zookeeper，然后访问-ROOT-表，接着访问.META.表，最后才能找到用户数据的位置去访问

    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/table.png"/></div>

### 四、Region Server

* 维护Master 分配给它的region，处理对这些region 的IO 请求

* 负责切分在运行过程中变得过大的region

可以看出，client 访问hbase 上数据的过程并不需要master 参与，寻址访问先zookeeper再regionserver，数据读写访问regioneserver。HRegionServer主要负责响应用户I/O请求，向HDFS文件系统中读写数据，是HBase中最核心的模块。 	

### 五、系统架构

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/系统架构.png"></div>

### 六、HBASE与相关软件

* hadoop-2.2.0

* hbase-0.96.2-hadoop2

* JDK7

* RHEL6.3

* 前提条件：本机或集群环境下hadoop.1.1.2已经安装成功

### 七、HBase Shell

* hbase提供了一个shell的终端给用户交互（#$HBASE_HOME/bin/hbase shell  ）

<table>
   <tr>
      <td>名称</td>
      <td>命令表达式</td>
   </tr>
   <tr>
      <td>创建表</td>
      <td>create '表名称', '列族名称1','列族名称2','列族名称N'</td>
   </tr>
   <tr>
      <td>添加记录      </td>
      <td>put '表名称', '行名称', '列名称:', '值'</td>
   </tr>
   <tr>
      <td>查看记录</td>
      <td>get '表名称', '行名称'</td>
   </tr>
   <tr>
      <td>查看表中的记录总数</td>
      <td>count  '表名称'</td>
   </tr>
   <tr>
      <td>删除记录</td>
      <td>delete  '表名' ,'行名称' , '列名称'</td>
   </tr>
   <tr>
      <td>删除一张表</td>
      <td>先要屏蔽该表，才能对该表进行删除，第一步 disable '表名称' 第二步  drop '表名称'</td>
   </tr>
   <tr>
      <td>查看所有记录</td>
      <td>scan "表名称"  </td>
   </tr>
   <tr>
      <td>查看某个表某个列中所有数据</td>
      <td>scan "表名称" , {COLUMNS=>'列族名称:列名称'}</td>
   </tr>
   <tr>
      <td>更新记录 </td>
      <td>就是重写一遍进行覆盖</td>
   </tr>
</table>

### 八、HBASE Shell的DDL操作（数据库操作语言））

* 1、创建表

		>create 'users','user_id','address','info'
		表users,有三个列族user_id,address,info

* 2、列出全部表

		>list 

* 3、得到表的描述

		>describe 'users'

* 创建表

		>create 'users_tmp','user_id','address','info'

* 删除表

		>disable 'users_tmp'
		>drop 'users_tmp'

-----------------------------------------------------------------
* 其他操作

		#$HBASE_HOME/bin/hbase shell
		…… 
		>quit 

		>exists 'users'
		>is_enabled 'users'
		>is_disabled 'users



















