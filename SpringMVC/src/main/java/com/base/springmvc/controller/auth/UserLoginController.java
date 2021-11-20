package com.base.springmvc.controller.auth;

import com.base.springmvc.domain.User;
import com.base.springmvc.dto.UserLoginDto;
import com.base.springmvc.service.Implementation.UserLoginServiceImplementation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private PasswordEncoder encoder;
    UserLoginServiceImplementation service;
    ModelMapper modelMapper;

    public UserLoginController(UserLoginServiceImplementation service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String showUserLoginForm(Model model){
        UserLoginDto userDto = new UserLoginDto();
        model.addAttribute("userDto",userDto);
        return  "user/login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute("userDto") @Valid UserLoginDto userDto,
            BindingResult result
    ){

        if (result.hasErrors()){
            return "user/login";
        }

        User user = authCheck(userDto);

        if (user != null) {
             return "redirect:/user/" + user.getId();
        }

        return "user/login";

    }

    private User authCheck(UserLoginDto userDto){

        User user = modelMapper.map(userDto,User.class);
        User user1 = service.attempt(user);

        if (encoder.matches(user.getPassword(),user1.getPassword())) {
            return user1;
        }

        return null;
    }
}
