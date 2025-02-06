package com.example.dynamicdatasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dynamicdatasource.entity.TeacherPO;
import com.example.dynamicdatasource.mapper.TeacherMapper;
import com.example.dynamicdatasource.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * Description:
 * Create DateTime: 2023/9/6 17:31
 *
 * @author zhangchangsheng
 * @module
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, TeacherPO> implements TeacherService{
}
