# Zookeepe安装与配置

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

