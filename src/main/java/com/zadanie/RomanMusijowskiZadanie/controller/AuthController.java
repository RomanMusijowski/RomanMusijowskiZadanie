package com.zadanie.RomanMusijowskiZadanie.controller;

import com.zadanie.RomanMusijowskiZadanie.models.RoleName;
import com.zadanie.RomanMusijowskiZadanie.models.User;
import com.zadanie.RomanMusijowskiZadanie.services.GalleryService;
import com.zadanie.RomanMusijowskiZadanie.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final GalleryService galleryService;

    @RequestMapping({"/", ""})
    public String index(ModelMap modelMap, Authentication  authentication){

        User user = userService.findByUsername(authentication.getName());
        modelMap.addAttribute("user", user);
        log.info("Return index page for userName - "+ user.getUsername());

        if (user.getRoles().stream()
                .anyMatch(role -> role.getName().equals(RoleName.ROLE_ADMIN))
        ){
            modelMap.addAttribute("users" ,userService.findAll());
            modelMap.addAttribute("galleries", galleryService.findAll());
            return "admin/home";
        }

        modelMap.addAttribute("galleries", galleryService.findAllByUser(user.getId()));
        return "client/home";

    }

    @RequestMapping("/access_denied")
    public String notAuth(){
        log.error("Access denied");
        return "access_denied";
    }

    @RequestMapping("/login")
    public String loginForm(){
        log.info("Login form");
        return "login";
    }
}
