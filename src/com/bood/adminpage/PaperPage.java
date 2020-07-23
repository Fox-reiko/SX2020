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

public class PaperPage extends IndexAdminPage {

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;
	int getTableId = -1;

	public PaperPage(String username) {
		super(username);
		showPaper(username);

	}

	private void showPaper(String username) {

		JLabel title = new JLabel("证件信息");
		title.setFont(d);
		title.setBounds(20, 50, 200, 40);

		Vector titi = new Vector<>();
		titi.add("证件编号");
		titi.add("证件等级");
		titi.add("献血次数");
		titi.add("上次献血日期");
		titi.add("上次献血量");
		titi.add("上次纪念品");

		ShowPaperCc chec = new ShowPaperCc();
		Vector list = chec.getPaper();

		jt = new JTable(list, titi);
		jt.setFont(x);

		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(2, 100, 580, 120);

		JButton updateU = new JButton("修改证件信息");
		updateU.setFont(x);
		updateU.setBounds(200, 230, 200, 20);

		jf.add(title);
		jf.add(jsp);
		jf.add(updateU);

		jt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getTableId = (int) jt.getValueAt(jt.getSelectedRow(), 0);
			}
		});

		updateU.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getTableId < 0) {
					JOptionPane.showMessageDialog(null, "必须用鼠标点击一个证件信息");
				} else {
					jf.setVisible(false);
					new ChangePaperNews(username, getTableId);
				}
			}
		});

	}

}
