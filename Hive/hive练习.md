# hive练习

### 一、内部表

* 建一个heros表

      create table heros (id bigint,name string,age int,height double) row 
                                                            format delimited fields terminated by '\t';

* 上传文件到heros目录下
      
      vim heros.txt
      1       gailun  56      180.6
      2       timo    34      120.3
      3       taitan  89      200.5
      vim heros.avi
      4       jiqi    900     170.9
      5       dachongzi       200     300.8

      [root@hadoop03 /]# hdfs dfs -put /heros.txt /heros.avi /user/hive/warehouse/heros/

* 查看

      hive> select * from heros;           
      OK
      4	jiqi	900	170.9
      5	dachongzi	200	300.8
      1	gailun	56	180.6
      2	timo	34	120.3
      3	taitan	89	200.5
      Time taken: 0.135 seconds, Fetched: 5 row(s)

      hive> select name,height,age from heros order height desc;
      ........................
      Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 1
      2018-07-29 18:40:29,391 Stage-1 map = 0%,  reduce = 0%
      2018-07-29 18:41:16,351 Stage-1 map = 100%,  reduce = 0%, Cumulative CPU 27.53 sec
      2018-07-29 18:41:26,049 Stage-1 map = 100%,  reduce = 100%, Cumulative CPU 29.29 sec
      MapReduce Total cumulative CPU time: 29 seconds 290 msec
      Ended Job = job_1532882569615_0002
      MapReduce Jobs Launched: 
      Job 0: Map: 1  Reduce: 1   Cumulative CPU: 29.29 sec   HDFS Read: 354 HDFS Write: 81 SUCCESS
      Total MapReduce CPU Time Spent: 29 seconds 290 msec
      OK
      dachongzi	300.8	200
      taitan	200.5	89
      gailun	180.6	56
      jiqi	170.9	900
      timo	120.3	34
      Time taken: 83.914 seconds, Fetched: 5 row(s)


### 二、外部表

* 建一个heros2表指向'/sqoop/td2'

      hive> create external table heros2 (id string,name string,weizhi string,age int) 
                row format delimited fields terminated by '\t' location '/sqoop/td2';
      OK
      Time taken: 0.242 seconds
      hive> select * from heros2;
      OK
      Time taken: 0.084 seconds

* 上传文件到'/sqoop/td2'目录下

      [root@hadoop03 /]# hdfs dfs -put /heros.txt /heros.avi /sqoop/td2/

* 查看

      hive> select * from heros2;
      OK
      4	jiqi	900	170
      5	dachongzi	200	300
      1	gailun	56	180
      2	timo	34	120
      3	taitan	89	200
      Time taken: 0.092 seconds, Fetched: 5 row(s)

### 三、分区表













