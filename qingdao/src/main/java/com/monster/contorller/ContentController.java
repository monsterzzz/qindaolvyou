package com.monster.contorller;
import com.alibaba.fastjson.JSON;
import com.monster.pojo.Action;
import com.monster.pojo.Photo;
import com.monster.pojo.SightSpot;
import com.monster.pojo.User;
import com.monster.services.ActionsService;
import com.monster.services.PhotoService;
import com.monster.services.SightService;
import com.monster.services.SightSpotImp;
import com.monster.utils.Page;
import com.monster.utils.RecommendUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    SightService sightService;
    @Autowired
    ActionsService actionsService;
    @Autowired
    PhotoService photoService;



    @RequestMapping("")
    public String index(){
        return "redirect:/content/0";
    }

    @RequestMapping("/{page}")
    public String sightSpotList(@PathVariable Integer page, Model model,HttpSession session) throws IOException {
        System.out.println(page.toString());
        int start = page * 10;
        int end = 10;
        List<SightSpot> sightSpots = sightService.getPageSightSpot(start,end);
        Page true_page = new Page(sightService.getTotal(),sightSpots,page,"content");
        User user = (User) session.getAttribute("userSession");
        RecommendUtil recommendUtil = new RecommendUtil(sightService,actionsService);
        model.addAttribute("page",true_page);
        model.addAttribute("recommend",recommendUtil.recommendByUser(user.getId()));
        System.out.println(true_page);
        return "content";
    }

//    @RequestMapping("/Season/{season}/{page}")
//    public String SeasonSightSpotList(@PathVariable Integer page, @PathVariable Integer season, Model model,HttpSession session) throws IOException {
//        int total = 0;
//        List<SightSpot> sightSpots = sightService.getPageSightSpot(start,end);
//        Page true_page = new Page(sightService.getTotal(),sightSpots,page,"content");
//        User user = (User) session.getAttribute("userSession");
//        RecommendUtil recommendUtil = new RecommendUtil(sightService,actionsService);
//        model.addAttribute("page",true_page);
//        model.addAttribute("recommend",recommendUtil.recommendByUser(user.getId()));
//        System.out.println(true_page);
//        return "content";
//    }

    @RequestMapping("/detail/{sid}")
    public String sightSpotDetail(@PathVariable Integer sid,Model model,HttpSession session) throws IOException {
        SightSpot sightSpot = sightService.querySightSpotBySid(sid);
        sightSpot.convertTime();
        System.out.println(sightSpot);
        model.addAttribute("sightspot",sightSpot);
        User user = (User) session.getAttribute("userSession");
        List<Integer> sid_list = actionsService.queryAllUserActionId(user.getId());
        Boolean flag = false;
        if(sid_list.contains(sid)){
            flag = true;
        }
        model.addAttribute("islike",flag);
        RecommendUtil recommendUtil = new RecommendUtil(sightService,actionsService);
        List<SightSpot> sightSpots = new ArrayList<>();
        try{
            sightSpots = recommendUtil.similySightSpot(sid);
        }catch (Exception e){
            e.printStackTrace();
        }

        //List<SightSpot> sightSpots1 = recommendUtil.recommendByUser(111);
        //System.out.println(sightSpots1.size());
        //model.addAttribute("recommend1",sightSpots1);
        model.addAttribute("recommend",sightSpots);
        return "detail";
    }
    
    @RequestMapping("/search/{type}/{word}/{page}")
    public String searchSightSpot(HttpSession session, @PathVariable("type") String type, @PathVariable("word") String word, @PathVariable("page") Integer page, Model model) throws IOException {
        List<SightSpot> sightSpots;
        List<SightSpot> recommendSightSpots = new ArrayList<>();
        Page true_page;
        User user = (User) session.getAttribute("userSession");
        int total = 0;
        if (type.equals("place")){
            sightSpots =  sightService.getSightSpotByName(word);
            RecommendUtil recommendUtil = new RecommendUtil(sightService,actionsService);
            recommendSightSpots = recommendUtil.recommendByUser(user.getId());
            total = sightService.getNameTotal(word);
        }else{
            sightSpots  = sightService.getSightSpotByTime(Integer.valueOf(word),page);
            //sightSpots =  sightService.getSightSpotByName(word);

            RecommendUtil recommendUtil = new RecommendUtil(sightService,actionsService);
            List<SightSpot> all_recommend =  recommendUtil.recommendByUser(user.getId());
            recommendSightSpots = new ArrayList<>();
            Integer time = Integer.valueOf(word);
            for(SightSpot s : all_recommend){
//                System.out.println(time);
//                System.out.println(s.getOpen_time() <= time && s.getClose_time() > time);
//                if (s.getOpen_time() <= time && s.getClose_time() > time){
//                    s.setSmall_img(photoService.querySmallImg(s.getSpot_id()));
//                    recommendSightSpots.add(s);
//                }
                s.setSmall_img(photoService.querySmallImg(s.getSpot_id()));
                recommendSightSpots.add(s);
                if (recommendSightSpots.size() >= 6){
                    break;
                }
            }
            total = sightService.getTimeTotal(time);
        }
        System.out.println(recommendSightSpots);
        true_page = new Page(total,sightSpots,page,"content/search/" + type + "/" + word );
        model.addAttribute("page",true_page);
        model.addAttribute("recommend",recommendSightSpots);
        return "content";
    }

}
