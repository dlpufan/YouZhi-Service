package com.txx.springboot.service;

import com.txx.springboot.entity.WorkInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author 淘米水浇花
 */
public class WorkInfoRowMapper implements RowMapper<WorkInfo> {
    @Override
    public WorkInfo mapRow(ResultSet resultSet, int i) throws SQLException {
        WorkInfo workInfo = new WorkInfo();
        workInfo.setId(resultSet.getInt("id"));
        workInfo.setTitle(resultSet.getString("title"));
        workInfo.setSalary(resultSet.getInt("salary"));
        workInfo.setWorkType(resultSet.getInt("workType"));
        workInfo.setTags(resultSet.getString("tags"));
        workInfo.setPosition(resultSet.getString("position"));
        workInfo.setWorkDescription(resultSet.getString("workDescription"));
        workInfo.setWorkNeedCount(resultSet.getInt("workNeedCount"));
        workInfo.setSendTime(resultSet.getDate("sendTime"));
        workInfo.setUserID(resultSet.getInt("userID"));
        workInfo.setWorkTime(resultSet.getString("workTime"));
        workInfo.setPayment(resultSet.getInt("payment"));
        workInfo.setUserTel(resultSet.getLong("userTel"));
        workInfo.setPageViews(resultSet.getInt("pageViews"));
        return workInfo;
    }
}
