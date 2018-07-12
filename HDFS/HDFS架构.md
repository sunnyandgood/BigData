## HDFS架构

* NameNode
* DataNode
* Secondary NameNode

  <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/HDFS/img/HDFS%20Architecure.png"/></div>
  
* 元数据存储细节  
    
    <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/HDFS/img/%E5%85%83%E6%95%B0%E6%8D%AE%E5%AD%98%E5%82%A8%E7%BB%86%E8%8A%82.png"/></div>   
  
### 一、NameNode

* 是整个文件系统的管理节点。它**维护着整个文件系统的文件目录树**，文件/目录的元信息和每个文件对应的数据块列表。接收用户的操作请求。文件包括：hdfs-site.xml的dfs.name.dir属性
     
     * fsimage:元数据镜像文件。存储某一时段NameNode内存元数据信息。
     
     * edits:操作日志文件。
     
     * fstime:保存最近一次checkpoint的时间

* 以上这些文件是保存在linux的文件系统中。

### 二、NameNode的工作特点

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  <div align="center"><img src=""/></div>
