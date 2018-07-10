## Linux的echo 命令

* echo 命令是将字符串输出到标准输出，通常使用 echo 命令输出提示信息或检测结果是否正确。
  * 命令：
  
        echo Test Bash script!

  * 命令执行后会在控制台输出：`Test Bash script!。`没问题！因为 echo 命令默认后面跟着的是字符串。
  * 但是有些情况下，输出会出现不正确的结果，如下面的情况：
     * 命令：
     
           echo Test 'Bash script'!

    * 控制台输出：
    
           Test Bash script!

    * 这不是我们想要的结果，单引号被过滤掉了，所以在使用 echo 命令时，建议将输出的字符串加上双引号，如下：
      * 命令：
      
              echo "Test 'Bash script'"!

      * 控制台输出：
      
              Test 'Bash script'!

        这次的输出是我们想要的结果。
* echo 命令输出后会自动添加一个换行符，下一次输出将在新行输出，如下：
   * 命令：
   
         echo "Test 'Bash script'"!;echo "Helle bash" 

   * 控制台输出：
   
         Test 'Bash script'!
         Helle bash
         
* 有时候我们需要将输出同一行显示，这就需要使用`-n 参数`，如下：
   * 命令：
   
         echo -n "Test 'Bash script'"! ; echo "Helle bash"
   * 控制台输出：
   
         Test 'Bash script'!Helle bash
