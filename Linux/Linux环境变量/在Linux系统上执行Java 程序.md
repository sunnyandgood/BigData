## 在 Linux 系统上执行 Java 程序

* 第一种情况是不带包名的 Java 程序。

      java LinuxTest01

  >For example
  
        public class LinuxTest01
        {
              public static void main(String[] args)
              {
                      System.out.println("Hello Linux!");
              }
        }

        [root@localhost pretice]# touch LinuxTest01.java
        [root@localhost pretice]# vim Linuxtest01.java
        [root@localhost pretice]# javac LinuxTest01.java 
        [root@localhost pretice]# java LinuxTest01
        Hello Linux!


* 第二种情况是带包名的 Java 程序。

      java com.edu.java.LinuxTest02

  >For example
  
        package com.edu.java;

        public class LinuxTest02
        {
                public static void main(String[] args)
                {
                        System.out.println("Hello Linux!");
                }
        }

        [root@localhost pretice]# touch LinuxTest02.java
        [root@localhost pretice]# vim Linuxtest02.java
        [root@localhost pretice]# javac -d . LinuxTest02.java
        [root@localhost pretice]# java com.edu.java.LinuxTest02
        Hello Linux!

  
* 第三种情况是打成 jar 包的 Java 程序。

      java -jar LinuxTest03.jar

     对于第三种情况，需要在 jar 文件中做一些配置，要对 META-INF 目录下 MANIFEST.MF 文件进行配置，指明有 main()方法的 Java 程序名称，如下：

          Manifest-Version: 1.0
          Main-class: com.edu.java2.LinuxTest03
