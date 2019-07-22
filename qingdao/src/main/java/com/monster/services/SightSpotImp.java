package com.monster.services;

import com.monster.dao.CommentDao;
import com.monster.dao.PhotoDao;
import com.monster.dao.SightSpotDao;
import com.monster.pojo.Comment;
import com.monster.pojo.Photo;
import com.monster.pojo.SightSpot;
import org.apache.ibatis.executor.ExecutorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SightSpotImp implements SightService {

    @Autowired
    SightSpotDao sightSpotDao;
    @Autowired
    PhotoDao photoDao;
    @Autowired
    CommentDao commentDao;

    public int getTotal() {
        return sightSpotDao.getTotal();
    }

    public void addSightSpot(SightSpot sightSpot) {
        Photo photo = sightSpot.getSmall_img();
        photo.setIs_small(1);
        photo.setSid(sightSpot.getSpot_id());
        photoDao.addPhoto(photo);
        sightSpotDao.addSightSpot(sightSpot);
    }

    public void deleteSightSpot(int sid) {
        photoDao.deleteSightSpotPhoto(sid);
        commentDao.deleteSightSpotComment(sid);
        sightSpotDao.deleteSightSpot(sid);
    }

    public void updateSightSpot(SightSpot sightSpot) {
        photoDao.updateSmallPhoto(sightSpot.getSpot_id(),sightSpot.getSmall_img().getPath());
        sightSpotDao.updateSightSpot(sightSpot);
    }

    public SightSpot querySightSpotByid(int id) {
        SightSpot sightSpot = sightSpotDao.querySightSpotByid(id);
        int sid = sightSpot.getSpot_id();
        SightSpot sightSpot1 = this.querySightSpotBySid(sid);
        return  sightSpot1;
    }

    //@Transactional(rollbackFor = Exception.class)
    public SightSpot querySightSpotBySid(int sid) {
        SightSpot sightSpot = sightSpotDao.querySightSpotBySid(sid);
        List<Photo> photos = photoDao.queryPhotosBySid(sid);
        for(Photo p : photos){
            if(p.getIs_small() == 1){
                photos.remove(p);
                sightSpot.setSmall_img(p);
                break;
            }
        }
        sightSpot.setPhotos(photos);
        List<Comment> comments = commentDao.queryComment(sid);
        sightSpot.setComments(comments);
        //throw new ExecutorException();
        return  sightSpot;
    }

    public List<SightSpot> getAllSightSpot() {
        List<SightSpot> sightSpots;
        sightSpots = sightSpotDao.getAllSightSpot();
        return sightSpots;
    }

    @Override

    public List<SightSpot> getPageSightSpot(int start, int end) {
        List<SightSpot> sightSpots = sightSpotDao.getPageSightSpot(start,end);
        for(SightSpot s : sightSpots){
            int sid = s.getSpot_id();
            List<Photo> photos = photoDao.queryPhotosBySid(sid);
            for(Photo p : photos){
                if(p.getIs_small() == 1){
                    s.setSmall_img(p);
                    photos.remove(p);
                    break;
                }
            }
            s.setPhotos(photos);
            List<Comment> comments = commentDao.queryComment(sid);
            s.setComments(comments);
        }
        return sightSpots;
    }

    @Override
    public List<SightSpot> getSightSpotByName(String name) {
        List<SightSpot> sightSpots = sightSpotDao.getSightSpotByName(name);
        for (int i = 0; i <  sightSpots.size(); i++) {
            SightSpot tmp = sightSpots.get(i);
            int spot_id = tmp.getSpot_id();
            tmp.setComments(commentDao.queryComment(spot_id));
            List<Photo> photos = photoDao.queryPhotosBySid(spot_id);
            for(Photo p : photos){
                if(p.getIs_small() == 1){
                    tmp.setSmall_img(p);
                    break;
                }
            }
            tmp.setPhotos(photos);
        }
        return sightSpots;
    }

    @Override
    public List<SightSpot> getSightSpotByTime(int time,int start) {
        int end = 10;
        List<SightSpot> sightSpots = sightSpotDao.getSightSpotByTime(time,start,end);
        for (int i = 0; i <  sightSpots.size(); i++) {
            SightSpot tmp = sightSpots.get(i);
            int spot_id = tmp.getSpot_id();
            tmp.setSmall_img(photoDao.querySmallImg(spot_id));
        }
        return sightSpots;
    }

    @Override
    public int getTimeTotal(int time) {
        return sightSpotDao.getTimeTotal(time);
    }

    @Override
    public int getNameTotal(String name) {
        return sightSpotDao.getNameTotal(name);
    }

    @Override
    public List<SightSpot> getBigSightSpots() {
        List<SightSpot> sightSpots = sightSpotDao.getBigSightSpots();
        for(SightSpot s : sightSpots){
            Photo smallImg = photoDao.querySmallImg(s.getSpot_id());
            s.setSmall_img(smallImg);
        }
        return sightSpots;
    }

    @Override
    public List<SightSpot> getSeasonSightSpots(int season) {
        List<SightSpot> sightSpots = sightSpotDao.getSeasonSightSpots(season);
        for(SightSpot s : sightSpots){
            Photo smallImg = photoDao.querySmallImg(s.getSpot_id());
            s.setSmall_img(smallImg);
        }
        return sightSpots;
    }


}
