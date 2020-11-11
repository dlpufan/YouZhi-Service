package com.txx.springboot.service;

import com.txx.springboot.entity.UserInfo;
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
import java.util.Map;

/**
 * @author 淘米水浇花
 */
@Service
public class UserInfoUserServiceImpl implements UserInfoUserService {
    private final JdbcTemplate jdbcTemplate;
    private  Tools tools;
    public UserInfoUserServiceImpl(JdbcTemplate jdbcTemplate,Tools tools) {
        this.jdbcTemplate = jdbcTemplate;
        this.tools = tools;
    }
    @Override
    public Json getAllUserInfo() {
        String sql = "select * from t_user_info";
        MessageCode messageCode = new MessageCode();
        List<Map<String , Object>> list = jdbcTemplate.queryForList(sql);
        return new Json(messageCode , list);
    }

    @Override
    public Json getUserInfoById(int id) {
        UserInfo userInfo;
        String sql = "select * from t_user_info where id = ?";
        MessageCode messageCode = new MessageCode();
        List<UserInfo> list = jdbcTemplate.query(sql , new Object[] {id} , new UserInfoRowMapper());
        if(!list.isEmpty()){
            userInfo = list.get(0);
        }
        else {
            userInfo = null;
            messageCode.setMessage("未查询到该用户id ， 返回失败");
            messageCode.setCode(0);
        }
        return new Json(messageCode , userInfo);
    }

    @Override
    public Json registerUserInfo(UserInfo userInfo) {
        String sql = "insert into t_user_info(id , userTimeOn , headImg , userName , sex , " +
                "userNativePlace , school , userNo , userBirth , userProfile , userEdu , userTel)" +
                " values(null , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
        MessageCode messageCode = new MessageCode();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql , new String[] {"id"});
                ps.setObject(1 , Tools.getLocalTime());
                ps.setObject(2 , userInfo.getHeadImg());
                ps.setString(3 , userInfo.getUserName());
                ps.setBoolean(4 , userInfo.isSex());
                ps.setString(5 , userInfo.getUserNativePlace());
                ps.setString(6 , userInfo.getSchool());
                if(userInfo.getUserNo()!=null){
                    ps.setLong(7 , userInfo.getUserNo());
                }
                else{
                    ps.setLong(7 , 0);
                }
                ps.setDate(8 , userInfo.getUserBirth());
                ps.setString(9 , userInfo.getUserProfile());
                ps.setString(10 , userInfo.getUserEdu());
                if(userInfo.getUserTel()!=null){
                    ps.setLong(11 , userInfo.getUserTel());
                }
                else{
                    ps.setLong(11 , 0);
                }
                return ps;
            }
        } , keyHolder);
        userInfo.setId(keyHolder.getKey().intValue());
        userInfo.setUserTimeOn(Tools.getLocalTime());
        return new Json(messageCode , userInfo);
    }

    @Override
    public String deleteUserInfo(int id) {
        String sql = "delete from t_user_info where id = ?";
        int res = jdbcTemplate.update(sql , id);
        return res == 1 ? "删除成功" : "未找到id ， 删除失败";
    }

    @Override
    public Json updateUserInfo(UserInfo userInfo,String token) {
        if(!tools.isHasToken(token)){
            return new Json(false);
        }
        String sql = "update t_user_info set headImg = ? , " +
                "userName = ? , sex = ? , userNativePlace = ? , school = ? , " +
                "userNo = ? , userBirth = ? , userProfile = ? , userEdu = ? , " +
                "userTel= ? where id = ?";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setObject(1 , userInfo.getHeadImg());
                preparedStatement.setString(2 , userInfo.getUserName());
                preparedStatement.setBoolean(3 , userInfo.isSex());
                preparedStatement.setString(4 , userInfo.getUserNativePlace());
                preparedStatement.setString(5 , userInfo.getSchool());
                preparedStatement.setLong(6 , userInfo.getUserNo());
                preparedStatement.setDate(7 , userInfo.getUserBirth());
                preparedStatement.setString(8 , userInfo.getUserProfile());
                preparedStatement.setString(9 , userInfo.getUserEdu());
                preparedStatement.setLong(10 , userInfo.getUserTel());
                preparedStatement.setInt(11 , userInfo.getId());
            }
        });
        return new Json(messageCode , userInfo);
    }

    @Override
    public int isHasUser(int id) {
        String sql = "select * from t_user_info where id = ?";
        List<UserInfo> list = jdbcTemplate.query(sql , new Object[] {id} , new UserInfoRowMapper());
        if(list.size() > 0){
            return 1;
        }
        return 0;
    }
}
