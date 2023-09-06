package com.example.dynamicdatasource.base.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Description:
 * Create DateTime: 2023/1/31 11:45
 *
 * @author zhangchangsheng01@gmail.com
 */
@Getter
@AllArgsConstructor
public enum ErrorEnum {
    SUCCESS(200, "Execution successful"),

    UN_KNOWN(400, "The server is having a little trouble, please try again later"),

    NOT_FOUND(404, "The resource does not exist"),
    ARG_ERROR(405, "Parameter binding error"),
    NOT_PARAM(406, "The required parameter is empty"),

    ARG_PARSE_ERROR(407, "Parameter serialization failed, please check if the format is correct"),

    METHOD_NOT_ALLOWED(408, "The request method is not supported by the server"),
    ;


    private int code;

    private String errorDescription;


}
