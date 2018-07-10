## 循环控制(break、continue)

循环一旦开始执行会完成所有的迭代，通常情况下我们会使用**break 和 continue 命令**根
据不同的条件在循环体内部控制循环的执行流程。break 命令和 continue 命令可以用在 for
循环和 while 循环中，下面通过例子说明。

### 一、break 命令

* break 命令可以退出当前循环程序，接着执行循环语句后面的命令，可以用在 for 循环和
while 循环中。**通常情况下 break 会和 if 语句连用，当满足一定的条件时，执行 break 命
令，break 命令后面的命令不会在执行**，下面通过例子说明。

* 1、例子：

      #!/bin/bash
      # 循环从 1 到 9
      for var in 1 2 3 4 5 6 7 8 9
      do
         # 当变量的值大于 7 时执行 then 后面的代码
         if [ $var -gt 7 ]
         then
           # 换行
           echo
           echo "Execute break!"
           # 跳出循环执行 for 循环后面的命令，注意 break 命令后面的命令不会执行
           break
         fi
         if [ $[ $var % 2 ] -eq 0 ]
         then
             echo -n "* "
         else
             echo -n "$var "
         fi
      done
      # 换行
      echo
      
  * 控制台显示：
  
        1 * 3 * 5 * 7
        Execute break!

* 2、例子：遍历文件，查找目标文件进行操作

      #!/bin/bash
      # 配置文件目录
      file=/usr/local/hadoop/etc/hadoop
      # 遍历目录下后缀名为 xml 的文件
      for files in $file/*.xml
      do
          # 查找 core-site.xml 文件
          if [ "$files" = "$file/core-site.xml" ]
          then
              # 拷贝 core-site.xml 文件到/home/yarn/bash01 目录下
              cp $file/core-site.xml $HOME/bash01
              # 在控制台显示 core-site.xml 文件的内容
              cat $HOME/bash01/core-site.xml
              # 完成对文件 core-site.xml 的操作，跳出循环
              break
         fi
      done
      
  * 在目录下找到 core-site.xml 文件后，拷贝到当前目录并显示到控制台，然后终止循环的执行。

* 3、在嵌套循环中使用 break 命令时，如果 break 命令在内层循环只会终止内层循环，不会终止外层循环，下面通过例子说明。

      #!/bin/bash
      # 外层循环
      for outer in A B C D
      do
         # 内层循环
         for inner in 1 2 3 4 5 6 7
         do
            # 判断变量是否等于 4
            if [ $inner -eq 4 ]
            then
                echo "*"
                # 当变量的值等于 4 时终止内层循环
                break
            else
                echo -n "$inner "
            fi
         done
         echo "$outer "
      done
      
  * 控制台显示：
  
         1 2 3 *
         A
         1 2 3 *
         B
         1 2 3 *
         C
         1 2 3 *
         D
         
  * 内层循环是外层循环的一部分，所以内层循环输出多次。
  
* 4、break 命令可以指定终止循环的层数，默认情况下是 break 1，如果是两层循环嵌套，使用 break 2 可以跳出外层循环，修改上面的代码。

      #!/bin/bash
      # 外层循环
      for outer in A B C D
      do
          # 内层循环
          for inner in 1 2 3 4 5 6 7
          do
              # 判断变量是否等于 4
              if [ $inner -eq 4 ]
              then
                  echo "*"
                  # 当变量的值等于 4 时终止从里到外第二层循环
                  break 2
              else
                  echo -n "$inner "
              fi
          done
          echo "$outer "
      done
      
  * 控制台显示：
  
          1 2 3 *
          
### 二、continue 命令

* continue 命令终止本次循环，continue 命令后面的命令不在执行并开始下一次循环。通常
和 if 语句连用，当满足特定条件时执行 continue 命令，**continue 命令不会终止整个循环，
只是结束本次循环继续执行下一次循环**。通过例子说明。

* 1、例子：

      #!/bin/bash
      for var in A B C D E F G
      do
          # 当变量等于 D 时，执行 continue 命令
          if [ $var = "D" ]
          then
             echo -n "* "
             # 执行 continue 命令终止本次循环，开始下一次循环
             # 不会执行 continue 命令后面的的命令
             continue
          fi
          echo -n "$var "
      done
      
      echo
      
   * 控制台显示：
   
          A B C * E F G
           
* 2、continue 命令可以根据条件跳过命令集，在某种条件下不执行特定的代码。

      #!/bin/bash
      # 外层循环
      for outer in A B C D
      do
       # 内层循环
       for inner in 1 2 3 4 5 6 7
       do
       # 当变量的值等于 4 时执行 continue 命令
       if [ $inner -eq 4 ]
      then
       # 终止当次循环，开始执行下一次循环
       continue
       fi
       echo -n "$inner "
       done
       echo
       echo "$outer "
      done
      
     * 控制台显示：
     
           1 2 3 5 6 7
           A
           1 2 3 5 6 7
           B
           1 2 3 5 6 7
           C
           1 2 3 5 6 7
           D
           
* 3、如果 continue 命令用在 while 循环中，需要注意的是 continue 命令可能会跳过修改 test命令退出码状态的命令，程序可能死循环。如下代码。

      #!/bin/bash
      # 变量
      var=10
      
      while [ $var -gt 0 ]
      do
         # 输出到控制台
         echo "var: $var"
         # 变量是否等于 5
         if [ $var -eq 5 ]
         then
            # 如果变量等于 5，终止本次循环，并开始下一次循环
            # 注意：continue 命令后面的命令不会执行
            continue
         fi
         # 改变循环 test 命令的退出状态码
         var=$[ $var - 1 ]
      done | more
      
    * 控制台显示：
    
           var: 10
           var: 9
           var: 8
           var: 7
           var: 6
           var: 5
           var: 5
           var: 5
           …
           
    * 当变量的值等于 5 时，continue 命令后面的命令不会执行，test 命令的退出状态码不会改
变，将变成死循环。将 while 循环的输出通过管道输出到 more 命令，可以控制死循环。
也可以在执行命令时将输出定向到 more 命令，如下。

          bash62.sh | more

* 4、continue 命令和 break 命令相同，可以在命令行指定循环的层级，默认情况下 continue 1，
在嵌套循环中，continue 命令可以指定开始哪个层级的循环，下面通过例子说明。

          #!/bin/bash
          # 外层循环
          for outer in A B C D
          do
               echo -n "$outer "
               # 内存循环
               for inner in 1 2 3 4
               do
                   if [ $inner = "3" ]
                   then
                       # 当变量等于 3 时，开始执行第二层级的循环
                       continue 2
                   fi
                   echo -n "$inner "
               done
               # 不会执行到这里
               echo "Not Executed!"
          done
          # 换行
          echo
    
   * 控制台显示：

          A 1 2 B 1 2 C 1 2 D 1 2
