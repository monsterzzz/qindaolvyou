package com.monster.contorller;

import com.alibaba.fastjson.JSON;
import com.monster.pojo.Message;
import com.monster.pojo.SightSpot;
import com.monster.pojo.User;
import com.monster.services.SightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    
    @Autowired
    SightService sightService;

    @RequestMapping("/{sid}")
    @ResponseBody
    public List<SightSpot> recommend(@PathVariable Integer sid, HttpSession session) throws IOException {
        User user = (User)session.getAttribute("userSession");
        if( user == null){
            System.out.println("!");
        }else {
            System.out.println(user.getName());
        }
//        File file = new File("C:\\Users\\monster\\Desktop\\ItemCf\\martix.txt");
//        FileReader fileInputStream = new FileReader(file);
//        BufferedReader bufferedReader = new BufferedReader(fileInputStream);
//        StringBuilder current_line = new StringBuilder();
//        while (true){
//            String tmp_line = bufferedReader.readLine();
//            if(tmp_line == null){
//                break;
//            }
//            current_line.append(tmp_line);
//        }
//        Map<String,Map<String,Double>> mapMap = (Map<String,Map<String,Double>>) JSON.parse(current_line.toString());
//        List<Map.Entry<String,Double>> mapList = new ArrayList<>();
//
//        for (Map.Entry<String,Double> entry:  mapMap.get(sid.toString()).entrySet()) {
//            entry.setValue(Double.valueOf(String.valueOf(entry.getValue())));
//            mapList.add(entry);
//        }
//        System.out.println(mapList.get(0));
//        boolean a = mapList.get(0).getValue() > mapList.get(1).getValue();
//        System.out.println(a);
//        mapList.sort((o1, o2) -> {
//            if(o1.getValue() > o2.getValue()){
//                return -1;
//            }else {
//                return 1;
//            }
//        });
//        int max_recommend = 5;
//        if(max_recommend > mapList.size()){
//            max_recommend = mapList.size();
//        }
//        mapList = mapList.subList(0,max_recommend);
//        List<SightSpot> sightSpots = new ArrayList<>();
//        for (Map.Entry<String,Double> entry: mapList){
//            sightSpots.add(sightService.querySightSpotBySid(Integer.valueOf(entry.getKey())));
//        }
        return null;
    }
}
