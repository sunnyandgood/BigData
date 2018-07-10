## case 分支语句

case 同 Java 中的 switch 类似。它允许通过判断来选择执行代码块中多条路径中的一条。
case 命令会将制定的变量与不同的模式进行比较，如果变量和模式匹配则执行该模式指
定的语句块。可以通过竖线增加不同的匹配模式。星号可以捕捉与所有模式不匹配的值。

* 语句格式如下。

          case "$variable" in
          parameter1 | parameter2 | parameter3)
          bash 语句块
          ;;
          parameter4)
          bash 语句块
          ;;
          *)
           default 语句块
          ;;
          esac

* 通过例子说明 case 语句的使用方式。例：bash01.sh

          #!/bin/bash
          # 成绩
          scort=90
          # case 语句
          case $scort in
           6* | 7*)
              echo "中"
           ;;
           8*)
              echo "良"
           ;;
           9*)
              echo "优"
           ;;
           *)
              echo "差"
          esac
          
    * 控制台输出：
      
              [root@localhost pretice]# ./bash01.sh 98
              优
