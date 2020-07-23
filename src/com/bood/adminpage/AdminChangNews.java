package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;
import com.bood.login.RegDao;
import com.bood.userpage.ShowUserNews;

public class AdminChangNews extends IndexAdminPage {
	JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

	Font a = new Font("楷体", Font.BOLD, 30);
	Font d = new Font("楷体", Font.BOLD, 25);

	JLabel system = new JLabel("个人信息修改");

	JLabel usernamela = new JLabel("用户名");
	JLabel passwordla = new JLabel("密码");
	JLabel name = new JLabel("姓名");
	JLabel sex = new JLabel("性别");
	JLabel age = new JLabel("年龄");
	JLabel boodtype = new JLabel("血型");

	JTextField usernames = new JTextField();
	JPasswordField password = new JPasswordField();
	JTextField namela = new JTextField();
	JRadioButton radioButton1 = new JRadioButton("男");
	JRadioButton radioButton2 = new JRadioButton("女");
	JTextField agela = new JTextField();
	JTextField boodtypela = new JTextField();

	JButton changeNews = new JButton("确定更改");
	JButton back = new JButton("取消更改");

	User user = new User();

	public AdminChangNews(String username) {
		super(username);
		adminChangNewsPage(username);
	}

	private void adminChangNewsPage(String username) {

		// 临时改一下
		jf.setBounds(350, 20, 650, 700);

		system.setBounds(150, 80, 300, 40);
		system.setFont(a);

		back.setBounds(450, 80, 140, 25);
		back.setFont(d);
		;
		jf.add(back);

		// 左边文本
		usernamela.setBounds(120, 150, 100, 30);
		usernamela.setFont(d);
		passwordla.setBounds(120, 210, 100, 30);
		passwordla.setFont(d);
		name.setBounds(120, 270, 100, 30);
		name.setFont(d);
		sex.setBounds(120, 330, 100, 30);
		sex.setFont(d);
		age.setBounds(120, 390, 100, 30);
		age.setFont(d);
		boodtype.setBounds(120, 450, 100, 30);
		boodtype.setFont(d);

		// 输入框
		usernames.setBounds(250, 150, 200, 30);
		usernames.setFont(d);
		password.setBounds(250, 210, 200, 30);
		namela.setBounds(250, 270, 200, 30);
		namela.setFont(d);
		agela.setBounds(250, 390, 200, 30);
		agela.setFont(d);
		boodtypela.setBounds(250, 450, 200, 30);
		boodtypela.setFont(d);

		// 性别 默认为男性
		radioButton1.setBounds(250, 330, 80, 30);
		radioButton1.setFont(d);
		radioButton2.setBounds(350, 330, 120, 30);
		radioButton2.setFont(d);
		radioButton1.setSelected(true);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioButton1);
		bg.add(radioButton2);

		// 按钮
		changeNews.setBounds(120, 500, 330, 50);
		changeNews.setFont(d);
		
		

		jf.add(system);
		jf.add(usernamela);
		jf.add(passwordla);
		jf.add(name);
		jf.add(sex);
		jf.add(age);
		jf.add(boodtype);

		jf.add(usernames);
		jf.add(password);
		jf.add(namela);
		jf.add(radioButton1);
		jf.add(radioButton2);
		jf.add(agela);
		jf.add(boodtypela);
		jf.add(changeNews);

		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				jf.setVisible(false);
				new AdminUpdateUserNews(username);

			}
		});

		changeNews.addActionListener(new ActionListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Boolean tf = true;
				// 首先没填数据 提示错误
				if (tf) {
					if (usernames.getText().trim().equals("") || password.getText().trim().equals("")
							|| namela.getText().trim().equals("") || boodtypela.getText().trim().equals("")
							|| agela.getText().trim().equals("")) {
						tf = false;
						JOptionPane.showMessageDialog(null, "框内不许留空", "错误提示", JOptionPane.ERROR_MESSAGE);
					}
				}

				// 判断年龄，不合要求就不进行后面的勒
				// 年龄判断
				int ageText = -1;
				if (tf) {
					try {
						ageText = Integer.parseInt(agela.getText());// 年龄
					} catch (Exception ex) {
						tf = false;
						JOptionPane.showMessageDialog(null, "年龄请输入数字");
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
					String usernameText = usernames.getText();// 用户名
					String namelaText = namela.getText();// 姓名
					String boodtypeText = boodtypela.getText();// 血型
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

					// 根据用户名密码 验证
					RegDao userDao = new RegDao();
					if (userDao.checkUser(user)) {
						// 用户名重名提示
						JOptionPane.showMessageDialog(null, "用户名已存在", "重名警告", JOptionPane.WARNING_MESSAGE);
					} else {
						// 添加用户
						if (userDao.changeSaveUser(user, username)) {
							JOptionPane.showMessageDialog(null, "信息修改成功");
							jf.setVisible(false);
							new AdminUpdateUserNews(user.getUsername());

						} else {
							JOptionPane.showMessageDialog(null, "信息修改失败");
						}
					}

				}

			}
		});
	}

}
