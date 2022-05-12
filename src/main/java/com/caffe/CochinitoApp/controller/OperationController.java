package com.caffe.CochinitoApp.controller;

import com.caffe.CochinitoApp.core.entity.Operation;
import com.caffe.CochinitoApp.core.service.OperationService;
import com.caffe.CochinitoApp.resource.OperationResource;
import com.caffe.CochinitoApp.service.CustomUserDetailsService;
import com.caffe.CochinitoApp.service.GroupServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class OperationController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private OperationService OperationService;

    @GetMapping(value = "/operations")
    public Page<OperationResource> getAllOperations(Pageable pageable) {
        Page<Operation> OperationPage = OperationService.getAllOperations(pageable);
        List<OperationResource> resources = OperationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    // Get all Operations by groupId and by userId
    @GetMapping(value = "groups/{groupId}/users/{userId}/operations")
    public Page<OperationResource> getAllOperationsByUserIdAndGroupId(Pageable pageable,  @PathVariable(name = "groupId") Long groupId, @PathVariable(name = "userId") Long userId) {
        Page<Operation> OperationPage = OperationService.getAllOperationsByUserIdAndGroupId(pageable, userId, groupId);
        List<OperationResource> resources = OperationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    // Get all Operations by userId
    @GetMapping(value = "/users/{userId}/operations")
    public Page<OperationResource> getAllOperationsByUserId(Pageable pageable, @PathVariable(name = "userId") Long userId) {
        Page<Operation> OperationPage = OperationService.getAllOperationsByUserId(pageable,userId);
        List<OperationResource> resources = OperationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping(value = "/groups/{groupId}/operations")
    public Page<OperationResource> getAllOperationsByGroupId(Pageable pageable, @PathVariable(name = "groupId") Long groupId) {
        Page<Operation> OperationPage = OperationService.getAllOperationsByGroupId(pageable,groupId);
        List<OperationResource> resources = OperationPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }
   // getOperationByIdAndUserId- not required-pending..
    @GetMapping(value = "/users/{userId}/operations/{operationId}")
    public OperationResource getOperationsByIdAndUserId(@PathVariable(name = "userId") Long userId, @PathVariable(name = "operationId") Long operationId){
        return  convertToResource(OperationService.getOperationByIdAndUserId(operationId,userId));
    }



    @GetMapping(value = "/Operations/{operationId}")
    public OperationResource getOperationById(
            @PathVariable(name = "operationId") Long OperationId) {
        return convertToResource(OperationService.getOperationById(OperationId));
    }

    // Assing to Operation
    @PostMapping("/groups/{groupId}/users/{userId}/operations")
    public OperationResource createOperation(@PathVariable(name = "userId") Long userId,@PathVariable(name = "groupId") Long groupId,
                                     @Valid @RequestBody OperationResource resource) {
        return convertToResource(OperationService.saveOperation( convertToEntity(resource),userId,groupId));

    }
    @PutMapping("/groups/{groupId}/users/{userId}/operations/{operationId}")
    public OperationResource updateOperation(@PathVariable(name = "userId") Long userId, @PathVariable(name = "operationId") Long operationId,@PathVariable(name = "groupId") Long groupId,@Valid @RequestBody OperationResource resource) {

        return convertToResource(OperationService.updateOperationById(convertToEntity(resource),operationId,userId,groupId));
    }

    @DeleteMapping("/groups/{groupId}/users/{userId}/operations/{operationId}")
    public ResponseEntity<?> deleteOperation(@PathVariable(name = "groupId") Long groupId,@PathVariable(name = "userId") Long userId, @PathVariable(name = "operationId") Long operationId) {
        return OperationService.deleteOperation(operationId,userId,groupId);
    }

    // Auto Mapper
    private Operation convertToEntity( OperationResource resource) {
        return mapper.map(resource, Operation.class);
    }

    private OperationResource convertToResource(Operation entity) {
        return mapper.map(entity, OperationResource.class);
    }
}
