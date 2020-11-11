package com.txx.springboot.service;

import com.txx.springboot.entity.UserInfo;
import com.txx.springboot.entity.UserLogin;
import com.txx.springboot.json.Json;
import com.txx.springboot.json.MessageCode;
import com.txx.springboot.tool.Tools;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 淘米水浇花
 */
@Service
public class UserLoginUserServiceImpl implements UserLoginUserService {
    private final JdbcTemplate jdbcTemplate;
    private final Tools tools;
    private String token;

    public UserLoginUserServiceImpl(JdbcTemplate jdbcTemplate, Tools tools) {
        this.jdbcTemplate = jdbcTemplate;
        this.tools = tools;
    }

    @Override
    public Json getUserLoginById(int id) {
        UserLogin userLogin;
        String sql = "select * from t_user_login where id = ?";
        MessageCode messageCode = new MessageCode();
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {id} , new UserLoginRowMapper());
        if(!list.isEmpty()){
            userLogin = list.get(0);
        }
        else {
            userLogin = null;
            messageCode.setMessage("未查询到该用户id ， 返回失败");
            messageCode.setCode(0);
        }
        return new Json(messageCode , userLogin);
    }
    @Override
    public Json userLoginRegister(UserLogin userLogin) {
        String sql = "insert into t_user_login(id , username ," +
                " password , token )" +
                " values(null , ? , ? , ? )";
        MessageCode messageCode = new MessageCode();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isUserRepeat = tools.isHasUsername(userLogin.getUsername());
        if(isUserRepeat){
            messageCode.setMessage("您的用户名已经被注册");
            messageCode.setCode(0);
            return new Json(messageCode , null);
        }
        int resRow = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql , new String[] {"id"});
                ps.setString(1 , userLogin.getUsername());
                try {
                    ps.setString(2 , tools.shaEncode(userLogin.getPassword()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    token = tools.shaEncode(userLogin.getUsername() + userLogin.getPassword() + Tools.getLocalTime());
                    ps.setString(3 , token);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ps;
            }
        } , keyHolder);
        userLogin.setId(keyHolder.getKey().intValue());
        try {
            userLogin.setPassword(tools.shaEncode(userLogin.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userLogin.setToken(token);
        System.out.println("操作记录数："+ resRow + "主键数：" + keyHolder.getKey());
        System.out.println(userLogin);
        new UserInfoUserServiceImpl(jdbcTemplate,tools).registerUserInfo(new UserInfo());
        userLogin.setIdentity(0);
        return new Json(messageCode , userLogin);
    }

    @Override
    public String deleteUserLogin(int id) {
        String sql = "delete from t_user_login where id = ?";
        int res = jdbcTemplate.update(sql , id);
        return res == 1 ? "删除成功" : "未找到id ， 删除失败";
    }

    @Override
    public Json updateUserLogin(UserLogin userLogin,String token) {
        if(!tools.isHasToken(token)){
            return new Json(false);
        }
        String sql = "update t_user_login set username = ? , password = ? , identity = ?" +
                " where id = ?";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1 , userLogin.getUsername());
                try {
                    preparedStatement.setString(2 , tools.shaEncode(userLogin.getPassword()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                preparedStatement.setInt(3 , userLogin.getIdentity());
                preparedStatement.setInt(4 , userLogin.getId());
            }
        });
        try {
            userLogin.setPassword(tools.shaEncode(userLogin.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userLogin.getToken());
        return new Json(messageCode , userLogin);
    }

    @Override
    public int isHasUserLogin(int id) {
        String sql = "select * from t_user_login where id = ?";
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {id} , new UserLoginRowMapper());
        return list.size() > 0 ? 1 : 0;
    }

    @Override
    public Json userLogin(UserLogin userLogin) {

        String sql = "select * from t_user_login where username = ? and password = ?";
        MessageCode messageCode = new MessageCode();
        List<UserLogin> list = null;
        try {
            list = jdbcTemplate.query(sql , new Object[] {userLogin.getUsername(),tools.shaEncode(userLogin.getPassword())} , new UserLoginRowMapper());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!list.isEmpty()){
            userLogin = list.get(0);
        }
        else {
            messageCode.setMessage("用户名或密码错误！");
            messageCode.setCode(0);
            return new Json(messageCode , "error");
        }
        return new Json(messageCode , userLogin);
    }

    @Override
    public Json isCompany(String token) {
        if(!tools.isHasToken(token)){
            return new Json(false);
        }
        return tools.isCompany(token)? new Json(new MessageCode("是公司",1),1):new Json(new MessageCode("不是公司",0),0);
    }

    @Override
    public Json loginByToken(String token) {
        if(!tools.isHasToken(token)){
            return new Json(false);
        }
        String sql = "select * from t_user_login where token = ?";
        List<UserLogin> list = jdbcTemplate.query(sql , new Object[] {token} , new UserLoginRowMapper());
        return new Json(list.get(0));
    }


}
