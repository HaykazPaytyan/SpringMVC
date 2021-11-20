package com.base.springmvc.controller.auth;


import com.base.springmvc.dto.AdminLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @GetMapping("/login")
    public String showAdminLoginPage(Model model){
        AdminLoginDto adminDto = new AdminLoginDto();
        model.addAttribute("adminDto",adminDto);
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute("adminDto") @Valid AdminLoginDto adminDto,
            BindingResult result
    ){
        if (result.hasErrors()){
            return "admin/login";
        }

      return "redirect:/admin/show";
    }
}
