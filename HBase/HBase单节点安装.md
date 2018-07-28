# HBase单节点安装

### 一、上传hbase-0.96.2-hadoop2-bin.tar.gz

* [获得hbase-0.96.2-hadoop2-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/HBase/hbase-0.96.2-hadoop2-bin.tar.gz)

* 上传

### 二、安装HBase

* 解压hbase-0.96.2-hadoop2-bin.tar.gz

      tar -zxvf hbase-0.96.2-hadoop2-bin.tar.gz -C /softWare/
  
* 修改配置文件

  * 修改hbase-env.sh
  
        vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-env.sh
        
        export JAVA_HOME=/softWare/jdk1.7.0_80

  * 修改hbase-site.xml

        vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-site.xml
        
        <configuration>
              <property>
                  <name>hbase.rootdir</name>
                  <value>file:///softWare/hbase-0.96.2-hadoop2/hbase</value>
              </property>
        </configuration>

### 三、使用HBase

* 启动HBase

      cd /softWare/hbase-0.96.2-hadoop2/bin
      ./start-hbase.sh

* 查看HBase使用方法

      [root@hadoop01 bin]# ./hbase
      Usage: hbase [<options>] <command> [<args>]
      Options:
        --config DIR    Configuration direction to use. Default: ./conf
        --hosts HOSTS   Override the list in 'regionservers' file

      Commands:
      Some commands take arguments. Pass no args or -h for usage.
        shell           Run the HBase shell
        hbck            Run the hbase 'fsck' tool
        hlog            Write-ahead-log analyzer
        hfile           Store file analyzer
        zkcli           Run the ZooKeeper shell
        upgrade         Upgrade hbase
        master          Run an HBase HMaster node
        regionserver    Run an HBase HRegionServer node
        zookeeper       Run a Zookeeper server
        rest            Run an HBase REST server
        thrift          Run the HBase Thrift server
        thrift2         Run the HBase Thrift2 server
        clean           Run the HBase clean up script
        classpath       Dump hbase CLASSPATH
        mapredcp        Dump CLASSPATH entries required by mapreduce
        version         Print the version
        CLASSNAME       Run the class named CLASSNAME

* 配置环境变量

      cd /etc/
      vim profile
      export HBASE_HOME=/softWare/hbase-0.96.2-hadoop2
      exprot PATH=$PATH:JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$HBASE_HOME/bin
      source profile

* 使用Hbase

      [root@hadoop01 ~]# hbase shell
      2018-07-28 22:26:55,437 INFO  [main] Configuration.deprecation: hadoop.native.lib is deprecated. Instead, use io.native.lib.available
      HBase Shell; enter 'help<RETURN>' for list of supported commands.
      Type "exit<RETURN>" to leave the HBase Shell
      Version 0.96.2-hadoop2, r1581096, Mon Mar 24 16:03:18 PDT 2014

      hbase(main):001:0> 
