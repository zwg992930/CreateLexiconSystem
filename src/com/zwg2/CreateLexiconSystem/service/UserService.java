package com.zwg2.CreateLexiconSystem.service;

import java.util.List;

import com.zwg2.CreateLexiconSystem.model.User;
import com.zwg2.CreateLexiconSystem.util.WwjException;

/**
 * 用户业务服务类
 * 
 * @author 王伟杰
 */

public interface UserService {
	/** 新增用户 */
	public User register(User user) throws WwjException;

	/** 删除用户 */
	public void remove(User user);

	/** 通过电话号码删除用户 */
	public boolean deluserbyuid(int uid);

	/** 查找用户 */
	public User fingUserByUid(int uid);

	/** 修改用户 */
	public boolean modify(User user);

	/** 查询所有用户 */
	public List<User> getPersons();

	/** 用户登陆 */
	public User login(String name, String password);

	// 注册时检测用户信息
	public boolean checkUserName(String username);

	public boolean checkEmail(String email);

	public boolean checkPhone(String phone);

	public boolean checkQQ(String QQ);

	// 修改用户信息时检测用户信息
	public boolean checkEmailMod(int cur_user_uid, String email);

	public boolean checkPhoneMod(int cur_user_uid, String phone);

	public boolean checkQQMod(int cur_user_uid, String QQ);

	/** 分页显示所有用户 */
	public List<User> getPersons(int start, int end);

	public List<User> getAllUser();

	public int getCount();

}
