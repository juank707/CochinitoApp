package com.caffe.CochinitoApp.core.repository;

import com.caffe.CochinitoApp.core.entity.DAOUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DAOUser,Long>{
    DAOUser findByUserName(String username);

    Optional<DAOUser> findDAOUserByEmail(String email);
}
