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
