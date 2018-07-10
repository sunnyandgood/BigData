## Linux 中设置环境变量的几个文件
   系统环境变量可以让用户的程序或脚本获得系统信息，大多数系统环境变量不需要用户设置，当用户登录系统或启动一个 Bash Shell 解释程序时，系统会根据用户信息设置大多数系统环境变量，如：当前登录用户 USER 等等。

   而用户可以根据自己的需求设置自己特定的环境变量，搭建自己的系统环境。在 Linux 系统中，可以设置环境变量的文件有几个，当用户登录 Linux 系统或启动一个 Bash Shell 时，默认情况下，**这几个可配置文件会首先执行**，所以这几个配置文件可以为每个登录用户开启一个可配置的系统环境。但是需要注意的是，这几个可配置文件的执行时机和顺序是不同的，下面我们根据不同的情况说明。

### 一、登录系统时启动默认 Shell 进程
* 当用户登录 Linux 系统时，系统会默认启动一个登录 Shell 进程，这个进程主要是**初始化系统环境并且会按照一定的顺序执行几个可配置脚本文件**，这些可配置文件都是可以执行的脚本文件。执行的顺序是：
  * 1、/etc/profile
  * 2、~/.bash_profile
   
      `/etc/profile` 配置文件是系统默认的 Bash Shell 进程主启动文件，系统上的每个用户登录系统时都会被执行一次，并且只执行一次。

      另外一个文件属于用户级别的，每个用户可以根据不同需求定制自己的系统环境。不同的 Linux 发行版本这个文件略不相同，我们当前的 Linux 版本存在的是`~/.bash_profile` 文件。
      下面对两个文件的内容做一些介绍。
      
       * a、/etc/profile：
            
               # /etc/profile

               # System wide environment and startup programs, for login setup
               # Functions and aliases go in /etc/bashrc

               # It's NOT a good idea to change this file unless you know what you
               # are doing. It's much better to create a custom.sh shell script in
               # /etc/profile.d/ to make custom changes to your environment, as this
               # will prevent the need for merging in future updates.

               pathmunge () {
                   case ":${PATH}:" in
                       *:"$1":*)
                           ;;
                       *)
                           if [ "$2" = "after" ] ; then
                               PATH=$PATH:$1
                           else
                               PATH=$1:$PATH
                           fi
                   esac
               }


               if [ -x /usr/bin/id ]; then
                   if [ -z "$EUID" ]; then
                       # ksh workaround
                       EUID=`id -u`
                       UID=`id -ru`
                   fi
                   USER="`id -un`"
                   LOGNAME=$USER
                   MAIL="/var/spool/mail/$USER"
               fi

               # Path manipulation
               if [ "$EUID" = "0" ]; then
                   pathmunge /sbin
                   pathmunge /usr/sbin
                   pathmunge /usr/local/sbin
               else
                   pathmunge /usr/local/sbin after
                   pathmunge /usr/sbin after
                   pathmunge /sbin after
               fi

               HOSTNAME=`/bin/hostname 2>/dev/null`
               HISTSIZE=1000
               if [ "$HISTCONTROL" = "ignorespace" ] ; then
                   export HISTCONTROL=ignoreboth
               else
                   export HISTCONTROL=ignoredups
               fi

               export PATH USER LOGNAME MAIL HOSTNAME HISTSIZE HISTCONTROL

               # By default, we want umask to get set. This sets it for login shell
               # Current threshold for system reserved uid/gids is 200
               # You could check uidgid reservation validity in
               # /usr/share/doc/setup-*/uidgid file
               if [ $UID -gt 199 ] && [ "`id -gn`" = "`id -un`" ]; then
                   umask 002
               else
                   umask 022
               fi

               for i in /etc/profile.d/*.sh ; do
                   if [ -r "$i" ]; then
                       if [ "${-#*i}" != "$-" ]; then
                           . "$i"
                       else
                           . "$i" >/dev/null 2>&1
                       fi
                   fi
               done

               unset i
               unset -f pathmunge

         **/etc/profile 配置文件中设置并导出的环境变量在登录 Shell 进程创建的所有子进程都可以访问。另外需要注意的是一个 for 循环，这个循环主要是循环执行/etc/profile.d 目录下的所有的.sh 结尾的脚本文件**。

      * b、~/.bash_profile：
      
         `~/.bash_profile` 配置文件在用户的工作目录下，每个用户有一个。这个文件是用户专属的启动文件主要用来定义用户专有的环境变量。
      

            # .bash_profile
            # Get the aliases and functions
            if [ -f ~/.bashrc ]; then
            . ~/.bashrc
            fi
            # User specific environment and startup programs
            PATH=$PATH:$HOME/bin
            export PATH
            
### 二、非登录启动的新 Shell 进程
如果 Shell 进程不是登录时启动的，是通过命令行执行 bash 命令启动的 Shell 进程。通常称作交互式 Shell 进程，交互式进程和登录系统时启动默认 Shell 进程有所不同，不会执行/etc/profile 和~/.bash_profile 文件，但是会执行`~/.bashrc` 脚本文件。
 
* ~/.bashrc：

      # .bashrc
      # Source global definitions
      if [ -f /etc/bashrc ]; then
      . /etc/bashrc
      fi
      # User specific aliases and functions

   `.bashrc` 脚本的作用是检查/etc/bashrc 脚本是否存在，并执行此脚本。另外可以定义用户自己的命令别名。或定义一些特有的变量。
 
* /etc/bashrc：
 
      # /etc/bashrc
      …
       # Only display echos from profile.d scripts if we are no login shell
       # and interactive - otherwise just process them to set envvars
       for i in /etc/profile.d/*.sh; do
       if [ -r "$i" ]; then
       if [ "$PS1" ]; then
       . "$i"
       else
       . "$i" >/dev/null 2>&1
       fi
       fi
       done
       unset i
       unset pathmunge
      fi
      # vim:ts=4:sw=4
     注意：启动一个新的交互 Shell 进程或子 Shell 进程都会执行当前用户的~/.bashrc 脚本文件。
     
### 三、执行脚本时启动的新 Shell 进程
   当系统执行 Bash 脚本时启动的就是非交互 Shell 进程，这种进程默认情况下不会执行上面提到的脚本文件，但是可以根据需求动态指定要执行的脚本文件，这个系统变量是`BASH_ENV`，默认情况下次变量没有指定任何文件，如果通过此变量指定了要执行的配置文件，那么在执行脚本时，会首先执行指定的脚本文件然后执行脚本。可以将环境变量 BASH_ENV 配置到用户的.bashrc 文件中，当用户执行脚本时会先执行变量指定的脚本。
 
* 例子：在.bashrc 文件中或.bash_profile 文件中添加 BASH_ENV 环境变量。
