package com.gomboclabs.codespirit.practice.improve.class01;

import java.util.Arrays;

/**
 * @Auther: xucg
 * @Date: 2022/11/2 - 11 - 02 - 13:04
 * @Description: com.gomboclabs.codespirit.practice.improve.class01
 * @version: 1.0
 */
public class Code04_MinSwapStep {
    // 一个数组中只有两种字符'G'和'B'，
    // 可以让所有的G都放在左侧，所有的B都放在右侧
    // 或者可以让所有的G都放在右侧，所有的B都放在左侧
    // 但是只能在相邻字符之间进行交换操作，请问请问至少需要交换几次，
    public static void main(String[] args) {
        // String s1 = "GBBBBBGB";
        // int a = minSwapStep(s1, 'G');
        // int b = minSwapStep(s1, 'B');
        // System.out.println("最少交换次数：" + Math.min(a, b));
        int maxLen = 100;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            String str = randomString(maxLen);
            int ans1 = Math.min(minSteps1(str, 'G'), minSteps1(str, 'B'));
            int ans2 = minSteps2(str);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
    // 可借鉴左神的写法，在一次循环中比较两次，保存两次结果
    public static int minSteps1(String str, char target) {
        if (str == null || str.equals("")) {
            return 0;
        }
        int res = 0;
        int L = 0;
        int R = 0;
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                R = i;
                res += R - L++;
            }
        }
        return res;
    }

    // zuo for contrast
    public static int minSteps2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] str = s.toCharArray();
        int step1 = 0;
        int step2 = 0;
        int gi = 0;
        int bi = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 'G') { // 当前的G，去左边   方案1
                step1 += i - (gi++);
            } else {// 当前的B，去左边   方案2
                step2 += i - (bi++);
            }
        }
        return Math.min(step1, step2);
    }

    // for test
    public static String randomString(int maxLen) {
        char[] str = new char[(int) (Math.random() * maxLen)];
        for (int i = 0; i < str.length; i++) {
            str[i] = Math.random() < 0.5 ? 'G' : 'B';
        }
        return String.valueOf(str);
    }
}
