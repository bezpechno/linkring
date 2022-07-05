package com.linkring.beta.service;


import com.linkring.beta.domain.User;
import com.linkring.beta.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLegacyService {
    @Autowired
    private UserRepo userRepo;

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void save (User user){userRepo.save(user);}
}
