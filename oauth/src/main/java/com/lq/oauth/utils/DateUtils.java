package com.lq.oauth.utils;

import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**

 * @author  liuqq

 * @create  2022/3/15 10:27

 * @desc    日期处理工具类

 **/
public class DateUtils {

    /**
     * 计算当前时间与指定时间相差秒数
     */
    public static Long durationSeconds(LocalDateTime start,LocalDateTime end){
        Duration duration=Duration.between(start,end);
        return duration.getSeconds();
    }

    /**
     * 获取昨日日期
     * @return
     */
    public static String getYesterdayStr(){
//        LocalDate yesterday = LocalDate.now().plusDays(-1);
//        return getStringFromLocalDate(yesterday);
        return "2022-10-31";
    }

    /**
     * 获取指定日期所属周的周一的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getMondayForThisWeek(LocalDate localDate) {
        LocalDateTime monday = LocalDateTime.of(localDate, LocalTime.MIN).with(DayOfWeek.MONDAY);
        return monday;
    }

    /**
     * 获取指定日期所属周的周日的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getSundayForThisWeek(LocalDate localDate) {
        LocalDateTime sunday = LocalDateTime.of(localDate, LocalTime.MIN).with(DayOfWeek.SUNDAY);
        return sunday;
    }

    /**
     * 获取指定日期所属周的下周一的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getMondayForNextWeek(LocalDate localDate) {
        LocalDateTime monday = LocalDateTime.of(localDate, LocalTime.MIN).plusWeeks(1).with(DayOfWeek.MONDAY);
        return monday;
    }

    /**
     * 获取指定日期所属周的下周日的日期
     * @param localDate
     * @return
     */
    public static LocalDateTime getSundayForNextWeek(LocalDate localDate) {
        LocalDateTime sunday = LocalDateTime.of(localDate, LocalTime.MIN).plusWeeks(1).with(DayOfWeek.SUNDAY);
        return sunday;
    }

    /**
     * 指定格式为"yyyy-MM-dd HH:mm:ss"的字符串时间转化为LocalDateTime类型
     * @param dateStr
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromString(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime;
    }

    /**
     * 指定格式字符串时间转化为LocalDateTime类型
     * @param dateStr
     * @param format
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromString(String dateStr,String format) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(format));
        return localDateTime;
    }

    /**
     * LocalDateTime类型转化为格式为"yyyy-MM-dd HH:mm:ss"的字符串时间类型
     * @param localDateTime
     * @return
     */
    public static String getStringFromLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTimeStr;
    }

    /**
     * 日期格式转为指定类型字符串
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getStringFromLocalDateTime(LocalDateTime localDateTime,String format) {
        if (localDateTime == null) {
            return null;
        }
        String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern(format));
        return localDateTimeStr;
    }

    public static String getStringFromTimestamp(long timestamp) {
        return getStringFromLocalDateTime(getLocalDateTimeFromTimestamp(timestamp));
    }

    public static String getStringFromTimestamp(long timestamp,String format) {
        return getStringFromLocalDateTime(getLocalDateTimeFromTimestamp(timestamp),format);
    }

    public static String getGpsStringLocalDateTime(String timeStr) {
        if (timeStr == null) {
            return null;
        }
        LocalDateTime localDateTime=getLocalDateTimeFromString(timeStr);
        String localDateTimeStr = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd/HHmmss"));
        return localDateTimeStr;
    }


    public static String getStringFromLocalDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        String localDateStr = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDateStr;
    }

    /**
     * 指定格式为"yyyy-MM-dd"的字符串时间转化为LocalDateTime类型
     * @param dateStr
     * @return
     */
    public static LocalDate getLocalDateFromString(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }

    /**
     * 获取指定格式字符串转日期
     * @param dateStr
     * @param format
     * @return
     */
    public static LocalDate getLocalDateFromString(String dateStr,String format) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }
        LocalDate localDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(format));
        return localDate;
    }

    /**
     * Date类型时间转化为LocalDateTime类型
     * @param date
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromDate(Date date) {
        LocalDateTime localDateTime = date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        return localDateTime;
    }

    /**
     * LocalDateTime类型转化为Date类型时间
     * @param localDateTime
     * @return
     */
    public static Date getDateFromLocalDateTime(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
        return date;
    }

    /**
     * 获取指定时间的00:00:00
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLocalDateTimeForBegin(LocalDateTime localDateTime) {
        LocalDateTime begin = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MIN);
        return begin;
    }

    /**
     * 获取指定时间的23:59:59
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLocalDateTimeForEnd(LocalDateTime localDateTime) {
        LocalDateTime end = LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        return end;
    }

    /**
     * 时间戳转化为LocalDateTime格式
     * @param timestamp
     * @return
     */
    public static LocalDateTime getLocalDateTimeFromTimestamp(Long timestamp) {
        LocalDateTime localDateTime;
        if (String.valueOf(timestamp).length()>10){
            localDateTime=LocalDateTime.ofEpochSecond(timestamp/1000, 0, ZoneOffset.ofHours(8));
        }else {
            localDateTime=LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        }
        return localDateTime;
    }

    /**
     * LocalDateTime格式转化为时间戳(毫秒)
     * @param localDateTime
     * @return
     */
    public static Long getTimestampFromLocalDateTime(LocalDateTime localDateTime) {
        Long timestamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }

    /**
     * LocalDateTime格式转化为时间戳(秒)
     * @param localDateTime
     * @return
     */
    public static Long getSecondTimestampFromLocalDateTime(LocalDateTime localDateTime) {
        Long timestamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp/1000;
    }

    /**
     * 字符串格式转化为时间戳(毫秒)
     * @param timeStr
     * @return
     */
    public static Long getTimestampFromString(String timeStr) {
        LocalDateTime localDateTime=getLocalDateTimeFromString(timeStr);
        Long timestamp = localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        return timestamp;
    }

    /**
     * 根据当前日期获得往前指定月份字符串 yyyy-MM
     */
    public static String getBeforeMonthStr(int count){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime before=now.plusMonths(count);
        String month=getStringFromLocalDateTime(before);
        return month.substring(0,7);
    }

}
