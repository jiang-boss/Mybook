package com.jiang.untils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author jiangboss
 * @create 2021-05-19-16:57
 */
public class webUtils {
    /**
     * 将参数注入到javabean中的工具方法
     *
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map map, T bean) {
        try {
            System.out.println("注入前"+bean);
            BeanUtils.populate(bean, map);
            System.out.println("注入后"+bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int parseInt(String str,int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
