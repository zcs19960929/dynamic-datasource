package com.example.dynamicdatasource.base.result;

import com.example.dynamicdatasource.base.error.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 * Create DateTime: 2023/1/31 11:45
 *
 * @author zhangchangsheng01@gmail.com
 */
@Data
public class Response implements Serializable {

    private static final long serialVersionUID = 9001101487984755344L;

    private Integer code;
    private String status;
    private String message;
    private Object data;

    public Response() {
    }

    public static Response success() {
        return success(null);
    }

    public static Response success(Object data) {
        Response response = new Response();
        response.setCode(BaseResponseStatus.DEFAULT_SUCCESS_CODE);
        response.setStatus(BaseResponseStatus.DEFAULT_SUCCESS);
        response.setMessage(BaseResponseStatus.DEFAULT_SUCCESS_DETAIL);
        response.setData(data);
        return response;
    }

    public static Response failed(ErrorEnum errorEnum) {
        Response response = new Response();
        response.setCode(errorEnum.getCode());
        response.setStatus(BaseResponseStatus.DEFAULT_FAIL);
        response.setMessage(errorEnum.getErrorDescription());
        response.setData(null);
        return response;
    }

    public static Response failed(String message) {
        Response response = new Response();
        response.setCode(BaseResponseStatus.DEFAULT_FAIL_CODE);
        response.setStatus(BaseResponseStatus.DEFAULT_FAIL);
        response.setMessage(message);
        response.setData(null);
        return response;
    }
}
