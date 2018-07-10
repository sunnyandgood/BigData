## for 循环

* 脚本中的 for 循环语句和 Java 中的 for 循环有类似之处，在 Java 中，会根据一个集合的
长度来决定循环的次数。而脚本中的 for 循环，是遍历一系列值的循环。每次循环对一
个特定的值执行一组代码。循环次数和操作的对象是相关的。

*  for 循环的语法格式。

        for var in list
        do
           语句块
        done

* list 参数提供了循环操作的一系列值，每一次循环 var 变量包含 list 参数列表的当前值，
第一次循环 var 变量包含 list 列表的第一个值，第二次循环包含 list 列表的第二个值，直
到 list 列表中所有的值遍历一遍。执行的代码块在 do 和 done 之间，**do 代表代码块开始，
done 代表代码块结束**。变量 var 包含 list 列表中的当前值，可以通过$var 引用。下面通
过例子说明 for 循环的常用方式。
### 一、固定参数列表
* 1、循环遍历自定义的一系列值特定值。例：bash40.sh：

      #!/bin/bash
      # 循环的列表
      list="one two three four"
      # 循环遍历变量 list
      for var in $list
      do
         # 引用变量 var
         echo "Parameter is $var"
      done
      # 变量 var 引用列表的最后一个值，直到脚本结束或修改 var 的值
      echo "Final parameter var is value: $var"
      
 变量 var 第一次循环取得 list 参数列表的第一个值，直到遍历到最后一个值，并且变量
var 会一直保留 list 参数列表的最后一个值，直到脚本执行结束或变量修改或删除。
 
* 2、for 循环中使用 case 语句。例子：

      #!/bin/bash
      list="A B C D E"
      for score in $list
      do 
         case $score in
         A | B)
            echo "优"
         ;;
         C)
            echo "良"
         ;;
         D)
             echo "中"
         ;;
         *)
            echo "差"
         ;;
         esac
      done
* 3、循环遍历无规则参数列表。例子：

      #!/bin/bash
      # 无规则参数列表，带单引号
      for var in GNU isn't UNIX I don't know
      do
          echo "Word: $var"
      done
      echo "-------------"
      # 单引号转义
      for var in GNU isn\'t UNIX I don\'t know
      do
          echo "Word: $var"
      done
      echo "-------------"
      # 声明变量，主意用双引号
      list="GNU isn't UNIX I don't know"
      for var in $list
      do
          echo "Word: $var"
      done
      
  * 控制台输出：

          Word: GNU
          Word: isnt UNIX I dont
          Word: know
          -------------
          Word: GNU
          Word: isn't
          Word: UNIX
          Word: I
          Word: don't
          Word: know
          -------------
          Word: GNU
          Word: isn't
          Word: UNIX
          Word: I
          Word: don't
          Word: know

### 二、从文件读取参数
参数列表可以从文件中读取。上面的例子中，参数列表是固定的，如果想修改参数列表
需要修改脚本文件。如果将参数事先录入到文件中，从文件中读取到参数列表就比较灵
活，这种方式也是常见的方式，相当于从配置文件中读取配置信息。

* file05：

    192.168.1.2 192.168.1.3 192.168.1.4
    192.168.1.5
    192.168.1.6
    192.168.1.7
    
* 例子一：

      #!/bin/bash
      # 声明变量
      file=/home/hadoop01/bash01/file05
      # 使用反引号和 cat 命令将文件内容输出
      for var in `cat $file`
      do
         echo "IP: $var"
      done
      
  * 控制台显示：
  
        IP: 192.168.1.2
        IP: 192.168.1.3
        IP: 192.168.1.4
        IP: 192.168.1.5
        IP: 192.168.1.6
        IP: 192.168.1.7
        
  * 存入文件中的参数值要用空格分割或者每一行一个参数，每个参数占用一行是常用的方
式。如果参数在同一行，需要注意分割符。注意，需要使用 cat命令将文件的内容输出。

### 三、分隔符

在 Linux 中有一个**环境变量 IFS，称为内部字段分隔符**，IFS 定义了在 Linux 中分割字段
的一系列字符，默认情况下会将空格、制表符和换行符当做字段的分隔符。所以在上面
的例子中，参数文件的 IP 在同一行但使用空格分隔，使用 cat 命令正常输出。Shell 解释
程序发现这三种字符的任何一种，会认为是新的字段的开始。

* 可以通过在脚本中修改环境变量 IFS 来改变字段分隔符，设置的方式如下。

      IFS=$'\t'

* 通常情况下，在修改 IFS 变量前先将默认的值保存到一个变量中，当脚本执行结束后要
还原 IFS 变量的默认值而不改变默认环境，设置的方式如下。

    …
    IFS_bak=$IFS
    …
    IFS=$IFS_bak
    …
    
* 1、文件格式( ；)：
  
        192.168.1.2;192.168.1.3;192.168.1.4;192.168.1.5;192.168.1.6;192.168.1.7

    * 文件中字段的分隔符是分号，下面的例子通过修改变量 IFS 来循环读取文件的字段值。例子：

          #!/bin/bash
          # 保存默认值
          IFS_bak=$IFS
          # 可以
          #IFS=$'\;'
          # 设置分隔符为分号
          IFS=$';'
          file=/home/yarn/bash01/file06
          for var in `cat $file`
          do
             echo "IP: $var"
          done
          # 还原默认值
          IFS=$IFS_bak
      
