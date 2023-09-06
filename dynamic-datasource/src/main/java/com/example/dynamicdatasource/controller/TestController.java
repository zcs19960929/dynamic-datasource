package com.example.dynamicdatasource.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.example.dynamicdatasource.entity.StudentPO;
import com.example.dynamicdatasource.entity.TeacherPO;
import com.example.dynamicdatasource.service.StudentService;
import com.example.dynamicdatasource.service.TeacherService;
import com.example.dynamicdatasource.service.TransactionalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description: demo
 * Create DateTime: 2023/9/6 16:56
 *
 * @author zhangchangsheng
 * @module
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    @Resource
    private TransactionalService transactionalService;

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/teacher")
    public TeacherPO getTeacher(@RequestParam("id") Long id) {
        return teacherService.getById(id);
    }

    @GetMapping("/student")
    public StudentPO getStudent(@RequestParam("id") Long id) {
        return studentService.getById(id);
    }

    @PostMapping("saveTeacherAndStudent")
    public String saveTeacherAndStudent(@RequestBody Map<String,Object> request) {
        StudentPO student = JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(request.get("student"))), StudentPO.class);
        TeacherPO teacher = JSON.toJavaObject(JSON.parseObject(JSON.toJSONString(request.get("teacher"))), TeacherPO.class);
        transactionalService.saveTeacherAndStudent(teacher, student);
        return "success";
    }

}
