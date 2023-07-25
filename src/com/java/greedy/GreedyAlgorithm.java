package com.java.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {

    public static void main(String[] args) {
        HashMap<String, HashSet<String>> hashMap = new HashMap<>();
        // 放入数据
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        hashMap.put("k1", hashSet1);
        hashMap.put("k2", hashSet2);
        hashMap.put("k3", hashSet3);
        hashMap.put("k4", hashSet4);
        hashMap.put("k5", hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        // 存放已选择电台的集合
        ArrayList<String> selects = new ArrayList<>();

        // 定义临时集合，在遍历的过程中，存放遍历过程中电台覆盖地区的个数与总集合中还未被覆盖地区的交集的个数
        HashSet<String> tempSet = new HashSet<String>();

        // 定义 maxKey，保存在一次遍历过程中，能够覆盖地区最多的key值。如果 maxKey 不为空，则加入到 selects 中
        String maxKey = null;
        while (allAreas.size() != 0) {
            maxKey = null;
            // 遍历存放电台的map，取出对应的key
            for (String key : hashMap.keySet()) {
                tempSet.clear();
                HashSet<String> currAreas = hashMap.get(key);
                tempSet.addAll(currAreas);
                // 求出 tempSet 和 allAreas 集合的交集，并将交集结果赋给 tempSet
                tempSet.retainAll(allAreas);
                // 得到上一个maxKey对应的覆盖地区的个数
                int size = 0;
                if (maxKey != null) {
                    HashSet<String> lastSet = new HashSet<>();
                    lastSet = hashMap.get(maxKey);
                    lastSet.retainAll(allAreas);
                    size = lastSet.size();
                }
                // 如果当前 key 包含了未覆盖的地区，并且比上一个key未覆盖的地区还多，则进行重新赋值
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > size)) {
                    maxKey = key;
                }
            }

            if (maxKey != null) {
                selects.add(maxKey);
                // 将 maxKey 指向的地区从 allAreas 中去除
                allAreas.removeAll(hashMap.get(maxKey));
            }
        }
        System.out.println("集合" + selects);
    }
}






