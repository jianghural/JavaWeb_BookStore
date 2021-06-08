package com.albook.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ���븴�� ����
public abstract class BaseServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	/**
	 * �����Ż� else-if ����
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            // ��ȡactionҵ������ַ�������ȡ��Ӧ��ҵ�� �����������
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            System.out.println(method);
            // ����Ŀ��ҵ�� ����
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
