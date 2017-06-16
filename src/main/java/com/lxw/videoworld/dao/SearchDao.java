package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.Search;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zion on 2017/6/15.
 */
public interface SearchDao extends Dao<Search> {
    int add(Search search);

    int del(Search search);

    int update(Search search);

    Search findOneById(Serializable Id);

    List<Search> findAll();

    List<Search> getRecordByParams(@Param("uid")String uid, @Param("url")String url);
}
