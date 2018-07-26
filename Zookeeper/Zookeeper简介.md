# Zookeeper简介

### 一、Zookeeper的数据模型

* 层次化的目录结构，命名符合常规文件系统规范

* 每个节点在zookeeper中叫做znode,并且其有一个唯一的路径标识

* 节点Znode可以包含数据和子节点，但是EPHEMERAL类型的节点不能有子节点

* Znode中的数据可以有多个版本，比如某一个路径下存有多个数据版本，那么查询这个路径下的数据就需要带上版本

* 客户端应用可以在节点上设置监视器

* 节点不支持部分读写，而是一次性完整读写

### 二、Zookeeper的节点

* Znode有两种类型，短暂的（ephemeral）和持久的（persistent）

* Znode的类型在创建时确定并且之后不能再修改

* 短暂znode的客户端会话结束时，zookeeper会将该短暂znode删除，短暂znode不可以有子节点

* 持久znode不依赖于客户端会话，只有当客户端明确要删除该持久znode时才会被删除

* Znode有四种形式的目录节点，PERSISTENT、PERSISTENT_SEQUENTIAL、EPHEMERAL、EPHEMERAL_SEQUENTIAL

### 三、Zookeeper的角色

* 领导者（leader），负责进行投票的发起和决议，更新系统状态

* 学习者（learner），包括跟随者（follower）和观察者（observer），follower用于接受客户端请求并想客户端返回结果，在选主过程中参与投票

* 观察者（Observer），可以接受客户端连接，将写请求转发给leader，但observer不参加投票过程，只同步leader的状态，observer的目的是为了扩展系统，提高读取速度

* 客户端（client），请求发起方

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/Zookeeper/img/zookeeper2.png"/></div>

### 四、Zookeeper的顺序号

* 创建znode时设置顺序标识，znode名称后会附加一个值

* 顺序号是一个单调递增的计数器，由父节点维护

* 在分布式系统中，顺序号可以被用于为所有的事件进行全局排序，这样客户端可以通过顺序号推断事件的顺序

### 五、Zookeeper的读写机制

* Zookeeper是一个由多个server组成的集群

* 一个leader，多个follower

* 每个server保存一份数据副本

* 全局数据一致

* 分布式读写

* 更新请求转发，由leader实施

### 六、Zookeeper的保证

* 更新请求顺序进行，来自同一个client的更新请求按其发送顺序依次执行

* 数据更新原子性，一次数据更新要么成功，要么失败

* 全局唯一数据视图，client无论连接到哪个server，数据视图都是一致的

* 实时性，在一定事件范围内，client能读到最新数据

### 七、Zookeeper的API接口

* String create(String path, byte[] data, List<ACL> acl, CreateMode createMode) 

* Stat exists(String path, boolean watch) 

* void delete(String path, int version) 

* List<String> getChildren(String path, boolean watch) 

* List<String> getChildren(String path, boolean watch) 

* Stat setData(String path, byte[] data, int version) 

* byte[] getData(String path, boolean watch, Stat stat) 

* void addAuthInfo(String scheme, byte[] auth) 

* Stat setACL(String path, List<ACL> acl, int version) 

* List<ACL> getACL(String path, Stat stat) 

### 八、观察（watcher）

* Watcher 在 ZooKeeper 是一个核心功能，Watcher 可以监控目录节点的数据变化以及子目录的变化，一旦这些状态发生变化，服务器就会通知所有设置在这个目录节点上的 Watcher，从而每个客户端都很快知道它所关注的目录节点的状态发生变化，而做出相应的反应 

* 可以设置观察的操作：exists,getChildren,getData

* 可以触发观察的操作：create,delete,setData

### 九、写操作与zookeeper内部事件的对应关系

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/Zookeeper/img/写操作与zookeeper内部事件的对应关系.png"/></div>
