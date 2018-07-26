# hadoop2.0集群搭建

* hadoop2.0已经发布了稳定版本了，增加了很多特性，比如HDFS HA、YARN等。

* 注意：apache提供的hadoop-2.2.0的安装包是在32位操作系统编译的，因为hadoop依赖一些C++的本地库，所以如果在64位的操作上安装hadoop-2.2.0就需要重新在64操作系统上重新编译

### 第一步、[准备Linux环境](https://github.com/sunnyandgood/BigData/blob/master/hadoop2.2.0伪分布式搭建/1、准备Linux环境.md)

* 修改Linux主机名

* 修改IP

* 修改主机名和IP的映射关系

	**注意**:如果租用的服务器或是使用的云主机（如华为用主机、阿里云主机等），/etc/hosts里面要配置的是内网IP地址和主机名的映射关系	
  
* 关闭防火墙

* ssh免登陆 

* 安装JDK，配置环境变量等

* 重启（reboot）

### 第二步、集群规划：

	主机名		    IP		安装的软件					      运行的进程
	hadoop01	192.168.1.101	jdk、hadoop					NameNode、DFSZKFailoverController
	hadoop02	192.168.1.102	jdk、hadoop					NameNode、DFSZKFailoverController
	hadoop03	192.168.1.103	jdk、hadoop					ResourceManager
	hadoop04	192.168.1.104	jdk、hadoop、zookeeper		DataNode、NodeManager、JournalNode、QuorumPeerMain
	hadoop05	192.168.1.105	jdk、hadoop、zookeeper		DataNode、NodeManager、JournalNode、QuorumPeerMain
	hadoop06	192.168.1.106	jdk、hadoop、zookeeper		DataNode、NodeManager、JournalNode、QuorumPeerMain
	
* 说明：
	
  * 在hadoop2.0中通常由两个NameNode组成，一个处于active状态，另一个处于standby状态。Active NameNode对外提供服务，而Standby NameNode则不对外提供服务，仅同步active namenode的状态，以便能够在它失败时快速进行切换。
	
  * hadoop2.0官方提供了两种HDFS HA(High Available：高可靠性)的解决方案，一种是NFS，另一种是QJM。这里我们使用简单的QJM。在该方案中，主备NameNode之间通过一组JournalNode同步元数据信息，一条数据只要成功写入多数JournalNode即认为写入成功。通常配置奇数个JournalNode
	
  * 这里还配置了一个zookeeper集群，用于ZKFC（DFSZKFailoverController）故障转移，当Active NameNode挂掉了，会自动切换Standby NameNode为standby状态
	
### 第三步、安装步骤：

* 1、安装配置zooekeeper集群

	* 1.1解压
    
			tar -zxvf zookeeper-3.4.5.tar.gz -C /hadoop/

	* 1.2修改配置
	
			cd /hadoop/zookeeper-3.4.5/conf/
			mv zoo_sample.cfg zoo.cfg
			vim zoo.cfg
			修改：dataDir=/itcast/zookeeper-3.4.5/tmp
			在最后添加：
			server.1=hadoop04:2888:3888
			server.2=hadoop05:2888:3888
			server.3=hadoop06:2888:3888
			保存退出

			然后创建一个tmp文件夹
			mkdir /hadoop/zookeeper-3.4.5/tmp

			再创建一个空文件
			touch /hadoop/zookeeper-3.4.5/tmp/myid
			hadoop
			最后向该文件写入ID
			echo 1 > /hadoop/zookeeper-3.4.5/tmp/myid
		
	* 1.3将配置好的zookeeper拷贝到其他节点(首先分别在hadoop05、hadoop06根目录下创建一个itcast目录：mkdir /hadoop)
	
			scp -r /hadoop/zookeeper-3.4.5/ hadoop05:/hadoop/
			scp -r /hadoop/zookeeper-3.4.5/ hadoop06:/hadoop/

			注意：修改hadoop05、hadoop06对应/hadoop/zookeeper-3.4.5/tmp/myid内容
			hadoop05：
				echo 2 > /hadoop/zookeeper-3.4.5/tmp/myid
			hadoop06：
				echo 3 > /hadoop/zookeeper-3.4.5/tmp/myid
	
