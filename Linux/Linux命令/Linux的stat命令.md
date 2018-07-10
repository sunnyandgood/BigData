Linux stat命令 用来显示inode内容
## stat命令格式
````
stat [OPTION]... FILE...
````
## stat命令常用的参数
````
-L, --dereference
              follow links

-Z, --context
              print the SELinux security context

-f, --file-system
              display file system status instead of file status

-c  --format=FORMAT
              use the specified FORMAT instead of the default; output a newline after each use of FORMAT

--printf=FORMAT
              like  --format,  but  interpret backslash escapes, and do not output a mandatory trailing newline.  If you
              want a newline, include \n in FORMAT.

-t, --terse
              print the information in terse form

       --help display this help and exit

       --version
              output version information and exit
````
其中用的比较多的是 -f 参数和 -c参数。

-f 参数是显示文件系统信息 ， -c是按照规定格式输出。

其中 -c 的可用参数主要有以下几个：
````
%A：用文件权限代码来表示权限
%a：用数字代码来表示权限
%F：用八进制表示文件权限
%G：文件拥有者的组名 
%g：文件拥有者的属组id(gid) 
%i：inode编号 
%n：文件名 
%s：文件大小 
%U：文件拥有者名称 
%u：文件拥有者的id(uid) 
%x: 取用时间
%y: 修改时间
%z: 属性改动时间
````
## stat命令 应用实例
* 1、查看文件信息
````
[root@localhost Shared]# stat 1.txt 
  File: `1.txt'
  Size: 32        	Blocks: 1          IO Block: 1024   regular file
Device: 12h/18d	Inode: 1507371     Links: 1
Access: (0777/-rwxrwxrwx)  Uid: (    0/    root)   Gid: (    0/    root)
Access: 2018-07-02 18:40:09.287896100 -0700
Modify: 2018-07-04 23:37:32.704427900 -0700
Change: 2018-07-04 23:37:32.704427900 -0700
````
* 2、使用固定格式查看文件access time。
````
[root@localhost Shared]#stat file_new 
  File: `file_new'
  Size: 0             Blocks: 0          IO Block: 4096   regular empty file
Device: ca01h/51713d    Inode: 655637      Links: 1
Access: (0644/-rw-r--r--)  Uid: (  111/linuxdaxue.com)   Gid: (  503/linuxdaxue.com)
Access: 2016-12-31 22:34:50.000000000 +0800
Modify: 2016-12-31 22:34:50.000000000 +0800
Change: 2016-06-16 14:30:33.233275690 +0800
````
说明：

Access time(atime):是指取用文件的时间，所谓取用，常见的操作有：使用编辑器查看文件内容，使用cat命令显示文件内容，使用cp命令把该文件（即来源文件）复制成其他文件，或者在这个文件上运用grep sed more less tail head 等命令，凡是读取而不修改文件的操作，均衡改变文件的Access time.  

Modify time(mtime)：是指修改文件内容的时间，只要文件内容有改动（如使用转向输出或转向附加的方式）或存盘的操作，就会改变文件的Modify time,平常我们使用ls –l查看文件时，显示的时间就是Modify time  

Change time(ctime):是指文件属性或文件位置改动的时间，如使用chmod，chown,mv指令集使用ln做文件的硬是连接，就会改变文件的Change time. 
* 3、查看文件系统信息
````
[root@localhost Shared]# stat -f /dev/
  File: "/dev/"
    ID: 0        Namelen: 255     Type: tmpfs
Block size: 4096       Fundamental block size: 4096
Blocks: Total: 126762     Free: 126706     Available: 126706
Inodes: Total: 126762     Free: 126093
````
