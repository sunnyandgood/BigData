# Hive练习之MySQL

### 一、准备工作

* 在windows上的mysql数据库中创建两张表（account、money）

  * account表
  
        CREATE TABLE `acount` (
        `account_id` varchar(50) NOT NULL,
        `account_name` varchar(100) default NULL,
        `account_vip` int(11) default NULL,
        PRIMARY KEY  (`account_id`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
        INSERT INTO `acount` VALUES ('1', 'zhangsan@163.com', '0');
        INSERT INTO `acount` VALUES ('2', 'lisi@163,com', '1');
        INSERT INTO `acount` VALUES ('3', 'wangwu@qq.com', '2');
        INSERT INTO `acount` VALUES ('4', 'zhaoliu', '0');
        INSERT INTO `acount` VALUES ('5', 'maqi', '1');
        
  * money表
 
        CREATE TABLE `money` (
         `m_id` varchar(50) NOT NULL,
         `m_incom` double default NULL,
         `m_paid` double default NULL,
         `account_name` varchar(100) default NULL,
         PRIMARY KEY  (`m_id`)
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
         INSERT INTO `money` VALUES ('1', '6000', '200', 'zhangsan@163.com');
         INSERT INTO `money` VALUES ('2', '9000', '0', 'lisi@163,com');
         INSERT INTO `money` VALUES ('3', '10000', '5000', 'wangwu@qq.com');
         INSERT INTO `money` VALUES ('4', '3000', '200', 'zhangsan@163.com');
         INSERT INTO `money` VALUES ('5', '80000', '90000', 'maqi');
         INSERT INTO `money` VALUES ('6', '4000', '0', 'wangwu@qq.com');
         INSERT INTO `money` VALUES ('7', '800', '0', 'lisi@163,com');
         INSERT INTO `money` VALUES ('8', '500000', '0', 'maqi');
 
### 二、在hive中创建两张表

* account表

       hive> create table account (account_id string,account_name string,account_vip int) 
                row format delimited fields terminated by '\t';      
       OK
       Time taken: 0.17 seconds

* money表

       hive> create table money (m_id string,m_income double,m_paid double,account_name string) 
                            row format delimited fields terminated by '\t';
       OK
       Time taken: 0.138 seconds

### 三、将Windows中mysq的数据直接导入到hive当中

* 将sqoop拷贝到hadoop03（运行着hive）上

       [root@hadoop01 softWare]# scp -r sqoop-1.4.4.bin__hadoop-2.0.4-alpha/ hadoop03:/softWare/

* 将mysq当中的数据直接导入到hive当中(切记关闭物理机的防火墙)

  * account表
  
        ./sqoop import \
        --connect jdbc:mysql://192.168.2.1:3306/yan \
        --username root \
        --password root \
        --table account \
        --fields-terminated-by '\t' \
        --delete-target-dir \
        --num-mappers 1 \
        --hive-import \
        --hive-database default \
        --hive-table account
  
  * money表

        ./sqoop import \
        --connect jdbc:mysql://192.168.2.1:3306/yan \
        --username root \
        --password root \
        --table money \
        --fields-terminated-by '\t' \
        --delete-target-dir \
        --num-mappers 1 \
        --hive-import \
        --hive-database default \
        --hive-table money


