package com.dymao.common.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 19:02
 */
public class DateUtils {

    public static final String DATE_YYYY_MM_DD_WEEK = "yyyy年MM月dd日 E";
    public static final String DATE_YYYYMMDD = "yyyyMMdd";

    public static String getStringDate(Date date,String pattern){
        SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
        return  dateFormat.format(date);
    }

}
