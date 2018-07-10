## 使用 test 命令(数值比较、字符串比较、文件比较)

   在前边的 if 语句中，if 根据后面命令的退出码是否为 0 来决定是否执行 then 后面的语句
块，如果存在 else 语句，在命令退出码非 0 的情况下才会执行 else 后面的语句块。

   并且if 后面跟着的都是命令。那么脚本中的 if 语句能不能像其他语言，如 Java 中的 if 语句根
据后面的表达式返回的 true 或 false 来决定是否执行 then 或 else 后面的语句块呢！

   但是要注意，在脚本中 if 判断的一定是后面命令的退出码是 0 或非 0，那么解决的方法是什
么呢！就是 `test 命令`，**test 命令提供了在 if…then 语句中测试不同条件的途径，如果 test
后面的条件成立，那么 test 命令返回的退出码为 0，如果条件不成立，test 命令返回的退
出码为 1**，这样既满足了 if 语句根据命令退出码来决定执行方向，同时又拓宽了判断条
件。

* test 命令一般有两种格式：
    * `test 表达式`
    * `[ 表达式 ]`
    
    * 但是需要注意的是如果使用方括号方式时，要注意在表达式** 两边加上空格 **，否则执行会提示错误信息。
* 使用第一种方式的语句格式如下。

      if test 表达式
      then
       语句块
      fi
          
    * 当 test 命令后面的的表达式成立，test 命令的退出码为 0，如果 test 命令后面的表达式不成立，test 命令的退出码为 1。如下面的例子。
    * 例子：判断两个数值类的大小并输出信息
    
          #!/bin/bash
          # 声明变量并赋值
          var1=100
          var2=200
          # 判断变量 var1 是否大于变量 var2
          if test $var1 -gt $var2
          then
           echo "$var1 > $var2"
          else
           echo "$var1 < $var2"
          fi
      * 控制台显示：
      
            100 < 200
            
* 上面的脚本中使用了 test 命令，后面跟着的是逻辑表达式。**还有一种方式可以替代上面的方式，就是使用方括号来替代 test 命令**，修改上面的代码如下。
  * 例子：使用方括号的方式实现上面的例子，通常这种方式使用较为普遍。
  
        #!/bin/bash
        var1=100
        var2=200
        if [ $var1 -gt $var2 ]
        then
         echo "$var1 > $var2"
        else
         echo "$var1 < $var2"
        fi
   * 注意方括号是 test 命令的简写方式，方括号中是 test 命令的条件，另外需要注意的是方括号的两边要有空格，否则会报错。通常使用的就是这种方式。

* test 命令可以判断三类条件：

    * 1、数值比较
    * 2、字符串比较
    * 3、文件比较

### 一、数值比较

 数值比较比较常见，下面的表中列出了数值比较符，这和 Java 中是有区别的。
* 数值比较：
               <table>
                  <tr>
                     <td>比较符</td>
                     <td>说明</td>
                  </tr>
                  <tr>
                     <td>v1 -eq v2</td>
                     <td>v1 是否等于 v2</td>
                  </tr>
                  <tr>
                     <td>v1 -gt v2</td>
                     <td>v1 是否大于 v2</td>
                  </tr>
                  <tr>
                     <td>v1 -ge v2</td>
                     <td>v1 是否大于或等于 v2</td>
                  </tr>
                  <tr>
                     <td>v1 -lt v2</td>
                     <td>v1 是否小于 v2</td>
                  </tr>
                  <tr>
                     <td>v1 -le v2</td>
                     <td>v1 是否小于或等于 v2</td>
                  </tr>
                  <tr>
                     <td>v1 -ne v2</td>
                     <td>v1 是否不等于 v2</td>
                  </tr>
               </table>

 
* 例子：比较大小。通过一个例子说明数值比较。

      #!/bin/bash
      var1=200
      var2=300
      # 判断 var1 是否等于 var2
      if [ $var1 -eq $var2 ]
      then
          echo "$var1 等于$var2"
      # 判断 var1 是否大于 var2
      elif [ $var1 -gt $var2 ]
      then
          echo "$var1 大于$var2"
      # 判断 var1 是否小于 var2
      elif [ $var1 -lt $var2 ]
      then
         echo "$var1 小于$var2"
      fi
   * 控制台输出：
   
         200 小于 300
         
