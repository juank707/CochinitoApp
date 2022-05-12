package com.caffe.CochinitoApp.service;

import com.caffe.CochinitoApp.core.entity.DAOUser;
import com.caffe.CochinitoApp.core.entity.Group;
import com.caffe.CochinitoApp.core.repository.GroupRepository;
import com.caffe.CochinitoApp.core.repository.UserRepository;
import com.caffe.CochinitoApp.core.service.GroupService;
import com.caffe.CochinitoApp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public Page<Group> getAllGroups(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @Override
    public Group getGroupById(Long groupId) {
        Group group = groupRepository.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("Group", "Id", groupId));
        return group;
    }

    @Override
    public Group updateGroupById(Group group, Long groupId, Long userId) {
       DAOUser user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        Group group2= groupRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Group","Id",groupId));
        group2.setGroupName(group.getGroupName());
        group2.setFrequency(group.getFrequency());
        group2.setFinishDate(group.getFinishDate());
        group2.setLeaderId(user.getId());
        group2.setMinimumAmount(group.getMinimumAmount());
        group2.setApprovedLoanInterest(group.getApprovedLoanInterest());

        return groupRepository.save(group2);

    }

    @Override
    public Group saveGroup(Group group, Long userId) {
        DAOUser user= userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        group.setLeaderId(user.getId());
        user.setGroup(group);

        
        return  groupRepository.save(group);




    }

    @Override
    public ResponseEntity<?> deleteGroup(Long groupId, Long userId) {
       Group group= groupRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Group","Id",groupId));
       groupRepository.delete(group);
       return ResponseEntity.ok().build();

    }
}
