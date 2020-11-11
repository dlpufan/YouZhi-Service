package com.txx.springboot.service;

import com.txx.springboot.entity.UserInfo;
import com.txx.springboot.json.Json;

/**
 * @author 淘米水浇花
 */
public interface UserInfoUserService {
    /**
     * 获取全部学生用户
     * @return
     */
    Json getAllUserInfo();

    /**
     * 根据ID获取学生用户
     * @param id
     * @return
     */
    Json getUserInfoById(int id);

    /**
     * 增加学生用户
     * @param userInfo
     * @return
     */
    Json registerUserInfo(UserInfo userInfo);

    /**
     * 根据ID删除学生用户
     * @param id
     * @return
     */
    String deleteUserInfo(int id);

    /**
     * 修改学生用户信息
     * @param userInfo
     * @return
     */
    Json updateUserInfo(UserInfo userInfo,String token);

    /**
     * 根据ID判断是否存在该学生
     * @param id
     * @return
     */
    int isHasUser(int id);
}
