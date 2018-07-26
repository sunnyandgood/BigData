# hadoop集群搭建


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

