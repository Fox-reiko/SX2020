package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;

public class AdminUpdateUserNews extends IndexAdminPage {

	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;
	int getedTableId = -1;

	public AdminUpdateUserNews(String username) {
		super(username);
		showUser(username);
	}

	private void showUser(String username) {

		JLabel title = new JLabel("用户信息");
		title.setFont(d);
		title.setBounds(20, 50, 100, 40);

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

		jt = new JTable(list, titi);
		jt.setFont(x);

		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(2, 100, 580, 120);

		jf.add(title);
		jf.add(jsp);

		JButton updateU = new JButton("更改管理员个人信息");
		updateU.setFont(x);
		updateU.setBounds(150, 230, 300, 20);
		jf.add(updateU);

		JButton delT = new JButton("删除(设置用户状态成不可用)");
		delT.setFont(x);
		delT.setBounds(150, 270, 300, 20);
		jf.add(delT);
		
		JButton candelT = new JButton("恢复(设置用户状态成可用)");
		candelT.setFont(x);
		candelT.setBounds(150, 310, 300, 20);
		jf.add(candelT);
		
		//改数据
		updateU.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new AdminChangNews(username);
				
			}
		});

		// get choose id
		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getedTableId = (int) jt.getValueAt(jt.getSelectedRow(), 0);
//				System.out.println(getedTableId);
			}

		});
		
		//可用
		candelT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getedTableId < 0) {
					JOptionPane.showMessageDialog(null, "先用鼠标选一个表格数据,再点击修改");
				} else {
					if (isNotYouself(username, getedTableId)) {// 选中的不许是你自己

						JOptionPane.showMessageDialog(null, "恢复不能选择自己");
					} else {
						if (candelTypeChange(getedTableId)) {
							JOptionPane.showMessageDialog(null, "恢复成功");
							jf.setVisible(false);
							new AdminUpdateUserNews(username);
						} else {
							JOptionPane.showMessageDialog(null, "恢复失败");
						}
					}
				}

			}


		});

		//不可用
		delT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getedTableId < 0) {
					JOptionPane.showMessageDialog(null, "先用鼠标选一个表格数据,再点击修改");
				} else {
					if (isNotYouself(username, getedTableId)) {// 选中的不许是你自己

						JOptionPane.showMessageDialog(null, "删除不能选择自己");
					} else {
						if (delTypeChange(getedTableId)) {
							JOptionPane.showMessageDialog(null, "删除成功");
							jf.setVisible(false);
							new AdminUpdateUserNews(username);
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}

			}

		});

	}
	
	//可用 1
	private boolean candelTypeChange(int getedTableId) {
		try {
			String sql = " update user set deltype = 1 where uid =? ";
			link.update(sql, getedTableId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	//不可用 0
	private boolean delTypeChange(int getedTableId) {
		try {
			String sql = " update user set deltype = 0 where uid =? ";
			link.update(sql, getedTableId);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	//不是自己
	private boolean isNotYouself(String username, int getedTableId) {

		User ret = link.queryForObject("select * from user where username=? ", new BeanPropertyRowMapper<>(User.class), username);

		if(ret.getUid() == getedTableId) {
			return true;
			
		}
		return false;

	}
}
