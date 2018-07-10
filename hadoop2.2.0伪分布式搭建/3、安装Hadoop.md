## 安装Hadoop
* 一、上传hadoop安装包
	
* 二、解压hadoop安装包

		mkdir /cloud
		#解压到/cloud/目录下
		tar -zxvf hadoop-2.2.0.tar.gz -C /cloud/
		
* 三、修改配置文件（5个）`hadoop-2.2.0/etc/hadoop/`目录下
	* 第一个：hadoop-env.sh
    
		      #在27行修改
		      export JAVA_HOME=/mnt/softWare/jdk1.7.0_80
		
	* 第二个：core-site.xml
  
		      <configuration>
			<!-- 指定HDFS老大（namenode）的通信地址 -->
			<property>
			    <name>fs.defaultFS</name>
			    <value>hdfs://itcast01:9000</value>
			</property>
			<!-- 指定hadoop运行时产生文件的存储路径 -->
			<property>
			    <name>hadoop.tmp.dir</name>
			    <value>/cloud/hadoop-2.2.0/tmp</value>
			</property>
		      </configuration>
		
	* 第三个：hdfs-site.xml
  
		      <configuration>
			<!-- 设置hdfs副本数量 -->
			<property>
			    <name>dfs.replication</name>
			    <value>1</value>
			</property>
		      </configuration>
		
	* 第四个：mapred-site.xml.template 需要重命名： mv mapred-site.xml.template mapred-site.xml
  
		      <configuration>
			<!-- 通知框架MR使用YARN -->
			<property>
			    <name>mapreduce.framework.name</name>
			    <value>yarn</value>
			</property>
		      </configuration>
		
	* 第五个：yarn-site.xml
	
			<configuration>
				<!-- reducer取数据的方式是mapreduce_shuffle -->
				<property>
					<name>yarn.nodemanager.aux-services</name>
					<value>mapreduce_shuffle</value>
				</property>
			</configuration>
	
* 四、将hadoop添加到环境变量

		vim /etc/profile
		
		export JAVA_HOME=/usr/java/jdk1.7.0_55
		export HADOOP_HOME=/cloud/hadoop-2.2.0
		export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin
	
		source /etc/profile
    
* 五、格式化HDFS（namenode）第一次使用时要格式化
  
		hadoop namenode -format(过时了)
		
		hdfs namenode -format
		
* 六、启动hadoop
		
		./start-all.sh(过时了)
		
		先启动HDFS
		sbin/start-dfs.sh
		
		再启动YARN
		sbin/start-yarn.sh
		
* 七、验证是否启动成功

		使用jps命令验证
		27408 NameNode
		28218 Jps
		27643 SecondaryNameNode
		28066 NodeManager
		27803 ResourceManager
		27512 DataNode
	
		http://192.168.1.44:50070  (HDFS管理界面)
		在这个文件中添加linux主机名和IP的映射关系
		C:\Windows\System32\drivers\etc\hosts
		192.168.1.119	itcast
		
		http://192.168.1.44:8088 （MR管理界面）
