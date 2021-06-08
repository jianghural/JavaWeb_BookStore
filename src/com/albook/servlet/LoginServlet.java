package com.albook.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albook.pojo.User;
import com.albook.service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ����Ĳ���
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// ���� UserService ����ҵ��
		UserService uService = new UserService();
		
		User logingUser = uService.loginUser(new User(null, username, password, null));
		// ���=null,˵����¼ ʧ��
		if (logingUser == null) {
			// �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽 request ����
			request.setAttribute("msg", "�û������������");
			request.setAttribute("username", username);
			
			// ���ص�¼ҳ��
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else {
			// ��¼�ɹ�����ת���ɹ�ҳ��
			request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
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
