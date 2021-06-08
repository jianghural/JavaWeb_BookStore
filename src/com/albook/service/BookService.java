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
     * �����ҳҵ��
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();

        // ����ÿҳ��ʾ������
        page.setPageSize(pageSize);
        // ���ܼ�¼��
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        // �����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);
        // ����ҳ��
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // ������ҳ��
        page.setPageTotal(pageTotal);

        
        
        // ���õ�ǰҳ��
        page.setPageNo(pageNo);

        // ��ǰҳ���ݵĿ�ʼ����
        int begin = (page.getPageNo() - 1) * pageSize;
        // ��ǰҳ����
        List<Book> items = bookDao.queryForPageItems(begin,pageSize);
        // ���õ�ǰҳ����
        page.setItems(items);

        return page;
    }
    
    
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max){
        Page<Book> page = new Page<Book>();

        // ����ÿҳ��ʾ������
        page.setPageSize(pageSize);
        // ���ܼ�¼��
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        // �����ܼ�¼��
        page.setPageTotalCount(pageTotalCount);
        // ����ҳ��
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        // ������ҳ��
        page.setPageTotal(pageTotal);

        // ���õ�ǰҳ��
        page.setPageNo(pageNo);

        // ��ǰҳ���ݵĿ�ʼ����
        int begin = (page.getPageNo() - 1) * pageSize;
        // ��ǰҳ����
        List<Book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        // ���õ�ǰҳ����
        page.setItems(items);

        return page;
    }
}
