package com.bood.page;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bood.check.ShowGiftCc;
import com.bood.check.ShowUserCc;

public class UserPage extends IndexPage{
	
	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;
	
	public UserPage(String username) {
		super(username);
		showUser();
	}

	private void showUser() {


		JLabel title = new JLabel("用户信息");
		title.setFont(d);
		title.setBounds(20, 50, 200, 40);
		
		Vector titi = new Vector<>();
		titi.add("用户编号");
		titi.add("登录名");
		titi.add("登录密码");
		titi.add("姓名");
		titi.add("性别");
		titi.add("年龄");
		titi.add("血型");
		titi.add("用户状态");
		titi.add("用户权限");
		
		ShowUserCc chec = new ShowUserCc();
		Vector list = chec.getUser();
		
		jt = new JTable(list,titi);
		jt.setFont(x);
		
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(2, 100, 580, 120);
		
		JButton updateU = new JButton("x修改用户信息");
		updateU.setFont(x);
		updateU.setBounds(200, 230, 200, 20);

		jf.add(title);
		jf.add(jsp);
		jf.add(updateU);

		
	}

	
}
