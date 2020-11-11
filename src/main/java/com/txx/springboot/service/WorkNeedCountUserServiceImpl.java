package com.txx.springboot.service;

import com.txx.springboot.entity.WorkNeedCount;
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
public class WorkNeedCountUserServiceImpl implements WorkNeedCountUserService {
    private final JdbcTemplate jdbcTemplate;
    private final Tools tools;
    public WorkNeedCountUserServiceImpl(JdbcTemplate jdbcTemplate,Tools tools) {
        this.jdbcTemplate = jdbcTemplate;
        this.tools = tools;
    }
    @Override
    public Json getAllWorkNeedCount() {
        String sql = "select * from t_work_workNeedCount";
        MessageCode messageCode = new MessageCode();
        List<Map<String , Object>> list = jdbcTemplate.queryForList(sql);
        return new Json(messageCode , list);
    }

    @Override
    public Json getWorkNeedCountById(int id) {
        WorkNeedCount workNeedCount;
        String sql = "select * from t_work_workNeedCount where id = ?";
        MessageCode messageCode = new MessageCode();
        List<WorkNeedCount> list = jdbcTemplate.query(sql , new Object[] {id} , new WorkNeedCountRowMapper());
        if(!list.isEmpty()){
            workNeedCount = list.get(0);
        }
        else {
            workNeedCount = null;
            messageCode.setMessage("未查询到该用户id ， 返回失败");
            messageCode.setCode(0);
        }
        return new Json(messageCode , workNeedCount);
    }

    @Override
    public Json registerWorkNeedCount(WorkNeedCount workNeedCount) {
        String sql = "insert into t_work_workNeedCount(id) values(?)";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql , new String[] {"id"});
                ps.setInt(1 , workNeedCount.getWorkNeedPeople());
                return ps;
            }
        });
        return new Json(messageCode , workNeedCount);
    }

    @Override
    public String deleteWorkNeedCount(int id) {
        String sql = "delete from t_work_workNeedCount where id = ?";
        int res = jdbcTemplate.update(sql , id);
        return res == 1 ? "删除成功" : "未找到id ， 删除失败";
    }

    @Override
    public Json updateWorkNeedCount(WorkNeedCount workNeedCount) {
        String sql = "update t_work_workNeedCount set workNeedPeople = ?";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1 , workNeedCount.getWorkNeedPeople());
            }
        });
        return new Json(messageCode , workNeedCount);
    }

    @Override
    public int isHasWorkNeedCount(int id) {
        String sql = "select * from t_company_info where id = ?";
        List<WorkNeedCount> list = jdbcTemplate.query(sql , new Object[] {id} , new WorkNeedCountRowMapper());
        if(list.size() > 0){
            return 1;
        }
        return 0;
    }
}
