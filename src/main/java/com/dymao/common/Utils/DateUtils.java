package com.dymao.common.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Mervin
 * @Description:
 * @date 2017/11/19 19:02
 */
public class DateUtils {

    public static final String DATE_YYYY_MM_DD_WEEK = "yyyy年MM月dd日 E";
    public static final String DATE_YYYYMMDD = "yyyyMMdd";
    public static final String DATE_YYMMDD = "yyMMdd";

    public static String getStringDate(Date date, String pattern, Locale locale){
        SimpleDateFormat dateFormat = null;
        if(locale != null) {
            dateFormat = new SimpleDateFormat(pattern,locale);
        }else{
            dateFormat = new SimpleDateFormat(pattern);
        }
        return  dateFormat.format(date);
    }
    public static String getStringDate(Date date, String pattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return  dateFormat.format(date);
    }

    public static boolean sameDate(Date d1, Date d2,String pattern){
        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        return fmt.format(d1).equals(fmt.format(d2));
    }

}
