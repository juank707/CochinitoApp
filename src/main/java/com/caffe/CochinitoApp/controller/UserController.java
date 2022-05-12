package com.caffe.CochinitoApp.controller;

import com.caffe.CochinitoApp.core.entity.DAOUser;
import com.caffe.CochinitoApp.resource.UserResource;
import com.caffe.CochinitoApp.service.CustomUserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private ModelMapper mapper;



    @Operation(summary = "Get Users", description = "Get All Users by Pages", tags = { "users" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/users")
    public Page<UserResource> getAllUsers(Pageable pageable, Principal principal) {
        String name = principal.getName();
        System.out.println(name);
        Page<DAOUser> usersPage = userDetailsService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    @GetMapping(value = "/users/{userId}")
    public UserResource getUserById(
            @PathVariable(name = "userId") Long userId) {
        return convertToResource(userDetailsService.getUserById(userId));
    }
    @PutMapping("/users/{userId}")
    public UserResource updateUser(@PathVariable(name = "userId") Long userId, @Valid @RequestBody UserResource resource) {
        DAOUser user = convertToEntity(resource);
        return convertToResource(userDetailsService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId) {
        return userDetailsService.deleteUser(userId);
    }

    // Auto Mapper
    private DAOUser convertToEntity( UserResource resource) {
        return mapper.map(resource, DAOUser.class);
    }

    private UserResource convertToResource(DAOUser entity) {
        return mapper.map(entity, UserResource.class);
    }

}
