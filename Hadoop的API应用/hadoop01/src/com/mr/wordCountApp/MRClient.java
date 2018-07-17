package com.mr.wordCountApp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MRClient {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration configuration=new Configuration();
		Job job=Job.getInstance(configuration);
		//设置当前作业主函数所在类
		job.setJarByClass(MRClient.class);
		
		job.setMapperClass(WordCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		FileInputFormat.setInputPaths(job, "c:/data.txt");
		
		job.setReducerClass(WordCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		FileOutputFormat.setOutputPath(job,new Path("c:/out"));
		
		job.setCombinerClass(WordCountReducer.class);
		
		//提交作业，参数：true为显示计算过程，false不显示计算过程
		job.waitForCompletion(true);
		
	}

}
