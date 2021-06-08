package com.albook.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnTest {

	private  static Connection  con;
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetConnection() {
		Conn.getConnection();
	}

	@Test
	void testClose() {
		con = Conn.getConnection();
		Conn.close(con);
	}

}
