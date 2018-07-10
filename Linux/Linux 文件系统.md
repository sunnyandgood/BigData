## Linux 文件系统
Linux 系统区别于 Windows 系统，将所有的文件存储在单个目录结构中，也就是所有的
目录都基于一个根目录。

* Linux目录结构
<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/Linux%20/img/Linux%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.jpg"/></div>

树状目录结构解释如下:

/ 根目录； 文件系统的入口，最高一级目录,只存放目录，不存放文件,只有root用户具有该目录下的写权限。
````
___/bin (binary二进制) 常用Linux命令

___/boot 存放着启动Linux时使用的一些核心文件，包括一些链接文件以及镜像文件

___/cdrom 这个目录在你刚刚安装系统的时候是空的。你可以将光驱文件系统挂在这个目录下。
            例如：mount /dev/cdrom /cdrom 

___/sbin 存放管理员使用的系统管理程序

___/home 存放用户主目录。如果我们建立一个用户，用户名是"xx",
           那么在/home目录下就有一个对应的/home/xx路径

___/lib (library库)存放系统动态连接共享库

___/lost+found 一般情况下是空的，当系统非法关机后，这里就存放了一些文件

___/mnt 挂载其它文件系统

___/root 系统管理员（超级用户）主目录

___/shlib 使用运行SCO UNIX程序的iBCS2用的

___/tmp (temporary临时)存放不同程序执行时产生的临时文件,系统会周期性地清除里面的内容

___/vmlinuz 存放的是系统的内核

___/dev (device设备) 该目录下存放的是Linux的外部设备，
          在Linux中访问设备的方式和访问文件的方式是相同的 
        _____/dev/console 系统控制台
        _____/dev/fd0 第一个软盘驱动器
        _____/dev/hda1 第一个IDE硬盘驱动器的第一个逻辑分区
        _____/dev/hdb3 第二个IDE硬盘驱动器的第三个逻辑分区
        _____/dev/ttys0 第一个串行端口
        _____/dev/lp0 第一个并行端口
        _____/dev/cua 用来连接调制解调器
        _____/dev/sda 第一个SCSI硬盘驱动器
        _____/dev/null 是一个接受设备输入但不产生任何输出的虚拟的设备
        _____/dev/tty1 第一个虚拟控制台
        _____/dev/pty* “虚拟终端”，用于为远程登录提供终端

___/usr 用户应用程序文件夹
        _____/usr/X11R6 存放X-Windows的目录 
        _____/usr/bin 用户用到的大多数应用程序 
        _____/usr/sbin 给超级用户使用的比较高级的管理程序和系统守护程序 
        _____/usr/doc 文档 
        _____/usr/include Linux下开发或编译应用程序需要用的头文件 
        _____/usr/openwin 存放SUN的OpenWin 
        _____/usr/lib 常用的动态、静态库 
        _____/usr/local 普通用户、超级用户安装新软件使用 
        _____/usr/man 联机帮助手册 
        _____/usr/src 系统源代码，(/usr/src/linux系统源代码)

___/etc (etcetera) 系统管理的配置文件和子目录 
        _____/etc/DIR_COLORS 设定颜色 
        _____/etc/HOSTNAME 设定用户的节点名 
        _____/etc/NETWORKING 只有YES标明网络存在
        _____/etc/host.conf 文件说明用户的系统如何查询节点名 
        _____/etc/hosts 设定用户自已的IP与名字的对应表 
        _____/etc/hosts.allow 设置允许使用inetd的机器使用 
        _____/etc/hosts.deny 设置不允许使用inetd的机器使用 
        _____/etc/hosts.equiv 设置远端机不用密码 
        _____/etc/inetd.conf 设定系统网络守护进程inetd的配置 
        _____/etc/inetd.pid inetd这个进程的进程id 
        _____/etc/hosts.lpd 设定远端有哪些节点可以使用本机的打印机 
        _____/etc/gateways 设定路由器 
        _____/etc/protocols 设定系统支持的协议 
        _____/etc/named.boot 设定本机为名字服务器的配置文件 
        _____/etc/named.pid 本机上运行的名字服务器的进程id 
        _____/etc/networks 设定网络的配置文件 
        _____/etc/resolv.conf 设定系统的名字服务器 
        _____/etc/services 设定系统的端品与协议类型和提供的服务 
        _____/etc/exports 设定NFS系统用的 
        _____/etc/NNTP_INEWS_DOMAIN 设置新闻服务器的配置文件 
        _____/etc/nntpserver 设置用户使用的新闻服务器的地址 
        _____/etc/XF86Config X Window的配置文件 
        _____/etc/hostid 系统独有的一个硬件id 
        _____/etc/at.deny 设置哪些用户不能使用at命令 
        _____/etc/bootptab 给MAKEDEV程序设定各种不同的设备驱动文件的格式 
        _____/etc/makedev.cfg 同DEVINFO一样给MAKEDEV使用的设置文件 
        _____/etc/diphosts 设置拔号服务器的用户名和口令 
        _____/etc/slip.hosts,/etc/slip.login 设定SLIP的配置文件 
        _____/etc/fastboot 使用shutdown -f产生的，重启系统要查这个文件 
        _____/etc/fstab 记录开机要mount的文件系统 
        _____/etc/mtab 系统在启动时创建的信息文件 
        _____/etc/ftpaccess FTP服务器的一些配置 
        _____/etc/ftpconversions 设定在FTP时使用的过滤器的位置 
        _____/etc/ftpusers 设定不能使用FTP服务的用户 
        _____/etc/inittab 设定系统启动时init进程将把系统设置成什么样的runlevel 
        _____/etc/ld.so.cache 查找系统动态链接库的缓存 
        _____/etc/ld.so.conf 系统动态链接库的路径 
        _____/etc/lilo.conf lilo的配置文件 
        _____/etc/magic 给file命令使用的 
        _____/etc/aliases 给sendmail使用的设置别名的文件 
        _____/etc/mail.rc,
                  /etc/mailcap,
                  /etc/sendmail.cf,
                  /etc/sendmail.st 设置sendmail的 
        _____/etc/issue 记录用户登录前显示的信息 
        _____/etc/motd 超级用户发布通知的地方 
        _____/etc/organization 存放用户的名字和组织 
        _____/etc/group 设定用户的组名与相关信息 
        _____/etc/passwd 用户密码文件(重要呀) 
        _____/etc/shadow 见/etc/passwd 
        _____/etc/pnpdevices 列出支持的Plug&Play设备 
        _____/etc/snooptad 监控用户的屏幕，监听的终端列表 
        _____/etc/sudoers 可以sudo命令的配置文件 
        _____/etc/syslog.conf 系统记录程序syslogd的配置文件 
        _____/etc/utmp 目前在用系统的用户信息 
        _____/etc/wtmp 同utmp差不多，只是它累加 
        _____/etc/nologin 系统在shutdown时不希望用户登录就产生这个文件 
        _____/etc/securetty 设定哪些终端可以让root登录 
        _____/etc/termcap 设置系统终端信息的 
        _____/etc/ttys 设定系统的终端类型 
        _____/etc/gettydefs getty_ps的定义文件 
        _____/etc/yp.conf NIS的配置文件 
        _____/etc/mtools.conf 设定mtools程序的参数 
        _____/etc/fdprm 设定格式化软盘的参数 
        _____/etc/login.access 控制用户登录权限的文件 
        _____/etc/login.defs 所有用户登录时的缺省配置文件

