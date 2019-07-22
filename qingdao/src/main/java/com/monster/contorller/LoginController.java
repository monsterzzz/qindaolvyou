package com.monster.contorller;


import com.monster.pojo.Message;
import com.monster.pojo.User;
import com.monster.services.SightSpotImp;
import com.monster.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    @ResponseBody
    public Message login(User user, HttpSession httpSession){
        Message message = new Message();
        User find_user = userService.cheakLogin(user);
        if(find_user == null){
            message.setCode(10001);
            message.setMsg("login fail!");
        }else{
            message.setCode(10000);
            message.setMsg("login success!");
        }
        httpSession.setAttribute("userSession",find_user);
        System.out.println(httpSession.getAttribute("userSession"));
        return message;
    }

    @RequestMapping("sign_in")
    @ResponseBody
    public Message signIn(User user){
        Message message = new Message();
        try{
            userService.addUser(user);
            message.setCode(20000);
            message.setMsg("sign in success!");
        }catch (Exception e){
            e.printStackTrace();
            message.setCode(20001);
            message.setMsg("sign in fail!");
        }
        return message;
    }

    @RequestMapping("log_out")
    public String logOut(HttpSession session){
        try {
            session.removeAttribute("userSession");
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
