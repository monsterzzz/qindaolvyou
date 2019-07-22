package com.monster.contorller;

import com.alibaba.fastjson.JSON;
import com.monster.pojo.Action;
import com.monster.pojo.SightSpot;
import com.monster.pojo.User;
import com.monster.services.ActionsService;
import com.monster.services.SightService;
import com.monster.services.UserService;
import com.monster.services.UserServiceImpl;
import com.sun.deploy.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    ActionsService actionsService;
    @Autowired
    SightService sightService;
    @Autowired
    UserService userService;

    @RequestMapping("/detail")
    public String userDetail(HttpSession session, Model model) throws IOException {
        User user = (User) session.getAttribute("userSession");
        model.addAttribute("user",user);
        List<Integer> sid_list = actionsService.queryAllUserActionId(user.getId());
        List<SightSpot> sightSpots = new ArrayList<>();
        for(Integer sid : sid_list){
            try {
                sightSpots.add(sightService.querySightSpotBySid(sid));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        model.addAttribute("sightspots",sightSpots);
        return "userMsg";
    }
}
