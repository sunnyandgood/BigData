### touch命令格式
    touch [OPTION]... FILE...
    
   通过命令 touch 可以创建一个空的文本文件，如用户的配置文件。也可以创建一个日志文件，记录程序运行过程中的特定信息。
## touch命令常用的参数：
````
  -a   或--time=atime或--time=access或--time=use 　只更改存取时间。
  -c   或--no-create 　不建立任何文档。
  -d   设定时间与日期，可以使用各种不同的格式。
  -f 　此参数将忽略不予处理，仅负责解决BSD版本touch指令的兼容性问题。
  -m   或--time=mtime或--time=modify 　只更改变动时间。
  -r 　把指定文档或目录的日期时间，统统设成和参考文档或目录的日期时间相同。
  -t 　使用指定的日期时间，而非现在的时间，格式与 date 指令相同。
  --help 列出指令格式。
  --version 列出版本讯息。
````
## touch命令详解
````
    [root@localhost mnt]# touch test01
    [root@localhost mnt]# ls
    hgfs  test01
    [root@localhost mnt]# ls -li
    total 5
         1 dr-xr-xr-x. 1 root root 4192 Jul  3 04:02 hgfs
    393222 -rw-r--r--. 1 root root    0 Jul  3 12:02 test01
````
