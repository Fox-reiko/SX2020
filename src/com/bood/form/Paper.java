package com.bood.form;

public class Paper {
	
	private int	uid;//证件的人编号
	private int	lv;//证件等级
	private int	boodtime;//献血次数
	private String	dated;//献血日期
	private int	volume;//献血量0-200cc 1-300cc
	private int	gid;//纪念品的编号
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getLv() {
		return lv;
	}
	public void setLv(int lv) {
		this.lv = lv;
	}
	public int getBoodtime() {
		return boodtime;
	}
	public void setBoodtime(int boodtime) {
		this.boodtime = boodtime;
	}
	public String getDated() {
		return dated;
	}
	public void setDated(String dated) {
		this.dated = dated;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	@Override
	public String toString() {
		return "Paper [uid=" + uid + ", lv=" + lv + ", boodtime=" + boodtime + ", dated=" + dated + ", volume=" + volume
				+ ", gid=" + gid + "]";
	}
	
	
}
