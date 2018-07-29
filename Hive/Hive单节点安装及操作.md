# Hive单节点安装及操作

### 一、上传

* [获得apache-hive-0.13.0-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/Hive/apache-hive-0.13.0-bin.tar.gz)

* 上传

### 二、安装Hive

* 解压apache-hive-0.13.0-bin.tar.gz

      tar -zxvf apache-hive-0.13.0-bin.tar.gz -C /softWare/

### 三、使用Hive

* 启动Hive

      cd /softWare/apache-hive-0.13.0-bin/bin
      [root@hadoop03 bin]# ./hive

* show tables

      hive> show tables;
      OK
      Time taken: 0.061 seconds

* show databases

      hive> show databases;
      OK
      default
      Time taken: 0.042 seconds, Fetched: 1 row(s)

* use default(数据库名)

      hive> use default;
      OK
      Time taken: 0.027 seconds

* create table students (s_id int,s_name string)

      hive> create table students (s_id int,s_name string);
      OK
      Time taken: 0.536 seconds
      hive> show tables;
      OK
      students
      Time taken: 0.082 seconds, Fetched: 1 row(s)

* describe students

      hive> describe students;
      OK
      s_id                	int                 	                    
      s_name              	string              	                    
      Time taken: 0.306 seconds, Fetched: 2 row(s)

* select * from students

      hive> select * from students;
      OK
      Time taken: 0.32 seconds

* select count(*) from students

      hive> select count(*) from students;
      Total jobs = 1
      Launching Job 1 out of 1
      Number of reduce tasks determined at compile time: 1
      In order to change the average load for a reducer (in bytes):
        set hive.exec.reducers.bytes.per.reducer=<number>
      In order to limit the maximum number of reducers:
        set hive.exec.reducers.max=<number>
      In order to set a constant number of reducers:
        set mapreduce.job.reduces=<number>
      Starting Job = job_1532882569615_0001, Tracking URL = http://hadoop03:8088/proxy/application_1532882569615_0001/
      Kill Command = /softWare/hadoop-2.2.0/bin/hadoop job  -kill job_1532882569615_0001
      Hadoop job information for Stage-1: number of mappers: 1; number of reducers: 1
      2018-07-29 15:36:29,344 Stage-1 map = 0%,  reduce = 0%
      2018-07-29 15:36:52,819 Stage-1 map = 100%,  reduce = 0%, Cumulative CPU 5.01 sec
      2018-07-29 15:37:11,736 Stage-1 map = 100%,  reduce = 100%, Cumulative CPU 6.99 sec
      MapReduce Total cumulative CPU time: 6 seconds 990 msec
      Ended Job = job_1532882569615_0001
      MapReduce Jobs Launched: 
      Job 0: Map: 1  Reduce: 1   Cumulative CPU: 6.99 sec   HDFS Read: 251 HDFS Write: 2 SUCCESS
      Total MapReduce CPU Time Spent: 6 seconds 990 msec
      OK
      0
      Time taken: 70.546 seconds, Fetched: 1 row(s)

* 加载本地数据到Hive（load data local inpath '/s.txt' into table students）
      
      [root@hadoop01 /]# vim s.txt
      xiaoming        12
      xiaoliang       13
      xiaohong        11

      hive> load data local inpath '/s.txt' into table students;
      Copying data from file:/s.txt
      Copying file: file:/s.txt
      Loading data to table default.students
      Table default.students stats: [numFiles=1, numRows=0, totalSize=38, rawDataSize=0]
      OK
      Time taken: 1.066 seconds


