package com.example.dynamicdatasource.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dynamicdatasource.entity.AccountPO;
import com.example.dynamicdatasource.mapper.AccountMapper;
import com.example.dynamicdatasource.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * Create DateTime: 2025/2/9 00:48
 *
 * @author zhangchangsheng
 **/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, AccountPO> implements AccountService {
}
