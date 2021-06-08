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
		// 获取请求的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 调用 UserService 处理业务
		UserService uService = new UserService();
		
		User logingUser = uService.loginUser(new User(null, username, password, null));
		// 如果=null,说明登录 失败
		if (logingUser == null) {
			// 把错误信息，和回显的表单项信息，保存到 request 域中
			request.setAttribute("msg", "用户名或密码错误");
			request.setAttribute("username", username);
			
			// 调回登录页面
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		} else {
			// 登录成功，跳转到成功页面
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
