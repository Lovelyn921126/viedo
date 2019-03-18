/*
 * FileName: DateTime.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * <p>
 * Description:
 * </p>
 *
 * @author dell
 * @version 4.1

 * <p>
 * History:
 *
 * Date                     Author         Version     Description
 * ---------------------------------------------------------------------------------
 * 2018年4月25日 上午9:26:13          dell        4.1         To create
 * </p>
 *
 * @since
 * @see
 */
public class DateTime implements Serializable, Cloneable, Comparable<DateTime> {

    /**
     *
     */
    private static final long serialVersionUID = 7462846416467638220L;

    private final static String[] PARSEPATTERNS = { "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd HH-mm-ss.SSS", "yyyy/MM/dd HH-mm-ss", "yyyy/MM/dd", "yy-M-d H:m:s.SSS", "yy-M-d H:m:s", "yy-M-d", "yy/M/d H:m:s.SSS", "yy/M/d H:m:s", "yy/M/d", "yyyyMMdd" };

    private static Calendar defaultC = Calendar.getInstance();

    private static Calendar c = null;

    static {
        defaultC.set(Calendar.YEAR, 1900);
        defaultC.set(Calendar.MONTH, 0);
        defaultC.set(Calendar.DAY_OF_MONTH, 1);
        defaultC.set(Calendar.HOUR, 0);
        defaultC.set(Calendar.MINUTE, 0);
        defaultC.set(Calendar.SECOND, 0);
    }

    //-----------------------------构造函数---------------------------------

    public DateTime() {
        c = Calendar.getInstance();
        c.clear();
    }

    public DateTime(Date date) {
        c = Calendar.getInstance();
        c.setTime(date);
    }

    public DateTime(Calendar calendar) {
        c = calendar;

    }

    public DateTime(Long milliseconds) {
        c = Calendar.getInstance();
        c.setTimeInMillis(milliseconds);
    }

    //================获取时间部分================
    /**
     * 获取 日期的年
     * @return
     */
    public int Year() {
        return c.get(Calendar.YEAR);
    }

    /**
    * 获取日期的 月份
    * @return
    */
    public int Month() {
        return c.get(Calendar.MONTH);
    }

