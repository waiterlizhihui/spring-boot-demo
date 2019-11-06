package com.waiter.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserAgentUtils
 * @Description UA简单工具类（如果想通过UA获取更多信息，百度有一个JS实现的UA开源工具，直接将这个JS部署成服务就行了），主要提供了以下功能
 * 1.
 * @Author lizhihui
 * @Date 2019/3/3 14:52
 * @Version 1.0
 */
public class UserAgentUtils {
    /**
     * 获取UserAgent对象
     * @param request
     * @return
     */
    public static UserAgent getUserAgent(HttpServletRequest request){
        return UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
    }

    /**
     * 获取设备类型
     * @param request
     * @return
     */
    public static DeviceType getDeviceType(HttpServletRequest request){
        return getUserAgent(request).getOperatingSystem().getDeviceType();
    }

    /**
     * 根据UA判断访问设备是否是PC
     * @param request
     * @return
     */
    public static boolean isPC(HttpServletRequest request){
        return DeviceType.COMPUTER.equals(getDeviceType(request));
    }

    /**
     * 根据UA判断访问设备是否是手机
     * @param request
     * @return
     */
    public static boolean isMobile(HttpServletRequest request){
        return DeviceType.MOBILE.equals(getDeviceType(request));
    }

    /**
     * 根据UA判断访问设备是否是平板
     * @param request
     * @return
     */
    public static boolean isTablet(HttpServletRequest request){
        return DeviceType.TABLET.equals(getDeviceType(request));
    }

    /**
     * 根据UA判断访问设备是否是移动设备
     * @param request
     * @return
     */
    public static boolean isMobileOrTablet(HttpServletRequest request){
        return DeviceType.MOBILE.equals(getDeviceType(request)) || DeviceType.TABLET.equals(getDeviceType(request));
    }

    /**
     * 获取浏览器类型
     * @param request
     * @return
     */
    public static Browser getBrowser(HttpServletRequest request){
        return getUserAgent(request).getBrowser();
    }
}
