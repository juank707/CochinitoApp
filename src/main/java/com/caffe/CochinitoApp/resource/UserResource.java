package com.caffe.CochinitoApp.resource;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
public class UserResource {


    private String userName;


    private String firstName;


    private String lastName;


    private String email;


    private String password;


    private String role;
}
