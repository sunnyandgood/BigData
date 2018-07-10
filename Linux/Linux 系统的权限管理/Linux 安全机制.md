## Linux 安全机制

  **账户管理是 Linux 安全机制的核心部分**。登录 Linux 系统的用户都会被分配一个的用户账户。
用户对系统上文件的访问权限取决于他们登录系统时使用的账户。每个用户的权限是通过创建用户时分配的用户 ID 来实现跟踪的，
每个用户的 UID 是唯一的。而用户登录系统时使用的不是 UID 而是**用户名和相应的密码**。

在 Linux 系统中，通过特定的文件跟踪和管理系统上的用户账户等信息，这个文件是`/etc/passwd`。

### 1、/etc/passwd 文件：
````
[root@localhost etc]# cat passwd
root:x:0:0:root:/root:/bin/bash
bin:x:1:1:bin:/bin:/sbin/nologin
daemon:x:2:2:daemon:/sbin:/sbin/nologin
adm:x:3:4:adm:/var/adm:/sbin/nologin
lp:x:4:7:lp:/var/spool/lpd:/sbin/nologin
······
abrt:x:173:173::/etc/abrt:/sbin/nologin
gdm:x:42:42::/var/lib/gdm:/sbin/nologin
sshd:x:74:74:Privilege-separated SSH:/var/empty/sshd:/sbin/nologin
tcpdump:x:72:72::/:/sbin/nologin
hadoop01:x:500:500:hadoop01:/home/hadoop01:/bin/bash
````

Linux 系统通过/etc/passwd 文件将用户的登录名对应到用户的 UID 值，如：root 用户的
UID 值为 0，hadoop01 用户的 UID 值为 500。这个文件包含了登录系统用户的相关信息。但
是需要注意的是，/etc/passwd 文件中除了登录系统的用户信息外，**Linux 系统会为特定
的功能组件创建不同的用户账户，这些账户不是正真意义上的用户账户，通常被称作系
统账户，是系统上运行的各种服务进程访问资源时要用到的特殊账户**。

注意：所有运行在后台的服务都需要用一个系统用户账户首先登录到 Linux 系统上。

 Linux 系统预留了 500 以下的值作为备用系统账户的 UID 值，普通用户账户的 UID 值从
500 开始。如：hadoop01 用户的 UID 值为 500。同样，为了系统的安全，一些系统服务要使
用特定的 UID 才能正常工作。

### 2、/etc/passwd 文件中包含以下信息：

      For example: hadoop01:x:500:500:hadoop01:/home/hadoop01:/bin/bash

<table>
  <tr>
    <td>字段</td>
    <td>说明</td>
  </tr>
    <tr>
    <td>登录用户名</td>
    <td>hadoop01</td>
  </tr>
    <tr>
    <td>用户密码</td>
    <td>x  注：在其他文件中加密保存，此处是占位符</td>
  </tr>
    <tr>
    <td>用户账户 ID：UID</td>
    <td>500</td>
  </tr>
    <tr>
    <td>用户账户组 ID：GID</td>
    <td>500</td>
  </tr>
    <tr>
    <td>用户全名或备注</td>
    <td>hadoop01</td>
  </tr>
    <tr>
    <td>用户默认工作目录</td>
    <td>/home/hadoop01</td>
  </tr>
    <tr>
    <td>用户的默认 Shell 程序</td>
    <td>/bin/bash</td>
  </tr>
</table>

可以看到，**账户的密码都设置成了 x，这不是正真的密码，只是一个占位符**。在 Linux
系统中，系统将**用户账户的密码保存到了另一个单独的文件/etc/shadow 中**。只有特定的
程序才能访问这个文件，如用户登录系统时。

 对于/etc/passwd 文件，不要手动修改或添加用户信息。因为这个文件是系统读写的，手
动添加或修改账户信息可能会出现系统登录失败。**通过/etc/shadow 文件系统对用户的登录密码进行管理和保护**。

### 3、/etc/shadow 文件：
````
[root@localhost etc]# cat shadow
root:$1$uKC/KChi$uMgdLLe1XG83xkc1wnzcW0:17715:0:99999:7:::
bin:*:15980:0:99999:7:::
daemon:*:15980:0:99999:7:::
adm:*:15980:0:99999:7:::
lp:*:15980:0:99999:7:::
······
postfix:!!:17715::::::
abrt:!!:17715::::::
gdm:!!:17715::::::
sshd:!!:17715::::::
tcpdump:!!:17715::::::
hadoop01:$1$uKC/KChi$uMgdLLe1XG83xkc1wnzcW0:17715:0:99999:7:::
````

   **注意：只有 root 用户才有权限访问/etc/shadow 文件，这比/etc/passwd 文件更加安全**。
   
### 4、/etc/shadow 文件字段信息：

    hadoop01:$1$uKC/KChi$uMgdLLe1XG83xkc1wnzcW0:17715:0:99999:7:::
   
<table>
  <tr>
    <td>字段</td>
    <td>说明</td>
  </tr>
    <tr>
    <td>登录用户名</td>
    <td>hadoop01</td>
  </tr>
    <tr>
    <td>加密后的密码</td>
    <td></td>
  </tr>
    <tr>
    <td>1970.1.1 到当前天数</td>
    <td>17715</td>
  </tr>
    <tr>
    <td>多少天后才能更改密码</td>
    <td>0</td>
  </tr>
    <tr>
    <td>多少天后必须更改密码</td>
    <td>99999</td>
  </tr>
    <tr>
    <td>密码过期前提前多少天提醒用户更改密码</td>
    <td>7</td>
  </tr>
    <tr>
    <td>密码过期后多少天禁用用户账户</td>
    <td></td>
  </tr>
    <tr>
    <td>用户账户被禁用的日期</td>
    <td></td>
  </tr>
    <tr>
    <td>预留</td>
    <td></td>
  </tr>
</table>   
