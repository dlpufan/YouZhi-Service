package com.txx.springboot.service;

import com.txx.springboot.entity.CompanyInfo;
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
public class CompanyInfoUserServiceImpl implements CompanyInfoUserService {
    private final JdbcTemplate jdbcTemplate;
    private final Tools tools;
    public CompanyInfoUserServiceImpl(JdbcTemplate jdbcTemplate,Tools tools) {
        this.jdbcTemplate = jdbcTemplate;
        this.tools = tools;
    }
    @Override
    public Json getAllCompanyInfo() {
        String sql = "select * from t_company_info";
        MessageCode messageCode = new MessageCode();
        List<Map<String , Object>> list = jdbcTemplate.queryForList(sql);
        return new Json(messageCode , list);
    }

    @Override
    public Json getCompanyInfoById(int id) {
        CompanyInfo userInfo;
        String sql = "select * from t_company_info where id = ?";
        MessageCode messageCode = new MessageCode();
        List<CompanyInfo> list = jdbcTemplate.query(sql , new Object[] {id} , new CompanyInfoRowMapper());
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
    public Json registerCompanyInfo(CompanyInfo companyInfo) {
        String sql = "insert into t_company_info(id , userID , companyDescription , tags , name)" +
                " values(null , ? , ? , ? , ?)";
        MessageCode messageCode = new MessageCode();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql , new String[] {"id"});
                ps.setInt(1 , companyInfo.getUserID());
                ps.setString(2 , companyInfo.getCompanyDescription());
                ps.setString(3 , companyInfo.getTags());
                ps.setString(4 , companyInfo.getName());
                return ps;
            }
        } , keyHolder);
        companyInfo.setId(keyHolder.getKey().intValue());
        return new Json(messageCode , companyInfo);
    }

    @Override
    public String deleteCompanyInfo(int id) {
        String sql = "delete from t_company_info where id = ?";
        int res = jdbcTemplate.update(sql , id);
        return res == 1 ? "删除成功" : "未找到id ， 删除失败";
    }

    @Override
    public Json updateCompanyInfo(CompanyInfo companyInfo) {
        String sql = "update t_company_info set userID = ? , " +
                "companyDescription = ? , tags = ? , name = ? where id = ?";
        MessageCode messageCode = new MessageCode();
        jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1 , companyInfo.getUserID());
                preparedStatement.setString(2 , companyInfo.getCompanyDescription());
                preparedStatement.setString(3 , companyInfo.getTags());
                preparedStatement.setString(4 , companyInfo.getName());
                preparedStatement.setInt(5 , companyInfo.getId());
            }
        });
        return new Json(messageCode , companyInfo);
    }

    @Override
    public int isHasCompanyInfo(int id) {
        String sql = "select * from t_company_info where id = ?";
        List<CompanyInfo> list = jdbcTemplate.query(sql , new Object[] {id} , new CompanyInfoRowMapper());
        if(list.size() > 0){
            return 1;
        }
        return 0;
    }
}
