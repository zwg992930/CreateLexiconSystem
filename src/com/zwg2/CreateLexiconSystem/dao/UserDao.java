package com.zwg2.CreateLexiconSystem.dao;

import java.util.List;

import com.zwg2.CreateLexiconSystem.model.User;

/**
 * 用户访问数据对象接口
 * 
 * @author 王伟杰
 */

public interface UserDao {
	/** 用户注册 */
	public User save(User user);

	/** 删除用户 */
	public void remove(User user);

	/** 用户注册 */
	public boolean removeByUid(int uid);

	/** 修改用户 */
	public boolean update(User user);

	/** 查询所有用户 */
	public List<User> findall();

	/** 根据用户名查找用户 */
	public User findbyUid(int uid);

	/** 用户登陆 */
	public User login(String name, String password);

	/** 检测用户名是否存在,存在返回true，否则返回false */
	public boolean checkUserName(String username);

	/** 分页显示所有用户 */
	public List<User> getPersons(int start, int end);

	public boolean checkEmail(String email);

	public boolean checkPhone(String phone);

	public boolean checkQQ(String QQ);

	// 修改用户信息时检测用户信息
	public boolean checkEmailMod(int cur_user_uid, String email);

	public boolean checkPhoneMod(int cur_user_uid, String phone);

	public boolean checkQQMod(int cur_user_uid, String QQ);

}
