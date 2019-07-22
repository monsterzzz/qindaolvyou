package com.monster.contorller;

import com.monster.pojo.Message;
import com.monster.pojo.User;
import com.monster.services.UserService;
import com.monster.utils.UserPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminUserController {


    @Autowired
    UserService userService;

    @RequestMapping("/user/{page}")
    public String queryUserBypage(@PathVariable("page") Integer page, Model model){
        Integer total = userService.getTotal();
        UserPage userPage = new UserPage(total,userService.getAllUser(),page);
        model.addAttribute("page",userPage);
        return "userList";
    }

    @RequestMapping("/user/query")
    public String queryUserByAccount(@RequestParam("account") String account, Model model){
        List<User> users = new ArrayList<>();
        users.add(userService.queryUserByAccount(account));
        UserPage userPage = new UserPage(1,users,1);

        model.addAttribute("page",userPage);
        model.addAttribute("word",account);
        return "userList";
    }

    @RequestMapping("/user/rest/delete")
    @ResponseBody
    public Message deleteUserById(@RequestParam("uid") Integer uid){
        System.out.println(uid);
        Message message = new Message();
        if(!userService.isAdmin(uid)){
            userService.deleteUser(uid);
            message.setCode(60000);
            message.setMsg("delete success!");
        }else {
            message.setCode(60001);
            message.setMsg("delete fail,delete user is admin!");
        }
        return message;
    }

}
