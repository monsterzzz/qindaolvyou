package com.monster.pojo;

import java.util.List;

public class SightSpot {
    int id;
    int spot_id;
    String name;
    String descri;
    String tel;
    String pay_time;
    double money;
    int open_time;
    int close_time;
    String tsf;
    List<Photo> photos;
    List<Comment> comments;
    Photo small_img;
    String time;
    int season;
    int big;

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getBig() {
        return big;
    }

    public void setBig(int big) {
        this.big = big;
    }


    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Photo getSmall_img() {
        return small_img;
    }

    public void setSmall_img(Photo small_img) {
        this.small_img = small_img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getOpen_time() {
        return open_time;
    }

    public void setOpen_time(int open_time) {
        this.open_time = open_time;
    }

    public int getClose_time() {
        return close_time;
    }

    public void setClose_time(int close_time) {
        this.close_time = close_time;
    }

    public String getTsf() {
        return tsf;
    }

    public void setTsf(String tsf) {
        this.tsf = tsf;
    }

    public void convertTime(){
        int open_h = open_time / 60;
        int open_m = open_time % 60;
        int close_h = close_time / 60;
        int close_m = close_time % 60;
        String s = String.format("%02d:%02d - %02d:%02d",open_h,open_m,close_h,close_m);
        this.time = s;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "SightSpot{" +
                "id=" + id +
                ", spot_id=" + spot_id +
                ", name='" + name + '\'' +
                ", descri='" + descri + '\'' +
                ", tel='" + tel + '\'' +
                ", pay_time='" + pay_time + '\'' +
                ", money=" + money +
                ", open_time=" + open_time +
                ", close_time=" + close_time +
                ", tsf='" + tsf + '\'' +
                ", photos=" + photos +
                ", comments=" + comments +
                ", small_img=" + small_img +
                ", time='" + time + '\'' +
                '}';
    }
}
