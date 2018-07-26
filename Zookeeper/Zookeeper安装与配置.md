# Zookeeper安装与配置

### 一、单节点模式

* 1、[下载ZooKeeper](https://github.com/sunnyandgood/BigData/blob/master/Zookeeper/file/zookeeper-3.4.5.tar.gz)

* 2、解压

       tar -zxvf zookeeper-3.4.5.tar.gz

* 3、在conf目录下创建一个配置文件zoo.cfg

    * zookeeper的默认配置文件为zookeeper/conf/zoo_sample.cfg，需要将其修改为zoo.cfg。
    
          tickTime=2000
          dataDir=/Users/zdandljb/zookeeper/data
          dataLogDir=/Users/zdandljb/zookeeper/dataLog         
          clientPort=2181
    
    * zoo.cfg文件的内容详解
    
        * tickTime：CS通信心跳时间
        
          Zookeeper 服务器之间或客户端与服务器之间维持心跳的时间间隔，也就是每个 tickTime 时间就会发送一个心跳。tickTime以毫秒为单位。
        
              tickTime=2000  

        * initLimit：LF初始通信时限
        
          集群中的follower服务器(F)与leader服务器(L)之间初始连接时能容忍的最多心跳数（tickTime的数量）。
        
              initLimit=5  

        * syncLimit：LF同步通信时限
        
          集群中的follower服务器与leader服务器之间请求和应答之间能容忍的最多心跳数（tickTime的数量）。
        
              syncLimit=2  

        * dataDir：数据文件目录
        
          Zookeeper保存数据的目录，默认情况下，Zookeeper将写数据的日志文件也保存在这个目录里。
        
              dataDir=/home/michael/opt/zookeeper/data  

        * clientPort：客户端连接端口
        
          客户端连接 Zookeeper 服务器的端口，Zookeeper 会监听这个端口，接受客户端的访问请求。
        
              clientPort=2181 

        * 服务器名称与地址：集群信息（服务器编号，服务器地址，LF通信端口，选举端口）
        
          这个配置项的书写格式比较特殊，规则如下：
        
              server.N=YYY:A:B 

              server.1=itcast05:2888:3888
              server.2=itcast06:2888:3888
              server.3=itcast07:2888:3888

* 4、启动ZooKeeper的Server：

        ./zkServer.sh start, 

* 5、关闭：

        ./zkServer.sh stop

### 二、集群模式

* 1、上传zk安装包

* 2、解压

	   tar -xzvf /zookeeper-3.4.5.tar.gz -C /mnt/softWare/

* 3、配置（先在一台节点上配置）

	* 3.1、添加一个zoo.cfg配置文件
	
			$zookeeper/conf/
			mv zoo_sample.cfg zoo.cfg
	
	* 3.2、修改配置文件（zoo.cfg）
	
			dataDir=/mnt/softWare/zookeeper-3.4.5/data

			server.4=hadoop04:2888:3888
			server.5=hadoop05:2888:3888
			server.6=hadoop06:2888:3888
	
	* 3.3、在（dataDir=/mnt/softWare/zookeeper-3.4.5/data）创建一个myid文件，里面内容是server.N中的N（server.2里面内容为2）
		echo "4" > myid

			mkdir data
			vim myid

	* 3.4、修改主机映射
	
			vim /etc/hosts
			192.168.1.104 hadoop04
			192.168.1.105 hadoop05
			192.168.1.106 hadoop06

	
	* 3.5、将配置好的zk拷贝到其他节点 
	
			[root@hadoop04 zookeeper-3.4.5]# scp -r /mnt/softWare/zookeeper-3.4.5 root@hadoop05:/mnt/softWare
			[root@hadoop04 zookeeper-3.4.5]# scp -r /mnt/softWare/zookeeper-3.4.5 root@hadoop06:/mnt/softWare
	
	* 3.6、注意：在其他节点上一定要修改myid的内容
	
			在itcast06应该讲myid的内容改为6 （echo "6" > myid）
			在itcast07应该讲myid的内容改为7 （echo "7" > myid）
		
* 4、启动集群（分别启动zk）

        ./zkServer.sh start

* 5、测试leader自动切换

		[root@hadoop06 bin]# jps
		2385 Jps
		2293 QuorumPeerMain
		[root@hadoop06 bin]# kill -9 2293

### 三、伪集群模式

* 1、建3个文件夹，server1 server2 server3，然后每个文件夹里面解压一个zookeeper的下载包

* 2、进入data目录，创建一个myid的文件，里面写入一个数字，server1,就写一个1，server2对应myid文件就写入2，server3对应myid文件就写个3

* 3、在conf目录下创建一个配置文件zoo.cfg：

		tickTime=2000
		dataDir=/mnt/softWare/zookeeper/data
		dataLogDir=xxx/zookeeper/server1/          
		clientPort=2181				                  
		initLimit=5 					      
		syncLimit=2 				    		     
		server.1=server1:2888:3888 		               	     
		server.2=server2:2888:3888 		                          
		server.3=server3:2888:3888
