package com.caffe.CochinitoApp.controller;

import com.caffe.CochinitoApp.core.entity.Group;
import com.caffe.CochinitoApp.core.service.GroupService;
import com.caffe.CochinitoApp.resource.GroupResource;
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
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api")
public class GroupController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private GroupService groupService;

    @GetMapping(value = "/groups")
    public Page<GroupResource> getAllGroups(Pageable pageable) {
        Page<Group> groupPage = groupService.getAllGroups(pageable);
        List<GroupResource> resources = groupPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }
    // Get all users By GroupId falta
    // Assing to Group
    @GetMapping(value = "/groups/{groupId}")
    public GroupResource getGroupById(
            @PathVariable(name = "groupId") Long groupId) {
        return convertToResource(groupService.getGroupById(groupId));
    }

    @PostMapping("/users/{userId}/groups")
    public GroupResource createGroup(@PathVariable(name = "userId") Long userId,
                                         @Valid @RequestBody GroupResource resource) {
        return convertToResource(groupService.saveGroup( convertToEntity(resource),userId));

    }
    @PutMapping("/users/{userId}/groups/{groupId}")
    public GroupResource updateGroup(@PathVariable(name = "userId") Long userId, @PathVariable(name = "groupId") Long groupId,@Valid @RequestBody GroupResource resource) {
        Group group = convertToEntity(resource);
        return convertToResource(groupService.updateGroupById(group,groupId,userId));
    }


    @DeleteMapping("/users/{userId}/groups/{groupId}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "userId") Long userId,@PathVariable(name = "groupId") Long groupId) {
        return groupService.deleteGroup(groupId,userId);
    }

    // Auto Mapper
    private Group convertToEntity( GroupResource resource) {
        return mapper.map(resource, Group.class);
    }

    private GroupResource convertToResource(Group entity) {
        return mapper.map(entity, GroupResource.class);
    }
}
