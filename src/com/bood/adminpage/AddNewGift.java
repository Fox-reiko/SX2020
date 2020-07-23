package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.Gift;
import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;

public class AddNewGift extends IndexAdminPage {
	
	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);

	private JLabel title, jlna, jlnum;
	private JButton addc;
	private JTextField jtna, jtnum;

	public AddNewGift(String username) {
		super(username);
		addNewG();

	}

	private void addNewG() {
		title = new JLabel("添加献血的纪念礼品");
		title.setFont(d);
		title.setBounds(200, 50, 200, 40);
		jf.add(title);

		jlna = new JLabel("礼品名称:");
		jlna.setFont(x);
		jlna.setBounds(150, 100, 200, 30);
		jf.add(jlna);

		jtna = new JTextField();
		jtna.setFont(x);
		jtna.setBounds(300, 100, 150, 30);
		jf.add(jtna);

		jlnum = new JLabel("礼品份额(数量):");
		jlnum.setBounds(150, 160, 200, 30);
		jlnum.setFont(x);
		jf.add(jlnum);

		jtnum = new JTextField();
		jtnum.setBounds(300, 160, 150, 30);
		jtnum.setFont(x);
		jf.add(jtnum);

		addc = new JButton("确定添加");
		addc.setFont(d);
		addc.setBounds(200, 220, 150, 22);
		jf.add(addc);

		addc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jtna.getText().trim().equals("") || jtnum.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "礼品名称&礼品份额不允许为空", "出错提示", JOptionPane.ERROR_MESSAGE);
				} else {
					String gName = jtna.getText();
					int gNum = -1;
					Boolean tf = true;

					// 将String 转为 int
					try {
						gNum = Integer.parseInt(jtnum.getText());
					} catch (Exception ex) {
						tf = false;
						JOptionPane.showMessageDialog(null, "礼品份额处请输入数字", "出错提示", JOptionPane.WARNING_MESSAGE);
						jtnum.setText("");
					}

					// 上面一步，转型出错，则不执行
					if (tf) {
						Gift gift = new Gift();

						gift.setName(gName);
						gift.setShare(gNum);
						if (addNewGiftCc(gift)) {
							JOptionPane.showMessageDialog(null, "【" + gName + "】礼品已存在", "出错提示",
									JOptionPane.QUESTION_MESSAGE);
						} else {
							if(addNewGiftUpdate(gift)) {
								JOptionPane.showMessageDialog(null, "【" + gName + "】礼品添加成功");
							}else {
								JOptionPane.showMessageDialog(null, "【" + gName + "】礼品添加失败","失败提示",JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
			}
		});
	}
	
	private Boolean addNewGiftUpdate(Gift gift) {
		
		try {
			String sql =" insert into gift(name,share) values(?,?) ";
			link.update(sql, gift.getName(),gift.getShare());
//			link.update(" insert into gift(name,share) values(?,?) ", "果果狸",99);
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}

	private Boolean addNewGiftCc(Gift gift) {
		
		try {
			link.queryForObject("select * from gift where name=?", new BeanPropertyRowMapper<>(Gift.class),
					gift.getName());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
