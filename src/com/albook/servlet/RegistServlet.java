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
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		UserService uService = new UserService();
		
		// �����֤���Ƿ���ȷ д����Ҫ����֤��Ϊ��aaaaaa
		if ("aaaaaa".equalsIgnoreCase(code)) {
			// ��� �û����Ƿ����
			if (uService.existsUsername(username)) {
				System.out.println("�û���[" + username + "]�Ѵ���!");
				
				// �ѻ�����Ϣ�����浽Request����
                request.setAttribute("msg", "�û����Ѵ��ڣ���");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                
				// ���ص�ע��ҳ��
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			} else {
				// ���ã����� service ���浽���ݿ�
				uService.registUser(new User(null, username, password, email));
				// ��ת��ע��ɹ�ҳ��
				request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
			}
		} else {
			// �ѻ�����Ϣ�����浽Request����
            request.setAttribute("msg", "��֤����󣡣�");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            
			System.out.println("��֤��[" + code + "]����");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
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
