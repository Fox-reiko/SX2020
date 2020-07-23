package com.bood.adminpage;

import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.bood.form.Gift;
import com.bood.form.Paper;
import com.bood.form.User;

public class ShowPaperCc extends CheckDB {

	public Vector getPaper() {

		Vector ret = new Vector<>();
		List<Paper> lis = link.query(" select * from paper ", new BeanPropertyRowMapper<Paper>(Paper.class));

		for (Paper paper : lis) {
			Vector v = new Vector<>();
			v.add(paper.getUid());
			v.add(paper.getLv());
			v.add(paper.getBoodtime());
			v.add(paper.getDated());
			v.add(paper.getVolume() == 1?"300cc":"200cc");
			v.add(getGiftName(paper.getGid()));

			ret.add(v);
		}

		return ret;
	}

	// 从gid获取Gift.name
	private String getGiftName(int gid) {

			String ret = "";
			String sql = " select * from gift where gid = ? ";
			List<Gift> lis = link.query(sql, new BeanPropertyRowMapper<Gift>(Gift.class), gid);

			for (Gift gift : lis) {
				ret = gift.getName();
			}
			
			return ret;

		
	}
}
