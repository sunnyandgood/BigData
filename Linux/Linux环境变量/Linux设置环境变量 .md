## 设置环境变量 

系统环境变量通常不需要用户来设置，当用户登录系统时，系统环境变量会由系统**自动加载**到内存，但是 PATH 系统环境变量在有些情况下需要用户**手动设置**，如JAVA_HOME。所以，大多数情况下设置环境变量是针对用户特定环境下来讲的。
### 一、设置用户局部变量
* 1、设置局部环境变量可以通过变量名=值的形式进行设置，但是需要注意的是等号的两端不能存在空格。另外，变量的值也不能存在空格，如果存在空格，需要使用引号，如下：
  * 变量说明：
    <table>
      <tr>
        <td>变量赋值</td>
        <td>说明</td>
      </tr>
      <tr>
        <td>var = abc</td>
        <td>错！等号两端不能存在空格</td>
      </tr>
      <tr>
        <td>var=abc</td>
        <td>对</td>
      </tr>
       <tr>
        <td>var=abc d ef</td>
        <td>错！值的中间有空格</td>
      </tr>
      <tr>
        <td>var="abc d ef" </td>
        <td>对</td>
      </tr>
      <tr>
        <td>var='abc d ef</td>
        <td>对</td>
      </tr> 
    </table>

* 2、**引用环境变量需要使用$符，如$var**。用户的局部变量通常用小写字符串声明，用于区别全局环境变量。局部变量只能在当前进程中可见，如果创建另一个进程，这个变量就不可见了，如下：

          $ var1="Hello bash shell"
          $ var2=100
          $ echo $var1
          Hello bash shell
          $ echo "$var1"
          Hello bash shell
          $ echo $var2
          100
          $ echo "$var2"
          100
          $ echo '$var2'
          $var2
          # 重新启动一个 Shell 进程
          $ bash
          $ echo "$var1"
          $ echo "$var2"
          # 退出当前 Shell 进程，回到父进程
          $ exit
          exit
          $ echo "$var1"
          Hello bash shell
          $ echo "$var2"
          100    
    **注意：当使用变量时，不能用单引号包裹变量名称，如果使用单引号进行包裹，则表示当前为字符串**    
    
* bash 命令会在当前 Shell 进程中启动一个新的子进程，exit 命令会结束当前进程并回到启动此进程的父进程中，所以在父进程中声明的局部变量只能在父进程是可见的，在子进程中不可见。当执行 exit 命令后，子进程结束并返回父进程，所以父进程中的局部变量又可见了。
    
### 二、设置用户全局变量
* 全局环境变量在创建他的进程中和此进程启动的所有子进程中都是可见的，创建全局变量需要用到**命令 export**，export 命令会将变量保存到内存，下面修改上面的例子说明全局变量设置和特点。

          $ var1="Hello bash shell"
          $ var2=100
          $ export var1
          $ export var2
          $ echo "$var1"
          Hello bash shell
          $ echo "$var2"
          100
          # 打开一个新的子进程
          $ bash
          $ echo "$var1"
          Hello bash shell
          $ echo "$var2"
          100
          # 结束子进程
          $ exit
          $ echo "$var1"
          Hello bash shell
          $ echo "$var2"
          100
 
     **export 命令将变量保存到内存，所以在子进程中是可以访问到的**。    
### 三、删除环境变量
* 删除环境变量需要使用 **unset 命令**，命令将变量删除掉，如下：

        $ var1="Hello bash shell"
        $ var2=100
        $ echo "$var1"
        Hello bash shell
        $ echo "$var2"
        100    
        $ unset var1
        $ unset var2
        $ echo "$var1"
        $ echo "$var2"

* 创建两个变量并赋值后，使用 unset 命令将变量删除，再访问变量时返回空值。需要注意的是在子进程中删除全局变量后只在子进程中有效，父进程中变量依然存在，如下：

        $ var="Hello bash shell"
        $ export var
        $ bash
        $ echo "$var"
        Hello bash shell
        # 在子进程中删除变量
        $ unset var
        $ echo "$var"
        # 退出子进程
        $ exit
        exit
        $ echo "$var"
        Hello bash shell
        
