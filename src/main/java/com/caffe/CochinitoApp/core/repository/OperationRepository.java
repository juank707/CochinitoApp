package com.caffe.CochinitoApp.core.repository;

import com.caffe.CochinitoApp.core.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {
    //Page<TestResult> findByUserId(Long userId, Pageable pageable);
    Page<Operation> findAllByUserId(Long userId, Pageable pageable);
    //getAllOperationsByUserIdByGroupId
    Page<Operation> findAllOperationsByUserIdAndGroupId(Long groupId, Long userId, Pageable pageable);

    //findOperationByIdAndUserId
    Operation findOperationByIdAndUserId (Long operationId, Long userId);

    Page<Operation> findAllByGroupId(Long groupId, Pageable pageable);

}
