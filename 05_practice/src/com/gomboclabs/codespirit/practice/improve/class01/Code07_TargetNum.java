package com.gomboclabs.codespirit.practice.improve.class01;

import java.util.HashMap;

/**
 * @Auther: xucg
 * @Date: 2022/11/5 - 11 - 05 - 16:56
 * @Description: com.gomboclabs.codespirit.practice.improve.class01
 * @version: 1.0
 */
public class Code07_TargetNum {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        HashMap<Integer, HashMap<Integer, Integer>> dp = new HashMap<>();
        System.out.println(targetNum1(arr, 5, 0, 0, 0));
        System.out.println(targetNum2(arr, 0, 5));
        System.out.println(targetNum3(arr, 0, 5, dp));

    }

    /*
     * @param: res:保存上一次计算的结果值
     * @param: ans:保存有多少种可能
     *  */
    // 自己实现的比较low的方法
    public static int targetNum1(int[] arr, int target, int index, int res, int ans) {
        if (index == arr.length) {
            // 如果相等，返回1
            if (res == target) {
                return 1;
            }
            return 0;
        }
        int res1 = res + arr[index];
        int ans1 = ans;
        ans += targetNum1(arr, target, index + 1, res1, ans1);
        int res2 = res - arr[index];
        ans += targetNum1(arr, target, index + 1, res2, ans1);
        return ans;
    }

    // 左神简练的方法
    public static int targetNum2(int[] arr, int index, int rest) {
        if (index == arr.length) { // 没数了！
            return rest == 0 ? 1 : 0;
        }
        // 还有数！arr[index] arr[index+1 ... ]
        return targetNum2(arr, index + 1, rest - arr[index]) + targetNum2(arr, index + 1, rest + arr[index]);
    }

    // 存在可以利用的中间状态，所以设计缓存结构进行保存
    // HashMap<integer, HashMap<integer, integer>>
    //           index          rest        结果数
    public static int targetNum3(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
        // 边界条件
        if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
            return dp.get(index).get(rest);
        } else if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            int ans = targetNum3(arr, index + 1, rest - arr[index], dp) + targetNum3(arr, index + 1, rest + arr[index], dp);
            // 先判断是否有该index，没有则创建，
            if (!dp.containsKey(index)) {
                // 注意写法
                dp.put(index, new HashMap<>());
            }
            dp.get(index).put(rest, ans);
            return ans;
        }
    }
}
