package com.txx.springboot.service;

import com.txx.springboot.entity.UserLogin;
import com.txx.springboot.json.Json;

/**
 * @author 淘米水浇花
 */
public interface UserLoginUserService {
    /**
     * 根据ID返回学生用户名和密码
     * @param id
     * @return
     */
    Json getUserLoginById(int id);
    /**
     * 注册学生用户密码
     * @param userLogin
     * @return
     */
    Json userLoginRegister(UserLogin userLogin);
    /**
     * 根据ID删除学生用户密码
     * @param id
     * @return
     */
    String deleteUserLogin(int id);
    /**
     * 修改学生用户密码
     * @param userLogin
     * @return
     */
    Json updateUserLogin(UserLogin userLogin,String token);
    /**
     * 根据ID判断是否存在该学生用户密码
     * @param id
     * @return
     */
    int isHasUserLogin(int id);
    /**
     * 用户登录
     * @param userLogin
     * @return
     */
    Json userLogin(UserLogin userLogin);

    Json isCompany(String token);

    Json loginByToken(String token);

}
