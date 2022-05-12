package com.caffe.CochinitoApp.core.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transactionName", nullable = false)
    private String transactionName;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "operation_id")
    Operation operation;
}
