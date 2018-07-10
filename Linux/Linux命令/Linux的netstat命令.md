netstat命令是一个网络管理类命令，可以显示IP、TCP、UDP和ICMP协议相关的统计数据，一般用来检验主机端口的网络连接情况。
## netstat命令格式
````
netstat [-acCeFghilMnNoprstuvVwx][-A<网络类型>][--ip]
````
## netstat命令常用的参数
````
-a或--all 显示所有连线中的Socket。
-A<网络类型>或--<网络类型> 列出该网络类型连线中的相关地址。
-c或--continuous 持续列出网络状态。
-C或--cache 显示路由器配置的快取信息。
-e或--extend 显示网络其他相关信息。
-F或--fib 显示FIB。
-g或--groups 显示多重广播功能群组组员名单。
-h或--help 在线帮助。
-i或--interfaces 显示网络界面信息表单。
-l或--listening 显示监控中的服务器的Socket。
-M或--masquerade 显示伪装的网络连线。
-n或--numeric 直接使用IP地址，而不通过域名服务器。
-N或--netlink或--symbolic 显示网络硬件外围设备的符号连接名称。
-o或--timers 显示计时器。
-p或--programs 显示正在使用Socket的程序识别码和程序名称。
-r或--route 显示Routing Table。
-s或--statistice 显示网络工作信息统计表。
-t或--tcp 显示TCP传输协议的连线状况。
-u或--udp 显示UDP传输协议的连线状况。
-v或--verbose 显示指令执行过程。
-V或--version 显示版本信息。
-w或--raw 显示RAW传输协议的连线状况。
-x或--unix 此参数的效果和指定"-A unix"参数相同。
--ip或--inet 此参数的效果和指定"-A inet"参数相同。
````
## 套接口类型：
````
-t ：TCP

-u ：UDP

-raw ：RAW类型

--unix ：UNIX域类型

--ax25 ：AX25类型

--ipx ：ipx类型

--netrom ：netrom类型
````
## 状态说明：
````
LISTEN：侦听来自远方的TCP端口的连接请求

SYN-SENT：再发送连接请求后等待匹配的连接请求（如果有大量这样的状态包，检查是否中招了）

SYN-RECEIVED：再收到和发送一个连接请求后等待对方对连接请求的确认（如有大量此状态，估计被flood攻击了）

ESTABLISHED：代表一个打开的连接

FIN-WAIT-1：等待远程TCP连接中断请求，或先前的连接中断请求的确认

FIN-WAIT-2：从远程TCP等待连接中断请求

CLOSE-WAIT：等待从本地用户发来的连接中断请求

CLOSING：等待远程TCP对连接中断的确认

LAST-ACK：等待原来的发向远程TCP的连接中断请求的确认（不是什么好东西，此项出现，检查是否被攻击）

TIME-WAIT：等待足够的时间以确保远程TCP接收到连接中断请求的确认

CLOSED：没有任何连接状态
````
## netstat命令 应用实例
* 1、查看主机网络连接情况
````
[root@localhost ~]# netstat
Active Internet connections (w/o servers)
Proto Recv-Q Send-Q Local Address               Foreign Address             State      
tcp        0      0 localhost:ssh               localhost:58131             ESTABLISHED 
Active UNIX domain sockets (w/o servers)
Proto RefCnt Flags       Type       State         I-Node Path
unix  2      [ ]         DGRAM                    8865   @/org/kernel/udev/udevd
unix  2      [ ]         DGRAM                    12623  @/org/freedesktop/hal/udev_event
unix  17     [ ]         DGRAM                    12026  /dev/log
unix  2      [ ]         DGRAM                    24392  
unix  3      [ ]         STREAM     CONNECTED     24230  
··················································································
unix  3      [ ]         STREAM     CONNECTED     13929  
unix  2      [ ]         DGRAM                    13501  
unix  2      [ ]         DGRAM                    13465  
unix  2      [ ]         DGRAM                    13383  
unix  2      [ ]         DGRAM                    13381  
unix  3      [ ]         STREAM     CONNECTED     13374  
unix  3      [ ]         STREAM     CONNECTED     13373  
···········································································
unix  3      [ ]         STREAM     CONNECTED     12899  /var/run/acpid.socket
unix  3      [ ]         STREAM     CONNECTED     12898  
unix  3      [ ]         STREAM     CONNECTED     12893  @/var/run/hald/dbus-8Ywl3oTz4Y
unix  3      [ ]         STREAM     CONNECTED     12890  
unix  3      [ ]         STREAM     CONNECTED     12852  @/var/run/hald/dbus-8Ywl3oTz4Y
unix  3      [ ]         STREAM     CONNECTED     12851  
unix  3      [ ]         STREAM     CONNECTED     12618  @/var/run/hald/dbus-ctao2jY4bh
unix  3      [ ]         STREAM     CONNECTED     12617  
unix  3      [ ]         STREAM     CONNECTED     12593  /var/run/dbus/system_bus_socket
unix  3      [ ]         STREAM     CONNECTED     12592  
unix  2      [ ]         DGRAM                    12562  
unix  3      [ ]         STREAM     CONNECTED     12432  /var/run/dbus/system_bus_socket
unix  3      [ ]         STREAM     CONNECTED     12431  
unix  2      [ ]         DGRAM                    12398  
unix  3      [ ]         STREAM     CONNECTED     12299  /var/run/dbus/system_bus_socket
unix  3      [ ]         STREAM     CONNECTED     12298  
unix  2      [ ]         DGRAM                    12297  
unix  3      [ ]         STREAM     CONNECTED     12263  /var/run/dbus/system_bus_socket
unix  3      [ ]         STREAM     CONNECTED     12262  
unix  3      [ ]         STREAM     CONNECTED     12244  /var/run/dbus/system_bus_socket
unix  3      [ ]         STREAM     CONNECTED     12243  
unix  2      [ ]         DGRAM                    12237  
unix  3      [ ]         STREAM     CONNECTED     12211  /var/run/dbus/system_bus_socket
unix  3      [ ]         STREAM     CONNECTED     12210  
unix  3      [ ]         STREAM     CONNECTED     12204  
unix  3      [ ]         STREAM     CONNECTED     12203  
unix  3      [ ]         DGRAM                    8883   
unix  3      [ ]         DGRAM                    8882   

