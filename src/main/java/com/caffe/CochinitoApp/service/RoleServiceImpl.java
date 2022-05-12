package com.caffe.CochinitoApp.service;

import com.caffe.CochinitoApp.core.entity.Role;
import com.caffe.CochinitoApp.core.repository.RoleRepository;
import com.caffe.CochinitoApp.core.repository.UserRepository;
import com.caffe.CochinitoApp.core.service.RoleService;
import com.caffe.CochinitoApp.exception.ResourceNotFoundException;
import com.caffe.CochinitoApp.resource.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private  RoleRepository roleRepository;
    @Autowired
    private  UserRepository userRepository;
    @Override
    public Role createRole(RoleResource resource) {
        Role role = new Role();
        role.setName(resource.getName());
        return roleRepository.save(role);
    }


}
