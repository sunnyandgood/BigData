ps命令 是Process Status的缩写，用来列出系统中当前运行的那些进程。

不过，ps命令 为我们提供的是进程的快照，也就是说，使用ps命令 查看的进程情况只是在你执行 ps命令 的时刻进程的一个状态，并不是动态的。

如果想要动态的查看进程情况，还得使用另一个命令--top命令 ，
## ps命令格式

      ps [options]

## ps命令常用的参数

        -a 显示同一终端下的所有程序
        -A 显示所有进程
        c  显示进程的真实名称
        -N 反向选择
        -e 等于“-A”
        e  显示环境变量
        f  显示程序间的关系
        -H 显示树状结构
        r  显示当前终端的进程
        T  显示当前终端的所有程序
        u  指定用户的所有进程
        -w 显示加宽可以显示较多的资讯
        -au 显示较详细的资讯
        -aux 显示所有包含其他使用者的行程 
        -C<命令> 列出指定命令的状况
        --lines<行数> 每页显示的行数
        --width<字符数> 每页显示的字符数
        --help 显示帮助信息
        --version 显示版本显示
        -------------------------------------------------------------------------
        ----aux详细用法    
        -------------------------------------------------------------------------
        -au 显示较详细的资讯
        -aux 显示所有包含其他使用者的行程
        au(x) 输出格式 :
        USER PID %CPU %MEM VSZ RSS TTY STAT START TIME COMMAND
        USER: 行程拥有者
        PID: pid
        %CPU: 占用的 CPU 使用率
        %MEM: 占用的内存使用率
        VSZ: 占用的虚拟内存大小
        RSS: 占用的内存大小
        TTY: 终端的次要装置号码 (minor device number of tty)
        STAT: 该行程的状态:
        D: 不可中断的静止
        R: 正在执行中
        S: 静止状态
        T: 暂停执行
        Z: 不存在但暂时无法消除
        W: 没有足够的记忆体分页可分配
        <: 高优先序的行程
        N: 低优先序的行程
        L: 有记忆体分页分配并锁在记忆体内 (实时系统或捱A I/O)
        START: 行程开始时间
        TIME: 执行的时间
        COMMAND:所执行的指令

## 在 -aux 所列出的讯息中，其进程状态（STAT）标识的状态与Linux 进程 状态的对应情况如下

      D状态代表 不可中断的静止状态， 对应Linux系统中的不可中断状态(收到信号不唤醒和不可运行, 进程必须等待直到有中断发生)

      R状态（runnable）代表 运行状态，对应Linux系统中 的运行状态(正在运行或在运行队列中等待) 

      S状态（sleeping ）代表 中断状态，对应Linux系统中 的中断状态(休眠中, 受阻, 在等待某个条件的形成或接受到信号) 

      T状态（traced ）代表 停止状态，对应Linux系统中 的停止状态(进程收到SIGSTOP, SIGSTP, SIGTIN, SIGTOU信号后停止运行运行) 

      Z状态（zombie）代表 僵死状态，对应Linux系统中 的僵死状态(进程已终止, 但进程描述符存在, 
                                                                          直到父进程调用wait4()系统调用后释放) 

      这五个字母与Linux 进程状态一一对应，从其输出即可看出相应进程的运行情况。
      
