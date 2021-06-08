package com.albook.service;

import java.util.Date;
import java.util.Map;

import com.albook.dao.BookDao;
import com.albook.dao.OrderDao;
import com.albook.dao.OrderItemDao;
import com.albook.pojo.Book;
import com.albook.pojo.Cart;
import com.albook.pojo.CartItem;
import com.albook.pojo.Order;
import com.albook.pojo.OrderItem;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private BookDao bookDao = new BookDao();

    
    public String createOrder(Cart cart, Integer userId) {
        // ������===Ψһ��
        String orderId = System.currentTimeMillis()+""+userId;
        // ����һ����������
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);
        // ���涩��
        orderDao.saveOrder(order);

        // �������ﳵ��ÿһ����Ʒ��ת����Ϊ������浽���ݿ�
        for (Map.Entry<Integer, CartItem>entry : cart.getItems().entrySet()){
            // ��ȡÿһ�����ﳵ�е���Ʒ��
            CartItem cartItem = entry.getValue();
            // ת��Ϊÿһ��������
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            // ���涩������ݿ�
            orderItemDao.saveOrderItem(orderItem);

            // ���¿�������
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales( book.getSales() + cartItem.getCount() );
            book.setStock( book.getStock() - cartItem.getCount() );
            bookDao.updateBook(book);

        }
        // ��չ��ﳵ
        cart.clear();

        return orderId;
    }
}
