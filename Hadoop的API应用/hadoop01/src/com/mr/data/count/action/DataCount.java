package com.mr.data.count.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet.P;

public class DataCount {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub
		Job job=Job.getInstance(new Configuration());
		
		job.setJarByClass(DataCount.class);
		
		job.setMapperClass(DataCountMapper.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DataBean.class);
		FileInputFormat.setInputPaths(job, args[0]);
		
		job.setReducerClass(DataCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DataBean.class);
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setPartitionerClass(DataPartitioner.class);
		job.setNumReduceTasks(Integer.parseInt(args[2]));
		
		job.waitForCompletion(true);
	}

	
	public static class DataCountMapper extends Mapper<LongWritable, Text, Text, DataBean>{

		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DataBean>.Context context)
				throws IOException, InterruptedException {
			String hang=value.toString();
			String[] strings=hang.split("\t");
			String phone=strings[1];
			long up=Long.parseLong(strings[2]);
			long down=Long.parseLong(strings[3]);
			DataBean dataBean=new DataBean(phone,up, down);
			
			context.write(new Text(phone), dataBean);
		}
		
	}
	
	
	public static class DataCountReducer extends Reducer<Text, DataBean, Text, DataBean>{

		@Override
		protected void reduce(Text k2, Iterable<DataBean> v2, Reducer<Text, DataBean, Text, DataBean>.Context context)
				throws IOException, InterruptedException {
			long upSum=0;
			long downSum=0;
			
			for(DataBean dataBean:v2){
				upSum += dataBean.getUpPayLoad();
				downSum += dataBean.getDownPayLoad();
			}
			
			DataBean dataBean=new DataBean(k2.toString(),upSum,downSum);
			
			context.write(new Text(k2), dataBean);
		}
		
	}
	
	
	public static class DataPartitioner extends Partitioner<Text, DataBean>{

		private static Map<String,Integer> map=new HashMap<String,Integer>();
		
		static{
			/**
			 * 规则：1表示移动，2表示联通，3表示电信，0表示其他
			 */
			map.put("134", 1);
			map.put("135", 1);
			map.put("136", 1);
			map.put("137", 1);
			map.put("138", 2);
			map.put("139", 2);
			map.put("150", 3);
			map.put("159", 3);
		}
		
		@Override
		public int getPartition(Text key, DataBean value, int numPartitions) {
			// TODO Auto-generated method stub
			String tel=key.toString();
			String tel_sub=tel.substring(0, 3);
			Integer code=map.get(tel_sub);
			if(code == null){
				code = 0;
			}
			return code;
		}
		
	}
}
