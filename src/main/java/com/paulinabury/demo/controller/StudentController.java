package com.paulinabury.demo.controller;

import com.paulinabury.demo.model.Student;
import com.paulinabury.demo.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping
    public String welcomePage(ModelMap modelMap) {
        modelMap.addAttribute("schoolName", "Wyższa Szkoła Handlowa");
        return "welcome";
    }


    @GetMapping("/student")
    public String student(ModelMap modelMap) {
        modelMap.addAttribute("studentList", studentService.getAllStudents());
        return "student";
    }


    @GetMapping("/student/{id}")
    public String student(ModelMap modelMap, @PathVariable Long id) {
        modelMap.addAttribute("student", studentService.getStudentById(id));
        return "one-student";
    }


    @GetMapping("/student/add")
    public String showStudentAdd(ModelMap modelMap) {
        modelMap.addAttribute("student", new Student());
        modelMap.addAttribute("error-msg", "błąd danych");
        return "student-add";
    }


    @PostMapping("/student/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student, final Errors error) {
        if (error.hasErrors()) {
            return "student-add";
        }
        studentService.addNewStudent(student);
        return "succeeded";
    }


    @PostMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/student";
    }

    @GetMapping("/student/delete/{id}")
    public String getDelete(@PathVariable Long id) {
        studentService.getStudentById(id);
        return "one-student";
    }


    @PostMapping("/student/{id}")
    public String updateStudent(@Valid @ModelAttribute("student") Student student,
                                @PathVariable Long id, final Errors errors) {
        if (errors.hasErrors()) {
            return "one-student";
        }
        studentService.updateStudentById(id, student);
        return "redirect:/student";
    }


}
