package com.example.dynamicdatasource.base.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description:
 * Create DateTime: 2023/1/31 10:24
 *
 * @author zhangchangsheng01@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRequest extends AbstractBaseRequest {

    @Override
    public void validate() {

    }
}
