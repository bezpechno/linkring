package com.linkring.beta.controllers;

import com.linkring.beta.domain.*;
import com.linkring.beta.repository.BackgroundImageRepo;
import com.linkring.beta.repository.PostRepo;
import com.linkring.beta.repository.UserRepo;
import com.linkring.beta.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UserLegacyService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private DonateAddressService DonateAddressService;
    @Autowired
    private BackgroundImageService backgroundImageService;

    @GetMapping("/register")
    public String register(Model model) {
        return "register";
    }

    @PostMapping("/register")
    public String newUser(User user, Map<String, Object> model) {
        User userExist = userService.getUserByUsername(user.getUsername());
        if (userExist != null) {
            model.put("Message", "Username is already used");
            return "register";
        }
        user.setStatuses(Collections.singleton(Status.USER));
        userService.save(user);
        User newuser = userService.getUserByUsername(user.getUsername());
        Optional<BackgroundImage> image = backgroundImageService.findByUsername(newuser);
        BackgroundImage newImage = image.orElseGet(() -> new BackgroundImage("", newuser));
        newImage.setUrl("");
        backgroundImageService.save(newImage);
        Optional<DonateAddress> donate_link = DonateAddressService.findByUsername(user);
        DonateAddress donateAddress = donate_link.orElseGet(() -> new DonateAddress("", newuser));
        donateAddress.setAddress("");
        DonateAddressService.save(donateAddress);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/s/{username}")
    public String basicService(@PathVariable String username,Model model) {

        User userExist = userService.getUserByUsername(username);

        if (userExist != null) {
            User user = userService.getUserByUsername(username);
            System.out.println(user.getId());
            List<Post> posts = postService.getPostsByUsername(user);


            Optional<DonateAddress> donate_link = DonateAddressService.findByUsername(user);
            if (donate_link.isPresent()) {
                model.addAttribute("donate_link", donate_link.get());
                model.addAttribute("donate_link_absolute", "https://www.blockchain.com/btc/address/" + donate_link.get().getAddress());
            }
            Optional<BackgroundImage> background = backgroundImageService.findByUsername(user);
            background.ifPresent(backgroundImage -> model.addAttribute("image_url", backgroundImage));
            model.addAttribute("url",username);
            model.addAttribute("posts",posts);
            System.out.println(posts);

            return ("blogpost");
        } else
            return ("oops");
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}
