package com.txx.springboot.service;

import com.txx.springboot.entity.UserInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 淘米水浇花
 */
public class UserInfoRowMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(resultSet.getInt("id"));
        userInfo.setUserTimeOn(resultSet.getString("userTimeOn"));
        userInfo.setHeadImg(resultSet.getObject("headImg"));
        userInfo.setUserName(resultSet.getString("userName"));
        userInfo.setSex(resultSet.getBoolean("sex"));
        userInfo.setUserNativePlace(resultSet.getString("userNativePlace"));
        userInfo.setSchool(resultSet.getString("school"));
        userInfo.setUserNo(resultSet.getLong("userNo"));
        userInfo.setUserBirth(resultSet.getDate("userBirth"));
        userInfo.setUserProfile(resultSet.getString("userProfile"));
        userInfo.setUserEdu(resultSet.getString("userEdu"));
        userInfo.setUserTel(resultSet.getLong("userTel"));
        return userInfo;
    }
}
