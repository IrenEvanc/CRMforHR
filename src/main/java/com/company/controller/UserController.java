package com.company.controller;

import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import com.company.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @GetMapping("/")
   public String homePage(Model model){
      return "menu";
   }

   @GetMapping("/in")
   public String showUserList(Model model) {
      model.addAttribute("users", userRepository.findAll());
      return "employee";
   }

   @GetMapping("/signup")
   public String showSignUpForm(User user) {
      System.out.println("TRYTOADDD");
      return "employee";
   }

   @PostMapping("/adduser")
   public String addUser(@Valid User user, BindingResult result, Model model) {
      System.out.println("BRGINOFADDING");
      if (result.hasErrors()) {
         System.out.println("ERROR????");
         return "employee";
      }

      userRepository.save(user);
      System.out.println("SAVED??????");
      return "redirect:/in";
   }
//    @GetMapping("/memu")
//    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "ManagerHRView";
//    }
//
//    @GetMapping("/signup")
//    public String showSignUpForm(User user) {
//        System.out.println("TRYTOADDD");
//        return "add-user";
//    }
//
//    @PostMapping("/adduser")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        System.out.println("BRGINOFADDING");
//        if (result.hasErrors()) {
//            System.out.println("ERROR????");
//            return "add-user";
//        }
//
//        userRepository.save(user);
//        System.out.println("SAVED??????");
//        return "redirect:/index";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        model.addAttribute("user", user);
//
//        return "update-user";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//
//        userRepository.save(user);
//
//        return "redirect:/index";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable("id") long id, Model model) {
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        userRepository.delete(user);
//
//        return "redirect:/index";
//    }

   }

