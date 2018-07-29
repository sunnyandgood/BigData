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
 
 
