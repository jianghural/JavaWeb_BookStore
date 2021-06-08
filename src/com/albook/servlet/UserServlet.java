package com.albook.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.albook.pojo.User;
import com.albook.service.UserService;
import com.albook.utils.WebUtils;
import com.google.code.kaptcha.servlet.KaptchaServlet;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    // 调用 UserService 处理业务
 	UserService uService = new UserService();
    
 	/**
 	 * 注销
 	 * @param request
 	 * @param response
 	 * @throws ServletException
 	 * @throws IOException
 	 */
 	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		/* 销毁 Session 中用户登录的信息（或者销毁 Session ） */
 		request.getSession().invalidate();
 		/* 重定向到首页（或登录页面） */
 		response.sendRedirect(request.getContextPath());
 		System.out.println("注销");
 	}
 	    
 	
 	
 	
 	/**
 	 * 处理登录的业务
 	 * @param request
 	 * @param response
 	 * @throws ServletException
 	 * @throws IOException
 	 */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 获取请求的参数
    				String username = request.getParameter("username");
    				String password = request.getParameter("password");
    				
    				
    				
    				User logingUser = uService.loginUser(new User(null, username, password, null));
    				// 如果=null,说明登录 失败
    				if (logingUser == null) {
    					// 把错误信息，和回显的表单项信息，保存到 request 域中
    					request.setAttribute("msg", "用户名或密码错误");
    					request.setAttribute("username", username);
    					
    					// 调回登录页面
    					request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
    				} else {
    					// 登录成功
    					request.getSession().setAttribute("user", logingUser);
    					// 跳转到成功页面
    					request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
    				}
	}
    
    /**
     * 处理注册的业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// 获取Session中的验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session中的验证码
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
    	
    			// 获取请求的参数
    				String username = request.getParameter("username");
    				String password = request.getParameter("password");
    				String email = request.getParameter("email");
    				String code = request.getParameter("code");
    				
    				
//    				try {
//						User user = new User();
//						System.out.println("注入之前"+user);
//						// 把所有请求的参数都注入到user对象中
//						BeanUtils.populate(user, request.getParameterMap());
//						System.out.println("注入之后"+user);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
    				User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
    				
    				// 检查验证码是否正确 写死，要求验证码为：aaaaaa
    				if (token!=null && token.equalsIgnoreCase(code)) {
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
