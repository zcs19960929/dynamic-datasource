package com.example.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 订单表
 * Create DateTime: 2025/3/27 17:35
 *
 * @author zhangchangsheng
 **/
@Data
@TableName("t_order")
@Accessors(chain = true)
public class OrderPO {

    private Long id;
    
    private Long tenantId;

    private String status;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
