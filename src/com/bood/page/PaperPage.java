package com.bood.page;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bood.check.ShowPaperCc;
import com.bood.check.ShowUserCc;

public class PaperPage extends IndexPage{
	
	
	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;
	
	public PaperPage(String username) {
		super(username);
		showPaper();

	}

	private void showPaper() {
		
		JLabel title = new JLabel("证件信息");
		title.setFont(d);
		title.setBounds(20, 50, 200, 40);
		
		Vector titi = new Vector<>();
		titi.add("证件编号");
		titi.add("证件等级");
		titi.add("献血次数");
		titi.add("上次献血日期");
		titi.add("上次献血量");
		titi.add("纪念品类别号");
		
		ShowPaperCc chec = new ShowPaperCc();
		Vector list = chec.getPaper();
		
		jt = new JTable(list,titi);
		jt.setFont(x);
		
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(2, 100, 580, 120);
		
		JButton updateU = new JButton("x修改证件信息");
		updateU.setFont(x);
		updateU.setBounds(200, 230, 200, 20);

		jf.add(title);
		jf.add(jsp);
		jf.add(updateU);

		
		
	}

}
