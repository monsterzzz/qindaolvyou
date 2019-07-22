package com.monster.services;

import com.monster.pojo.User;

import java.util.List;


public interface UserService {
    void addUser(User user) throws Exception;
    void deleteUser(int id);
    User queryUserById(int id);
    User queryUserByAccount(String account);
    int queryAccountExists(String account);
    int getTotal();
    void updateUser(User user);
    List<User> getAllUser();
    Boolean isAdmin(Integer id);

}
