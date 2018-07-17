package com.mr.sort.action;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class InfoBean implements WritableComparable<InfoBean>{

	private String accout;
	private double incom;
	private double zhichu;
	private double jieyu;
	
	@Override
	public void write(DataOutput out) throws IOException {
		// TODO Auto-generated method stub
		out.writeUTF(accout);
		out.writeDouble(incom);
		out.writeDouble(zhichu);
		out.writeDouble(jieyu);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		// TODO Auto-generated method stub
		this.accout=in.readUTF();
		this.incom=in.readDouble();
		this.zhichu=in.readDouble();
		this.jieyu=in.readDouble();
	}

	@Override
	public int compareTo(InfoBean infoBean) {
		if(this.incom == infoBean.getIncom()){
			return this.zhichu > infoBean.getZhichu() ? 1 : -1;
		}else{
			return this.incom > infoBean.getIncom() ? -1 : 1;
		}
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public double getIncom() {
		return incom;
	}

	public void setIncom(double incom) {
		this.incom = incom;
	}

	public double getZhichu() {
		return zhichu;
	}

	public void setZhichu(double zhichu) {
		this.zhichu = zhichu;
	}

	public double getJieyu() {
		return jieyu;
	}

	public void setJieyu(double jieyu) {
		this.jieyu = jieyu;
	}

	@Override
	public String toString() {
		return incom +"\t" + zhichu +"\t" + jieyu;
	}
	
	public void set(String accout,double incom,double zhichu){
		this.accout=accout;
		this.incom=incom;
		this.zhichu=zhichu;
		this.jieyu=incom - zhichu;
	}
}
