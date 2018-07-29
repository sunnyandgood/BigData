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

* 总结：Hive的表对应HDFS的目录（或文件夹）；Hive表中的数据对应HDFS的文件。

### 二、Hive的系统架构(一)

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/Hive/img/Hive的系统架构.png"/></div>

* 用户接口，包括 CLI，JDBC/ODBC，WebUI

* 元数据存储，通常是存储在关系数据库如 mysql, derby 中

* 解释器、编译器、优化器、执行器

* Hadoop：用 HDFS 进行存储，利用 MapReduce 进行计算

### 三、Hive的系统架构（二）

* 用户接口主要有三个：CLI，JDBC/ODBC和 WebUI

    * CLI，即Shell命令行

    * JDBC/ODBC 是 Hive 的Java，与使用传统数据库JDBC的方式类似

    * WebGUI是通过浏览器访问 Hive

* Hive 将元数据存储在数据库中(metastore)，目前只支持 mysql、derby。Hive 中的元数据包括表的名字，表的列和分区及其属性，表的属性（是否为外部表等），表的数据所在目录等

* 解释器、编译器、优化器完成 HQL 查询语句从词法分析、语法分析、编译、优化以及查询计划（plan）的生成。生成的查询计划存储在 HDFS 中，并在随后有 MapReduce 调用执行

* Hive 的数据存储在 HDFS 中，大部分的查询由 MapReduce 完成（包含 * 的查询，比如 select * from table 不会生成 MapRedcue 任务）

### 四、Hive的运行模式

* Hive的运行模式即任务的执行环境

* 分为本地与集群两种

   * 我们可以通过mapred.job.tracker 来指明

         设置方式：
         hive > SET mapred.job.tracker=local

### 五、Hive的启动方式

* 1、hive 命令行模式，直接输入#/hive/bin/hive的执行程序，或者输入 #hive --service cli 

* 2、 hive web界面的 (端口号9999) 启动方式

      #hive --service hwi &
      用于通过浏览器来访问hive
      http://hadoop0:9999/hwi/

* 3、 hive 远程服务 (端口号10000) 启动方式

      #hive --service hiveserver &

### 六、Hive与传统数据库

<table>
   <tr>
      <td>查询语言</td>
      <td>HiveQL</td>
      <td>SQL</td>
   </tr>
   <tr>
      <td>数据存储位置</td>
      <td>HDFS</td>
      <td>Raw Device or 本地FS</td>
   </tr>
   <tr>
      <td>数据格式</td>
      <td>用户定义</td>
      <td>系统决定</td>
   </tr>
   <tr>
      <td>数据更新</td>
      <td>不支持</td>
      <td>支持</td>
   </tr>
   <tr>
      <td>索引</td>
      <td>新版本有，但弱</td>
      <td>有</td>
   </tr>
   <tr>
      <td>执行</td>
      <td>MapReduce</td>
      <td>Executor</td>
   </tr>
   <tr>
      <td>执行延迟</td>
      <td>高</td>
      <td>低</td>
   </tr>
   <tr>
      <td>可扩展性</td>
      <td>高</td>
      <td>低</td>
   </tr>
   <tr>
      <td>数据规模</td>
      <td>大</td>
      <td>小</td>
   </tr>
</table>

### 七、Hive的数据类型

* 基本数据类型

      tinyint/smallint/int/bigint
      float/double
      boolean
      string

* 复杂数据类型

      Array/Map/Struct
      没有date/datetime

### 八、Hive的数据存储

* Hive的数据存储基于Hadoop HDFS

* Hive没有专门的数据存储格式

* 存储结构主要包括：数据库、文件、表、视图

* Hive默认可以直接加载文本文件（TextFile），还支持sequence file 、RC file

* 创建表时，指定Hive数据的列分隔符与行分隔符，Hive即可解析数据

### 九、Hive的数据模型-数据库

* 类似传统数据库的DataBase

* 默认数据库"default"

   * 使用#hive命令后，不使用hive>use <数据库名>，系统默认的数据库。可以显式使用hive> use default;

* 创建一个新库

      hive > create database test_dw;

### 十、Hive的数据模型-表

* Table 内部表

* Partition  分区表

* External Table 外部表

* Bucket  Table 桶表 

### 十一、Hive的数据模型-内部表

* 与数据库中的 Table 在概念上是类似

* 每一个 Table 在 Hive 中都有一个相应的目录存储数据。例如，一个表 test，它在 HDFS 中的路径为：/ warehouse/test。 warehouse是在 hive-site.xml 中由 ${hive.metastore.warehouse.dir} 指定的数据仓库的目录

* 所有的 Table 数据（不包括 External Table）都保存在这个目录中。

* 删除表时，元数据与数据都会被删除

* 内部表的使用

   * 创建数据文件inner_table.dat

   * 创建表
   
         hive>create table inner_table (key string);

   * 加载数据
   
         hive>load data local inpath '/root/inner_table.dat' into table inner_table;

   * 查看数据
   
         select * from inner_table
         select count(*) from inner_table

   * 删除表 
   
         drop table inner_table


