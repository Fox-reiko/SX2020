package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class IndexAdminPage {

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);

	public JFrame jf;//公共的

	private JMenuBar jb;
	private JMenu f1, f2;// 一级菜单
	private JMenuItem s1, s2, s3, s4;// 二级菜单

	public IndexAdminPage(String username) {
		indexInit();// 初始化页面
		init(username);// 框架初始化
		action(username);// 确定退出事件
	}
	
	// 确定退出事件
	private void action(String username) {

//		s1 = new JMenuItem("用户信息");
		s1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new UserPage(username);
			}
		});
		
//		s2 = new JMenuItem("纪念品信息");
		s2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setVisible(false);
				new GiftPage(username);
				
			}
		});
		
//		s3 = new JMenuItem("证件信息");
		s3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new PaperPage(username);
				
			}
		});
		
//		s4 = new JMenuItem("确定退出");
		s4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
			}
		});	

	}

	// 框架初始化
	private void init(String username) {
		jf = new JFrame("欢迎【" + username + "】使用系统");
		jf.setBounds(400, 100, 600, 400);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setLayout(null);
		jf.setJMenuBar(jb);
	}

	// 初始化页面
	private void indexInit() {
		jb = new JMenuBar();
		// 主菜单
		f1 = new JMenu("功能");
		f2 = new JMenu("退出");
		f1.setFont(d);
		f2.setFont(d);
		// 子菜单
		s1 = new JMenuItem("用户信息");
		s2 = new JMenuItem("纪念品信息");
		s3 = new JMenuItem("证件信息");
		s4 = new JMenuItem("确定退出");
		s1.setFont(x);
		s2.setFont(x);
		s3.setFont(x);
		s4.setFont(x);

		jb.add(f1);
		jb.add(f2);
		f1.add(s1);
		f1.add(s2);
		f1.add(s3);
		f2.add(s4);
	}
}
