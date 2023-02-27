package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/2/27 - 02 - 27
 * description:
 */
public class PatternTest {
    public static void main(String[] args) {
        String demoString = "SELECT\n" +
                "  t.`t2_name` AS `t2_name`,\n" +
                "  t.`t3_name` AS `t3_name`\n" +
                "FROM\n" +
                "  (\n" +
                "    SELECT\n" +
                "      t2.`name` AS `t2_name`,\n" +
                "      t3.`name` AS `t3_name`\n" +
                "    FROM\n" +
                "      mysql130.database1.`table1` t1\n" +
                "      LEFT JOIN mysql130.database1.`table2` t2 ON t2.`id` = t1.`id`\n" +
                "      LEFT JOIN mysql130.database1.`table3` t3 ON t1.`id` = t3.`id`\n" +
                "  ) t\n" +
                "WHERE\n" +
                "  t.`t2_name` = '${t2_name}' and t.`t3_name` = '${t3_name}'";

        String demoString2 = "${t2_name}  ${t22_name}\r\n${t3_name}";
        String pattern = "\\$\\{[a-zA-Z]\\w{2,62}\\}";

        Pattern compiler = Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher matcher = compiler.matcher(demoString2);
        // 匹配到了，才开始处理
        while (matcher.find()) {
            System.out.println(matcher.group(0));

        }

        System.out.println("=====================================");
        // 有组的情况下，group(0)是整个匹配的字符串，group(1)是第一个组匹配的字符串
        // 无组的情况下，group(0)是整个匹配的字符串，group(1)是null
        // 无组的情况下，groupCount()是0
        // 有组的情况下，groupCount()是组的个数
        String demoString3 = "010-12345";
        String pattern2 = "(\\d{3})-(\\d{3,8})";
        Pattern compiler2 = Pattern.compile(pattern2);
        Matcher matcher2 = compiler2.matcher(demoString3);
        while (matcher2.find()) {
            System.out.println(matcher2.group(0));
            System.out.println(matcher2.group(1));
            System.out.println(matcher2.group(2));
        }
    }
}
