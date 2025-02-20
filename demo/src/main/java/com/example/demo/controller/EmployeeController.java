package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Display form on landing page
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // Save employee details
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/displayAll";
    }

    // Display all employees
    @GetMapping("/displayAll")
    public String displayAll(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "displayAll";
    }

    // Display specific employee by ID
    @GetMapping("/display/{employeeID}")
    public String displayEmployee(@PathVariable String employeeID, Model model) {
        Optional<Employee> employee = employeeRepository.findById(employeeID);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
        } else {
            model.addAttribute("error", "Employee not found");
        }
        return "displayEmployee";
    }
}
