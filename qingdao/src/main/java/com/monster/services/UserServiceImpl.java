package com.monster.services;

import com.monster.dao.UserDao;
import com.monster.pojo.Action;
import com.monster.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    ActionsService actionsService;

    @Override
    public void addUser(User user) throws Exception {
        if(userDao.queryAccountExists(user.getAccount()) == 1){
            throw new Exception();
        }
        userDao.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        List<Integer> actions = actionsService.queryAllUserActionId(id);
        for(Integer sid:actions){
            actionsService.deleteAction(id,sid);
        }
        userDao.deleteUser(id);
    }

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public User queryUserByAccount(String account) {
        return userDao.queryUserByAccount(account);
    }

    @Override
    public int queryAccountExists(String account) {
        return userDao.queryAccountExists(account);
    }

    @Override
    public int getTotal() {
        return userDao.getTotal();
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public Boolean isAdmin(Integer id) {
        if(userDao.isAdmin(id) == 1){
            return true;
        }
        return  false;
    }

    public User cheakLogin(User user){
        String account = user.getAccount();
        System.out.println(user);
        User db_user = userDao.queryUserByAccount(account);
        if(user.equals(db_user)){
            if(userDao.isAdmin(db_user.getId()) >= 1){
                db_user.setAdmin(true);
            }else {
                db_user.setAdmin(false);
            }
            return db_user;
        }
        return null;
    }
}
