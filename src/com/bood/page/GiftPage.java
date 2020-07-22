package com.bood.page;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.bood.check.ShowGiftCc;

public class GiftPage extends IndexPage {

	Font d = new Font("楷体", Font.BOLD, 20);
	Font x = new Font("楷体", Font.BOLD, 16);
	private JTable jt;

	public GiftPage(String username) {
		super(username);
		showGift();
	}

//		jf.setBounds(400, 100, 600, 400);
	private void showGift() {
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
		
		JButton addG = new JButton("x增添新礼品");
		addG.setFont(x);
		addG.setBounds(100, 220, 150, 20);
		
		JButton addNum = new JButton("x增添礼品数量");
		addNum.setFont(x);
		addNum.setBounds(350, 220, 150, 20);
		
		jf.add(title);
		jf.add(jsp);
		jf.add(addG);
		jf.add(addNum);

	}

}
