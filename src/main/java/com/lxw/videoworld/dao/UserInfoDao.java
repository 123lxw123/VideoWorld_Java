package com.lxw.videoworld.dao;

import com.lxw.videoworld.domain.UserInfo;

/**
 * Created by Zion on 2017/8/6.
 */
public interface UserInfoDao {
    int add(UserInfo userInfo);
    int update(UserInfo userInfo);
}
