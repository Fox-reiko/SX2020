package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.Gift;
import com.bood.linkdb.JDBCUtils;

public class ChangeGiftNum extends IndexAdminPage {

	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);

	public ChangeGiftNum(String username) {
		super(username);
		showGiftName(username);

	}

	private void showGiftName(String username) {

		JLabel title = new JLabel("礼品数量修改");
		title.setFont(d);
		title.setBounds(200, 50, 200, 40);
		jf.add(title);

		JLabel gName = new JLabel("礼品名称:");
		gName.setFont(x);
		gName.setBounds(150, 100, 200, 30);
		jf.add(gName);

		Vector ret = getGiftName();

		JComboBox<Vector> jName = new JComboBox<>(ret);
		jName.setBounds(300, 100, 150, 30);
		jName.setFont(x);
		jf.add(jName);

		JLabel gNum = new JLabel("剩余份额:");
		gNum.setBounds(150, 160, 200, 30);
		gNum.setFont(x);
		jf.add(gNum);

		JTextField jNum = new JTextField();
		jNum.setBounds(300, 160, 150, 30);
		jNum.setFont(x);
		jf.add(jNum);

		JButton changeGN = new JButton("确定修改");
		changeGN.setFont(d);
		changeGN.setBounds(200, 220, 150, 22);
		jf.add(changeGN);

		changeGN.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (jNum.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "剩余份额不允许为空", "出错提示", JOptionPane.ERROR_MESSAGE);
				} else {
					// 获取下拉框值 gName
					String jcb = jName.getSelectedItem().toString().trim();
//					System.out.println(j);
					String gNames = jcb.replace("[", "");
					String gName = gNames.replace("]", "");
//					System.out.println(gName);

					int gNum = -1;
					Boolean tf = true;

					// 将String 转为 int
					try {
						gNum = Integer.parseInt(jNum.getText());
					} catch (Exception ex) {
						tf = false;
						JOptionPane.showMessageDialog(null, "剩余份额处请输入数字", "出错提示", JOptionPane.WARNING_MESSAGE);
						jNum.setText("");
					}
					// 判断输入 剩余份额 是否大于等于0
					if (tf && gNum < 0) {
						tf = false;
						JOptionPane.showMessageDialog(null, "剩余份额大于等于0", "出错提示", JOptionPane.WARNING_MESSAGE);
						jNum.setText("");
					}

					// 上面一步，转型出错，则不执行
					if (tf) {
						Gift gift = new Gift();

						gift.setName(gName);
						gift.setShare(gNum);

						if (changeNewGiftUpdate(gift)) {
							JOptionPane.showMessageDialog(null, "【" + gName + "】礼品修改成功");
						} else {
							JOptionPane.showMessageDialog(null, "【" + gName + "】礼品修改失败", "失败提示",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}

	// 修改礼物数据
	private boolean changeNewGiftUpdate(Gift gift) {
		try {
			String sql = " update gift set share = ? where name =? ";
			link.update(sql, gift.getShare(), gift.getName());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// 获取礼物名
	private Vector getGiftName() {

		Vector ret = new Vector<>();
		List<Gift> lis = link.query(" select * from gift ", new BeanPropertyRowMapper<Gift>(Gift.class));

		for (Gift gift : lis) {
			Vector v = new Vector<>();
			v.add(gift.getName());

			ret.add(v);
		}

		return ret;

	}
}
