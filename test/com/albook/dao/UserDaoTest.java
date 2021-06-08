package com.albook.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.albook.pojo.User;

class UserDaoTest {
	UserDao dao;
	@BeforeEach
	void setUp() throws Exception {
		dao = new UserDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSave() {
		User user = new User("mayun", "123", "2343432@qq.com");
		dao.save(user);
	}

	@Test
	void testDelete() {
		dao.delete(2);
	}

	@Test
	void testUpdate() {
		User user = new User(6, "xiugai", "456", "2343432@qq.com");
		dao.update(user);
	}

	@Test
	void testGetUsers() {
		List<User> list = new ArrayList<User>();
		list = dao.getUsers(1, 2);
		System.out.println(list.toString());
	}

}
