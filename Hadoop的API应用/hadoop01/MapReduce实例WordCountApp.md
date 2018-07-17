# [MapReduce实例WordCountApp](https://github.com/sunnyandgood/BigData/tree/master/Hadoop%E7%9A%84API%E5%BA%94%E7%94%A8/hadoop01/src/com/mr/wordCountApp)

## [MapReduce原理](https://github.com/sunnyandgood/BigData/blob/master/MapReduce/MapReduce%E5%8E%9F%E7%90%86.md)

### 一、WordCountMapper类

    package com.mr.wordCountApp;

    import java.io.IOException;

    import org.apache.hadoop.io.LongWritable;
    import org.apache.hadoop.io.Text;
    import org.apache.hadoop.mapreduce.Mapper;

    public class WordCountMapper extends Mapper<LongWritable, Text, Text,LongWritable >{
      protected void map(LongWritable key, Text value, 
      Mapper<LongWritable, Text, Text,LongWritable >.Context context)
          throws IOException, InterruptedException {
        // TODO Auto-generated method stub
        String hang = value.toString();
        String[] strings = hang.split(" ");
        for(String string : strings) {
          context.write(new Text(string),new LongWritable(1));
        }		
      }
    }

### 二、WordCountReducer类

package com.mr.wordCountApp;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable>{

	@Override
	protected void reduce(Text key2, Iterable<LongWritable> value2,
			Reducer<Text, LongWritable, Text, LongWritable>.Context context) 
      throws IOException, InterruptedException {
		long sum=0;
		
		for(LongWritable i :value2){
			sum += i.get();
		}
		
		context.write(key2,new LongWritable(sum));
	}	
}

### 三、MRClient类

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
        FileInputFormat.setInputPaths(job, "hdfs://hadoop01:9000/words");

        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
        FileOutputFormat.setOutputPath(job,new Path("hdfs://hadoop01:9000/out"));
        //提交作业，参数：true为显示计算过程，false不显示计算过程
        job.waitForCompletion(true);	
      }
    }
    
### 四、操作

  [root@hadoop01 /]# cd /mnt/softWare/hadoop-2.2.0/sbin/
  [root@hadoop01 sbin]# ./start-dfs.sh 
  [root@hadoop01 sbin]# ./start-yarn.sh
  [root@hadoop01 sbin]# cd / 
  [root@hadoop01 /]# vim /words
  [root@hadoop01 /]# cat /words 
  hello tom
  hello kittty
  hello jerry
  hello cat
  hello tom
  [root@hadoop01 /]# hdfs dfs -put /words /
  [root@hadoop01 /]# hadoop jar WordCount.jar 
