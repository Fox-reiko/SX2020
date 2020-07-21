package com.bood.page;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class HomePage {

	JFrame jf = new JFrame("管理员界面");

	// 1-admin 2-user 3-共用
	JMenuBar jmb = new JMenuBar();
	JMenu funM1 = new JMenu("管理功能");
	JMenu outM3 = new JMenu("退出");
	JMenu funM2 = new JMenu("功能");

	JMenuItem person1 = new JMenuItem("个人信息管理");
	JMenuItem user1 = new JMenuItem("用户信息管理");
	JMenuItem gift1 = new JMenuItem("纪念品管理");
	JMenuItem paper1 = new JMenuItem("证件管理");
	JMenuItem person2 = new JMenuItem("个人信息管理");
	JMenuItem bood2 = new JMenuItem("挂号（再次献血准备）");
	JMenuItem sureout3 = new JMenuItem("确定退出");

	// 退出
	public void outSys() {
		sureout3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setVisible(false);
			}
		});
	}

	// 初始化界面
	public void setJFrame() {
		jf.setSize(600, 400);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jf.setJMenuBar(jmb);
		jf.setVisible(true);
	}

	// 管理员页面
	public void adminPage() {
		setJFrame();

		// 界面设置
		jmb.add(funM1);
		jmb.add(outM3);
		funM1.add(person1);
		funM1.add(user1);
		funM1.add(gift1);
		funM1.add(paper1);

		outM3.add(sureout3);

		outSys();
	}

	// 用户界面
	public void userPage() {
		setJFrame();

		// 界面设置
		jmb.add(funM2);
		jmb.add(outM3);
		funM2.add(person2);
		funM2.add(bood2);

		outM3.add(sureout3);

		outSys();
	}
}
