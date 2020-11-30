package com.p.v2iserver.account.utils;

import lombok.extern.log4j.Log4j2;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * TODO date util
 * @author Pactera
 * @date 2020-11-13 15:03:07
 **/
@Log4j2
public class DateUtil {
    /**
     * 默认日期格式, yyyy-MM-dd
     */
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 默认时间格式, HH:mm:ss
     */
    public static final String DEFAULT_SHORT_TIME_PATTERN = "HH:mm:ss";
    /**
     * 没有下划线的日期
     */
    public static final String DEFAULT_DATE_PATTERN_UNLINE = "yyyyMMdd";
    /**
     * 默认时间格式, yyyy-MM-dd hh24:mm:ss
     */
    public static final String DEFAULT_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间格式, yyyy-MM-dd hh24:mm:ss SSS
     */
    public static final String DEFAULT_TIME_PATTERN_S = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String PATTERN_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String PATTERN_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 得到现在时间
     *
     * @return
     */
    public static Date getNow() {
        return DateTime.now().toDate();
    }

    public static String convertDate2Str(Date date) {
        return convertDate2Str(date, DEFAULT_TIME_PATTERN);
    }

    public static String convertDate2Str(Date date, String format) {
        return new DateTime(date).toString(format);
    }

    /**
     * 得到现在分钟
     *
     * @return
     */

    public static String getTime() {

        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_TIME_PATTERN);

        String dateString = formatter.format(currentTime);

        String min;

        min = dateString.substring(14, 16);

        return min;

    }

    /**
     * 
     * TODO
     * @author Pactera
     * @date 2020-11-13 15:11:13
     * @param: dateStr
 * @param: pattern
 * @param: locale
     * @return java.util.Date
     **/
    public static Date getDate(String dateStr, String pattern, Locale locale) {
        String dateStrTemp = "";
        Date result = null;
        if (dateStr == null || dateStr.trim().length() < 10) {
            return result;
        } else {
            try {
                if (dateStr.trim().length() >= pattern.length()) {
                    dateStrTemp = dateStr.substring(0, pattern.length());
                }
                result = new SimpleDateFormat(pattern, locale).parse(dateStrTemp);
            } catch (Exception e) {
                log.error("DateUtil.getDate", e);
            }
        }
        return result;
    }

    public static Date getDate(String dateStr, String pattern) {
        return getDate(dateStr, pattern, Locale.getDefault());
    }

    public static Date getDate(String dateStr) {
        return getDate(dateStr, DEFAULT_DATE_PATTERN, Locale.getDefault());
    }

    /**
     * 根据日期字符串（格式为［yyyy-MM-dd HH:mm:ss］）转换成DateTime类型
     *
     * @param dateStr
     * @return
     */
    public static DateTime convertStrToDateTime(String dateStr) {
        return DateTime.parse(dateStr, DateTimeFormat.forPattern(DEFAULT_TIME_PATTERN));
    }

    public static Date addDay(Date date, int addDay) {
        if (null == date) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DATE, addDay);// num为增加的天数，可以改变的
        return ca.getTime();
    }

    public static Date addYear(Date date, int addYear) {
        if (null == date) {
            return null;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.YEAR, addYear);// num为增加的年数，可以改变的
        return ca.getTime();
    }
}
