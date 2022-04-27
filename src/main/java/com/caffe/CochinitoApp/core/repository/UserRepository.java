package com.caffe.CochinitoApp.core.repository;

import com.caffe.CochinitoApp.core.entity.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DAOUser,Long>{
    DAOUser findByuserName(String username);
}
