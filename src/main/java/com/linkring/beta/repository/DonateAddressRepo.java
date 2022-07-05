package com.linkring.beta.repository;

import com.linkring.beta.domain.DonateAddress;
import com.linkring.beta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DonateAddressRepo extends JpaRepository<DonateAddress,Long> {
    Optional<DonateAddress> findByUsername(User username);

}
