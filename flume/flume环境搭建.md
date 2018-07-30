# flume环境搭建

### 一、上传

* [获得apache-flume-1.5.0-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/flume/apache-flume-1.5.0-bin.tar.gz)

* 上传

### 二、安装

* 解压并重命名

      tar -zxvf apache-flume-1.5.0-bin.tar.gz -C /softWare/
      mv apache-flume-1.5.0-bin/ flume-1.5.0-bin

* 改名

      cd /softWare/flume-1.5.0-bin/conf
      mv flume-env.sh.template flume-env.sh

* 更改配置文件

     * 修改flume-env.sh文件
     
            vim flume-env.sh
            
            JAVA_HOME=/softWare/jdk1.7.0_80     

### 三、启动

* 1、文件下载

     * [conf和lib的文件](https://github.com/sunnyandgood/BigData/blob/master/flume/conflib.zip)

* 2、上传a4.conf到flume的conf目录下

      mv /a4.conf /softWare/flume-1.5.0-bin/conf/









