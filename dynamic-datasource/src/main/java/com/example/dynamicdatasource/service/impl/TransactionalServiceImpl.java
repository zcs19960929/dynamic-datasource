package com.example.dynamicdatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DSTransactional;
import com.example.dynamicdatasource.entity.StudentPO;
import com.example.dynamicdatasource.entity.TeacherPO;
import com.example.dynamicdatasource.service.StudentService;
import com.example.dynamicdatasource.service.TeacherService;
import com.example.dynamicdatasource.service.TransactionalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Description:
 * Create DateTime: 2023/9/6 18:20
 *
 * @author zhangchangsheng
 * @module
 */
@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Resource
    private TeacherService teacherService;

    @Resource
    private StudentService studentService;

    /**
     * save teacher and student with transaction
     *
     * @param teacher teacher
     * @param student student
     */
    @Override
    @DSTransactional
    public void saveTeacherAndStudent(TeacherPO teacher, StudentPO student) {
        teacherService.save(teacher);
//        int a = 1 / 0;
        studentService.save(student);
    }
}
