package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.SourceDetail;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lxw9047 on 2017/5/3.
 */
public interface KkwSourceDetailDao extends Dao<SourceDetail> {
    int add(SourceDetail sourceDetail);

    int del(SourceDetail sourceDetail);

    int update(SourceDetail sourceDetail);

    SourceDetail findOneById(Serializable Id);

    List<SourceDetail> findAll();

    List<SourceDetail> getRecordByType(@Param("start") int start, @Param("limit") int limit, @Param("category") String category, @Param("type") String type);
}
