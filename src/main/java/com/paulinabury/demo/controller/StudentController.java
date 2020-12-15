package com.paulinabury.demo.controller;

import com.paulinabury.demo.model.Student;
import com.paulinabury.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@Slf4j
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

    @GetMapping("/studyPlan")
    public String planPage() {
        return "study-plan";
    }


    @GetMapping("/student")
    public String student(ModelMap modelMap, String keyword) {
        if (keyword != null) {
            modelMap.addAttribute("studentList", studentService.findByKeyword(keyword));
        } else {
            modelMap.addAttribute("studentList", studentService.getAllStudents());
        }
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
        return "redirect:/student";
    }


    @PostMapping ("student/save")
    public String save() {
        List<Student> studentList = studentService.getAllStudents();
        try {
            studentService.save(studentList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/student";
    }

    @GetMapping("student/save")
    public String showSaveStudents(ModelMap modelMap) {
        modelMap.addAttribute("studentList", studentService.getAllStudents());
        return "student-save";
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
        return "redirect:/student/" + id;
    }


}
