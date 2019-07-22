package com.monster.utils;

import com.monster.pojo.SightSpot;
import com.monster.pojo.User;

import java.util.List;

public class UserPage {
    int total;
    int first = 0;
    int end;
    boolean has_pre = true;
    Integer current_page;
    boolean has_next = true;
    List<User> users;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public UserPage(Integer total, List<User> users, Integer current_page){
        this.total = total;
        int page_num = calcAllPageNum(total);
        end = page_num;
        if(current_page < 0){
            current_page = 0;
        }else if (current_page > page_num){
            current_page = page_num;
        }
        this.current_page = current_page;

        if(current_page == 0){
            setHas_pre(false);

        }
        if(current_page == page_num){
            setHas_next(false);
        }
        List<User> users1;
        try{
            users1 = users.subList(current_page * 10, current_page * 10 + 10);
        }catch (Exception e){
            if(current_page == 0){
                users1 = users.subList(current_page, users.size());
            }else {
                int list_end = total % 10;
                users1 = users.subList(current_page * 10, current_page * 10 + list_end);
            }
        }
        setUsers(users1);

    }



    public int calcAllPageNum(int total){
        int pageNum;
        pageNum = total / 10;
        return pageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isHas_pre() {
        return has_pre;
    }

    public void setHas_pre(boolean has_pre) {
        this.has_pre = has_pre;
    }

    public boolean isHas_next() {
        return has_next;
    }

    public void setHas_next(boolean has_next) {
        this.has_next = has_next;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserPage{" +
                "total=" + total +
                ", first=" + first +
                ", end=" + end +
                ", has_pre=" + has_pre +
                ", current_page=" + current_page +
                ", has_next=" + has_next +
                ", users=" + users +
                '}';
    }
}
