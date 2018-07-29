# hive练习

### 

* 建一个heros表

      create table heros (id bigint,name string,age int,height double) row 
                                                            format delimited fields terminated by '\t';

* 上传文件到heros目录下
      
      vim heros.txt
      1       gailun  56      180.6
      2       timo    34      120.3
      3       taitan  89      200.5
      vim heros.avi
      4       jiqi    900     170.9
      5       dachongzi       200     300.8

      [root@hadoop03 /]# hdfs dfs -put /heros.txt /heros.avi /user/hive/warehouse/heros/



