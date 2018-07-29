# HBase基础知识

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/TheHadoopEcosytem.png"/></div>

### 一、HBase简介

* HBase – Hadoop Database，是一个高可靠性、高性能、面向列、可伸缩的分布式存储系统，利用HBase技术可在廉价PC Server上搭建起大规模结构化存储集群。HBase利用Hadoop HDFS作为其文件存储系统，利用Hadoop MapReduce来处理HBase中的海量数据，利用Zookeeper作为协调工具。

* 思考：如何存储物流信息

      运单号：625311858890

      2018-01-07 10:09:25	上海			已发货
      2018-01-07 18:27:32	上海航空部		已收件
      2018-01-07 20:25:38	快件离开上海航空部	已发往北京
      2018-01-09 08:27:14	北京回龙观		派件中
      2018-01-09 12:37:37	北京回龙观		已签收

### 二、HBase三要素

* 1、主键：Row Key （主键是用来检索记录的主键，访问hbase table中的行，只有三种方式：）

     * 通过单个row key访问

     * 通过row key的range

     * 全表扫描（scan）

* 2、列族：Column Family

     * 列族在创建表的时候声明，一个列族可以包含多个列，列中的数据都是以二进制形式存在，没有数据类型。

* 3、时间戳：timestamp

     * HBase中通过row和columns确定的为一个存贮单元称为cell。每个 cell都保存着同一份数据的多个版本。版本通过时间戳来索引

### 三、HBASE基础知识

* 1、物理存储
	 
     * able 在行的方向上分割为多个HRegion，一个region由[startkey,endkey)表示，每个HRegion分散在不同的RegionServer中

     <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/物理存储1.png"></div> 

     <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/物理存储2.png"></div>

* 2、架构体系

* Client  包含访问hbase 的接口，client 维护着一些cache 来加快对hbase 的访问，比如regione 的位置信息

* Zookeeper
 
 	* 保证任何时候，集群中只有一个running master
 
 	* 存贮所有Region 的寻址入口	
 
 	* 实时监控Region Server 的状态，将Region server 的上线和下线信息，实时通知给Master
 
 	* 存储Hbase 的schema,包括有哪些table，每个table 有哪些column family

* Master 可以启动多个HMaster，通过Zookeeper的Master Election机制保证总有一个Master运行

	* 为Region server 分配region

	* 负责region server 的负载均衡

	* 发现失效的region server 并重新分配其上的region

* 3、HBase的逻辑梳理

   * HBase中有两张特殊的Table，-ROOT-(hbase:namespace)和.META.(hbase:meta)
	
     * -ROOT-(hbase:namespace) ：记录了.META.(hbase:meta)表的Region信息，-ROOT-(hbase:namespace)只有一个region
		
     * .META.(hbase:meta) ：记录了用户创建的表的Region信息，.META.(hbase:meta)可以有多个regoin

    * Zookeeper中记录了-ROOT-(hbase:namespace)表的location

    * Client访问用户数据之前需要首先访问zookeeper，然后访问-ROOT-(hbase:namespace)表，接着访问.META.(hbase:meta)表，最后才能找到用户数据的位置去访问

    <div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/table.png"/></div>

### 四、Region Server

* 维护Master 分配给它的region，处理对这些region 的IO 请求

* 负责切分在运行过程中变得过大的region

可以看出，client 访问hbase 上数据的过程并不需要master 参与，寻址访问先zookeeper再regionserver，数据读写访问regioneserver。HRegionServer主要负责响应用户I/O请求，向HDFS文件系统中读写数据，是HBase中最核心的模块。 	

### 五、系统架构

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/系统架构.png"></div>

### 六、HBASE与相关软件

* hadoop-2.2.0

* hbase-0.96.2-hadoop2

* JDK7

* RHEL6.3

* 前提条件：本机或集群环境下hadoop.1.1.2已经安装成功

### 七、HBase Shell

* hbase提供了一个shell的终端给用户交互（#$HBASE_HOME/bin/hbase shell  ）

<table>
   <tr>
      <td>名称</td>
      <td>命令表达式</td>
   </tr>
   <tr>
      <td>创建表</td>
      <td>create '表名称', '列族名称1','列族名称2','列族名称N'</td>
   </tr>
   <tr>
      <td>添加记录      </td>
      <td>put '表名称', '行名称', '列名称:', '值'</td>
   </tr>
   <tr>
      <td>查看记录</td>
      <td>get '表名称', '行名称'</td>
   </tr>
   <tr>
      <td>查看表中的记录总数</td>
      <td>count  '表名称'</td>
   </tr>
   <tr>
      <td>删除记录</td>
      <td>delete  '表名' ,'行名称' , '列名称'</td>
   </tr>
   <tr>
      <td>删除一张表</td>
      <td>先要屏蔽该表，才能对该表进行删除，第一步 disable '表名称' 第二步  drop '表名称'</td>
   </tr>
   <tr>
      <td>查看所有记录</td>
      <td>scan "表名称"  </td>
   </tr>
   <tr>
      <td>查看某个表某个列中所有数据</td>
      <td>scan "表名称" , {COLUMNS=>'列族名称:列名称'}</td>
   </tr>
   <tr>
      <td>更新记录 </td>
      <td>就是重写一遍进行覆盖</td>
   </tr>
