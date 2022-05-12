package com.caffe.CochinitoApp.core.service;

import com.caffe.CochinitoApp.core.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OperationService {

    Page<Operation> getAllOperations(Pageable pageable);

    Page<Operation> getAllOperationsByUserId(Pageable pageable, Long userId);

    Page<Operation> getAllOperationsByGroupId(Pageable pageable, Long groupId);

    //Page<Operation> getAllOperationsByUserId(Long userId, Pageable pageable);
    Page<Operation> getAllOperationsByUserIdAndGroupId(Pageable pageable, Long userId, Long groupId);

    Operation getOperationById(Long operationId);
    Operation getOperationByIdAndUserId(Long operationId,Long userId);
    Operation updateOperationById(Operation operation,Long operationId, Long userId, Long groupId);
    Operation saveOperation(Operation operation, Long userId,Long groupId);

    ResponseEntity<?> deleteOperation(Long operationId, Long userId,Long groupId);

    //Operation getOperationsByIdAndUserId(Pageable pageable, Long operationId, Long userId);
}
