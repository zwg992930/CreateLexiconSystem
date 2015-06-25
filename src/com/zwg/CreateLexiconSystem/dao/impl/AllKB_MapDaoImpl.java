package com.zwg.CreateLexiconSystem.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zwg2.CreateLexiconSystem.dao.AllKB_MapDao;
import com.zwg2.CreateLexiconSystem.model.All_KB;
import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;

@Repository
public class AllKB_MapDaoImpl implements AllKB_MapDao {
	@Autowired
	SessionFactory sf;

	@Override
	public List<Integer> getAllKB_intvalue() {
		String kbbds = "";
		List<Integer> list = new ArrayList<Integer>();
		String hql = "select KB from All_KB";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<String> kblist = (ArrayList<String>) qu.list();
		for (int i = 0; i < kblist.size(); i++) {
			kbbds = kblist.get(i);
			if (kbbds == "BBD000768") {
				continue;
			}
			list.add(Integer.parseInt(kbbds.substring(
					kbbds.lastIndexOf("-") + 1).substring(4)));
		}
		return list;
	}

	@Override
	public Map<String, String> getAllKBAndItsAttributes() {
		Map<String, String> map = new HashMap<String, String>();
		String hql = "from All_KB";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<All_KB> all_kblist = (ArrayList<All_KB>) qu.list();
		if (all_kblist.size() >= 1) {
			for (int i = 0; i < all_kblist.size(); i++) {
				map.put(all_kblist.get(i).getKB(), all_kblist.get(i)
						.getClassname());
			}

		}
		return map;
	}

	@Override
	public void add_AllKB(Map<String, String> map) {
		List<Map.Entry<String, String>> info = new ArrayList<Map.Entry<String, String>>(
				map.entrySet());
		for (int i = 0; i < info.size(); i++) {
			All_KB all_kb = new All_KB(info.get(i).getKey(), info.get(i)
					.getValue());
			sf.getCurrentSession().save(all_kb);
		}
	}

	@Override
	public boolean add_KB(ThisKB_Belong thiskb_belong) {
		boolean boo = false;
		All_KB allkb = new All_KB(thiskb_belong.getKB(),
				thiskb_belong.getClassName());
		sf.getCurrentSession().save(allkb);
		boo = true;
		return boo;
	}

	@Override
	public void ClearAll_kb() {
		String hql = "delete from All_KB";
		Session session = sf.getCurrentSession();
		Query qu = session.createQuery(hql);
		qu.executeUpdate();
	}
}
