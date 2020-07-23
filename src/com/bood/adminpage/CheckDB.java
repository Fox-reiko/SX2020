package com.bood.adminpage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.linkdb.JDBCUtils;

public class CheckDB {
	
	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());

}
