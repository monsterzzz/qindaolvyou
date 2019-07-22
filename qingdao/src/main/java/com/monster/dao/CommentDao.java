package com.monster.dao;

import com.monster.pojo.Comment;

import java.util.List;

public interface CommentDao {
    void addComment(Comment comment);
    void deleteComment(int id);
    void deleteSightSpotComment(int sid);
    List<Comment> queryComment(int sid);
}
