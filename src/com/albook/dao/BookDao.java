package com.albook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.albook.pojo.Book;
import com.albook.pojo.User;
import com.albook.utils.Conn;

public class BookDao {
	
	/**
	 * ��۸������ܼ�¼��
	 * @param min
	 * @param max
	 * @return
	 */
    public Integer queryForPageTotalCountByPrice(int min, int max) {
    	Connection con = Conn.getConnection();
		int t = 0;
		String sql = "select count(*) from t_book where price between ? and ?";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, min);
			st.setInt(2, max);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				t=rs.getInt(1);
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
		return t;
    }
    
    /**
     * ��۸����䵱ǰҳ����
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max){
    	List<Book> list = new ArrayList<Book>();
		Connection con = Conn.getConnection();
		String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath " +
                "from t_book where price between ? and ? order by price limit ?,?";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			st.setInt(1, min);
			st.setInt(2, max);
			st.setInt(3, begin);
			st.setInt(4, pageSize);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				int sales = rs.getInt("sales");
				int stock = rs.getInt("stock");
				String imgPath = rs.getString("imgPath");
				Book book = new Book(id, name, author, price, sales, stock, imgPath);
				list.add(book);
			}

			return list;
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
	
	/*          ������ҳ�۸�����                   */
	/**
	 * ��ǰҳ����
	 * @param begin ��ǰҳ��
	 * @param pageSize ÿҳ����
	 * @return ���ص�ǰҳ������
	 */
	public List<Book> queryForPageItems(int begin, int pageSize) {
		List<Book> list = new ArrayList<Book>();
		Connection con = Conn.getConnection();
		String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book limit ?,?";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			st.setInt(1, begin);
			st.setInt(2, pageSize);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				int sales = rs.getInt("sales");
				int stock = rs.getInt("stock");
				String imgPath = rs.getString("imgPath");
				Book book = new Book(id, name, author, price, sales, stock, imgPath);
				list.add(book);
			}

			return list;
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
	 * ���ܼ�¼��
	 * @return
	 */
	public Integer queryForPageTotalCount() {
		Connection con = Conn.getConnection();
		int t = 0;
		String sql = "select count(*) from t_book";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				t=rs.getInt(1);
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
		return t;
	}
	
	
	/**
	 * ��� book ��Ϣ
	 * @param book
	 * @return ��ӳɹ����� 1�����ʧ�ܷ��� 0
	 */
    public int addBook(Book book) {
    	Connection con = Conn.getConnection();
		String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?, ?, ?, ?, ?, ?)";
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, book.getName());
			st.setString(2, book.getAuthor());
			st.setDouble(3, book.getPrice());
			st.setInt(4, book.getSales());
			st.setInt(5, book.getStock());
			st.setString(6, book.getImgPath());
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
     * ���� id ɾ�� book
     * @param id
     * @return ��ӳɹ����� 1�����ʧ�ܷ��� 0
     */
    public int deleteBookById(Integer id) {
    	Connection con = Conn.getConnection();
		StringBuffer sql = new StringBuffer("delete from t_book ");
		sql.append("where id=?");
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			st.setInt(1, id);
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
     * �޸� book ��Ϣ
     * @param book
     * @return ��ӳɹ����� 1�����ʧ�ܷ��� 0
     */
    public int updateBook(Book book) {
    	Connection con = Conn.getConnection();
		String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id = ?";
		
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1, book.getName());
			st.setString(2, book.getAuthor());
			st.setDouble(3, book.getPrice());
			st.setInt(4, book.getSales());
			st.setInt(5, book.getStock());
			st.setString(6, book.getImgPath());
			st.setInt(7, book.getId());
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
     * ���� id ��ѯ book
     * @param id
     * @return ��ӳɹ����� book�����ʧ�ܷ��� null
     */
    public Book queryBookById(Integer b_id) {
    	Connection con = Conn.getConnection();
    	String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book where id = ?";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setInt(1, b_id);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				int sales = rs.getInt("sales");
				int stock = rs.getInt("stock");
				String imgPath = rs.getString("imgPath");
				Book book = new Book(id, name, author, price, sales, stock, imgPath);
				return book;
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
     * ��ѯ book
     * @return ��ѯ�ɹ����� book����ѯʧ�ܷ��� null
     */
    public List<Book> queryBooks(){
    	List<Book> list = new ArrayList<Book>();
		Connection con = Conn.getConnection();
		String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path` imgPath from t_book";
		System.out.println(sql);
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql.toString());
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				int sales = rs.getInt("sales");
				int stock = rs.getInt("stock");
				String imgPath = rs.getString("imgPath");
				Book book = new Book(id, name, author, price, sales, stock, imgPath);
				list.add(book);
			}

			return list;
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
