## Linux数组变量
在 Linux 中可以声明变量数组，数组可以在一个变量中保存多个值，对于数组变量来说，**可以对数组变量单值引用也可以对整个数组变量进行引用**。

* 1、声明一个数组变量要用圆括号将多个值包括起来，并且值和值之间要使用空格分隔，声明一个数组变量的语法格式如下。

      # array=(first second third fourth)
      
   >For example
      
      [root@localhost pretice]# array=(first second third fourth)

    * 使用圆括号内的值要用空格分隔，上面声明的数组变量有四个值。
    * 需要注意的是对数组变量内容的提取方式和普通变量是不同的，如下所示。
    
          # echo $array
          first
          
      >For example    
          
          [root@localhost pretice]# echo $array
          first
          
     * 可以看到，对数组采用通常的方式，显示的内容只有数值中第一个值。所以对于数组变量来说，要引用数组中的某个单独的值要**采用这个值在数组中的索引，并使用方括号将索引值包括起来**，如下所示。
     
           [root@localhost pretice]# echo ${array[*]}
           first second third fourth
           [root@localhost pretice]# echo ${array[2]}
           third
           [root@localhost pretice]# echo ${array[0]}
           first
           [root@localhost pretice]# echo ${array[4]}

           [root@localhost pretice]# echo ${array[0]} ${array[1]} ${array[3]} ${array[4]}
           first second fourth


     如果使用`*`号，将显示数组中所有的元素。需要注意的是数组的索引从 0 开始，索引为 0 的值是数组中的第一值，数组中如果有 n 个值，数组的索引从 0 到 n-1，如果数组的索引超出范围，将显示空，如${array[4]}返回的是空。

* 2、可以将数组变量赋值给另一个数组变量，但是要注意的是不能简单的方式，如下所示。

      [root@localhost pretice]# array_bak=$array
      [root@localhost pretice]# echo ${array_bak[*]}
      first

    可以看到，采用普遍变量赋值的方式只能将数组中的第一个元素赋值给变量。
    
    * 采用下面的方式将一个数组变量赋值给另一个变量。 `${array[*]};`

      注意：下面的例子是将数组中的所有元素组成一个字符串变量赋值给一个普通变量，如下所示。

            [root@localhost pretice]# array_bak=${array[*]}
            [root@localhost pretice]# echo ${array_bak[2]}
            
            [root@localhost pretice]# echo $array_bak
            first second third fourth
            
         变量 array_bak 是一个普通的变量，这个变量的值是一个字符串，同数组下标的方式访问第三个元素返回为空。
         
* 3、将数组变量中的某个元素赋值给一个变量，需要用到数组元素的索引号，如下所示。

      [root@localhost pretice]# var1=${array[2]}
      [root@localhost pretice]# echo $var1
      third

* 4、可以对数组变量中的某个值进行修改，需要采用数组索引的方式，如下所示。

      [root@localhost pretice]# array[2]=Hello
      [root@localhost pretice]# echo ${array[*]}
      first second Hello fourth
      [root@localhost pretice]# array[4]=fifth
      [root@localhost pretice]# echo ${array[*]}
      first second Hello fourth fifth

     >对数组变量采用索引的方式进行赋值，会将之前的值覆盖掉，要注意。也可以采用这种方式为数组变量增加元素，需要明确的是数组中已存在元素的个数和对应的索引号，如果索引错误，将会出现覆盖之前的值。
     
* 5、在 Linux 系统中，数组变量的使用较为复杂，如果操作不当，会出现取值错误，如下例子。

      [root@localhost pretice]# echo ${array[*]}
      first second Hello fourth fifth
      [root@localhost pretice]# array[6]=Bash
      [root@localhost pretice]# echo ${array[*]}
      first second Hello fourth fifth Bash
      [root@localhost pretice]# echo ${array[5]}

     数组变量 array 已存在五个元素，下标从 0~4 对应数组中的五个元素，注意：上面直接为数组变量的第七个元素进行赋值，数组中的第六个元素为空。显示数组的全部元素时很难判断某个元素为空，当显示第五个元素的值时，控制台显示空。

* 6、删除数组变量中的某个元素使用命令 `unset`，同样采用索引的方式，但是要注意，删除元素后，数组中保存元素的空间还存在，只是值为空。如下。

      [root@localhost pretice]# echo ${array[*]}
      first second third fourth fifth
      [root@localhost pretice]# unset array[2]
      [root@localhost pretice]# echo ${array[*]}
      first second fourth fifth
      [root@localhost pretice]# echo ${array[2]}

     数组中有五个元素，删除了第三个元素后，显示数组的内容很难判断数组中哪个下标的元素为空，采用下标的方式访问被删除的元素时，显示为空。

* 7、使用 unset 命令将数组变量中的值清空，如下。

      [root@localhost pretice]# unset array
      [root@localhost pretice]# echo ${array[*]}
      
     可变数组变量在实际的脚本中很少使用。有几个环境变量采用的是数组的方式声明。
