## 在 Bash 脚本中使用变量
与 Java 或其他语言相似，在 Bash 脚本中也可以使用变量，将值保存到变量中在脚本中使用，下面通过例子说明在 Bash 脚本中变量的使用。
在之前我们讲了 Linux 系统中的环境变量，那么对 Bash 脚本的变量就更容易理解了。 

### 一、Bash 脚本中的环境变量    

* 在 Linux 系统中维护着一组环境变量，用来记录系统的各类信息，如当前的用户、用户 ID 和主机名称等等信息，这些环境变量的值可以在 Bash 脚本中使用。
可以通过 set 命令 看到 Linux 系统维护的环境变量的值。    
   * 命令： 
    
          set 
 
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
 
        这些环境变量是系统维护的，我们可以在脚本中使用，引用变量需要使用$符号，如下：
        
        * 例：在脚本中访问系统环境变量 
        
              #!/bin/bash 
              # 使用系统维护的环境变量 
              echo "当前的用户：$USER" 
              echo "当前用户 ID：$UID" 
              echo "用户的工作目录：$HOME" 
              echo "主机名：$HOSTNAME" 
              echo "系统默认脚本解释器：$BASH" 
 
        * 控制台输出： 
          
              当前的用户：hadoop01
              当前用户 ID：500 
              户的工作目录：/home/hadoop01
              主机名：hadoop01 
              系统默认脚本解释器：/bin/bash 
 
             这里需要注意的是$符，系统会认为后面跟着的是一般变量，在脚本执行过程中，系统会 将变量的值替换显示。所以如果想显示$符到控制台，
             需要进行转义： 
   
               echo "\$USER：$USER" 
               $USER：hadoop01 
 
   * 对变量的引用可以可以采用${varname}的方式，大括号中是变量名称，修改上面的例子。
      * 例：采用${varname}的方式访问系统环境变量 
 
            #!/bin/bash 
            # 使用系统维护的环境变量 

            echo "当前的用户：${USER}" 
            echo "当前用户 ID：${UID}" 
            echo "用户的工作目录：${HOME}" 
            echo "主机名：${HOSTNAME}" 
            echo "系统默认脚本解释器：${BASH}" 
 
      * 控制台输出： 

            当前的用户：hadoop01
            当前用户 ID：500 
            用户的工作目录：/home/hadoop01 
            主机名：hadoop01 
            系统默认脚本解释器：/bin/bash 
 
   **使用效果是相同的，建议使用第二种方式**。 
 
### Bash 脚本中的用户变量    

* 在用户脚本中可以定义和使用变量，定义后的变量可以在脚本中引用。
这些变量的生命周期同脚本的执行周期相同，脚本执行结束变量同时在内存中被销毁。
为了区别环境变量，用户变量建议使用小写字母，并且变量 Var 和 var 是不同的两个变量，要注意变量名区分大小写。    

* 为变量赋值要使用等号，与其他语言的区别是，**变量名、等号和变量值之间是不能存在空格**。如下：    
  * 例： 

        #!/bin/bash 
        name=张三 
        age=22 
        address=山西太原 

        echo "name：$name" 
        echo "age：$age" 
        echo "address：$address" 
 
       Bash 脚本中的变量类型会根据变量的值决定，引用变量需要用到$符。 
 
* 例子：编写两个脚本文件，在第一个脚本中调用第二个脚本，父脚本中设置局部变量， 在子脚本中进行访问，
因为执行子脚本会启动一个新的子进程，所以父脚本中的局部变 量不能访问到。 
 
 
* 例子：编写两个脚本文件，在第一个脚本中调用第二个脚本，父脚本中设置环境变量并 使用 export 命令将用户环境变量导出，
在子脚本中进行访问父脚本导出的环境变量，因 为执行子脚本会启动一个新的子进程，在子进程中可以访问到父脚本中的导出的用户环 境变量。 
 
 
* 例子：编写两个脚本文件，在第一个脚本中使用”.”的方式调用第二个脚本，父脚本中设 置局部变量，
再设置用户环境变量并使用 export 命令将用户环境变量导出，在子脚本中 进行访问父脚本导出的环境变量和局部变量，
因为采用.的方式启动子脚本不会启动一个 新的子进程，还是在父进程中运行，所以，
在子进程中可以访问到父脚本中的导出的用 户环境变量和局部变量。 
