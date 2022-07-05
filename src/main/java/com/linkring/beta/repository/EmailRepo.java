package com.linkring.beta.repository;

import com.linkring.beta.domain.Email;
import com.linkring.beta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EmailRepo extends JpaRepository<Email,Long> {
    Optional<Email> findByUsername(User username);

}
