package com.example.dynamicdatasource.advice;


import com.example.dynamicdatasource.base.error.CommonException;
import com.example.dynamicdatasource.base.error.ErrorEnum;
import com.example.dynamicdatasource.base.response.BaseResponse;
import com.example.dynamicdatasource.base.result.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Description: 全局异常处理处理器
 * Create DateTime: 2021/04/12 15:04
 *
 * @author zcs19960929
 */
@Slf4j
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(value = CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handlerNoHandlerFoundException(CommonException exception) {
        BaseResponse baseResponse = getBaseResponse();
        baseResponse.setMessage(exception.getDetail());
        return baseResponse;
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        BaseResponse baseResponse = getBaseResponse();
        baseResponse.setMessage(ErrorEnum.NOT_FOUND.getErrorDescription());
        return baseResponse;
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handlerIllegalArgumentException(IllegalArgumentException exception) {
        BaseResponse baseResponse = getBaseResponse();
        baseResponse.setMessage(ErrorEnum.ARG_ERROR.getErrorDescription());
        return baseResponse;
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public BaseResponse handlerHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        BaseResponse baseResponse = getBaseResponse();
        baseResponse.setMessage(ErrorEnum.METHOD_NOT_ALLOWED.getErrorDescription());
        return baseResponse;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        BaseResponse baseResponse = getBaseResponse();
        baseResponse.setMessage(ErrorEnum.ARG_PARSE_ERROR.getErrorDescription());
        return baseResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handlerException(Exception exception) {
        BaseResponse baseResponse = getBaseResponse();
        baseResponse.setMessage(exception.getMessage());
        return baseResponse;
    }

    protected BaseResponse getBaseResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(BaseResponseStatus.DEFAULT_FAIL_CODE);
        baseResponse.setStatus(BaseResponseStatus.DEFAULT_FAIL);
        baseResponse.setRequestId(MDC.get("MDC_LOG_ID"));
        return baseResponse;
    }

}
