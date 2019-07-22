package com.monster.dao;

import com.monster.pojo.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    void deleteUser(int id);
    User queryUserById(int id);
    User queryUserByAccount(String account);
    int queryAccountExists(String account);
    int getTotal();
    void updateUser(User user);
    List<User> getAllUser();
    Integer isAdmin(int id);
}
