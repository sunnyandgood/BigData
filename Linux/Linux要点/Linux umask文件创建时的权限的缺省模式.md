## Linux umask文件创建时的权限的缺省模式

   当用户登录系统中时，**umask 命令**会确定用户创建文件时的权限缺省模式。这一命令和chmod 命令正好相反。
通常情况下系统管理员 root 要为普通用户设置一个合理的 umask值，确保用户在创建文件时具有合理的缺省权限，防止其他非同组用户对你的文件具有**写权限**。

   用户登录以后，可以使用 umask 命令来改变文件创建时的缺省权限。但是这个设置将在退出系统时失效。
umask 命令是在/etc/profile 文件中设置，每个用户在登录时都会执行这个文件，所以如果希望改变所有用户的 umask，只有 root 用户才可以进行修改。
如果用户希望设置自己的 umask 值，可以在`.bash_profile` 文件中进行设置。

### 一、如何设置 umask 值
   通过 umask 命令可以设定文件创建时的缺省模式，对于每一类用户(文件属主、同组用户、其他用户)都存在一个相应的 umask 值中的数字。对于文件来说，这一数字的**最大值为 6**。因为系统不允许你在创建一个文本文件时就赋予它执行权限，必须在创建后使用chmod 命令在增加执行权限。目录允许设置执行权限，所以对于目录来说，**umask 中各个数字最大可以到 7**。
 
命令格式如下：

      umask nnn

   nnn 的取值范围从 000~777，下表中是 umask 值对应的权限位。
   
* 表：umask 值和权限位的对应值

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/Linux%20/LinuxKeyPoints/img/umask%20%E5%80%BC%E5%92%8C%E6%9D%83%E9%99%90%E4%BD%8D%E7%9A%84%E5%AF%B9%E5%BA%94%E5%80%BC.png"/></div>

* 举例：

<table>
   <tr>
      <td>文件权限</td>
      <td>rwxrwxrwx</td>
      <td>777</td>
      <td></td>
   </tr>
   <tr>
      <td>umask 值 002</td>
      <td>-------w-</td>
      <td>002</td>
      <td>异或方式</td>
   </tr>
   <tr>
      <td>目录</td>
      <td>rwxrwxr-x</td>
      <td>775</td>
      <td>目录最大值为 7，有执行权限</td>
   </tr>   
   <tr>
      <td>文件</td>
      <td>rw-rw-r--</td>
      <td>664</td>
      <td>文件最大值为 6，没有执行权限(系统默认)</td>
   </tr>   
   <tr>
      <td>umask 值 022</td>
      <td>----w--w-</td>
      <td>022</td>
      <td></td>
   </tr>   
   <tr>
      <td>目录</td>
      <td>rwxr-xr-x</td>
      <td>755</td>
      <td></td>
   </tr>   
   <tr>
      <td>文件</td>
      <td>rw-r--r--</td>
      <td>644</td>
      <td></td>
   </tr>   
</table>
