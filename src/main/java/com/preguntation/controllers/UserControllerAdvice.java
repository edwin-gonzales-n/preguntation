package com.preguntation.controllers;

import com.preguntation.models.user;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class UserControllerAdvice {

    @ModelAttribute("user")
    public user populateUser(Principal principal, Model model) {
        return principal == null ? new user() : (user) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
