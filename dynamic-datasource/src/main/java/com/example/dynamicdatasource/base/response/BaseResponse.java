package com.example.dynamicdatasource.base.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Description:
 * Create DateTime: 2023/1/31 11:45
 *
 * @author zhangchangsheng01@gmail.com
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -3166696559131342439L;


    private Integer code;

    private String message;


    private String status;

    private T data;

    private String requestId;

    private String host = getHostName();


    public BaseResponse(T data) {
        this.data = data;
    }

    @JsonIgnore
    private static String getHostName() {
        if (System.getenv("COMPUTERNAME") != null) {
            return System.getenv("COMPUTERNAME");
        } else {
            try {
                return InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException var3) {
                String host = var3.getMessage();
                if (host != null) {
                    int colon = host.indexOf(58);
                    if (colon > 0) {
                        return host.substring(0, colon);
                    }
                }

                return "UnknownHost";
            }
        }
    }
}
