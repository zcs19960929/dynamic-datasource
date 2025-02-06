package com.example.dynamicdatasource.constant;

import com.example.dynamicdatasource.base.error.CommonException;
import com.example.dynamicdatasource.base.error.ErrorEnum;
import com.example.dynamicdatasource.util.NetUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.util.List;

@Slf4j
public class ServerInfoConstants {
    public static String SERVER_HOSTNAME;
    public static String SERVER_IP_ADDRESS;



    static {
        try {
            List<InetAddress> networkAddressList = NetUtils.getNetworkAddress();

            InetAddress inetAddress = networkAddressList.get(0);
            SERVER_HOSTNAME = inetAddress.getHostName();
            SERVER_IP_ADDRESS = inetAddress.getHostAddress();
        } catch (Throwable e) {
            log.error("获取本机IP地址出错", e);
            throw new CommonException(ErrorEnum.UN_KNOWN, "获取本机IP地址出错");
        }
        log.debug("本机服务器信息，IP={}，hostName={}", SERVER_IP_ADDRESS, SERVER_HOSTNAME);
    }
}

