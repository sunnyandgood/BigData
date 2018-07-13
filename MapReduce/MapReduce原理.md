## MapReduce原理


  <div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/%E5%AF%BC%E6%B5%B7%E9%87%8F%E6%95%B0%E6%8D%AE%E8%AE%A1%E7%AE%97.png"/></div>

### MapReduce概述
* MapReduce是一种分布式计算模型，由Google提出，主要用于搜索领域，解决海量数据的计算问题.
* MR由两个阶段组成：Map和Reduce，用户只需要实现map()和reduce()两个函数，即可实现分布式计算，非常简单。
* 这两个函数的形参是key、value对，表示函数的输入信息。

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/%E8%AE%BE%E8%AE%A1%E4%B8%80%E4%B8%AAMapReduce%E6%A1%86%E6%9E%B6.png"/></div>

<div align="center"><img src="https://github.com/sunnyandgood/BigBata/blob/master/MapReduce/img/MapReduce%E6%A1%86%E6%9E%B6.png"/></div>
