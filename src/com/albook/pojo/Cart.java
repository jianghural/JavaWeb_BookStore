package com.albook.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ���ﳵ����
 */
public class Cart {
//	���þֲ��������þֲ�����
//    private Integer totalCount;
//    private BigDecimal totalPrice;

    /**
     * key����Ʒ��ţ�
     * value������Ʒ��Ϣ
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    /**
     * �����Ʒ��
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        // �Ȳ鿴���ﳵ���Ƿ��Ѿ���ӹ�����Ʒ���������ӣ��������ۼӣ��ܽ����£����û����ӹ���ֱ�ӷŵ������м���
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            // ֮ǰû��ӹ�����Ʒ
            items.put(cartItem.getId(), cartItem);
        } else {
            // �Ѿ� ��ӹ������
            item.setCount( item.getCount() + 1 ); // ���� �ۼ�
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal( item.getCount() )) ); // �����ܽ��
        }

    }

    /**
     * ɾ����Ʒ��
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }


    /**
     * ��չ��ﳵ
     */
    public void clear() {
        items.clear();
    }

    /**
     * �޸���Ʒ����
     */
    public void updateCount(Integer id,Integer count) {
        // �Ȳ鿴���ﳵ���Ƿ��д���Ʒ������У��޸���Ʒ�����������ܽ��
        CartItem cartItem = items.get(id);
        if (cartItem != null) {
            cartItem.setCount(count);// �޸���Ʒ����
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal( cartItem.getCount() )) ); // �����ܽ��
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

//        for (CartItem value : items.values()) {
//        	
//        }
        
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
