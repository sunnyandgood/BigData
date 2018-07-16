package com.mr.wordCountApp;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text,LongWritable >{
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text,LongWritable >.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String hang = value.toString();
		String[] strings = hang.split(" ");
		for(String string : strings) {
			context.write(new Text(string),new LongWritable(1));
		}		
	}
}
