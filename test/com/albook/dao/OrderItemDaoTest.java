package com.albook.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.albook.pojo.OrderItem;

class OrderItemDaoTest {
	OrderItemDao dao;
	@BeforeEach
	void setUp() throws Exception {
		dao = new OrderItemDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSaveOrderItem() {
		dao.saveOrderItem(new OrderItem(1, "123", 2, new BigDecimal(34), new BigDecimal(34), "24234ksdjfisdf"));
	}

	@Test
	void testQueryOrderItemByOrderId() {
		
		System.out.println(dao.queryOrderItemByOrderId("24234ksdjfisdf"));
	}
	
	
	@Test
	void test() {
		Date date = new Date();
		//日期格式，精确到日 2017-4-16
		DateFormat df1 = DateFormat.getDateInstance();
		System.out.println(df1.format(date));
		System.out.println(System.currentTimeMillis());
	}

}
