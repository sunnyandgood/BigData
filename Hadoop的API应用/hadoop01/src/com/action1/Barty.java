package com.action1;

public interface Barty {

	//在接口中定义versionID，在客户端调用时需要使用
	public static final long versionID=1;
	
	public String sayHI(String name);
}
