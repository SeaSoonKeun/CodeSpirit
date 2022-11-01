package com.gomboclabs.codespirit.practice.improve.class01;

/**
 * @Auther: xucg
 * @Date: 2022/11/1 - 11 - 01 - 13:37
 * @Description: com.gomboclabs.codespirit.practice.improve.class01
 * @version: 1.0
 */
public class Code03_Near2Power {
    public static void main(String[] args) {
        int cap = 1073741823;
        System.out.println(near2Power(cap));
        System.out.println(tableSizeFor(cap));
    }

    public static int near2Power(int num){
        // 非正数情况
        if (num <= 0) {
            return 1;
        }
        int res;
        // 兼容2的整数幂情况
        num--;
        num |= num >>> 1;
        num |= num >>> 2;
        num |= num >>> 4;
        num |= num >>> 8;
        num |= num >>> 16;
        // +1 进位，得到2的整数幂
        res = ++num;
        return res;
    }

    // for test by zuo
    public static int tableSizeFor(int n) {
        // n-- 可避免int类型的数字越界
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }
}
