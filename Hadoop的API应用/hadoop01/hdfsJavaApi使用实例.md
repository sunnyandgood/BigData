# [hdfsJavaApi使用实例](https://github.com/sunnyandgood/BigData/tree/master/Hadoop%E7%9A%84API%E5%BA%94%E7%94%A8/hadoop01/src/com/hdfs/javaApi)

### 实例

    package com.hdfs.javaApi;

    import java.io.File;
    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.OutputStream;
    import java.net.URI;
    import java.net.URISyntaxException;

    import org.apache.hadoop.conf.Configuration;
    import org.apache.hadoop.fs.FileSystem;
    import org.apache.hadoop.fs.Path;
    import org.apache.hadoop.io.IOUtils;
    import org.junit.Test;

    public class HDFSJavaApi {

      @Test
      public void testDownLoad() throws IOException, URISyntaxException {
        FileSystem fSystem = FileSystem.get(new URI("hdfs://192.168.1.101:9000"),new Configuration());
        InputStream input = fSystem.open(new Path("/dianying.mp4"));
        OutputStream output = new FileOutputStream(new File("d:/dianying.mp4"));
        IOUtils.copyBytes(input, output, 4096, true);
      }

      @Test
      public void testUpLoad() throws IOException, InterruptedException, URISyntaxException {
        FileSystem fSystem = FileSystem.get(new URI("hdfs://192.168.1.101:9000"),new Configuration(),"root");
        OutputStream output = fSystem.create(new Path("/test"));
        InputStream input = new FileInputStream(new File("d:/testFile.jpg"));
        IOUtils.copyBytes(input, output, 4096,true);
      }

      @Test
      public void testCopyFromLocalFile() throws IllegalArgumentException, IOException, InterruptedException, 
      URISyntaxException {
        FileSystem fSystem = FileSystem.get(new URI("hdfs://192.168.1.101:9000"),new Configuration(),"root");
        fSystem.copyFromLocalFile(new Path("d:/testFile.jpg"), new Path("/test1/file"));
      }


      @Test
      public void testDelete() throws IllegalArgumentException, IOException, InterruptedException, 
      URISyntaxException {
        FileSystem fSystem = FileSystem.get(new URI("hdfs://192.168.1.101:9000"),new Configuration(),"root");
        boolean flag = fSystem.delete(new Path("/test1"),true);
        /**
         * 参数1：表示要删除的文件或目录，为path对象
         * 参数2：表示是否递归删除
         * 		true：可以删除文件、可以删除空目录、可以删除不为空的目录
         * 		false：可以删除文件、可以删除空目录
         */
        System.out.println(flag);
      }

      @Test
      public void testMkdir() throws IOException, InterruptedException, URISyntaxException {
        FileSystem fSystem = FileSystem.get(new URI("hdfs://192.168.1.101:9000"),new Configuration(),"root");
        boolean flag = fSystem.mkdirs(new Path("/test2"));
        System.out.println(flag);
      }

      @Test
      public void test() throws IOException, InterruptedException, URISyntaxException {
        FileSystem fSystem = FileSystem.get(new URI("hdfs://192.168.1.101:9000"),new Configuration(),"root");		
      }	
    }