* 例子：比较小数是否可以？？浮点数是否可以比较！通过例子说明：

      #!/bin/bash
      var1=3.33
      var2=3
      if [ $var1 -gt $var2 ]
      then
         echo "$var1 大于$var2"
      else
         echo "$var1 小于等于$var2"
      fi
   * 控制台输出：
   
         3.33 小于等于 3
   比较结果是错误的，**注意：脚本中只能处理整数，不支持浮点数作为数值比较**。
   
### 二、字符串比较
test 命令还支持比较字符串，**比较字符串和比较数值使用的比较符是不同的**，**使用标准的数学比较符进行字符串的比较，
而用文本代码来比较数值**，这是要注意的。

* 字符串比较符列表说明:

    <table>
         <tr>
            <td>比较符</td>
            <td>说明</td>
         </tr>
         <tr>
            <td>s1 = s2</td>
            <td>s1 是否于 s2 相同</td>
         </tr>
         <tr>
            <td>s1 != s2</td>
            <td>s1 是否于 s2 不同</td>
         </tr>
         <tr>
            <td>s1 > s2</td>
            <td>s1 是否比 s2 大</td>
         </tr>
         <tr>
            <td>s1 < s2</td>
            <td>s1 是否比 s2 小</td>
         </tr>
         <tr>
            <td>-n s1</td>
            <td>s1 的长度是否不等于 0，是否非空</td>
         </tr>
         <tr>
            <td>-z s1</td>
            <td>s1 的长度是否为 0，是否为空</td>
         </tr>
      </table>

* 例子：当前用户是否是 root（**比较字符串是否相同**。）

      #!/bin/bash
      # 判断当前登录用户是否是 root
      if [ $USER = "root" ]
      then
         echo "当前用户是超级管理员：$USER"
      # 判断当前登录用户是否是 yarn
      elif [ $USER = "hadoop01" ]
      then
         echo "当前用户是：$USER"
      fi
      
   * 控制台显示：
   
         当前用户是：hadoop01

   * 判断字符串不相同，使用方式和判断字符串相同相似，可以使用在相同的场合。
* 例子：判断用户

      #!/bin/bash
      user=yarn
      if [ $user != "root" ]
      then
         echo "你不是管理员 root"
      elif [ $user = "root" ]
      then
         echo "欢迎管理员$user 登录！"
      fi
      
   * 控制台显示：
   
         你不是管理员 root

* 例子：比较字符串是否相等(**字符串比较考虑字符的大小写**)

      #!/bin/bash
      if [ "Hello" = "hello" ]
      then
       echo "Hello = hello"
      else
       echo "Hello != hello"
      fi
      
   * 控制台显示：
   
         Hello != hello
         
#### 比较字符串大小时要注意，大于号和小于号要转义，否则系统会将大于号和小于号认为是重定向符，字符串当成文件名。如下例子。
* 例子：字符串大于小于比较时，大于号和小于号要转义，否则结果出错

      #!/bin/bash
      # 注意：系统会将大于号>认为是重定向符，认为 bcd 是文件名
      # 会在当前目录创建一个 bcd 文件
      if [ "abc" > "bcd" ]
      then
       echo "abc > bcd"
      else
       echo "abc <= bcd"
      fi
      
   * 控制台输出：
   
         abc > bcd
         
* 脚本似乎正确执行，事实上系统将大于号视同重定向符，将 bcd 认为是文件名，会在当
前目录创建一个 bcd 文件，并且执行结果也是错的。要注意，字符串比较时的大于号和
小于号要转义，修改上面脚本如下。

   * 例子：修改上面的例子
   
            #!/bin/bash
            # 大于号要转义
            if [ "abc" \> "bcd" ]
            then
             echo "abc > bcd"
            else
             echo "abc <= bcd"
            fi
        * 控制台显示：
       
               abc <= bcd
               
