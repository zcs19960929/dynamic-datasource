package com.example.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dynamicdatasource.entity.StudentPO;

/**
 * Description:
 * Create DateTime: 2023/9/6 17:32
 *
 * @author zhangchangsheng
 * @module
 */
@DS("master")
public interface StudentMapper extends BaseMapper<StudentPO> {
}
