# hadoop1.0集群搭建


掌握Hadoop集群的搭建过程

了解集群管理的常用命令

集群的副本管理机制

集群的监控管理

掌握sqoop框架

了解pig框架

zookeeper

### 一、集群的概念

* 计算机集群是一种计算机系统， 它通过一组松散集成的计算机软件和/或硬件连接起来高度紧密地协作完成计算工作。

* 集群系统中的单个计算机通常称为节点，通常通过局域网连接。

* 集群技术的特点：

* 通过多台计算机完成同一个工作。达到更高的效率

* 两机或多机内容、工作过程等完全一样。如果一台死机，另一台可以起作用。

### 二、集群模式安装步骤

* (在伪分布模式下继续)

    * 安装jdk

    * 关闭防火墙

    * 修改ip

    * 修改hostname

    * 设置ssh自动登录

    * 解压hadoop

### 三、Hadoop集群搭建准备

  <table>
     <tr>
        <td>机器名　</td>
        <td>机器IP</td>
        <td>用途</td>
     </tr>
     <tr>
        <td>hadoop01</td>
        <td>192.168.1.101</td>
        <td>namenode/secondaryNamenode/jobTracker</td>
     </tr>
     <tr>
        <td>hadoop02</td>
        <td>192.168.1.202</td>
        <td>datanode/taskTracker</td>
     </tr>
     <tr>
        <td>hadoop03</td>
        <td>192.168.1.103</td>
        <td>datanode/taskTracker</td>
     </tr>
     <tr>
        <td>hadoop04</td>
        <td>192.168.1.104</td>
        <td>datanode/taskTracker</td>
     </tr>
  </table>

### 四、Step1:各服务器安装JDK6

      上传jdk-6u24-linux-i586.bin到/home/
      #cd /home/
      #./jdk-6u24-linux-i586.bin
      #mv jdk-6u24-linux-i586.bin jdk
      #vi /etc/profile，在文件尾部添加
      export JAVA_HOME=/home/jdk 
      export PATH=$JAVA_HOME/bin:$PATH
      保存退出
      #source /etc/profile
      #java -version

### 五、Step2:各服务器的网络设置

* 修改机器名

      #hostname <机器名>
      #vi /etc/sysconfig/network
      HOSTNAME=<机器名>     保存退出，重启
      
* 修改/etc/hosts

      hosts文件参考：
      192.168.1.101 hadoop01
      192.168.1.102 hadoop02
      192.168.1.103 hadoop03
      192.168.1.104 hadoop04
      
* 修改/etc/sysconfig/network-scripts/相应的网络配置
      
      ifcfg-eth0文件参考：
      DEVICE="eth0"
      BOOTPROTO="static"
      ONBOOT="yes"
      TYPE="Ethernet"
      IPADDR=192.168.1.101
      PREFIX=24
      GATEWAY=192.168.1.1

      
* 关闭防火墙
      
      #service iptables stop

* 修改windows的hosts文件

      C:\WINDOWS\system32\drivers\etc\hosts

### 六、Step3:SSH免密码登录

* 从namenode到本身及各datanode免密码登录

     * 在各机器上执行
     
            #ssh-keygen  -t rsa　一路回车
            在~/.ssh/生成文件id_rsa  id_rsa.pub
         
     * 在namenode机器上执行：
         
            #cd ~/.ssh/
            #scp id_rsa.pub root@<各datanode的IP>:/home
         
     * 在各datanode机器上执行：
         
            #cd /home/
            #cat id_rsa.pub >>/root/.ssh/authorized_keys

　　Hadoop运行过程中需要管理远端Hadoop守护进程，在Hadoop启动以后，NameNode是通过SSH（Secure Shell）来无密码登录启动和停止各个DataNode上的各种守护进程的同样原理，DataNode上也能使用SSH无密码登录到NameNode。

### 七、Step4:在namenode安装Hadoop

Hadoop的安装位置（HADOOP_HOME）：/HOME/hadoop

