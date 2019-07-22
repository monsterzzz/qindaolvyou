package com.monster.services;

import com.monster.pojo.Action;

import java.util.List;

public interface ActionsService {
    void addAction(Action action);
    List<Action> queryActionByUid(int uid);
    List<Action> queryActionBySpotId(int spot_id);
    List<Action> queryActionByTime(int want_time);
    Action queryActionByid(int id);
    List<Integer> queryAllUserActionId(int uid);
    void deleteAction(int uid,int sid);
}
