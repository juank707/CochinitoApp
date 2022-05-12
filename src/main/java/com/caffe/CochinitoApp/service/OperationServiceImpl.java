package com.caffe.CochinitoApp.service;

import com.caffe.CochinitoApp.core.entity.DAOUser;
import com.caffe.CochinitoApp.core.entity.Group;
import com.caffe.CochinitoApp.core.entity.Operation;
import com.caffe.CochinitoApp.core.repository.GroupRepository;
import com.caffe.CochinitoApp.core.repository.OperationRepository;
import com.caffe.CochinitoApp.core.repository.UserRepository;
import com.caffe.CochinitoApp.core.service.OperationService;
import com.caffe.CochinitoApp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Page<Operation> getAllOperations(Pageable pageable) {
        return operationRepository.findAll(pageable);
    }

    @Override
    public Page<Operation> getAllOperationsByUserId(Pageable pageable, Long userId) {
        userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        return operationRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Page<Operation> getAllOperationsByGroupId(Pageable pageable, Long groupId) {
        groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("Group", "Id", groupId));
        return operationRepository.findAllByGroupId(groupId,pageable);
    }

    @Override
    public Page<Operation> getAllOperationsByUserIdAndGroupId(Pageable pageable, Long userId, Long groupId) {
        userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("Group", "Id", groupId));
        return operationRepository.findAllOperationsByUserIdAndGroupId(groupId,userId,pageable);
    }

    @Override
    public Operation getOperationById(Long operationId) {
        return operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Not Found the Operation"));
    }

    @Override
    public Operation getOperationByIdAndUserId(Long operationId, Long userId) {
        userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        return operationRepository.findOperationByIdAndUserId(operationId,userId);
    }

    @Override
    public Operation updateOperationById(Operation operation, Long operationId, Long userId, Long groupId) {
        groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("Group", "Id", groupId));
        userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        Operation operation1 = operationRepository.findById(operationId).orElseThrow(()-> new ResourceNotFoundException("Operation","Id", operationId));
        operation1.setConsecutive(operation.getConsecutive());
        operation1.setValue(operation.getValue());
        operation1.setTransactionDate(operation.getTransactionDate());


        return operationRepository.save(operation1);
    }

    @Override
    public Operation saveOperation(Operation operation, Long userId, Long groupId) {
      Group group=  groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("Group", "Id", groupId));
      DAOUser user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
      operation.setUser(user);
      operation.setDocumentId(user.getDocumentId());
      operation.setGroup(group);
      return operationRepository.save(operation);
    }

    @Override
    public ResponseEntity<?> deleteOperation(Long operationId, Long userId, Long groupId) {
        groupRepository.findById(groupId).orElseThrow(()-> new ResourceNotFoundException("Group", "Id", groupId));
        userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
        Operation operation = operationRepository.findById(operationId).orElseThrow(() -> new ResourceNotFoundException("Operation","ID",operationId));
        operationRepository.delete(operation);
        return  ResponseEntity.ok().build();
    }
}
