# Hive之UDF

* UDF---自定义函数

### 一、UDF简介

* 1、UDF函数可以直接应用于select语句，对查询结构做格式化处理后，再输出内容。

* 2、编写UDF函数的时候需要注意一下几点：

    * a）自定义UDF需要继承import org.apache.hadoop.hive.ql.exec.UDF。

    * b）需要定义并实现evaluate函数。


* 3、步骤

    * a）把程序打包放到目标机器上去；

    * b）进入hive客户端，添加jar包：hive>add jar /run/jar/udf_test.jar;

    * c）创建临时函数：hive>CREATE TEMPORARY FUNCTION add_example AS 'hive.udf.Add';

    * d）查询HQL语句：

            SELECT add_example(8, 9) FROM scores;
            SELECT add_example(scores.math, scores.art) FROM scores;
            SELECT add_example(6, 7, 8, 6.8) FROM scores;

    * e）销毁临时函数：
    
            hive> DROP TEMPORARY FUNCTION add_example;
            
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
















