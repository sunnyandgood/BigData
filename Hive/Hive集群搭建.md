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












