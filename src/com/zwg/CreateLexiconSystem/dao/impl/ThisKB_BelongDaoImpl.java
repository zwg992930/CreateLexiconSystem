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

import com.zwg2.CreateLexiconSystem.dao.ThisKB_BelongDao;
import com.zwg2.CreateLexiconSystem.model.All_KB;
import com.zwg2.CreateLexiconSystem.model.ThisKB_Belong;

@Repository
public class ThisKB_BelongDaoImpl implements ThisKB_BelongDao {

	@Autowired
	SessionFactory sf;

	@Override
	public Map<String, String> getAllKBAndItsAttributes() {
		Map<String, String> map = new HashMap<String, String>();
		String hql = "from ThisKB_Belong";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<ThisKB_Belong> thiskb_belonglist = (ArrayList<ThisKB_Belong>) qu
				.list();

		if (thiskb_belonglist.size() >= 1) {
			for (int i = 0; i < thiskb_belonglist.size(); i++) {
				map.put(thiskb_belonglist.get(i).getKB(), thiskb_belonglist
						.get(i).getClassName());
			}
		}

		return map;
	}

	@Override
	public Map<String, String> getAllKBAndItsAttributes2() {
		Map<String, String> map = new HashMap<String, String>();
		String hql = "from all_kb";
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
	public String getAttributesByKB(String KB) {
		String classname = "";
		String hql = "from ThisKB_Belong where KB = :KB";
		Query qu = sf.getCurrentSession().createQuery(hql);
		qu.setString("KB", KB);
		@SuppressWarnings("unchecked")
		ArrayList<ThisKB_Belong> thiskb_belonglist = (ArrayList<ThisKB_Belong>) qu
				.list();
		if (thiskb_belonglist.size() >= 1) {
			classname = thiskb_belonglist.get(0).getClassName();
		}
		return classname;
	}

	@Override
	public List<String> KBlist() {
		List<String> list = new ArrayList<String>();
		String hql = "select KB from ThisKB_Belong";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<String> thiskb_belonglist = (ArrayList<String>) qu.list();
		if (thiskb_belonglist.size() >= 1) {
			for (int i = 0; i < thiskb_belonglist.size(); i++) {
				list.add(thiskb_belonglist.get(i));
			}
		}
		return list;
	}

	@Override
	public boolean addKB(ThisKB_Belong thiskb_Belong) {
		boolean b = false;
		sf.getCurrentSession().save(thiskb_Belong);
		b = true;
		return b;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ThisKB_Belong> query() {
		String hql = "from KB_Belong";
		Query qu = sf.getCurrentSession().createQuery(hql);

		return (ArrayList<ThisKB_Belong>) qu.list();
	}

	@Override
	public void clearKB_Belong() {
		String hql = "delete from ThisKB_Belong";
		Session session = sf.getCurrentSession();
		Query qu = session.createQuery(hql);
		qu.executeUpdate();

	}
}
