## if…then 语句

* 在 Shell 脚本中，if…then 语句的语法格式与其他语言有相似之处，语句格式如下：

      if 命令                     if 命令 ; then
      then                           语句块
         语句块        或         fi   
      fi                         
      

    * if 后面要跟一个命令，then 和 fi 关键字中的语句块可以是多条命令，**如果 if和 then在同一行，注意要在 if 语句后面加分号**。
    * 需要注意的是其他语言 if 后面跟着的表达式的值只能是 true 或 false。
    * bash 脚本中的 if 不同其他语言，if 后面的命令执行后的退出码为 0 时，执行 then 后面的语句块，当命令执行后的退出码非 0 时，不会执行 then 后面的语句块，跳过 if 语句执行 if 语句后面的命令，而不是像其他语言当 if 后面为 true 时执行，为 false是跳过 if 语句执行后面的语句。
 
    * 例子：如果切换工作目录成功则输出一句话到控制台

          #!/bin/bash
          # if 后面的命令是切换到一个目录
          if cd /home/hadoop01/bash01
          then
           echo "切换到目录/home/hadoop01/bash01 成功！"
          fi
* 如果 if 后面的命令执行后的退出码是非 0 的其他正整数值，then 后面的语句块将不会被执行，将跳过 then…fi 语句执行后面的命令，如下所示：

    * 例子：如果目录创建成功则输出一句话到控制台，否则不会执行 then 后面的语句块
    
          #!/bin/bash
          # 判断创建目录 logs 是否成功，如果创建成功将执行 then 后面的语句块
          # 注意：本例中当前目录下 logs 文件已经存在，mkdir 命令不能正确执行
          if mkdir logs ; then
           echo "在当前目录下创建 logs 目录成功！"
          fi
          
         * 控制台显示：
         
                mkdir: 无法创建目录"logs": 文件已存在

         * logs 文件已经存在，所以 mkdir 命令没有正确执行，退出码是非 0 正整数，所以 then 后面的语句块没有执行。

* if 语句的后面可以跟任何合法的命令语句，命令执行成功退出码为 0，命令执行失败退出码非 0，if 语句就是根据命令的退出码来判断执行的分支

    * 例子：判断当前登录用户是否存在，如果存在显示工作目录下的文件。
    
            #!/bin/bash
            # 定义变量 user 并赋值用户名为 yarn
            user=yarn
            # 在文件/etc/passwd 中查找当前用户，如果找到会执行 then 后面的语句块
            if grep "$user" /etc/passwd
            then
             echo "当前登录用户：$user "
             cd /home/$user
             ls -l
            fi
      * 控制台显示：
      
            hadoop01:x:500:500:hadoop01:/home/hadoop01:/bin/bash
            当前登录用户：hadoop01
            总用量 36
            drwxrwxr-x. 2 hadoop01 hadoop01 4096 2 月 28 20:51 bash01
            drwxr-xr-x. 2 hadoop01 hadoop01 4096 2 月 24 13:14 公共的
            drwxr-xr-x. 2 hadoop01 hadoop01 4096 2 月 24 13:14 模板
            …
           * 如果变量 user 赋值一个不存在的用户名，then 后面的语句将不会被执行。
