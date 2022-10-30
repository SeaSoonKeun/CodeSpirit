package com.gomboclabs.codespirit.practice.improve.class01;


import org.junit.Test;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {
    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * max);
            int[] dotList = generateArray(len, max);
            int ans1 = cordCoverMaxPoint1(dotList, k);
            int ans2 = cordCoverMaxPoint2(dotList, k);
            int ans3 = cordCoverMaxPoint3(dotList, k);
            int ans4 = test(dotList, k);
            if (ans1 == ans2 || ans2 == ans3 || ans3 == ans4) {
                    System.out.println("Congratulations!!!");
            } else {
                System.out.println("dotList : [");
                for (int i1 : dotList) {
                    System.out.printf(i1 + ", ");
                }
                System.out.println("]");
                System.out.println("k :" + k);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                System.out.println("ooops, try again");
            }
        }
    }


    // 暴力for循环
    public static int cordCoverMaxPoint1(int[] dotList, int k) {
        int maxCoverNum = 1;
        for (int i = 0; i < dotList.length; i++) {
            int curCoverNum = 1;
            for (int j = i; j < dotList.length - 1; j++) {
                if (dotList[j + 1] - dotList[i] <= k) {
                    curCoverNum++;
                    if (curCoverNum > maxCoverNum) {
                        maxCoverNum = curCoverNum;
                    }
                } else {
                    break;
                }
            }
        }
        return maxCoverNum;
    }

    // 二分法找指定位置
    @Test
    public static int cordCoverMaxPoint2(int[] dotList, int k) {
//    public void cordCoverMaxPoint2() {
//        int[] dotList = new int[]{205, 284, 559, 615, 726};
//        int k = 220;
        int maxCoverNum = 1;
        int left = 0;
        int L;
        int R = dotList.length - 1;
        for (; left < dotList.length; left++) {
            L = left;
            R = dotList.length - 1;
            // 注意此处需要两个括号
            int Mid = L + ((R - L) >> 1);
            while (L <= R) {
                Mid = L + ((R - L) >> 1);
                // 能压到最远的值在mid右边
                if (dotList[Mid] - dotList[L] <= k) {
                    L = Mid + 1;
                }
                // 能压到最远的值在mid左边
                else {
                    R = Mid - 1;
                }
            }
            // 此处Mid已经超出最远能够到的右边界，所以需要-1
            maxCoverNum = Math.max(Mid - left + 1, maxCoverNum);
        }
        return maxCoverNum;
//        System.out.println(maxCoverNum);
    }

    @Test
    // 双指针（滑动窗口）
    public static int cordCoverMaxPoint3(int[] dotList, int k) {
//    public void cordCoverMaxPoint3() {
//        int[] dotList = new int[]{205, 284, 559, 615, 726};
//        int k = 220;
        int cordMaxNum = 1;
        int L = 0;
        // R 指针其实位置也为0，因为不一定能覆盖到
        int R = 0;
        // L从0往右移
        for (; L < dotList.length; L++) {
            // R不越界 且 绳子能压住R所在的点，R往右移
            while (L < dotList.length && R < dotList.length && dotList[R] - dotList[L] <= k) {
                R++;
            }
            // R点绳子压不住，R回退一点
            R = R - 1;
            // 比较当前R和L距离与目前最大距离，取最大值
            // 此处要注意，下标是从0开始的，所以需要+1
            cordMaxNum = Math.max(cordMaxNum, R - L + 1);
        }
        return cordMaxNum;
//        System.out.println(cordMaxNum);
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

}


