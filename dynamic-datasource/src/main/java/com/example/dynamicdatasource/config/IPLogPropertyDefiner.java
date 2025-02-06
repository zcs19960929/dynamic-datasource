package com.example.dynamicdatasource.config;

import ch.qos.logback.core.PropertyDefinerBase;
import com.example.dynamicdatasource.constant.ServerInfoConstants;

public class IPLogPropertyDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        return ServerInfoConstants.SERVER_IP_ADDRESS;
    }
}
