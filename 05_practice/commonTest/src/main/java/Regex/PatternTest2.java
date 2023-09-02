package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/3/7 - 03 - 07
 * description:
 */
public class PatternTest2 {
    public static void main(String[] args) {
        // String input = "Hello ${param_name1}, welcome to '${param_name2}' Java programming!";
        String input = "select t.`xxxx` as x1, t.`yyyy` as y1 from ( select t2.`name` as `xxxx`, t3.`param_name` as `yyyy` from mysql11.drillTest.`api_request_param_unit_assoc` t1 left join mysql11.drillTest.`api_config` t2 on t2.`id` = t1.`api_id` left join mysql11.drillTest.`api_request_param` t3 on t1.`api_request_param_id` = t3.`id` ) t where t.`yyyy` = 'xx${param_namex}yy' and t.`xxxx` = 'xcg' and t.`xxxx` = ${param_namey}${param_namez}";
        String patternString = "(')\\$\\{[a-zA-Z]\\w{2,62}\\}(')";
        String patternString2 = "(?<!')\\$\\{[a-zA-Z]\\w{2,62}\\}(?!')";
        String patternString3 = "(?<!')\\$\\{[a-zA-Z]\\w{2,62}\\}(?!')";



        Pattern pattern = Pattern.compile(patternString2);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            System.out.println(match);
        }
    }
}
