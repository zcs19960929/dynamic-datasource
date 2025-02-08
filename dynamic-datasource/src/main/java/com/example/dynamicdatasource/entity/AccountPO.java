package com.example.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Create DateTime: 2025/2/8 22:13
 *
 * @author zhangchangsheng
 **/
@Data
@TableName("account")
@Accessors(chain = true)
public class AccountPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long tenantId;

    private String name;

    private String desc;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
