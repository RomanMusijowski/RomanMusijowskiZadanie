package com.zadanie.RomanMusijowskiZadanie.controller;

import com.zadanie.RomanMusijowskiZadanie.dtos.UserDto;
import com.zadanie.RomanMusijowskiZadanie.forms.UserForm;
import com.zadanie.RomanMusijowskiZadanie.models.User;
import com.zadanie.RomanMusijowskiZadanie.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @RequestMapping("/list")
    public String listUsers(Model model){

        List<UserDto> users = userService.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveOrUpdate(@Valid UserForm userForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            log.error("UserForm hasErrors");
            return "user/userForm";
        }

        userService.saveUser(
                new User(null,
                userForm.getUsername(),
                userForm.getPassword(),
                true,
                Collections.emptySet(),
                Collections.emptySet()));
        log.info("User has been created.");
        return "redirect:user/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        log.info("Delete by id - "+ id);
        userService.deleteById(id);
        return "redirect:/user/list";
    }

    @RequestMapping("/new")
    public String newUser(Model model){
        model.addAttribute("userForm", new UserForm());
        return "user/userForm";
    }


}
