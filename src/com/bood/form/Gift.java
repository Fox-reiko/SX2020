package com.bood.form;

public class Gift {
	
	private int gid;//礼品的编号
	private String name;//礼品名称
	private	int share;//数量
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	@Override
	public String toString() {
		return "Gift [gid=" + gid + ", name=" + name + ", share=" + share + "]";
	}
	
	
	
}
