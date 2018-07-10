## cp命令格式
````
cp [OPTION]... [-T] SOURCE DEST

cp [OPTION]... SOURCE... DIRECTORY

cp [OPTION]... -t DIRECTORY SOURCE...
````
可以通过命令将文件或目录复制到其他的目标目录而不改变原来的文件或目录。可以通过 cp 命令对文件或目录进行复制。

cp命令的功能是将给出的⽂件或⽬录复制到另⼀个⽂件或⽬录中，相当于DOS下的copy命令。
## cp命令常用的参数：
````
-a, --archive                     等于-dR --preserve=all--backup[=CONTROL 为每个已存在的目标文件创建备份
-b                                类似--backup 但不接受参数--copy-contents 在递归处理是复制特殊文件内容
-d                                等于--no-dereference --preserve=links
-f, --force                       如果目标文件无法打开则将其移除并重试(当 -n 选项存在时则不需再选此项)
-i, --interactive                 覆盖前询问(使前面的 -n 选项失效)
-H                                跟随源文件中的命令行符号链接
-l, --link                        链接文件而不复制
-L, --dereference                 总是跟随符号链接
-n, --no-clobber                  不要覆盖已存在的文件(使前面的 -i 选项失效)
-P, --no-dereference              不跟随源文件中的符号链接
-p                                等于--preserve=模式,所有权,时间戳--preserve[=属性列表 保持指定的属性
                                  (默认：模式,所有权,时间戳)，如果可能保持附加属性：环境、链接、xattr 等
-R, -r, --recursive               复制目录及目录内的所有项目
-t --target-directory=DIRECTORY   将所有源文件拷贝到目标目录
-T, --no-target-directory         目标为文件而不是文件夹
````
## Linux cp命令 应用实例
* 将file1复制到另一个位置
````
  #cp file1 ../destDir/
  #ls ../destDir/
  file1
````
* 复制整个文件夹到另一个文件夹
````
  ls
  destDir  testDir
  #cp -r testDir destDir
  #ls destDir
  file1  testDir
````
* 强制覆盖目标文件而不询问
````
  #cp -r testDir destDir
  cp: overwrite `destDir/testDir/file2'? y
  cp: overwrite `destDir/testDir/file1'? y
  cp: overwrite `destDir/testDir/file3'? y
  #cp -nrf testDir destDir
  #
````
  使用-n选项会忽略掉覆盖询问。cp 是 cp -i的别名，默认是提示是否覆盖的，所以需要加-n选项。
* 为file1复制一个链接
````

````
