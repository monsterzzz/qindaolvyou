package com.monster.pojo;

public class Photo {
    int id;
    int sid;
    int is_small;
    String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getIs_small() {
        return is_small;
    }

    public void setIs_small(int is_small) {
        this.is_small = is_small;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