* **判断字符串变量是否 非空使用比较符-n，判断字符串是否为空使用比较符-z**。在字符串操作中使用较多，下面通过例子说明。

     * 例子：字符串非空判断
     
            #!/bin/bash
            # 声明变量
            var1="abcdefg"
            # 声明一个空串
            var2=""
            var3=
            # 判断变量非空
            if [ -n "$var1" ]
            then
             echo "变量 var1 非空：$var1"
            fi
            # 判断变量是否为空取反，判断变量是否不为空
            if [ ! -z "$var1" ]
            then
             echo "变量 var1 非空：$var1"
            fi
            # 判断变量是否为空
            if [ -z "$var2" ]
            then
             echo "变量 var2 为空"
            fi
            # 判断变量是否不为空取反，判断变量是否为空
            if [ ! -n "$var2" ]
            then
             echo "变量 var2 为空"
            fi
            # 判断变量是否为空
            if [ -z "$var3" ]
            then
             echo "变量 var3 为空"
            fi
            # 变量 var4 未定义，变量任然为空
            if [ -z "$var4" ]
            then
             echo "变量 var4 为空"
            fi
         * 控制台输出：
         
               变量 var1 非空：abcdefg
               变量 var1 非空：abcdefg
               变量 var2 为空
               变量 var2 为空
               变量 var3 为空
               变量 var4 为空
### 三、文件比较
* 脚本中常用到的是文件比较，test 命令允许测试 Linux 系统上的文件和目录的状态。下面表中是文件比较常用到的测试函数。
            <table>
               <tr>
                  <td>测试符</td>
                  <td>说明</td>
               </tr>
               <tr>
                  <td>-d filename</td>
                  <td>filename 是否存在并是一个目录</td>
               </tr>
               <tr>
                  <td>-e filename </td>
                  <td>filename 是否存在</td>
               </tr>
               <tr>
                  <td>-f filename</td>
                  <td>filename 是否存在并是一个文件</td>
               </tr>
               <tr>
                  <td>-r filename </td>
                  <td>filename 是否存在并可读</td>
               </tr>
               <tr>
                  <td>-s filename</td>
                  <td>filename 是否存在并非空</td>
               </tr>
               <tr>
                  <td>-w filename</td>
                  <td>filename 是否存在并可写</td>
               </tr>
               <tr>
                  <td>-x filename</td>
                  <td>filename 是否存在并可执行</td>
               </tr>
               <tr>
                  <td>-O filename</td>
                  <td>filename 是否存在并属于当前用户</td>
               </tr>
               <tr>
                  <td>-G filename</td>
                  <td>filename 是否存在并且默认组与当前用户相同</td>
               </tr>
            </table>

**这些测试函数可以让你检查文件系统中的文件。在脚本中对要访问的文件进行操作前要做的检查工作，脚本中经常要用到**。

* 例子：判断文件是否为目录：-d 检查文件是否在文件系统中以目录的方式存在，当要在此目录下读写文件时经常用到，如果目录本身不存在，要给出提示信息或首先创建目录，下面通过例子说明。
 
      #!/bin/bash
      # 声明一个目录变量
      dir1=/home/hadoop01/bash01
      # 判断/home/hadoop01/bash01 是否存在并且是个目录
      if [ -d "$dir1" ]
      then
       # 切换到/home/hadoop01/bash01 目录下
       cd ${dir1}
       # 创建一个临时目录 tmp
       mkdir tmp
       # 切换到 tmp 目录下
       cd tmp
       # 创建一个空日志文件
       touch logs.log
       # 将当天的日期输入到文件中
       date > logs.log
       # 在控制台显示日志文件内容
       cat logs.log
      else
       # 如果目录不存在，在控制台显示提示信息
       echo "$dir1 is not exist!"
      fi
   脚本执行后，如果/home/hadoop01/bash01 目录存在，会在目录下创建一个 tmp 目录，在 tmp目录中创建一个 logs.log 日志文件，如果目录不存在，会在控制台显示提示信息：目录不存在。


* 下面的例子首先判断当前用户是否是 hadoop01，如果不是 hadoop01 用户不允许操作 hadoop01 用户的
工作目录。如果当前用户是 hadoop01，判断/home/hadoop01/bash01/tmp 目录是否存在，如果存在
追加 hadoop01 用户登录信息到日志文件中。如果 tmp 目录不存在，首先创建 tmp 目录并且在
目录中创建日志文件并输入用户登录信息。

      #!/bin/bash
      if [ $USER = "hadoop01" ]
      then
          if [ -d "/home/hadoop01/bash01/tmp" ]
          then
             cd /home/hadoop01/bash01/tmp
             dateStr=`date +%D" "%T`
             echo "$dateStr: User $USER login system" >> logs.log
          else
             cd /home/hadoop01/bash01
             mkdir tmp
             cd tmp
             dateStr=`date +%D" "%T`
             echo "$dateStr: User $USER login system" > logs.log
          fi
      else
         echo "You is not User hadoop01!"
      fi
      
   * 日志文件信息格式：
   
         02/29/16 17:40:49: User hadoop01 login system
         
