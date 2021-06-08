package com.albook.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	private  static Connection  con;
	
	/**
	 * 获取数据库的连接
	 * @return
	 */
	public synchronized static Connection getConnection(){
		try {
			if(con==null||con.isClosed()){
			//localhost  ip地址，127.0.0.1
			Class.forName("com.mysql.jdbc.Driver");//jar 没引进来
			String url="jdbc:mysql://localhost:3306/book";//test是数据库的名字
			
			con=DriverManager.getConnection(url, "root", "");
			if(con!=null){
				System.out.println("连接成功");
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
                System.out.println("关闭成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	

}
