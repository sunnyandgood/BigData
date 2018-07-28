# HBase单节点安装及使用

### 一、上传hbase-0.96.2-hadoop2-bin.tar.gz

* [获得hbase-0.96.2-hadoop2-bin.tar.gz](https://github.com/sunnyandgood/BigData/blob/master/HBase/hbase-0.96.2-hadoop2-bin.tar.gz)

* 上传

### 二、安装HBase

* 解压hbase-0.96.2-hadoop2-bin.tar.gz

      tar -zxvf hbase-0.96.2-hadoop2-bin.tar.gz -C /softWare/
  
* 修改配置文件

  * 修改hbase-env.sh
  
        vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-env.sh
        
        export JAVA_HOME=/softWare/jdk1.7.0_80

  * 修改hbase-site.xml

        vim /softWare/hbase-0.96.2-hadoop2/conf/hbase-site.xml
        
        <configuration>
              <property>
                  <name>hbase.rootdir</name>
                  <value>file:///softWare/hbase-0.96.2-hadoop2/hbase</value>
              </property>
        </configuration>
        
* 配置环境变量

      cd /etc/
      vim profile
      export HBASE_HOME=/softWare/hbase-0.96.2-hadoop2
      exprot PATH=$PATH:JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$HBASE_HOME/bin
      source profile
      
### 三、使用HBase

* 启动HBase

      cd /softWare/hbase-0.96.2-hadoop2/bin
      ./start-hbase.sh

* 查看HBase使用方法

      [root@hadoop01 bin]# ./hbase
      Usage: hbase [<options>] <command> [<args>]
      Options:
        --config DIR    Configuration direction to use. Default: ./conf
        --hosts HOSTS   Override the list in 'regionservers' file

      Commands:
      Some commands take arguments. Pass no args or -h for usage.
        shell           Run the HBase shell
        hbck            Run the hbase 'fsck' tool
        hlog            Write-ahead-log analyzer
        hfile           Store file analyzer
        zkcli           Run the ZooKeeper shell
        upgrade         Upgrade hbase
        master          Run an HBase HMaster node
        regionserver    Run an HBase HRegionServer node
        zookeeper       Run a Zookeeper server
        rest            Run an HBase REST server
        thrift          Run the HBase Thrift server
        thrift2         Run the HBase Thrift2 server
        clean           Run the HBase clean up script
        classpath       Dump hbase CLASSPATH
        mapredcp        Dump CLASSPATH entries required by mapreduce
        version         Print the version
        CLASSNAME       Run the class named CLASSNAME

* 使用Hbase

     * hbase shell
     
            [root@hadoop01 ~]# hbase shell
            2018-07-28 22:26:55,437 INFO  [main] Configuration.deprecation: hadoop.native.lib is deprecated. 
            Instead, use io.native.lib.available
            HBase Shell; enter 'help<RETURN>' for list of supported commands.
            Type "exit<RETURN>" to leave the HBase Shell
            Version 0.96.2-hadoop2, r1581096, Mon Mar 24 16:03:18 PDT 2014

            hbase(main):001:0> 

     *  help
     
            hbase(main):001:0> help
            HBase Shell, version 0.96.2-hadoop2, r1581096, Mon Mar 24 16:03:18 PDT 2014
            Type 'help "COMMAND"', (e.g. 'help "get"' -- the quotes are necessary) for help on a specific command.
            Commands are grouped. Type 'help "COMMAND_GROUP"', (e.g. 'help "general"') for help on a command group.

            COMMAND GROUPS:
              Group name: general
              Commands: status, table_help, version, whoami

              Group name: ddl（数据库操作语言）
              Commands: alter, alter_async, alter_status, create, describe, disable, disable_all, drop, drop_all, 
              enable, enable_all, exists, get_table, is_disabled, is_enabled, list, show_filters

              Group name: namespace
              Commands: alter_namespace, create_namespace, describe_namespace, drop_namespace, list_namespace, 
              list_namespace_tables

              Group name: dml（数据操作语言）
              Commands: count, delete, deleteall, get, get_counter, incr, put, scan, truncate, truncate_preserve

              Group name: tools
              Commands: assign, balance_switch, balancer, catalogjanitor_enabled, catalogjanitor_run, 
              catalogjanitor_switch, close_region, compact, flush, hlog_roll, major_compact, merge_region, move, 
              split, trace, unassign, zk_dump

              Group name: replication
              Commands: add_peer, disable_peer, enable_peer, list_peers, list_replicated_tables, remove_peer

              Group name: snapshot
              Commands: clone_snapshot, delete_snapshot, list_snapshots, rename_snapshot, restore_snapshot, snapshot

              Group name: security
              Commands: grant, revoke, user_permission

            SHELL USAGE:
            Quote all names in HBase Shell such as table and column names.  Commas delimit
            command parameters.  Type <RETURN> after entering a command to run it.
            Dictionaries of configuration used in the creation and alteration of tables are
            Ruby Hashes. They look like this:

              {'key1' => 'value1', 'key2' => 'value2', ...}

            and are opened and closed with curley-braces.  Key/values are delimited by the
            '=>' character combination.  Usually keys are predefined constants such as
            NAME, VERSIONS, COMPRESSION, etc.  Constants do not need to be quoted.  Type
            'Object.constants' to see a (messy) list of all constants in the environment.

            If you are using binary keys or values and need to enter them in the shell, use
            double-quote'd hexadecimal representation. For example:

              hbase> get 't1', "key\x03\x3f\xcd"
              hbase> get 't1', "key\003\023\011"
              hbase> put 't1', "test\xef\xff", 'f1:', "\x01\x33\x40"

            The HBase shell is the (J)Ruby IRB with the above HBase-specific commands added.
            For more on the HBase Shell, see http://hbase.apache.org/docs/current/book.html
            hbase(main):002:0>  

     * create(help 'create')
     
            hbase(main):002:0> create

            ERROR: wrong number of arguments (0 for 1)

            Here is some help for this command:
            Creates a table. Pass a table name, and a set of column family
            specifications (at least one), and, optionally, table configuration.
            Column specification can be a simple string (name), or a dictionary
            (dictionaries are described below in main help output), necessarily 
            including NAME attribute. 
            Examples:

            Create a table with namespace=ns1 and table qualifier=t1
              hbase> create 'ns1:t1', {NAME => 'f1', VERSIONS => 5}

            Create a table with namespace=default and table qualifier=t1
              hbase> create 't1', {NAME => 'f1'}, {NAME => 'f2'}, {NAME => 'f3'}
              hbase> # The above in shorthand would be the following:
              hbase> create 't1', 'f1', 'f2', 'f3'
              hbase> create 't1', {NAME => 'f1', VERSIONS => 1, TTL => 2592000, BLOCKCACHE => true}
              hbase> create 't1', {NAME => 'f1', CONFIGURATION => {'hbase.hstore.blockingStoreFiles' => '10'}}

            Table configuration options can be put at the end.
            Examples:

              hbase> create 'ns1:t1', 'f1', SPLITS => ['10', '20', '30', '40']
              hbase> create 't1', 'f1', SPLITS => ['10', '20', '30', '40']
              hbase> create 't1', 'f1', SPLITS_FILE => 'splits.txt', OWNER => 'johndoe'
              hbase> create 't1', {NAME => 'f1', VERSIONS => 5}, METADATA => { 'mykey' => 'myvalue' }
              hbase> # Optionally pre-split the table into NUMREGIONS, using
              hbase> # SPLITALGO ("HexStringSplit", "UniformSplit" or classname)
              hbase> create 't1', 'f1', {NUMREGIONS => 15, SPLITALGO => 'HexStringSplit'}
              hbase> create 't1', 'f1', {NUMREGIONS => 15, SPLITALGO => 'HexStringSplit', 
                        CONFIGURATION => {'hbase.hregion.scan.loadColumnFamiliesOnDemand' => 'true'}}

            You can also keep around a reference to the created table:

              hbase> t1 = create 't1', 'f1'

            Which gives you a reference to the table named 't1', on which you can then
            call methods.


            hbase(main):003:0>

     * create 'heros', {NAME => 'info', VERSIONS => 3}, { NAME => 'date' }
     
            hbase(main):004:0> create 'heros', {NAME => 'info', VERSIONS => 3}, { NAME => 'date' }
            SLF4J: Class path contains multiple SLF4J bindings.
            SLF4J: Found binding in [jar:file:/softWare/hbase-0.96.2-hadoop2/lib/slf4j-
            log4j12-1.6.4.jar!/org/slf4j/impl/StaticLoggerBinder.class]
            SLF4J: Found binding in [jar:file:/softWare/hadoop-2.2.0/share/hadoop/common/lib/slf4j-
            log4j12-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
            SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
            0 row(s) in 1.8830 seconds

            => Hbase::Table - heros
            hbase(main):005:0> 
            
     * list       
     
            hbase(main):005:0> list
            TABLE                                                                                  
            heros                                                                                  
            1 row(s) in 0.0580 seconds

            => ["heros"]

     * desceibe
     
            hbase(main):009:0> describe 'heros'
            DESCRIPTION                                              ENABLED                       
             'heros', {NAME => 'date', DATA_BLOCK_ENCODING => 'NONE' true                          
             , BLOOMFILTER => 'ROW', REPLICATION_SCOPE => '0', VERSI                               
             ONS => '1', COMPRESSION => 'NONE', MIN_VERSIONS => '0',                               
              TTL => '2147483647', KEEP_DELETED_CELLS => 'false', BL                               
             OCKSIZE => '65536', IN_MEMORY => 'false', BLOCKCACHE =>                               
              'true'}, {NAME => 'info', DATA_BLOCK_ENCODING => 'NONE                               
             ', BLOOMFILTER => 'ROW', REPLICATION_SCOPE => '0', VERS                               
             IONS => '3', COMPRESSION => 'NONE', MIN_VERSIONS => '0'                               
             , TTL => '2147483647', KEEP_DELETED_CELLS => 'false', B                               
             LOCKSIZE => '65536', IN_MEMORY => 'false', BLOCKCACHE =                               
             > 'true'}                                                                             
            1 row(s) in 0.1250 seconds
     
     * put
     
            hbase(main):015:0> put 'heros','rk001','info:name','gailun'
            0 row(s) in 0.1210 seconds

            hbase(main):016:0> put 'heros','rk001','info:age',45
            0 row(s) in 0.0140 seconds

            hbase(main):017:0> put 'heros','rk001','info:dazhao','大宝剑'
            0 row(s) in 0.0100 seconds
            
            hbase(main):002:0> put 'heros','rk001','date:like','找基友'
            0 row(s) in 0.1660 seconds

            hbase(main):003:0> put 'heros','rk002','info:name','zhaoxin'
            0 row(s) in 0.0080 seconds
            
            hbase(main):003:0> put 'heros','rk002','info:xiaozhao','sczj'
            0 row(s) in 0.1460 seconds
     
     * scan
     
          * scan 'heros'
          
                  hbase(main):004:0> scan 'heros'
                  ROW                    COLUMN+CELL                                                     
                   rk001                 column=date:like, timestamp=1532847628556, value=\xE6\x89\xBE\xE
                                         5\x9F\xBA\xE5\x8F\x8B                                           
                   rk001                 column=info:age, timestamp=1532847161785, value=45              
                   rk001                 column=info:dazhao, timestamp=1532847248863, value=\xE5\xA4\xA7\
                                         xE4\xBF\x9D\xE5\xAE\x9D\xE5\x89\x91                             
                   rk001                 column=info:name, timestamp=1532847103503, value=gailun         
                   rk002                 column=info:name, timestamp=1532847674775, value=zhaoxin        
                   rk002                 column=info:xiaozhao, timestamp=1532847896217, value=sczj       
                  2 row(s) in 0.1100 seconds
         
         * 加值后再scan 'heros'
          
                  hbase(main):005:0> put 'heros','rk001','info:age','78'
                  0 row(s) in 0.0070 seconds
                  hbase(main):007:0> put 'heros','rk001','info:age','56'
                  0 row(s) in 0.0100 seconds

                  hbase(main):008:0> scan 'heros'
                  ROW                    COLUMN+CELL                                                     
                   rk001                 column=date:like, timestamp=1532847628556, value=\xE6\x89\xBE\xE
                                         5\x9F\xBA\xE5\x8F\x8B                                           
                   rk001                 column=info:age, timestamp=1532848066460, value=56              
                   rk001                 column=info:dazhao, timestamp=1532847248863, value=\xE5\xA4\xA7\
                                         xE4\xBF\x9D\xE5\xAE\x9D\xE5\x89\x91                             
                   rk001                 column=info:name, timestamp=1532847103503, value=gailun         
                   rk002                 column=info:name, timestamp=1532847674775, value=zhaoxin        
                   rk002                 column=info:xiaozhao, timestamp=1532847896217, value=sczj       
                  2 row(s) in 0.0480 seconds
     
        * scan 'heros',{RAW => true,VERSIONS => 3} 
     
                  hbase(main):009:0> scan 'heros',{RAW => true,VERSIONS => 3}
                  ROW                    COLUMN+CELL                                                     
                   rk001                 column=date:like, timestamp=1532847628556, value=\xE6\x89\xBE\xE
                                         5\x9F\xBA\xE5\x8F\x8B                                           
                   rk001                 column=info:age, timestamp=1532848066460, value=56              
                   rk001                 column=info:age, timestamp=1532848049048, value=78              
                   rk001                 column=info:age, timestamp=1532847161785, value=45              
                   rk001                 column=info:dazhao, timestamp=1532847248863, value=\xE5\xA4\xA7\
                                         xE4\xBF\x9D\xE5\xAE\x9D\xE5\x89\x91                             
                   rk001                 column=info:name, timestamp=1532847103503, value=gailun         
                   rk002                 column=info:name, timestamp=1532847674775, value=zhaoxin        
                   rk002                 column=info:xiaozhao, timestamp=1532847896217, value=sczj       
                  2 row(s) in 0.0500 seconds
                  
        * 加值后再scan 'heros',{RAW => true,VERSIONS => 3}
        
                  hbase(main):010:0> put 'heros','rk001','info:age','18'
                  0 row(s) in 0.0070 seconds

                  hbase(main):011:0> scan 'heros',{RAW => true,VERSIONS => 3}
                  ROW                    COLUMN+CELL                                                     
                   rk001                 column=date:like, timestamp=1532847628556, value=\xE6\x89\xBE\xE
                                         5\x9F\xBA\xE5\x8F\x8B                                           
                   rk001                 column=info:age, timestamp=1532848697823, value=18              
                   rk001                 column=info:age, timestamp=1532848066460, value=56              
                   rk001                 column=info:age, timestamp=1532848049048, value=78              
                   rk001                 column=info:dazhao, timestamp=1532847248863, value=\xE5\xA4\xA7\
                                         xE4\xBF\x9D\xE5\xAE\x9D\xE5\x89\x91                             
                   rk001                 column=info:name, timestamp=1532847103503, value=gailun         
                   rk002                 column=info:name, timestamp=1532847674775, value=zhaoxin        
                   rk002                 column=info:xiaozhao, timestamp=1532847896217, value=sczj       
                  2 row(s) in 0.0400 seconds
        
        * scan 'heros',{RAW => true,VERSIONS => 10}
        
                  hbase(main):012:0> scan 'heros',{RAW => true,VERSIONS => 10}
                  ROW                    COLUMN+CELL                                                     
                   rk001                 column=date:like, timestamp=1532847628556, value=\xE6\x89\xBE\xE
                                         5\x9F\xBA\xE5\x8F\x8B                                           
                   rk001                 column=info:age, timestamp=1532848697823, value=18              
                   rk001                 column=info:age, timestamp=1532848066460, value=56              
                   rk001                 column=info:age, timestamp=1532848049048, value=78              
                   rk001                 column=info:age, timestamp=1532847161785, value=45              
                   rk001                 column=info:dazhao, timestamp=1532847248863, value=\xE5\xA4\xA7\
                                         xE4\xBF\x9D\xE5\xAE\x9D\xE5\x89\x91                             
                   rk001                 column=info:name, timestamp=1532847103503, value=gailun         
                   rk002                 column=info:name, timestamp=1532847674775, value=zhaoxin        
                   rk002                 column=info:xiaozhao, timestamp=1532847896217, value=sczj       
                  2 row(s) in 0.0270 seconds
        
     * quit
     
            hbase(main):014:0> quit
            [root@hadoop01 ~]#
        
### 四、停止HBase

      [root@hadoop01 ~]# cd /softWare/hbase-0.96.2-hadoop2/bin/
      [root@hadoop01 bin]# ./stop-hbase.sh 
      stopping hbase...................
      [root@hadoop01 bin]#     
