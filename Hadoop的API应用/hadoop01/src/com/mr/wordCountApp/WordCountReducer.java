package com.mr.wordCountApp;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	@Override
	protected void reduce(Text key2, Iterable<LongWritable> value2,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context) throws IOException, InterruptedException {
		long sum=0;
		
		for(LongWritable i :value2){
			sum += i.get();
		}
		
		context.write(key2,new LongWritable(sum));
	}

	
}
