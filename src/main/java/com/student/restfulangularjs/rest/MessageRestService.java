/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.student.restfulangularjs.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.student.restfulangularjs.model.StudentManage;
import com.student.restfulangularjs.pojo.StudentBean;
import com.student.restfulangularjs.utils.CodeUtils;

@Path("/message")
public class MessageRestService {

    private String jsonResponse = null;
    private Integer status = null;

    @GET
    @Path("/{param}")
    public Response printMessage(@PathParam("param") String msg) {

        String result = "Restful example : " + msg;

        return Response.status(200).entity(result).build();

    }

    @GET
    @Path("/getAllProducts")
    public Response getAllProducts() {
        List<StudentBean> prodList = new ArrayList<StudentBean>();

        StudentManage manager = new StudentManage();
        prodList = manager.getAllProducts();
        Gson gson = new Gson();
        String json = gson.toJson(prodList);

        return Response.status(200).entity(json).build();
    }

    @POST
    @Path("/addNewProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addNewProduct(String jsonRequest) {

        try {
            Gson gson = new Gson();
            StudentBean prod = gson.fromJson(jsonRequest, StudentBean.class);

            StudentManage manager = new StudentManage();
             System.out.println("This is the Student being added: " + prod.getName() + " : " + prod.getAddress());

            manager.addNewProduct(prod);

            jsonResponse = "{ \"response\" : \" New Student has been successfully saved.\"}";
            status = CodeUtils.STATUS_CODE_OK;
        } catch (Exception ex) {
            jsonResponse = "{ \"response\" : \"Error while trying to add a Student. \"}";
            ex.printStackTrace();
            status = CodeUtils.STATUS_CODE_INTERNAL_SERVER_ERROR;
        }

        return Response.status(status).entity(jsonResponse).build();
    }
 @DELETE
    @Path("/removeProductById/{rollno}")
    public Response removeProductById(@PathParam("rollno") String rollno) {

        StudentManage manager = new StudentManage();
        System.out.println("The Student with id: " + rollno + " will be removed from the list");
        manager.removeProdById(rollno);
        String json = "{ \"response\" : \"Student has been successfully removed.\"}";

        return Response.status(200).entity(json).build();
    }

    @GET
    @Path("/getProductById/{rollno}")
    public Response getProductById(@PathParam("rollno") String rollno) {
        StudentBean aProduct = new StudentBean();

        StudentManage manager = new StudentManage();
        System.out.println("The Student with id: " + rollno + " will be gotten from the list");
        aProduct = manager.getProductById(rollno);
        Gson gson = new Gson();
        String json = gson.toJson(aProduct);

        return Response.status(200).entity(json).build();
    }

    @PUT
    @Path("/updateProductById")
    public Response updateProductById(String jsonProduct) {

        try {
            Gson gson = new Gson();
            StudentBean aProd = gson.fromJson(jsonProduct, StudentBean.class);
            StudentManage manager = new StudentManage();
            System.out.println("The Student with id: " + aProd.getRollno() + " will be updated");
            System.out.println(aProd.toString());
            manager.updateProductById(aProd);

            jsonResponse = "{ \"response\" : \"Student has been successfully updated.\"}";
            status = CodeUtils.STATUS_CODE_OK;
        } catch (Exception ex) {
            jsonResponse = "{ \"response\" : \"Error while trying to update.\"}";
            status = CodeUtils.STATUS_CODE_INTERNAL_SERVER_ERROR;
        }

        return Response.status(status).entity(jsonResponse).build();
    }
}
