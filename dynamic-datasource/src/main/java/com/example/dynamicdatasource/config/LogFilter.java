package com.example.dynamicdatasource.config;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

/**
 * description :
 * create time : 2022/5/27 10:53
 *
 * @author : fangjiancheng
 */
public class LogFilter extends AbstractMatcherFilter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        return onMismatch;
    }
}

