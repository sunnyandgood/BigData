## if…then…else 语句

* if…then…else 语句与 if…then 语句不同的是至少要执行一个分支，当命令的退出码为 0 时执行 then 后面的语句块，
退出码为非 0 时执行 else 后面的语句块，语句格式如下：

        if 命令
        then
           语句块
        else
           语句块
        fi
* 例子：当用户不存在时，执行 else 语句

        #!/bin/bash
        # 一个不存在的用户
        user=ccc
        if grep "$user" /etc/passwd
        then
           echo "当前登录用户：$user"
           cd /home/$user
           ls -l
        else
           echo "$user 用户不存在！"
        fi
    * 控制台显示：
    
            ccc 用户不存在！
