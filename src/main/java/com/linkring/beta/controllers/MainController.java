package com.linkring.beta.controllers;

import com.linkring.beta.domain.DonateAddress;
import com.linkring.beta.domain.Post;
import com.linkring.beta.domain.User;
import com.linkring.beta.repository.DonateAddressRepo;
import com.linkring.beta.repository.PostRepo;
import com.linkring.beta.repository.UserRepo;
import com.linkring.beta.service.DonateAddressService;
import com.linkring.beta.service.PostService;
import com.linkring.beta.service.UserLegacyService;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserLegacyService userLegacyService;
    @Autowired
    private PostService postService;
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/main")
    public String mainApp(@AuthenticationPrincipal User creator, Model model) {
        User user = userLegacyService.getUserByUsername(creator.getUsername());
        System.out.println(user.getId());
        List<Post> posts = postService.getPostsByUsername(user);
        model.addAttribute("posts",posts);
        model.addAttribute("isActive","nav-link active");

        return "main";
    }

    @GetMapping("/deletePost")
    public String postKiller(@RequestParam Long deleteTargetId){
        postRepo.deleteById(deleteTargetId);
        return "redirect:/main";

    }
    @PostMapping("/main")
    public String newPost(@AuthenticationPrincipal User creator,
                                        @RequestParam String target_title,
                                        @RequestParam String target_url,

                                        Model model) {
        Post newPost = new Post(target_title,target_url,creator);
        postService.save(newPost);


        User user = userLegacyService.getUserByUsername(creator.getUsername());
        System.out.println(user.getId());
        List<Post> posts = postService.getPostsByUsername(user);
        model.addAttribute("posts",posts);


        return "main";
    }

    @GetMapping("/about")
    public String about(Model model){return "about";}


}
