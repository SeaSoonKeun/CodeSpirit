package Regex;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/3/13 - 03 - 13
 * description:
 */
public class Test {
    public static void main(String[] args) {
        String str = "StatementCallback; uncategorized SQLException for SQL [select t.area_name as res_area_name, t.month_id as res_month_id, count_num from ( select area_name, month_id, count(area_name) as count_num from mysql_cdp.data_cdp.dwd_product_expire_resource_info_wf t group by area_name, month_id ) t where t.area1_name in ('1') and t.month_id = '1']; SQL state [null]; error code [0]; VALIDATION ERROR: From line 18, column 5 to line 18, column 14: Column 'area1_name' not found in table 't' [Error Id: a467b2dd-4643-4fce-a773-14de21a90338 ]; nested exception is java.sql.SQLException: VALIDATION ERROR: From line 18, column 5 to line 18, column 14: Column 'area1_name' not found in table 't' [Error Id: a467b2dd-4643-4fce-a773-14de21a90338 ]";
        String[] split = str.split(";");
        String s = split[4];
        String[] split1 = s.split(":");
        String s1 = split1[2];
        String[] split2 = s1.split("\\[");
        System.out.println(split2[0]);
    }
}
