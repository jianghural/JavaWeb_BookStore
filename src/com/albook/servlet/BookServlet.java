package com.albook.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albook.pojo.Book;
import com.albook.pojo.Page;
import com.albook.service.BookService;
import com.albook.utils.WebUtils;

public class BookServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	BookService bookService = new BookService();
	
	
	
	
	
    /**
     * �����ҳ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 ��ȡ����Ĳ��� pageNo �� pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 ����BookService.page(pageNo��pageSize)��Page����
        Page<Book> page = bookService.page(pageNo,pageSize);

        
        page.setUrl("manager/bookServlet?action=page");

        //3 ����Page����Request����
        req.setAttribute("page",page);
        //4 ����ת����pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }	
	
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
		pageNo+=1;
		 //        1����ȡ����Ĳ���==��װ��ΪBook����
        Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
//        2������BookService.addBook()����ͼ��
        bookService.addBook(book);
//        3������ͼ���б�ҳ��
//                /manager/bookServlet?action=list
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        System.out.println("request.getContextPath():::"+request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
	}
	
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      1����ȡ����Ĳ���id��ͼ����
      int id = WebUtils.parseInt(request.getParameter("id"), 0);
//      2������bookService.deleteBookById();ɾ��ͼ��
      bookService.deleteBookById(id);
//      3���ض����ͼ���б����ҳ��
//              /book/manager/bookServlet?action=list
      response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}
	
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      1����ȡ����Ĳ���==��װ��ΪBook����
      Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
//      2������BookService.updateBook( book );�޸�ͼ��
      bookService.updateBook(book);
//      3���ض����ͼ���б����ҳ��
//      ��ַ��/������/manager/bookServlet?action=list
      response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));		
	}
	
	
	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ��ȡ����Ĳ���ͼ����
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2 ����bookService.queryBookById��ѯͼ��
        Book book = bookService.queryBookById(id);
        //3 ���浽ͼ�鵽Request����
        request.setAttribute("book", book) ;
        //4 ����ת������pages/manager/book_edit.jspҳ��
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 ͨ��BookService��ѯȫ��ͼ��
        List<Book> books = bookService.queryBooks();
        //2 ��ȫ��ͼ�鱣�浽Request����
        request.setAttribute("books", books);
        System.out.println("servlet:--"+books);
        //3������ת����/pages/manager/book_manager.jspҳ��
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * �����Ż� else-if ����
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
							
		try {
			// ��ȡactionҵ������ַ�������ȡ��Ӧ��ҵ�� �����������
			Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			
			System.out.println(method);
			
			// ����Ŀ��ҵ�񷽷�
			method.invoke(this,request,response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
