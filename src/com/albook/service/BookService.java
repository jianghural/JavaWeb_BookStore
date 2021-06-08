package com.albook.service;

import java.util.List;

import com.albook.dao.BookDao;
import com.albook.pojo.Book;
import com.albook.pojo.Page;

public class BookService {
	
	BookDao bookDao = new BookDao();
	

    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
    
    /**
     * 处理分页业务
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        
        
        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
    
    
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max){
        Page<Book> page = new Page<Book>();

        // 设置每页显示的数量
        page.setPageSize(pageSize);
        // 求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // 设置总记录数
        page.setPageTotalCount(pageTotalCount);
        // 求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // 设置总页码
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        // 求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        // 求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // 设置当前页数据
        page.setItems(items);

        return page;
    }
}
