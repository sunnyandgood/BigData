## RPC(Remote Procedure Call远程程序调用)及HDFS的读写过程

### 一、Remote Procedure Call
* RPC——远程过程调用协议，它是一种通过网络从远程计算机程序上请求服务，而不需要了解底层网络技术的协议。RPC协议假定某些传输协议的存在，如TCP或UDP，为通信程序之间携带信息数据。在OSI网络通信模型中，RPC跨越了传输层和应用层。RPC使得开发包括网络分布式多程序在内的应用程序更加容易。

* RPC采用客户机/服务器模式。请求程序就是一个客户机，而服务提供程序就是一个服务器。首先，客户机调用进程发送一个有进程参数的调用信息到服务进程，然后等待应答信息。在服务器端，进程保持睡眠状态直到调用信息的到达为止。当一个调用信息到达，服务器获得进程参数，计算结果，发送答复信息，然后等待下一个调用信息，最后，客户端调用进程接收答复信息，获得进程结果，然后调用执行继续进行。

* hadoop的整个体系结构就是构建在RPC之上的(见org.apache.hadoop.ipc)。

### 二、HDFS读过程
* 1、初始化FileSystem，然后客户端(client)用FileSystem的open()函数打开文件

* 2、FileSystem用RPC调用元数据节点，得到文件的数据块信息，对于每一个数据块，元数据节点返回保存数据块的数据节点的地址。

* 3、FileSystem返回FSDataInputStream给客户端，用来读取数据，客户端调用stream的read()函数开始读取数据。

* 4、DFSInputStream连接保存此文件第一个数据块的最近的数据节点，data从数据节点读到客户端(client)

* 5、当此数据块读取完毕时，DFSInputStream关闭和此数据节点的连接，然后连接此文件下一个数据块的最近的数据节点。

* 6、当客户端读取完毕数据的时候，调用FSDataInputStream的close函数。

* 7、在读取数据的过程中，如果客户端在与数据节点通信出现错误，则尝试连接包含此数据块的下一个数据节点。

* 8、失败的数据节点将被记录，以后不再连接。


     <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/HDFS%E8%AF%BB%E8%BF%87%E7%A8%8B.png"/></div>


          public static void main(String[] args) throws Exception {
               // TODO Auto-generated method stub
               
               FileSystem fs = FileSystem.get(new URI("hdfs://hadoop01:9000"),new Configuration());
               InputStream in = fs.open(new Path("/dianying.mp4"));
               OutputStream out = new  FileOutputStream(new File("c:/dianying.mp4"));
               IOUtils.copyBytes(in, out, 4096, true);		
	     }


### 三、HDFS写过程

* 1、初始化FileSystem，客户端调用create()来创建文件

* 2、FileSystem用RPC调用元数据节点，在文件系统的命名空间中创建一个新的文件，元数据节点首先确定文件原来不存在，并且客户端有创建文件的权限，然后创建新文件。

* 3、FileSystem返回DFSOutputStream，客户端用于写数据，客户端开始写入数据。

* 4、DFSOutputStream将数据分成块，写入data queue。data queue由Data Streamer读取，并通知元数据节点分配数据节点，用来存储数据块(每块默认复制3块)。分配的数据节点放在一个pipeline里。Data Streamer将数据块写入pipeline中的第一个数据节点。第一个数据节点将数据块发送给第二个数据节点。第二个数据节点将数据发送给第三个数据节点。

* 5、DFSOutputStream为发出去的数据块保存了ack queue，等待pipeline中的数据节点告知数据已经写入成功。

* 6、当客户端结束写入数据，则调用stream的close函数。此操作将所有的数据块写入pipeline中的数据节点，并等待ack queue返回成功。最后通知元数据节点写入完毕。

* 7、如果数据节点在写入的过程中失败，关闭pipeline，将ack queue中的数据块放入data queue的开始，当前的数据块在已经写入的数据节点中被元数据节点赋予新的标示，则错误节点重启后能够察觉其数据块是过时的，会被删除。失败的数据节点从pipeline中移除，另外的数据块则写入pipeline中的另外两个数据节点。元数据节点则被通知此数据块是复制块数不足，将来会再创建第三份备份。
 
    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HDFS/img/HDFS%E5%86%99%E8%BF%87%E7%A8%8B.png"/></div>


     	FileSystem fs=null;
	
          @Before
          public void init() throws Exception{
               fs= FileSystem.get(new URI("hdfs://hadoop01:9000"),new Configuration(),"root");
          }

          @Test
          public void testUpLoad() throws Exception{
               OutputStream out = fs.create(new Path("/Xshellqqq"));
               InputStream in = new FileInputStream(new File("c:/Xshell-5.0.1337p.exe"));
               IOUtils.copyBytes(in, out, 4096, true);
          }

          @Test
          public void testCopyFromLocalFile() throws IllegalArgumentException, IOException{
               fs.copyFromLocalFile(new Path("c:/Xshell-5.0.1337p.exe"), new Path("/1132/Xshellaaa"));
          }


### 四、问题

* 1、hdfs的组成部分有哪些，分别解释一下

* 2、hdfs的高可靠如何实现

* 3、hdfs的常用shell命令有哪些

* 4、hdfs的常用java api有哪些

* 5、请用shell命令实现目录、文件的增删改查

* 6、请用java api实现目录、文件的增删改查
