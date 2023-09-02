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
public class FindMedianSortedArrays01 {
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

        // 把两个数组合并成一个有序数组
        int[] total = new int[nums1.length + nums2.length];
        int nums1Index = 0;
        int nums2Index = 0;
        int totalIndex = 0;
        for (;totalIndex < nums1.length + nums2.length;){
            if (nums2Index >= nums2.length || nums1Index < nums1.length && nums1[nums1Index] <= nums2[nums2Index] ) { // 注意顺序
                total[totalIndex++] = nums1[nums1Index++];
            }else {
                total[totalIndex++] = nums2[nums2Index++];
            }
        }

        if ((total.length & 1) == 0){ // 偶数
            return (total[total.length / 2 - 1] + total[total.length / 2]) / 2.0;
        }else {
            return total[total.length / 2];
        }
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2};
        int[] nums2 = new int[]{3,4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
