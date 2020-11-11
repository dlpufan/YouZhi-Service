package com.txx.springboot.service;

import com.txx.springboot.entity.CompanyInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 淘米水浇花
 */
public class CompanyInfoRowMapper implements RowMapper<CompanyInfo> {
    @Override
    public CompanyInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setId(resultSet.getInt("id"));
        companyInfo.setUserID(resultSet.getInt("userID"));
        companyInfo.setCompanyDescription(resultSet.getString("companyDescription"));
        companyInfo.setTags(resultSet.getString("tags"));
        companyInfo.setName(resultSet.getString("name"));
        return companyInfo;
    }
}
