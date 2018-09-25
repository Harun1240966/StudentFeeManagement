package com.student.restfulangularjs.model;

import com.student.restfulangularjs.dao.DbConnection;
import com.student.restfulangularjs.dao.StudentHandler;
import com.student.restfulangularjs.pojo.StudentBean;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class StudentManage {

    public ArrayList<StudentBean> getAllProducts() {
        ArrayList<StudentBean> list = new ArrayList<StudentBean>();

        try {
            DbConnection db = new DbConnection();
            Connection connection = db.getConnection();
            StudentHandler handler = new StudentHandler();
            list = handler.getAllProducts(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addNewProduct(StudentBean prod) throws Exception {

        DbConnection db = new DbConnection();
        Connection connection = db.getConnection();
       StudentHandler handler = new StudentHandler();
        handler.addNewProduct(connection, prod);

    }

    public void removeProdById(String rollno) {
        try {
            DbConnection db = new DbConnection();
            Connection connection = db.getConnection();
             StudentHandler handler = new StudentHandler();
            handler.removeProdById(connection, rollno);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public StudentBean getProductById(String rollno) {
        StudentBean aProd = new StudentBean();

        try {
            DbConnection db = new DbConnection();
            Connection connection = db.getConnection();
             StudentHandler handler = new StudentHandler();
            aProd = handler.getProdById(connection, rollno);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return aProd;
    }

    public void updateProductById(StudentBean aProd) {
        try {
            DbConnection db = new DbConnection();
            Connection connection = db.getConnection();
             StudentHandler handler = new StudentHandler();
            handler.updateProdById(connection, aProd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
//    public static void main(String[] args) {
//        try {
//            StudentManage  sm = new StudentManage();
//            
//            List a =sm.getAllProducts();
//            System.out.println("products : "+a);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }

 
}