### 十二、Hive的数据模型-分区表

* Partition 对应于数据库的 Partition 列的密集索引

* 在 Hive 中，表中的一个 Partition 对应于表下的一个目录，所有的 Partition 的数据都存储在对应的目录中

      例如：test表中包含 date 和 city 两个 Partition，
      则对应于date=20180729, city = bj 的 HDFS 子目录为：
      /warehouse/test/date=20130201/city=bj
      对应于date=20180729, city=sh 的HDFS 子目录为；
      /warehouse/test/date=20180729/city=sh

* 分区表

      CREATE TABLE tmp_table #表名
      (
      title   string, # 字段名称 字段类型
      minimum_bid     double,
      quantity        bigint,
      have_invoice    bigint
      )COMMENT '注释：XXX' #表注释
       PARTITIONED BY(pt STRING) #分区表字段（如果你文件非常之大的话，采用分区表可以快过滤出按分区字段划分的数据）
       ROW FORMAT DELIMITED 
         FIELDS TERMINATED BY '\001'   # 字段是用什么分割开的
      STORED AS SEQUENCEFILE; #用哪种方式存储数据，SEQUENCEFILE是hadoop自带的文件压缩格式

* 一些相关命令

      SHOW TABLES; # 查看所有的表
      SHOW TABLES '*TMP*'; #支持模糊查询
      SHOW PARTITIONS TMP_TABLE; #查看表有哪些分区
      DESCRIBE TMP_TABLE; #查看表结构

* 分区表的使用

   * 创建数据文件partition_table.dat

   * 创建表
   
         create table partition_table(rectime string,msisdn string) partitioned by(daytime string,city string)
         row format delimited fields terminated by '\t' stored as TEXTFILE;

   * 加载数据到分区
   
         load data local inpath '/home/partition_table.dat' into table partition_table partition
         (daytime='2013-02-01',city='bj');

   * 查看数据
   
         select * from partition_table
         select count(*) from partition_table

   * 删除表 
   
         drop table partition_table

   * 通过load data 加载数据
   
         alter table partition_table add partition (daytime='2018-07-29',city='bj');

   * 元数据，数据文件删除，但目录daytime=2013-02-04还在
   
         alter table partition_table drop partition (daytime='2018-07-29',city='bj')

### 十三、Hive的数据模型—桶表

* 桶表是对数据进行哈希取值，然后放到不同文件中存储。

* 创建表
	
         create table bucket_table(id string) clustered by(id) into 4 buckets;		

* 加载数据
	
         set hive.enforce.bucketing = true;
         insert into table bucket_table select name from stu;	
         insert overwrite table bucket_table select name from stu;

* 数据加载到桶表时，会对字段取hash值，然后与桶的数量取模。把数据放到对应的文件中。


* 抽样查询
	
         select * from bucket_table tablesample(bucket 1 out of 4 on id);

### 十四、Hive的数据模型-外部表

* 指向已经在 HDFS 中存在的数据，可以创建 Partition

* 它和 内部表 在元数据的组织上是相同的，而实际数据的存储则有较大的差异

* 内部表 的创建过程和数据加载过程（这两个过程可以在同一个语句中完成），在加载数据的过程中，实际数据会被移动到数据仓库目录中；之后对数据对访问将会直接在数据仓库目录中完成。删除表时，表中的数据和元数据将会被同时删除

* 外部表 只有一个过程，加载数据和创建表同时完成，并不会移动到数据仓库目录中，只是与外部数据建立一个链接。当删除一个 外部表 时，仅删除该链接

* 外部表

         CREATE EXTERNAL TABLE page_view
         ( viewTime INT, 
           userid BIGINT,
           page_url STRING, 	
          referrer_url STRING, 							
           ip STRING COMMENT 'IP Address of the User',
           country STRING COMMENT 'country of origination‘
         )
             COMMENT 'This is the staging page view table'
             ROW FORMAT DELIMITED FIELDS TERMINATED BY '44' LINES 	TERMINATED BY '12'
             STORED AS TEXTFILE
             LOCATION 'hdfs://centos:9000/user/data/staging/page_view';

* 外部表的使用

   * 创建数据文件external_table.dat

   * 创建表
   
            hive>create external table external_table1 (key string) ROW FORMAT DELIMITED 
                                   FIELDS TERMINATED BY '\t' location '/home/external';
            在HDFS创建目录/home/external
            #hadoop fs -put /home/external_table.dat /home/external

   * 加载数据

            LOAD DATA INPATH '/home/external_table1.dat' INTO TABLE external_table1;

   * 查看数据

            select * from external_table
            select count(*) from external_table

   * 删除表 

            drop table external_table

### 十五、视图操作

* 视图的创建

      CREATE VIEW v1 AS select * from t1;

### 十六、表的操作

* 表的修改

      alter table target_tab add columns (cols,string)

