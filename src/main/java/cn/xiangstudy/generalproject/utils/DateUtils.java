package cn.xiangstudy.generalproject.utils;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 日期处理类
 * @author zhangxiang
 * @date 2025-07-11 11:46
 */
public class DateUtils {

    /**
     * 当前时间
     * @author zhangxiang
     * @date 2025/7/11 11:47
     * @return java.util.Date
     */
    public static Date nowDate(){
        return new Date();
    }

    /**
     * 当前时间
     * @author zhangxiang
     * @date 2025/7/11 11:50
     * @return java.time.LocalDate
     */
    public static LocalDate nowLocalDate(){
        return LocalDate.now();
    }

    /**
     * 当前时间
     * @author zhangxiang
     * @date 2025/7/11 11:50
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime nowLocalDateTime(){
        return LocalDateTime.now();
    }

    /**
     * localDate 转 date
     * @author zhangxiang
     * @date 2025/7/11 11:53
     * @param localDate
     * @return java.util.Date
     */
    public static Date localDateToDate(LocalDate localDate){

        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }

    /**
     * localDate 转 date; 自定义具体时间
     * @author zhangxiang
     * @date 2025/7/11 12:03
     * @param localDate
     * @param hour
     * @param minute
     * @param second
     * @return java.util.Date
     */
    public static Date localDateToDate(LocalDate localDate, int hour, int minute, int second){
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDate.atStartOfDay(zoneId).withHour(hour).withMinute(minute).withSecond(second).toInstant());
    }

    /**
     * localDateTime 转 Date
     * @author zhangxiang
     * @date 2025/7/11 15:24
     * @param localDateTime
     * @return java.util.Date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    /**
     * date 转 localDate
     * @author zhangxiang
     * @date 2025/7/11 15:33
     * @param date
     * @return java.time.LocalDate
     */
    public static LocalDate dateToLocalDate(Date date){
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return LocalDate.ofInstant(instant, zoneId);
    }

    /**
     * date 转 localDateTime
     * @author zhangxiang
     * @date 2025/7/11 15:36
     * @param date
     * @return java.time.LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * 日期减去多少天
     * @author zhangxiang
     * @date 2025/7/11 15:45
     * @param date
     * @param day
     * @return java.util.Date
     */
    public static Date dateMinusDay(Date date, int day){
        LocalDateTime localDateTime = dateToLocalDateTime(date).minusDays(day);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 日期增加多少天
     * @author zhangxiang
     * @date 2025/7/11 15:48
     * @param date
     * @param day
     * @return java.util.Date
     */
    public static Date datePlusDay(Date date, int day){
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusDays(day);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 日期增加分钟
     * @author zhangxiang
     * @date 2025/7/11 17:01
     * @param date
     * @param minute
     * @return java.util.Date
     */
    public static Date datePlusMinute(Date date, int minute){
        LocalDateTime localDateTime = dateToLocalDateTime(date).plusMinutes(minute);
        return localDateTimeToDate(localDateTime);
    }

    /**
     * 获取开始时间和结束时间之间的日期
     * @author zhangxiang
     * @date 2025/7/11 15:53
     * @param startDate
     * @param endDate
     * @return java.util.List<java.util.Date>
     */
    public static List<Date> startAndStopDate(Date startDate, Date endDate){

        List<Date> result = new ArrayList<>();

        Date end = datePlusDay(endDate, 1);

        if(startDate.before(endDate)){
            for(Date begin = startDate; begin.before(end);){
                result.add(begin);
                begin = datePlusDay(begin, 1);
            }
        }

        return result;
    }

    /**
     * 获取开始时间和结束时间之间的日期
     * @author zhangxiang
     * @date 2025/7/11 16:13
     * @param startDate
     * @param endDate
     * @return java.util.List<java.time.LocalDate>
     */
    public static List<LocalDate> startAndStopLocalDate(Date startDate, Date endDate){
        List<LocalDate> result = new ArrayList<>();

        Date end = datePlusDay(endDate, 1);

        if(startDate.before(endDate)){
            for(Date begin = startDate; begin.before(end);){
                result.add(dateToLocalDate(begin));
                begin = datePlusDay(begin, 1);
            }
        }

        return result;
    }

    /**
     * 获取开始时间和结束时间之间的日期
     * @author zhangxiang
     * @date 2025/7/11 16:47
     * @param startDate
     * @param endDate
     * @return java.util.List<java.time.LocalDate>
     */
    public static List<LocalDateTime> startAndStopLocalDateTime(Date startDate, Date endDate){
        List<LocalDateTime> result = new ArrayList<>();

        Date end = datePlusDay(endDate, 1);

        if(startDate.before(endDate)){
            for(Date begin = startDate; begin.before(end);){
                result.add(dateToLocalDateTime(begin));
                begin = datePlusDay(begin, 1);
            }
        }

        return result;
    }

    /**
     * 两个时间的差值(毫秒)
     * @author zhangxiang
     * @date 2025/7/11 17:11
     * @param startDate
     * @param endDate
     * @return java.lang.Long
     */
    public static Long differenceTime(Date startDate, Date endDate){
        long start = startDate.getTime();
        long end = endDate.getTime();
        return start - end;
    }


    /**
     * 两个时间的差
     * @author zhangxiang
     * @date 2025/7/11 17:34
     * @param startDate
     * @param endDate
     * @param type  0:分钟差, 1:小时差, 2:天数差
     * @return java.lang.Long
     */
    public static Long differenceDate(Date startDate, Date endDate, int type){
        Instant startInstant = startDate.toInstant();
        Instant endInstant = endDate.toInstant();

        Duration duration = Duration.between(startInstant, endInstant);

        long result;

        if(type == 0){
            result = duration.toMinutes();
        }else if(type == 1){
            result = duration.toHours();
        }else {
            result = duration.toDays();
        }
        return result;
    }


}
