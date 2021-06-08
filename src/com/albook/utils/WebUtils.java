package com.albook.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	/**
     * ��Map�е�ֵע�뵽��Ӧ��JavaBean�����С�
     * ʹ��map����������Ը���һЩ����϶ȸ��ͣ���չ�Ը�ǿ��ʹ�ø������
     * @param value
     * @param bean
     * HTTPServletRequest
     * Dao��
     * Service��
     * Web�� ��϶ȸ�
     */
    public static <T>T copyParamToBean( Map value , T bean ){
        try {
            System.out.println("ע��֮ǰ��" + bean);
            /**
             * ����������Ĳ�����ע�뵽bean������
             */
            BeanUtils.populate(bean, value);
            System.out.println("ע��֮��" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    
    /**
     *  ���ַ���תΪ int ���͵�����
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt, int defaultValue) {
		try {
			return Integer.parseInt(strInt);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return defaultValue;
	}

}
