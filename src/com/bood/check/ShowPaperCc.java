package com.bood.check;

import java.util.List;
import java.util.Vector;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.bood.form.Paper;

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
			v.add(paper.getGid());

			ret.add(v);
		}

		return ret;
	}
}
