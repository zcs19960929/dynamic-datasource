package com.example.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dynamicdatasource.entity.OrderPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单
 * Create DateTime: 2025/3/27 19:14
 *
 * @author zhangchangsheng
 **/
@DS("sharding")
public interface OrderMapper {

    @Insert("<script>insert into t_order (tenant_id,status,description) values " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.tenantId},#{item.status},#{item.description})" +
            "</foreach></script>")
    void saveBatch(@Param("list") List<OrderPO> list);

    @Insert("insert into t_order (tenant_id, status, description) value (#{orderPO.tenantId},#{orderPO.status},#{orderPO.description} )")
    boolean save(@Param("orderPO") OrderPO orderPO);

    @Select("select * from t_order where tenant_id = #{tenantId} ")
    List<OrderPO> listByTenantId(@Param("tenantId") Long tenantId);
}
