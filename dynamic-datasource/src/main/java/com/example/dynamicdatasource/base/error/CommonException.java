package com.example.dynamicdatasource.base.error;

import lombok.Getter;
import lombok.Setter;

/**
 * Description:
 * Create DateTime: 2023/1/31 11:45
 *
 * @author zhangchangsheng01@gmail.com
 */
@Getter
@Setter
public class CommonException extends RuntimeException {


    private ErrorEnum error;


    private String detail;


    public CommonException(ErrorEnum error) {
        this(error, error.getErrorDescription());
    }

    public CommonException(ErrorEnum error, Throwable throwable) {
        super(error.getErrorDescription(), throwable);

        this.error = error;
        this.detail = error.getErrorDescription();
    }

    public CommonException(ErrorEnum error, String detail) {
        super(detail);

        this.error = error;
        this.detail = detail;
    }

}
