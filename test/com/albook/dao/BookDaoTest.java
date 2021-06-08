package com.albook.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.albook.pojo.Book;

class BookDaoTest {
	BookDao dao;
	@BeforeEach
	void setUp() throws Exception {
		dao = new BookDao();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAddBook() {
		System.out.println(dao.addBook(new Book(null, "english", "tom", 22, 22, 22, "static/img/default.jpg")));
	}

	@Test
	void testDeleteBookById() {
		System.out.println(dao.deleteBookById(21));
	}

	@Test
	void testUpdateBook() {
		System.out.println(dao.updateBook(new Book(22, "math", "jike", 22, 22, 22, "static/img/default.jpg")));
	}

	@Test
	void testQueryBookById() {
		System.out.println(dao.queryBookById(22));
	}

	@Test
	void testQueryBooks() {
		System.out.println(dao.queryBooks());
	}
	
	@Test
	void testQueryForPageTotalCount() {
		System.out.println(dao.queryForPageTotalCount());
	}
	
	@Test
	void testQueryForPageItems() {
		System.out.println(dao.queryForPageItems(1, 4));
	}
	
	@Test
	void testQueryForPageItemsByPrice() {
		System.out.println(dao.queryForPageItemsByPrice(1, 4, 10, 80));
	}
	
	@Test
	void testQueryForPageTotalCountByPrice() {
		System.out.println(dao.queryForPageTotalCountByPrice(100, 1000));
	}

}
