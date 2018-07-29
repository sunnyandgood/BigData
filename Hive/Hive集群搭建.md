# Hive集群搭建

### 一、上传

* [获得apache-hive-0.13.0-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/Hive/apache-hive-0.13.0-bin.tar.gz)

* 上传

### 二、安装Hive（Hive只在一个节点上安装即可）

* 解压apache-hive-0.13.0-bin.tar.gz

      tar -zxvf apache-hive-0.13.0-bin.tar.gz -C /softWare/
      
* 修改配置文件

     * 将hive添加到环境变量中
     
           cd /etc/
           export HIVE_HOME=/softWare/apache-hive-0.13.0-bin
           export PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$HBASE_HOME/bin:$HIVE_HOME/bin
     
     * 修改hive-default.xml.template为hive-site.xml
     
           cd /softWare/apache-hive-0.13.0-bin/conf
           mv hive-default.xml.template hive-site.xml
     
     * 修改hive-site.xml文件
     
           <property>
              <name>javax.jdo.option.ConnectionURL</name>
              <value>jdbc:mysql://hadoop05:3306/hive?createDatabaseIfNotExist=true</value>
              <description>JDBC connect string for a JDBC metastore</description>
            </property>

            <property>
              <name>javax.jdo.option.ConnectionDriverName</name>
              <value>com.mysql.jdbc.Driver</value>
              <description>Driver class name for a JDBC metastore</description>
            </property>

            <property>
              <name>javax.jdo.option.ConnectionUserName</name>
              <value>root</value>
              <description>username to use against metastore database</description>
            </property>

            <property>
              <name>javax.jdo.option.ConnectionPassword</name>
              <value>123</value>
              <description>password to use against metastore database</description>
            </property>
     
     * 将数据库连接驱动拷贝到/softWare/apache-hive-0.13.0-bin/lib里
     
     	 * [mysql-connector-5.1.8.jar](https://github.com/sunnyandgood/BigData/blob/master/Sqoop/mysql-connector-5.1.8.jar)
     
     
* mysql授权

      GRANT ALL PRIVILEGES ON hive.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
      FLUSH PRIVILEGES;
      
      GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY 'root' WITH GRANT OPTION;
      FLUSH PRIVILEGES;
      
     -------------------------------------操作---------------------------------------------------

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

### 三、启动hive

      cd /softWare/apache-hive-0.13.0-bin/bin
      ./hive





