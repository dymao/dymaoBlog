package com.dymao.common.Utils;

import com.dymao.common.constants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Mervin
 * @Description: 判断设备类型
 * @date 2018-04-11 23:34
 */
public class DeviceUtil {

    private static final Logger logger = LoggerFactory.getLogger(DeviceUtil.class);

    public static String getDeviceTypeByAgent(HttpServletRequest request){

        String deviceType = Constant.DEVICE_TYPE_OHTERS;
        String userAgent = "";
        if (request.getHeader("User-Agent") != null) {
            userAgent = request.getHeader("User-Agent");
        }

        logger.debug("User-Agent = " + userAgent);
       // String[] keywords = { "android", "iphone", "ipod", "ipad", "windows phone", "mqqbrowser" };

        // Windows 桌面系统
        if (userAgent.toLowerCase().indexOf("windows nt") >=0 || userAgent.toLowerCase().indexOf("compatible; msie") >= 0){
            deviceType = Constant.DEVICE_TYPE_PC_WINDOWS;
        }else if(userAgent.toLowerCase().indexOf("macintosh") >= 0){
            deviceType = Constant.DEVICE_TYPE_PC_IOS;
        }else if(userAgent.toLowerCase().indexOf("android") >= 0){
            deviceType = Constant.DEVICE_TYPE_MOBILE_ANDROID;
        }else if(userAgent.toLowerCase().indexOf("iphone") >= 0
                || userAgent.toLowerCase().indexOf("ipod") >= 0
                || userAgent.toLowerCase().indexOf("ipad") >= 0 ){
            deviceType = Constant.DEVICE_TYPE_MOBILE_IOS;
        }
        return deviceType;
    }

//    public static boolean JudgeIsMoblie(HttpServletRequest request) {
//        boolean isMoblie = false;
//        String[] mobileAgents = { "iphone", "android","ipad", "phone", "mobile", "wap", "netfront", "java", "opera mobi",
//                "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry", "dopod",
//                "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
//                "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
//                "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem",
//                "wellcom", "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos",
//                "pantech", "gionee", "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320",
//                "240x320", "176x220", "w3c ", "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac",
//                "blaz", "brew", "cell", "cldc", "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs",
//                "kddi", "keji", "leno", "lg-c", "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi",
//                "mot-", "moto", "mwbp", "nec-", "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port",
//                "prox", "qwap", "sage", "sams", "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem",
//                "smal", "smar", "sony", "sph-", "symb", "t-mo", "teli", "tim-", "tosh", "tsm-", "upg1", "upsi", "vk-v",
//                "voda", "wap-", "wapa", "wapi", "wapp", "wapr", "webc", "winw", "winw", "xda", "xda-",
//                "Googlebot-Mobile" };
//        if (request.getHeader("User-Agent") != null) {
//            String agent=request.getHeader("User-Agent");
//            for (String mobileAgent : mobileAgents) {
//                if (agent.toLowerCase().indexOf(mobileAgent) >= 0&&agent.toLowerCase().indexOf("windows nt")<=0 &&agent.toLowerCase().indexOf("macintosh")<=0) {
//                    isMoblie = true;
//                    break;
//                }
//            }
//        }
//        return isMoblie;
//    }
}
