package com.bood.form;

public class User {
	
	private int uid;//用户的编号
	private String username;//登录名账户
	private String password;//密码
	private String name;//姓名
	private String sex;//性别
	private int age;//年龄
	private String boodType;//血型 AB A B O
	private int delType;//0-不可用状态1-可用状态
	private int	power;//权限  1有  0无
	
	//get set
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBoodType() {
		return boodType;
	}
	public void setBoodType(String boodType) {
		this.boodType = boodType;
	}
	public int getDelType() {
		return delType;
	}
	public void setDelType(int delType) {
		this.delType = delType;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", power=" + power + ", name="
				+ name + ", sex=" + sex + ", age=" + age + ", boodType=" + boodType + ", delType=" + delType + "]";
	}
	
}