* 例子：使用-e 判断文件是否存在，文件可以是目录和普通文件： -e 判断文件或目录是否存在，判断更宽泛，包括普通文件和目录文件。下面通过例子说明。
 
      #!/bin/bash
      # 判断/home/hadoop01/bash01 目录是否存在
      if [ -e "/home/${USER}/bash01" ]
      then
          # 判断 tmp 目录是否存在
          if [ -e "/home/${USER}/bash01/tmp" ]
          then
             # 判断 tmp 目录下的 logs.log 日志文件是否存在
             if [ -e "/home/${USER}/bash01/tmp/logs.log" ]
             then
                cd /home/${USER}/bash01/tmp
                dateStr=`date +%D" "%T`
                echo "$dateStr: User $USER login system" >> logs.log
             else
                cd /home/${USER}/bash01/tmp
                dateStr=`date +%D" "%T`
                echo "$dateStr: User $USER login system" > logs.log
             fi
          else
             mkdir /home/${USER}/bash01/tmp
             dateStr=`date +%D" "%T`
             echo "$dateStr: User $USER login system" > /home/${USER}/bash01/tmp/logs.log
          fi
      else
         echo "/home/${USER}/bash01 is not exist!"
      fi
      
   在脚本中首先判断目录是否存在，然后判断目录下的日志文件是否存在，如果文件存在
追加日志信息到日志文件，否则创建一个日志文件。脚本中对 tmp 目录也做了判断，如
果不存在，创建 tmp 目录和 logs.log 文件。

* -e 判断文件或者目录是否存在，如果具体到目录，可以使用-d。如果具体到文件，可以
使用-f。下面通过例子说明。

      #!/bin/bash
      # 判断目录是否存在，包括 bash01/tmp
      if [ -d "/home/hadoop01/bash01/tmp" ]
      then
          # 判断 logs.log 日志文件是否存在
          if [ -f "/home/hadoop01/bash01/tmp/logs.log" ]
          then
             cd /home/hadoop01/bash01/tmp
             dateStr=`date +%D" "%T`
             # 追加日志信息
             echo "$dateStr: User $USER login system" >> logs.log
          else
             cd /home/hadoop01/bash01/tmp
             dateStr=`date +%D" "%T`
             # 创建日志文件并输入日志信息
             echo "$dateStr: User $USER login system" > logs.log
          fi
      else
          # 递归创建目录
          mkdir -p /home/hadoop01/bash01/tmp
          cd /home/hadoop01/bash01/tmp
          dateStr=`date +%D" "%T`
          echo "$dateStr: User $USER login system" > logs.log
      fi
      
* -r 用来判断对文件是否有可读权限，如下说明：

      --w-rw-r--. 1 hadoop01 hadoop01 35 3 月 1 10:49 file04
      
   * 文件 file04 的属主是 hadoop01，但是 hadoop01 没有读权限只有写权限，如果要查看文件内容会提示警告信息，如下：
 
            $ cat file04
            cat: file04: 权限不够

* 下面的例子是判断文件是否有读写权限如果有显示文件内容，如果没有读权限，先修改用户权限，然后显示文件内容。

         #!/bin/bash
         # 声明变量
         file=/home/hadoop01/bash01/file04
         # 判断文件是否可以读
         if [ -r "$file" ]
         then
             # 显示文件内容
             cat $file
         else
             # 判断当前用户是否是 hadoop01
             if [ "$USER" = "hadoop01" ]
             then
                # 给 hadoop01 用户增加读权限
                chmod u+r $file
                cat $file
             else
                # 如果不是 hadoop01 用户，提示信息
                echo "You are not a hadoop01 user!"
             fi
         fi
         
* -s 用来判断文件是否不为空。如果要将空文件删除，首先要判断文件是否为空，对于非空的文件删除要谨慎。下面通过一个例子说明。
   * 想创建一个空文件：
   
         touch newfile
         ls -l
         -rw-rw-r--. 1 hadoop01  hadoop01 0 3 月 1 11:11 newfile
   * 判断 newfile 文件是否为空。

         #!/bin/bash
         file=/home/hadoop01/bash01/newfile
         # 判断文件是否不为空
         if [ -s "$file" ]
         then
             echo "$file is a non empty file"
          else
             echo "$file is an empty file"
          fi
            
          # 向文件中追加内容
          echo "We are learning Bash Shell!" >> $file
          
          # 判断文件是否不为空
          if [ -s "$file" ]
          then
             echo "$file is a non empty file"
             echo "The contents fo the file:"
             # 显示文件尾部的内容
             tail $file
         else
            echo "$file is an empty file"
         fi
