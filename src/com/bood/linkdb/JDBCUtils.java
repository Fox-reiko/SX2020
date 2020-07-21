package com.bood.linkdb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;


public class JDBCUtils {
	
	static DataSource  dataSource;
	
	// 休息20分钟
	static {
		// DriverManager 注册驱动
		try {
			//1 Properties
			Properties pro = new Properties();
			//2 加载器
			InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("src_jdbc_properties");
			//3 load
			pro.load(is);
			dataSource = DruidDataSourceFactory.createDataSource(pro);
			
			
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	
	/*
	 * 提供连接
	 */
	public static Connection getConnection() throws SQLException {
		return  dataSource.getConnection();
	}
	
	
	
	
}
