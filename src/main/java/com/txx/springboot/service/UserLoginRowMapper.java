package com.txx.springboot.service;

import com.txx.springboot.entity.UserLogin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 淘米水浇花
 */
public class UserLoginRowMapper implements RowMapper<UserLogin> {
    @Override
    public UserLogin mapRow(ResultSet resultSet, int i) throws SQLException {
        UserLogin userLogin = new UserLogin();
        userLogin.setId(resultSet.getInt("id"));
        userLogin.setUsername(resultSet.getString("username"));
        userLogin.setPassword(resultSet.getString("password"));
        userLogin.setToken(resultSet.getString("token"));
        userLogin.setIdentity(resultSet.getInt("identity"));
        return userLogin;
    }
}
