package com.caffe.CochinitoApp.controller;

import com.caffe.CochinitoApp.core.entity.DAOUser;
import com.caffe.CochinitoApp.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class GridViewUserController {

    @Autowired
    private UserRepository userRepository;

    public Page<DAOUser> get(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
