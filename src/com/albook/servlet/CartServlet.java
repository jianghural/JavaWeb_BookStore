package com.albook.servlet;

import com.albook.pojo.Book;
import com.albook.pojo.Cart;
import com.albook.pojo.CartItem;
import com.albook.service.BookService;
import com.albook.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookService();

    /**
     * �޸���Ʒ����
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // ��ȡ����Ĳ��� ��Ʒ��� ����Ʒ����
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // ��ȡCart���ﳵ����
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // �޸���Ʒ����
            cart.updateCount(id,count);
            // �ض����ԭ�����ﳵչʾҳ��
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * ��չ��ﳵ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // 1 ��ȡ���ﳵ����
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            // ��չ��ﳵ
            cart.clear();
            // �ض����ԭ�����ﳵչʾҳ��
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * ɾ����Ʒ��
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        // ��ȡ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // ��ȡ���ﳵ����
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        if (cart != null) {
            // ɾ�� �˹��ﳵ��Ʒ��
            cart.deleteItem(id);
            // �ض����ԭ�����ﳵչʾҳ��
            resp.sendRedirect(req.getHeader("Referer"));
        }

    }



    /**
     * ���빺�ﳵ
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ��ȡ����Ĳ��� ��Ʒ���
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        // ����bookService.queryBookById(id):Book�õ�ͼ�����Ϣ
        Book book = bookService.queryBookById(id);
        // ��ͼ����Ϣ��ת����ΪCartItem��Ʒ��
        
        // ͼ��� price �� double��cartItem�е�price��bigDecimal��double ����ֱ��תbigdecimal
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,new BigDecimal(Double.toString(book.getPrice())  ),new BigDecimal(Double.toString(book.getPrice())));
        
        // ����Cart.addItem(CartItem);�����Ʒ��
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        System.out.println(cart);
        System.out.println("����ͷReferer��ֵ��" + req.getHeader("Referer"));
        
        // ���һ����ӵ���Ʒ����
        req.getSession().setAttribute("lastName", cartItem.getName());

        // �ض����ԭ����Ʒ���ڵĵ�ַҳ��
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
