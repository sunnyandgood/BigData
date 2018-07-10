## mv命令格式
````
  mv [OPTION]... [-T] SOURCE DEST
  mv [OPTION]... SOURCE... DIRECTORY
  mv [OPTION]... -t DIRECTORY SOURCE...
````
## mv 命令常用的参数：
````
-b ：    若需覆盖文件，则覆盖前先行备份。 
-f ：    force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖；
-i ：    若目标文件 (destination) 已经存在时，就会询问是否覆盖！
-u ：    若目标文件已经存在，且 source 比较新，才会更新(update)
-t ：    --target-directory=DIRECTORY move all SOURCE arguments into DIRECTORY，即指定mv的目标目录，
         该选项适用于移动多个源文件到一个目录的情况，此时目标目录在前，源文件在后。
````
## mv命令 应用实例
* 将文件file1改名为file_mv
````
  #mv file1 file_mv
  #ls
  file1_link  file2  file3  file_mv  file_remote
````
* 将file2移动到其他目录
````
  #ls
  file1_link  file2  file3  file_mv  file_remote
  #mv file2 ../destDir
  #ls
  file1_link  file3  file_mv  file_remote
  #ls ../destDir/
  file1  file2  testDir
  #
````
* 强制覆盖其他文件而不询问
````
  #ls
  file1  file2  testDir
  #mv -f file1 file2     //说明：加 –f 参数是强制的意思，不会弹出消息询问。
  #ls
  file2  testDir
````
* 覆盖其他文件之前先备份
````
  #ls
  file1  file2  testDir
  #mv -b file1 file2
  mv: overwrite `file2'? y
  #ls
  file2  file2~  testDir
````
  可以看到，覆盖掉file2之前，生成了一个file2的备份文件。

* 多文件移动
````
  #ls
  file1  file2  file2~  testDir
  #mv file1 file2 file2~ testDir/
  mv: overwrite `testDir/file1'? y
  mv: overwrite `testDir/file2'? y
  #ls
  testDir
  #ls testDir/
  file1  file2  file2~  file3
````
  多文件移动只要输入相应的文件名，最后输入目标文件夹即可。

  注意，只能将多文件移入文件夹中，目标不能为文件。
