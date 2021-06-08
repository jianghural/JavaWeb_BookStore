package com.albook.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.albook.pojo.Order;

class OrderDaoTest {
	OrderDao dao;
	@BeforeEach
	void setUp() throws Exception {
		dao = new OrderDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSaveOrder() {
		int flag1 = dao.saveOrder(new Order("24334ksdjfisdf", new java.sql.Date(new java.util.Date().getTime()), new BigDecimal(100), 0, 2));
		int flag2 = dao.saveOrder(new Order("34233ksdjfisdf", new java.sql.Date(new java.util.Date().getTime()), new BigDecimal(100), 0, 2));
		System.out.println(flag1 + "\t" + flag2);
	}

	@Test
	void testQueryOrder() {
		System.out.println(dao.queryOrder().toString());
	}

	@Test
	void testChangeOrderStatus() {
		System.out.println(dao.changeOrderStatus("24234ksdjfisdf", 1));
	}

	@Test
	void testQueryOrdersByUserId() {
		System.out.println(dao.queryOrdersByUserId(2));
	}

}
