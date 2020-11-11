package com.txx.springboot.service;

import com.txx.springboot.entity.WorkNeedCount;
import com.txx.springboot.json.Json;

/**
 * @author 淘米水浇花
 */
public interface WorkNeedCountUserService {
    /**
     * 获取全部工作需要人数
     * @return
     */
    Json getAllWorkNeedCount();

    /**
     * 根据ID获取工作需要人数
     * @param id
     * @return
     */
    Json getWorkNeedCountById(int id);

    /**
     * 增加工作需要人数
     * @param workNeedCount
     * @return
     */
    Json registerWorkNeedCount(WorkNeedCount workNeedCount);

    /**
     * 根据ID删除工作需要人数
     * @param id
     * @return
     */
    String deleteWorkNeedCount(int id);

    /**
     * 修改工作需要人数
     * @param workNeedCount
     * @return
     */
    Json updateWorkNeedCount(WorkNeedCount workNeedCount);

    /**
     * 根据ID判断是否存在该工作需要人数
     * @param id
     * @return
     */
    int isHasWorkNeedCount(int id);
}
