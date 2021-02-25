package com.bukeji.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bukeji.model.Student;
import com.bukeji.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Reference(interfaceClass = StudentService.class,version = "1.0.0",check = false)
    private StudentService studentService;

    @RequestMapping("/login.do")
    public String getStudent(HttpServletRequest request, Model model, Integer id) {

        log.debug("====login====");
        Student student = studentService.getStudentById(id);

        request.getSession().setAttribute("student", student);

        model.addAttribute("student", student);

        return "detail";
    }

    @RequestMapping("/getCount.do")
    public String getCount(Model model) {

        log.debug("====count=====");
        Integer count = studentService.getCount();

        model.addAttribute("count", count);
        return "detail";
    }

}