    /**
    * 获取 日期的 天数（关于月的 就是几号）
    * @return
    */
    public int DayOfMonth() {
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的 的天数 （关于周 就是这个礼拜的 第几天 从周日开始算）
     * @return
     */
    public int DayOfWeek() {
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
    * 获取日期的 的小时(12小时制)
    * @return
    */
    public int Hour() {
        return c.get(Calendar.HOUR);
    }

    /**
     * 获取日期的 的小时（24 小时制）
     * @return
     */
    public static int HourOfDay() {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的 的分钟
     * @return
     */
    public int MINUTE() {
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的 的 秒
     * @return
     */
    public int SECOND() {
        return c.get(Calendar.SECOND);
    }

    /**
     * 获取日期的 的 毫秒
     * @return
     */
    public Long MILLISECOND() {
        return c.getTimeInMillis();
    }

    /**
     * 获取当前日期  年月日 时分秒 毫秒
     * @return
     */
    public static DateTime Now() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar);
    }

    /**
     * 获取当前日期  年月日
     * @return
     */
    public DateTime date() {
        Calendar calendar = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new DateTime(calendar);
    }

    //====================转换=======================
    /**
     * 将格式转换成  java.util.Calendar 类型
     * @return
     */
    public Calendar toCalendar() {
        return c;
    }

    /**
     * 将格式转换成  java.util.Date 类型
     * @return
     */
    public Date toDate() {
        return c.getTime();
    }

    /**
    *   将时间转换成 指定的格式
    * @param pattern 指定的格式
    * @return
    */
    public String toString(String pattern) {
        return DateFormatUtils.format(c, pattern);
    }

    /**
     *   将时间转换成 指定的格式（yyyy-MM-dd）
     * @return
     */
    public String toDateString() {
        return toString(DateFormet.YY_MM_DD.getValue());
    }

    /**
     *   将时间转换成 指定的格式（HH:mm:ss）
     * @return
     */
    public String toTimeString() {
        return toString(DateFormet.HH_MM_SS.getValue());
    }

    /**
     *   将时间转换成 指定的格式（yyyyMMdd）
     * @return
     */
    public String toTimeDayString() {
        return toString(DateFormet.YYMMDD.getValue());
    }

    /**
     *   将时间转换成 指定的格式（yyyy-MM-dd HH:mm:ss）
     * @return
     */
    public String toDateTimeString() {
        return toString(DateFormet.YY_MM_DD_HH_MM_SS.getValue());
    }

    /**
     *   将时间转换成 指定的格式（yyyy-MM-dd HH:mm:ss.SSS）
     * @return
     */
    public String toDateTimeMillisString() {
        return toString(DateFormet.YY_MM_DD_HH_MM_SS_SSS.getValue());
    }

    //==================解析===========================
    /**
     *   转换字符串为 给定的格式 （如果 格式未定义 则转换成为 默认时间 即 1900-0-0 ）
     *   如果没有给定的 格式 可以自行在 该类 中的 PARSEPATTERNS 中添加 也可以使用 该类的tostring 方法
     * @param str 时间的字符串
     * @return
     * @throws ParseException
     */
    public static DateTime parse(String str) throws ParseException {
        return parse(str, PARSEPATTERNS);
    }

    /**
     *   转换字符串为 给定的格式
     * @param str  时间的字符串
     * @param PARSEPATTERNS 给定的格式
     * @return
     * @throws ParseException
     */
    public static DateTime parse(String str, String... PARSEPATTERNS) throws ParseException {
        if (StringUtils.isBlank(str) && PARSEPATTERNS == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        for (String pattern : PARSEPATTERNS) {

            ParsePosition position = new ParsePosition(0);
            simpleDateFormat.applyPattern(pattern);

            position.setIndex(0);
            //使用 该方法 不会抛出 异常 可以保证 及时  时间 字符串的格式 与跟定的不一样 方法也能继续往下走 不会报错
            Date date = simpleDateFormat.parse(str, position);
            if (date != null) {
                return new DateTime(date);
            }
        }

        throw new ParseException("Unable to parse the date: " + str, -1);

    }

    /**
    *  转换字符串为 给定的格式
    * @param str 时间的字符串
    * @param defaultDate  如果转换失败 返回的默认时间(如果给定 默认时间也无法转换成功 则返回 设置的默认时间 即 1900-01-01)
    * @param partten 给定的格式
    * @return
    */
    public static DateTime parse(String str, String defaultDate, String... partten) {
        try {
            return parse(str, partten);
        } catch (ParseException e) {
            try {
                return parse(defaultDate, partten);
            } catch (ParseException e1) {
                return defaultDate();
            }
        }
    }

    /**
     * 转换字符串为 给定的格式
     * @param str   时间的字符串
     * @param defaultDate 如果转换失败 返回的默认时间(如果给定 默认时间也无法转换成功 则返回 设置的默认时间 即 1900-01-01)
     * @return
     */
    public static DateTime parse(String str, String defaultDate) {
        try {
            return parse(str);
        } catch (ParseException e) {
            try {
                return parse(defaultDate);
            } catch (ParseException e1) {
                return defaultDate();
            }
        }
    }

    /**
     * 转换字符串为 给定的格式   如果给定 默认时间也无法转换成功 则返回 设置的默认时间 即 1900-01-01
     * @param str   时间的字符串
     * @return
     */
    public static DateTime parseOrDefault(String str) {
        try {
            return parse(str);
        } catch (ParseException e) {
            return defaultDate();
        }
    }

    //=================运算=================
    /**
     * 比较当前时间 与提供的时间的大小
     */
    @Override
    public int compareTo(DateTime date) {
        return c.compareTo(date.c);
    }

    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return new DateTime((Calendar) c.clone());
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return c.hashCode();
    }

    public boolean equal(Object obj) {
        if (obj instanceof Calendar) {
            return c.equals(obj);
        }
        if (obj instanceof Date) {
            return c.getTime().equals(obj);
        }
        if (obj instanceof DateTime) {
            return c.equals(((DateTime) obj).toCalendar());
        }
        return false;
    }

    /**
     *  提供日期关于 年的 加运算
     * @param count
     * @return
     */
    public DateTime addYear(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.YEAR, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 天的 加运算
     * @param count
     * @return
     */
    public DateTime addDay(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.DAY_OF_MONTH, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 月的 加运算
     * @param count
     * @return
     */
    public DateTime addMonth(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.MONTH, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 小时的 加运算
     * @param count
     * @return
     */
    public DateTime addHour(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.HOUR, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 分钟的 加运算
     * @param count
     * @return
     */
    public DateTime addMinute(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.MINUTE, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 秒的 加运算
     * @param count
     * @return
     */
    public DateTime addSecond(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.SECOND, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 星期的 加运算
     * @param count
     * @return
     */
    public DateTime addDayOfWeek(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.DAY_OF_WEEK, count);
        return new DateTime();
    }

    /**
     *  提供日期关于 计算当前时间 与给定时间的差值
     * @param count
     * @return
     */
    public DateTime subtract(final DateTime dateTime) {
        return new DateTime(c.getTimeInMillis() - dateTime.MILLISECOND());
    }

    /**
     *  提供日期关于 两个给定时间的差值
     * @param count
     * @return
     */
    public DateTime subtract(DateTime dateTime, DateTime dateTime2) {
        return new DateTime(dateTime.MILLISECOND() - dateTime2.MILLISECOND());
    }

    //=================其他==================

    public static DateTime defaultDate(final DateTime dateTime) {
        return dateTime == null ? new DateTime(defaultC) : dateTime;
    }

    public static DateTime defaultDate() {
        return defaultDate(null);
    }

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */

    public static void main(String[] args) {
        try {
            System.out.println(parse("2018-01-01 12:00:11").toDateTimeString());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
