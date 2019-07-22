package com.monster.dao;

import com.monster.pojo.Action;

import java.util.List;

public interface ActionDao {
    void addAction(Action action);
    List<Action> queryActionByUid(int uid);
    List<Action> queryActionBySpotId(int spot_id);
    List<Action> queryActionByTime(int want_time);
    Action queryActionByid(int id);
    List<Integer> queryAllUserActionId(int uid);
    void deleteAction(int uid,int sid);
}