````
说明：

netstat的输出结果可以分为两个部分：

一个是Active Internet connections，称为有源TCP连接，其中"Recv-Q"和"Send-Q"指的是接收队列和发送队列。这些数字一般都应该是0。如果不是则表示软件包正在队列中堆积。

另一个是Active UNIX domain sockets，称为有源Unix域套接口(和网络套接字一样，但是只能用于本机通信，性能可以提高一倍)。

Proto显示连接使用的协议,RefCnt表示连接到本套接口上的进程号,Types显示套接口的类型,State显示套接口当前的状态,Path表示连接到套接口的其它进程使用的路径名。
* 2、显示主机所有端口
````
[root@localhost ~]# netstat -a
Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address               Foreign Address             State      
tcp        0      0 *:ssh                       *:*                         LISTEN      
tcp        0      0 localhost:ipp               *:*                         LISTEN      
tcp        0      0 localhost:smtp              *:*                         LISTEN      
tcp        0     64 localhost:ssh               localhost:58131             ESTABLISHED 
tcp        0      0 *:ssh                       *:*                         LISTEN      
tcp        0      0 localhost:ipp               *:*                         LISTEN      
tcp        0      0 localhost:smtp              *:*                         LISTEN      
udp        0      0 *:bootpc                    *:*                                     
udp        0      0 *:ipp                       *:*                                     
Active UNIX domain sockets (servers and established)
Proto RefCnt Flags       Type       State         I-Node Path
unix  2      [ ACC ]     STREAM     LISTENING     16644  /tmp/orbit-root/linc-8e4-0-370106e1a6966
unix  2      [ ACC ]     STREAM     LISTENING     16671  /tmp/orbit-root/linc-8f1-0-3f34f9b3ae2fd
unix  2      [ ACC ]     STREAM     LISTENING     16756  /tmp/orbit-root/linc-8f9-0-2fa38bdcc4688
·································································································
````
* 3、显示UDP连接状况
````
[root@localhost ~]# netstat -nu
Active Internet connections (w/o servers)
Proto Recv-Q Send-Q Local Address               Foreign Address             State      
````
* 4、显示UDP端口使用情况
````
[root@localhost ~]# netstat -apu
Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address               Foreign Address             State       PID/Program name   
udp        0      0 *:bootpc                    *:*                                     1701/dhclient       
udp        0      0 *:ipp                       *:*                                     1700/cupsd          
````
* 5、显示网卡列表信息
````
[root@localhost ~]# netstat -i
Kernel Interface table
Iface       MTU Met    RX-OK RX-ERR RX-DRP RX-OVR    TX-OK TX-ERR TX-DRP TX-OVR Flg
eth0       1500   0     2605      0      0      0      238      0      0      0 BMRU
lo        16436   0       12      0      0      0       12      0      0      0 LRU
````
* 6、显示所有TCP端口
````
[root@localhost ~]# netstat -at
Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address               Foreign Address             State      
tcp        0      0 *:ssh                       *:*                         LISTEN      
tcp        0      0 localhost:ipp               *:*                         LISTEN      
tcp        0      0 localhost:smtp              *:*                         LISTEN      
tcp        0     64 localhost:ssh               localhost:58131             ESTABLISHED 
tcp        0      0 *:ssh                       *:*                         LISTEN      
tcp        0      0 localhost:ipp               *:*                         LISTEN      
tcp        0      0 localhost:smtp              *:*                         LISTEN      
````
* 7、找出运行在指定端口的进程
````
[root@localhost ~]#netstat -anpt | grep ':80'
tcp        0      0 0.0.0.0:8080                0.0.0.0:*                   LISTEN      1309/monitorix-http 
tcp        0      0 0.0.0.0:80                  0.0.0.0:*                   LISTEN      1128/httpd          
tcp        0      0 192.168.124.17:80           162.244.95.175:32802        TIME_WAIT   -
````
