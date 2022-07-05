package com.linkring.beta.service;

import com.linkring.beta.domain.BackgroundImage;
import com.linkring.beta.domain.Email;
import com.linkring.beta.domain.User;
import com.linkring.beta.repository.BackgroundImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BackgroundImageService {
    @Autowired
    private BackgroundImageRepo backgroundImageRepo;

    public Optional<BackgroundImage> findByUsername(User username){
        return backgroundImageRepo.findByUsername(username);
    }
    public void save (BackgroundImage image){backgroundImageRepo.save(image);}

}
