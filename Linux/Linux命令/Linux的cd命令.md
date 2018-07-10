### cd 命令格式：
````
cd /mnt/hgfs                # 切换到/mnt/hgfs 目录下
cd 2                        # 使用相对路径
cd ..                       # 回到上一级目录
cd ../..                    # 回到上两级目录
cd                          # 切换到当前用户的工作目录(用户主目录)
cd ~                        # 同上
cd -                        # 回到最近一次所在的目录
````
#### 与cd命令一起使用时的符号含义
* 单点（.）：当前目录（目前的目录）。
* 双点（..）：父目录。
* 波浪线符号（〜）：当前用户的主目录。
* $HOME变量：当前用户的主目录。
## cd 命令格式详解  
* 1、使用绝对路径：

  绝对路径定义了目录或文件在整个文件系统中的确切位置，如：cd /etc/profile.d，系统会从根上开始找，在/下找到 etc 目录，然后在/etc 目录下找 profile.d 目录。
  >For example
 ````
  [root@localhost ~]# cd /mnt/hgfs/Shared/
  [root@localhost Shared]# ls
  1.txt
````
* 2、使用相对路径：

  相对路径是基于当前位置的定位方式，也就是系统会从当前位置为基点开始找需要定位的目录。如：cd bash01，系统会在当前目录下找 bash01 目录，如果找不到会提示警告信息到控制台。
  >For example 
````
  [root@localhost Shared]# ls
  1.txt  2

  [root@localhost Shared]# cd 2
  [root@localhost 2]#

  [root@localhost Shared]# cd 3
  bash: cd: 3: No such file or directory
````
* 3、跳入/mnt/hgfs目录：
  >For example
````
  [root@localhost Shared]# cd /mnt/hgfs
  [root@localhost hgfs]# 
````
* 4、跳至上层目录
  >For example
````
  [root@localhost hgfs]# cd ..
  [root@localhost mnt]# 
````  
* 5、跳入上次使用目录
  >For example
````
  [root@localhost mnt]# cd -
  /mnt/hgfs
  [root@localhost hgfs]# 
````  
* 6、跳至上上层目录
  >For example
````
  [root@localhost hgfs]# cd ../..
  [root@localhost /]#   
````  
* 7、跳入用户主目录
  >For example
````
  [root@localhost /]# cd 
  [root@localhost ~]

  [root@localhost /]# cd ~
  [root@localhost ~]#
````  
* 8、使用环境变量
  >For example
````
  [root@localhost mnt]# cd $TEST_PATH
  [root@localhost ~]# 
````  
