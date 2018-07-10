## 文件所属管理

### 改变文件的属主需要使用 root 用户的权限，使用的命令是 chown，下面进行说明。
* 命令格式：

      chown [参数] 用户名 文件/目录名
      chown [参数] 用户名:所属组名 文件/目录名
      
* 更改文件的所有者，将文件 hadoop.txt 的所有者更改为hadoop01。用户 hadoop01 可以使用 chmod 命令允许或拒绝其他用户访问 hadoop.txt 文件

      chown hadoop01 /home/hadoop01/hadoop.txt
      
* 将/home/hadoop01 目录下的 dir1 目录的所有者和所有组更改成 hadoop01 和 hadoop01group，需要注意dir1 目录下的文件所有者和所有组不会更新

      chown hadoop01:hadoop01group /home/hadoop01/dir1
      
* 将/home/hadoop01 目录下的目录 dir1 的所有者和所有组更新为 hadoop01 和 hadoop01group，参数-R 递归实现 dir1 目录下的所有文件的所有者和所有组都进行了修改

      chown -R yarn:yarngroup /home/yarn/dir1
