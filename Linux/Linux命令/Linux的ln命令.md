Linux ln命令是一个非常重要命令，它的功能是为某一个文件在另外一个位置建立一个同步的链接。

当我们需要在不同的目录，用到相同的文件时，我们不需要在每一个需要的目录下都放一个必须相同的文件，
我们只要在某个固定的目录，放上该文件，然后在 其它的目录下用ln命令链接（link）它就可以，不必重复的占用磁盘空间。
## ln命令格式
````
ln [参数][源文件或目录][目标文件或目录]
````
* 其中参数的格式为
````
[-bdfinsvF] [-S backup-suffix] [-V {numbered,existing,simple}] [--help] [--version] [--]
````
## ln命令常用的参数
* 必要参数：
````
-b 删除，覆盖以前建立的链接
-d 允许超级用户制作目录的硬链接
-f 强制执行
-i 交互模式，文件存在则提示用户是否覆盖
-n 把符号链接视为一般目录
-s 软链接(符号链接)
-v 显示详细的处理过程
````
* 选择参数：
````
-S "-S<字尾备份字符串> "或 "--suffix=<字尾备份字符串>"
-V "-V<备份方式>"或"--version-control=<备份方式>"
--help 显示帮助信息
--version 显示版本信息
````
## ln命令功能 : 

Linux文件系统中，有所谓的链接(link)，我们可以将其视为档案的别名，而链接又可分为两种 : 硬链接(hard link)与软链接(symbolic link)。

* 硬链接的意思是一个档案可以有多个名称，硬链接是存在同一个文件系统中。
* 软链接的方式则是产生一个特殊的档案，该档案的内容是指向另一个档案的位置，软链接可以跨越不同的文件系统。
* 软、硬链接说明　
  * 软链接：不可以删除源文件，删除源文件导致链接文件找不到，出现文件红色闪烁
  * 硬链接：可以删除源文件，链接文件可以正常打开
  
不论是硬链接或软链接都不会将原本的档案复制一份，只会占用非常少量的磁碟空间。
## ln命令 应用实例

一个文件的引用数或链接数由 Linux 系统维护，文件的链接如果是多个，删除其中一个引用，Linux 系统不会删除文件在系统中的数据块，
直到最后一个引用删除后，才会将文件系统中的数据块删除。

* 硬链接
给文件创建硬链接，为test.txt创建硬链接linkHardTest，test.txt与linkHardTest的各项属性相同
````
[root@localhost file1]# ls -il
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 test.txt
[root@localhost file1]# ln test.txt linkHardTest
[root@localhost file1]# ls -il
total 0
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 linkHardTest
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 test.txt
````
* 软链接
给文件创建软链接，为test.txt文件创建软链接linkSoftTest，如果test.txt丢失，linkSoftTest将失效：
````
[root@localhost file1]# ls -il
total 0
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 linkHardTest
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 test.txt
[root@localhost file1]# ln -s test.txt linkSoftTest
[root@localhost file1]# ls -li
total 0
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 linkHardTest
395459 lrwxrwxrwx. 1 root root 8 Jul  5 16:46 linkSoftTest -> test.txt
395458 -rw-r--r--. 2 root root 0 Jul  5 16:33 test.txt
````
