## Linux命令别名

在 Linux 中可以为一个完整的命令和相关参数设置一个别名，通过别名可以执行与命令相同的效果，这样可以减少命令行输入方便操作。

* 在 Linux 的发行版本中默认设置了几个命令的别名，可以通过命令 `alias -p 或 alias` 命令查看，如下。

      $ alias -p
      alias l.='ls -d .* --color=auto'
      alias ll='ls -l --color=auto'
      alias ls='ls --color=auto'
      alias vi='vim'
      alias which='alias | /usr/bin/which --tty-only --read-alias --show-dot --show-tilde'

* 可以使用 alias 命令定义自己的命令别名，如下例子。

      $ alias hlog='cd /usr/local/hadoop/logs'
      $ pwd
      /home/hadoop01
      $ hlog
      $ pwd
      /usr/local/hadoop/logs
      
     上面例子定义了一个命令的别名 hlog，是切换到 Hadoop 的日志目录，定义了别名后，就可以在 Shell 进程和脚本中使用了。但是要注意，定义的别名如果与局部变量相同，则只能在定义的别名的 Shell 进程中可以使用，如下说明。
     
          注：定义别名显示/etc/profile 配置文件内容到控制台
          $ alias catPf='cat /etc/profile'
          
          注：执行命令别名，下面打印文件内容到控制台，
          $ catPf
          # /etc/profile
          # System wide environment and startup programs, for login setup
          # Functions and aliases go in /etc/bashrc
          # It's NOT a good idea to change this file unless you know what you
          # are doing. It's much better to create a custom.sh shell script in
          # /etc/profile.d/ to make custom changes to your environment, as this
          # will prevent the need for merging in future updates.
          …
          
          * 注：在当前 Shell 进程中启动一个新的 Shell 子进程
          $ bash
          
          注：执行命令别名，可以看到命令找不到，说明定义的命令别名与局部变量相同，只能在定义别名的 Shell 进程中才可见
          $ catPf
          bash: catPf: command not found
          
          注：终止子 Shell 进程并回到定义别名的 Shell 进程(父进程)
          $ exit
          exit
          
          注：执行别名，没问题，找到了
          $ catPf
          # /etc/profile
          # System wide environment and startup programs, for login setup
          # Functions and aliases go in /etc/bashrc
          # It's NOT a good idea to change this file unless you know what you
          …
* 如果想将定义的别名在所有的 Shell 进程中可用，通常可以将命令别名定义在.bashrc 文件中，这样登录的用户就可以在所有的 Shell 进程和脚本中可以使用定义的命令别名了。因为配置文件.bashrc 会在启动一个 Shell 进程时提前执行，如下所示。
  ### .bashrc：
  
          # .bashrc
          # Source global definitions
          if [ -f /etc/bashrc ]; then
           . /etc/bashrc
          fi
          # User specific aliases and functions
          # 定义命令别名 vpf，用 vi 编辑器打开/etc/profile 配置文件
          # 在此文件中定义的命令别名会在所有启动的 Shell 进程或脚本中访问
          # 注意：只有当前用户启动的 Shell 进程和运行的脚本中可以访问
          alias vpf='vi /etc/profile'
          
* 命令别名调用：

          注：重新执行配置文件使配置生效
          $ . .bashrc
          
          注：调用别名，会用 vi 编辑器打开/etc/profile 配置文件
          $ vpf
          
          注：重启一个 Shell 进程
          $ bash
          
          注：调用命令别名，还可以打开/etc/profile 配置文件
          $ vpf
          
          注：退出当前进程
          $ exit
          exit
