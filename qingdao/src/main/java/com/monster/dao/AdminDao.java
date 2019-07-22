package com.monster.dao;

import com.monster.pojo.Admin;

public interface AdminDao {
    void addAdmin(Admin admin);
    void deleteUser(int id);
    Admin queryAdminById(int id);
}
