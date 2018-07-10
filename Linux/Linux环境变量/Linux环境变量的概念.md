## 环境变量的概念
  **环境变量用来保存系统环境信息和用户工作环境中一些特定的信息，使用环境变量可以 将我们需要的信息保存到内存，在系统生命周期内运行的程序或脚本都可以访问到**。
* 环境变量按定义者可以分为两类:

  * 一类是系统默认设置的环境变量，如：PATH。
  * 另一类 是用户特定的环境变量，如：JAVA_HOME。

  环境变量通常使用**大写字母**并且变量名**不能重复**，如果重复，后面变量的赋值会覆盖前面的赋值。 

* 环境变量按可见性也可以分为两类：
  * 一类是全局变量，也就是声明全局变量的脚 本和此脚本所有子脚本都可以访问。
  * 另一类是局部变量，只在创建变量的脚本中是可见 的，当脚本执行结束，变量也同时销毁。

  这里需要注意的是系统环境变量中也分为全局环境变量和局部环境变量，与后面章节讲到的**用户全局环境变量和用户局部变量**还是有一些区别的。 

* 对于 Linux 系统默认设置的全局环境变量可以通过命令 **printenv**查看： 

      命令： printenv 
 
    * 控制台显示： 
    
          [root@localhost etc]# printenv
          HOSTNAME=localhost
          SELINUX_ROLE_REQUESTED=
          TERM=xterm
          SHELL=/bin/bash
          HISTSIZE=1000
          SSH_CLIENT=192.168.48.1 51643 22
          SELINUX_USE_CURRENT_RANGE=
          OLDPWD=/
          SSH_TTY=/dev/pts/1
          JRE_HOME=/mnt/softWare/jdk1.7.0_80/jre
          USER=root
          LS_COLORS=rs=0:di=01;34:ln=01;36:mh=00:pi=40;33:so=01;35:do=01;35:bd=40;33;01:
                                               cd=40;33;01:or=40;31;01:mi=01;05;37;41:su=37;41:s
          MAIL=/var/spool/mail/root
          PATH=/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/mnt/softWare
                                   /jdk1.7.0_80/bin:/mnt/softWare/jdk1.7.0_80/jre/bin:/root/bin
          PWD=/etc
          JAVA_HOME=/mnt/softWare/jdk1.7.0_80
          LANG=en_US.UTF-8
          CLASS_PASS=.:/mnt/softWare/jdk1.7.0_80/lib:/mnt/softWare/jdk1.7.0_80/jre/lib
          SELINUX_LEVEL_REQUESTED=
          SSH_ASKPASS=/usr/libexec/openssh/gnome-ssh-askpass
          HISTCONTROL=ignoredups
          SHLVL=1
          HOME=/root
          LOGNAME=root
          SSH_CONNECTION=192.168.48.1 51643 192.168.48.135 22
          LESSOPEN=|/usr/bin/lesspipe.sh %s
          G_BROKEN_FILENAMES=1
          _=/usr/bin/printenv


         可以看到很多大写变量名称和他们的值，这些值大多数是用户登录系统时设置并保存到内存的，并且系统中所有的进程都可以访问(包括用户程序进程)。 
 
 * 要想显示单个变量的值，可以通过 echo 命令和$符，如下： 
 
          命令： echo $USER yarn 
 
      而**系统局部环境变量**只能在定义他们的脚本中或进程中可以访问到，包括这些进程的子进程。

      **这和用户局部变量也是存在区别的**，因为用户程序进程执行结束后，这些变量就不存在了，而系统局部变量定义的进程的生命周期和系统的生命周期相同，
      所以启动这些进程中的子进程是可以访问到这些系统局部变量的。

* 系统局部变量可以通过 set 命令 显示，但是这些变量是不同进程中的局部变量。 

          命令：  set 
 
     * 控制台输出： 
     
           [root@localhost etc]# set
           BASH=/bin/bash
           BASHOPTS=checkwinsize:cmdhist:expand_aliases:extquote:force_fignore:hostcomplete:
                                          interactive_comments:login_shell:progcomp:promptvars:sourcepath
           BASH_ALIASES=()
           BASH_ARGC=()
           BASH_ARGV=()
           BASH_CMDS=()
           BASH_LINENO=()
           BASH_SOURCE=()
           BASH_VERSINFO=([0]="4" [1]="1" [2]="2" [3]="1" [4]="release" [5]="i386-redhat-linux-gnu")
           BASH_VERSION='4.1.2(1)-release'
           CLASS_PASS=.:/mnt/softWare/jdk1.7.0_80/lib:/mnt/softWare/jdk1.7.0_80/jre/lib
           COLORS=/etc/DIR_COLORS
           COLUMNS=88
           DIRSTACK=()
           EUID=0
           GROUPS=()
           G_BROKEN_FILENAMES=1
           HISTCONTROL=ignoredups
           HISTFILE=/root/.bash_history
           HISTFILESIZE=1000
           HISTSIZE=1000
           HOME=/root
           HOSTNAME=localhost
           HOSTTYPE=i386
           ID=0
           IFS=$' \t\n'
           JAVA_HOME=/mnt/softWare/jdk1.7.0_80
           JRE_HOME=/mnt/softWare/jdk1.7.0_80/jre
           LANG=en_US.UTF-8
           LESSOPEN='|/usr/bin/lesspipe.sh %s'
           LINES=25
           LOGNAME=root
           MACHTYPE=i386-redhat-linux-gnu
           MAIL=/var/spool/mail/root
           MAILCHECK=60
           OLDPWD=/
           OPTERR=1
           OPTIND=1
           OSTYPE=linux-gnu
           PATH=/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin:/mnt/softWare/jdk1.7.0_80/bin:
                                                               /mnt/softWare/jdk1.7.0_80/jre/bin:/root/bin
           PIPESTATUS=([0]="0")
           PPID=2479
           PROMPT_COMMAND='printf "\033]0;%s@%s:%s\007" "${USER}" "${HOSTNAME%%.*}" "${PWD/#$HOME/~}"'
           PS1='[\u@\h \W]\$ '
           PS2='> '
           PS4='+ '
           PWD=/etc
           SELINUX_LEVEL_REQUESTED=
           SELINUX_ROLE_REQUESTED=
           SELINUX_USE_CURRENT_RANGE=
           SHELL=/bin/bash
           SHELLOPTS=braceexpand:emacs:hashall:histexpand:history:interactive-comments:monitor
           SHLVL=1
           SSH_ASKPASS=/usr/libexec/openssh/gnome-ssh-askpass
           SSH_CLIENT='192.168.48.1 51643 22'
           SSH_CONNECTION='192.168.48.1 51643 192.168.48.135 22'
           SSH_TTY=/dev/pts/1
           TERM=xterm
           UID=0
           USER=root
           _=printenv
           colors=/etc/DIR_COLORS

* printenv 命令输出的全局环境变量在 set 命令中也都输出了，所以 **set 命令输出了所有的系统环境变量**。 
