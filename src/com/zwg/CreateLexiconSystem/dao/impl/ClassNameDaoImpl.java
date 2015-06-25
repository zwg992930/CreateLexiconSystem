package com.zwg.CreateLexiconSystem.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zwg2.CreateLexiconSystem.dao.ClassNameDao;
import com.zwg2.CreateLexiconSystem.model.ClassName;

@Repository
public class ClassNameDaoImpl implements ClassNameDao {
	@Autowired
	SessionFactory sf;

	@Override
	public List<String> getAttributesByClassName(String classname) {
		List<String> list = new ArrayList<String>();
		String hql = "from ClassName where classname = :classname";
		Query qu = sf.getCurrentSession().createQuery(hql);
		qu.setString("classname", classname);
		if (qu.list().size() >= 1) {
			ClassName classname2 = (ClassName) qu.list().get(0);
			list.add(classname2.getAttribute1());
			list.add(classname2.getAttribute2());
			list.add(classname2.getAttribute3());
			list.add(classname2.getAttribute4());
			list.add(classname2.getAttribute5());
		}
		return list;

	}

	@Override
	public void clearClassName_table() {
		String hql = "delete from ClassName";
		Session session = sf.getCurrentSession();
		Query qu = session.createQuery(hql);
		qu.executeUpdate();

	}

	@Override
	public HashMap<String, ArrayList<String>> getEachAttributesList() {
		ArrayList<String> list = null;
		HashMap<String, ArrayList<String>> maps = new HashMap<String, ArrayList<String>>();
		String className = null;
		String hql = "from ClassName";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<ClassName> classnamelist = (ArrayList<ClassName>) qu.list();

		if (classnamelist.size() >= 1) {
			for (int i = 0; i < classnamelist.size(); i++) {
				ClassName classname2 = classnamelist.get(i);
				className = classname2.getClassname();
				list = new ArrayList<String>();
				list.add(classname2.getAttribute1());
				list.add(classname2.getAttribute2());
				list.add(classname2.getAttribute3());
				list.add(classname2.getAttribute4());
				list.add(classname2.getAttribute5());
				maps.put(className, list);
			}

		}

		return maps;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> classNamelist() {
		String hql = "select classname from ClassName";
		Query qu = sf.getCurrentSession().createQuery(hql);
		return (ArrayList<String>) qu.list();
	}

	@Override
	public void addClassName(ClassName classname) {
		sf.getCurrentSession().save(classname);
	}

	@Override
	public ArrayList<ClassName> query() {
		String hql = "from ClassName";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		ArrayList<ClassName> classnamelist = (ArrayList<ClassName>) qu.list();
		return classnamelist;

	}

}
