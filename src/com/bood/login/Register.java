package com.bood.login;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.bood.form.User;

public class Register {

	public Register() {
		registerUi();

	}

	// 类中声明用到的组件
	// 初始化字体
	Font a = new Font("楷体", Font.BOLD, 30);
	Font d = new Font("楷体", Font.BOLD, 25);
	// 初始化对象
	JFrame registerui = new JFrame("用户注册页面");
	JLabel system = new JLabel("献血者信息管理系统");
	JLabel usernamela = new JLabel("用户名");
	JLabel passwordla = new JLabel("密码");
	JLabel name = new JLabel("姓名");
	JLabel sex = new JLabel("性别");
	JLabel age = new JLabel("年龄");
	JLabel boodtype = new JLabel("血型");
//	JLabel power = new JLabel("权限");

	JTextField username = new JTextField();
	JPasswordField password = new JPasswordField();
	JTextField namela = new JTextField();
	JRadioButton radioButton1 = new JRadioButton("男");
	JRadioButton radioButton2 = new JRadioButton("女");
	JTextField agela = new JTextField();
	JTextField boodtypela = new JTextField();
//	JRadioButton radioButton3 = new JRadioButton("无权限");
//	JRadioButton radioButton4 = new JRadioButton("有权限");

	// 按钮组
//	ButtonGroup btButtonGroup1;
//	ButtonGroup btButtonGroup2;

	JButton login = new JButton("注册");

	User user = new User();

	// 绘画登录页面
	private void registerUi() {
		// 设置x y w h
		registerui.setBounds(350, 20, 650, 700);
		// 设置退出
		registerui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		registerui.setVisible(true);
		// 绝对布局
		registerui.setLayout(null);
		// 系统名称
		system.setBounds(150, 80, 300, 40);
		system.setFont(a);
		// 用户名
		usernamela.setBounds(120, 150, 100, 30);
		usernamela.setFont(d);
		// 密码
		passwordla.setBounds(120, 210, 100, 30);
		passwordla.setFont(d);
		// 姓名
		name.setBounds(120, 270, 100, 30);
		name.setFont(d);
		// 性别
		sex.setBounds(120, 330, 100, 30);
		sex.setFont(d);
		// 年龄
		age.setBounds(120, 390, 100, 30);
		age.setFont(d);
		// 血型
		boodtype.setBounds(120, 450, 100, 30);
		boodtype.setFont(d);
		// 权限
//		power.setBounds(120, 510, 100, 30);
//		power.setFont(d);

		// 输入框
		username.setBounds(250, 150, 200, 30);
		username.setFont(d);
		password.setBounds(250, 210, 200, 30);
//		password.setFont(d);
		namela.setBounds(250, 270, 200, 30);
		namela.setFont(d);
		agela.setBounds(250, 390, 200, 30);
		agela.setFont(d);
		boodtypela.setBounds(250, 450, 200, 30);
		boodtypela.setFont(d);

		// 性别
		radioButton1.setBounds(250, 330, 80, 30);
		radioButton1.setFont(d);
		radioButton2.setBounds(350, 330, 120, 30);
		radioButton2.setFont(d);
		// 默认为男性
		radioButton1.setSelected(true);
//		radioButton3.setBounds(250, 510, 80, 30);
//		radioButton4.setBounds(350, 510, 80, 30);
//		// 按钮
		login.setBounds(120, 500, 330, 50);
		login.setFont(d);

		registerui.add(system);
		registerui.add(usernamela);
		registerui.add(passwordla);
		registerui.add(name);
		registerui.add(sex);
		registerui.add(age);
		registerui.add(boodtype);
//		registerui.add(power);

		registerui.add(username);
		registerui.add(password);
		registerui.add(namela);
		registerui.add(radioButton1);
		registerui.add(radioButton2);
		registerui.add(agela);
		registerui.add(boodtypela);
//		registerui.add(radioButton3);
//		registerui.add(radioButton4);
		registerui.add(login);
//
//		btButtonGroup1 = new ButtonGroup();
//		btButtonGroup1.add(radioButton1);
//		btButtonGroup1.add(radioButton2);
//		btButtonGroup2 = new ButtonGroup();
//		btButtonGroup2.add(radioButton3);
//		btButtonGroup2.add(radioButton4);

		// 登录
		login.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {

				Boolean tf = true;
//				name.getText().trim().equals("") 
				// 首先没填数据 提示错误
				if (tf) {
					if (username.getText().trim().equals("") || password.getText().trim().equals("")
							|| namela.getText().trim().equals("") || boodtypela.getText().trim().equals("")
							|| agela.getText().trim().equals("")) {
						tf = false;
						JOptionPane.showMessageDialog(null, "框内不许留空", "错误提示", JOptionPane.ERROR_MESSAGE);
					}
				}

				// 判断年龄，不合要求就不进行后面的勒
				// 年龄判断
//				String ageText = agela.getText();
				int ageText = -1;
				if (tf) {
					try {
						ageText = Integer.parseInt(agela.getText());// 年龄
					} catch (Exception ex) {
						tf = false;
						JOptionPane.showMessageDialog(null, "年龄请输入数字");
//					user.setAge("");
						agela.setText("");
					}
				}

				// 年龄限制
				if (tf && ageText < 16) {
					JOptionPane.showMessageDialog(null, "年龄至少16");
					tf = false;
					agela.setText("");
				}

				if (tf) {
					// 获取用户名密码
					String usernameText = username.getText();// 用户名
					String namelaText = namela.getText();// 姓名
					String boodtypeText = boodtypela.getText();// 血型
//				char[] p = password.getPassword();
					String passwordText = password.getText();// 密码

					// 存入到user
					user.setUsername(usernameText);
					user.setPassword(passwordText);
					user.setName(namelaText);
					if (radioButton1.isSelected()) {
						user.setSex("男");
					} else {
						user.setSex("女");
					}
					user.setAge(ageText);
					user.setBoodType(boodtypeText);

					// 默认信息
					user.setDelType(1);// 0-不可用状态1-可用状态
					user.setPower(0);// 权限 1有 0无
//					System.out.println(user);

					// 根据用户名密码 验证
					RegDao userDao = new RegDao();
					if (userDao.checkUser(user)) {
						// 用户名重名提示
						JOptionPane.showMessageDialog(null, "用户名已存在", "重名警告", JOptionPane.WARNING_MESSAGE);
					} else {
						// 添加用户
						if (userDao.saveUser(user)) {
							JOptionPane.showMessageDialog(null, "注册成功,请登录");
							registerui.setVisible(false);

						} else {
							JOptionPane.showMessageDialog(null, "注册失败");
						}
					}

				}

			}

//				if (radioButton3.isSelected()) {
//					user.setPower(0);
//				}
//				if (radioButton4.isSelected()) {
//					user.setPower(1);
//				}	
		});
	}
}
