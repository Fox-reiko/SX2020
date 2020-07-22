package com.bood.login;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;

public class LoginCheck {
	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

	public Boolean checkLogin(User user) {

		try {
//			User ret = 
			link.queryForObject("select * from user where username=? and password=? and power= ? and deltype = 1",
					new BeanPropertyRowMapper<>(User.class), user.getUsername(), user.getPassword(), user.getPower());
		} catch (Exception e) {
//			System.out.println("0");
			return false;
		}
//		System.out.println("1");
		return true;
	}
}
