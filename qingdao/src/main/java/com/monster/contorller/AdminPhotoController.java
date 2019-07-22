package com.monster.contorller;

import com.monster.pojo.Message;
import com.monster.pojo.Photo;
import com.monster.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPhotoController {

    @Autowired
    PhotoService photoService;

    @RequestMapping("/photo/manager/{pid}/remove")
    @ResponseBody
    public Message removePhoto(@PathVariable Integer pid, HttpServletRequest httpServletRequest){
        Message msg = new Message();
        try{
            Photo photo = photoService.queryPhotoByPid(pid);
            String path = photo.getPath();
            System.out.println(path);
            if(!path.contains("http")){
                path = httpServletRequest.getSession().getServletContext().getRealPath("/") + path;
                try{
                    File file = new File(path);
                    file.delete();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            photoService.deletePhoto(pid);
            msg.setMsg("remove success!");
            msg.setCode(60000);
        }catch (Exception e){
            msg.setMsg("remove fali!");
            msg.setCode(60001);
        }
        return msg;
    }

    @RequestMapping("/photo/manager/{sid}/add")
    @ResponseBody
    public Message removePhoto(@PathVariable Integer sid,Photo photo){
        Message msg = new Message();
        try{
            photo.setSid(sid);
            photo.setIs_small(0);
            photoService.addPhoto(photo);
            msg.setMsg("add success!");
            msg.setCode(61000);
        }catch (Exception e){
            msg.setMsg("add fail!");
            msg.setCode(61001);
        }
        return msg;
    }

    @RequestMapping("/photo/manager/{sid}")
    public String photoIndex(@PathVariable Integer sid, Model model){
        List<Photo> photos = photoService.queryPhotosBySid(sid);
        for(Photo photo : photos){
            if(photo.getIs_small() == 1){
                photos.remove(photo);
                break;
            }
        }
        model.addAttribute("photos",photos);
        return "PhotoManager";
    }


}
