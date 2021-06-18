package com.jiang.untils;

import com.alibaba.druid.pool.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author jiangboss
 * @create 2021-05-19-14:57
 * 操作数据库的工具类
 */
public class jdbcUtils {
    public static DruidDataSource druidDataSource;
    //用threadlocal确保所有的操作用都是使用同一个Connection对象
    private static ThreadLocal<Connection> connectionThreadLocal=new InheritableThreadLocal<Connection>();
    static {
        try {
            Properties properties=new Properties();
            //读取jdbc配置文件
            InputStream resourceAsStream = jdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中获取信息
            properties.load(resourceAsStream);
            //获取数据库连接池
            druidDataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    /**
     * 获取数据库中的连接
     */
    public static Connection getConnection(){
        Connection connection=connectionThreadLocal.get();
        if(connection==null){//判断是否为空 空的话就创建并把 这个连接放在threaLocal中
            try {
                connection= druidDataSource.getConnection();
                connectionThreadLocal.set(connection);
                connection.setAutoCommit(false);//设置手动提交
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        return connection;
    }
//    /**
//     * 关闭连接
//     */
//    public void close(Connection connection){
//        if(connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//        }
//    }

    /**
     *提交事务和关闭连接
     */
    public static void commitAndClose(){
        Connection connection=connectionThreadLocal.get();
        if(connection!=null){
            try {
                connection.commit();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
connectionThreadLocal.remove();//Tomcat底层使用的线程池技术  此时要remove
    }
    /**
     * 回滚事务和关闭连接
     */
    public static void rollBackAndClose(){
        Connection connection=connectionThreadLocal.get();
        if(connection!=null){
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }finally {
                try {
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        }
        connectionThreadLocal.remove();
    }
}
