package com.albook.servlet;

import java.lang.reflect.Method;

// ʹ�÷����Ż�����else if����
public class UserServletTest {
	
	public void login() {
		System.out.println("����login����");
	}
	public void regist() {
		System.out.println("����regist����");
	}
	
	
	public static void main(String[] args) {
		String action = "login";
		
		try {
			Method method = UserServletTest.class.getDeclaredMethod(action);
			
			System.out.println(method);
			
			// ����Ŀ��ҵ�񷽷�
			method.invoke(new UserServletTest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
