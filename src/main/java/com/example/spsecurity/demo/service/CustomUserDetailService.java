package com.example.spsecurity.demo.service;

import com.example.spsecurity.demo.model.CustomUserDetails;
import com.example.spsecurity.demo.model.Users;
import com.example.spsecurity.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepository.findByName(username);
        optionalUsers.orElseThrow(() -> new UsernameNotFoundException("UserName Not Found"));
        return optionalUsers.map(CustomUserDetails::new).get();
    }
}
