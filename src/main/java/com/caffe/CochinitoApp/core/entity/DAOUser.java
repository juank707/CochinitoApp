package com.caffe.CochinitoApp.core.entity;

import lombok.Data;
import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class DAOUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "typeDocument", nullable = false)
    private String typeDocument;

    @Column(name = "documentId", nullable = false)
    private String documentId;

    @Column(name = "firstName", nullable = false)
    private String firstName;


    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name="bornDate", nullable = false)
    private Date bornDate;

    @Column(name = "gender", nullable = false)
    private  String gender;

    @Column(name = "phone", nullable = false)
    private  String phone;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;


}

