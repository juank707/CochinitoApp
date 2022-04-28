package com.caffe.CochinitoApp.core.repository;

import com.caffe.CochinitoApp.core.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
