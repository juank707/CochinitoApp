package com.caffe.CochinitoApp.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group")
@Data
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "groupName", nullable = false)
    private String groupName;


    @Column(name = "finishDate")
    private String finishDate;

    @Column(name = "minimumAmount")
    private String minimumAmount;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "approvedLoanInterest")
    private String approvedLoanInterest;

    @Column(name = "leaderId")
    @JsonIgnore
    private Long leaderId;



}
