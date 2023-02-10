package com.gomboclabs.codespirit.practice.improve.class01;

/**
 * @Auther: xucg
 * @Date: 2022/11/23 - 11 - 23 - 19:08
 * @Description: com.gomboclabs.codespirit.practice.improve.class01
 * @version: 1.0
 */
import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.cronutils.model.CronType.QUARTZ;

public class test2 {

    public static void main(String[] args) throws ParseException {
        String expressiion = "0 0 0 * * ?";
        //expressiion = "0 0 0 0 5 2#1";

        //格式校验
        boolean b = checkValid(expressiion);
        System.out.println(b);

        if (b) {
            //解释cron表达式
            String s = describeCron(expressiion);
            System.out.println(s);

            //获取下次运行时间
            List<Date> nextExecTime = getNextExecTime(expressiion, 5);
            nextExecTime.stream().forEach(d -> {
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d));
            });
        }
    }

    /**
     * 解释cron表达式
     */
    public static String describeCron(String expressiion) {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        Cron cron = parser.parse(expressiion);
        //设置语言
        CronDescriptor descriptor = CronDescriptor.instance(Locale.CHINESE);
        return descriptor.describe(cron);
    }

    /**
     * 检查cron表达式的合法性
     *
     * @param cron cron exp
     * @return true if valid
     */
    public static boolean checkValid(String cron) {
        try {
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
            CronParser parser = new CronParser(cronDefinition);
            parser.parse(cron);
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("cron=%s not valid", cron));
            return false;
        }
        return true;
    }

    /**
     * @param cronExpression cron表达式
     * @param numTimes       下一(几)次运行的时间
     * @return
     */
    public static List<Date> getNextExecTime(String cronExpression, Integer numTimes) throws ParseException {
        List<String> list = new ArrayList<>();
        CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
        cronTriggerImpl.setCronExpression(cronExpression);
        // 这个是重点，一行代码搞定
        return TriggerUtils.computeFireTimes(cronTriggerImpl, null, numTimes);
    }

}
