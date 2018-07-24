package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.Source;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxw9047 on 2017/4/20.
 */
public interface KkwSourceDao extends Dao<Source> {
    int add(Source source);

    int del(Source source);

    int update(Source source);

    int updateStatus(@Param("url") String url, @Param("status") String status);

    Source findOneById(Serializable Id);

    List<Source> findAll();

    List<String> findAllUrl();

    List<String> findAllUrlNoDetail();

}