</table>

### 八、HBASE Shell的DDL操作（数据库操作语言））

* 1、创建表

		>create 'users','user_id','address','info'
		表users,有三个列族user_id,address,info

* 2、列出全部表

		>list 

* 3、得到表的描述

		>describe 'users'

* 4、创建表

		>create 'users_tmp','user_id','address','info'

* 5、删除表

		>disable 'users_tmp'
		>drop 'users_tmp'

-----------------------------------------------------------------
* 其他操作

		#$HBASE_HOME/bin/hbase shell
		…… 
		>quit 

		>exists 'users'
		>is_enabled 'users'
		>is_disabled 'users

### 九、HBASE Shell的DML操作（数据操作语言）

* 1、添加记录

		put 'users','xiaoming','info:age','24';
		put 'users','xiaoming','info:birthday','1987-06-17';
		put 'users','xiaoming','info:company','alibaba';
		put 'users','xiaoming','address:contry','china';
		put 'users','xiaoming','address:province','zhejiang';
		put 'users','xiaoming','address:city','hangzhou';
		put 'users','zhangyifei','info:birthday','1987-4-17';
		put 'users','zhangyifei','info:favorite','movie';
		put 'users','zhangyifei','info:company','alibaba';
		put 'users','zhangyifei','address:contry','china';
		put 'users','zhangyifei','address:province','guangdong';
		put 'users','zhangyifei','address:city','jieyang';
		put 'users','zhangyifei','address:town','xianqiao';


* 2、获取一条记录

	* 1.取得一个id的所有数据

			>get 'users','xiaoming'

	* 2.获取一个id，一个列族的所有数据
	
			>get 'users','xiaoming','info'

	* 3.获取一个id，一个列族中一个列的所有数据

			get 'users','xiaoming','info:age'

* 3、更新记录

		>put 'users','xiaoming','info:age' ,'29'
		>get 'users','xiaoming','info:age'
		>put 'users','xiaoming','info:age' ,'30'
		>get 'users','xiaoming','info:age'

* 4、获取单元格数据的版本数据

		>get 'users','xiaoming',{COLUMN=>'info:age',VERSIONS=>1}
		>get 'users','xiaoming',{COLUMN=>'info:age',VERSIONS=>2}
		>get 'users','xiaoming',{COLUMN=>'info:age',VERSIONS=>3}

* 5、获取单元格数据的某个版本数据

		〉get 'users','xiaoming',{COLUMN=>'info:age',TIMESTAMP=>1364874937056}

* 6、全表扫描

		>scan 'users'

* 7、删除xiaoming值的'info:age'字段

		>delete 'users','xiaoming','info:age'
		>get 'users','xiaoming'

* 8、删除整行

		>deleteall 'users','xiaoming'

* 9、统计表的行数

		>count 'users'

* 10、清空表

		>truncate 'users'

### 十、HBASE的Java_API

* hbase操作必备

		//hbase操作必备
		private static Configuration getConfiguration() {
			Configuration conf = HBaseConfiguration.create();
			conf.set("hbase.rootdir", "hdfs://hadoop0:9000/hbase");
			//使用eclipse时必须添加这个，否则无法定位
			conf.set("hbase.zookeeper.quorum", "hadoop0");
			return conf;
		}

* 创建一张表

		//创建一张表
		public static void create(String tableName, String columnFamily) throws IOException{
			HBaseAdmin admin = new HBaseAdmin(getConfiguration());
			if (admin.tableExists(tableName)) {
				System.out.println("table exists!");
			}else{
				HTableDescriptor tableDesc = new HTableDescriptor(tableName);
				tableDesc.addFamily(new HColumnDescriptor(columnFamily));
				admin.createTable(tableDesc);
				System.out.println("create table success!");
			}
		}

* 添加一条记录

		//添加一条记录
		public static void put(String tableName, String row, String columnFamily, 
					String column, String data) throws IOException{
			HTable table = new HTable(getConfiguration(), tableName);
			Put p1 = new Put(Bytes.toBytes(row));
			p1.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(data));
			table.put(p1);
			System.out.println("put'"+row+"',"+columnFamily+":"+column+"','"+data+"'");
		}

* 读取一条记录

		//读取一条记录
		public static void get(String tableName, String row) throws IOException{
			HTable table = new HTable(getConfiguration(), tableName);
			Get get = new Get(Bytes.toBytes(row));
			Result result = table.get(get);
			System.out.println("Get: "+result);
		}

* 显示所有数据

		//显示所有数据
		public static void scan(String tableName) throws IOException{
			HTable table = new HTable(getConfiguration(), tableName);
			Scan scan = new Scan();
			ResultScanner scanner = table.getScanner(scan);
			for (Result result : scanner) {
				System.out.println("Scan: "+result);
			}
		}

