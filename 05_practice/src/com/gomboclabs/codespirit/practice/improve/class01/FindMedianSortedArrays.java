package com.gomboclabs.codespirit.practice.improve.class01;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/4/3 - 04 - 03
 * description:
 *
 * 加强版的二分查找
 *
 * 长度不够进行补充
 *
 * 两个有序数组
 * nums1 nums2
 *
 *
 */
public class FindMedianSortedArrays {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        }

        // 有一个为空数组的情况
        if (nums1.length == 0) {
            if (nums2.length % 2 == 1) { // 奇数
                int i = (int)(nums2.length / 2);
                return (double) nums2[i];
            }else {
                int i = (int)(nums2.length / 2);
                return (nums2[i] + nums2[i - 1]) / 2.0;
            }
        }

        if (nums2.length == 0) {
            if (nums1.length % 2 == 1) { // 奇数
                int i = (int)(nums1.length / 2);
                return (double) nums1[i];
            }else {
                int i = (int)(nums1.length / 2);
                return (nums1[i] + nums1[i - 1]) / 2.0;
            }
        }

        // 两个数组都不为空的情况
        // 如果m + n为偶数，找到第（m + n）/2 ,（m + n）/2 + 1 的数
        // 如果m + n为奇数，找到第 （m + n )/2 + 1 的数

        // 如何得出第几大的值

        // 第一次比较，两个数组的最小值比较，确认排名第一的数字
        // 第二次比较，上一次比较的较大者，与较小者数组右移一位的值进行比较，确认排名第二的数字
        // 第三次比较，上一次比较的较大者，与较小者数组右移一位的值进行比较，确认排名第三的数字
        // ……
        // 直到找到排名为（m + n）/2 + 1的数结束

        int length1 = nums1.length;
        int length2 = nums2.length;
        // s1为nums1数组的游标，s2为nums2数组的游标
        int s1 = 0;
        int s2 = 0;
        // 记录排名为i的数值
        int upMedian = 0;
        int median = 0;
        // 遍历找 第几大 的值，直到排名为（m + n）/2 + 1 的数
        for (int i = 0; i < (length1 + length2)/2 + 1; i++){
            upMedian = median;
            if ((s1 < length1 && s2 < length2 && nums1[s1] <= nums2[s2]) || s2 >= length2) { // s1 s2不能越界,如果s1越界，则进入另外一个数组继续
                median = nums1[s1++]; // 小的数组游标右移一位进行比较
            }else {
                median = nums2[s2++];
            }
        }

        if (((length1 + length2) & 1) == 0){ // 奇数
            return (upMedian + median)/2.0;
        }else {
            return median;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{100001};
        int[] nums2 = new int[]{100000};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
