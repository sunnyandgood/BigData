# Hbase集群搭建

* 1、上传hbase安装包

	* [获得/hbase-0.96.2-hadoop2-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/HBase/hbase-0.96.2-hadoop2-bin.tar.gz)
	
	* 上传

* 2、解压

		tar -zxvf hbase-0.96.2-hadoop2-bin.tar.gz -C /softWare/

* 3、配置hbase集群，要修改3个文件（首先zk集群已经安装好了）

	  注意：要把hadoop的hdfs-site.xml和core-site.xml 放到hbase/conf下
	
	* 3.1修改hbase-env.sh
  
	      export JAVA_HOME=/usr/java/jdk1.7.0_55
	      //告诉hbase使用外部的zk 
	      export HBASE_MANAGES_ZK=false
	
	* 3.2vim hbase-site.xml
  
		      <configuration>
			<!-- 指定hbase在HDFS上存储的路径 -->
			    <property>
				    <name>hbase.rootdir</name>
				    <value>hdfs://ns1/hbase</value>
			    </property>
			<!-- 指定hbase是分布式的 -->
			    <property>
				    <name>hbase.cluster.distributed</name>
				    <value>true</value>
			    </property>
			<!-- 指定zk的地址，多个用“,”分割 -->
			    <property>
				    <name>hbase.zookeeper.quorum</name>
				    <value>hadoop04:2181,hadoop05:2181,hadoop06:2181</value>
			    </property>
		      </configuration>
	
	* 3.3vim regionservers
   
	      hadoop03
	      hadoop04
	      hadoop05
	      hadoop06
	
	* 3.4拷贝hbase到其他节点
  
			scp -r /hadoop/hbase-0.96.2-hadoop2/ hadoop02:/hadoop/
			scp -r /hadoop/hbase-0.96.2-hadoop2/ hadoop03:/hadoop/
			scp -r /hadoop/hbase-0.96.2-hadoop2/ hadoop04:/hadoop/
			scp -r /hadoop/hbase-0.96.2-hadoop2/ hadoop05:/hadoop/
			scp -r /hadoop/hbase-0.96.2-hadoop2/ hadoop06:/hadoop/
    
* 4、将配置好的HBase拷贝到每一个节点并同步时间。

* 5、启动所有的hbase

      分别启动zk
        ./zkServer.sh start
      启动hbase集群
        start-dfs.sh
      启动hbase，在主节点上运行：
        start-hbase.sh
        
* 6、通过浏览器访问hbase管理页面

	    192.168.1.201:60010
  
* 7、为保证集群的可靠性，要启动多个HMaster

	    hbase-daemon.sh start master
	
