package com.caffe.CochinitoApp.configuration;


import com.caffe.CochinitoApp.core.entity.DAOUser;
import com.caffe.CochinitoApp.core.repository.UserRepository;
import com.caffe.CochinitoApp.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> roles = null;


        DAOUser daoUser = userRepository.findByuserName(username);
        if (daoUser != null) {
            roles = Arrays.asList(new SimpleGrantedAuthority(daoUser.getRole()));
            return new org.springframework.security.core.userdetails.User(daoUser.getUserName(), daoUser.getPassword(), roles);
        }
        throw new UsernameNotFoundException("User not found with the name " + username);    }

    public DAOUser save(UserResource user) {
        DAOUser newDAOUser = new DAOUser();
        newDAOUser.setUserName(user.getUserName());
        newDAOUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newDAOUser.setRole(user.getRole());
        newDAOUser.setFirstName(user.getFirstName());
        newDAOUser.setLastName(user.getLastName());
        newDAOUser.setEmail(user.getEmail());
        return userRepository.save(newDAOUser);
    }
}
