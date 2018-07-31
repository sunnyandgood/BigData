package cn.itcast.hadoop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Cleaner extends Configured implements Tool{
	@Override
	public int run(String[] args) throws Exception {
		final String inputPath = args[0];
		final String outPath = args[1];
		
		final Configuration conf = new Configuration();
		final Job job = new Job(conf, Cleaner.class.getSimpleName());
		job.setJarByClass(Cleaner.class);
		
		FileInputFormat.setInputPaths(job, inputPath);
		
		job.setMapperClass(MyMapper.class);
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setReducerClass(MyReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);
		FileOutputFormat.setOutputPath(job, new Path(outPath));
		
		job.waitForCompletion(true);
		return 0;
	}
	public static void main(String[] args)  throws Exception{
		ToolRunner.run(new Cleaner(), args);
	}
	
	
	static class MyMapper extends Mapper<LongWritable, Text, LongWritable, Text>{
		LogParser parser = new LogParser();
		
		Text v2 = new Text();
		protected void map(LongWritable key, Text value, org.apache.hadoop.mapreduce.Mapper<LongWritable,Text,LongWritable,Text>.Context context) throws java.io.IOException ,InterruptedException {
			final String line = value.toString();
			final String[] parsed = parser.parse(line);
			final String ip = parsed[0];
			final String logtime = parsed[1];
			String url = parsed[2];
			
			//过滤所有静态的资源请求
			if(url.startsWith("GET /static")||url.startsWith("GET /uc_server")){
				return;
			}
			
			if(url.startsWith("GET")){
				url = url.substring("GET ".length()+1, url.length()-" HTTP/1.1".length());
			}
			if(url.startsWith("POST")){
				url = url.substring("POST ".length()+1, url.length()-" HTTP/1.1".length());
			}
			
			v2.set(ip+"\t"+logtime +"\t"+url);
			context.write(key, v2);
		};
	}
	
	static class MyReducer extends Reducer<LongWritable, Text, Text, NullWritable>{
		protected void reduce(LongWritable k2, java.lang.Iterable<Text> v2s, org.apache.hadoop.mapreduce.Reducer<LongWritable,Text,Text,NullWritable>.Context context) throws java.io.IOException ,InterruptedException {
			for (Text v2 : v2s) {
				context.write(v2, NullWritable.get());
			}
		};
	}
}
class LogParser {
	public static final SimpleDateFormat FORMAT = new SimpleDateFormat("d/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);
	public static final SimpleDateFormat dateformat1=new SimpleDateFormat("yyyyMMddHHmmss");
//	public static void main(String[] args) throws ParseException {
//		final String S1 = "27.19.74.143 - - [30/May/2013:17:38:20 +0800] \"GET /static/image/common/faq.gif HTTP/1.1\" 200 1127";
//		LogParser parser = new LogParser();
//		final String[] array = parser.parse(S1);
//		System.out.println("样例数据： "+S1);
//		System.out.format("解析结果：  ip=%s, time=%s, url=%s, status=%s, traffic=%s", array[0], array[1], array[2], array[3], array[4]);
//	}
	/**
	 * 解析日志的行记录
	 * @param line
	 * @return 数组含有5个元素，分别是ip、时间、url、状态、流量
	 */
	public String[] parse(String line){
		String ip = parseIP(line);
		String time;
		try {
			time = parseTime(line);
		} catch (Exception e1) {
			time = "null";
		}
		String url;
		try {
			url = parseURL(line);
		} catch (Exception e) {
			url = "null";
		}
		String status = parseStatus(line);
		String traffic = parseTraffic(line);
		
		return new String[]{ip, time ,url, status, traffic};
	}
	
	private String parseTraffic(String line) {
		final String trim = line.substring(line.lastIndexOf("\"")+1).trim();
		String traffic = trim.split(" ")[1];
		return traffic;
	}
	private String parseStatus(String line) {
		String trim;
		try {
			trim = line.substring(line.lastIndexOf("\"")+1).trim();
		} catch (Exception e) {
			trim = "null";
		}
		String status = trim.split(" ")[0];
		return status;
	}
	private String parseURL(String line) {
		final int first = line.indexOf("\"");
		final int last = line.lastIndexOf("\"");
		String url = line.substring(first+1, last);
		return url;
	}
	private String parseTime(String line) {
		final int first = line.indexOf("[");
		final int last = line.indexOf("+0800]");
		String time = line.substring(first+1,last).trim();
		try {
			return dateformat1.format(FORMAT.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	private String parseIP(String line) {
		String ip = line.split("- -")[0].trim();
		return ip;
	}
	
}
