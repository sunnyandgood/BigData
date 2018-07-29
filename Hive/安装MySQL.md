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
      [root@hadoop05 mysql]# /usr/bin/mysql_secure_installation




      NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MySQL
            SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!


      In order to log into MySQL to secure it, we'll need the current
      password for the root user.  If you've just installed MySQL, and
      you haven't set the root password yet, the password will be blank,
      so you should just press enter here.

      Enter current password for root (enter for none): 
      OK, successfully used password, moving on...

      Setting the root password ensures that nobody can log into the MySQL
      root user without the proper authorisation.

      Set root password? [Y/n] Y
      New password: 
      Re-enter new password: 
      Sorry, passwords do not match.

      New password: 
      Re-enter new password: 
      Password updated successfully!
      Reloading privilege tables..
       ... Success!


      By default, a MySQL installation has an anonymous user, allowing anyone
      to log into MySQL without having to have a user account created for
      them.  This is intended only for testing, and to make the installation
      go a bit smoother.  You should remove them before moving into a
      production environment.

      Remove anonymous users? [Y/n] Y
       ... Success!

      Normally, root should only be allowed to connect from 'localhost'.  This
      ensures that someone cannot guess at the root password from the network.

      Disallow root login remotely? [Y/n] n
       ... skipping.

      By default, MySQL comes with a database named 'test' that anyone can
      access.  This is also intended only for testing, and should be removed
      before moving into a production environment.

      Remove test database and access to it? [Y/n] n
       ... skipping.

      Reloading the privilege tables will ensure that all changes made so far
      will take effect immediately.

      Reload privilege tables now? [Y/n] Y
       ... Success!

      Cleaning up...



      All done!  If you've completed all of the above steps, your MySQL
      installation should now be secure.

      Thanks for using MySQL!


      [root@hadoop05 mysql]#



