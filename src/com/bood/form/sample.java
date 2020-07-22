package com.bood.form;

public class sample {
	
	private	int	sid;//样本的编号
	private	int	uid;//样本人的编号
	private String sdate;//样本日期
	
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	@Override
	public String toString() {
		return "sample [sid=" + sid + ", uid=" + uid + ", sdate=" + sdate + "]";
	}
	
	
	
}
