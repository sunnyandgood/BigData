top命令 用于实时动态显示 Linux进程 的动态信息。
## top命令格式
````
top -hv | -abcHimMsS -d delay -n iterations -p pid [, pid ...]
````
## top命令常用的参数
````
d : 改变显示的更新速度，或是在交谈式指令列( interactive command)按 s 

q : 没有任何延迟的显示速度，如果使用者是有 superuser 的权限，则 top 将会以最高的优先序执行 

c : 切换显示模式，共有两种模式，一是只显示执行档的名称，另一种是显示完整的路径与名称

S : 累积模式，会将己完成或消失的子行程 ( dead child process ) 的 CPU time 累积起来 

s : 安全模式，将交谈式指令取消, 避免潜在的危机 

i : 不显示任何闲置 (idle) 或无用 (zombie) 的行程 

n : 更新的次数，完成后将会退出 top 

b : 批次档模式，搭配 "n" 参数一起使用，可以用来将 top 的结果输出到档案内
````
## Linux top命令 的一些可使用的交互命令
````
h 显示帮助画面，给出一些简短的命令总结说明

k 终止一个进程。

i 忽略闲置和僵死进程。这是一个开关式命令。

q 退出程序

r 重新安排一个进程的优先级别

S 切换到累计模式

s 改变两次刷新之间的延迟时间（单位为s），如果有小数，就换算成m s。输入0值则系统将不断刷新，默认值是5 s

f或者F 从当前显示中添加或者删除项目

o或者O 改变显示项目的顺序

l 切换显示平均负载和启动时间信息

m 切换显示内存信息

t 切换显示进程和CPU状态信息

c 切换显示命令名称和完整命令行

M 根据驻留内存大小进行排序

P 根据CPU使用百分比大小进行排序

T 根据时间/累计时间进行排序

W 将当前设置写入~/.toprc文件中
````
## top命令 应用实例
* 1、显示进程运行状态信息
````
[root@localhost ~]# top
top - 15:38:57 up 39 days, 17:30,  1 user,  load average: 0.10, 0.07, 0.01
Tasks:  95 total,   1 running,  94 sleeping,   0 stopped,   0 zombie
Cpu(s): 11.4%us,  0.7%sy,  0.0%ni, 88.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:   1018600k total,   831520k used,   187080k free,     9536k buffers
Swap:        0k total,        0k used,        0k free,    54292k cached

  PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND
  954 apache    20   0  369m  55m 4520 S 10.6  5.6   0:01.90 httpd
  956 apache    20   0  334m  18m 3292 S  0.3  1.8   0:00.29 httpd
````

显示信息说明：

top命令 所显示信息的前五行是当前系统情况整体的统计情况。

第一行，任务队列信息，同 uptime 命令的执行结果，具体参数说明情况如下：

15:38:57  — 当前系统时间

up 39 days, 17:30 — 系统已经运行了39天17小时30分钟。

1 users — 当前有1个用户登录系统

load average: 0.10, 0.07, 0.01 — load average后面的三个数分别是1分钟、5分钟、15分钟的负载情况。

load average数据是每隔5秒钟检查一次活跃的进程数，然后按特定算法计算出的数值。如果这个数除以逻辑CPU的数量，结果高于5的时候就表明系统在超负荷运转了。

第二行，Tasks — 任务（进程），具体信息说明如下：

系统现在共有95个进程，其中处于运行中的有1个，94个在休眠（sleep），stoped状态的有0个，zombie状态（僵尸）的有0个。

第三行，cpu状态信息，具体属性说明如下：

us — 用户空间占用CPU的百分比。

sy — 内核空间占用CPU的百分比。

ni — 改变过优先级的进程占用CPU的百分比

id — 空闲CPU百分比

wa — IO等待占用CPU的百分比

hi — 硬中断（Hardware IRQ）占用CPU的百分比

si — 软中断（Software Interrupts）占用CPU的百分比

第四行,内存状态，具体信息如下：

total — 物理内存总量

used — 使用中的内存总量

free — 空闲内存总量

buffers — 缓存的内存量 

第五行，swap交换分区信息，具体信息说明如下：

total — 交换区总量

used — 使用的交换区总量

free — 空闲交换区总量

cached — 缓冲的交换区总量

备注：

第四行中使用中的内存总量（used）指的是现在系统内核控制的内存数，空闲内存总量（free）是内核还未纳入其管控范围的数量。纳入内核管理的内存不见得都在使用中，还包括过去使用过的现在可以被重复利用的内存，内核并不把这些可被重新使用的内存交还到free中去，因此在linux上free内存会越来越少，但不用为此担心。

如果出于习惯去计算可用内存数，这里有个近似的计算公式：第四行的free + 第四行的buffers + 第五行的cached，按这个公式可计算出此台服务器的可用内存。

对于内存监控，在top里我们要时刻监控第五行swap交换分区的used，如果这个数值在不断的变化，说明内核在不断进行内存和swap的数据交换，这是真正的内存不够用了。

第六行，空行。

第七行以下：各进程（任务）的状态监控，项目列信息说明如下：

PID — 进程id

USER — 进程所有者

PR — 进程优先级

