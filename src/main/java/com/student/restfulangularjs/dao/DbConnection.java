/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.restfulangularjs.dao;

import java.sql.*;

public class DbConnection {

    public Connection getConnection() throws Exception {
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/student";
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "root");
            return connection;
        } catch (SQLException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
    
   
}
