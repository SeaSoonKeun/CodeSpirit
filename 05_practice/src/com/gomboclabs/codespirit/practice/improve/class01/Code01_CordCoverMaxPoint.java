package com.gomboclabs.codespirit.practice.improve.class01;

import java.util.ArrayList;

public class Code01_CordCoverMaxPoint {
    public static void main(String[] args) {
        int[] dotList = new int[]{210, 255, 273, 300, 304, 382, 717, 739, 938};
        int k = 977;
        System.out.println(cordCoverMaxPoint(dotList, k));
    }

    public static int cordCoverMaxPoint(int[] dotList, int k) {
        int maxCoverNum = 1;
        ArrayList<Integer> startPointIndex = new ArrayList<>();
        for (int i = 0; i < dotList.length; i++) {
            int curCoverNum = 1;
            for (int j = i; j < dotList.length - 1; j++) {
                if (dotList[j + 1] - dotList[i] <= k) {
                    curCoverNum++;
                    if (curCoverNum > maxCoverNum) {
                        maxCoverNum = curCoverNum;
                        startPointIndex = new ArrayList<>();
                        startPointIndex.add(i);
                    }else if (curCoverNum == maxCoverNum){
                        startPointIndex.add(i);
                    }
                }else {
                    break;
                }
            }
        }
//        System.out.println("覆盖最多点位时，绳子的起始点包含：");
//        for (Integer pointIndex : startPointIndex) {
//            System.out.println(pointIndex);
//        }
        return maxCoverNum;
    }
}


