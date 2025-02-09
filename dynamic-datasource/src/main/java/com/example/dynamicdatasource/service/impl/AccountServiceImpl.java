package com.example.dynamicdatasource.service.impl;

import com.example.dynamicdatasource.entity.AccountPO;
import com.example.dynamicdatasource.mapper.AccountMapper;
import com.example.dynamicdatasource.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create DateTime: 2025/2/9 00:48
 *
 * @author zhangchangsheng
 **/
@Service
public class AccountServiceImpl  implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public void saveBatch(List<AccountPO> list) {
        accountMapper.saveBatch(list);
    }

    @Override
    public List<AccountPO> list() {
        return accountMapper.list();
    }

    @Override
    public List<AccountPO> listByTenantId(Long  tenantId) {
        return accountMapper.listByTenantId(tenantId);
    }
}
