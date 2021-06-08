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
		// 获取请求的参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		UserService uService = new UserService();
		
		// 检查验证码是否正确 写死，要求验证码为：aaaaaa
		if ("aaaaaa".equalsIgnoreCase(code)) {
			// 检查 用户名是否可用
			if (uService.existsUsername(username)) {
				System.out.println("用户名[" + username + "]已存在!");
				
				// 把回显信息，保存到Request域中
                request.setAttribute("msg", "用户名已存在！！");
                request.setAttribute("username", username);
                request.setAttribute("email", email);
                
				// 跳回到注册页面
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			} else {
				// 可用，调用 service 保存到数据库
				uService.registUser(new User(null, username, password, email));
				// 跳转到注册成功页面
				request.getRequestDispatcher("/pages/user/regist_success.jsp").forward(request, response);
			}
		} else {
			// 把回显信息，保存到Request域中
            request.setAttribute("msg", "验证码错误！！");
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            
			System.out.println("验证码[" + code + "]错误");
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
