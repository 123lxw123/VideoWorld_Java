package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.Source;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/20.
 */
public interface PhdySourceDao extends Dao<Source> {
    int add(Source source);

    int del(Source source);

    int update(Source source);

    int updateStatus(String url, String status);

    Source findOneById(Serializable Id);

    List<Source> findAll();

    List<String> findAllUrlNoDetail();

}
