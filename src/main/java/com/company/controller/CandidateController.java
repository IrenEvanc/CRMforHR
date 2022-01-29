package com.company.controller;

import com.company.entity.Candidate;
import com.company.entity.Employee;
import com.company.entity.User;
import com.company.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CandidateController {
    private final CandidateRepository candidateRepository;
    private final EmployeeRepository employeeRepository;
    private final StatusRepository statusRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    @Autowired
    public CandidateController(CandidateRepository candidateRepository, EmployeeRepository employeeRepository, StatusRepository statusRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.candidateRepository = candidateRepository;
        this.employeeRepository = employeeRepository;
        this.statusRepository = statusRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

//    @GetMapping("/candidate")
//    public String homePage(){
//        return "candidate";
//    }

    @GetMapping("/candidate")
    public String showCandidateList(Model model) {
        model.addAttribute("candidates", candidateRepository.findAll());
        model.addAttribute("tags", tagRepository.findAll());
        return "candidate";
    }

    @GetMapping("/closex")
    public String closeInfoForm(Model model) {
        return "redirect:/candidate#tab_candidate";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        System.out.println(candidate.getUser());
        model.addAttribute("candidate", candidate);
        model.addAttribute("user", user);

        return "popup-infoCandidateUpdate";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Candidate candidate, BindingResult result, Model model) {
        if (result.hasErrors()) {
//            user.setId(id);
            candidate.setId(id);
            return "popup-infoCandidateUpdate";
        }
        System.out.println(candidate);
        System.out.println(candidate.getUser());
//        userRepository.save(user);
        candidateRepository.save(candidate);

        return "redirect:/candidate#tab_candidate";
    }

//    @GetMapping("/infocand/{id}")
//    public String showInfoForm(@PathVariable("id") long id, Model model) {
//        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//        model.addAttribute("candidate", candidate);
//        model.addAttribute("tags", tagRepository.findAll());
//        return "popup-infoCandidateUpdate";
//    }
//        @PostMapping("/emailokcand/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid Candidate candidate, @Valid User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
////            user.setId(id);
//            candidate.setId(id);
//            return "popup-infoCandidateUpdate";
//        }
////        userRepository.save(user);
//        candidateRepository.save(candidate);
//
//        return "redirect:/candidate#tab_candidate";
//    }


    //TODO ДОДЕЛАТЬ контроллер на редактирование телефона
//    @PostMapping("/phoneokcand")
//    public String phoneNewCandidate(@PathVariable("id") long id, @Valid Candidate candidate,  BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            candidate.setId(id);
//            return "popup-infoCandidate";
//        }
//
//
//        candidateRepository.save(candidate);
//
//        return "redirect:/candidate#tab_candidate";
//    }
    //TODO ДОДЕЛАТЬ контроллер на редактирование почты
    @GetMapping("/emailokcand")
    public String emailNewCandidate(Model model) {
        return "redirect:/candidate#tab_candidate";
    }
    //TODO ДОДЕЛАТЬ контроллер на добавление выбранного тега
    @GetMapping("/addtagcand")
    public String tagAddCandidate(Model model) {
        return "redirect:/candidate#tab_candidate";
    }

//    @PostMapping("/candok")
//    public String okayInfoForm(@PathVariable("id") long id, @Valid Candidate candidate, BindingResult result, Model model) {
////        System.out.println("BRGINOFADDING");
////        if (result.hasErrors()) {
////            System.out.println("ERROR????");
////            return "popup-infocand";
////        }
////        candidate.getUser().setEmail();
//
//        if (result.hasErrors()) {
//            candidate.setId(id);
//
//            return "popup-infocand";
//        }
////
//        candidateRepository.save(candidate);
//        return "redirect:/candidate#tab_candidate";
//    }

        @GetMapping("/create/{id}")
        public String addUser(@PathVariable("id") long id,  Model model) {
        System.out.println("BRGINOFADDING");
            System.out.println(id);

        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid candidate Id:" + id));
        Employee employee = new Employee();

        employee.setUser(candidate.getUser());
        employee.setPost(candidate.getPost());
        employee.setStatus(statusRepository.findByName("Работает"));

        candidateRepository.delete(candidate);
        employeeRepository.save(employee);
        System.out.println("SAVED??????");
        return "redirect:/candidate#tab_candidate";
    }
}