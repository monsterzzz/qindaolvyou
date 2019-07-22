package com.monster.utils;

import com.monster.dao.SightSpotDao;
import com.monster.pojo.SightSpot;
import com.monster.services.SightService;
import com.monster.services.SightSpotImp;

import java.util.List;

public class Page {
    int total;
    int first = 0;
    int end;
    boolean has_pre = true;
    Integer current_page;
    boolean has_next = true;
    List<SightSpot> sightSpots;

    public String getHead_path() {
        return head_path;
    }

    public void setHead_path(String head_path) {
        this.head_path = head_path;
    }

    String head_path;

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

    public Page(Integer total,List<SightSpot> sightSpots, Integer current_page,String path){
        this.total = total;
        head_path = path;
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



        setSightSpots(sightSpots);

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

    public List<SightSpot> getSightSpots() {
        return sightSpots;
    }

    public void setSightSpots(List<SightSpot> sightSpots) {
        this.sightSpots = sightSpots;
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", has_pre=" + has_pre +
                ", current_page=" + current_page +
                ", has_next=" + has_next +
                ", sightSpots=" + sightSpots +
                '}';
    }
}
