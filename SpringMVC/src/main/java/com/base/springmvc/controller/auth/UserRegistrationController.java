package com.base.springmvc.controller.auth;


import com.base.springmvc.domain.User;
import com.base.springmvc.dto.UserDto;
import com.base.springmvc.service.Implementation.UserRegistrationServiceImplementation;
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
public class UserRegistrationController {

    @Autowired
    private PasswordEncoder encoder;
    UserRegistrationServiceImplementation service;
    ModelMapper modelMapper;

    public UserRegistrationController(UserRegistrationServiceImplementation service,ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String showUserRegistrationForm(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("userDto",userDto);
        return "user/register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            BindingResult result
    ){
         if (result.hasErrors()){
              return "user/register";
         }

         User user = create(userDto);
         return "redirect:/user/" + user.getId();
    }

    private User create(UserDto userDto){

        User user = modelMapper.map(userDto,User.class);
        user.setPassword(encoder.encode(userDto.getPassword()));
        User user1 = service.store(user);
        return user1;
    }
}
