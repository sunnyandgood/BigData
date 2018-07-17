# Combiners编程

* 每一个map可能会产生大量的输出，combiner的作用就是在map端对输出先做一次合并，以减少传输到reducer的数据量。
    
* combiner最基本是实现本地key的归并，combiner具有类似本地的reduce功能。

* 如果不用combiner，那么，所有的结果都是reduce完成，效率会相对低下。使用combiner，先完成的map会在本地聚合，提升速度。

* 注意：Combiner的输出是Reducer的输入，如果Combiner是可插拔的，添加Combiner绝不能改变最终的计算结果。所以Combiner只应该用于那种Reduce的输入key/value与输出key/value类型完全一致，且不影响最终结果的场景。比如累加，最大值等。

### 例子

>WordCountMapper类

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

>WordCountReducer类

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

>MRClient类

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

>数据

      hello tom
      hello kittty
      hello jerry
      hello cat
      hello tom
