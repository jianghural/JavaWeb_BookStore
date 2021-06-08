package com.albook.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
	private  static Connection  con;
	
	/**
	 * ��ȡ���ݿ������
	 * @return
	 */
	public synchronized static Connection getConnection(){
		try {
			if(con==null||con.isClosed()){
			//localhost  ip��ַ��127.0.0.1
			Class.forName("com.mysql.jdbc.Driver");//jar û������
			String url="jdbc:mysql://localhost:3306/book";//test�����ݿ������
			
			con=DriverManager.getConnection(url, "root", "");
			if(con!=null){
				System.out.println("���ӳɹ�");
			}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * �ر�����
	 * @param conn
	 */
	public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
                System.out.println("�رճɹ�");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	

}
