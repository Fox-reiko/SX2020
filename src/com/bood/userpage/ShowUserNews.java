package com.bood.userpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bood.adminpage.ShowUserCc;

public class ShowUserNews extends IndexUserPage {

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt,jt2;

	public ShowUserNews(String username) {
		super(username);
		showNews(username);

	}

	private void showNews(String username) {

		// 个人信息-------------------------------------------------
		JLabel title = new JLabel("个人信息");
		title.setFont(x);
		title.setBounds(20, 25, 200, 20);

		Vector titi = new Vector<>();
		titi.add("登录名");
		titi.add("登录密码");
		titi.add("姓名");
		titi.add("性别");
		titi.add("年龄");
		titi.add("血型");

		ShowUserNewsCc chec = new ShowUserNewsCc();
		Vector list = chec.getUserNews(username);

		jt = new JTable(list, titi);
		jt.setFont(x);

		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(30, 50, 500, 39);

		// 献血证信息-------------------------------------------------
		JLabel title2 = new JLabel("献血证信息");
		title2.setFont(x);
		title2.setBounds(20, 125, 200, 20);

		Vector titi2 = new Vector<>();
		titi2.add("献血证等级");
		titi2.add("献血次数");
		titi2.add("上次献血时间");
		titi2.add("上次献血量");
		titi2.add("上次纪念品");

		Vector list2 = chec.getUserPaper(username);

		jt2 = new JTable(list2, titi2);
		jt2.setFont(x);

		JScrollPane jsp2 = new JScrollPane(jt2);
		jsp2.setBounds(30, 150, 500, 39);

		JButton again = new JButton("去献血");
		again.setFont(x);
		again.setBounds(70, 230, 200, 20);
		
		JButton upnews = new JButton("修改个人信息");
		upnews.setFont(x);
		upnews.setBounds(330, 230, 150, 20);

		jf.add(upnews);
		jf.add(again);
		jf.add(title);
		jf.add(jsp);
		jf.add(title2);
		jf.add(jsp2);
		
		//再次献血事件
		again.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new AgainBoodDonor(username);
				
			}
		});
		
		
		//修改个人信息事件
		upnews.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new ChangeUserNews(username);
				
			}
		});

	}

}
