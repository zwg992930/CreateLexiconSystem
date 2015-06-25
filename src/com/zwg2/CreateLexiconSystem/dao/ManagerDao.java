package com.zwg2.CreateLexiconSystem.dao;

import com.zwg2.CreateLexiconSystem.model.Manager;

/**
 * @author 王伟杰
 */

public interface ManagerDao {
	/** 管理员 */
	public Manager login(String mname, String password);

}
