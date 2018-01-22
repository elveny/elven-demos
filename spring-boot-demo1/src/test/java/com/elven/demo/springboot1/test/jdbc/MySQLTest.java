package com.elven.demo.springboot1.test.jdbc;

import org.junit.Test;

import java.sql.*;

public class MySQLTest {

    @Test
    public void test1(){
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.56.1:3306/mysql?useUnicode=true&characterEncoding=UTF-8";
        String username = "admin";
        String password = "Admin123!";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println(conn);

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from user");
            System.out.print("Host");
            System.out.print("\t\t");
            System.out.println("User");
            while (resultSet.next()){
                System.out.print(resultSet.getNString(1));
                System.out.print("\t\t");
                System.out.println(resultSet.getNString(2));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
