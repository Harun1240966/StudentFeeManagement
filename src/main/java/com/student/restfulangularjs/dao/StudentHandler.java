/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.restfulangularjs.dao;

import com.student.restfulangularjs.pojo.StudentBean;
import java.sql.*;
import java.util.ArrayList;

public class StudentHandler {

    private static String QUERY_ALL_STUDENTS = "SELECT * FROM fee_student";
    private static String INSERT_NEW_STUDENT = "INSERT INTO fee_student (NAME,EMAIL,SEX,COURSE,FEE,PAID,DUE,ADDRESS,CONTACT) values(?,?,?,?,?,?,?,?,?)";
    private static String REMOVE_STUDENT = "DELETE FROM fee_student WHERE rollno = ?";
    private static String GET_STUDENT_BY_ID = "SELECT * FROM fee_student WHERE rollno = ?";
    private static String UPDATE_STUDENT_BY_ID = "UPDATE fee_student SET NAME = ?, EMAIL = ?,SEX=?, COURSE = ?, FEE = ?, PAID = ?, DUE = ?, ADDRESS = ?,CONTACT = ?WHERE rollno = ?";

    public ArrayList<StudentBean> getAllProducts(Connection connection) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<StudentBean> studentList = new ArrayList<StudentBean>();
        try {
            ps = connection
                    .prepareStatement(QUERY_ALL_STUDENTS);

            rs = ps.executeQuery();
            while (rs.next()) {
                StudentBean prod = new StudentBean();
                prod.setRollno(Integer.valueOf(rs.getString("rollno")));
                prod.setName(rs.getString("NAME"));
                prod.setEmail(rs.getString("EMAIL"));
                prod.setSex(rs.getString("SEX"));
                prod.setCourse(rs.getString("COURSE"));
                prod.setFee(rs.getInt("FEE"));
                prod.setPaid(rs.getInt("PAID"));
                prod.setDue(rs.getInt("DUE"));
                prod.setAddress(rs.getString("ADDRESS"));
                prod.setContact(rs.getString("CONTACT"));

                studentList.add(prod);
            }

        } finally {
            closeStatement(ps);
            closeResultSet(rs);
            closeConnection(connection);
        }

        return studentList;
    }

    public void addNewProduct(Connection connection, StudentBean prod) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(INSERT_NEW_STUDENT);
//            ps.setInt(1, prod.getRollno());
            ps.setString(1, prod.getName());
            ps.setString(2, prod.getEmail());
            ps.setString(3, prod.getSex());
            ps.setString(4, prod.getCourse());
            ps.setInt(5, prod.getFee());
            ps.setInt(6, prod.getPaid());
            ps.setInt(7, prod.getDue());
            ps.setString(8, prod.getAddress());
            ps.setString(9, prod.getContact());
            System.out.println(ps);
            ps.execute();
           
        } finally {
            closeConnection(connection);
            closeStatement(ps);
        }
       
    }

    public void removeProdById(Connection connection, String rollno) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = connection
                    .prepareStatement(REMOVE_STUDENT);

            ps.setInt(1, Integer.valueOf(rollno));

            ps.execute();

        } finally {
            closeConnection(connection);
            closeStatement(ps);
        }
    }

    public StudentBean getProdById(Connection connection, String rollno) throws SQLException {
        StudentBean aProd = new StudentBean();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection
                    .prepareStatement(GET_STUDENT_BY_ID);

            ps.setInt(1, Integer.valueOf(rollno));

            rs = ps.executeQuery();

            if (rs != null && rs.next()) {
                aProd.setRollno(Integer.valueOf(rs.getString("rollno")));
                aProd.setName(rs.getString("NAME"));
                aProd.setEmail(rs.getString("EMAIL"));
                aProd.setSex(rs.getString("SEX"));
                aProd.setCourse(rs.getString("COURSE"));
                aProd.setFee(rs.getInt("FEE"));
                aProd.setPaid(rs.getInt("PAID"));
                aProd.setDue(rs.getInt("DUE"));
                aProd.setAddress(rs.getString("ADDRESS"));
                aProd.setContact(rs.getString("CONTACT"));

            }

        } finally {
            closeStatement(ps);
            closeResultSet(rs);
            closeConnection(connection);
        }

        return aProd;
    }

    public void updateProdById(Connection connection, StudentBean aProd) throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = connection
                    .prepareStatement(UPDATE_STUDENT_BY_ID);

            ps.setString(1, aProd.getName());
            ps.setString(2, aProd.getEmail());
            ps.setString(3, aProd.getSex());
            ps.setString(4, aProd.getCourse());
            ps.setInt(5, aProd.getFee());
            ps.setInt(6, aProd.getPaid());
            ps.setInt(7, aProd.getDue());
            ps.setString(8, aProd.getAddress());
            ps.setString(9, aProd.getContact());
            ps.setInt(10, aProd.getRollno());
            ps.execute();

        } finally {
            closeConnection(connection);
            closeStatement(ps);
        }
    }

    private void closeStatement(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            ps.close();
        }
    }

    private void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    private void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

//    public static void main(String[] args) {
//        try {
//            StudentHandler sh = new StudentHandler();
//            DbConnection db = new DbConnection();
//            StudentBean prod = new StudentBean();
//            Connection connection = db.getConnection();
//           String a = sh.addNewProduct(connection, prod);
//            System.out.println("aaa = "+a.toString());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

}
