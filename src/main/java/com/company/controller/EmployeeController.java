package com.company.controller;

import com.company.entity.Employee;
import com.company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final CandidateRepository candidateRepository;
    private final EmployeeITRepository employeeITRepository;
    private final EmployeeHrRepository employeeHrRepository;
    private final StatusRepository statusRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, CandidateRepository candidateRepository, EmployeeITRepository employeeITRepository, EmployeeHrRepository employeeHrRepository, StatusRepository statusRepository) {
        this.employeeRepository = employeeRepository;
        this.candidateRepository = candidateRepository;
        this.employeeITRepository = employeeITRepository;
        this.employeeHrRepository = employeeHrRepository;
        this.statusRepository = statusRepository;
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
        model.addAttribute("candidatess", candidateRepository.findAll());
        model.addAttribute("employeesIt", employeeITRepository.findAll());
        model.addAttribute("employeesHr", employeeHrRepository.findAll());
        return "employee";}

    @GetMapping("info/{id}")
    public String showInfoForm(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("employees", employee);
        return "popup-info";
    }

    @GetMapping("/close")
    public String closeInfoForm(Model model) {
        return "redirect:/employee#tab_employee";
    }


//    @GetMapping("/empok")
//    public String okayInfoForm(Model model) {
//        return "redirect:/employee#tab_employee";
//    }

    //TODO ДОДЕЛАТЬ контроллер на редактирование телефона
    @GetMapping("/phoneok")
    public String phoneNew(Model model) {
        return "redirect:/employee#tab_employee";
    }
    //TODO ДОДЕЛАТЬ контроллер на редактирование почты
    @GetMapping("/emailok")
    public String emailNew(Model model) {
        return "redirect:/employee#tab_employee";
    }



//    @PostMapping("/info/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid Employee employee, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            employee.setId(id);
//            return "popup-info";
//        }
//
//        employeeRepository.save(employee);
//
//        return "redirect:/employee";
//    }
//    @PostMapping("info/{id}/#")
//    public String closeInfoForm(@Valid Employee employee, BindingResult result, Model model) {
//        System.out.println("BRGINOFADDING");
//
//            return "redirect:/employee";
//        }

//
//    @GetMapping("/popup-create")
//    public String addEmployeeList(Model model) {
//        model.addAttribute("candidatess", candidateRepository.findAll());
//        return "popup-create-acount";
//    }

//    @PostMapping("/create")
//    public String addUser(BindingResult result, @PathVariable("id") long id, Model model) {
//        System.out.println("BRGINOFADDING");
//        if (result.hasErrors()) {
//            System.out.println("ERROR????");
//            return "popup_acount";
//        }
//
//        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid candidate Id:" + id));
//        Employee employee1 = new Employee();
//
//        employee1.setUser(candidate.getUser());
//        employee1.setPost(candidate.getPost());
////        employee1.setStatus(new Status());
//        employee1.setStatus(statusRepository.findById(1L));
//
//        candidateRepository.delete(candidate);
//        employeeRepository.save(employee1);
//        System.out.println("SAVED??????");
//        return "redirect:/employee";
//    }
//
}
