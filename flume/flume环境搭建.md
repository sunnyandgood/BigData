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

### 三、启动（bin/flume-ng agent -n a4 -c conf -f conf/a4.conf -Dflume.root.logger=INFO,console）

* 1、上传a4.conf到flume的conf目录下

      mv /a4.conf /softWare/flume-1.5.0-bin/conf/

* 2、上传hadoop-common-2.2.0.jar到flume的lib目录下

      scp /softWare/hadoop-2.2.0/share/hadoop/common/hadoop-common-2.2.0.jar 
                        root@192.168.2.107:/softWare/flume-1.5.0-bin/lib/

* 3、上传commons-configuration-1.6.jar到flume的lib目录下

      scp /softWare/hadoop-2.2.0/share/hadoop/common/lib/commons-configuration-1.6.jar 
                              root@192.168.2.107:/softWare/flume-1.5.0-bin/lib/

* 4、上传hadoop-auth-2.2.0.jar到flume的lib目录下

      scp /softWare/hadoop-2.2.0/share/hadoop/common/lib/hadoop-auth-2.2.0.jar 
                              root@192.168.2.107:/softWare/flume-1.5.0-bin/lib/

* 5、创建a4.conf里`a4.sources.r1.spoolDir = /flumelogs`指定的`/flumelogs`目录
      
      mkdir /logs

* 6、启动

      cd /softWare/flume-1.5.0-bin/
      bin/flume-ng agent -n a4 -c conf -f conf/a4.conf -Dflume.root.logger=INFO,console

* 7、将数据放进logs里

      cp /access_2013_05_30.log /logs/




* 、a4.conf的内容

      #定义agent名， source、channel、sink的名称
      a4.sources = r1
      a4.channels = c1
      a4.sinks = k1

      #具体定义source
      a4.sources.r1.type = spooldir
      a4.sources.r1.spoolDir = /logs

      #具体定义channel
      a4.channels.c1.type = memory
      a4.channels.c1.capacity = 10000
      a4.channels.c1.transactionCapacity = 100

      #定义拦截器，为消息添加时间戳
      a4.sources.r1.interceptors = i1
      a4.sources.r1.interceptors.i1.type = org.apache.flume.interceptor.TimestampInterceptor$Builder


      #具体定义sink
      a4.sinks.k1.type = hdfs
      a4.sinks.k1.hdfs.path = hdfs://ns1/flume/%Y%m%d
      a4.sinks.k1.hdfs.filePrefix = events-
      a4.sinks.k1.hdfs.fileType = DataStream
      #不按照条数生成文件
      a4.sinks.k1.hdfs.rollCount = 0
      #HDFS上的文件达到128M时生成一个文件
      a4.sinks.k1.hdfs.rollSize = 134217728
      #HDFS上的文件达到60秒生成一个文件
      a4.sinks.k1.hdfs.rollInterval = 60

      #组装source、channel、sink
      a4.sources.r1.channels = c1
      a4.sinks.k1.channel = c1
