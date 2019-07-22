package com.monster.utils;

import com.alibaba.fastjson.JSON;
import com.monster.pojo.Action;
import com.monster.pojo.SightSpot;
import com.monster.services.ActionsService;
import com.monster.services.SightService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RecommendUtil {

    SightService sightService;
    ActionsService actionsService;

    private Map<String, Map<String,Double>> similyMartix;

    public RecommendUtil(SightService sightService,ActionsService actionsService) throws IOException {
        this.sightService = sightService;
        this.actionsService = actionsService;
        File file = new File("C:\\Users\\monster\\Desktop\\ItemCf\\martix.txt");
        FileReader fileInputStream = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
        StringBuilder current_line = new StringBuilder();
        while (true){
            String tmp_line = bufferedReader.readLine();
            if(tmp_line == null){
                break;
            }
            current_line.append(tmp_line);
        }
        similyMartix = (Map<String,Map<String,Double>>) JSON.parse(current_line.toString());
    }

    public List<SightSpot> similySightSpot(Integer sid) throws IOException {

        List<Map.Entry<String,Double>> mapList = sortList(similyMartix.get(sid.toString()),5);
        List<SightSpot> sightSpots = new ArrayList<>();
        for (Map.Entry<String,Double> entry: mapList){
            try {
                sightSpots.add(sightService.querySightSpotBySid(Integer.valueOf(entry.getKey())));
            }catch (Exception e){
                e.printStackTrace();
                continue;
            }
        }
        return sightSpots;
    }

    public List<SightSpot> recommendByUser(Integer uid){
        List<Action> actions = actionsService.queryActionByUid(uid);
        List<Integer> spot_id_list = new ArrayList<>();
        List<Map.Entry<String,Double>> similydoule = new ArrayList<>();
        List<SightSpot> sightSpots = new ArrayList<>();
        List<SightSpot> result = new ArrayList<>();
        for(Action action:actions){
            spot_id_list.add(action.getSpot_id());
        }

        for (Integer spot_id:spot_id_list){
            List<Map.Entry<String,Double>> sort_list = sortList(similyMartix.get(String.valueOf(spot_id)),3);
            for(Map.Entry<String,Double> entry:sort_list){
                try{
                    if(spot_id_list.contains(Integer.valueOf(entry.getKey())) ){
                        continue;
                    }
                    //sightSpots.add(sightService.querySightSpotBySid(Integer.valueOf(entry.getKey())));
                    similydoule.add(entry);
                }catch (Exception e){
                    continue;
                }
            }
        }
        similydoule = innerSort(similydoule);

        for (Map.Entry<String,Double> entry:similydoule) {
            try{
                result.add(sightService.querySightSpotBySid(Integer.valueOf(entry.getKey())));
            }catch (Exception e){
                continue;
            }
        }

        return result.subList(0,6);
    }

    public List<Map.Entry<String,Double>> sortList(Map<String,Double> map,Integer length){
        List<Map.Entry<String,Double>> entryList = new ArrayList<>(map.entrySet());
        entryList = innerSort(entryList);
        if(length > entryList.size()){
            length = entryList.size();
        }
        return entryList.subList(0,length);
    }

    public List<Map.Entry<String,Double>> innerSort(List<Map.Entry<String,Double>> entryList){
        entryList.sort((o1, o2) -> {
            Double v1 = Double.valueOf(String.valueOf(o1.getValue()));
            Double v2 = Double.valueOf(String.valueOf(o2.getValue()));
            if(v1 > v2){
                return -1;
            }
            return 1;
        });
        return entryList;
    }



}
