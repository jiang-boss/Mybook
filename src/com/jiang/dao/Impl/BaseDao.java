package com.jiang.dao.Impl;

import com.jiang.untils.jdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author jiangboss
 * @create 2021-05-19-15:15
 * 这个类是是给每个dao复用 的代码 针对于数据库数据的增删改查相关操作
 */
public abstract class BaseDao {
private QueryRunner queryRunner=new QueryRunner();
//private static Connection connection=jdbcUtils.getConnection();
    /**
     * 返回-1说明修改失败
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql,Object...args){
        Connection connection=jdbcUtils.getConnection();
    try {
        //返回修改的条数
        return queryRunner.update(connection, sql, args);
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw new RuntimeException(sqlException);
    }
}
    /**
     *返回一条javabean的对象
     * @param tClass
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> T QueryForOne(Class<T> tClass,String sql,Object...args){
    Connection connection = jdbcUtils.getConnection();
    try {
        return  queryRunner.query(connection, sql, new BeanHandler<>(tClass), args);
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw new RuntimeException(sqlException);
    }
}
    /**
     * 返回一个集合的javabean对象
     * @param tClass
     * @param sql
     * @param args
     * @param <T>
     * @return
     */
    public <T> List<T>  QueryForList(Class<T> tClass,String sql,Object...args){
    Connection connection = jdbcUtils.getConnection();
    try {
        return queryRunner.query(connection, sql, new BeanListHandler<>(tClass), args);
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw new RuntimeException(sqlException);
    }
}
    /**
     * 返回一组对象的条数
     * @param sql
     * @param args
     * @return
     */
    public Object QueryForCount(String sql,Object...args){
    Connection connection = jdbcUtils.getConnection();
    try {
        return  queryRunner.query(connection,sql,new ScalarHandler<>(),args);
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        throw new RuntimeException(sqlException);
    }
}
}
