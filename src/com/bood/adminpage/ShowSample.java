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

import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.linkdb.JDBCUtils;

public class ShowSample extends IndexAdminPage {

	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());
	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;
	int getedTableId = -1;

	public ShowSample(String username) {
		super(username);
		showPaperPage(username);

	}

	private void showPaperPage(String username) {

		JLabel title = new JLabel("样本信息");
		title.setFont(x);
		title.setBounds(20, 25, 200, 20);

		Vector titi = new Vector<>();
		titi.add("样本编号");
		titi.add("样本人用户名");
		titi.add("样本日期");

		ShowSampleCc cc = new ShowSampleCc();
		Vector list = cc.getPaperNews();

		jt = new JTable(list, titi);
		jt.setFont(x);

		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(30, 50, 500, 130);

		jf.add(title);
		jf.add(jsp);

		JButton delT = new JButton("删除(不可恢复)");
		delT.setFont(x);
		delT.setBounds(150, 200, 300, 20);
		jf.add(delT);

		// 确定删除事件
		delT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getedTableId < 0) {
					JOptionPane.showMessageDialog(null, "先用鼠标选一个表格数据,再点击修改");
				} else {
					if (delSample(getedTableId)) {
						JOptionPane.showMessageDialog(null, "删除成功");
						jf.setVisible(false);
						new ShowSample(username);
					} else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
				}
			}

		});

		// get choose id
		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getedTableId = (int) jt.getValueAt(jt.getSelectedRow(), 0);
			}

		});
	}

	private boolean delSample(int getedTableId) {
		try {
			String sql = " delete from sample where sid =? ";
			link.update(sql, getedTableId);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
