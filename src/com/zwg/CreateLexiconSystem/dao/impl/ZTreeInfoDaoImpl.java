package com.zwg.CreateLexiconSystem.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zwg2.CreateLexiconSystem.dao.ZTreeInfoDao;
import com.zwg2.CreateLexiconSystem.model.ZTreeInfo;

@Repository
public class ZTreeInfoDaoImpl implements ZTreeInfoDao {
	@Autowired
	SessionFactory sf;

	public boolean add(ZTreeInfo ztreeinfo) {
		boolean boo = false;
		sf.getCurrentSession().save(ztreeinfo);
		boo = true;
		return boo;
	}

	public void deleteZtreeInfoById(int id) {
		ZTreeInfo ztreeinfo = null;
		Session session = sf.getCurrentSession();
		ztreeinfo = (ZTreeInfo) session.get(ZTreeInfo.class, id);
		if (null != ztreeinfo) {
			session.delete(ztreeinfo);
		}
	}

	public void clearZtreeInfos() {
		String hql = "delete from ZTreeInfo";
		Session session = sf.getCurrentSession();
		Query qu = session.createQuery(hql);
		qu.executeUpdate();
	}

	public List<ZTreeInfo> getZtreeInfoByLevel(int level) {
		String hql = "from ZTreeInfo where level = :level";
		Query qu = sf.getCurrentSession().createQuery(hql);
		qu.setInteger("level", level);
		@SuppressWarnings("unchecked")
		List<ZTreeInfo> ztreeinfolist = (ArrayList<ZTreeInfo>) qu.list();
		return ztreeinfolist;
	}

	public List<ZTreeInfo> getZtreeInfoBypId(int pid) {
		String hql = "from ZTreeInfo where pid = :pid";
		Query qu = sf.getCurrentSession().createQuery(hql);
		qu.setInteger("pid", pid);
		@SuppressWarnings("unchecked")
		List<ZTreeInfo> ztreeinfolist = (ArrayList<ZTreeInfo>) qu.list();
		return ztreeinfolist;
	}

	public List<ZTreeInfo> getAllZtreeInfos() {
		String hql = "from ZTreeInfo";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<ZTreeInfo> ztreeinfolist = (ArrayList<ZTreeInfo>) qu.list();
		return ztreeinfolist;
	}

	public int getMaxZtreeID() {
		String hql = "select max(id) from ZTreeInfo";
		Query qu = sf.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> idlist = (ArrayList<Integer>) qu.list();
		return idlist.get(0);
	}

	public ZTreeInfo getZtreeInfoByClassPath(String classname) {
		String hql = "from ZTreeInfo where filepath = :classname";
		Query qu = sf.getCurrentSession().createQuery(hql);
		qu.setString("classname", classname);
		@SuppressWarnings("unchecked")
		List<ZTreeInfo> ztreeinfolist = (ArrayList<ZTreeInfo>) qu.list();
		ZTreeInfo ztreeinfo = null;
		if (ztreeinfolist.size() >= 1) {
			ztreeinfo = ztreeinfolist.get(0);
		}
		return ztreeinfo;
	}

}
