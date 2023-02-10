package com.gomboclabs.codespirit.practice.improve.class01;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

/**
 * 通过Cron表达式获取近5次的执行时间
 * @author Declan
 *
 */
public class GetScheduleTimeOfCron {


    public static void main(String[] args) {
        // 1-周日， 2-周一，
<<<<<<< HEAD
        System.out.println(getCronSchdule("0 1 1 1 * ?"));
=======
        System.out.println(getCronSchdule("0 15 10 ? * 2"));
>>>>>>> origin/main
        System.out.println("********************************");
        System.out.println(getCronSchdule("0 15 10 ? * 2", 10));
    }


    /**
     * 根据Cron表达式获取任务最近5次的执行时间
     * @param cron
     * @return
     */
    public static String getCronSchdule(String cron){
        String timeSchdule="";
        if(!CronExpression.isValidExpression(cron)){
            return "Cron is Illegal!";
        }
        try {
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("Caclulate Date").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            Date time0 = trigger.getStartTime();
            Date time1 = trigger.getFireTimeAfter(time0);
            Date time2 = trigger.getFireTimeAfter(time1);
            Date time3 = trigger.getFireTimeAfter(time2);
            Date time4 = trigger.getFireTimeAfter(time3);
            Date time5 = trigger.getFireTimeAfter(time4);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            StringBuilder timeBuilder=new StringBuilder();
            timeBuilder
                    .append(format.format(time1))
                    .append("\n")
                    .append(format.format(time2))
                    .append("\n")
                    .append(format.format(time3))
                    .append("\n")
                    .append(format.format(time4))
                    .append("\n")
                    .append(format.format(time5));
            timeSchdule=timeBuilder.toString();
        } catch (Exception e) {
            timeSchdule="unKnow Time!";
        }
        return timeSchdule;
    }

    /**
     * 根据Cron表达式获取任务最近 几次的执行时间
     * @param cron  cron表达式
     * @param count 次数
     * @return
     */
    public static List<String> getCronSchdule(String cron, int count){
        List<String> retList = new ArrayList<String>();
        if(!CronExpression.isValidExpression(cron)){
            //Cron表达式不正确
            return retList;
        }
        try {
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("Caclulate Date").withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date startTime = trigger.getStartTime();
            for (int i = 0; i < count; i++) {
                Date time = trigger.getFireTimeAfter(startTime);
                retList.add(format.format(time ));
                startTime = time;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return retList;
    }

}
