package com.albook.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.albook.pojo.Order;
import com.albook.pojo.OrderItem;
import com.albook.utils.Conn;
import com.sun.media.sound.AlawCodec;

import javafx.scene.input.DataFormat;

public class OrderDao {

	
	/**
	 * 保存订单
	 * @param order
	 * @return 成功返回1 ，失败返回0
	 */
	public int saveOrder(Order order) {
		Connection con = Conn.getConnection();
		String sql = "insert into t_order(`order_id`,`create_time`,`total_money`,`status`,`user_id`) values(?,?,?,?,?)";
		PreparedStatement st = null;
		
		// 向数据库插入时间类型数据
		java.util.Date date = order.getCreateTime();
		long time=date.getTime();
		
		try {
			st = con.prepareStatement(sql);
			st.setString(1, order.getOrderId());
			
			// 向数据库插入时间类型数据
			st.setDate(2, new java.sql.Date(time));
			
			st.setBigDecimal(3, order.getPrice());
			st.setInt(4, order.getStatus());
			st.setInt(5, order.getUserId());
			System.out.println(sql.toString());
			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 查询全部订单
	 * @return 查询成功返回 List<Order>,查询失败返回 null
	 */
	public List<Order> queryOrder() {
		Connection con = Conn.getConnection();
		List<Order> orders = new ArrayList<Order>();
    	String sql = "select * from t_order";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String orderId = rs.getString("order_id");
				Date createTime = rs.getDate("create_time");
				BigDecimal totalMoney = rs.getBigDecimal("total_money");
				Integer status = rs.getInt("status");
				Integer userId = rs.getInt("user_id");
				Order order = new Order(orderId, createTime, totalMoney, status, userId);
				orders.add(order);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return orders;
	}
	
	/**
	 * 修改订单状态
	 * @param orderId
	 * @param status
	 * @return 修改成功返回 1，失败返回 0
	 */
	public int changeOrderStatus(String orderId, int status) {
		Connection con = Conn.getConnection();
		String sql = "update t_order set status=? where order_id = ?";
		
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, status);
			st.setString(2, orderId);
			System.out.println(sql.toString());
			
			st.executeUpdate();
			return 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 根据用户编号查询订单信息
	 * @param userId
	 * @return 查找成功返回 Order（订单信息），失败返回 null
	 */
	public List<Order> queryOrdersByUserId(int userId) {
		Connection con = Conn.getConnection();
		List<Order> orders = new ArrayList<Order>();
    	String sql = "select * from t_order where user_id = ?";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String orderId = rs.getString("order_id");
				Date createTime = rs.getDate("create_time");
				BigDecimal totalMoney = rs.getBigDecimal("total_money");
				Integer status = rs.getInt("status");
				Integer user_Id = rs.getInt("user_id");
				Order order = new Order(orderId, createTime, totalMoney, status, user_Id);
				orders.add(order);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return orders;
	}
	
}
