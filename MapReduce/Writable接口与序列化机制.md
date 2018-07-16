## Writable接口与序列化机制

### 一、序列化概念

* 序列化（Serialization）是指把结构化对象转化为字节流。

* 反序列化（Deserialization）是序列化的逆过程。即把字节流转回结构化对象。

* Java序列化（java.io.Serializable）

### 二、Hadoop序列化的特点

* 序列化格式特点：

    * 紧凑：高效使用存储空间。
    
    * 快速：读写数据的额外开销小
    
    * 可扩展：可透明地读取老格式的数据
    
    * 互操作：支持多语言的交互

* Hadoop的序列化格式：Writable

### 三、Hadoop序列化的作用

* 序列化在分布式环境的两大作用：进程间通信，永久存储。

* Hadoop节点间通信。

  <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/Hadoop%E8%8A%82%E7%82%B9%E9%97%B4%E9%80%9A%E4%BF%A1.png"/></div>
  
  
### 四、Writable接口  
  
Writable接口, 是根据 DataInput 和 DataOutput 实现的简单、有效的序列化对象.
MR的任意Key和Value必须实现Writable接口.
  
   <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/Writable%E6%8E%A5%E5%8F%A3.png"/></div>  
  
MR的任意key必须实现WritableComparable接口
  
   <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/WritableComparable%E6%8E%A5%E5%8F%A3.png"/></div>  
  
  
  
  
  
  
 <div align="center"><img src=""/></div>
