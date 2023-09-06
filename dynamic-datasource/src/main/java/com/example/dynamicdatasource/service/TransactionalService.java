package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.entity.StudentPO;
import com.example.dynamicdatasource.entity.TeacherPO;

/**
 * Description:
 * Create DateTime: 2023/9/6 18:19
 *
 * @author zhangchangsheng
 */
public interface TransactionalService {

    /**
     * save teacher and student with transaction
     * @param teacher teacher
     * @param student student
     */
    void saveTeacherAndStudent(TeacherPO teacher, StudentPO student);

}
