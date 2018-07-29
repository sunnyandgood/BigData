package com.hbase.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Before;
import org.junit.Test;

public class HbaseDemo {

	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Configuration configuration =HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "hadoop04:2181,hadoop05:2181,hadoop06:2181");
		
		HBaseAdmin admin = new HBaseAdmin(configuration);
		
		TableName hTableName =TableName.valueOf("mingxing");
		
		HTableDescriptor descriptor = new HTableDescriptor(hTableName);
		HColumnDescriptor hColumnDescriptor1 = new HColumnDescriptor("info");
		hColumnDescriptor1.setMaxVersions(3);
		
		HColumnDescriptor hColumnDescriptor2 = new HColumnDescriptor("data");
		descriptor.addFamily(hColumnDescriptor1);
		descriptor.addFamily(hColumnDescriptor2);
		
		admin.createTable(descriptor);
		
		admin.close();
	}

	Configuration configuration=null;
	
	@Before
	public void init(){
		configuration =HBaseConfiguration.create();
		configuration.set("hbase.zookeeper.quorum", "hadoop04:2181,hadoop05:2181,hadoop06:2181");
	}
	
	@Test
	public void testPut() throws Exception{
		HTable hTable=new HTable(configuration, Bytes.toBytes("mingxing"));
		Put put=new Put(Bytes.toBytes("rk001"));
		put.add(Bytes.toBytes("info"), Bytes.toBytes("age"), Bytes.toBytes("345"));
		hTable.put(put);
		hTable.close();
	}
	
	@Test
	public void testPutList() throws Exception{
		HTable hTable=new HTable(configuration, Bytes.toBytes("mingxing"));
		List<Put> list= new ArrayList<Put>();
		for(int i=1;i<=1000001;i++){
			Put put=new Put(Bytes.toBytes(""+i));
			put.add(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("jingtian"+i));
			list.add(put);
			if(i % 10000 == 0){
				hTable.put(list);
				list=new ArrayList<Put>();
			}
		}
		hTable.put(list);
		hTable.close();
	}
	
	@Test
	public void testDelete() throws Exception{
		HTable hTable=new HTable(configuration, Bytes.toBytes("mingxing"));
		List<Delete> list= new ArrayList<Delete>();
		for(int i=1;i<=1000001;i++){
			Delete delete=new Delete(Bytes.toBytes(""+i));
			list.add(delete);
			if(i % 10000 == 0){
				hTable.delete(list);
				list=new ArrayList<Delete>();
			}
		}
		hTable.delete(list);
		hTable.close();
	}
	
	@Test
	public void testGet() throws Exception{
		HTable hTable=new HTable(configuration, Bytes.toBytes("mingxing"));
		Get get=new Get(Bytes.toBytes("9999"));
		Result result=hTable.get(get);
		List<KeyValue> list=result.list();
		for(KeyValue keyValue : list){
			System.out.println(new String(keyValue.getFamily()) + "=====" + keyValue.getQualifier()+ "====="+ new String(keyValue.getValue()));
		}
		hTable.close();
	}
	
	@Test
	public void testScan() throws IOException{
		HTable hTable=new HTable(configuration, Bytes.toBytes("mingxing"));
		
		Scan scan=new Scan(Bytes.toBytes("299990"), Bytes.toBytes("300000"));
		
		ResultScanner  resultScanner=hTable.getScanner(scan);
		
		for(Result result : resultScanner){
			String value = new String(result.getValue(Bytes.toBytes("info"), Bytes.toBytes("name")));
			System.out.println(value);
		}
		
		hTable.close();
	}
}
