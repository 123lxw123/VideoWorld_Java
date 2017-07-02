package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.Config;

import java.io.Serializable;

/**
 * Created by Zion on 2017/6/3.
 */
public interface ConfigDao extends Dao<Config> {
    Config findOneById(Serializable Id);
    int updateKeyword(String keyword);
}
