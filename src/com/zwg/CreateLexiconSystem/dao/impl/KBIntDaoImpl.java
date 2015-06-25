package com.zwg.CreateLexiconSystem.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zwg2.CreateLexiconSystem.dao.KBIntDao;
import com.zwg2.CreateLexiconSystem.model.KB;

@Repository
public class KBIntDaoImpl implements KBIntDao {
	@Autowired
	SessionFactory sf;

	@Override
	public boolean add(int intvalue) {
		boolean boo = false;
		KB kb = new KB();
		kb.setKb_intvalue(intvalue);
		Session session = sf.getCurrentSession();
		session.save(kb);
		boo = true;
		return boo;
	}

	@Override
	public boolean add(KB kb) {
		boolean boo = false;
		Session session = sf.getCurrentSession();
		session.save(kb);
		boo = true;
		return boo;

	}

	@Override
	public int getMaxKB_intvalue() {
		String hql = "select max(kb_intvalue) from KB";
		int max_kbintvalue = 0;
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<Integer> intvaluelist = (ArrayList<Integer>) qu.list();
		if (intvaluelist.size() >= 1) {
			max_kbintvalue = intvaluelist.get(0);
		}

		return max_kbintvalue;
	}

	@Override
	public void clearkbsint() {
		String hql = "delete from KB";
		Session session = sf.getCurrentSession();
		Query qu = session.createQuery(hql);
		qu.executeUpdate();
	}

	@Override
	public long getKB_count() {
		String hql = "select count(kb_intvalue) from KB";
		Query qu = sf.getCurrentSession().createQuery(hql);
		return ((Long) qu.iterate().next()).longValue();
	}

}
