package com.albook.servlet;

import java.lang.reflect.Method;

// 使用反射优化大量else if代码
public class UserServletTest {
	
	public void login() {
		System.out.println("调用login方法");
	}
	public void regist() {
		System.out.println("调用regist方法");
	}
	
	
	public static void main(String[] args) {
		String action = "login";
		
		try {
			Method method = UserServletTest.class.getDeclaredMethod(action);
			
			System.out.println(method);
			
			// 调用目标业务方法
			method.invoke(new UserServletTest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
