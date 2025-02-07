package com.example.dynamicdatasource;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dynamicdatasource.entity.StudentPO;
import com.example.dynamicdatasource.entity.TeacherPO;
import com.example.dynamicdatasource.service.StudentService;
import com.example.dynamicdatasource.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class DynamicDatasourceApplicationTests {

    @Resource
    private StudentService studentService;

    @Resource
    private TeacherService teacherService;

    @Test
    void contextLoads() {
    }

    @Test
    void createStudent() {
        StudentPO studentPO = StudentPO.builder()
                .name("zhangsan")
                .age(16)
                .build();
        studentService.save(studentPO);
        System.out.println(studentPO);
    }

    @Test
    void queryStudent() {
        StudentPO studentPO = studentService.getOne(new LambdaQueryWrapper<StudentPO>()
                .eq(StudentPO::getName, "zhangsan")
                .last("limit 1"));
        System.out.println(studentPO);
    }

    @Test
    void createTeacher() {
        TeacherPO teacherPO = TeacherPO.builder()
                .name("teacher ma")
                .age(38)
                .build();
        teacherService.save(teacherPO);
        System.out.println(teacherPO);
    }

    @Test
    void queryTeacher() {
        TeacherPO teacherPO = teacherService.getOne(new LambdaQueryWrapper<TeacherPO>()
                .eq(TeacherPO::getName, "teacher ma")
                .last("limit 1"));
        System.out.println(teacherPO);
    }
}
