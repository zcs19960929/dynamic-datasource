package com.example.dynamicdatasource.config;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.context.ApplicationListener;

public class StartFailedEvent implements ApplicationListener<ApplicationFailedEvent> {

    @Override
    public void onApplicationEvent(ApplicationFailedEvent event) {
        Throwable ex = event.getException();
        System.err.println("=============================spring boot start failed !=============================\n" + ExceptionUtils.getStackTrace(ex));
    }
}