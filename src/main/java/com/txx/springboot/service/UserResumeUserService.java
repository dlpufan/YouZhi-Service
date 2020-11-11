package com.txx.springboot.service;


import com.txx.springboot.entity.UserResume;
import com.txx.springboot.json.Json;

/**
 * @author 淘米水浇花
 */
public interface UserResumeUserService {
    /**
     * 获取全部学生简历
     * @return
     */
    Json getAllUserResume();

    /**
     * 根据ID获取学生简历
     * @param id
     * @param userResume
     * @return
     */
    Json getUserResumeById(int id , UserResume userResume);

    /**
     * 增加学生简历
     * @param userResume
     * @return
     */
    Json addUserResume(UserResume userResume,String token);

    /**
     * 根据ID删除学生简历
     * @param id
     * @return
     */
    String deleteUserResume(int id);

    /**
     * 修改学生简历信息
     * @param userResume
     * @return
     */
    Json updateUserResume(UserResume userResume,String token);

    /**
     * 根据ID判断是否存在该学生简历
     * @param id
     * @return
     */
    int isHasUserResume(int id);
}
