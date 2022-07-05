package com.linkring.beta.repository;

import com.linkring.beta.domain.Post;
import com.linkring.beta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> getPostsByUsername(User username);


}
