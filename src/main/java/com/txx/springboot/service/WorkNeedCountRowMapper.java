package com.txx.springboot.service;

import com.txx.springboot.entity.WorkNeedCount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 淘米水浇花
 */
public class WorkNeedCountRowMapper implements RowMapper<WorkNeedCount> {
    @Override
    public WorkNeedCount mapRow(ResultSet resultSet, int i) throws SQLException {
        WorkNeedCount workNeedCount = new WorkNeedCount();
        workNeedCount.setWorkNeedPeople(resultSet.getInt("workNeedPeople"));
        return workNeedCount;
    }
}
