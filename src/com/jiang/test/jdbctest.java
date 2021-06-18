package com.jiang.test;

import com.jiang.untils.jdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author jiangboss
 * @create 2021-05-19-15:12
 */
public class jdbctest {
    @Test
    public void test(){
        Connection connection = jdbcUtils.getConnection();
        System.out.println(connection);
    }
}