* 删除表

		//删除表
		public static void delete(String tableName) throws IOException{
			HBaseAdmin admin = new HBaseAdmin(getConfiguration());
			if(admin.tableExists(tableName)){
				try {
				  admin.disableTable(tableName);
				  admin.deleteTable(tableName);
				} catch (IOException e) {
				  e.printStackTrace();
				  System.out.println("Delete "+tableName+" 失败");
				}
			}
			System.out.println("Delete "+tableName+" 成功");
		}

* 主函数

		public static void main(String[] args) throws IOException {
			String tableName="hbase_tb";
			String columnFamily="cf";

			HBaseTestCase.create(tableName, columnFamily);
			HBaseTestCase.put(tableName, "row1", columnFamily, "cl1", "data");
			HBaseTestCase.get(tableName, "row1");
			HBaseTestCase.scan(tableName);
			HBaseTestCase.delete(tableName);
		}


### 十一、练习——详单入库

* 1、题目要求(一)

	* HBASE表定义为：

			>create 'wlan_log', 'cf'

	* 源文件数据增加一个字段rowkey

			RowKey设计：
			msisdn:日期时间串（yyyyMMddHHmmss）

	<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/详单入库.png"></div>

* 2、解题方法

	 * 1>HBASE结合MapReduce批量导入
	
		static class BatchImportMapper extends Mapper<LongWritable, Text, LongWritable, Text>{
			SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss");
			Text v2 = new Text();

			protected void map(LongWritable key, Text value, Context context) throws 
			java.io.IOException ,InterruptedException {
				final String[] splited = value.toString().split("\t");
				try {
					final Date date = new Date(Long.parseLong(splited[0].trim()));
					final String dateFormat = dateformat1.format(date);
					String rowKey = splited[1]+":"+dateFormat;
					v2.set(rowKey+"\t"+value.toString());
					context.write(key, v2);
				} catch (NumberFormatException e) {
					final Counter counter = context.getCounter("BatchImport", "ErrorFormat");
					counter.increment(1L);
					System.out.println("出错了"+splited[0]+" "+e.getMessage());
				}
			};
		}


		static class BatchImportReducer extends TableReducer<LongWritable, Text, NullWritable>{
			protected void reduce(LongWritable key, java.lang.Iterable<Text> values, Context context) 
			throws java.io.IOException ,InterruptedException {
				for (Text text : values) {
					final String[] splited = text.toString().split("\t");

					final Put put = new Put(Bytes.toBytes(splited[0]));
					put.add(Bytes.toBytes("cf"), Bytes.toBytes("date"), Bytes.toBytes(splited[1]));
					//省略其他字段，调用put.add(....)即可
					context.write(NullWritable.get(), put);
				}
			};
		}


		public static void main(String[] args) throws Exception {
			final Configuration configuration = new Configuration();
			//设置zookeeper
			configuration.set("hbase.zookeeper.quorum", "hadoop0");
			//设置hbase表名称
			configuration.set(TableOutputFormat.OUTPUT_TABLE, "wlan_log");
			//将该值改大，防止hbase超时退出
			configuration.set("dfs.socket.timeout", "180000");

			final Job job = new Job(configuration, "HBaseBatchImport");

			job.setMapperClass(BatchImportMapper.class);
			job.setReducerClass(BatchImportReducer.class);
			//设置map的输出，不设置reduce的输出类型
			job.setMapOutputKeyClass(LongWritable.class);
			job.setMapOutputValueClass(Text.class);

			job.setInputFormatClass(TextInputFormat.class);
			//不再设置输出路径，而是设置输出格式类型
			job.setOutputFormatClass(TableOutputFormat.class);

			FileInputFormat.setInputPaths(job, "hdfs://hadoop0:9000/input");

			job.waitForCompletion(true);
		}


* 3、查询

		（1）按RowKey查询
		（2）按手机号码查询
		（3）按手机号码的区域查询

	* 查询手机13450456688的所有上网记录

			查询手机13450456688的所有上网记录
			public static void scan(String tableName) throws IOException{
				HTable table = new HTable(getConfiguration(), tableName);
				Scan scan = new Scan();
				scan.setStartRow(Bytes.toBytes("13450456688:/"));
				scan.setStopRow(Bytes.toBytes("13450456688::"));
				ResultScanner scanner = table.getScanner(scan);
				int i=0;
				for (Result result : scanner) {
					System.out.println("Scan: "+i+++" "+result);
				}
			}

	<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/查询记录.png"></div>
	
	* 查询134号段的所有上网记录

			查询134号段的所有上网记录
			public static void scanPeriod(String tableName) throws IOException{
					HTable table = new HTable(getConfiguration(), tableName);
					Scan scan = new Scan();
					scan.setStartRow(Bytes.toBytes("134/"));
					scan.setStopRow( Bytes.toBytes("134:"));
					scan.setMaxVersions(1);
					ResultScanner scanner = table.getScanner(scan);
					int i=0;
					for (Result result : scanner) {
						System.out.println("Scan: "+i+++" "+result);
					}
			}

	
	<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/查询记录2.png"></div>
	
	
### 十二、思考

* HBASE是什么数据库，与普通RDBMS有什么区别

* HBASE的结构

* HBASE的常用命令

<div align="center"><img src="https://github.com/sunnyandgood/BigData/blob/master/HBase/img/ASCII码表.png"></div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	









