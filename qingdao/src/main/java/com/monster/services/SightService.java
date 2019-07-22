package com.monster.services;

import com.monster.pojo.SightSpot;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface SightService {
    int getTotal();
    void addSightSpot(SightSpot sightSpot);
    void deleteSightSpot(int sid);
    void updateSightSpot(SightSpot sightSpot);
    SightSpot querySightSpotByid(int id);
    SightSpot querySightSpotBySid(int sid);
    List<SightSpot> getAllSightSpot();
    List<SightSpot> getPageSightSpot(int start,int end);
    List<SightSpot> getSightSpotByName(String name);
    List<SightSpot> getSightSpotByTime(int time,int start);
    int getTimeTotal(int time);
    int getNameTotal(String name);
    List<SightSpot> getBigSightSpots();
    List<SightSpot> getSeasonSightSpots(int season);




}
