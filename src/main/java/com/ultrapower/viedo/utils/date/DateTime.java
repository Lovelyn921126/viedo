/*
 * FileName: DateTime.java
 *
 * Company: 北京神州泰岳软件股份有限公司
 * Copyright 2011-2012 (C) Ultrapower Software CO., LTD. All Rights Reserved.
 */
package com.ultrapower.viedo.utils.date;

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

    private final static String[] PARSEPATTERNS = { "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd HH-mm-ss.SSS", "yyyy/MM/dd HH-mm-ss", "yyyy/MM/dd", "yy-M-d H:m:s.SSS", "yy-M-d H:m:s", "yy-M-d", "yy/M/d H:m:s.SSS", "yy/M/d H:m:s", "yy/M/d", };

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

    public int Year() {
        return c.get(Calendar.YEAR);
    }

    public int Month() {
        return c.get(Calendar.MONTH);
    }

    public int DayOfMonth() {
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public int DayOfWeek() {
        return c.get(Calendar.DAY_OF_WEEK);
    }

    public int Hour() {
        return c.get(Calendar.HOUR);
    }

    public static int HourOfDay() {
        return c.get(Calendar.HOUR_OF_DAY);
    }

    public int MINUTE() {
        return c.get(Calendar.MINUTE);
    }

    public int SECOND() {
        return c.get(Calendar.SECOND);
    }

    public Long MILLISECOND() {
        return c.getTimeInMillis();
    }

    public static DateTime Now() {
        Calendar calendar = Calendar.getInstance();
        return new DateTime(calendar);
    }

    public DateTime date() {
        Calendar calendar = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return new DateTime(calendar);
    }
    //====================转换=======================

    public static Calendar toCalendar() {
        return c;
    }

    public static Date toDate() {
        return c.getTime();
    }

    public static String toString(String pattern) {
        return DateFormatUtils.format(c, pattern);
    }

    public static String toDateString() {
        return toString(DateFormet.YY_MM_DD.getValue());
    }

    public static String toTimeString() {
        return toString(DateFormet.HH_MM_SS.getValue());
    }

    public static String toDateTimeString() {
        return toString(DateFormet.YY_MM_DD_HH_MM_SS.getValue());
    }

    public static String toDateTimeMillisString() {
        return toString(DateFormet.YY_MM_DD_HH_MM_SS_SSS.getValue());
    }

    //==================解析===========================
    public static DateTime parse(String str) throws ParseException {
        return parse(str, PARSEPATTERNS);
    }

    public static DateTime parse(String str, String... PARSEPATTERNS) throws ParseException {
        if (StringUtils.isBlank(str) && PARSEPATTERNS == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        for (String pattern : PARSEPATTERNS) {

            ParsePosition position = new ParsePosition(0);
            simpleDateFormat.applyPattern(pattern);

            position.setIndex(0);

            Date date = simpleDateFormat.parse(str, position);
            if (date != null) {
                return new DateTime(date);
            }
        }

        throw new ParseException("Unable to parse the date: " + str, -1);

    }

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

    public static DateTime parseOrDefault(String str) {
        try {
            return parse(str);
        } catch (ParseException e) {
            return defaultDate();
        }
    }

    //=================运算=================
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

    public DateTime addYear(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.YEAR, count);
        return new DateTime();
    }

    public DateTime addDay(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.DAY_OF_MONTH, count);
        return new DateTime();
    }

    public DateTime addMonth(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.MONTH, count);
        return new DateTime();
    }

    public DateTime addHour(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.HOUR, count);
        return new DateTime();
    }

    public DateTime addMinute(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.MINUTE, count);
        return new DateTime();
    }

    public DateTime addSecond(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.SECOND, count);
        return new DateTime();
    }

    public DateTime addDayOfWeek(int count) {
        Calendar calendar = (Calendar) c.clone();
        c.add(Calendar.DAY_OF_WEEK, count);
        return new DateTime();
    }

    public DateTime subtract(final DateTime dateTime) {
        return new DateTime(c.getTimeInMillis() - dateTime.MILLISECOND());
    }

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
