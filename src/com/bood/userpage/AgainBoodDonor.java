package com.bood.userpage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.JOptionPane;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.linkdb.JDBCUtils;

public class AgainBoodDonor extends IndexUserPage {

	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

	public AgainBoodDonor(String username){
		super(username);
		JOptionPane.showMessageDialog(null, "此页面正在抢修中！");
		jf.setVisible(false);
		new ShowUserNews(username);

	}

	public void getTime() throws Exception {
		// 取得当前日期 并 格式化
		LocalDate date = LocalDate.now();
//				System.out.println(date.getClass());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//				System.out.println(formatter.getClass());

		// 把日期转为串
		String newTime = formatter.format(date);
//				System.out.println(newTime.getClass());

		// 把日期串转为Date类型
		Date nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(newTime);
		System.out.println(nowDate.getClass());
		System.out.println(nowDate);

		try {
			link.update(" update set sdate = ? where uid = 1 ", formatter);
			System.out.println("succeed");

		} catch (Exception e) {
			System.out.println("error");
		}
	}

}
