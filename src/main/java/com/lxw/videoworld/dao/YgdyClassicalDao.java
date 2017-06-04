package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.Source;
import com.lxw.videoworld.domain.SourceDetail;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/20.
 */
public interface YgdyClassicalDao extends Dao<Source> {
    int add(Source source);

    int del(Source source);

    int update(Source source);

    Source findOneById(Serializable Id);

    List<Source> findAll();

    List<SourceDetail> getRecordByType(@Param("start")int start, @Param("limit")int limit, @Param("category")String category, @Param("type")String type);
}
