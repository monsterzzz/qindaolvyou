package com.monster.contorller;

import com.monster.pojo.Message;
import com.monster.pojo.Photo;
import com.monster.pojo.SightSpot;
import com.monster.services.PhotoService;
import com.monster.services.SightService;
import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Controller
@RequestMapping("/admin")
public class AdminSightSpotController {

    @Autowired
    SightService sightService;
    @Autowired
    PhotoService photoService;


    @RequestMapping("/sight_spot/add")
    public String addSightSpotPage(Model model){
        SightSpot sightSpot = new SightSpot();
        model.addAttribute("sight_spot",sightSpot);
        model.addAttribute("add",true);
        return "SightSpotManager";
    }

    @RequestMapping("/sight_spot/rest/add")
    @ResponseBody
    public Message addSightSpotServer(@RequestBody SightSpot sightSpot){
        Message msg = new Message();
        Random random = new Random();
        sightSpot.setSpot_id(random.nextInt(10000) + 1000000);
        sightService.addSightSpot(sightSpot);
        msg.setCode(10001);
        msg.setMsg("add success");
        return msg;
    }

    @RequestMapping("/sight_spot/manager/{sid}")
    public String updateSightSpot(@PathVariable Integer sid, Model model){
        SightSpot sight_spot = sightService.querySightSpotBySid(sid);
        Photo small_img = photoService.querySmallImg(sid);
        model.addAttribute("sight_spot",sight_spot);
        model.addAttribute("small_img",small_img);
        System.out.println(sight_spot);
        return "SightSpotManager";
    }

    @RequestMapping("/sight_spot/rest/update")
    @ResponseBody
    public Message updateServer(@RequestBody SightSpot sightSpot){
        Message msg = new Message();
        System.out.println(sightSpot);
        sightSpot.setDescri(sightSpot.getDescri().trim());
        sightService.updateSightSpot(sightSpot);
        Photo small_photo = sightSpot.getSmall_img();
        if(photoService.smallPhotoExists(sightSpot.getSpot_id())){
            photoService.updateSmallPhoto(sightSpot.getSpot_id(),small_photo.getPath());
        }else {
            small_photo.setSid(sightSpot.getSpot_id());
            small_photo.setIs_small(1);
            photoService.addPhoto(small_photo);
        }
        msg.setCode(40000);
        msg.setMsg("update success");
        return  msg;
    }

    @RequestMapping("/sight_spot/rest/delete")
    @ResponseBody
    public Message deleteSightSpot(@RequestParam int sid){
        Message msg = new Message();
        sightService.deleteSightSpot(sid);
        msg.setCode(10002);
        msg.setMsg("delete success");
        return msg;
    }

    @RequestMapping("/photo/add")
    @ResponseBody
    public Message addPhoto(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest httpServletRequest) throws IOException {
        String filename = UUID.randomUUID().toString().replace("-","") + ".jpg";
        String path = httpServletRequest.getSession().getServletContext().getRealPath("/") + "img/" + filename ;
        File test_path = new File(httpServletRequest.getSession().getServletContext().getRealPath("/") + "img/");
        if(!test_path.exists()){
            test_path.mkdir();
        }
        multipartFile.transferTo(new File(path));
        //photoService.updateSmallPhoto(sid,"/img/" + filename);
        Message message = new Message();
        message.setCode(50000);
        message.setMsg("/img/" + filename);
        return message;
    }

    @RequestMapping("/bigSightSpot")
    public String bigSightSpo(Model model){
        List<SightSpot> sightSpots = sightService.getBigSightSpots();
        model.addAttribute("sightspots",sightSpots);
        model.addAttribute("flitername","大型景点");
        return "FilterPage";
    }

    @RequestMapping("/SeasonSightSpot/{season}")
    public String bigSightSpo(@PathVariable Integer season,Model model){
        if(season < 0 && season > 4){
            return null;
        }
        List<SightSpot> sightSpots = sightService.getSeasonSightSpots(season);
        model.addAttribute("sightspots",sightSpots);
        model.addAttribute("flitername","时令景点");
        return "FilterPage";
    }


}
