package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.entity.AccountPO;

import java.util.List;

/**
 * Create DateTime: 2025/2/9 00:47
 *
 * @author zhangchangsheng
 **/
public interface AccountService {

    void saveBatch(List<AccountPO> list);

    List<AccountPO> list();

    List<AccountPO> listByTenantId(Long tenantId);
}
