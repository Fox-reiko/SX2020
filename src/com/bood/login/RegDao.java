package com.bood.login;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;

public class RegDao {
	// jdbctemplate
	JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

	public Boolean checkUser(User user) {

		try {
			User u = jt.queryForObject("select * from user where username= ? ", new BeanPropertyRowMapper<>(User.class),
					user.getUsername());
		} catch (Exception e) {

			return false;
		}
		return true;// 用户名 重名查找

	}

	// 添加用户
	public Boolean saveUser(User user) {

		try {
			jt.update(
					"INSERT INTO `kcsj2020`.`user`( `username`, `password`, `name`, `sex`, `age`, `boodtype`, `deltype`, `power`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
					user.getUsername(), user.getPassword(), user.getName(), user.getSex(), user.getAge(),
					user.getBoodType(), user.getDelType(), user.getPower());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	//修改个人信息
	public boolean changeSaveUser(User user,String username) {
		try {
			
			String sql = " update user  set username =?,password =?,"
					+ "name =?,sex = ?,age = ?,boodtype = ? where  username = ? ";
			jt.update(sql,user.getUsername(),user.getPassword(),user.getName()
					,user.getSex(),user.getAge(),user.getBoodType(),username);
			
		} catch (Exception e) {
			
			return false;
		}
		
		
		return true;
	}
}
