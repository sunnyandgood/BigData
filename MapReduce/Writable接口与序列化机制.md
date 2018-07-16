## Writable接口与序列化机制

### 一、序列化概念

* 序列化（Serialization）是指把结构化对象转化为字节流。

* 反序列化（Deserialization）是序列化的逆过程。即把字节流转回结构化对象。

* Java序列化（java.io.Serializable）

### 二、Hadoop序列化的特点

* 序列化格式特点：

    * 紧凑：高效使用存储空间。
    
    * 快速：读写数据的额外开销小
    
    * 可扩展：可透明地读取老格式的数据
    
    * 互操作：支持多语言的交互

* Hadoop的序列化格式：Writable

### 三、Hadoop序列化的作用

* 序列化在分布式环境的两大作用：进程间通信，永久存储。

* Hadoop节点间通信。

  <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/Hadoop%E8%8A%82%E7%82%B9%E9%97%B4%E9%80%9A%E4%BF%A1.png"/></div>
  
  
### 四、Writable接口  
  
Writable接口, 是根据 DataInput 和 DataOutput 实现的简单、有效的序列化对象.
MR的任意Key和Value必须实现Writable接口.
  
   <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/Writable%E6%8E%A5%E5%8F%A3.png"/></div>  
  
MR的任意key必须实现WritableComparable接口
  
   <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/WritableComparable%E6%8E%A5%E5%8F%A3.png"/></div>  
  
### 五、序列化与反序列化例子

>数据

            1363157985066 	13726230503		2481	24681	200
            1363157993044 	18211575961		1527	2106	200
            1363157995074 	84138413	   	4116	1432	200
            1363157993055 	13560439658		1116	954	200
            1363157995033 	15920133257		3156	2936	200
            1363157983019	13719199419		240	0	200
            1363157984041 	13660577991		6960	690	200
            1363157973098 	15013685858		3659	3538	200
            1363157986029 	15989002119		1938	180	200
            1363157992093 	13560439658		918	4938	200
            1363157986041 	13480253104		180	180	200
            1363157984040 	13602846565		1938	2910	200


            13726230503	2481	24681	sum

>DataBean类

         import java.io.DataInput;
         import java.io.DataOutput;
         import java.io.IOException;

         import org.apache.hadoop.io.Writable;

         public class DataBean implements Writable{

            //电话号码
            private String phone;
            //上行流量
            private Long upPayLoad;
            //下行流量
            private Long downPayLoad;
            //总流量
            private Long totalPayLoad;

            public DataBean(){}

            public DataBean(String phone,Long upPayLoad, Long downPayLoad) {
               super();
               this.phone=phone;
               this.upPayLoad = upPayLoad;
               this.downPayLoad = downPayLoad;
               this.totalPayLoad=upPayLoad+downPayLoad;
            }

            /**
             * 序列化
             * 注意：序列化和反序列化的顺序和类型必须一致
             */
            @Override
            public void write(DataOutput out) throws IOException {
               // TODO Auto-generated method stub
               out.writeUTF(phone);
               out.writeLong(upPayLoad);
               out.writeLong(downPayLoad);
               out.writeLong(totalPayLoad);
            }

            /**
             * 反序列化
             */
            @Override
            public void readFields(DataInput in) throws IOException {
               // TODO Auto-generated method stub
               this.phone=in.readUTF();
               this.upPayLoad=in.readLong();
               this.downPayLoad=in.readLong();
               this.totalPayLoad=in.readLong();
            }

            @Override
            public String toString() {
               return upPayLoad +"\t"+ downPayLoad +"\t"+  totalPayLoad;
            }

            public String getPhone() {
               return phone;
            }

            public void setPhone(String phone) {
               this.phone = phone;
            }

            public Long getUpPayLoad() {
               return upPayLoad;
            }

            public void setUpPayLoad(Long upPayLoad) {
               this.upPayLoad = upPayLoad;
            }

            public Long getDownPayLoad() {
               return downPayLoad;
            }

            public void setDownPayLoad(Long downPayLoad) {
               this.downPayLoad = downPayLoad;
            }

            public Long getTotalPayLoad() {
               return totalPayLoad;
            }

            public void setTotalPayLoad(Long totalPayLoad) {
               this.totalPayLoad = totalPayLoad;
            }
         }

>DataCount类

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
         }
  
  
  
 <div align="center"><img src=""/></div>
