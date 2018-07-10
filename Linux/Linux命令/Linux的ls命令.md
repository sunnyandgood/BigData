## ls命令格式
    ls [-alrtAFR] [name...]
    
  ls是英⽂单词list的简写，其功能为列出⽬录的内容，是⽤户最常⽤的命令之⼀，它类似于DOS下的dir命令,ls 命令只显示当前目录下的非隐藏文件，并且是只显示基本的文件信息。
## ls 命令常用的参数：
````
-a 显示所有文件及目录 (ls内定将文件名或目录名称开头为"."的视为隐藏档，不会列出) 

-l 除文件名称外，亦将文件型态、权限、拥有者、文件大小等资讯详细列出 

-i 显示文件的索引值，在系统中，每个文件都有一个唯一的索引值，并且文件的属主是当前用户。

-r 将文件以相反次序显示(原定依英文字母次序) 

-t 将文件依建立时间之先后次序列出 

-A 同 -a ，但不列出 "." (目前目录) 及 ".." (父目录) 

-F 在列出的文件名称后加一符号；例如可执行档则加 "*", 目录则加 "/" 

-R 若目录下有文件，则以下之文件亦皆依序列出
````

### ls基本显示
* `ls -a`

    >For example
````
    [root@localhost ~]# ls -a
    .                .config    .gconfd          .ICEauthority       Public
    ..               .cshrc     .gnome2          install.log         .pulse
    anaconda-ks.cfg  .dbus      .gnome2_private  install.log.syslog  .pulse-cookie
    .bash_history    Desktop    .gnote           .local              .ssh
    .bash_logout     Documents  .gnupg           .mozilla            .tcshrc
    .bash_profile    Downloads  .gstreamer-0.10  Music               Templates
    .bashrc          .esd_auth  .gtk-bookmarks   .nautilus           Videos
    .cache           .gconf     .gvfs            Pictures
````
* `ls -l`

    >For example
````
    [root@localhost ~]# ls -l
    total 92
    -rw-------. 1 root root  3338 Jul  3 02:32 anaconda-ks.cfg
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Desktop
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Documents
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Downloads
    -rw-r--r--. 1 root root 39935 Jul  3 02:32 install.log
    -rw-r--r--. 1 root root  9154 Jul  3 02:31 install.log.syslog
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Music
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Pictures
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Public
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Templates
    drwxr-xr-x. 2 root root  4096 Jul  3 02:50 Videos
````
* `ls -i`

    >For example
````
[root@localhost ~]# ls -i
266659 anaconda-ks.cfg  262147 install.log         266669 Public
266666 Desktop          262148 install.log.syslog  266668 Templates
266670 Documents        266671 Music               266673 Videos
266667 Downloads        266672 Pictures
````
* `ls -r`

    >For example
````
    [root@localhost ~]# ls -r
    Videos     Pictures            install.log  Desktop
    Templates  Music               Downloads    anaconda-ks.cfg
    Public     install.log.syslog  Documents
````    
* `ls -t`

    >For example
````
[root@localhost ~]# ls -t
Documents  Pictures  Templates  Desktop    anaconda-ks.cfg  install.log.syslog
Music      Public    Videos     Downloads  install.log
````    
* `ls -A`

    >For example
````
    [root@localhost ~]# ls -A
    anaconda-ks.cfg  .dbus      .gnome2_private  install.log.syslog  .pulse-cookie
    .bash_history    Desktop    .gnote           .local              .ssh
    .bash_logout     Documents  .gnupg           .mozilla            .tcshrc
    .bash_profile    Downloads  .gstreamer-0.10  Music               Templates
    .bashrc          .esd_auth  .gtk-bookmarks   .nautilus           Videos
    .cache           .gconf     .gvfs            Pictures
    .config          .gconfd    .ICEauthority    Public
    .cshrc           .gnome2    install.log      .pulse
````    
* `ls -F`

    >For example
````
    [root@localhost ~]# ls -F
    anaconda-ks.cfg  Documents/  install.log         Music/     Public/     Videos/
    Desktop/         Downloads/  install.log.syslog  Pictures/  Templates/
````    
* `ls -R`

    >For example
````
    [root@localhost ~]# ls -R
    .:
    anaconda-ks.cfg  Documents  install.log         Music     Public     Videos
    Desktop          Downloads  install.log.syslog  Pictures  Templates

    ./Desktop:

    ./Documents:

    ./Downloads:

    ./Music:

    ./Pictures:

    ./Public:

    ./Templates:

    ./Videos:
````    
### ls过滤显示
    如果想在多个文件中只显示相关的文件信息，可以采用下面的几种方式。
* 1、ls install.log -l (或 ls -l install.log）
>For example
````
    [root@localhost ~]# ls install.log -l
    -rw-r--r--. 1 root root 39935 Jul  3 02:32 install.log
    [root@localhost ~]# ls -l install.log
    -rw-r--r--. 1 root root 39935 Jul  3 02:32 install.log 
````
* 2、ls -l instal*  和  ls -l instal?

      ?号占位符代表一个字符，*号占位符代表零个到多个字符。
      
>For example
````
    [root@localhost ~]# ls -l instal?
    ls: cannot access instal?: No such file or directory
    
    [root@localhost ~]# ls -l instal*
    -rw-r--r--. 1 root root 39935 Jul  3 02:32 install.log
    -rw-r--r--. 1 root root  9154 Jul  3 02:31 install.log.syslog
````
* 3、命令 ls 还可以指定显示某个目录下存在的文件内容。
>For example
````
    [root@localhost ~]# ls -l  /mnt/hgfs/Shared
    total 1
    -rwxrwxrwx. 1 root root 14 Jul  2 18:41 1.txt
    drwxrwxrwx. 1 root root  0 Jul  3 02:53 2
````
### 通配符
````
通配符                 含义
*               ⽂件代表⽂件名中所有字符
ls te*          查找以te开头的⽂件
ls *html        查找结尾为html的⽂件
？              代表⽂件名中任意⼀个字符
ls ?.c          只找第⼀个字符任意，后缀为.c的⽂件
ls a.?          只找只有3个字符，前2字符为a.，最后⼀个字符任意的⽂件
[]              [”和“]”将字符组括起来，表示可以匹配字符组中的任意⼀个。“-”⽤于表示字符范围。
[abc]           匹配a、b、c中的任意⼀个
[a-f]           匹配从a到f范围内的的任意⼀个字符
ls [af]*        找到从a到f范围内的的任意⼀个字符开头的⽂件
ls a-f          查找⽂件名为a-f的⽂件,当“-”处于⽅括号之外失去通配符的作⽤
\               如果要使通配符作为普通字符使⽤，可以在其前⾯加上转义字符。
                “?”和“*”处于⽅括号内时不⽤使⽤转义字符就失去通配符的作⽤。
ls \*a          查找⽂件名为*a的⽂件
````
### 输出重定向命令：>

Linux允许将命令执⾏结果重定向到⼀个⽂件，本应显示在终端上的内容保存到指定⽂件中。

        如：ls > test.txt ( test.txt 如果不存在，则创建，存在则覆盖其内容 )
