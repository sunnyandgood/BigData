Linux which命令 通常用于查找文件。可以在PATH变量指定的路径中，搜索某个系统命令的位置，并且返回第一个搜索结果。

## which命令格式
````
which [options] [—] programname [...]
````

## which命令常用的参数

````
-n  指定文件名长度，指定的长度必须大于或等于所有文件中最长的文件名。 

-p  与-n参数相同，但此处的包括了文件的路径。 

-w  指定输出时栏位的宽度。 

-V  显示版本信息
````

## which命令 应用实例
* 1、查找文件位置、路径

````
[root@localhost ~]# which pwd
/bin/pwd
````

which命令的搜索路径是根据环境变量的PATH变量去读取的，因此，如果PATH中没有，则会报无法找到的错误，如下所示

````
[root@localhost ~]# which influx
/usr/bin/which: no influx in (/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:
                  /mnt/softWare/jdk1.7.0_80/bin:/mnt/softWare/jdk1.7.0_80/jre/bin:/root/bin)
````

* 2、显示命令别名

````
[root@localhost ~]# which ll
alias ll='ls -l --color=auto'
	/bin/ls
````

众所周知，Linux中是不提供 ll 这个命令的，一般来说，这个命令是 ls –l 的别名，使用which命令则会显示出其别名信息。

3 、特殊命令

````
[root@localhost ~]# which cd
/usr/bin/which: no cd in (/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:
             /mnt/softWare/jdk1.7.0_80/bin:/mnt/softWare/jdk1.7.0_80/jre/bin:/root/bin)

````

像 cd 命令这种bash内建命令，which命令是搜索不到的。

which命令只能搜索 PATH目录下的可执行命令，并不能识别bash内部的命令，因此，使用 which cd 会报错误信息。
