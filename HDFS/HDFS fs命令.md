## HDFS fs命令  (DFS-->Distributed File System)

### 一、命令

      -help [cmd]	//显示命令的帮助信息
      -ls(r) <path>	//显示当前目录下所有文件
      -du(s) <path>	//显示目录中所有文件大小
      -count[-q] <path>	//显示目录中文件数量
      -mv <src> <dst>	//移动多个文件到目标目录
      -cp <src> <dst>	//复制多个文件到目标目录
      -rm(r)		//删除文件(夹)
      -put <localsrc> <dst>	//本地文件复制到hdfs
      -copyFromLocal	//同put
      -moveFromLocal	//从本地文件移动到hdfs
      -get [-ignoreCrc] <src> <localdst>	//复制文件到本地，可以忽略crc校验
      -getmerge <src> <localdst>		//将源目录中的所有文件排序合并到一个文件中
      -cat <src>	//在终端显示文件内容
      -text <src>	//在终端显示文件内容
      -copyToLocal [-ignoreCrc] <src> <localdst>	//复制到本地
      -moveToLocal <src> <localdst>
      -mkdir <path>	//创建文件夹
      -touchz <path>	//创建一个空文件
      
### 二、HDFS的Shell命令练习
      
      # cd /mnt/softWare/hadoop-2.2.0/sbin/
      # ./start-dfs.sh
      
      # hdfs dfs -ls / （或hadoop fs -ls /）               查看HDFS根目录
      # hdfs dfs -mkdir /test                             在根目录创建一个目录test
      # hdfs dfs -mkdir /test1                            在根目录创建一个目录test1
      # hadoop fs -put ./test.txt /test　                  将根目录下test.txt文件上传到/test下
           或#hadoop fs -copyFromLocal ./test.txt /test   
      # hadoop fs -get /test/test.txt .                    将/test目录下的test.txt文件下载到当前目录
           或#hadoop fs -getToLocal /test/test.txt .
      # hadoop fs -cp /test/test.txt /test1                将/test目录下的test.txt复制到/test1目录下
      # hadoop fs -rm /test1/test.txt                      将/test1目录下的test.txt文件删除
      # hadoop fs -mv /test/test.txt /test1                将/test目录下的test.txt移动到/test1目录下
      # hadoop fs -rmr /test1                              将/test1文件夹（空的）删除------（已弃用）
            （换用）# hadoop fs rm -r /test1
