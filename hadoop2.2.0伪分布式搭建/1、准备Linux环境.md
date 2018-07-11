## 准备Linux环境

* 1、准备Linux环境

  * 步骤一、点击VMware快捷方式，右键打开文件所在位置 -> 双击vmnetcfg.exe -> VMnet1 host-only ->修改subnet ip 设置网段：192.168.1.0 子网掩码：255.255.255.0 -> apply -> ok

  * 步骤二、回到windows --> 打开网络和共享中心 -> 更改适配器设置 -> 右键VMnet1 -> 属性 -> 双击IPv4 -> 设置windows的IP：192.168.1.110 子网掩码：255.255.255.0 -> 点击确定
  
  * 步骤三、在虚拟软件上 --My Computer -> 选中虚拟机 -> 右键 -> settings -> network adapter -> host only -> ok	
* 2、修改主机名

		vim /etc/sysconfig/network
		
		NETWORKING=yes 
		HOSTNAME=hadoop01    ###

* 3、修改IP——两种方式：
   * 第一种：通过Linux图形界面进行修改（强烈推荐）
      * 进入Linux图形界面 -> 右键点击右上方的两个小电脑 -> 点击Edit connections -> 选中当前网络System eth0 -> 点击edit按钮 -> 选择IPv4 -> method选择为manual -> 点击add按钮 -> 添加IP：192.168.1.119 子网掩码：255.255.255.0 网关：192.168.1.1 -> apply
   * 第二种：修改配置文件方式（屌丝程序猿专用）
    
              vim /etc/sysconfig/network-scripts/ifcfg-eth0

              DEVICE="eth0"
              BOOTPROTO="static"           ###
              HWADDR="00:0C:29:3C:BF:E7"
              IPV6INIT="yes"
              NM_CONTROLLED="yes"
              ONBOOT="yes"
              TYPE="Ethernet"
              UUID="ce22eeca-ecde-4536-8cc2-ef0dc36d4a8c"
              IPADDR="192.168.1.119"       ###
              NETMASK="255.255.255.0"      ###
              GATEWAY="192.168.1.1"        ###
			
* 4、修改主机名和IP的映射关系

		vim /etc/hosts
			
		192.168.1.119	hadoop01
	
* 5、关闭防火墙(Linux里)

		#查看防火墙状态
		service iptables status
		#关闭防火墙
		service iptables stop
		#查看防火墙开机启动状态
		chkconfig iptables --list
		#关闭防火墙开机启动
		chkconfig iptables off
	
* 6、重启Linux

		reboot
