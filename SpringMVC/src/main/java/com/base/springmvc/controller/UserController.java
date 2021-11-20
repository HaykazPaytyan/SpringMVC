package com.base.springmvc.controller;

import com.base.springmvc.domain.User;
import com.base.springmvc.dto.UserDto;
import com.base.springmvc.service.Implementation.UserServiceImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private PasswordEncoder encoder;
    UserServiceImplementation service;
    ModelMapper modelMapper;

    public UserController(UserServiceImplementation service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") String id, Model model){
        long userId = Long.parseLong(id);
        User user = service.findById(userId);
        UserDto userDto = new UserDto();
        model.addAttribute("user",user);
        model.addAttribute("userDto",userDto);
        model.addAttribute("userId",userId);
        return "user/show";
    }

    @PutMapping("/{id}/edit")
    public String update(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "user/show";
        }

      return "redirect:/user";
    }


}