NI — nice值。负值表示高优先级，正值表示低优先级

VIRT — 进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES

RES — 进程使用的、未被换出的物理内存大小，单位kb。RES=CODE+DATA

SHR — 共享内存大小，单位kb

S — 进程状态。D=不可中断的睡眠状态 R=运行 S=睡眠 T=跟踪/停止 Z=僵尸进程

%CPU — 上次更新到现在的CPU时间占用百分比

%MEM — 进程使用的物理内存百分比

TIME+ — 进程使用的CPU时间总计，单位1/100秒

COMMAND — 进程名称（命令名/命令行）
* 2、显示进程完整命令
````
[root@localhost ~]# top -c
top - 14:10:37 up  2:07,  3 users,  load average: 0.00, 0.00, 0.00
Tasks: 135 total,   1 running, 134 sleeping,   0 stopped,   0 zombie
Cpu(s):  0.3%us,  0.3%sy,  0.0%ni, 99.3%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:   1030528k total,   394880k used,   635648k free,    24444k buffers
Swap:  2064376k total,        0k used,  2064376k free,   196644k cached

  PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND                   
    1 root      20   0  2900 1436 1216 S  0.0  0.1   0:01.40 /sbin/init                 
    2 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [kthreadd]                 
    3 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 [migration/0]              
    4 root      20   0     0    0    0 S  0.0  0.0   0:00.02 [ksoftirqd/0]              
    5 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 [migration/0]              
    6 root      RT   0     0    0    0 S  0.0  0.0   0:00.05 [watchdog/0]               
    7 root      20   0     0    0    0 S  0.0  0.0   0:00.32 [events/0]                 
    8 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [cgroup]                   
    9 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [khelper]                  
   10 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [netns]                    
   11 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [async/mgr]                
   12 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [pm]                       
   13 root      20   0     0    0    0 S  0.0  0.0   0:00.02 [sync_supers]              
   14 root      20   0     0    0    0 S  0.0  0.0   0:00.02 [bdi-default]              
   15 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [kintegrityd/0]            
   16 root      20   0     0    0    0 S  0.0  0.0   0:00.42 [kblockd/0]                
   17 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [kacpid]                   
   18 root      20   0     0    0    0 S  0.0  0.0   0:00.00 [kacpi_notify] 
````
* 3、以批处理模式显示程序信息
````
[root@localhost ~]# top -b
top - 14:11:29 up  2:07,  3 users,  load average: 0.00, 0.00, 0.00
Tasks: 135 total,   1 running, 134 sleeping,   0 stopped,   0 zombie
Cpu(s):  0.2%us,  0.2%sy,  0.0%ni, 99.5%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:   1030528k total,   394880k used,   635648k free,    24464k buffers
Swap:  2064376k total,        0k used,  2064376k free,   196648k cached

  PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND                    
    1 root      20   0  2900 1436 1216 S  0.0  0.1   0:01.40 init                       
    2 root      20   0     0    0    0 S  0.0  0.0   0:00.00 kthreadd                   
    3 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 migration/0                
    4 root      20   0     0    0    0 S  0.0  0.0   0:00.02 ksoftirqd/0                
    5 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 migration/0       
````
* 4、以累积模式显示程序信息
````
[root@localhost ~]# top -s
top - 14:12:54 up  2:09,  3 users,  load average: 0.00, 0.00, 0.00
Tasks: 135 total,   1 running, 134 sleeping,   0 stopped,   0 zombie
Cpu(s):  0.2%us,  0.2%sy,  0.0%ni, 99.5%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:   1030528k total,   394872k used,   635656k free,    24480k buffers
Swap:  2064376k total,        0k used,  2064376k free,   196648k cached

  PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND                   
    1 root      20   0  2900 1436 1216 S  0.0  0.1   0:01.40 init                       
    2 root      20   0     0    0    0 S  0.0  0.0   0:00.00 kthreadd                   
    3 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 migration/0                
    4 root      20   0     0    0    0 S  0.0  0.0   0:00.02 ksoftirqd/0   
````
* 5、设置信息更新次数

      命令：top -n 2        表示更新两次后终止更新显示

* 6、设置信息更新时间

      命令： top -d 3       表示更新周期为3秒

* 7、显示指定的进程信息
````
[root@localhost ~]# top -p 3
top - 14:14:55 up  2:11,  3 users,  load average: 0.00, 0.00, 0.00
Tasks:   1 total,   0 running,   1 sleeping,   0 stopped,   0 zombie
Cpu(s):  0.0%us,  0.0%sy,  0.0%ni,100.0%id,  0.0%wa,  0.0%hi,  0.0%si,  0.0%st
Mem:   1030528k total,   394252k used,   636276k free,    24520k buffers
Swap:  2064376k total,        0k used,  2064376k free,   196652k cached

  PID USER      PR  NI  VIRT  RES  SHR S %CPU %MEM    TIME+  COMMAND                   
    3 root      RT   0     0    0    0 S  0.0  0.0   0:00.00 migration/0  
````
* 8、其他说明：

      在top 命令执行过程中可以使用的一些交互命令。这些命令都是单字母的，如果在命令行中使用了s 选项， 
      其中一些命令可能会被屏蔽。