___/proc 虚拟系统，内存映象
        ____/proc/1 关于进程1的信息目录。每个进程在/proc 下有一个名为其进程号的目录。 
        _____/proc/cpuinfo 处理器信息，如类型、制造商、型号和性能。 
        _____/proc/devices 当前运行的核心配置的设备驱动的列表。 
        _____/proc/dma 显示当前使用的DMA通道。 
        _____/proc/filesystems 核心配置的文件系统。 
        _____/proc/interrupts 显示使用的中断
        _____/proc/ioports 当前使用的I/O端口。 
        _____/proc/kcore 系统物理内存映象
        _____/proc/kmsg 核心输出的消息。也被送到syslog 。 
        _____/proc/ksyms 核心符号表。 
        _____/proc/loadavg 系统"平均负载"；3个指示器指出系统当前的工作量。 
        _____/proc/meminfo 存储器使用信息，包括物理内存和swap。 
        _____/proc/modules 当前加载了哪些核心模块。 
        _____/proc/net 网络协议状态信息。 
        _____/proc/self 到查看/proc 的程序的进程目录的符号连接。当2个进程查看/proc 时， 
                         是不同的连接。这主要便于程序得到它自己的进程目录。     
        _____/proc/stat 系统的不同状态
        _____/proc/uptime 系统启动的时间长度。 
        _____/proc/version 核心版本。

___/var 存放着那些不断在扩充着的东西，为了保/usr的相对稳定，那些经常被修改的目录可以
          放在这个目录下。包括系统一般运行时要改变的数据。
   每个系统是特定的，即不通过网络与其他计算机共享。
        _____/var/adm 
        _____/var/catman 当要求格式化时的man页的cache。
           man页的源文件一般存在/usr/man/man* 中；有些man页可能有预格式化的版本，
     存在/usr/man/cat* 中。而其他的man页在第一次看时需要格式化，
     格式化完的版本存在/var/man 中，这样其他人再看相同的页时就无须等待
     格式化了。 (/var/catman 经常被清除，就象清除临时目录一样。) 
_____/var/lib 系统正常运行时要改变的文件。 
_____/var/local    /usr/local 中安装的程序的可变数据(即系统管理员安装的程序)。
               注意，如果必要，即使本地安装的程序也会使用其他/var 目录，
        例如/var/lock 。 /var/lock 锁定文件。
        许多程序遵循在/var/lock 中产生一个锁定文件的约定，
        以支持他们正在使用某个特定的设备或文件。
        其他程序注意到这个锁定文件，将不试图使用这个设备或文件。 
_____/var/log 各种程序的Log文件，特别是login (/var/log/wtmp log所有到系统
      的登录和注销) 和syslog (/var/log/messages 里存储所有核心和系统程序信息。) 
                /var/log 里的文件经常不确定地增长，应该定期清除。 
_____/var/run 保存到下次引导前有效的关于系统的信息文件。例如， /var/run/utmp 
                包含当前登录的用户的信息。
_____/var/spool mail, news, 打印队列和其他队列工作的目录。
              每个不同的spool在/var/spool 下有自己的子目录，
              例如，用户的邮箱在/var/spool/mail 中。 
_____/var/tmp 比/tmp 允许的大或需要存在较长时间的临时文件。 
               (虽然系统管理员可能不允许/var/tmp 有很旧的文件。)
````
