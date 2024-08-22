package com.example.demo.EmployeeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Employee;
import com.example.demo.Repository.EmployeeRepository;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute Employee employee) {
        CrudRepository.save(employee);
        return "redirect:/displayAll";
    }

    @GetMapping("/displayAll")
    @ResponseBody
    public List<Employee> displayAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/display/{id}")
    @ResponseBody
    public Employee displayEmployee(@PathVariable String id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