* 2、文件格式( ：)：

      192.168.1.2:192.168.1.3:192.168.1.4:192.168.1.5:192.168.1.6:192.168.1.7

  * 文件中字段的分隔符是冒号，下面的例子通过修改变量 IFS 来循环读取文件的字段值。例子：

        #!/bin/bash
        # 保存默认值
        IFS_bak=$IFS
        # 设置分隔符为分号
        IFS=:
        file=/home/yarn/bash01/file07
        for var in `cat $file`
        do
          echo "IP: $var"
        done
        # 还原默认值
        IFS=$IFS_bak
        
* 另外 IFS 变量可以设置成：

      IFS=$'\n'
      IFS=$'\t'
      IFS=$'\n;:'

  通常情况下，IFS 不需要修改，默认的三种情况中换行使用的较多。也可以设置多个分隔符如上面的设置。

### 四、遍历目录
* 1、for 循环通过通配符星号`*`可以遍历目录下的文件，在循环体内对文件进行操作。这种方式使用较多，如下例子。

      #!/bin/bash
      # 声明变量
      dir=/home/hadoop01/bash01
      # 循环遍历目录下的所有文件
      for file in $dir/*
      do
          # 判断文件是否是目录
          if [ -d "$file" ]
          then
                echo "This is a directory: $file"
                cd $file
                ls -l
          fi
          # 判断文件是否是文件
          if [ -f "$file" ]
          then
                # 判断文件是否可执行
                if [ -x "$file" ]
                then
                     echo "This is an executable file: $file"
                fi
          fi
      done
      
 需要注意的是如果文件是个目录，目录可能存在空格，如果变量不使用双引号脚本执行过程中会报错，所以引用变量时要用双引号将变量引起来。这是一个好的习惯。
 
* 2、可以同时遍历多个目录，用空格分开。如下例子

      #!/bin/bash
      # 声明目录变量
      dir1=/home/hadoop01/bash01
      dir2=/usr/local/hadoop/etc/hadoop
      # 在一个 for 循环中遍历多个目录
      for file in $dir1/* $dir2/*
      do
               if [ -d "$file" ]
               then
                       echo "This is a directory: $file"
                       cd $file
                       ls -l
               fi
               if [ -f "$file" ]
               then
                       if [ -x "$file" ]
                       then
                                echo "This is an executable file: $file"
                       else
                                echo "This is not an executable file: $file"
                       fi
               fi
      done
* 3、使用文件扩展名遍历目录下的特定文件。例子：

      #!/bin/bash
      dir1=/usr/local/hadoop/etc/hadoop
      # 只筛选后缀名为 xml 的配置文件
      for file in $dir1/*.xml
      do
           echo "This is a configuration file: $file"
           cat $file
      done
      echo "------------"
      dir2=/home/hadoop01/bash01
      # 筛选后缀名为 sh 的脚本文件
      for file in $dir2/*.sh
      do
          echo "This is a executable file: $file"
      done
      
### 五、for 循环的另一种风格：Java 中的 for 循环风格，命令格式如下。

      for (( i = 0; i < 5; i++ ))
      do
          语句块
      done
  采用双圆括号，这种 for 循环可以指定循环次数，在需要指定循环次数的情况下较常用，使用方式简单，下面通过例子说明。

* 例子：

      #!/bin/bash
      # 外层循环
      for (( i = 1 ; i <= 9 ; i++ ))
      do
          # 内层循环
          for (( j = 1 ; j <= $i ; j++ ))
          do
                value=$[ $i * $j ]
                echo -n "$j * $i = $value "
          done
          echo
      done
      
  * 控制台显示：
  
        1 * 1 = 1
        1 * 2 = 2 2 * 2 = 4
        1 * 3 = 3 2 * 3 = 6 3 * 3 = 9
        1 * 4 = 4 2 * 4 = 8 3 * 4 = 12 4 * 4 = 16
        1 * 5 = 5 2 * 5 = 10 3 * 5 = 15 4 * 5 = 20 5 * 5 = 25
        1 * 6 = 6 2 * 6 = 12 3 * 6 = 18 4 * 6 = 24 5 * 6 = 30 6 * 6 = 36
        1 * 7 = 7 2 * 7 = 14 3 * 7 = 21 4 * 7 = 28 5 * 7 = 35 6 * 7 = 42 7 * 7 = 49
        1 * 8 = 8 2 * 8 = 16 3 * 8 = 24 4 * 8 = 32 5 * 8 = 40 6 * 8 = 48 7 * 8 = 56 8 * 8 = 64
        1 * 9 = 9 2 * 9 = 18 3 * 9 = 27 4 * 9 = 36 5 * 9 = 45 6 * 9 = 54 7 * 9 = 63 8 * 9 = 72 9 * 9 = 81
        
    这种格式的 for 循环对循环次数可以提前设置，对于固定循环次数的应用场合可以使用Java 风格的 for 循环。
