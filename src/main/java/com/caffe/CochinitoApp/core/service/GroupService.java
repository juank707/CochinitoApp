package com.caffe.CochinitoApp.core.service;

import com.caffe.CochinitoApp.core.entity.Group;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface GroupService {
    Page<Group> getAllGroups(Pageable pageable);
    //Page<Group> getAllGroupsByUserId(Long userId, Pageable pageable);


    Group getGroupById(Long groupId);
    Group updateGroupById(Group group,Long groupId, Long userId);
    Group saveGroup(Group group, Long userId);

    ResponseEntity<?> deleteGroup(Long groupId,Long userId);

}
