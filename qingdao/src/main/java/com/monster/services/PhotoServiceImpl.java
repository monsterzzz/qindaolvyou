package com.monster.services;

import com.monster.dao.PhotoDao;
import com.monster.pojo.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoDao photoDao;

    @Override
    public void addPhoto(Photo photo) {
        photoDao.addPhoto(photo);
    }

    @Override
    public void deletePhoto(int pid) {
        photoDao.deletePhoto(pid);
    }

    @Override
    public Photo querySmallImg(int sid) {
        return photoDao.querySmallImg(sid);
    }

    @Override
    public List<Photo> queryPhotosBySid(int sid) {
        return photoDao.queryPhotosBySid(sid);
    }

    @Override
    public Photo queryPhotoByPid(int pid) {
        return photoDao.queryPhotoByPid(pid);
    }

    @Override
    public Boolean smallPhotoExists(int sid) {
        if(photoDao.smallPhotoExists(sid) > 0){
            return true;
        }
        return false;
    }

    public void updateSmallPhoto(int sid,String path){
        photoDao.updateSmallPhoto(sid,path);
    }
}
