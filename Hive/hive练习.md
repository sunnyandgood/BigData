# hive练习

### 

* 建一个heros表

      create table heros (id bigint,name string,age int,height double) row 
                                                            format delimited fields terminated by '\t';

* 上传文件到heros目录下

      [root@hadoop03 ~]# hdfs dfs -put /s.txt /t.txt /user/hive/warehouse/heros/



