package com.caffe.CochinitoApp.core.service;

import com.caffe.CochinitoApp.core.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface TransactionService {
    Page<Transaction> getAllTransactions(Pageable pageable);
    //Page<Group> getAllGroupsByUserId(Long userId, Pageable pageable);


    Transaction getTransactionById(Long transactionId);
    Transaction updateTransactionById(Transaction transaction,Long transactionId);
    Transaction saveTransaction(Transaction transaction, Long userId);

    Transaction getTransactionByOperationId(Long operationId);

    ResponseEntity<?> deleteGroup(Long groupId, Long userId);
}
