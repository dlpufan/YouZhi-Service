package com.txx.springboot.service;

import com.txx.springboot.entity.UserResume;
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
public class UserResumeUserServiceImpl implements UserResumeUserService {
    private final JdbcTemplate jdbcTemplate;
    private final Tools tools;
    public UserResumeUserServiceImpl(JdbcTemplate jdbcTemplate,Tools tools) {
        this.jdbcTemplate = jdbcTemplate;
        this.tools = tools;
    }
    @Override
    public Json getAllUserResume() {
        String sql = "select * from t_user_resume";
        MessageCode messageCode = new MessageCode();
        List<Map<String , Object>> list = jdbcTemplate.queryForList(sql);
        return new Json(messageCode , list);
    }

    @Override
    public Json getUserResumeById(int id , UserResume userResume) {

        String sql = "select * from t_user_resume where id = ?";
        MessageCode messageCode = new MessageCode();
        List<UserResume> list = jdbcTemplate.query(sql , new Object[] {id} , new UserResumeRowMapper());
        if(!list.isEmpty()){
            userResume = list.get(0);
        }
        else {
            userResume = null;
            messageCode.setMessage("未查询到该用户id ， 返回失败");
            messageCode.setCode(0);
        }
        return new Json(messageCode , userResume);
    }

    @Override
    public Json addUserResume(UserResume userResume,String token) {
        if(!tools.isHasToken(token)){
            return new Json(false);
        }

        String sql = "insert into t_user_resume(id , userID , userPro , certificate ," +
                " jobHistory , userSalDesire , userDesire)" +
                " values(null , ? , ? , ? , ? , ? , ? )";
        MessageCode messageCode = new MessageCode();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int resRow = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql , new String[] {"id"});
                ps.setInt(1 , tools.getUserInfoByToken(token).get(0).getId());
                ps.setString(2 , userResume.getUserPro());
                ps.setString(3 , userResume.getCertificate());
                ps.setString(4 , userResume.getJobHistory());
                ps.setInt(5 , userResume.getUserSalDesire());
                ps.setString(6 , userResume.getUserDesire());
                return ps;
            }
        } , keyHolder);
        userResume.setId(keyHolder.getKey().intValue());
        System.out.println("操作记录数：" + resRow + "主键:" + keyHolder.getKey());
        System.out.println(userResume);
        return new Json(messageCode , userResume);
    }

    @Override
    public String deleteUserResume(int id) {
        String sql = "delete from t_user_resume where id = ?";
        int res = jdbcTemplate.update(sql , id);
        return res == 1 ? "删除成功" : "未找到id ， 删除失败";
    }

    @Override
    public Json updateUserResume(UserResume userResume,String token) {
        if(!tools.isHasToken(token)){
            return new Json(false);
        }
        String sql = "update t_user_resume set userPro = ? , certificate = ? , " +
                "jobhistory = ? , userSalDesire = ? , userDesire = ? , where id = ? and userID = ?";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1 , userResume.getUserPro());
                preparedStatement.setString(2 , userResume.getCertificate());
                preparedStatement.setString(3 , userResume.getJobHistory());
                preparedStatement.setInt(4 , userResume.getUserSalDesire());
                preparedStatement.setString(5 , userResume.getUserDesire());
                preparedStatement.setInt(6 , userResume.getId());
                preparedStatement.setInt(7 , tools.getUserInfoByToken(token).get(0).getId());
            }
        });
        return new Json(messageCode , userResume);
    }

    @Override
    public int isHasUserResume(int id) {
        String sql = "select * from t_user_resume where id = ?";
        List<UserResume> list = jdbcTemplate.query(sql , new Object[] {id} , new UserResumeRowMapper());
        if(list.size() > 0){
            return 1;
        }
        return 0;
    }
}
