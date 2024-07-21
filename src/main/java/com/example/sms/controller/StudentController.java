package com.example.sms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.sms.entity.Student;
import com.example.sms.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    
    @GetMapping("/student_new")
    public String newStudent() {
        return "newStudent";
    }

    @GetMapping("/available_students")
    public String availableStudents(Model model) {
        List<Student> studentList = studentService.getAllStudents();
        model.addAttribute("students", studentList); 
        return "available_students"; 
    }
    @PostMapping("/save")
    public String addStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/available_students";
    }
    

    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable("id") Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }
    
    @PostMapping("/editStudent/{id}")
    public String updateStudent(@PathVariable("id") Long id, @ModelAttribute Student student, RedirectAttributes redirectAttributes) {
        student.setId(id); 
        studentService.save(student);
        redirectAttributes.addAttribute("success", true);
        return "redirect:/available_students";
    }
    
    @RequestMapping("/deleteStudent/{id}")
	public String deleteStudent(@PathVariable("id") int id)
	{
		studentService.deleteById(id);
		return "redirect:/available_students";
	}


}
