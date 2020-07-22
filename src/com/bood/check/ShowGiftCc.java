package com.bood.check;

import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.bood.form.Gift;

public class ShowGiftCc extends CheckDB{
	
	public Vector getGift() {
		Vector ret =  new Vector<>();
		List<Gift> lis = link.query(" select * from gift ", new BeanPropertyRowMapper<Gift>(Gift.class));
		
		for (Gift gift : lis) {
			Vector v = new Vector<>();
			v.add(gift.getGid());
			v.add(gift.getName());
			v.add(gift.getShare());
			
			ret.add(v);
		}
		
		return ret;
	}
	
	
	
	
	
	
	
	
	
	
}
