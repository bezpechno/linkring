package com.linkring.beta.repository;

import com.linkring.beta.domain.BackgroundImage;
import com.linkring.beta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BackgroundImageRepo extends JpaRepository<BackgroundImage,Long> {
    Optional<BackgroundImage> findByUsername(User username);
}
