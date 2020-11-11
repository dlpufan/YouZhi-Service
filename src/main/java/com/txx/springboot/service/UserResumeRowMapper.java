package com.txx.springboot.service;

import com.txx.springboot.entity.UserResume;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 淘米水浇花
 */
public class UserResumeRowMapper implements RowMapper<UserResume>{
    @Override
    public UserResume mapRow(ResultSet resultSet, int i) throws SQLException {
        UserResume userResume = new UserResume();
        userResume.setId(resultSet.getInt("id"));
        userResume.setUserPro(resultSet.getString("userPro"));
        userResume.setCertificate(resultSet.getString("certificate"));
        userResume.setJobHistory(resultSet.getString("jobHistory"));
        userResume.setUserSalDesire(resultSet.getInt("userSalDesire"));
        userResume.setUserDesire(resultSet.getString("userDesire"));
        return userResume;
    }
}
