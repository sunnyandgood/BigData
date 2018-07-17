# 流量统计之MapReduce与Partitioner

## [Writable接口与序列化机制](https://github.com/sunnyandgood/BigData/blob/master/MapReduce/Writable%E6%8E%A5%E5%8F%A3%E4%B8%8E%E5%BA%8F%E5%88%97%E5%8C%96%E6%9C%BA%E5%88%B6.md)

### 一、DataBean类

      package com.mr.data.count.action;

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
      
### 二、DataCount类

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

### 三、数据

      1363157985066	13726230503	2481	24681	200
      1363157995052	13826544101	264	0	200
      1363157991076	13926435656	132	1512	200
      1363154400022	13926251106	240	0	200
      1363157993044	18211575961	1527	2106	200
      1363157995074	84138413	4116	1432	200
      1363157993055	13560439658	1116	954	200
      1363157995033	15920133257	3156	2936	200
      1363157983019	13719199419	240	0	200
      1363157984041	13660577991	6960	690	200
      1363157973098	15013685858	3659	3538	200
      1363157986029	15989002119	1938	180	200
      1363157992093	13560439658	918	4938	200
      1363157986041	13480253104	180	180	200
      1363157984040	13602846565	1938	2910	200
