package com.example.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.dynamicdatasource.entity.AccountPO;

/**
 * Create DateTime: 2025/2/9 00:49
 *
 * @author zhangchangsheng
 **/
@DS("sharding")
public interface AccountMapper extends BaseMapper<AccountPO> {

}
