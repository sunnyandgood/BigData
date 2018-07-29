# HBase伪分布式搭建

### 一、上传hbase-0.96.2-hadoop2-bin.tar.gz

* [获得hbase-0.96.2-hadoop2-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/HBase/hbase-0.96.2-hadoop2-bin.tar.gz)

* 上传

### 二、安装HBase

* 解压hbase-0.96.2-hadoop2-bin.tar.gz

      tar -zxvf hbase-0.96.2-hadoop2-bin.tar.gz -C /softWare/
  
* 修改配置文件
  
    * 修改/etc/profile文件。

            #vi /etc/profile
            增加 
            export HBASE_HOME=/home/hbase
            修改 
            export PATH=$JAVA_HOME/bin:$PATH:$HADOOP_HOME/bin:$HBASE_HOME/bin
            保存退出
            #source /etc/profile
            

     * 修改$HBASE_HOME/conf/hbase-env.sh文件 
     
            export JAVA_HOME=/usr/local/jdk
            export HBASE_MANAGES_ZK=true
            保存后退出

     * 修改$HBASE_HOME/conf/hbase-site.xml
     
            <property>
              <name>hbase.rootdir</name>
              <value>hdfs://hadoop0:9000/hbase</value>
            </property>
            <property>
              <name>hbase.cluster.distributed</name>
              <value>true</value>
            </property>
            <property>
              <name>hbase.zookeeper.quorum</name>
              <value>hadoop0</value>
            </property>
            <property>
              <name>dfs.replication</name>
              <value>1</value>
            </property>
      
     * 注意：$HBASE_HOME/conf/hbase-site.xml的hbase.rootdir的主机和端口号与$HADOOP_HOME/conf/core-site.xml的fs.default.name的主机和端口号一致

     * 修改$HBASE_HOME/conf/regionservers
     
           在$HBASE_HOME/conf/regionservers文件增加
           localhost
           保存退出
