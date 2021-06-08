package com.albook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albook.pojo.Book;
import com.albook.pojo.Page;
import com.albook.service.BookService;
import com.albook.utils.WebUtils;

public class ClientBookServlet extends BaseServlet {

	BookService bookService = new BookService();
	
	
    /**
     * ����۸������ҳ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 ��ȡ����Ĳ��� pageNo �� pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2 ����BookService.page(pageNo��pageSize)��Page����
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");
        // �������С�۸�Ĳ���,׷�ӵ���ҳ���ĵ�ַ������
        if (req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        // ��������۸�Ĳ���,׷�ӵ���ҳ���ĵ�ַ������
        if (req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3 ����Page����Request����
        req.setAttribute("page",page);
        //4 ����ת����pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }	
	
	
	
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

        // ���÷�ҳ�����ֵ������ַ
        page.setUrl("client/bookServlet?action=page");

        //3 ����Page����Request����
        req.setAttribute("page",page);
        //4 ����ת����pages/manager/book_manager.jspҳ��
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }	
	
}
