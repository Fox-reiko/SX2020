package com.bood.adminpage;

import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.bood.form.Gift;
import com.bood.form.Sample;
import com.bood.form.User;

public class ShowSampleCc extends CheckDB {

	public Vector getPaperNews() {

		Vector ret = new Vector<>();
		List<Sample> lis = link.query(" select * from sample ", new BeanPropertyRowMapper<Sample>(Sample.class));

		for (Sample sample : lis) {
			Vector v = new Vector<>();
			v.add(sample.getSid());
			v.add(uidGetUsername(sample.getUid()));//uid int
			v.add(sample.getSdate());

			ret.add(v);
		}

		return ret;
	}

	// 从uid获取username
	private String uidGetUsername(int uid) {

		String ret = "";
		String sql = " select * from user where uid = ? ";
		List<User> lis = link.query(sql, new BeanPropertyRowMapper<User>(User.class), uid);

		for (User user : lis) {
			ret = user.getUsername();
		}
		
		return ret;

	}

}
