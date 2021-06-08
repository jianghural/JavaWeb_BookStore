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
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数 pageNo 和 pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2 调用BookService.page(pageNo，pageSize)：Page对象
        Page<Book> page = bookService.page(pageNo,pageSize);

        
        page.setUrl("manager/bookServlet?action=page");

        //3 保存Page对象到Request域中
        req.setAttribute("page",page);
        //4 请求转发到pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }	
	
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNo = WebUtils.parseInt(request.getParameter("pageNo"), 0);
		pageNo+=1;
		 //        1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
//        2、调用BookService.addBook()保存图书
        bookService.addBook(book);
//        3、跳到图书列表页面
//                /manager/bookServlet?action=list
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        System.out.println("request.getContextPath():::"+request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
	}
	
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      1、获取请求的参数id，图书编程
      int id = WebUtils.parseInt(request.getParameter("id"), 0);
//      2、调用bookService.deleteBookById();删除图书
      bookService.deleteBookById(id);
//      3、重定向回图书列表管理页面
//              /book/manager/bookServlet?action=list
      response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));
	}
	
	
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      1、获取请求的参数==封装成为Book对象
      Book book = WebUtils.copyParamToBean(request.getParameterMap(),new Book());
//      2、调用BookService.updateBook( book );修改图书
      bookService.updateBook(book);
//      3、重定向回图书列表管理页面
//      地址：/工程名/manager/bookServlet?action=list
      response.sendRedirect(request.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + request.getParameter("pageNo"));		
	}
	
	
	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 获取请求的参数图书编号
        int id = WebUtils.parseInt(request.getParameter("id"), 0);
        //2 调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到Request域中
        request.setAttribute("book", book) ;
        //4 请求转发到。pages/manager/book_edit.jsp页面
        request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request,response);
	}
	
	protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        request.setAttribute("books", books);
        System.out.println("servlet:--"+books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
	}
	
	/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 反射优化 else-if 代码
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
							
		try {
			// 获取action业务鉴别字符串，获取相应的业务 方法反射对象
			Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
			
			System.out.println(method);
			
			// 调用目标业务方法
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
