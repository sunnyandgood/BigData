## CentOS卸载OpenJDK并安装Sun JDK

* 第一步：查看Linux自带的JDK是否已安装 （卸载centOS已安装的jdk）
   * 安装好的CentOS会自带OpenJdk,用命令 java -version ，会有下面的信息：

         [root@hadoop01 /]# java -version
         java version "1.6.0"
         OpenJDK Runtime Environment (build 1.6.0-b09)
         OpenJDK 64-Bit Server VM (build 1.6.0-b09, mixed mode)

   * 先卸载掉openjdk,再安装sun公司的jdk。
     * 先查看` rpm -qa | grep java `显示如下信息：
     
            [root@hadoop01 /]# rpm -qa | grep java
            java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
            java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5

     * 卸载：

            [root@hadoop01 /]# rpm -e --nodeps java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
            [root@hadoop01 /]# rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5

   * 还有一些其他的命令

          rpm -qa | grep gcj

          rpm -qa | grep jdk

   * 如果出现找不到openjdk source的话，那么还可以这样卸载

         yum -y remove java java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
         yum -y remove java java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5

         <1># rpm -qa|grep jdk                    ← 查看jdk的信息或直接执行 
               或 
               # rpm -q jdk 
               或 
               # java -version 
         <2># rpm -qa | grep gcj                  ← 确认gcj的版本号 
         <3># yum -y remove java-1.4.2-gcj-compat ← 卸载gcj 

* 第二步：安装JDK 

<1>从SUN下载jdk-1.7.0_80-linux-i586-rpm.bin或jdk-1.7.0_80-linux-i586.bin 

     在/usr下新建java文件夹，将安装包放在/usr/java目录下 
     # mkdir /usr/java 
   
<2>安装JDK  

   * jdk-1.7.0_80-linux-i586-rpm.bin文件安装 

          # cd /usr/java 
          # chmod 777 jdk-1.7.0_80-linux-i586-rpm.bin ← 修改为可执行 
          # ./jdk-1.7.0_80-linux-i586-rpm.bin ← 选择yes同意上面的协议 
          # rpm -ivh jdk-1.7.0_80-linux-i586.rpm ← 选择yes直到安装完毕 

   * jdk-1_5_0_14-linux-i586.bin文件安装 

          # cd /usr/java 
          # chmod a+x jdk-1_5_0_14-linux-i586.bin ← 使当前用户拥有执行权限 
          # ./jdk-1_5_0_14-linux-i586.bin ← 选择yes直到安装完毕 

第三步：配置环境变量 
* 1>配置环境变量的第一种方式：**用 root 用户登录配置环境变量**
  
        vi /etc/profile

       * a、在 profile 配置文件最后添加以下内容：

                export JAVA_HOME=/usr/local/jdk1.7.0_80
                export JRE_HOME=/usr/local/jdk1.7.0_80/jre
                export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
                export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib

       * b、为了确保 JAVA_HOME 配置生效，运行 profile 脚本
        
                source /etc/profile
                或者
                . /etc/profile

       * c、在控制台输入 java 和 javac 命令检查 JDK 是否安装成功。
        
* 2>配置环境变量的第二种方式：
        在/etc/profile.d 目录下创建一个脚本文件，如：java.sh，在脚本中添加下面的配置信息保存并退出，因为/etc/profile 配置文件在执行式会将/etc/profile.d 目录下的*.sh 文件都执行一遍，同样可以是配置生效。
        
                export JAVA_HOME=/usr/local/jdk1.7.0_80
                export JRE_HOME=/usr/local/jdk1.7.0_80/jre
                export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
                export CLASS_PATH=.:$JAVA_HOME/lib:$JRE_HOME/lib

   * a、如果为 JDK 配置的单独的配置文件，那么在/etc/profile 文件中的配置信息就可以删除掉了，建议使用第二种方式，Liunx 系统核心的配置文件我们尽量不要修改和添加信息，为每一个需要配置环境变量的应用创建一个单独的配置文件，便于维护，修改和删除都不会影响原有的核心配置文件。
        
   * b、为了确保 JAVA_HOME 配置立即生效，运行 profile 脚本
        
                source /etc/profile
                或者
                . /etc/profile
                
   * c、在控制台输入 java 和 javac 命令检查 JDK 是否安装成功。
        
### JDK安装成功！

