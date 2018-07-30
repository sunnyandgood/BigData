# Hive之UDF

* UDF---自定义函数

### 一、UDF简介

* 1、UDF函数可以直接应用于select语句，对查询结构做格式化处理后，再输出内容。

* 2、编写UDF函数的时候需要注意一下几点：

    * a）自定义UDF需要继承import org.apache.hadoop.hive.ql.exec.UDF。

    * b）需要定义并实现evaluate函数。


* 3、步骤

    * a）把程序打包放到目标机器上去；

    * b）进入hive客户端，添加jar包：
    
            hive>add jar /hiveUDF.jar;

    * c）创建临时函数：
    
            hive>create temporary function getType as 'com.edu.hive.udf.HiveUDF';

    * d）查询HQL语句：

            select *,getType(booktype) from book;
            select id,name,getType(booktype) from book;
            

    * e）销毁临时函数：
    
            hive> DROP TEMPORARY FUNCTION getType;
            
            
* 注：UDF只能实现一进一出的操作，如果需要实现多进一出，则需要实现UDAF


### 二、UDF之使用

* 1、创建book分区表，以bookType分区

   * 建表
   
         hive> create table book (id bigint,name string) partitioned by (bookType string) 
                                              row format delimited fields terminated by '\t';
         OK
         Time taken: 0.134 seconds

   * 加载数据
   
         vim book.wlwx
          1       zhaohuanwansui
          2       douluodalu
          3       doupochangqiong
          4       qindi
          5       jiushen
          6       binhuomochu
          7       shanlaingshishen
          8       guangzhizi
          9       tunshixinkong
          10      shenmu
          11      qlqshi
         hive> load data local inpath '/book.wlwx' into table book partition (bookType='wangluowenxue');
          Copying data from file:/book.txt
          Copying file: file:/book.txt
          Loading data to table default.book partition (booktype=wangluowenxue)
          Partition default.book{booktype=wangluowenxue} stats: [numFiles=1, numRows=0, totalSize=148, rawDataSize=0]
          OK
          Time taken: 0.702 seconds   


         vim book.jy
          1       yuwen
          2       shuxue
          3       yingyu
          4       shehui
          5       jisuanji
          6       makeshi
          7       shenwu
         hive> load data local inpath '/book.jy' into table book partition (bookType='jiaoyu');

* 2、Java代码

         package com.edu.hive.udf;

         import java.util.HashMap;
         import java.util.Map;

         import org.apache.hadoop.io.Text;
         import org.apache.hadoop.hive.ql.exec.UDF;

         public class HiveUDF extends UDF{
            private static Map<String, String> map = new HashMap<>();

            static {
               map.put("wangluowenxue", "网络文学");
               map.put("jiaoyu", "课本");
            }

            private Text text = new Text();

            public Text evaluate(Text bookType) {
               String booktype = bookType.toString();
               String value = map.get(booktype);
               if(value == null) {
                  value = "其他";
               }
               text.set(value);
               return text;
            }
         }

* 3、打包上传到虚拟机

* 4、添加jar包（在hive命令行里面执行）

      hive> add jar /hiveUDF.jar;
      Added /hiveUDF.jar to class path
      Added resource: /hiveUDF.jar

* 5、创建临时函数：

      hive> create temporary function getType as 'com.edu.hive.udf.HiveUDF';
      OK
      Time taken: 0.081 seconds

* 6、查询HQL语句：

   * select *,getType(booktype) from book;
   
         hive> select *,getType(booktype) from book;
         Total jobs = 1
         Launching Job 1 out of 1
         Number of reduce tasks is set to 0 since there's no reduce operator
         Starting Job = job_1532966988105_0007, Tracking URL = http://hadoop03:8088/proxy/application_1532966988105_0007/
         Kill Command = /softWare/hadoop-2.2.0/bin/hadoop job  -kill job_1532966988105_0007
         Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 0
         2018-07-30 14:39:28,422 Stage-1 map = 0%,  reduce = 0%
         2018-07-30 14:39:36,079 Stage-1 map = 100%,  reduce = 0%, Cumulative CPU 1.4 sec
         MapReduce Total cumulative CPU time: 1 seconds 400 msec
         Ended Job = job_1532966988105_0007
         MapReduce Jobs Launched: 
         Job 0: Map: 1   Cumulative CPU: 1.4 sec   HDFS Read: 510 HDFS Write: 608 SUCCESS
         Total MapReduce CPU Time Spent: 1 seconds 400 msec
         OK
         1	yuwen	jiaoyu	课本
         2	shuxue	jiaoyu	课本
         3	yingyu	jiaoyu	课本
         4	shehui	jiaoyu	课本
         5	jisuanji	jiaoyu	课本
         6	makeshi	jiaoyu	课本
         7	shenwu	jiaoyu	课本
         1	zhaohuanwansui	wangluowenxue	网络文学
         2	douluodalu	wangluowenxue	网络文学
         3	doupochangqiong	wangluowenxue	网络文学
         4	qindi	wangluowenxue	网络文学
         5	jiushen	wangluowenxue	网络文学
         6	binhuomochu	wangluowenxue	网络文学
         7	shanlaingshishen	wangluowenxue	网络文学
         8	guangzhizi	wangluowenxue	网络文学
         9	tunshixinkong	wangluowenxue	网络文学
         10	shenmu	wangluowenxue	网络文学
         11	qlqshi	wangluowenxue	网络文学
         Time taken: 56.565 seconds, Fetched: 18 row(s)

   * select id,name,getType(booktype) from book;

         hive> select id,name,getType(booktype) from book;
         Total jobs = 1
         Launching Job 1 out of 1
         Number of reduce tasks is set to 0 since there's no reduce operator
         Starting Job = job_1532966988105_0008, Tracking URL = http://hadoop03:8088/proxy/application_1532966988105_0008/
         Kill Command = /softWare/hadoop-2.2.0/bin/hadoop job  -kill job_1532966988105_0008
         Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 0
         2018-07-30 14:43:28,955 Stage-1 map = 0%,  reduce = 0%
         2018-07-30 14:43:36,627 Stage-1 map = 100%,  reduce = 0%, Cumulative CPU 1.39 sec
         MapReduce Total cumulative CPU time: 1 seconds 390 msec
         Ended Job = job_1532966988105_0008
         MapReduce Jobs Launched: 
         Job 0: Map: 1   Cumulative CPU: 1.39 sec   HDFS Read: 510 HDFS Write: 405 SUCCESS
         Total MapReduce CPU Time Spent: 1 seconds 390 msec
         OK
         1	yuwen	课本
         2	shuxue	课本
         3	yingyu	课本
         4	shehui	课本
         5	jisuanji	课本
         6	makeshi	课本
         7	shenwu	课本
         1	zhaohuanwansui	网络文学
         2	douluodalu	网络文学
         3	doupochangqiong	网络文学
         4	qindi	网络文学
         5	jiushen	网络文学
         6	binhuomochu	网络文学
         7	shanlaingshishen	网络文学
         8	guangzhizi	网络文学
         9	tunshixinkong	网络文学
         10	shenmu	网络文学
         11	qlqshi	网络文学
         Time taken: 36.267 seconds, Fetched: 18 row(s)














