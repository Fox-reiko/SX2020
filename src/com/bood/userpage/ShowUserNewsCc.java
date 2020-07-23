package com.bood.userpage;

import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bood.form.Gift;
import com.bood.form.Paper;
import com.bood.form.User;
import com.bood.linkdb.JDBCUtils;

public class ShowUserNewsCc {

	JdbcTemplate link = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public Vector getUserNews(String username) {
		Vector ret =  new Vector<>();
		List<User> lis = link.query(" select * from user where username = ? ", new BeanPropertyRowMapper<User>(User.class),username);
		
		for (User user : lis) {
			Vector v = new Vector<>();
			v.add(user.getUsername());
			v.add(user.getPassword());
			v.add(user.getName());
			v.add(user.getSex());
			v.add(user.getAge());
			v.add(user.getBoodType());

			ret.add(v);
		}
		
		return ret;
	}

	public Vector getUserPaper(String username) {
		Vector ret =  new Vector<>();
		String sql = " select * from paper where uid in(select uid from user where username = ?) ";
		List<Paper> lis = link.query(sql, new BeanPropertyRowMapper<Paper>(Paper.class),username);
		
		for (Paper paper : lis) {
			Vector v = new Vector<>();
			v.add(paper.getLv());
			v.add(paper.getBoodtime());
			v.add(paper.getDated());
			v.add(paper.getVolume() == 0?"200cc":"300cc");
//			v.add(paper.getGid());
			v.add(getGiftName(paper.getGid()));//从gid获取礼品名

			ret.add(v);
		}
//		System.out.println(ret);
		return ret;
	}

	//从gid获取礼品名
	private String getGiftName(int gid) {
		
		String ret ="";
		String sql = " select * from gift where gid = ? ";
		List<Gift> lis = link.query(sql, new BeanPropertyRowMapper<Gift>(Gift.class),gid);
		
		for (Gift gift : lis) {
			ret = gift.getName();
		}
		return ret;
	}

}
