package com.txx.springboot.service;

import com.txx.springboot.entity.WorkInfo;
import com.txx.springboot.json.Json;

/**
 * @author 淘米水浇花
 */
public interface WorkInfoUserService {
    /**
     * 获取全部工作信息
     * @return
     */
    Json getAllWorkInfo(int pageNum,int pageSum);

    /**
     * 根据ID获取工作信息
     * @param id
     * @return
     */
    Json getWorkInfoById(int id);

    /**
     * 增加工作信息
     * @param workInfo
     * @return
     */
    Json registerWorkInfo(WorkInfo workInfo,String token);

    /**
     * 根据ID删除工作信息
     * @param id
     * @return
     */
    String deleteWorkInfo(int id);

    /**
     * 修改工作信息
     * @param workInfo
     * @return
     */
    Json updateWorkInfo(WorkInfo workInfo,String token);

    /**
     * 根据ID判断是否存在该工作信息
     * @param id
     * @return
     */
    int isHasWorkInfo(int id);

    Json searchWork(String value);
}
