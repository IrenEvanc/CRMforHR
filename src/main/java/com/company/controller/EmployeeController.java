package com.company.controller;

import com.company.entity.Employee;
import com.company.entity.User;
import com.company.repository.EmployeeRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

//    @GetMapping("/employee")
//    public String homePage(){
//        return "employee";
//    }

//    @GetMapping("/employee")
//    public String showUserListEmployee(@PathVariable("id") long id, Model model) {
//
//        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        model.addAttribute("users", user);
////        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
////        model.addAttribute("workers", employee);
//        model.addAttribute("workers", employeeRepository.findAll());
////        model.addAttribute("users", userRepository.findAll());
//        return "employee";
//    }

    @GetMapping("/employee")
    public String showEmployeeList(Model model) {
        model.addAttribute("employees", employeeRepository.findAll());
//        model.addAttribute("users", userRepository.findAll());
        return "employee";
    }


}
