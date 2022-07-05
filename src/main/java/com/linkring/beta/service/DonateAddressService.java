package com.linkring.beta.service;

import com.linkring.beta.domain.DonateAddress;
import com.linkring.beta.domain.User;
import com.linkring.beta.repository.DonateAddressRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonateAddressService {
    @Autowired
    private DonateAddressRepo addressRepo;

    public Optional<DonateAddress> findByUsername(User username) {
        System.out.println("DonateService response user_id intro:" + username);

        return addressRepo.findByUsername(username);
    }

    public void save (DonateAddress address){
        addressRepo.save(address);
    }
}
