package com.zwg2.CreateLexiconSystem.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import com.zwg2.CreateLexiconSystem.dao.ManagerDao;
import com.zwg2.CreateLexiconSystem.model.Manager;
import com.zwg2.CreateLexiconSystem.util.HibernateUtil;

public class ManagerDaoImpl implements ManagerDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	// 注入hibernateUtil工具类
	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public Manager login(String mname, String mpassword) {
		Manager m = null;
		String hql = "from Manager where mname='" + mname + "' and mpassword='"
				+ mpassword + "'";
		@SuppressWarnings("unchecked")
		List<Manager> list = (List<Manager>)hibernateUtil.exeQuery(hql);
		if(list.size() >=1){
			m = list.get(0);
		}
		return m;
	}

}
