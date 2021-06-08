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
			System.out.println("�û������ã�");
		} else {
			System.out.println("�û����Ѵ��ڣ�");
		}
	}

	@Test
	void testQueryUserByUsernameAndPassword() {
		if ( dao.queryUserByUsernameAndPassword("admin","admin") == null) {
			System.out.println("�û�����������󣬵�¼ʧ��");
		} else {
			System.out.println("��ѯ�ɹ�");
		}
	}

	@Test
	void testSaveUser() {
		System.out.println( dao.saveUser(new User(null,"awl295", "123456", "wzg168@qq.com")) );
	}

}
