# [hadoop虚拟客户服务器](https://github.com/sunnyandgood/BigData/tree/master/Hadoop%E7%9A%84API%E5%BA%94%E7%94%A8/hadoop01/src/com/action1)


### 一、Barty类

    package com.action1;

    public interface Barty {

      //在接口中定义versionID，在客户端调用时需要使用
      public static final long versionID=1;

      public String sayHI(String name);
    }

### 二、RPCService类

    package com.action1;

    import java.io.IOException;

    import org.apache.hadoop.HadoopIllegalArgumentException;
    import org.apache.hadoop.conf.Configuration;
    import org.apache.hadoop.ipc.RPC;
    import org.apache.hadoop.ipc.RPC.Server;

    public class RPCService implements Barty{

      public String sayHI(String name){
        return "HI~"+name;
      }

      public static void main(String[] args) throws Exception, IOException {
        // TODO Auto-generated method stub
        Configuration configuration=new Configuration();
        //RPC为hadoop提供的工具类，用来创建一个服务
        Server server=new RPC
            //指定创建者，当前为configuration，查看方法要求的参数即可获悉需要传的参数类型
            .Builder(configuration)
            //设置服务器的地址，当前为本机，所以只要本机上存在的IP都可以（真实网卡和虚拟网卡）
            //注意：服务端和客户端都在本机运行时任何本机存在的IP都可以，如果客户端在Linux上运行则需要使用虚拟机网卡的端口号，
                                                                      如当前使用VMnet8，则地址为192.168.2.1
            .setBindAddress("192.168.2.1")
            //设置实例，即要调用谁的方法，当前为RPCServer中的sayHI方法
            .setInstance(new RPCService())
            //设置端口号，需要保证当前端口未被占用
            .setPort(55555)
            //客户端调用服务端的方法需要得到一个代理，在jdk默认的动态代理中需要一个接口，所以当前类需要实现一个接口，
                                                                              Barty即为当前类要实现的接口
            .setProtocol(Barty.class)
            //创建对象
            .build();
        server.start();
      }
    }
    
### 三、RPCClient类

    package com.action1;

    import java.io.IOException;
    import java.net.InetSocketAddress;

    import org.apache.hadoop.conf.Configuration;
    import org.apache.hadoop.ipc.RPC;

    public class RPCClient {

      public static void main(String[] args) throws IOException {

        Barty proxy = RPC
            /**获取代理对象，
             * 参数1为jdk默认自动代理需要的接口，即要调用方法的类实现的接口
             * 参数2为versionID，在接口中定义的versionID，貌似只要有ID就好，值无所谓，有待确认
             * 参数3为服务器设置，参数1为地址（字符串），参数2为服务器端口号，启动服务那边设置的
             * 参数4位configuration对象
             */
            .getProxy(Barty.class, 1,
            new InetSocketAddress("192.168.2.1", 55555), 
            new Configuration());
        String sayHi = proxy.sayHI("tomcat");
        RPC.stopProxy(proxy);
        System.out.println(sayHi);
      }
    }
