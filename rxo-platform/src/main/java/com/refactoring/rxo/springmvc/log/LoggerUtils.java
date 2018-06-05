package com.refactoring.rxo.springmvc.log;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 日志记录所有请求 工具类
 *
 * @author fei.yao
 * @create 2018-01-03 14:40
 **/
public final class LoggerUtils {

    public static final String LOGGER_RETURN = "LOGGER_RETURN";

    private LoggerUtils() {}

    /**
     * 获取客户端ip地址
     * @param request
     * @return
     */
    public static String getCliectIp(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || Objects.equals(ip.trim(), "") || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || Objects.equals(ip.trim(), "") || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || Objects.equals(ip.trim(), "") || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknown的ip
        final String[] arr = ip.split(",");
        for (final String str : arr) {
            if (!"unknown".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }
        return ip;
    }

    /**
     * 判断是否为ajax请求
     * @param request
     * @return
     */
    public static String getRequestType(HttpServletRequest request) {
        return request.getHeader("X-Requested-With");
    }
}
