# HBase单节点安装

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

* 配置环境变量

      cd /etc/
      vim profile
      export HBASE_HOME=/softWare/hbase-0.96.2-hadoop2
      exprot PATH=$PATH:JAVA_HOME/bin:$JRE_HOME/bin:$HADOOP_HOME/bin:$HBASE_HOME/bin
      source profile

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

              Group name: ddl
              Commands: alter, alter_async, alter_status, create, describe, disable, disable_all, drop, drop_all, 
              enable, enable_all, exists, get_table, is_disabled, is_enabled, list, show_filters

              Group name: namespace
              Commands: alter_namespace, create_namespace, describe_namespace, drop_namespace, list_namespace, 
              list_namespace_tables

              Group name: dml
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
