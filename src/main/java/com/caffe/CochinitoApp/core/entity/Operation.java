package com.caffe.CochinitoApp.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "operation")
@Data
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consecutive", nullable = false)
    private String consecutive;
    @Column(name = "documentId", nullable = false)
    @JsonIgnore
    private String documentId;
    @Column(name = "value", nullable = false)
    private String value;
    @Column(name = "transactionDate", nullable = false)
    private String transactionDate;
   /* @Column(name = "operationUserId", nullable = false)
    private Long operationUserId;*/

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private DAOUser user;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Group group;
}
