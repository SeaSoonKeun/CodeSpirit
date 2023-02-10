package com.gomboclabs.codespirit.practice.improve.class01;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * @Auther: xucg
 * @Date: 2022/12/1 - 12 - 01 - 19:07
 * @Description: com.gomboclabs.codespirit.practice.improve.class01
 * @version: 1.0
 */
public class test3 {
    public static void main(String[] args) {
        String sourceData = "";
        JSONObject jsonObject = JSONObject.parseObject(sourceData);
        JSONArray metrics = (JSONArray) jsonObject.get("metrics");
        JSONObject resJson = new JSONObject();
        ArrayList<String> recordEntries = new ArrayList<>();
        for (int i = 0; i < metrics.size(); i++) {
            JSONObject cur = metrics.getJSONObject(i);
            JSONObject fieldJson = cur.getJSONObject("fields");
            JSONObject tagJson = cur.getJSONObject("tags");
            Long timestamp = cur.getLong("timestamp");
            resJson.put("fields", fieldJson);
            resJson.put("tags", tagJson);
            resJson.put("_timestamp", timestamp);
        }
    }
}
