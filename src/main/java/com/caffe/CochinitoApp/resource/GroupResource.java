package com.caffe.CochinitoApp.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;


@Data
public class GroupResource {


    private String groupName;



    private String finishDate;


    private String minimumAmount;


    private String frequency;


    private String approvedLoanInterest;


    private Long leaderId;


}
