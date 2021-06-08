package com.albook.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.albook.pojo.User;

class UserDaoTest2 {
	UserDao dao;
	@BeforeEach
	void setUp() throws Exception {
		dao = new UserDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testQueryUserByUsername() {
		if (dao.queryUserByUsername("admin") == null ){
			System.out.println("用户名可用！");
		} else {
			System.out.println("用户名已存在！");
		}
	}

	@Test
	void testQueryUserByUsernameAndPassword() {
		if ( dao.queryUserByUsernameAndPassword("admin","admin") == null) {
			System.out.println("用户名或密码错误，登录失败");
		} else {
			System.out.println("查询成功");
		}
	}

	@Test
	void testSaveUser() {
		System.out.println( dao.saveUser(new User(null,"awl295", "123456", "wzg168@qq.com")) );
	}

}
