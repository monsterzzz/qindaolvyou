package com.monster.services;

import com.monster.pojo.Photo;

import java.util.List;

public interface PhotoService {
    void addPhoto(Photo photo);
    void deletePhoto(int pid);
    Photo querySmallImg(int sid);
    void updateSmallPhoto(int sid,String path);
    List<Photo> queryPhotosBySid(int sid);
    Photo queryPhotoByPid(int pid);
    Boolean smallPhotoExists(int sid);
}
