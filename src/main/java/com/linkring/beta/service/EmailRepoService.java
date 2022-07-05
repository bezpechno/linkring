package com.linkring.beta.service;

import com.linkring.beta.domain.Email;
import com.linkring.beta.domain.User;
import com.linkring.beta.repository.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailRepoService {
    @Autowired
    private EmailRepo emailRepo;

    public Optional<Email> findByUsername(User username){
        return emailRepo.findByUsername(username);
    }
    public void save (Email email){emailRepo.save(email);}
}
