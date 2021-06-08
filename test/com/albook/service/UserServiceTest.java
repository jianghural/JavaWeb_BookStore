package com.albook.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.albook.pojo.User;

class UserServiceTest {
	UserService uService;
	@BeforeEach
	void setUp() throws Exception {
		uService = new UserService();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRegistUser() {
		uService.registUser(new User(null, "bbj168", "666666", "bbj168@qq.com"));
	}

	@Test
	void testLoginUser() {
		System.out.println(uService.loginUser(new User(null, "bbj168", "666666", "bbj168@qq.com")));
	}

	@Test
	void testExistsUsername() {
		if (uService.existsUsername("bbj169")) {
			System.out.println("用户名已存在！");
		} else {
			System.out.println("用户名可用！");
		}
	}

}
