import com.alibaba.fastjson.JSON;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.*;

public class ItemBaseCF {



    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSource();
        String formatString = dataSource.queryActionData("all");
        Map<String,Map<String,String>> map = readData(formatString);
        Map<String,Map<String,Double>> similyMartix = calcSimilyMartix(map);
        String json = JSON.toJSONString(similyMartix);
        FileOutputStream fileOutputStream = new FileOutputStream("martix.txt");
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(json);
//        Map<String,Map<String,Double>> s = (Map<String,Map<String,Double>>) JSON.parse(json);
//        System.out.println(s);
//        System.out.println(json);
//        System.out.println(map);
//        System.out.println(similyMartix);
//        System.out.println(map.get("0"));
        List<Map.Entry<String, Double>> result1 = recommend(map,similyMartix,"3",30,10);
        ;
        //List<Map.Entry<String, Double>> result5 = recommend(map,similyMartix,"5",30,10);


        System.out.println(result1);



    }


    public static Map<String,Map<String,String>> readData(String formatString){
        Map<String,Map<String,String>> map = new HashMap<>();
        String[] allLine = formatString.trim().split("\n");

        for (String line:allLine) {
            String[] msg = line.trim().split(",");
            try{
                Map<String,String> innerMap = map.get(msg[0]);
                innerMap.put(msg[2],msg[1]);
            }catch (Exception e){
                Map<String,String> innerMap = new HashMap<>();
                innerMap.put(msg[2],msg[1]);
                map.put(msg[0],innerMap);
            }
        }
        return map;
    }


    public static Map<String,Map<String,Double>> calcSimilyMartix(Map<String,Map<String,String>> map){
        Map<String,Map<String,Double>> itemfindmap = new HashMap<>();
        Map<String,Double> itemcount = new HashMap<>();
        for (String key: map.keySet()) {
            for (String innerkey: map.get(key).keySet()) {
                try{
                    Double count = itemcount.get(innerkey);
                    count += 1;
                    itemcount.put(innerkey,count);
                }catch (Exception e){
                    itemcount.put(innerkey,1.0);
                }
                Map<String,Double> innerMap = new HashMap<>();
                itemfindmap.putIfAbsent(innerkey,innerMap);
                for (String innerKey2: map.get(key).keySet()) {
                    if(innerKey2.equals(innerkey)){
                        continue;
                    }
                    innerMap = itemfindmap.get(innerkey);
                    innerMap.putIfAbsent(innerKey2,0.0);
                    innerMap.put(innerKey2,innerMap.get(innerKey2) + 1/Math.log(1+map.get(key).keySet().size()));
                    itemfindmap.put(innerkey,innerMap);
                }

            }

        }
        //System.out.println(itemfindmap);

        Map<String,Map<String,Double>> stringIntegerMap = new HashMap<>();
        Map<String,Double> W_max = new HashMap<>();  // 纪录列的最大值

        for (Map.Entry<String,Map<String,Double>> entry: itemfindmap.entrySet()) {
            stringIntegerMap.putIfAbsent(entry.getKey(),new HashMap<>());
            for (Map.Entry<String,Double> innerEntry : entry.getValue().entrySet()){
                W_max.putIfAbsent(innerEntry.getKey(),0.0);
                Double similarScore = innerEntry.getValue()/Math.sqrt( itemcount.get(entry.getKey()) * itemcount.get(innerEntry.getKey()));
                stringIntegerMap.get(entry.getKey()).putIfAbsent(innerEntry.getKey(),similarScore);
                if(stringIntegerMap.get(entry.getKey()).get(innerEntry.getKey()) > W_max.get(innerEntry.getKey())){
                    W_max.put(innerEntry.getKey(),stringIntegerMap.get(entry.getKey()).get(innerEntry.getKey()));
                }
                //stringIntegerMap.get(entry.getKey()).putIfAbsent(innerEntry.getKey(),0.0);
            }

        }

        // 归一化处理
        for(Map.Entry<String,Map<String,Double>> tmp1:itemfindmap.entrySet()){
            for(Map.Entry<String,Double> tmp2 : tmp1.getValue().entrySet()){
                Double old_value = stringIntegerMap.get(tmp1.getKey()).get(tmp2.getKey());
                stringIntegerMap.get(tmp1.getKey()).put(tmp2.getKey(),old_value/W_max.get(tmp2.getKey()));
            }
        }

        return stringIntegerMap;
    }


    public static List<Map.Entry<String, Double>> recommend(Map<String, Map<String, String>> map, Map<String,Map<String,Double>> similyMartix, String user, Integer max_simily, Integer max_good) {

        Map<String, Double> doubleMap = new HashMap<>();
        Map<String, String> userItem = map.get(user);

        for (Map.Entry<String, String> entry : userItem.entrySet()) {
            List<Map.Entry<String, Double>> entryList = new ArrayList<>();
            for (Map.Entry<String, Double> innerEntry : similyMartix.get(entry.getKey()).entrySet()) {
                entryList.add(innerEntry);
            }
            entryList.sort((o1, o2) -> {
                if (o1.getValue() < o2.getValue()) {
                    return 1;
                }
                return -1;
            });

            if(max_simily > entryList.size()){
                max_simily = entryList.size();
            }
            //System.out.println("*");
            for (Map.Entry<String, Double> setEntry : entryList.subList(0,max_simily)) {
                //System.out.println("11" + entryList.subList(0,max_simily));
                if (userItem.keySet().contains(setEntry.getKey())) {
                    continue;
                }
                Double want = doubleMap.getOrDefault(setEntry.getKey(), 0.0) + setEntry.getValue();
                doubleMap.putIfAbsent(setEntry.getKey(), 0.0);
                doubleMap.put(setEntry.getKey(), want);
            }
        }
        List<Map.Entry<String,Double>> result_list = new ArrayList<>();
        for (Map.Entry<String,Double> tmp: doubleMap.entrySet()) {
            result_list.add(tmp);
        }
        result_list.sort((o1, o2) -> {
            if (o1.getValue() < o2.getValue()) {
                return 1;
            }
            return -1;
        });

        if(max_good > result_list.size()){
            max_good = result_list.size();
        }

        result_list = result_list.subList(0,max_good);

        return result_list;
    }



}
