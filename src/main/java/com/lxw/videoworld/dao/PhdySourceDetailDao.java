package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.SourceDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxw9047 on 2017/5/3.
 */
public interface PhdySourceDetailDao extends Dao<SourceDetail> {
    int add(SourceDetail sourceDetail);

    int del(SourceDetail sourceDetail);

    int update(SourceDetail sourceDetail);

    SourceDetail findOneById(Serializable Id);

    List<SourceDetail> findAll();
}
