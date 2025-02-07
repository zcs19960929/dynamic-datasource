package com.example.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dynamicdatasource.entity.TeacherPO;

/**
 * Description:
 * Create DateTime: 2023/9/6 17:31
 *
 * @author zhangchangsheng
 */
@DS("slave_1")
public interface TeacherMapper extends BaseMapper<TeacherPO> {

}
