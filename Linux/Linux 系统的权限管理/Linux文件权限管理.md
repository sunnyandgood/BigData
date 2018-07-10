## 文件权限管理
如果想修改一个文件或目录的已有权限，首先文件的属主是当前用户或者是超级用户root，如果是其他用户的文件是没有权限进行修改的。

如果要修改文件的权限，需要使用 **chmod** 命令，有两种使用方式，下面进行说明。

### 一、命令格式一：

        chmod [用户] [运算符] [权限] 文件/目录名

* 用户：

        u 表示文件所有者
        g 表示同组用户
        o 表示其它用户
        a 表示所有用户
* 运算符：

        + 添加某个权限
        - 取消某个权限
        = 赋予给定的权限并取消原有的权限
* 权限：

        r 可读
        w 可写
        x 可执行
        
* 给/home/hadoop01 目录下的 hadoop.txt 文件所有者和所在组添加读写权限

        chmod ug+rw /home/hadoop01/hadoop.txt
        
* 取消临时用户的执行权限

        chmod o-x /home/hadoop01/hadoop.txt

* 给临时用户重新设置读权限

        chmod o=r /home/hadoop01/hadoop.txt

### 二、命令格式二：

        chmod [权限] 文件/目录名

* 第二种方式更加简单，采用二进制方式设置：
        <table>
          <tr>
            <td colspan="3">文件所有者</td>
            <td colspan="3">文件所有者所在组</td>
            <td colspan="3">其他临时用户</td>
            <td colspan="1">值</td>
          </tr>
          <tr>
            <td>r</td>
            <td>w</td>
            <td>x</td>
            <td>x</td>
            <td>r</td>
            <td>w</td>
            <td>x</td>
            <td>r</td>
            <td>w</td>
            <td>/</td>
          </tr>
            <tr>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>777</td>
          </tr>
            <tr>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>1</td>
            <td>0</td>
            <td>1</td>
            <td>0</td>
            <td>0</td>
            <td>0</td>
            <td>750</td>
          </tr>
        </table>

* 同时设置三种类型用户的权限

         chmod 777 /home/hadoop01/hadoop.txt

* 同时设置三种类型用户的权限
 
         chmod 750 /home/hadoop01/hadoop.txt

* 对目录进行权限的设置，如果目录内的文件同时设置权限，需要使用递归方式，需要使用参数-R。

     同时将目录下的所有文件设置权限
     
        chmod -R 777 /home/yarn/dir
