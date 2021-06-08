package com.albook.utils;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class WebUtils {
	/**
     * 把Map中的值注入到对应的JavaBean属性中。
     * 使用map代码的适用性更好一些，耦合度更低，扩展性更强，使用更加灵活
     * @param value
     * @param bean
     * HTTPServletRequest
     * Dao层
     * Service层
     * Web层 耦合度高
     */
    public static <T>T copyParamToBean( Map value , T bean ){
        try {
            System.out.println("注入之前：" + bean);
            /**
             * 把所有请求的参数都注入到bean对象中
             */
            BeanUtils.populate(bean, value);
            System.out.println("注入之后：" + bean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    
    /**
     *  将字符串转为 int 类型的数据
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
