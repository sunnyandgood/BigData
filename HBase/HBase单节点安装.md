# HBase单节点安装

### 一、上传hbase-0.96.2-hadoop2-bin.tar.gz

* [获得hbase-0.96.2-hadoop2-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/HBase/hbase-0.96.2-hadoop2-bin.tar.gz)

* 上传

### 安装HBase

* 解压hbase-0.96.2-hadoop2-bin.tar.gz

      tar -zxvf hbase-0.96.2-hadoop2-bin.tar.gz -C /softWare/
  
* 修改配置文件

  * 修改hbase-env.sh
  
        vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-env.sh
        
        export JAVA_HOME=/softWare/jdk1.7.0_80

  * 修改hbase-site.xml

        vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-site.xml
        
        
