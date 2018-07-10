## CentOS卸载OpenJDK并安装Sun JDK

* 第一步：查看Linux自带的JDK是否已安装 （卸载centOS已安装的1.4）

安装好的CentOS会自带OpenJdk,用命令 java -version ，会有下面的信息：

java version "1.6.0"
OpenJDK Runtime Environment (build 1.6.0-b09)
OpenJDK 64-Bit Server VM (build 1.6.0-b09, mixed mode)

最好还是先卸载掉openjdk,在安装sun公司的jdk.

先查看 rpm -qa | grep java

显示如下信息：

java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5

卸载：

rpm -e --nodeps java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
rpm -e --nodeps java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5

还有一些其他的命令

rpm -qa | grep gcj

rpm -qa | grep jdk

如果出现找不到openjdk source的话，那么还可以这样卸载

yum -y remove java java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
yum -y remove java java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5

<1># rpm -qa|grep jdk ← 查看jdk的信息或直接执行 
或 
# rpm -q jdk 
或 
# java -version 
<2># rpm -qa | grep gcj ← 确认gcj的版本号 
<3># yum -y remove java-1.4.2-gcj-compat ← 卸载gcj 

第二步：安装JDK 
<1>从SUN下载jdk-1_5_0_14-linux-i586-rpm.bin或jdk-1_5_0_14-linux-i586.bin 
在/usr下新建java文件夹，将安装包放在/usr/java目录下 
# mkdir /usr/java 
<2>安装JDK 
# cd /usr/java 
①jdk-1_5_0_14-linux-i586-rpm.bin文件安装 
# chmod 777 jdk-1_5_0_14-linux-i586-rpm.bin ← 修改为可执行 
# ./jdk-1_5_0_14-linux-i586-rpm.bin ← 选择yes同意上面的协议 
# rpm -ivh jdk-1_5_0_14-linux-i586.rpm ← 选择yes直到安装完毕 
②jdk-1_5_0_14-linux-i586.bin文件安装 
# chmod a+x jdk-1_5_0_14-linux-i586.bin ← 使当前用户拥有执行权限 
# ./jdk-1_5_0_14-linux-i586.bin ← 选择yes直到安装完毕 

第三步：配置环境变量 
<1># vi /etc/profile 
<2>在最后加入以下几行： 
export JAVA_HOME=/usr/java/jdk1.6.0_10 
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar 
export PATH=$PATH:$JAVA_HOME/bin 
<3># reboot ← 重启机器配置生效

本文CentOS版本为5.4 final，使用图形界面与命令结合的操作方式

由于CentOS 5.4在默认情况下，会安装OpenOffice之类的软件，而这些软件需要Java支持，因此系统会默认安装一个JDK环境，如果需要使用特定的Java环境，最好将这些默认安装的JDK卸载或者彻底删除。

在安装完成CentOS 5.4之后，直接在终端中输入“java -version”，系统会显示当前的jdk版本号“java-1.6.0-openjdk-1.6.0.0-1.7.b09.el5”，可以看到jdk使用的是openjdk1.6版本的。

如果此时，直接在centOS的“应用程序--添加/删除软件--基本系统--java”中，删除java，与之相关的openoffice等软件，也会自动随之删除，因此，在进行原有的jdk删除之前，最好先安装配置好新的JDK及相应的环境。

首先，在Sun（现在属于Oracle）的网站下载相应的JDK版本，这里使用的是目前最新的JDK6.0_update21版本，在下载时，建议下载-rpm的安装文件，将下载完成的文件放在指定位置，这里直接放在/usr下。默认下载的JDK文件名较长，可以根据需要修改文件名，这里修改为“jdk-6u21.bin”。

其次，在jdk所在目录中打开终端，给当前用户赋予执行权限，“chmod +x jdk-6u21.bin”，或者直接在图形界面下右击文件，在弹出菜单中依次选择“属性”--“权限”，选择“以程序执行文件”复选框，“确定”按钮，完成执行权限的赋予。

然后，在jdk所在目录中打开终端，在终端中输入“./jdk-6u21.bin”执行解压及安装操作。此时，在“/usr”目录下，新增一个“/java”目录，另外，会有一些以“sun”开头的rpm文件，这些文件不用理会。

接下来，设置Java的环境变量。

•用文本编辑器打开/etc/profile（说明：根目录下的/etc/目录，其中的profile文件）
•在profile文件末尾加入如下字符串
JAVA_HOME=/usr/java/jdk1.6.0_21
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export JAVA_HOME
export PATH
export CLASSPATH
至此，新的JDK环境安装配置完成。

接下来，删除原有的jdk环境。

首先，在终端中输入“rpm -qa|grep gcj”，查看gcj的版本号，在这里得到的结果是：

java-1.4.2-gcj-compat-1.4.2.0-40jpp.115
libgcj-4.1.2-48.el5

其次，卸载系统自带jdk。在终端中输入“yum -y remove java java-1.4.2-gcj-compat-1.4.2.0-40jpp.115”，然后等待，等待系统卸载完自带的jdk。最终在终端中显示“Complete!”，卸载完成。

至此，关于CentOS的JDK配置基本完成，重启系统。

在终端中输入“java -version”，系统显示：

java version "1.6.0_21"
Java(TM) SE Runtime Environment (build 1.6.0_21-b06)
Java HotSpot(TM) Client VM (build 17.0-b16, mixed mode, sharing)

JDK安装成功！


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

