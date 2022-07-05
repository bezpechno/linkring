package com.linkring.beta.controllers;

import com.linkring.beta.domain.BackgroundImage;
import com.linkring.beta.domain.DonateAddress;
import com.linkring.beta.domain.Email;
import com.linkring.beta.domain.User;
import com.linkring.beta.repository.BackgroundImageRepo;
import com.linkring.beta.repository.DonateAddressRepo;
import com.linkring.beta.service.BackgroundImageService;
import com.linkring.beta.service.DonateAddressService;
import com.linkring.beta.service.EmailRepoService;
import com.linkring.beta.service.UserLegacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class BusinessController {
    @Autowired
    private UserLegacyService userLegacyService;
    @Autowired
    private DonateAddressService DonateAddressService;
    @Autowired
    EmailRepoService emailRepoService;
    @Autowired
    BackgroundImageService backgroundImageService;

    @GetMapping("/styling")
    public String appearanceEditor(@AuthenticationPrincipal User username,Model model){
        User user = userLegacyService.getUserByUsername(username.getUsername());
        Optional<BackgroundImage> image = backgroundImageService.findByUsername(user);
        if (!image.isEmpty())
            model.addAttribute("image",image.get());
        model.addAttribute("isActive","nav-link active");
        return("styling");
    }
    @PostMapping("/styling")
    public String addUrl(@AuthenticationPrincipal User username,
                         @RequestParam String url,
                         Model model){
        User user = userLegacyService.getUserByUsername(username.getUsername());
        Optional<BackgroundImage> image = backgroundImageService.findByUsername(user);
        BackgroundImage newImage = image.isPresent()?image.get():new BackgroundImage(url,username);
        newImage.setUrl(url);
        backgroundImageService.save(newImage);
        if (!image.isEmpty())
            model.addAttribute("image",image.get());
        return ("styling");
    }
    @GetMapping("/products")
    public String products(Model model){
        return "shop";
    }
    @GetMapping("/settings")
    public String settings(@AuthenticationPrincipal User username,Model model){
        User user = userLegacyService.getUserByUsername(username.getUsername());
        System.out.println("Settings> userAuth: " + user.getUsername());
        Optional<Email> mail = emailRepoService.findByUsername(user);
        if (!mail.isEmpty())
            model.addAttribute("mail",mail.get());
        return "settings";
    }
    @PostMapping("/settings")
    public String settingsPost(@AuthenticationPrincipal User username,
                               @RequestParam String email,
                               Model model){
        User user = userLegacyService.getUserByUsername(username.getUsername());

        Optional<Email> mail = emailRepoService.findByUsername(user);
        Email newMail = mail.isPresent()?mail.get():new Email(email, username);
        newMail.setEmail(email);
        emailRepoService.save(newMail);
        if (!mail.isEmpty())
            model.addAttribute("mail",mail.get());
        return "settings";
    }


    //Premium
    @GetMapping("/donate")
    public String moneyMaker(@AuthenticationPrincipal User username,Model model){
        User user = userLegacyService.getUserByUsername(username.getUsername());
        Optional<DonateAddress> donate_link = DonateAddressService.findByUsername(user);
        if (!donate_link.isEmpty())
            model.addAttribute("donate_link",donate_link.get());
        return "donate";
    }
    @PostMapping("/donate")
    public String addMoneyMaker(@AuthenticationPrincipal User username,
                                @RequestParam String address,
                                Model model){


        System.out.println(address);
        System.out.println(username);

        User user = userLegacyService.getUserByUsername(username.getUsername());
        Optional<DonateAddress> donate_link = DonateAddressService.findByUsername(user);
        DonateAddress donateAddress = donate_link.isPresent()?donate_link.get():new DonateAddress(address, username);
        donateAddress.setAddress(address);
        DonateAddressService.save(donateAddress);
        if (!donate_link.isEmpty())
            model.addAttribute("donate_link",donate_link.get());
        return "donate";
    }

    @GetMapping("/extras")
    public String extras(Model model){
        return "shop";
    }

    @GetMapping("/promote")
    public String promoterManager(Model model){
        return "shop";
    }

    @GetMapping("/special")
    public String special(Model model){
        return "shop";
    }
}
