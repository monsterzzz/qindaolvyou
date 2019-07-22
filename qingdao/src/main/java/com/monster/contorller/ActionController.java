package com.monster.contorller;

import com.monster.pojo.Action;
import com.monster.pojo.Message;
import com.monster.pojo.User;
import com.monster.services.ActionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("action")
public class ActionController {

    @Autowired
    ActionsService actionsService;

    @RequestMapping("/add/{sid}")
    @ResponseBody
    public Message addAction(HttpSession session, @PathVariable Integer sid){
        User user = (User) session.getAttribute("userSession");
        List<Integer> actions_list = actionsService.queryAllUserActionId(user.getId());
        Message message = new Message();
        if(actions_list.contains(sid)){
            message.setCode(70001);
            message.setMsg("actions exist!");
        }else {
            Action action = new Action();
            action.setSpot_id(sid);
            action.setWant_time(0);
            action.setUid(user.getId());
            actionsService.addAction(action);
            message.setCode(70000);
            message.setMsg("like success!");
        }
        return message;
    }

    @RequestMapping("/remove/{sid}")
    @ResponseBody
    public Message removeAction(HttpSession session, @PathVariable Integer sid){
        User user = (User) session.getAttribute("userSession");
        List<Integer> actions_list = actionsService.queryAllUserActionId(user.getId());
        Message message = new Message();
        if(!actions_list.contains(sid)){
            message.setCode(70001);
            message.setMsg("remove fail!");
        }else {

            actionsService.deleteAction(user.getId(),sid);
            message.setCode(71000);
            message.setMsg("remove success!");
        }
        return message;
    }


}
