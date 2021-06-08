package com.albook.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.albook.pojo.Cart;
import com.albook.pojo.User;
import com.albook.service.OrderService;

public class OrderServlet extends BaseServlet {

	
	private OrderService orderService = new OrderService();

    /**
     * ���ɶ���
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // �Ȼ�ȡCart���ﳵ����
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        // ��ȡUserid
        User loginUser = (User) req.getSession().getAttribute("user");

        if (loginUser == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        Integer userId = loginUser.getId();
//        ����orderService.createOrder(Cart,Userid);���ɶ���
        String orderId = orderService.createOrder(cart, userId);

        
        /* ����ת�����ظ��ύ�����ѡ���ض���request��֧�����ݵĹ��� */
//        req.setAttribute("orderId", orderId);
        // ����ת����/pages/cart/checkout.jsp
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);

        req.getSession().setAttribute("orderId",orderId);

        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
	
}