* 2、安装配置hadoop集群

     * 2.1解压
	
		tar -zxvf hadoop-2.2.0.tar.gz -C /hadoop/
			
     * 2.2配置HDFS（hadoop2.0所有的配置文件都在$HADOOP_HOME/etc/hadoop目录下）
	
		#将hadoop添加到环境变量中
		vim /etc/profile
		export JAVA_HOME=/usr/java/jdk1.7.0_55
		export HADOOP_HOME=/itcast/hadoop-2.2.0
		export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin
			
		#hadoop2.0的配置文件全部在$HADOOP_HOME/etc/hadoop下
		cd /itcast/hadoop-2.2.0/etc/hadoop
			
	* 2.2.1修改hadoo-env.sh
		      
				export JAVA_HOME=/usr/java/jdk1.7.0_55
				
			2.2.2修改core-site.xml
				<configuration>
					<!-- 指定hdfs的nameservice为ns1 -->
					<property>
						<name>fs.defaultFS</name>
						<value>hdfs://ns1</value>
					</property>
					<!-- 指定hadoop临时目录 -->
					<property>
						<name>hadoop.tmp.dir</name>
						<value>/mnt/softWare/hadoop-2.2.0/tmp</value>
					</property>
					<!-- 指定zookeeper地址 -->
					<property>
						<name>ha.zookeeper.quorum</name>
						<value>hadoop04:2181,hadoop05:2181,hadoop06:2181</value>
					</property>
				</configuration>
				
			2.2.3修改hdfs-site.xml
				<configuration>
						<!--指定hdfs的nameservice为ns1，需要和core-site.xml中的保持一致 -->
						<property>
							<name>dfs.nameservices</name>
							<value>ns1</value>
						</property>
						<!-- ns1下面有两个NameNode，分别是nn1，nn2 -->
						<property>
							<name>dfs.ha.namenodes.ns1</name>
							<value>nn1,nn2</value>
						</property>
						<!-- nn1的RPC通信地址 -->
						<property>
							<name>dfs.namenode.rpc-address.ns1.nn1</name>
							<value>hadoop01:9000</value>
						</property>
						<!-- nn1的http通信地址 -->
						<property>
							<name>dfs.namenode.http-address.ns1.nn1</name>
							<value>hadoop01:50070</value>
						</property>
						<!-- nn2的RPC通信地址 -->
						<property>
							<name>dfs.namenode.rpc-address.ns1.nn2</name>
							<value>hadoop02:9000</value>
						</property>
						<!-- nn2的http通信地址 -->
						<property>
							<name>dfs.namenode.http-address.ns1.nn2</name>
							<value>hadoop02:50070</value>
						</property>
						<!-- 指定NameNode的元数据在JournalNode上的存放位置 -->
						<property>
							<name>dfs.namenode.shared.edits.dir</name>							
							<value>qjournal://hadoop04:8485;hadoop05:8485;hadoop06:8485/ns1</value>
						</property>
						<!-- 指定JournalNode在本地磁盘存放数据的位置 -->
						<property>
							<name>dfs.journalnode.edits.dir</name>
							<value>/mnt/software/hadoop-2.2.0/journal</value>
						</property>
						<!-- 开启NameNode失败自动切换 -->
						<property>
							<name>dfs.ha.automatic-failover.enabled</name>
							<value>true</value>
						</property>
						<!-- 配置失败自动切换实现方式 -->
						<property>
							<name>dfs.client.failover.proxy.provider.ns1</name>
							<value>org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider</value>
						</property>
						<!-- 配置隔离机制方法，多个机制用换行分割，即每个机制占用一行-->
						<property>
							<name>dfs.ha.fencing.methods</name>
							<value>
								sshfence
								shell(/bin/true)
							</value>
						</property>
						<!-- 使用sshfence隔离机制时需要ssh免登陆 -->
						<property>
							<name>dfs.ha.fencing.ssh.private-key-files</name>
							<value>/root/.ssh/id_rsa</value>
						</property>
						<!-- 配置sshfence隔离机制超时时间 -->
						<property>
							<name>dfs.ha.fencing.ssh.connect-timeout</name>
							<value>30000</value>
						</property>
				</configuration>
			
			2.2.4修改mapred-site.xml
				<configuration>
					<!-- 指定mr框架为yarn方式 -->
					<property>
						<name>mapreduce.framework.name</name>
						<value>yarn</value>
					</property>
				</configuration>	
			
			2.2.5修改yarn-site.xml
				<configuration>
					<!-- 指定resourcemanager地址 -->
					<property>
						<name>yarn.resourcemanager.hostname</name>
						<value>hadoop03</value>
					</property>
					<!-- 指定nodemanager启动时加载server的方式为shuffle server -->
					<property>
						<name>yarn.nodemanager.aux-services</name>
						<value>mapreduce_shuffle</value>
					</property>
				</configuration>
			
				
			2.2.6修改slaves(slaves是指定子节点的位置，因为要在itcast01上启动HDFS、在itcast03启动yarn，所以itcast01上的slaves文件指定的是datanode的位置，itcast03上的slaves文件指定的是nodemanager的位置)
				hadoop04
				hadoop05
				hadoop06

			2.2.7配置免密码登陆
				#首先要配置itcast01到itcast02、itcast03、itcast04、itcast05、itcast06的免密码登陆
				#在itcast01上生产一对钥匙
				ssh-keygen -t rsa
				#将公钥拷贝到其他节点，包括自己
				ssh-coyp-id itcast01
				ssh-coyp-id itcast02
				ssh-coyp-id itcast03
				ssh-coyp-id itcast04
				ssh-coyp-id itcast05
				ssh-coyp-id itcast06
				
				#配置itcast03到itcast04、itcast05、itcast06的免密码登陆
				#在itcast03上生产一对钥匙
				ssh-keygen -t rsa
				#将公钥拷贝到其他节点
				ssh-coyp-id itcast04
				ssh-coyp-id itcast05
				ssh-coyp-id itcast06
				
				#注意：两个namenode之间要配置ssh免密码登陆，别忘了配置itcast02到itcast01的免登陆
				在itcast02上生产一对钥匙
				ssh-keygen -t rsa
				ssh-coyp-id -i itcast01				
		
	2.4将配置好的hadoop拷贝到其他节点
			scp -r /mnt/softWare/hadoop-2.2.0/ root@192.168.1.102:/mnt/softWare/
			scp -r /mnt/softWare/hadoop-2.2.0/ root@192.168.1.103:/mnt/softWare/
			scp -r /mnt/softWare/hadoop-2.2.0/ root@192.168.1.104:/mnt/softWare/
			scp -r /mnt/softWare/hadoop-2.2.0/ root@192.168.1.105:/mnt/softWare/
			scp -r /mnt/softWare/hadoop-2.2.0/ root@192.168.1.106:/mnt/softWare/
		
		###注意：严格按照下面的步骤
	2.5启动zookeeper集群（分别在hadoop04、hadoop05、hadoop06上启动zk）
			cd /mnt/softWare/zookeeper-3.4.5/bin/

			./zkServer.sh start
			#查看状态：一个leader，两个follower
			./zkServer.sh status
			
	2.6启动journalnode（在hadoop01上启动所有journalnode，注意：是调用的hadoop-daemons.sh这个脚本，注意是复数s的那个脚本）
			cd /mnt/softWare/hadoop-2.2.0/
			sbin/hadoop-daemons.sh start journalnode
			#运行jps命令检验，hadoop04、hadoop05、hadoop06上多了JournalNode进程
		
	2.7格式化HDFS
			#在hadoop01上执行命令:
			hdfs namenode -format
			#格式化后会在根据core-site.xml中的hadoop.tmp.dir配置生成个文件，这里我配置的是/itcast/hadoop-2.2.0/tmp，然后将/itcast/hadoop-2.2.0/tmp拷贝到itcast02的/itcast/hadoop-2.2.0/下。
			scp -r tmp/ root@hadoop02:/mnt/softWare/hadoop-2.2.0/
		
	2.8格式化ZK(在hadoop01上执行即可)
			hdfs zkfc -formatZK
		
	2.9启动HDFS(在hadoop01上执行)
			sbin/start-dfs.sh

	2.10启动YARN(#####注意#####：是在hadoop03上执行start-yarn.sh，把namenode和resourcemanager分开是因为性能问题，因为他们都要占用大量资源，所以把他们分开了，他们分开了就要分别在不同的机器上启动)
			sbin/start-yarn.sh

	到此，hadoop2.2.0配置完毕，可以统计浏览器访问:
		http://192.168.1.101:50070
		NameNode 'itcast01:9000' (active)
		http://192.168.1.102:50070
		NameNode 'itcast02:9000' (standby)
	
	验证HDFS HA
		首先向hdfs上传一个文件
		hadoop fs -put /etc/profile /profile
		hadoop fs -ls /
		然后再kill掉active的NameNode
		kill -9 <pid of NN>
		通过浏览器访问：http://192.168.1.202:50070
		NameNode 'itcast02:9000' (active)
		这个时候itcast02上的NameNode变成了active
		在执行命令：
		hadoop fs -ls /
		-rw-r--r--   3 root supergroup       1926 2014-02-06 15:36 /profile
		刚才上传的文件依然存在！！！
		手动启动那个挂掉的NameNode
		sbin/hadoop-daemon.sh start namenode
		通过浏览器访问：http://192.168.1.201:50070
		NameNode 'itcast01:9000' (standby)
	
	验证YARN：
		运行一下hadoop提供的demo中的WordCount程序：
		hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.2.0.jar wordcount /profile /out
	
	OK，大功告成！！！
			
