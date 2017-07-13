package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.Feedback;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/20.
 */
public interface FeedbackDao extends Dao<Feedback> {
    int add(Feedback feedback);

    int del(Feedback feedback);

    int update(Feedback feedback);

    Feedback findOneById(Serializable Id);

    List<Feedback> findAll();

}
