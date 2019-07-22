package com.monster.services;

import com.monster.dao.ActionDao;
import com.monster.pojo.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ActionsServiceImpl implements ActionsService {
    @Autowired
    ActionDao actionDao;

    @Override
    public void addAction(Action action) {
        actionDao.addAction(action);
    }

    @Override
    public List<Action> queryActionByUid(int uid) {
        return actionDao.queryActionByUid(uid);
    }

    @Override
    public List<Action> queryActionBySpotId(int spot_id) {
        return actionDao.queryActionBySpotId(spot_id);
    }

    @Override
    public List<Action> queryActionByTime(int want_time) {
        return actionDao.queryActionByTime(want_time);
    }

    @Override
    public Action queryActionByid(int id) {
        return actionDao.queryActionByid(id);
    }

    @Override
    public List<Integer> queryAllUserActionId(int uid) {
        return actionDao.queryAllUserActionId(uid);
    }

    @Override
    public void deleteAction(int uid, int sid) {
        actionDao.deleteAction(uid,sid);
    }
}
