package com.monster.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapContorller {

    @RequestMapping("/map")
    public String mymap(){
        return "mymap";
    }
}
