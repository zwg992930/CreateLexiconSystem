package com.zwg2.CreateLexiconSystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import com.zwg2.CreateLexiconSystem.dao.UserDao;
import com.zwg2.CreateLexiconSystem.model.User;
import com.zwg2.CreateLexiconSystem.service.UserService;
import com.zwg2.CreateLexiconSystem.util.WwjException;

public class UserServiceImpl implements UserService {
	UserDao userDao;
	/*
	 * 
	 * 可以考虑针对玉哲的代码新建一些包，引用的时候同时引用两者，从而避免扫描重复
	 * */
    
	public UserDao getUserDao() {
		return userDao;
	}
	//注入userDao层
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void remove(User user) {
		userDao.remove(user);
		
	}
	
	
	@Override
	public boolean deluserbyuid(int uid) {
		return userDao.removeByUid(uid);
	}
	public User register(User user) throws WwjException {
		User u = null;
		if(!checkUserName(user.getName())){
			 u=userDao.save(user);
		}
		else{
			throw new  WwjException("用户名已经存在");
		}
		return u;
	}

	public boolean modify(User user) {
		return userDao.update(user);
	}

	public List<User> getPersons() {
		return userDao.findall();
	}

	public User login(String name, String password) {
		return userDao.login(name, password);
	}

	public boolean checkUserName(String username){
		return userDao.checkUserName(username); 
	}
	public List<User> getPersons(int start, int end) {
		return userDao.getPersons(start, end);
	}
	public int getCount() {
		return this.getPersons().size();
	}
	
	@Override
	public User fingUserByUid(int uid) {
		return userDao.findbyUid(uid);
	}
	@Override
	public boolean checkEmail(String email) {
		return userDao.checkEmail(email);
	}
	@Override
	public boolean checkPhone(String phone) {
		return userDao.checkPhone(phone);
	}
	@Override
	public boolean checkQQ(String QQ) {
		return userDao.checkQQ(QQ);
	}
	
	
	
	@Override
	public boolean checkEmailMod(int cur_user_uid, String email) {
		return userDao.checkEmailMod(cur_user_uid, email);
	}
	@Override
	public boolean checkPhoneMod(int cur_user_uid, String phone) {
		return userDao.checkPhoneMod(cur_user_uid, phone);
	}
	@Override
	public boolean checkQQMod(int cur_user_uid, String QQ) {
		return userDao.checkQQMod(cur_user_uid, QQ);

	}
	@Override
	public List<User> getAllUser() {
		List<User> list = userDao.findall();
		for(int i=0;i<list.size();i++){
			list.get(i).setRegDateshow(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(list.get(i).getRegDate()));
		}
		return list;
	}
	
	
}
