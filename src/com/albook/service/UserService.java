package com.albook.service;

import com.albook.dao.UserDao;
import com.albook.pojo.User;

public class UserService {
	
	UserDao uDao = new UserDao();
	
	/**
	 * 注册用户
	 * @param user
	 */
	public void registUser(User user) {
		uDao.saveUser(user);
	}
	
	/**
	 * 用户登录
	 * @param user
	 * @return 如果返回null,说明登录失败，返回有值，登陆成功
	 */
	public User loginUser(User user) {
		return uDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	/**
	 * 检查 用户名是否可用
	 * @param username
	 * @return 返回true表示用户名已存在，返回false表示用户名可用
	 */
	public boolean existsUsername(String username) {
		if (uDao.queryUserByUsername(username) == null) {
			// 等于null，说明没查到，没查到表示可用
			return false;
		}
		return true;
	}
}
