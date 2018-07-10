## Linux用户管理

### 一、添加新用户
  在 Linux 中添加新用户的命令是 **useradd**，这个命令可以创建一个新用户账户并且创建用户的工作目录。
用 useradd 命令创建新用户使用的是系统默认的系统配置，通过下面的命令可以看到默认情况下创建用户账户时的默认配置信息。

* 1、命令：

      useradd -D
      
* 2、控制台显示：
````
    GROUP=100
    HOME=/home
    INACTIVE=-1
    EXPIRE=
    SHELL=/bin/bash
    SKEL=/etc/skel
    CREATE_MAIL_SPOOL=yes
````
* 3、字段含义：
<table>
  <tr>
    <td>默认值</td>
    <td>说明</td>
  </tr>
  <tr>
    <td>GROUP=100</td>
    <td>新用户会添加到 GID 为 100 的公共组</td>
  </tr>
  <tr>
    <td>HOME=/home</td>
    <td>新用户的工作目录在/home 目录下</td>
  </tr>
  <tr>
    <td>INACTIVE=-1</td>
    <td>新用户的账户过期后不会被禁用</td>
  </tr>
    <tr>
    <td>EXPIRE=</td>
    <td>新用户不设置过期日期</td>
  </tr>
  <tr>
    <td>SHELL=/bin/bash</td>
    <td>新用户的默认 Shell 解释器</td>
  </tr>
  <tr>
    <td>SKEL=/etc/skel </td>
    <td>系统会将/etc/skel 目录下的文件复制到用户工作目录下</td>
  </tr>
  <tr>
    <td>CREATE_MAIL_SPOOL=yes</td>
    <td>为新用户创建接收邮件的文件夹</td>
  </tr>
</table>

**注意：创建用户时，系统会将/etc/skel 目录下的文件复制到新用户的工作目录下**。
* 4、/etc/skel 目录下的文件：
````
drwxr-xr-x. 4 root root 4096 2 月 11 11:51 .
drwxr-xr-x. 111 root root 12288 2 月 20 09:38 ..
-rw-r--r--. 1 root root 18 2 月 22 2013 .bash_logout
-rw-r--r--. 1 root root 176 2 月 22 2013 .bash_profile
-rw-r--r--. 1 root root 124 2 月 22 2013 .bashrc
drwxr-xr-x. 2 root root 4096 11 月 12 2010 .gnome2
drwxr-xr-x. 4 root root 4096 10 月 28 22:12 .mozilla
````
* 5、创建一个新用户：**注意：创建新用户需要使用 root 用户的权限**
  * 1>命令如下：
  ````
  useradd [-mMnr][-c <备注>][-d <登入目录>][-e <有效期限>][-f <缓冲天数>][-g <群组>]
                                                       [-G <群组>][-s <shell>][-u <uid>][用户帐号]
  或
  useradd -D [-b][-e <有效期限>][-f <缓冲天数>][-g <群组>][-G <群组>][-s <shell>]
  ````
  * 2>参数说明
  ````
  -c<备注> 　         加上备注文字。备注文字会保存在passwd的备注栏位中。
  -d<登入目录> 　     指定用户登入时的启始目录。
  -D                 变更预设值．
  -e<有效期限> 　     指定帐号的有效期限。
  -f<缓冲天数> 　     指定在密码过期后多少天即关闭该帐号。
  -g<群组> 　         指定用户所属的群组。
  -G<群组> 　         指定用户所属的附加群组。
  -m 　              自动建立用户的登入目录。
  -M 　              不要自动建立用户的登入目录。
  -n 　              取消建立以用户名称为名的群组．
  -r 　              建立系统帐号。
  -s<shell>　 　     指定用户登入后所使用的shell。
  -u<uid> 　         指定用户ID。
  ````
  * 3>实例
      * a、添加一般用户
      
            # useradd tt
      
      * b、为添加的用户指定相应的用户组

            # useradd -g root tt
      
      * c、创建一个系统用户

            # useradd -r tt
      
      * d、为新添加的用户指定home目录

            # useradd -d /home/myd tt
      
      * e、建立用户且制定ID

            # useradd caojh -u 544
      
### 二、删除用户
  删除用户需要使用 **userdel** 命令，默认情况下删除账户不同时删除工作目录，如果想同时删除账户的工作目录，需要参数-r，命令如下：

  * 删除用户：
  ````
    userdel user01
    userdel -r user01
  ````
 需要注意的是，在删除账户时，要确认工作目录下是否有重要文件，以防删除重要文件。
### 三、修改用户
* 1、修改用户相对较复杂，Linux 提供了不同的命令用来修改账户信息，如下表：
          <table>
            <tr>
              <td>命令</td>
              <td>说明</td>
            </tr>
              <tr>
              <td>usermod</td>
              <td>修改账户字段的值</td>
            </tr>
              <tr>
              <td>passwd</td>
              <td>修改账户密码</td>
            </tr>
          </table>

* 2、usermod 命令可以修改/etc/passwd 文件中大多数的字段，只要命令后面跟上相应的参数就可以了。如下所示。
  * 修改账户登录名：
  
        usermod -l newName oldName
  * usermod 命令常用的参数：
                <table>
                <tr>
                  <td>参数</td>
                  <td>说明</td>
                </tr>
                <tr>
                  <td>-l newloginname</td>
                  <td>修改登录名</td>
                </tr>
                <tr>
                  <td>-L</td>
                  <td>锁定用户</td>
                </tr>
                <tr>
                  <td>-U</td>
                  <td>解锁用户</td>
                </tr>    
              </table>
  
  * passwd 命令用来修改账户的登录密码，使用方式较简单，如果修改当前用户的密码，直

      接输入 passwd 命令即可，**如果要修改其他用户密码，需要 root 用户的权限**，命令如下。
 
      修改用户登录密码：
      ````
        passwd
        passwd user01  
      ````