* 表的删除

      drop table

### 十七、导入数据

* 当数据被加载至表中时，不会对数据进行任何转换。Load 操作只是将数据复制/移动至 Hive 表对应的位置。

      LOAD DATA [LOCAL] INPATH 'filepath' [OVERWRITE]   
            INTO TABLE tablename    
            [PARTITION (partcol1=val1, partcol2=val2 ...)]

* 把一个Hive表导入到另一个已建Hive表

      INSERT OVERWRITE TABLE tablename [PARTITION (partcol1=val1, 
            partcol2=val2 ...)] select_statement FROM from_statement

* CTAS

      CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name 
         (col_name data_type, ...)	…
         AS SELECT …

* 例：create table new_external_test as  select * from external_table1;

### 十八、查询（select）

	SELECT [ALL | DISTINCT] select_expr, select_expr, ...
	   FROM table_reference 
	   [WHERE where_condition] 
	   [GROUP BY col_list] 
	   [ CLUSTER BY col_list | [DISTRIBUTE BY col_list] [SORT BY col_list] | [ORDER BY col_list] ]
	   [LIMIT number]

* 注：DISTRIBUTE BY 指定分发器（Partitioner）,多Reducer可用

* 基于Partition的查询  

	* 一般 SELECT 查询是全表扫描。但如果是分区表，查询就可以利用分区剪枝（input pruning）的特性，类似“分区索引“”，只扫描一个表中它关心的那一部分。Hive 当前的实现是，只有分区断言（Partitioned by）出现在离 FROM 子句最近的那个WHERE 子句中，才会启用分区剪枝。例如，如果 page_views 表（按天分区）使用 date 列分区，以下语句只会读取分区为‘2018-03-01’的数据。

			 SELECT page_views.*  FROM page_views  WHERE page_views.date >= '2018-03-01' 
					       AND page_views.date <= '2018-03-01'

* LIMIT Clause 

	* Limit 可以限制查询的记录数。查询的结果是随机选择的。下面的查询语句从 t1 表中随机查询5条记录：
	
			SELECT * FROM t1 LIMIT 5

* Top N查询

	* 下面的查询语句查询销售记录最大的 5 个销售代表。
	
			SET mapred.reduce.tasks = 1  
				    SELECT * FROM sales SORT BY amount DESC LIMIT 5

### 十九、表连接

* 导入ac信息表

		hive> create table acinfo (name string,acip string)  row format 
				delimited fields terminated by '\t' stored as TEXTFILE;
		hive> load data local inpath '/home/acinfo/ac.dat' into table acinfo; 

* 内连接

		select b.name,a.* from dim_ac a join acinfo b on (a.ac=b.acip) limit 10;

* 左外连接

		select b.name,a.* from dim_ac a left outer join acinfo b on a.ac=b.acip limit 10;

### 二十、Java客户端

* Hive远程服务启动#hive --service hiveserver >/dev/null  2>/dev/null &

* JAVA客户端相关代码

		Class.forName("org.apache.hadoop.hive.jdbc.HiveDriver");
		Connection con = DriverManager.getConnection("jdbc:hive://192.168.1.102:10000/wlan_dw", "", "");
		Statement stmt = con.createStatement();
		String querySQL="SELECT * FROM wlan_dw.dim_m order by flux desc limit 10";

		ResultSet res = stmt.executeQuery(querySQL);  

		while (res.next()) {
		System.out.println(res.getString(1) +"\t" +res.getLong(2)+"\t" +
			res.getLong(3)+"\t" +res.getLong(4)+"\t" +res.getLong(5));
		}

### 二十一、UDF

* 1、UDF函数可以直接应用于select语句，对查询结构做格式化处理后，再输出内容。

* 2、编写UDF函数的时候需要注意一下几点：

	* a）自定义UDF需要继承org.apache.hadoop.hive.ql.UDF。

	* b）需要实现evaluate函数，evaluate函数支持重载。


* 3、步骤

	* a）把程序打包放到目标机器上去；

	* b）进入hive客户端，添加jar包：hive>add jar /run/jar/udf_test.jar;

	* c）创建临时函数：hive>CREATE TEMPORARY FUNCTION add_example AS 'hive.udf.Add';

	* d）查询HQL语句：

		SELECT add_example(8, 9) FROM scores;
		SELECT add_example(scores.math, scores.art) FROM scores;
		SELECT add_example(6, 7, 8, 6.8) FROM scores;

	* e）销毁临时函数：hive> DROP TEMPORARY FUNCTION add_example;
	
* 注：UDF只能实现一进一出的操作，如果需要实现多进一出，则需要实现UDAF

### 二十二、为什么选择Hive？

* 基于Hadoop的大数据的计算/扩展能力

* 支持SQL like查询语言

* 统一的元数据管理

* 简单编程

### 二十三、总结

* MapReduce程序计算KPI

* HBASE详单查询

* HIVE数据仓库多维分析

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/Hive/img/Hive总结.png"/></div>









