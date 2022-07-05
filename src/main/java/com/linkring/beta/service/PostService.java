package com.linkring.beta.service;

import com.linkring.beta.domain.Post;
import com.linkring.beta.domain.User;
import com.linkring.beta.repository.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PostService {
    @Autowired
    private PostRepo postRepo;

    public List<Post> getPostsByUsername(User username) {
        System.out.println("PostService response user_id intro:" + username);

        return postRepo.getPostsByUsername(username);
    }

    public void save (Post newPost){postRepo.save(newPost);}
}