## ps命令 应用实例
* 1、显示所有进程信息
     * `ps -A`例
      
            [root@localhost ~]# ps -A
              PID TTY          TIME CMD
                1 ?        00:00:01 init
                2 ?        00:00:00 kthreadd
                3 ?        00:00:00 migration/0
                4 ?        00:00:00 ksoftirqd/0
                5 ?        00:00:00 migration/0
                6 ?        00:00:00 watchdog/0
                7 ?        00:00:00 events/0
                8 ?        00:00:00 cgroup
                9 ?        00:00:00 khelper
               10 ?        00:00:00 netns
            ······························

       * ps命令显示的字段含义：


          <table>
             <tr>
                <td>字段</td>
                <td>说明</td>
             </tr>
             <tr>
                <td>PID</td>
                <td>进程的ID号，Process ID</td>
             </tr>
             <tr>
                <td>TTY</td>
                <td>运行在哪个终端</td>
             </tr>
             <tr>
                <td>TIME</td>
                <td>进程已经使用的CPU时间</td>
             </tr>
             <tr>
                <td>CMD</td>
                <td>启动进程的命令</td>
             </tr>
          </table>

   * `ps -ef`例
   
            [root@localhost ~]# ps -ef
            UID        PID  PPID  C STIME TTY          TIME CMD
            root         1     0  0 09:03 ?        00:00:01 /sbin/init
            root         2     0  0 09:03 ?        00:00:00 [kthreadd]
            root         3     2  0 09:03 ?        00:00:00 [migration/0]
            root         4     2  0 09:03 ?        00:00:00 [ksoftirqd/0]
            root         5     2  0 09:03 ?        00:00:00 [migration/0]
            root         6     2  0 09:03 ?        00:00:00 [watchdog/0]
            root         7     2  0 09:03 ?        00:00:00 [events/0]
            root         8     2  0 09:03 ?        00:00:00 [cgroup]
            root         9     2  0 09:03 ?        00:00:00 [khelper]
            root        10     2  0 09:03 ?        00:00:00 [netns]
            ...........
         
      * ps命令显示的字段含义：
      
      <table>
         <tr>
            <td>字段</td>
            <td>说明</td>
         </tr>
         <tr>
            <td>UID</td>
            <td>启动进程的用户名称</td>
         </tr>
         <tr>
            <td>PID</td>
            <td>进程的ID号，Process ID</td>
         </tr>
         <tr>
            <td>PPID</td>
            <td>父进程的进程号，如果此进程是由另外一个进程启动</td>
         </tr>
         <tr>
            <td>C</td>
            <td>整个进程生命周期期间的CPU利用率</td>
         </tr>
         <tr>
            <td>STIME</td>
            <td>进程启动时的系统时间</td>
         </tr>
         <tr>
            <td>TTY</td>
            <td>运行在哪个终端</td>
         </tr>
         <tr>
            <td>TIME</td>
            <td>进程已经使用的CPU时间</td>
         </tr>
         <tr>
            <td>CMD</td>
            <td>启动进程的命令</td>
         </tr>
      </table>
   
   * `ps -lef`例
   
            [root@localhost ~]# ps -lef
            F S UID        PID  PPID  C PRI  NI ADDR SZ WCHAN  STIME TTY          TIME CMD
            4 S root         1     0  0  80   0 -   725 -      09:03 ?        00:00:01 /sbin/init
            1 S root         2     0  0  80   0 -     0 -      09:03 ?        00:00:00 [kthreadd]
            1 S root         3     2  0 -40   - -     0 -      09:03 ?        00:00:00 [migration/0]
            1 S root         4     2  0  80   0 -     0 -      09:03 ?        00:00:00 [ksoftirqd/0]
            1 S root         5     2  0 -40   - -     0 -      09:03 ?        00:00:00 [migration/0]
            5 S root         6     2  0 -40   - -     0 -      09:03 ?        00:00:00 [watchdog/0]
            1 S root         7     2  0  80   0 -     0 -      09:03 ?        00:00:00 [events/0]
            1 S root         8     2  0  80   0 -     0 -      09:03 ?        00:00:00 [cgroup]
            1 S root         9     2  0  80   0 -     0 -      09:03 ?        00:00:00 [khelper]
            1 S root        10     2  0  80   0 -     0 -      09:03 ?        00:00:00 [netns]
            ...................

     *  参数-l使输出增加了多个字段：ps命令显示的字段含义：
     
     <table>
         <tr>
            <td>字段</td>
            <td>说明</td>
         </tr>
         <tr>
            <td>F</td>
            <td>内核分配给进程的系统记号</td>
         </tr>
         <tr>
            <th rowspan="5">S</th>
            <td>进程的状态：</td>
         </tr>
         <tr>
            <td>S：进程处于休眠状态</td>
         </tr>
         <tr>
            <td>R：进程可以运行，等待状态</td>
         </tr>
         <tr>
            <td>Z：进程处于僵化状态，进程已经结束，但是启动父进程不存在</td>
         </tr>
         <tr>
            <td>T：代表停止</td>
         </tr>
         <tr>
            <td>PRI</td>
            <td>进程的优先级：数字越大优先级越低</td>
         </tr>
         <tr>
            <td>NI</td>
            <td>谦让度高优先级低</td>
         </tr>
         <tr>
            <td>ADDR</td>
            <td>进程的内存地址</td>
         </tr>
         <tr>
            <td>SZ</td>
            <td>进程启动需要的交换空间大小</td>
         </tr>
         <tr>
            <td>WCHAN</td>
            <td>进程休眠的内核函数的地址</td>
         </tr>
      </table>

* 2、层级显示所有进程信息：

      .......
      [root@localhost ~]# ps -efH
      UID        PID  PPID  C STIME TTY          TIME CMD
      root         1     0  0 09:03 ?        00:00:01 /sbin/init
      root       361     1  0 09:03 ?        00:00:00   /sbin/udevd -d
      root      2063   361  0 09:04 ?        00:00:00     /sbin/udevd -d
      root      2064   361  0 09:04 ?        00:00:00     /sbin/udevd -d
      root      1300     1  0 09:03 ?        00:00:00   /usr/sbin/vmware-vmblock-fuse
                                                -o subtype=vmware-vmblock,default_permis
      root      1321     1  0 09:03 ?        00:00:13   /usr/sbin/vmtoolsd
      .......

* 3、显示指定用户进程
````

````
* 4、显示所有进程信息，包括其执行的命令
````

````
* 5、列出进程的详细信息（aux参数）
````

````
aux参数非常强大，可以自定义其输出格式，如果其后不加任何参数，则系统会给出警告，并按照默认格式输出。
* 6、配合 grep 命令 筛选需要的结果
````

````
