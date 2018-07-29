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

* 统计heros表中单词数量

      cd /softWare/hadoop-2.2.0/share/hadoop/mapreduce
      hadoop jar hadoop-mapreduce-examples-2.2.0.jar wordcount /user/hive/warehouse/heros /heroswcout

* 删除heros表(表连数据全删除了)

      hive> drop table heros; 
      OK
      Time taken: 1.166 seconds

### 二、外部表(在/user/hive/warehouse目录下不显示，只知道其路径)

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
      
      
      hive> select sum(age) from heros2;
      Total jobs = 1
      Launching Job 1 out of 1
      Number of reduce tasks determined at compile time: 1
      In order to change the average load for a reducer (in bytes):
        set hive.exec.reducers.bytes.per.reducer=<number>
      In order to limit the maximum number of reducers:
        set hive.exec.reducers.max=<number>
      In order to set a constant number of reducers:
        set mapreduce.job.reduces=<number>
      Starting Job = job_1532882569615_0003, Tracking URL = http://hadoop03:8088/proxy/application_1532882569615_0003/
      Kill Command = /softWare/hadoop-2.2.0/bin/hadoop job  -kill job_1532882569615_0003
      Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 1
      2018-07-29 19:25:28,595 Stage-1 map = 0%,  reduce = 0%
      2018-07-29 19:25:43,162 Stage-1 map = 100%,  reduce = 0%, Cumulative CPU 3.9 sec
      2018-07-29 19:25:53,029 Stage-1 map = 100%,  reduce = 100%, Cumulative CPU 5.14 sec
      MapReduce Total cumulative CPU time: 5 seconds 140 msec
      Ended Job = job_1532882569615_0003
      MapReduce Jobs Launched: 
      Job 0: Map: 1  Reduce: 1   Cumulative CPU: 5.14 sec   HDFS Read: 322 HDFS Write: 4 SUCCESS
      Total MapReduce CPU Time Spent: 5 seconds 140 msec
      OK
      970
      Time taken: 43.236 seconds, Fetched: 1 row(s)

* 统计heros表中单词数量

      cd /softWare/hadoop-2.2.0/share/hadoop/mapreduce
      hadoop jar hadoop-mapreduce-examples-2.2.0.jar wordcount /sqoop/td2 /sqooptd2wcout

* 删除heros2表（仅删除表，不删除数据）

      hive> drop table heros2;
      OK
      Time taken: 0.186 seconds
      
### 三、分区表