* -w 用来判断当前用户对文件是否有写权限，下面通过例子说明。

         #!/bin/bash
         # 判断当前用户是否是 hadoop01
         if [ "$USER" = "hadoop01" ]
         then
            cd $HOME/bash01
             # 创建一个新的空文件
             touch logfile
             # 去掉 yarn 用户的文件写权限
             chmod u-w logfile
             # 判断当前用户 yarn 对 logfile 文件是否有写权限
             if [ -w "logfile" ]
             then
                # 这段代码不会执行，因为用户没有写权限
                echo "You have write permission!"
                echo "Not executed!"
             else
                echo "You do not have permission to write!"
             fi
                # 为 yarn 用户增加对 logfile 文件的写权限
                chmod u+w logfile
             if [ -w "logfile" ]
             then
                # 这段代码会执行
                echo "You already have permission to write!"
                echo "You have permission to write!" >> logfile
             else
                echo "You do not have permission to write!"
                echo "Not executed!"
             fi
         else
             # 如果不是 hadoop01 用户没有操作权限
             echo "You do not have permission!"
         fi
* -x 用来判断用户对文件是否有执行权限，通常的使用场景是在脚本中需要执行其他的脚本，要判断用户对脚本是否有执行权限，如果用户拥有脚本的执行权限则执行脚本，否则提示信息或修改脚本的执行权限。下面通过例子说明。
  * 首先创建一个测试执行脚本 bash31_1.sh 并去掉 hadoop01 用户的执行权限：
  
         #!/bin/bash
         echo "Bash Shell script action!"

    * bash31.sh：
    
            #!/bin/bash
            file=/home/yarn/bash01/bash31_1.sh
            # 取消文件属主的执行权限
            chmod u-x $file
            if [ "$USER" = "yarn" ]
            then
                # 判断文件是否有执行权限
                if [ -x "$file" ]
                then
                  echo "Not executed!"
                else
                   # 增加用户的执行权限
                   chmod u+x $file
                   # 执行脚本
                   $file
                fi
            else
                echo "You do not have premission!"
            fi
* -O 用来判断当前用户是否是文件的属主。
   * 首先使用 root 用户的权限创建一个文件 rootfile，然后使用 hadoop01 用户创建一个 yarnfile 文件：
   
         -rw-r--r--. 1 root root 15 3 月 1 12:50 rootfile
         -rw-rw-r--. 1 yarn hadoop01 15 3 月 1 12:55 yarnfile
   * 下面通过脚本说明-O 的使用方法。

         #!/bin/bash
         yarnfile=/home/hadoop01/bash01/hadoop01file
         rootfile=/home/hadoop01/bash01/rootfile
         if [ "$USER" = "hadoop01" ]
         then
             # 判断文件 hadoop01file 的属主是否是当前用户
             if [ -O "$hadoop01file" ]
             then
               # 会执行
               echo "$hadoop01file is owner user hadoop01!"
             else
               echo "Not executed!"
             fi
             # 判断 rootfile 文件的属主是否是当前用户
             if [ -O "$rootfile" ]
             then
                echo "Not executed!"
             else
                # 会执行
                echo "$rootfile is not owner user hadoop01!"
             fi
         else
            echo "You do not have permission!"
         fi
* -G 用来判断文件的默认组是否与当前用户的组相同，使用较少，下面通过例子说明。

      #!/bin/bash
      yarnfile=/home/hadoop01/bash01/hadoop01file
      rootfile=/home/hadoop01/bash01/rootfile
      if [ "$USER" = "hadoop01" ]
      then
          # 判断文件的默认组是否和当前用户是同一个组
          if [ -G "$hadoop01file" ]
          then
             # 会执行
             echo "With the user hadoop01 with a group! "
          else
              echo "Not executed!"
          fi
          # 判断文件的默认组是否和当前用户是同一个组
          if [ -G "$rootfile" ]
          then
             echo "Not executed!"
          else
             # 会执行
             echo "With is not the user hadoop01 with a group!"
          fi
      else
         echo "You do not have permission!"
      fi
