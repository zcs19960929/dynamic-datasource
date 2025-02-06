package com.example.dynamicdatasource.filter;

import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日志统一处理
 */
@Order(1)
@Slf4j
@WebFilter(urlPatterns = "/test/*",filterName = "traceIdFilter")
@Component
public class TraceIdFilter implements Filter {

    public final static String MDC_TRACE_ID = "MDC_LOG_ID";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String traceId = httpRequest.getHeader(MDC_TRACE_ID);
        if (StringUtils.isBlank(traceId)) {
            traceId = IdUtil.fastSimpleUUID();
        }
        MDC.put(MDC_TRACE_ID, traceId);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader(MDC_TRACE_ID, traceId);

        log.info("access:{}, traceId:{}", httpRequest.getRequestURI(), traceId);

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(httpServletRequest);
        mutableRequest.putHeader("X-Request-Id",traceId);
        chain.doFilter(mutableRequest, response);
    }
}
