package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.SourceDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * Created by Zion on 2018/5/5.
 */
public interface LocalSearchDao {
    List<SourceDetail> getRecordByKeyword(@Param("keyword")String keyword);
}
