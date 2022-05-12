package com.caffe.CochinitoApp.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Data
public class UserResource {



    private String userName;


    private String typeDocument;


    private String documentId;


    private String firstName;



    private String lastName;


    private String bornDate;


    private  String gender;


    private  String phone;



    private String email;


    private String password;




}
