package com.elven.demo.springboot1.test.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTest {

    @Test
    public void test1(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://::FFFF:192.168.56.1:3306/mysql?user=amdin&password=Admin123!&useUnicode=true&characterEncoding=UTF-8";
        String username = "admin";
        String password = "Admin123!";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println(conn);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
