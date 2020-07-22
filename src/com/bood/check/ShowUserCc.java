package com.bood.check;

import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.bood.form.User;

public class ShowUserCc extends CheckDB{

	public Vector getUser() {
		Vector ret =  new Vector<>();
		List<User> lis = link.query(" select * from user ", new BeanPropertyRowMapper<User>(User.class));
		
		for (User user : lis) {
			Vector v = new Vector<>();
			v.add(user.getUid());
			v.add(user.getUsername());
			v.add(user.getPassword());
			v.add(user.getName());
			v.add(user.getSex());
			v.add(user.getAge());
			v.add(user.getBoodType());
			v.add(user.getDelType() == 1?"可用":"不可用");
			v.add(user.getPower() == 1?"管理员":"用户");

			ret.add(v);
		}
		
		return ret;
	}

}