* 1、用软件上传hadoop-1.0.4.tar.gz到/home

      #cp hadoop-1.0.4.tar.gz /home

* 2、解压文件

      #cd /home
      #tar -zxvf hadoop-1.0.4.tar.gz
      #mv hadoop-1.0.4 hadoop

* 3、修改/etc/profile

      #vi /etc/profile
      export JAVA_HOME=/home/java
      export HADOOP_HOME=/home/hadoop
      export PATH=$JAVA_HOME/bin:$PATH:$HADOOP_HOME/bin
      保存退出
      #source /etc/profile

### 八、Step5:修改Hadoop的配置文件

* 1、修改conf/hadoop-env.sh

      export JAVA_HOME=/home/java
      export HADOOP_HEAPSIZE=1024
      export HADOOP_PID_DIR=/home/hadoop/pids
      保存退出

* 2、配置conf/core-site.xml，增加以下内容

      <property>
        <name>fs.default.name</name>
        <value>hdfs://hadoop00:9000</value>
      </property>
      <property>
        <name>hadoop.tmp.dir</name>
        <value>/home/hadoop/tmp</value>
      </property>

* 3、配置conf/hdfs-site.xml

      <property>
        <name>dfs.replication</name>
        <value>2</value>
      </property>

* 4、配置conf/mapred-site.xml

      <property>
        <name>mapred.job.tracker</name>
        <value>hdfs://hadoop00:9001/</value>
      </property>

* 5、配置conf/masters

      hadoop00

* 6、配置conf/slaves

      hadoop01
      hadoop02
      hadoop03

### 九、Step6:复制hadoop到各datanode并修改

把hadoop01的hadoop目录、jdk目录、/etc/hosts、/etc/profile复制到hadoop02，hadoop03、hadoop04节点

      #cd $HADOOP_HOME/.
      * .、      #scp -r hadoop hadoop02:/home
      #scp -r hadoop hadoop03:/home
      #scp -r hadoop hadoop04:/home

### 十、Step7:启动/停止Hadoop集群（在hadoop01上）

* 第一次启动Hadoop,必须先格式化namenode
      
      #cd $HADOOP_HOME /bin
      #hadoop namenode –format

* 启动Hadoop

      #cd $HADOOP_HOME/bin
      #./start-all.sh

* 如果启动过程，报错safemode相关的Exception

      执行命令
      # hadoop dfsadmin -safemode leave
      然后再启动Hadoop

* 停止Hadoop

      cd $HADOOP_HOME/bin
      #./stop-all.sh
* 终端查看集群状态：

      #hadoop dfsadmin -report

### 十一、集群成员

* 主从结构masters/slaves

### 十二、增加节点

* 1、修改新节点的/etc/hosts，增加namenode的主机名与IP

* 2、修改namenode的配置文件conf/slaves

      添加新增节点的ip或host

* 3、在新节点的机器上，启动服务

      #cd $HADOOP_HOME/bin
      #./hadoop-daemon.sh start datanode 
      #./hadoop-daemon.sh start tasktracker

* 4、在NameNode节点执行 

      # hadoop  dfsadmin  -refreshNodes

* 5、均衡block

      在新节点上执行(如果是增加多个节点，只需在一个新节点)
      #cd $HADOOP_HOME/bin
      # ./start-balancer.sh 

* 6、注意事项：

     * 1>、必须确保slave的firewall已关闭;
     
     * 2>、确保新的slave的ip已经添加到master及其他slaves的/etc/hosts中，反之也要将master及其他slave的ip添加到新的slave的/etc/hosts中

### 十三、删除节点

* 在hadoop01上修改conf/hdfs-site.xml文件

      <property>
         <name>dfs.hosts.exclude</name>
        <value>/usr/local/hadoop/conf/excludes</value>
       </property>
       
* 确定要下架的机器

      dfs.hosts.exclude定义的文件内容为,每个需要下线的机器，一行一个。

* 强制重新加载配置

      #hadoop dfsadmin  -refreshNodes 

