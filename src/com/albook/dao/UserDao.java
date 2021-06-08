package com.albook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.albook.pojo.User;
import com.albook.utils.Conn;


public class UserDao {
		
	/**
	 * 根据用户名查询用户信息
	 * @param username 用户名
	 * @return 如果返回null，说明没有这个用户。反之亦然。
	 */
	public User queryUserByUsername(String uname) {
		Connection con = Conn.getConnection();
		String sql = "SELECT * FROM USER WHERE username = '"+ uname +"'";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				User user = new User(id, username, password, email);
				return user;
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
	
	/**
	 * 根据用户名和密码查询用户信息
	 * @param username 
	 * @param password
	 * @return 如果返回null，说明用户名或密码错误，反之亦然。
	 */
	public User queryUserByUsernameAndPassword(String uname, String pwd) {
		Connection con = Conn.getConnection();
		String sql = "SELECT * FROM USER WHERE username = '"+ uname +"' and password = '"+pwd+"'";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				User user = new User(id, username, password, email);
				return user;
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
	
	/**
	 * 保存用户信息
	 * @param user
	 * @return 返回false 表示操作失败，true成功
	 */
	public boolean saveUser(User user) {
		Connection con = Conn.getConnection();
		String sql = "insert into user(username,password,email) values(?, ?, ?)";
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			System.out.println(sql.toString());
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
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

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void save(User user) {
		Connection con = Conn.getConnection();
		String sql = "insert into user(username,password,email) values(?, ?, ?)";
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			System.out.println(sql.toString());
			st.executeUpdate();
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
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id) {
		Connection con = Conn.getConnection();
		StringBuffer sql = new StringBuffer("delete from user ");
		sql.append("where id=?");
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			st.setInt(1, id);
			System.out.println(sql.toString());
			st.executeUpdate();
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
	}
	
	/**
	 * 用户信息修改
	 * @param user
	 */
	public void update(User user) {
		Connection con = Conn.getConnection();
		StringBuffer sql = new StringBuffer("update user set username=?, password=?, email=? ");
		sql.append("where id=?");
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			st.setString(1, user.getUsername());
			st.setString(2, user.getPassword());
			st.setString(3, user.getEmail());
			st.setInt(4, user.getId());
			System.out.println(sql.toString());
			st.executeUpdate();
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
	}
	
	/**
	 * 查
	 * @param cur
	 * @param pagesize
	 * @return
	 */
	public List<User> getUsers(int cur, int pagesize) {
		List<User> list = new ArrayList<User>();
		Connection con = Conn.getConnection();
		StringBuffer sql = new StringBuffer("select * from user order by id desc limit ?,?");
//		sql.append("(select t.*,ROWNUM rn from user where ROWNUM<=?) t");
//		sql.append(" where rn>?");
		System.out.println(sql.toString());
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			st.setInt(1, (cur-1)*pagesize);
			st.setInt(2, pagesize);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				
				User user = new User(id, username, password, email);
				list.add(user);
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
		return list;
	}
	
	
}
