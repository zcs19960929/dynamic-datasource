package com.example.dynamicdatasource.service;

import com.example.dynamicdatasource.entity.OrderPO;

import java.util.List;

/**
 * 订单
 * Create DateTime: 2025/3/27 19:13
 *
 * @author zhangchangsheng
 **/
public interface OrderService {
    void saveBatch(List<OrderPO> list);

    boolean save(OrderPO orderPO);

    List<OrderPO> listByTenantId(Long tenantId);
}
