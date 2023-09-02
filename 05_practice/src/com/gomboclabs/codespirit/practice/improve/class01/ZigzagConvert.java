package com.gomboclabs.codespirit.practice.improve.class01;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author xucg
 * @version 1.0
 * Created on 2023/4/12 - 04 - 12
 * description:
 */
public class ZigzagConvert {
    public static String zigzagConvert(String s, int numRows) {
        char[] chars = s.toCharArray();
        HashMap<Integer, ArrayList<Character>> resMap = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        // 索引数组
        int indexArrayLength = 2 * numRows - 2;
        int[] indexArray = new int[indexArrayLength];
        for (int i = 0; i < numRows; i++) {
            indexArray[i] = i;
        }
        int topRight = numRows - 2;
        for (int i = numRows; i < indexArrayLength; i++) {
            indexArray[i] = topRight--;
        }
        // for (int i : indexArray) {
        //     System.out.println(i);
        // }

        for (int i = 0; i < chars.length; i++) {
            int index = indexArray[i % indexArrayLength];
            if (resMap.get(index) == null) {
                ArrayList<Character> arrayList = new ArrayList<>();
                arrayList.add(chars[i]);
                resMap.put(index, arrayList);
            } else {
                ArrayList<Character> curArrayList = resMap.get(index);
                curArrayList.add(chars[i]);
            }
        }
        if (resMap.size() == 0) {
            return "";
        }

        // [0, numRows - 1]
        for (int i = 0; i < numRows; i++) {
            ArrayList<Character> arrayList = resMap.get(i);
            arrayList.forEach(a -> sb.append(a));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String input = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(zigzagConvert(input, numRows));
    }
}
