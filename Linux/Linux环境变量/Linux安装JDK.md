## Linux安装JDK

我们之后安装的 Hadoop 本身就是用 Java 语言编写的，在运行计算程序时需要存在 Java环境，
也就是要在 Linux 系统上安装并配置 JDK。Linux 系统发行版本已经安装了其他版本的 JDK，
虽然可以使 Java 程序正常的工作，但是还是建议使用 Sun 提供的 JDK。另外， 
Hadoop2.0 版本以上要求 Linux 的 JDK 版本在 1.6 及以上。我们安装 1.8 的版本。
安装之前，首先将 Linux 系统预安装的 JDK 删除。


* 1、通过命令删除预安装的 JDK 版本

        rpm -qa | grep java
        rpm -e --nodeps 文件名

* 2、卸载预安装 jdk yum -y remove jdk 版本名称

        用命令 java -version 检查是否已经卸载
 
* 3、安装 JDK

        cd /usr/local
        tar -xzvf jdk-8u51-linux-i586.tar.gz

     解压完成后在/usr/local 目录下多了一个 jdk1.8.0.51 目录，注意 JAVA_HOME 就是 JDK的安装目录/usr/local/ jdk1.8.0.51。

  * 1>配置环境变量的第一种方式：**用 root 用户登录配置环境变量**
  
        vi /etc/profile

       * a、在 profile 配置文件最后添加以下内容：

                export JAVA_HOME=/usr/local/jdk1.8.0_51
                export JRE_HOME=/usr/local/jdk1.8.0_51/jre
                export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
                export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib

       * b、为了确保 JAVA_HOME 配置生效，运行 profile 脚本
        
                source /etc/profile
                或者
                . /etc/profile

       * c、在控制台输入 java 和 javac 命令检查 JDK 是否安装成功。
        
   * 2>配置环境变量的第二种方式：
        在/etc/profile.d 目录下创建一个脚本文件，如：java.sh，在脚本中添加下面的配置信息保存并退出，因为/etc/profile 配置文件在执行式会将/etc/profile.d 目录下的*.sh 文件都执行一遍，同样可以是配置生效。
        
                export JAVA_HOME=/usr/local/jdk1.8.0_51
                export JRE_HOME=/usr/local/jdk1.8.0_51/jre
                export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
                export CLASS_PATH=.:$JAVA_HOME/lib:$JRE_HOME/lib

        * a、如果为 JDK 配置的单独的配置文件，那么在/etc/profile 文件中的配置信息就可以删除掉了，建议使用第二种方式，Liunx 系统核心的配置文件我们尽量不要修改和添加信息，为每一个需要配置环境变量的应用创建一个单独的配置文件，便于维护，修改和删除都不会影响原有的核心配置文件。
        
        * b、为了确保 JAVA_HOME 配置立即生效，运行 profile 脚本
        
                source /etc/profile
                或者
                . /etc/profile
                
        * c、在控制台输入 java 和 javac 命令检查 JDK 是否安装成功。
