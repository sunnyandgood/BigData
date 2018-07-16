package com.mr.data.count.action;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class DataBean implements Writable{

	//电话号码
	private String phone;
	//上行流量
	private Long upPayLoad;
	//下行流量
	private Long downPayLoad;
	//总流量
	private Long totalPayLoad;
	
	public DataBean(){}
	
	public DataBean(String phone,Long upPayLoad, Long downPayLoad) {
		super();
		this.phone=phone;
		this.upPayLoad = upPayLoad;
		this.downPayLoad = downPayLoad;
		this.totalPayLoad=upPayLoad+downPayLoad;
	}

	/**
	 * 序列化
	 * 注意：序列化和反序列化的顺序和类型必须一致
	 */
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(phone);
		out.writeLong(upPayLoad);
		out.writeLong(downPayLoad);
		out.writeLong(totalPayLoad);
	}

	/**
	 * 反序列化
	 */
	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.phone=in.readUTF();
		this.upPayLoad=in.readLong();
		this.downPayLoad=in.readLong();
		this.totalPayLoad=in.readLong();
	}

	@Override
	public String toString() {
		return upPayLoad +"\t"+ downPayLoad +"\t"+  totalPayLoad;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getUpPayLoad() {
		return upPayLoad;
	}

	public void setUpPayLoad(Long upPayLoad) {
		this.upPayLoad = upPayLoad;
	}

	public Long getDownPayLoad() {
		return downPayLoad;
	}

	public void setDownPayLoad(Long downPayLoad) {
		this.downPayLoad = downPayLoad;
	}

	public Long getTotalPayLoad() {
		return totalPayLoad;
	}

	public void setTotalPayLoad(Long totalPayLoad) {
		this.totalPayLoad = totalPayLoad;
	}

}
