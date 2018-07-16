package com.action1;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

public class RPCClient {

	public static void main(String[] args) throws Exception {
		
		Barty proxy = RPC
				/**获取代理对象，
				 * 参数1为jdk默认自动代理需要的接口，即要调用方法的类实现的接口
				 * 参数2为versionID，在接口中定义的versionID，貌似只要有ID就好，值无所谓，有待确认
				 * 参数3为服务器设置，参数1为地址（字符串），参数2为服务器端口号，启动服务那边设置的
				 * 参数4位configuration对象
				 */
				.getProxy(Barty.class, 1,
				new InetSocketAddress("192.168.2.1", 55555), 
				new Configuration());
		String sayHi = proxy.sayHI("tomcat");
		RPC.stopProxy(proxy);
		System.out.println(sayHi);
	}
}
