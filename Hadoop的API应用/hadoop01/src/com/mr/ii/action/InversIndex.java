package com.mr.ii.action;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InversIndex {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Job job=Job.getInstance(new Configuration());
		
		job.setJarByClass(InversIndex.class);
		
		job.setMapperClass(InversMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		
		job.setReducerClass(InversReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setCombinerClass(InversConbiner.class);
		
		job.waitForCompletion(true);
	}

	
	public static class InversMapper extends Mapper<LongWritable, Text, Text, Text>{

		private Text k2=new Text();
		private Text v2=new Text();
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			String hang=value.toString();
			String[] values=hang.split("\t");
			
			for(String string : values){
				FileSplit in=(FileSplit) context.getInputSplit();
				Path path=in.getPath();
				String fileName=path.getName();
				
				k2.set(string+"->"+ fileName);
				v2.set("1");
				context.write(k2, v2);
			}
		}
	}
	
	public static class InversConbiner extends Reducer<Text, Text, Text, Text>{

		private Text k22=new Text();
		private Text v22=new Text();
		@Override
		protected void reduce(Text k2, Iterable<Text> v2, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String keyAndName = k2.toString();
			String[] strings=keyAndName.split("->");
			String key = strings[0];
			String fileName = strings[1];
			
			long sum = 0;
			
			for(Text text : v2){
				sum += Long.parseLong(text.toString());
			}
			k22.set(key);
			v22.set(fileName +"->"+ sum);
			
			context.write(k22, v22);
		}
	}
	
	public static class InversReducer extends Reducer<Text, Text, Text, Text>{

		private Text v3=new Text();
		@Override
		protected void reduce(Text k2, Iterable<Text> v2, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String sum ="";
			for(Text text : v2){
				sum += text.toString() + "\t";
			}
			
			v3.set(sum);
			context.write(k2, v3);
		}
		
	}
}
