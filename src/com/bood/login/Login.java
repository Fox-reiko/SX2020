package com.bood.login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.bood.form.User;
import com.bood.page.HomePage;

public class Login {
	// 初始化字体
	Font d = new Font("楷体", Font.BOLD, 40);
	Font f = new Font("楷体", Font.BOLD, 20);

	// 初始化对象
	JFrame jf = new JFrame("登录页面v0.721");
	JLabel sys = new JLabel("献血管理系统");
	JLabel user = new JLabel("用户名:");
	JLabel pswd = new JLabel("密码:");
	JLabel card = new JLabel("身份:");

	JTextField name = new JTextField();
	JPasswordField pasword = new JPasswordField();
	JComboBox<String> cardtype = new JComboBox<>(new String[] { "献血用户", "管理员" });

	JButton jb = new JButton("登录");

	// 登陆页面详细布局
	private void loginPage() {
		// 设置窗口位置 大小 关闭
		jf.setBounds(400, 100, 600, 400);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jf.setLayout(null);// 绝对布局

		// sys 系统命设置
		sys.setBounds(180, 30, 350, 40);
		sys.setFont(d);
		jf.add(sys);

		// user 用户+登录框 设置
		user.setBounds(150, 100, 100, 30);
		user.setFont(f);
		name.setBounds(250, 100, 200, 30);
		name.setFont(f);
		jf.add(user);
		jf.add(name);

		// pswd 密码+登录框 设置
		pswd.setBounds(150, 160, 100, 30);
		pswd.setFont(f);
		pasword.setBounds(250, 160, 200, 30);
		// pasword.setFont(f);
		jf.add(pswd);
		jf.add(pasword);

		// card 用户身份 +选择框 设置
		card.setBounds(150, 220, 100, 30);
		card.setFont(f);
		cardtype.setBounds(250, 220, 200, 30);
		cardtype.setFont(f);
		jf.add(card);
		jf.add(cardtype);

		// 登录 按钮 设置
		jb.setBounds(150, 280, 300, 50);
		jb.setFont(f);
		jf.add(jb);

		//
		User user = new User();
		LoginCheck check = new LoginCheck();

		// 添加事件
		jb.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				if (name.getText().trim().equals("") || pasword.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "用户名密码不允许为空", "出错提示", JOptionPane.ERROR_MESSAGE);
				} else {
					if (cardtype.getSelectedItem().equals("献血用户"))
						user.setPower(0);// 权限设置 0无
					else
						user.setPower(1);// 权限设置 1有 ,其他出错

					user.setUsername(name.getText());
					user.setPassword(pasword.getText());

					if (check.checkLogin(user)) {

						jf.setVisible(false);// 关闭登录页
						HomePage homePage = new HomePage();
						if (user.getPower() == 0)// 下面调用用户界面
							homePage.adminPage();
						else
							// 下面调用管理员界面
							homePage.adminPage();

					} else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误", "出错提示", JOptionPane.QUESTION_MESSAGE);
						name.setText("");
						pasword.setText("");
					}

				}

			}
		});
	}

	// 程序入口
	public static void main(String[] args) {
		Login Start = new Login();
		Start.loginPage();
	}

}
