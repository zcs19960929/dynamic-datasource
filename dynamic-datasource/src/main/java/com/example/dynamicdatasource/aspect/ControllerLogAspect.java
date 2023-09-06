package com.example.dynamicdatasource.aspect;




import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.InputStreamSource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2017/03/16
 *
 * @author annpeter.it@gmail.com
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class ControllerLogAspect {

    @Value("${spring.profiles.active}")
    private String profile;




    @Pointcut("@within(org.springframework.stereotype.Controller) && execution(* com.example.dynamicdatasource.controller..*.*(..)) ")
    public void controller() {
    }

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && execution(* com.example.dynamicdatasource.controller..*.*(..)) ")
    public void restController() {
    }


    @Around("(controller() || restController())")
    public Object doRestControllerAroundMethod(ProceedingJoinPoint pig) throws Throwable {
        return doRestControllerAround(pig);
    }




    protected Object doRestControllerAround(ProceedingJoinPoint pig) throws Throwable {
        MethodSignature methodSign = (MethodSignature) pig.getSignature();

        Class targetClass = pig.getTarget().getClass();
        String methodSignName = methodSign.getMethod().getName();
        String targetClassName = targetClass.getSimpleName();

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();

        String requestUrI = request.getRequestURI();
        String method = request.getMethod();
        String[] parameterNames = methodSign.getParameterNames();
        Object[] args = pig.getArgs();

        Object retObject;
        boolean execError = false;
        log.info("【1】====start ====Request URI: {} method: {}  ====start ====", requestUrI, method);
        try {
            Pair<List<String>, List<Object>> argList = getArgList(parameterNames, args);

            long startTime = System.currentTimeMillis();
            log.debug("【2】param name:{}, param value:{}.", argList.getLeft(), JSON.toJSONString(argList.getRight()));
            log.info("【3】{} exec {} start.", targetClassName, methodSignName);

            // 执行controller的业务逻辑
            retObject = pig.proceed();
            log.debug("result:{}", JSON.toJSONString(retObject));
            log.info("【4】{} exec {} end, cost {} ms", targetClassName, methodSignName, System.currentTimeMillis() - startTime);
        } catch (Throwable e) {
            execError = true;
            log.error("【5】{} exec {} error.", targetClassName, methodSignName, e);
            throw e;
        } finally {
            // 防止有的人使用PageHelper使用不当，污染Request线程中的对象
            PageHelper.clearPage();
            log.info("【{}】 ====end ====Request URI：{} method：{} ====end ====", execError ? 6 : 5, requestUrI, method);
        }
        return retObject;
    }




    protected Pair<List<String>, List<Object>> getArgList(String[] parameterNames, Object[] args) {
        List<Object> argList = new LinkedList<>();
        List<String> parameterNameList = new LinkedList<>();

        for (int index = 0; index < args.length; index++) {
            Object arg = args[index];

            if (arg instanceof ServletRequest
                    || arg instanceof ServletResponse
                    || arg instanceof InputStreamSource) {
                continue;
            }

            if (arg instanceof HttpSession) {
                argList.add(((HttpSession) arg).getId());
            } else {
                argList.add(arg);
            }

            parameterNameList.add(parameterNames[index]);
        }
        return Pair.of(parameterNameList, argList);
    }

}
