package com.albook.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.albook.pojo.OrderItem;
import com.albook.utils.Conn;

public class OrderItemDao {

	
	/**
	 * 保存订单项
	 * @param orderItem
	 * @return 成功返回1，失败返回0
	 */
	public int saveOrderItem(OrderItem orderItem) {
		Connection con = Conn.getConnection();
		String sql = "insert into t_order_item(`name`,`count`,`price`,`total_money`,`order_id`) values(?,?,?,?,?)";
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, orderItem.getName());
			st.setInt(2, orderItem.getCount());
			st.setBigDecimal(3, orderItem.getPrice());
			st.setBigDecimal(4, orderItem.getTotalPrice());
			st.setString(5, orderItem.getOrderId());
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
	 * 根据订单号查询订单明细
	 * @param orderId
	 * @return 查找成功返回 OrderItem，失败返回 null
	 */
	public OrderItem queryOrderItemByOrderId(String orderId) {
		Connection con = Conn.getConnection();
    	String sql = "select `id` , `name` , `price` , `total_money` , `count` , `order_id` from t_order_item where order_id = ?";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, orderId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				BigDecimal price = rs.getBigDecimal("price");
				BigDecimal total_money = rs.getBigDecimal("total_money");
				int count = rs.getInt("count");
				String order_Id = rs.getString("order_id");
				OrderItem orderItem = new OrderItem(id, name, count, price, total_money, order_Id);
				return orderItem;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
}
