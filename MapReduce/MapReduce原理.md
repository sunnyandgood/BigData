## MapReduce原理


  <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/%E5%AF%BC%E6%B5%B7%E9%87%8F%E6%95%B0%E6%8D%AE%E8%AE%A1%E7%AE%97.png"/></div>

### MapReduce概述
* MapReduce是一种分布式计算模型，由Google提出，主要用于搜索领域，解决海量数据的计算问题.
* MR由两个阶段组成：Map和Reduce，用户只需要实现map()和reduce()两个函数，即可实现分布式计算，非常简单。
* 这两个函数的形参是key、value对，表示函数的输入信息。

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/%E8%AE%BE%E8%AE%A1%E4%B8%80%E4%B8%AAMapReduce%E6%A1%86%E6%9E%B6.png"/></div>

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/MapReduce%E6%A1%86%E6%9E%B6.png"/></div>

* 执行步骤：

   * 1、map任务处理
   
      * 1.1、读取输入文件内容，解析成key、value对。对输入文件的每一行，解析成key、value对。每一个键值对调用一次map函数。
      
      * 1.2、写自己的逻辑，对输入的key、value处理，转换成新的key、value输出。
      
      * 1.3、对输出的key、value进行分区。
      
      * 1.4、对不同分区的数据，按照key进行排序、分组。相同key的value放到一个集合中。
      
      * 1.5、(可选)分组后的数据进行归约。
      
  * 2、reduce任务处理
  
      * 2.1、对多个map任务的输出，按照不同的分区，通过网络copy到不同的reduce节点。
      
      * 2.2、对多个map任务的输出进行合并、排序。写reduce函数自己的逻辑，对输入的key、value处理，转换成新的key、value输出。
      
      * 2.3、把reduce的输出保存到文件中。
      
* 例子：实现WordCountApp

### map、reduce键值对格式

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/Map%E4%B8%8EReduce%E5%87%BD%E6%95%B0.png"/></div>

  <table>
     <tr>
        <td>函数</td>
        <td>输入键值对</td>
        <td>输出键值对</td>
     </tr>
     <tr>
        <td>map()</td>
        <td>k1,v1</td>
        <td>k2,v2</td>
     </tr>
     <tr>
        <td>reduce()</td>
        <td>k2,{v2}</td>
        <td>k3,v3</td>
     </tr>
  </table>
