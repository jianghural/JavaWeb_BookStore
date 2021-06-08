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
    
    // ���� UserService ����ҵ��
 	UserService uService = new UserService();
    
 	/**
 	 * ע��
 	 * @param request
 	 * @param response
 	 * @throws ServletException
 	 * @throws IOException
 	 */
 	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		/* ���� Session ���û���¼����Ϣ���������� Session �� */
 		request.getSession().invalidate();
 		/* �ض�����ҳ�����¼ҳ�棩 */
 		response.sendRedirect(request.getContextPath());
 		System.out.println("ע��");
 	}
 	    
 	
 	
 	
 	/**
 	 * �����¼��ҵ��
 	 * @param request
 	 * @param response
 	 * @throws ServletException
 	 * @throws IOException
 	 */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ��ȡ����Ĳ���
    				String username = request.getParameter("username");
    				String password = request.getParameter("password");
    				
    				
    				
    				User logingUser = uService.loginUser(new User(null, username, password, null));
    				// ���=null,˵����¼ ʧ��
    				if (logingUser == null) {
    					// �Ѵ�����Ϣ���ͻ��Եı�����Ϣ�����浽 request ����
    					request.setAttribute("msg", "�û������������");
    					request.setAttribute("username", username);
    					
    					// ���ص�¼ҳ��
    					request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
    				} else {
    					// ��¼�ɹ�
    					request.getSession().setAttribute("user", logingUser);
    					// ��ת���ɹ�ҳ��
    					request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
    				}
	}
    
    /**
     * ����ע���ҵ��
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// ��ȡSession�е���֤��
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // ɾ�� Session�е���֤��
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
    	
    			// ��ȡ����Ĳ���
    				String username = request.getParameter("username");
    				String password = request.getParameter("password");
    				String email = request.getParameter("email");
    				String code = request.getParameter("code");
    				
    				
//    				try {
//						User user = new User();
//						System.out.println("ע��֮ǰ"+user);
//						// ����������Ĳ�����ע�뵽user������
//						BeanUtils.populate(user, request.getParameterMap());
//						System.out.println("ע��֮��"+user);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} 
    				User user = WebUtils.copyParamToBean(request.getParameterMap(), new User());
    				
    				// �����֤���Ƿ���ȷ д����Ҫ����֤��Ϊ��aaaaaa
    				if (token!=null && token.equalsIgnoreCase(code)) {
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
