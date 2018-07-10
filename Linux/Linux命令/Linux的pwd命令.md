## pwd命令格式
````
  pwd [OPTION]...
````
  Linux pwd命令用于显示工作目录。执行pwd指令可立刻得知您目前所在的工作目录的绝对路径名称。
## pwd命令常用的参数
````
  -L, --logical     #use PWD from environment, even if it contains symlinks
                    目录连接链接时，输出连接路径
  -P, --physical    #avoid all symlinks    输出物理路径
  --help            #display this help and exit
  --version         #output version information and exit
````
## pwd命令 应用实例
* 查看当前所在目录：
````

````
* 查看链接路径
````
  #pwd 
  /etc/rc.d/init.d
  #pwd -P
  /etc/rc.d/init.d
  #pwd -L
  /etc/init.d
````
* 写到环境变量中
````
  export PS1='[\u@$PWD]\$'
````
这样在命令提示符前方会显示出当前的目录

如下所示：
````

````
