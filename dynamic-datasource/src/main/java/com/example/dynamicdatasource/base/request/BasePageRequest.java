package com.example.dynamicdatasource.base.request;


import com.example.dynamicdatasource.base.error.CommonException;
import com.example.dynamicdatasource.base.error.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * Description:
 * Create DateTime: 2023/1/31 10:26
 *
 * @author zhangchangsheng01@gmail.com
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageRequest extends BaseRequest {

    private Integer pageNum = 1;


    private Integer pageSize = 20;

    @Override
    public void validate() {
        super.validate();
        if (Objects.isNull(pageNum) || Objects.isNull(pageSize)) {
            throw new CommonException(ErrorEnum.NOT_PARAM, "Missing pagination parameter");
        }
        if (pageSize > 500) {
            throw new CommonException(ErrorEnum.ARG_ERROR, "Pagination parameter setting error, page size cannot exceed 500");
        }
    }
}
