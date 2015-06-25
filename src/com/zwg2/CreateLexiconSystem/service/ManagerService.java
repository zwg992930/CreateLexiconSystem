package com.zwg2.CreateLexiconSystem.service;

import com.zwg2.CreateLexiconSystem.model.Manager;



public interface ManagerService {
	/**
	 * g管理员登录
	 * @param mname
	 * @param password
	 * @return
	 */
	public Manager login(String mname, String mpassword);
	public void clearDatabaseDelRoot(String projectpath);
	public void deleteDir(String filepath);
	

}
