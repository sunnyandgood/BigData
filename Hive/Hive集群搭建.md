# Hive集群搭建

### 一、上传

* [获得apache-hive-0.13.0-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/Hive/apache-hive-0.13.0-bin.tar.gz)

* 上传

### 二、安装Hive

* 解压apache-hive-0.13.0-bin.tar.gz

      tar -zxvf apache-hive-0.13.0-bin.tar.gz -C /softWare/
      
* 修改配置文件

     * 将hive添加到环境变量中
     
           cd /etc/
           export HIVE_HOME=/softWare/apache-hive-0.13.0-bin
           export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$HBASE_HOME/bin:$HIVE_HOME/bin
           
* mysql授权

      GRANT ALL PRIVILEGES ON hive.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
      FLUSH PRIVILEGES;
      
      GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
      FLUSH PRIVILEGES;
      
----------------------------------------操作---------------------------------------------------

      [root@hadoop05 mysql]# mysql -u root -proot
        Welcome to the MySQL monitor.  Commands end with ; or \g.
        Your MySQL connection id is 9
        Server version: 5.1.73 MySQL Community Server (GPL)

        Copyright (c) 2000, 2013, Oracle and/or its affiliates. All rights reserved.

        Oracle is a registered trademark of Oracle Corporation and/or its
        affiliates. Other names may be trademarks of their respective
        owners.

        Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

      mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
      Query OK, 0 rows affected (0.00 sec)

      mysql> FLUSH PRIVILEGES;
      Query OK, 0 rows affected (0.00 sec)

      mysql>







