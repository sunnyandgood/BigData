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

* # Hive单节点安装及操作

      hive> describe students;
      OK
      s_id                	int                 	                    
      s_name              	string              	                    
      Time taken: 0.306 seconds, Fetched: 2 row(s)

* 






