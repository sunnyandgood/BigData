## 用户组管理
   用户账户对单个用户管理相对较方便，但是对于多个相同类型的账户同时进行管理就不太方便了，所以有了组的概念。组允许多个用户共享一组共享的权限，用相同的权限访问系统上的文件或目录。

  不同的 Linux 版本略有不同，有的系统会将所有的用户同属于一个组，这样出现的问题是一个用户的文件同组的其他用户都可以访问。有的系统为每个用户创建一个组，这样更安全些。在系统中每个组有唯一的 GID 和组名。类似于/etc/passwd 文件，系统上所有的组信息都保存到/etc/group 文件中，如下所示：
* /etc/group 文件也有四个字段组成：
   * 1、组名称
   * 2、组密码
   * 3、GID 
   * 4、属于本组的用户。

   * /etc/group中的内容：
````
    root:x:0:
    bin:x:1:bin,daemon
    daemon:x:2:bin,daemon
    sys:x:3:bin,adm
    adm:x:4:adm,daemon
    …
    tcpdump:x:72:
    slocate:x:21:
    hadoop01:x:500:
````

### 一、创建组
* 创建一个新组时，默认情况下没有用户属于这个组，如果需要将用户添加到新组中，需要使用 usermod 命令。创建新组的命令 groupadd，命令格式如下。
    * 创建一个新组 group01：
 
          groupadd group01
          groupadd group02 -G 800
    
    * 创建新用户并指定组：
    
           useradd user02 -G group01
      
    * 为 hadoop01 用户添加附加组：
    
          usermod -G group01 hadoop01

      **注意：需要使用 root 权限。**
      
### 二、修改组
* 因为组信息相对较少，所以通过 groupmod 命令可以修改组名称和 GID，命令如下：
    * 修改组名称：
    
          groupmod -n newName oldName

    * 修改 GID：
    
          groupmod -g 550 group01

### 三、删除组
* 删除组的命令是 **groupdel**，使用较简单，但是删除组时要谨慎，如果组中已经存在用户，要先删除组中的所有用户后才能删除组。
    * 删除组中的一个用户：
    
          gpasswd 组名称 -d 用户名称
        
    * 删除一个组：
    
          groupdel group01
