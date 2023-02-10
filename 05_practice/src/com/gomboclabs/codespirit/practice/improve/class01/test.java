package com.gomboclabs.codespirit.practice.improve.class01;

import org.junit.Test;
import org.quartz.CronExpression;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auther: xucg
 * @Date: 2022/11/14 - 11 - 14 - 15:36
 * @Description: com.gomboclabs.codespirit.practice.improve.class01
 * @version: 1.0
 */
public class test {
    public static void main(String[] args) {
        String str = "2022-03-15 02:36:45";
        System.out.println(getPreviousWeekStart(str));
        System.out.println(getPreviousWeekEnd(str));
        System.out.println(getPreviousMonthStart(str));
        System.out.println(getPreviousMonthEnd(str));
    }

    public static String getPreviousWeekStart(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);
        Date date1 = localDateTime2Date(localDateTime);
        Date thisWeekMonday = getThisWeekMonday(date1);
        LocalDateTime localDateTime1 = date2LocalDateTime(thisWeekMonday);
        LocalDateTime startDay = localDateTime1.plusDays(-7).with(LocalTime.MIN);
        return startDay.format(formatter);
    }

    public static String getPreviousWeekEnd(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String previousWeekStart = getPreviousWeekStart(date);
        LocalDateTime localDateTime1 = LocalDateTime.parse(previousWeekStart, formatter);
        LocalDateTime EndDay = localDateTime1.plusDays(+6).with(LocalTime.MAX);
        return EndDay.format(formatter);
    }

    public static String getPreviousMonthStart(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter).minusMonths(1);
        LocalDateTime startDay = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
        return startDay.format(formatter);
    }
    public static String getPreviousMonthEnd(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter).minusMonths(1);
        LocalDateTime startDay = localDateTime.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
        return startDay.format(formatter);
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date getThisWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

@Test
public void getFutureScheduleTime() throws ParseException {
    //此处为cron表达式
    String cronExpression =  "0 1 1 * * ?";
    // try{
    //     //导包import org.quartz.CronExpression
    //     CronExpression cronExpression =new CronExpression(cronExpress );
    //     Date date = cronExpression.getTimeAfter(new Date());
    //     //将date转换为指定日期格式字符串
    //     SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //     String dateString = dataFormat.format(date);
    //     System.out.println(dateString);
    //     //dateString为转换后的日期格式
    // } catch (ParseException e) {
    //     throw new RuntimeException(e);
    // }

    List<String> list = new ArrayList<>();
    CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
    cronTriggerImpl.setCronExpression(cronExpression);
    // 这个是重点，一行代码搞定
    System.out.println(TriggerUtils.computeFireTimes(cronTriggerImpl, null, 5));
}
}
