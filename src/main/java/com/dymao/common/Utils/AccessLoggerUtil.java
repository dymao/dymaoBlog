package com.dymao.common.Utils;

import com.dymao.common.constants.Constant;
import com.dymao.model.AccessLog;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @author Mervin
 * @Description:
 * @date 2018-04-10 23:55
 */
public class AccessLoggerUtil {

    public static final String LOG_OPERATE = "logData";

    public AccessLoggerUtil(){}

    public static AccessLog getLog(HttpServletRequest request){
        //1.依次获取每个属性信息 userId,operator,action,remark,ip,targetType
        AccessLog log = new AccessLog();
        String requestUri = request.getRequestURI();
        log.setUrl(requestUri);
        HttpSession session = request.getSession();
        if(session != null){
          String userId = (String)session.getAttribute("");
            if(StringUtils.isNotBlank(userId)){
                log.setUserid(userId);
            }
        }
        log.setDevicetype(DeviceUtil.getDeviceTypeByAgent(request));
        String channelType = Constant.CHANNEL_TYPE_FRONT;
        if(StringUtils.isNotBlank(requestUri) && requestUri.indexOf(Constant.CHANNEL_TYPE_ADMIN) >= 0){
            channelType = Constant.CHANNEL_TYPE_ADMIN;
        }
        log.setChanneltype(channelType);
        log.setSessionid(request.getSession().getId());
        log.setIp(AccessLoggerUtil.getIpAddr(request));
        log.setAccesstime(new Date());

        return log;
    }
    /**
     * 获取客户端ip地址
     **/
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}