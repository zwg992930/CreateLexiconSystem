package com.zwg2.CreateLexiconSystem.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zwg2.CreateLexiconSystem.dao.UserDao;
import com.zwg2.CreateLexiconSystem.model.User;
import com.zwg2.CreateLexiconSystem.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	HibernateUtil hibernateUtil;

	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	// 注入hibernateUtil工具类
	@Resource
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	public User save(User user) {
		User u = null;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			//查看是否重复
			session.save(user);
			u = user;
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}
		return u;
	}

	@Override
	public void remove(User user) {
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hibernateUtil.closeSession(session);
		}

	}
	
	

	@Override
	public boolean removeByUid(int uid) {
		Transaction transaction = null;
		Session session = null;
		User user = null;
		boolean boo = false;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			user = (User) session.get(User.class, uid);
			session.delete(user);
			transaction.commit();
			boo = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			hibernateUtil.closeSession(session);
		}
		
		return boo;
		
	}

	public boolean update(User user) {
		boolean flag = false;
		Transaction transaction = null;
		Session session = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			User u = (User) session.get(User.class, user.getUid());
			u.setName(user.getName());
			u.setPassword(user.getPassword());
			u.setSex(user.getSex());
			u.setAddr(user.getAddr());
			u.setEmail(user.getEmail());
			u.setPhone(user.getPhone());
			u.setQQ(user.getQQ());
			transaction.commit();
			flag = true;
		} catch (HibernateException e) {
			flag = false;
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();

		} finally {
			hibernateUtil.closeSession(session);
		}
		return flag;
	}

	@SuppressWarnings("unchecked")
	public List<User> findall() {
		String hql = "from User";
		return (List<User>)hibernateUtil.exeQuery(hql);
	}

	public User login(String name, String password) {
		// TODO Auto-generated method stub
		User u = null;
		String hql = "from User where name='" + name + "' and password='"
				+ password + "'";
		u = (User) hibernateUtil.exeQuery(hql).get(0);
		return u;
	}

	public boolean checkUserName(String username) {
		String hql = "from User where name='" + username + "'";
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<User> getPersons(int start, int end) {
		// TODO Auto-generated method stub
		String hql = "from User";
		return (List<User>)hibernateUtil.exeQueryPage(hql, start, end);
	}

	@Override
	public User findbyUid(int uid) {
		Transaction transaction = null;
		Session session = null;
		User user = null;
		try {
			session = hibernateUtil.getSession();
			transaction = session.beginTransaction();
			user = (User) session.get(User.class, uid);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			hibernateUtil.rollbackTransaction(transaction);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			hibernateUtil.closeSession(session);
		}

		return user;
	}

	@Override
	public boolean checkEmail(String email) {

		String hql = "from User where email='" + email + "'";
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean checkPhone(String phone) {
		String hql = "from User where phone='" + phone + "'";
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkQQ(String QQ) {
		String hql = "from User where QQ='" + QQ + "'";
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean checkEmailMod(int cur_user_uid, String email) {

		String hql = "from User where email='" + email + "' and uid != "+cur_user_uid;
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return false;
		}
		return true;

	}

	@Override
	public boolean checkPhoneMod(int cur_user_uid, String phone) {
		String hql = "from User where phone='" + phone + "' and uid != "+cur_user_uid;
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean checkQQMod(int cur_user_uid, String QQ) {
		String hql = "from User where QQ='" + QQ + "' and uid != "+cur_user_uid;
		if (hibernateUtil.exeQuery(hql).size() > 0) {
			return false;
		}
		return true;

	}
	

}
