package com.gomboclabs.codespirit.practice.improve.class01;

import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/4/1 - 04 - 01
 * description:
 */
public class LengthOfLongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        char[] sArray = s.toCharArray();
        HashMap<Character, Integer> positionMap = new HashMap<>();
        for (; right < sArray.length; right++) {
            if (positionMap.containsKey(sArray[right])) {
                if (positionMap.get(sArray[right]) >= left) {
                    left = positionMap.get(sArray[right]) + 1;
                }
                positionMap.put(sArray[right], right);
            }else {
                positionMap.put(sArray[right], right);
            }
            maxLength = Math.max(right - left + 1, maxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String input = "tmmzuxt";
        System.out.println(lengthOfLongestSubstring(input));
    }
}
