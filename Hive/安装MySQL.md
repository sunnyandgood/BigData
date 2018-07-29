# 安装MySQL

### 上传MySQL

* [获得MyQSL](https://github.com/sunnyandgood/BigData/tree/master/Hive/mysql安装包)

* 上传

### 安装MySQL

* 卸载自带mysql

      [root@hadoop05 mysql]# rpm -qa | grep mysql
      mysql-libs-5.1.71-1.el6.i686
      [root@hadoop05 mysql]# rpm -e mysql-libs-5.1.71-1.el6.i686 --nodeps
      [root@hadoop05 mysql]# rpm -qa | grep mysql
      [root@hadoop05 mysql]#

* 安装mysql

      [root@hadoop05 mysql]# rpm -ivh MySQL-server-5.1.73-1.glibc23.i386.rpm
      Preparing...                ########################################### [100%]
         1:MySQL-server           ########################################### [100%]

      PLEASE REMEMBER TO SET A PASSWORD FOR THE MySQL root USER !
      To do so, start the server, then issue the following commands:

      /usr/bin/mysqladmin -u root password 'new-password'
      /usr/bin/mysqladmin -u root -h hadoop05 password 'new-password'

      Alternatively you can run:
      /usr/bin/mysql_secure_installation

      which will also give you the option of removing the test
      databases and anonymous user created by default.  This is
      strongly recommended for production servers.

      See the manual for more instructions.

      Please report any problems with the /usr/bin/mysqlbug script!

      Starting MySQL.. SUCCESS! 
      [root@hadoop05 mysql]# rpm -ivh MySQL-client-5.1.73-1.glibc23.i386.rpm 
      Preparing...                ########################################### [100%]
         1:MySQL-client           ########################################### [100%]







