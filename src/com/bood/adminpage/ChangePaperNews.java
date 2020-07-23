package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.Gift;
import com.bood.form.Paper;
import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;

public class ChangePaperNews extends IndexAdminPage {

	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;

	public ChangePaperNews(String username, int getTableId) {
		super(username);
		onlyChangelv(username, getTableId);

	}

	private void onlyChangelv(String username, int getTableId) {
		JLabel title = new JLabel("选择修改的证件信息");
		title.setFont(d);
		title.setBounds(20, 50, 200, 40);

		Vector titi = new Vector<>();
		titi.add("证件编号");
		titi.add("证件等级");
		titi.add("献血次数");
		titi.add("上次献血日期");
		titi.add("上次献血量");
		titi.add("上次纪念品");

		Vector list = getPaperNews(getTableId);

		jt = new JTable(list, titi);
		jt.setFont(x);

		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(2, 100, 580, 39);

		jf.add(title);
		jf.add(jsp);

		JLabel jl = new JLabel("只能修改证件等级");
		jl.setBounds(50, 155, 150, 20);
		jf.add(jl);

		JLabel jl2 = new JLabel("修改之后等级:");
		jl2.setBounds(130, 170, 190, 30);
		jl2.setFont(d);
		jf.add(jl2);

		JTextField newLV = new JTextField();
		newLV.setBounds(280, 170, 150, 30);
		newLV.setFont(d);
		jf.add(newLV);

		JButton jb = new JButton("提交");
		jb.setBounds(230, 210, 100, 20);
		jb.setFont(d);
		jf.add(jb);

		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean tf = true;
				// 首先没填数据 提示错误
				if (tf) {
					if (newLV.getText().trim().equals("")) {
						tf = false;
						JOptionPane.showMessageDialog(null, "框内不许留空", "错误提示", JOptionPane.ERROR_MESSAGE);
					}
				}

				// 等级非数字提示
				int setLV = -1;
				if (tf) {
					try {
						setLV = Integer.parseInt(newLV.getText());
					} catch (Exception ex) {
						tf = false;
						JOptionPane.showMessageDialog(null, "等级请输入数字");
						newLV.setText("");
					}
				}

				// 等级范围限制 0<lv<10
				if (tf && (setLV < 0 || setLV > 10)) {
					JOptionPane.showMessageDialog(null, "等级范围限制 0-9");
					tf = false;
					newLV.setText("");
				}
				
				if(tf) {
					if(updateLv(getTableId,setLV)) {//更新等级成功
						JOptionPane.showMessageDialog(null, "等级更新成功");
						jf.setVisible(false);
						new PaperPage(username);
						
					}else {
						JOptionPane.showMessageDialog(null, "等级更新失败");
					}
					
					
				}

			}

		});

	}
	
	
	private Boolean updateLv(int getTableId,int setLV) {
		try {
			
			link.update(" update paper set lv = ? where uid = ? ", setLV,getTableId);
			
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}

	
	
	public Vector getPaperNews(int getTableId) {

		Vector ret = new Vector<>();
		List<Paper> lis = link.query(" select * from paper where uid = ? ",
				new BeanPropertyRowMapper<Paper>(Paper.class), getTableId);

		for (Paper paper : lis) {
			Vector v = new Vector<>();
			v.add(paper.getUid());
			v.add(paper.getLv());
			v.add(paper.getBoodtime());
			v.add(paper.getDated());
			v.add(paper.getVolume() == 1 ? "300cc" : "200cc");
			v.add(getGiftName(paper.getGid()));

			ret.add(v);
		}

		return ret;
	}

	// 从uid获取username
	private String uidGetUsername(int uid) {

		String ret = "";
		String sql = " select * from user where uid = ? ";
		List<User> lis = link.query(sql, new BeanPropertyRowMapper<User>(User.class), uid);

		for (User user : lis) {
			ret = user.getUsername();
		}

		return ret;

	}

	// 从gid获取Gift.name
	private String getGiftName(int gid) {

		String ret = "";
		String sql = " select * from gift where gid = ? ";
		List<Gift> lis = link.query(sql, new BeanPropertyRowMapper<Gift>(Gift.class), gid);

		for (Gift gift : lis) {
			ret = gift.getName();
		}

		return ret;

	}

}
