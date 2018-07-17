package com.mr.sort.action;

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
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class SortStep {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Job job=Job.getInstance(new Configuration());
		
		job.setJarByClass(SortStep.class);
		
		job.setMapperClass(SortMapper.class);
		job.setMapOutputKeyClass(InfoBean.class);
		job.setMapOutputValueClass(NullWritable.class);
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		
		job.setReducerClass(SortReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(InfoBean.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}

	public static class SortMapper extends Mapper<LongWritable, Text, InfoBean, NullWritable>{

		private InfoBean k2 = new InfoBean();
		
		@Override
		protected void map(LongWritable key, Text value,
				Mapper<LongWritable, Text, InfoBean, NullWritable>.Context context)
						throws IOException, InterruptedException {
			String line = value.toString();
			String[] hang=line.split("\t");
			String accout = hang[0];
			double incom = Double.parseDouble(hang[1]);
			double zhichu = Double.parseDouble(hang[2]);
			
			k2.set(accout, incom, zhichu);
			
			context.write(k2,NullWritable.get());
		}
		
	}
	
	
	public static class SortReducer extends Reducer<InfoBean, NullWritable, Text, InfoBean>{

		private Text k3=new Text();
		@Override
		protected void reduce(InfoBean k2, Iterable<NullWritable> v2,
				Reducer<InfoBean, NullWritable, Text, InfoBean>.Context context) throws IOException, InterruptedException {
			k3.set(k2.getAccout());
			context.write(k3, k2);
		}
		
	}
}
