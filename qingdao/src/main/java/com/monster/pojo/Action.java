package com.monster.pojo;

public class Action {
    int id;
    int want_time;
    int spot_id;
    int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWant_time() {
        return want_time;
    }

    public void setWant_time(int want_time) {
        this.want_time = want_time;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", want_time=" + want_time +
                ", spot_id=" + spot_id +
                '}';
    }
}