* 关闭节点 

      hadoop dfsadmin -report 

* 可以查看到现在集群上连接的节点

      正在执行Decommission，会显示： 
      Decommission Status : Decommission in progress    
      执行完毕后，会显示： 
      Decommission Status : Decommissioned 

* 再次编辑excludes文件

      一旦完成了机器下架，它们就可以从excludes文件移除了
      登录要下架的机器，会发现DataNode进程没有了，但是TaskTracker依然存在，需要手工处理一下

### 十四、节点的安全机制

* 准许进入dfs.hosts/mapred.hosts

* 必须排除dfs.hosts.exclude/mapred.hosts.exclude

### 十五、安全模式

* 在分布式文件系统启动的时候，开始的时候会有安全模式，当分布式文件系统处于安全模式的情况下，文件系统中的内容不允许修改也不允许删除，直到安全模式结 束。安全模式主要是为了系统启动的时候检查各个DataNode上数据块的有效性，同时根据策略必要的复制或者删除部分数据块。运行期通过命令也可以进入 安全模式。在实践过程中，系统启动的时候去修改和删除文件也会有安全模式不允许修改的出错提示，只需要等待一会儿即可。

* NameNode在启动的时候首先进入安全模式，如果datanode丢失的block达到一定的比例（1- dfs.safemode.threshold.pct），则系统会一直处于安全模式状态即只读状态。 dfs.safemode.threshold.pct（缺省值0.999f）表示HDFS启动的时候，如果DataNode上报的block个数达到了 元数据记录的block个数的0.999倍才可以离开安全模式，否则一直是这种只读模式。如果设为1则HDFS永远是处于SafeMode。

* hadoop dfsadmin -safemode enter | leave | get |wait

### 十六、集群监控——Web

* 查看MapReduce相关

      http://hadoop0:50030/

* 查看HDFS相关

      http://hadoop0:50070/


### 十七、HDFS　Replication实验（一）

      #hadoop fs -put ./movie.tar.gz /test

### 十八、MapReduce在集群下的工作机制

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/hadoop集群搭建/img/MapReduce在集群下的工作机制.png"/></div>

* JobClient的submit Job过程中
  
  * 向JobTracker请求新的作业ID
   
      * 检查程序的输入，输出
      
      * 计算输入分片
   
   * 复制作业资源到Jobtracker的文件系统中。

   * 初始化作业。由作业调度器进行调度。
   
   * 获取输入分片信息，为每个分片创建一个map任务以及reduce

   * TaskTracker心跳JobTracker。以测存活。JobTracker依据“数据本地化”或”机架本地化“分配任务。

   * Jar文件本地化。应用程序所需文件复制到本地磁盘２）解压Jar文件３）新建TaskRunner运行任务。
   
   * TaskRunner启动新JVM运行。

### 十九、Hadoop管理员常用命令

      hadoop job –list  #列出正在运行的Job

      hadoop job –kill <job_id> #kill job

      hadoop fsck / #检查HDFS块状态，是否损坏

      hadoop fsck / -delete #检查HDFS块状态，删除损坏块

      hadoop dfsadmin –report #检查HDFS状态，包括DN信息

      hadoop dfsadmin –safemode enter | leave

### 二十、常见分布模式安装错误

* Namenode与Datanode是互相都可以ssh无密码访问

* 在hosts文件中127.0.0.1 不要与hadoop01一起设置

### 二十一、Sqoop

* Sqoop是一个用来将Hadoop和关系型数据库中的数据相互转移的工具，可以将一个关系型数据库（例如 ： MySQL ,Oracle ,Postgres等）中的数据导进到Hadoop的HDFS中，也可以将HDFS的数据导进到关系型数据库中。

### 二十二、Pig

* Pig是hadoop生态圈的一员，它是一个利用mapreduce进行计算框架。有自己的一套语法PIG latin，可以非常方便的书写计算代码。

* Pig可以将PIG latin转换为mapreduce来执行计算任务
