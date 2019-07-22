package com.monster.dao;

import com.monster.pojo.Photo;

import java.util.List;

public interface PhotoDao {
    void addPhoto(Photo photo);
    void deletePhoto(int pid);
    Photo querySmallImg(int sid);
    void deleteSightSpotPhoto(int sid);
    List<Photo> queryPhotosBySid(int sid);
    void updateSmallPhoto(int sid,String path);
    Photo queryPhotoByPid(int pid);
    int smallPhotoExists(int sid);


}
