package com.caffe.CochinitoApp.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

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
    private String bornDate;

    @Column(name = "gender", nullable = false)
    private  String gender;

    @Column(name = "phone", nullable = false)
    private  String phone;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_Id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Role roles;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_Id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Group group;

    private Boolean enabled;

    }

