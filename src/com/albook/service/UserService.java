package com.albook.service;

import com.albook.dao.UserDao;
import com.albook.pojo.User;

public class UserService {
	
	UserDao uDao = new UserDao();
	
	/**
	 * ע���û�
	 * @param user
	 */
	public void registUser(User user) {
		uDao.saveUser(user);
	}
	
	/**
	 * �û���¼
	 * @param user
	 * @return �������null,˵����¼ʧ�ܣ�������ֵ����½�ɹ�
	 */
	public User loginUser(User user) {
		return uDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	/**
	 * ��� �û����Ƿ����
	 * @param username
	 * @return ����true��ʾ�û����Ѵ��ڣ�����false��ʾ�û�������
	 */
	public boolean existsUsername(String username) {
		if (uDao.queryUserByUsername(username) == null) {
			// ����null��˵��û�鵽��û�鵽��ʾ����
			return false;
		}
		return true;
	}
}
