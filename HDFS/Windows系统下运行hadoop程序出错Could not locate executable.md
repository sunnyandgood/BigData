## Windows系统下运行hadoop、spark程序出错Could not locate executable null\bin\winutils.exe in the Hadoop binaries

#### 在Windows系统下调试Hadoop读写，对linux虚拟机进行相应的操作，运行时报错Failed to locate the winutils binary in the hadoop binary path  java.io.IOException: Could not locate executable null\bin\winutils.exe in the Hadoop binaries. 

  <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/HDFS/img/Windows%E7%B3%BB%E7%BB%9F%E4%B8%8B%E8%BF%90%E8%A1%8Chadoop%E7%A8%8B%E5%BA%8F%E5%87%BA%E9%94%99.png"/></div>

winutils.exe是在Windows系统上需要的hadoop调试环境工具，里面包含一些在Windows系统下调试hadoop、spark所需要的基本的工具类，另外在使用eclipse调试hadoop程序是，也需要winutils.exe，需要配置上面的环境变量。

#### 解决办法：
* （1）下载winutils，注意需要与hadoop的版本相对应。

    * hadoop2.2版本下载[https://github.com/sunnyandgood/BigBata/blob/master/HDFS/hadoop-common-2.2.0.zip](https://github.com/sunnyandgood/BigBata/blob/master/HDFS/hadoop-common-2.2.0.zip)
    
    * hadoop2.6版本下载[https://github.com/sunnyandgood/BigBata/blob/master/HDFS/hadoop-common-2.6.0.zip](https://github.com/sunnyandgood/BigBata/blob/master/HDFS/hadoop-common-2.6.0.zip)
    
    * 下载后，将其解压。

* （2）配置环境变量

   * 增加系统变量HADOOP_HOME，值是下载的zip包解压的目录，我这里解压后将其重命名为hadoop-common-2.2.0
   
            E:\SoftWare\hadoop-common-2.2.0
      
   * 在系统变量path里增加%HADOOP_HOME%\bin
      
   * 重启电脑，使环境变量配置生效，上述问题即可解决。