### 四、PATH 环境变量
* PATH 环境变量定义了命令行输入命令的搜索路径。通常情况下会将可执行文件的目录设置到 PATH 变量中，当执行命令时，系统会从变量 PATH 中的目录开始寻找命令并执行。那么如何设置 PATH 环境变量呢！下面通过例子说明：

      $ echo "$PATH"
      /usr/local/jdk1.8.0_51/bin:/usr/local/jdk1.8.0_51/jre/bin:/usr/lib/qt-3.3/bin:/usr/local/bin:/usr/
      bin:/bin:/usr/local/sbin:/usr/sbin:/sbin:/home/hadoop01/bash01:/usr/local/hadoop/sbin:/usr/local/h
      adoop/bin:/home/yarn/bin:/home/hadoop01/bash01
      $ PATH=$PATH:/home/hadoop01/bash02
      $ echo "$PATH"
      /usr/local/jdk1.8.0_51/bin:/usr/local/jdk1.8.0_51/jre/bin:/usr/lib/qt-3.3/bin:/usr/local/bin:/usr/
      bin:/bin:/usr/local/sbin:/usr/sbin:/sbin:/home/hadoop01/bash01:/usr/local/hadoop/sbin:/usr/local/h
      adoop/bin:/home/hadoop01/bin:/home/yarn/bash01:/home/hadoop01/bash02
    
     这样设置 PATH，注意：当重启系统后/home/hadoop01/bash02 目录不会在 PATH 变量中，所以通常将 PATH 变量设置在文件中，当系统启动后会执行这些文件，而不用每次通过手动设置环境变量。

* 例子：在当前目录下创建一个简单的脚本文件，在当前目录下执行系统可以找到文件并执行，但是在其他目录下执行此脚本系统则提示命令找不到。然后将当前目录的完整路径设置到环境变量 PATH 中，脚本文件 print01.sh，使用 vi 编辑器编辑。

       echo "Hello Linux"
       echo $name

     * 在终端测试过程：
     
           # 切换到脚本文件的目录
           [root@localhost ~]# cd class02
           [root@localhost mnt]# ls
           print01.sh
           # 执行脚本
           [root@localhost mnt]# bash print01.sh
           Hello Linux
           [root@localhost mnt]# bash ./print01.sh
           Hello Linux
           [root@localhost mnt]# cd ..
           [root@localhost ~]# bash print01.sh
           -bash: print01.sh: command not found
           [root@localhost ~]# PATH=$PATH:/home/yarn/class02
           [root@localhost ~]# bash print01.sh
           Hello Linux
* 例子：重新打开远程工具，使用 hadoop01 用户登录，目的是将环境变量 PATH 还原。先切换到脚本所在的目录后，首先执行 bash 命令重启一个 shell 进程，那么 echo $SHLVL 命令执行后应该是 2，切换到其他目录，执行 bash print01.sh 脚本，提示命令找不到，然后设置环境变量 PATH，将脚本所在的目录添加到 PATH 中，切换到其他目录，执行 bassh print01.sh 脚本，可以执行。执行 bash 命令启动一个新的 shell 进程，执行 print01.sh 脚本，可以执行。然后执行两次 exit 命令，推到设置 PATH 环境变量的上一个 shell 进程，执行print01.sh 脚本，不能执行，说明变量只能在当前进程和子进程中才有效。

* 例子：当前 shell 进程中设置局部变量 name=zhangsan，执行 print01.sh 脚本，变量 name 的值没有输出到控制台，变量 name 的值不能访问到，因为执行脚本会重启一个 shell 进程，父进程中的局部变量在子进程中不能访问到。然后采用另一种方式在当前 shell 进程中执行 print01.sh 脚本，. print01.sh 或 source print01.sh，局部变量 name 的值可以访问到，因为点命令或 source 命令执行脚本时，不会重启一个新的 shell 进程，还在当前进程中执行，所以局部变量 name 可以访问到。
        
