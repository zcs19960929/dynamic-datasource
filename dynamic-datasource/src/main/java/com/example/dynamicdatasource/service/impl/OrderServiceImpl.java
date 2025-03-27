package com.example.dynamicdatasource.service.impl;

import com.example.dynamicdatasource.entity.OrderPO;
import com.example.dynamicdatasource.mapper.OrderMapper;
import com.example.dynamicdatasource.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单
 * Create DateTime: 2025/3/27 19:13
 *
 * @author zhangchangsheng
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public void saveBatch(List<OrderPO> list) {
        orderMapper.saveBatch(list);
    }

    @Override
    public boolean save(OrderPO orderPO) {
        return orderMapper.save(orderPO);
    }

    @Override
    public List<OrderPO> listByTenantId(Long tenantId) {
        return orderMapper.listByTenantId(tenantId);
    }

}
