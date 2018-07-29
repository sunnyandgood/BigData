# Hbase集群搭建

* 1、上传hbase安装包

	* [获得/hbase-0.96.2-hadoop2-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/HBase/hbase-0.96.2-hadoop2-bin.tar.gz)
	
	* 上传

* 2、解压

		tar -zxvf hbase-0.96.2-hadoop2-bin.tar.gz -C /softWare/

* 3、配置hbase集群，要修改3个文件（首先zk集群已经安装好了）

	  注意：要把hadoop的hdfs-site.xml和core-site.xml 放到hbase/conf下
	  	cd /softWare/hadoop-2.2.0/etc/hadoop
			cp hdfs-site.xml /softWare/hbase-0.96.2-hadoop2/conf/
			cp core-site.xml /softWare/hbase-0.96.2-hadoop2/conf/
	
	* 3.1修改hbase-env.sh
  
	      vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-env.sh

	      export JAVA_HOME=/softWare/jdk1.7.0_80
	      //告诉hbase使用外部的zk 
	      export HBASE_MANAGES_ZK=false
	
	* 3.2修改hbase-site.xml
	
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
	
	* 3.3修改regionservers
   
	      hadoop03
	      hadoop04
	      hadoop05
	      hadoop06
	
	* 3.4拷贝hbase到其他节点
  
			scp -r /softWare/hbase-0.96.2-hadoop2/ hadoop02:/softWare/
			scp -r /softWare/hbase-0.96.2-hadoop2/ hadoop03:/softWare/
			scp -r /softWare/hbase-0.96.2-hadoop2/ hadoop04:/softWare/
			scp -r /softWare/hbase-0.96.2-hadoop2/ hadoop05:/softWare/
			scp -r /softWare/hbase-0.96.2-hadoop2/ hadoop06:/softWare/
    
* 4、将配置好的HBase拷贝到每一个节点并同步时间。

* 5、启动所有的hbase

      分别启动zk
        ./zkServer.sh start
      启动hbase集群
        ./start-dfs.sh
      启动hbase，在主节点上运行：
        ./start-hbase.sh
        
* 6、通过浏览器访问hdase管理页面

	    192.168.2.101:60010
  
* 7、为保证集群的可靠性，要启动多个HMaster

	    hbase-daemon.sh start master
	
* 8、使用操作

	 * hbase shell
	 
			[root@hadoop01 bin]# hbase shell
			2018-07-29 01:32:53,499 INFO  [main] Configuration.deprecation: hadoop.native.lib is deprecated. 
			Instead, use io.native.lib.available
			HBase Shell; enter 'help<RETURN>' for list of supported commands.
			Type "exit<RETURN>" to leave the HBase Shell
			Version 0.96.2-hadoop2, r1581096, Mon Mar 24 16:03:18 PDT 2014

			hbase(main):001:0>
	
	 * create
	
			hbase(main):002:0> create 'heros' ,{NAME => 'info',VERSIONS => 3},{NAME => 'data'}
			0 row(s) in 1.7030 seconds

			=> Hbase::Table - heros
			hbase(main):003:0>
	
	 * describe
	 
			hbase(main):003:0> describe 'heros'
			DESCRIPTION                                                       ENABLED                           
			 'heros', {NAME => 'data', DATA_BLOCK_ENCODING => 'NONE', BLOOMFI true                              
			 LTER => 'ROW', REPLICATION_SCOPE => '0', VERSIONS => '1', COMPRE                                   
			 SSION => 'NONE', MIN_VERSIONS => '0', TTL => '2147483647', KEEP_                                   
			 DELETED_CELLS => 'false', BLOCKSIZE => '65536', IN_MEMORY => 'fa                                   
			 lse', BLOCKCACHE => 'true'}, {NAME => 'info', DATA_BLOCK_ENCODIN                                   
			 G => 'NONE', BLOOMFILTER => 'ROW', REPLICATION_SCOPE => '0', VER                                   
			 SIONS => '3', COMPRESSION => 'NONE', MIN_VERSIONS => '0', TTL =>                                   
			  '2147483647', KEEP_DELETED_CELLS => 'false', BLOCKSIZE => '6553                                   
			 6', IN_MEMORY => 'false', BLOCKCACHE => 'true'}                                                    
			1 row(s) in 0.1130 seconds

			hbase(main):004:0>
	 
	 * put
	 
			hbase(main):004:0> hbase put 'heros','rk001','info:name','qwe' 
			0 row(s) in 0.2480 seconds

			NoMethodError: undefined method `hbase' for #<Object:0x1519a59>

			hbase(main):005:0>
	 
	 * scan
	 
			hbase(main):006:0> scan 'heros'
			ROW                        COLUMN+CELL                                                              
			 rk001                     column=info:name, timestamp=1532853903893, value=qwe                     
			1 row(s) in 0.2180 seconds

			hbase(main):007:0>	
	 
	 
	
	
	
	
	
	
	
	
