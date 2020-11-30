package com.p.v2iserver.account.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * TODO NetUtil
 * @author Pactera
 * @date 2020-11-19 09:55:32
 **/
public class NetUtil {

    /**
     * 获取请求地址
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        if (ip != null && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }
        return ip;
    }
}
