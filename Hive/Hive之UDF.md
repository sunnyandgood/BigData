# Hive之UDF

* UDF---自定义函数

### 一、UDF简介

* 1、UDF函数可以直接应用于select语句，对查询结构做格式化处理后，再输出内容。

* 2、编写UDF函数的时候需要注意一下几点：

    * a）自定义UDF需要继承import org.apache.hadoop.hive.ql.exec.UDF。

    * b）需要定义并实现evaluate函数，evaluate函数支持重载。


* 3、步骤

    * a）把程序打包放到目标机器上去；

    * b）进入hive客户端，添加jar包：hive>add jar /run/jar/udf_test.jar;

    * c）创建临时函数：hive>CREATE TEMPORARY FUNCTION add_example AS 'hive.udf.Add';

    * d）查询HQL语句：

            SELECT add_example(8, 9) FROM scores;
            SELECT add_example(scores.math, scores.art) FROM scores;
            SELECT add_example(6, 7, 8, 6.8) FROM scores;

    * e）销毁临时函数：
    
            hive> DROP TEMPORARY FUNCTION add_example;
            
* 注：UDF只能实现一进一出的操作，如果需要实现多进一出，则需要实现UDAF
