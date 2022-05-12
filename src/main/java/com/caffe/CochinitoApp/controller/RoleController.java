package com.caffe.CochinitoApp.controller;

import com.caffe.CochinitoApp.core.entity.Group;
import com.caffe.CochinitoApp.core.entity.Role;
import com.caffe.CochinitoApp.core.service.RoleService;
import com.caffe.CochinitoApp.resource.GroupResource;
import com.caffe.CochinitoApp.resource.RoleResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoleService roleService;
    @PostMapping("/roles")
    public RoleResource createARole(@Valid @RequestBody RoleResource resource) {
        return convertToResource(roleService.createRole(resource));

    }


    private Role convertToEntity(RoleResource resource) {
        return mapper.map(resource, Role.class);
    }

    private RoleResource convertToResource(Role entity) {
        return mapper.map(entity, RoleResource.class);
    }
}
