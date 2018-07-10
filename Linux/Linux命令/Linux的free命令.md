Linux free命令 是Linux系统中另外一个常用的系统管理命令，用于显示内存状态。

free命令 会显示内存的使用情况，包括实体内存，虚拟的交换文件内存，共享内存区段，以及系统核心使用的缓冲区等。
## free命令格式
````
free [-b | -k | -m] [-o] [-s delay ] [-t] [-l] [-V]
````
## free命令常用的参数
````
-b 　以Byte为单位显示内存使用情况。 

-k 　以KB为单位显示内存使用情况。 

-m 　以MB为单位显示内存使用情况。 

-o 　不显示缓冲区调节列。 

-s<间隔秒数> 　持续观察内存使用状况。 

-t 　显示内存总和列。 

-V 　显示版本信息。
````
## free命令 应用实例
* 1、显示内存使用情况
````
[root@localhost ~]# free
             total       used       free     shared    buffers     cached
Mem:       1018600     309992     708608          0      14192      67412
-/+ buffers/cache:     228388     790212
Swap:            0          0          0
[root@localhost ~]# free -m
             total       used       free     shared    buffers     cached
Mem:           994        302        692          0         13         65
-/+ buffers/cache:        223        771
Swap:            0          0          0
````
说明：

total:总计物理内存的大小。

used:已使用多大。

free:可用有多少。

Shared:多个进程共享的内存总额。

Buffers/cached:磁盘缓存的大小。

第三行(-/+ buffers/cached):

used:已使用多大。

free:可用有多少。

第四行是交换分区SWAP的，也就是我们通常所说的虚拟内存。

区别：第二行(mem)的used/free与第三行(-/+ buffers/cache) used/free的区别。 这两个的区别在于使用的角度来看，第一行是从OS的角度来看，因为对于OS，buffers/cached 都是属于被使用。

第三行所指的是从应用程序角度来看，对于应用程序来说，buffers/cached 是等于可用的，因为buffer/cached是为了提高文件读取的性能，当应用程序需在用到内存的时候，buffer/cached会很快地被回收。

所以从应用程序的角度来说，可用内存=系统free memory+buffers+cached。

* 2、以总和的形式显示内存的使用信息
````
[root@localhost ~]# free -t
             total       used       free     shared    buffers     cached
Mem:       1018600     409764     608836          0      14652      67660
-/+ buffers/cache:     327452     691148
Swap:            0          0          0
Total:     1018600     409764     608836
````
* 3、周期性查询内存使用信息
````
[root@localhost ~]# free -s 10
             total       used       free     shared    buffers     cached
Mem:       1018600     495000     523600          0      15020      68896
-/+ buffers/cache:     411084     607516
Swap:            0          0          0

             total       used       free     shared    buffers     cached
Mem:       1018600     496296     522304          0      15036      68904
-/+ buffers/cache:     412356     606244
Swap:            0          0          0
````
