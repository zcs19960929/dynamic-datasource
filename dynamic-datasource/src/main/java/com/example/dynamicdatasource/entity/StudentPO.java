package com.example.dynamicdatasource.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Description: stu pojo
 * Create DateTime: 2023/9/6 17:27
 *
 * @author zhangchangsheng
 * @module
 */
@Data
@TableName("student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer age;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
