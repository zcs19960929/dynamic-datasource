package com.example.dynamicdatasource.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dynamicdatasource.entity.StudentPO;
import com.example.dynamicdatasource.mapper.StudentMapper;
import com.example.dynamicdatasource.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Create DateTime: 2023/9/6 17:30
 *
 * @author zhangchangsheng
 * @module
 */
@Service
@DS("master")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentPO> implements StudentService {

}
