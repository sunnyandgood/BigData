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
