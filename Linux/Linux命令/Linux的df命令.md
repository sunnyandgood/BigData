Linux df命令 主要用来显示指定的文件系统的可用空间等信息，但是如果没有指定文件系统，则会显示所有当前被挂载的文件系统的可用空间。

Linux df命令 的默认的显示单位为1KB 。
## df命令格式
````
df [OPTION]... [FILE]...
````
## df命令常用的参数
````
-a, --all 包含所有的具有 0 Blocks 的文件系统 
--block-size={SIZE} 使用 {SIZE} 大小的 Blocks
-h, --human-readable 使用人类可读的格式(预设值是不加这个选项的...)
-H, --si 很像 -h, 但是用 1000 为单位而不是用 1024
-i, --inodes 列出 inode 资讯，不列出已使用 block
-k, --kilobytes 就像是 --block-size=1024
-l, --local 限制列出的文件结构
-m, --megabytes 就像 --block-size=1048576
--no-sync 取得资讯前不 sync (预设值)
-P, --portability 使用 POSIX 输出格式
--sync 在取得资讯前 sync
-t, --type=TYPE 限制列出文件系统的 TYPE
-T, --print-type 显示文件系统的形式
-x, --exclude-type=TYPE 限制列出文件系统不要显示 TYPE
-v (忽略)
--help 显示这个帮手并且离开
--version 输出版本信息并且离开
````
## df命令 应用实例
* 显示系统整体文件系统的磁盘使用情况

````
  $ df
  文件系统	         1K-块      已用      可用 已用% 挂载点
  /dev/sda3             30446620   3351496  25548528  12% /
  tmpfs                   969996       228    969768   1% /dev/shm
  /dev/sda1               253871     35232    205532  15% /boot
  .host:/               83899428  54715820  29183608  66% /mnt/hgfs
````

* 显示所有信息

````
  $ df -h
  文件系统	      容量  已用  可用 已用%% 挂载点
  /dev/sda3              30G  3.2G   25G  12% /
  tmpfs                 948M  228K  948M   1% /dev/shm
  /dev/sda1             248M   35M  201M  15% /boot
  .host:/                81G   53G   28G  66% /mnt/hgfs
````

* 显示可读格式的数据
````

````

* **需要注意的是df命令计算的磁盘使用情况是当前系统的使用情况，如果系统中其他的程序在增加删除文件，空间的使用情况不会计算在内**。
