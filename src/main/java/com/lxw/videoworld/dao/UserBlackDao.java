package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.UserBlack;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Zion on 2017/8/6.
 */
public interface UserBlackDao {
    int add(UserBlack userBlack);
    UserBlack findOneByUid(@Param("uid")String uid);
}
