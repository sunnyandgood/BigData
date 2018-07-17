package com.mr.sort.action;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SumStep {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Job job=Job.getInstance(new Configuration());
		
		job.setJarByClass(SumStep.class);
		
		job.setMapperClass(SumMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(InfoBean.class);
		FileInputFormat.setInputPaths(job, args[0]);
		
		job.setReducerClass(sumReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(InfoBean.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
	}
	
	
	public static class SumMapper extends Mapper<LongWritable, Text, Text, InfoBean>{

		private Text k2 = new Text();
		private InfoBean v2 = new InfoBean();
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, InfoBean>.Context context)
				throws IOException, InterruptedException {
			String line=value.toString();
			String[] hang=line.split("\t");
			String accout=hang[0];
			double incom=Double.parseDouble(hang[1]);
			double zhichu=Double.parseDouble(hang[2]);
			
			k2.set(accout);
			v2.set(accout, incom, zhichu);
			context.write(k2, v2);
		}
		
	}

	
	
	public static class sumReducer extends Reducer<Text, InfoBean, Text, InfoBean>{

		private InfoBean v3 = new InfoBean();
		
		@Override
		protected void reduce(Text k2, Iterable<InfoBean> v2, Reducer<Text, InfoBean, Text, InfoBean>.Context context)
				throws IOException, InterruptedException {
			double sumIncom = 0;
			double sumZhichu = 0;
			for(InfoBean infoBean : v2){
				sumIncom += infoBean.getIncom();
				sumZhichu += infoBean.getZhichu();
			}
			v3.set("", sumIncom, sumZhichu);
			context.write(k2, v3);
		}
		
	}
}
