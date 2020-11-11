package com.txx.springboot.service;

import com.txx.springboot.entity.WorkInfo;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author 淘米水浇花
 */
@Service
public class WorkInfoUserServiceImpl implements WorkInfoUserService {
    private final JdbcTemplate jdbcTemplate;
    private final Tools tools;
    public WorkInfoUserServiceImpl(JdbcTemplate jdbcTemplate,Tools tools) {
        this.jdbcTemplate = jdbcTemplate;
        this.tools = tools;
    }
    @Override
    public Json getAllWorkInfo(int pageNum,int pageSum) {
        int count = Integer.parseInt(jdbcTemplate.queryForObject("select count(id) from t_work_info",String.class));
        List<Map<String , Object>> list;
        if(pageNum<=0||pageSum<=0){
            return new Json(new MessageCode("页码错误",400));
        }
        if(pageSum>=count){
            list = jdbcTemplate.queryForList("select * from t_work_info");
        }
        else if(pageNum*pageSum>count){
            list = jdbcTemplate.queryForList("select * from t_work_info limit "+(int)(count-Math.floor(count/pageSum)*pageSum));
        }
        else {
            list = jdbcTemplate.queryForList("select * from t_work_info limit " + (count - pageSum * pageNum) + "," + pageSum);
        }
        Collections.reverse(list);
        for(int i = 0 ; i < list.size(); i ++){
            String name = jdbcTemplate.queryForObject("select userName from t_user_info where id = "+list.get(i).get("userID"),String.class);
            list.get(i).put("userName",name);
        }
        return new Json(list);

    }

    @Override
    public Json getWorkInfoById(int id) {
        WorkInfo workInfo;
        String sql = "select * from t_work_info where id = ?";
        MessageCode messageCode = new MessageCode();
        List<WorkInfo> list = jdbcTemplate.query(sql , new Object[] {id} , new WorkInfoRowMapper());
        if(!list.isEmpty()){
            workInfo = list.get(0);
            String updateSql = "update t_work_info set pageViews = pageViews+1 where id = ?";
            jdbcTemplate.update(updateSql, new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement preparedStatement) throws SQLException {
                    preparedStatement.setInt(1 , id);
                }
            });
        }
        else {
            workInfo = null;
            messageCode.setMessage("未查询到该id ， 返回失败");
            messageCode.setCode(0);
        }
        return new Json(messageCode , workInfo);
    }

    @Override
    public Json registerWorkInfo(WorkInfo workInfo,String token) {
        if(!tools.isHasToken(token)||!tools.isCompany(token)){
            return new Json(false);
        }
        String sql = "insert into t_work_info(id , title , salary , workType , tags , " +
                "position , workDescription , workNeedCount , sendTime , userID , " +
                "workTime , payment , userTel, pageViews)" +
                " values(null , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , 0)";
        MessageCode messageCode = new MessageCode();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql , new String[] {"id"});
                ps.setString(1 , workInfo.getTitle());
                ps.setInt(2 , workInfo.getSalary());
                ps.setInt(3 , workInfo.getWorkType());
                ps.setString(4 , workInfo.getTags());
                ps.setString(5 , workInfo.getPosition());
                ps.setString(6 , workInfo.getWorkDescription());
                ps.setInt(7 , workInfo.getWorkNeedCount());
                ps.setDate(8 , workInfo.getSendTime());
                ps.setInt(9 , workInfo.getUserID());
                ps.setString(10 , workInfo.getWorkTime());
                ps.setInt(11 , workInfo.getPayment());
                ps.setLong(12 , workInfo.getUserTel());
                return ps;
            }
        } , keyHolder);
        workInfo.setId(keyHolder.getKey().intValue());
        return new Json(messageCode , workInfo);
    }

    @Override
    public String deleteWorkInfo(int id) {
        String sql = "delete from t_work_info where id = ?";
        int res = jdbcTemplate.update(sql , id);
        return res == 1 ? "删除成功" : "未找到id ， 删除失败";
    }

    @Override
    public Json updateWorkInfo(WorkInfo workInfo,String token) {
        if(!tools.isHasToken(token)||!tools.isCompany(token)){
            return new Json(false);
        }
        String sql = "update t_work_info set title = ? , " +
                "salary = ? , workType = ? , tags = ? , position = ? , " +
                "workDescription = ? , workNeedCount = ? , sendTime = ? , userID = ? , " +
                "workTime= ? , payment = ? , userTel = ? where id = ?";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1 , workInfo.getTitle());
                preparedStatement.setInt(2 , workInfo.getSalary());
                preparedStatement.setInt(3 , workInfo.getWorkType());
                preparedStatement.setString(4 , workInfo.getTags());
                preparedStatement.setString(5 , workInfo.getPosition());
                preparedStatement.setString(6 , workInfo.getWorkDescription());
                preparedStatement.setInt(7 , workInfo.getWorkNeedCount());
                preparedStatement.setDate(8 , workInfo.getSendTime());
                preparedStatement.setInt(9 , workInfo.getUserID());
                preparedStatement.setString(10 , workInfo.getWorkTime());
                preparedStatement.setInt(11 , workInfo.getPayment());
                preparedStatement.setLong(12 , workInfo.getUserTel());
                preparedStatement.setInt(13 , workInfo.getId());
            }
        });
        return new Json(messageCode , workInfo);
    }

    @Override
    public int isHasWorkInfo(int id) {
        String sql = "select * from t_work_info where id = ?";
        List<WorkInfo> list = jdbcTemplate.query(sql , new Object[] {id} , new WorkInfoRowMapper());
        if(list.size() > 0){
            return 1;
        }
        return 0;
    }

    @Override
    public Json searchWork(String value) {
        String[] arrs = {"<",">","p","strong","ul","li","span","style"};
        for(int i = 0; i < arrs.length ; i ++){
            if(value.equals(arrs[i])) {
                return new Json(new MessageCode("error",400),"包含非法字符");
            }
        }
        String sql = "select * from t_work_info where title Like ? or workDescription Like ? or tags Like ?";
        List<WorkInfo> messages = jdbcTemplate.query(sql ,new Object[] {'%'+value+'%','%'+value+'%','%'+value+'%'} , new WorkInfoRowMapper());
        if(messages.isEmpty()){
            return new Json(new MessageCode("error",401),"没有该文章");
        }
        return new Json(messages);
    }

}
