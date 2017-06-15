package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.SearchResult;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Zion on 2017/6/15.
 */
public interface SearchResultDao extends Dao<SearchResult> {
    int add(SearchResult searchResult);

    int del(SearchResult searchResult);

    int update(SearchResult searchResult);

    SearchResult findOneById(Serializable Id);

    List<SearchResult> findAll();

    List<SearchResult> getRecordByParams(@Param("uid")String uid, @Param("url")String url);
}
