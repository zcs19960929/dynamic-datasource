package com.example.dynamicdatasource.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.dynamicdatasource.entity.AccountPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create DateTime: 2025/2/9 00:49
 *
 * @author zhangchangsheng
 **/
@DS("sharding")
public interface AccountMapper {

    @Insert("<script>insert into account (tenant_id, name) values " +
            "<foreach collection='list' item='item' index='index' separator=','>" +
            "(#{item.tenantId}, #{item.name})" +
            "</foreach></script>")
    void saveBatch(@Param("list") List<AccountPO> list);

    @Select("select * from account")
    List<AccountPO> list();


    @Select("select * from account where tenant_id = #{tenantId}")
    List<AccountPO> listByTenantId(Long tenantId);
}
