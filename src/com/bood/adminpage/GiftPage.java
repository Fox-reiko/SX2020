package com.bood.adminpage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GiftPage extends IndexAdminPage {

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;

	public GiftPage(String username) {
		super(username);
		showGift(username);
	}
	
	private void showGift(String username) {
		JLabel title = new JLabel("礼品(纪念品)信息");
		title.setFont(d);
		title.setBounds(200, 50, 200, 40);
		
		Vector titi = new Vector<>();
		titi.add("礼品编号");
		titi.add("礼品名称");
		titi.add("剩余数量(份)");
		
		ShowGiftCc chec = new ShowGiftCc();
		Vector list = chec.getGift();
		
		jt = new JTable(list,titi);
		jt.setFont(x);
		
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setBounds(100, 100, 400, 100);
		
		JButton addG = new JButton("增添新礼品");
		addG.setFont(x);
		addG.setBounds(100, 220, 150, 20);
		
		JButton addNum = new JButton("增添礼品份额");
		addNum.setFont(x);
		addNum.setBounds(350, 220, 150, 20);
		
		jf.add(title);
		jf.add(jsp);
		jf.add(addG);
		jf.add(addNum);
		
		addNum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new ChangeGiftNum(username);
				
			}
		});
		
		addG.addActionListener(new  ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf.setVisible(false);
				new AddNewGift(username);
				
			}
		});

	}

}
