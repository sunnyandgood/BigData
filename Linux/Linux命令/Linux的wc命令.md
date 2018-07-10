Linux wc命令 用于统计指定文件中的字节数、字数、行数，并将统计结果显示输出。wc是(Word Count)的缩写，即统计单词数。

利用wc命令 我们可以计算文件的Byte数、字数、或是列数，若不指定文件名称、或是所给予的文件名为"-"，则wc指令会从标准输入设备读取数据。
## wc命令格式
````
wc [OPTION]... [FILE]...
wc [OPTION]... --files0-from=F
````
## wc命令常用的参数
````
-c 统计字节数。
-l 统计行数。
-m 统计字符数。这个标志不能与 -c 标志一起使用。
-w 统计字数。一个字被定义为由空白、跳格或换行字符分隔的字符串。
-L 打印最长行的长度。
-help 显示帮助信息
--version 显示版本信息
````
## wc命令 应用实例
* 1、查看file的字节数、字数及行数信息
````
[root@localhost Shared]# cat 1.txt 
Hello world!

Welcome!

Yes!
[root@localhost Shared]# wc 1.txt 
 5  4 32 1.txt
 ````
说明：

5是行数

4是单词数

32是字节数

1.txt是文件名

* 2、查看当前文件夹下文件数量
````
[root@localhost Shared]#ls -l |wc -l
7
````
说明：这样的话统计数据中会包含当前文件夹，也就是说，当前文件夹下共有文件、文件夹个数为6.

* 3、统计多个文件信息
````
[root@localhost Shared]#wc file file2
 4  7 51 file
 3  6 41 file2
 7 13 92 total
 ````
可以看到，wc命令 分别统计出了两个文件的相关信息，最后还输出了总共的统计信息。
