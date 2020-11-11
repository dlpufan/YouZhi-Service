package com.txx.springboot.service;

import com.txx.springboot.entity.CompanyInfo;
import com.txx.springboot.json.Json;

/**
 * @author 淘米水浇花
 */
public interface CompanyInfoUserService {
    /**
     * 获取全部企业信息
     * @return
     */
    Json getAllCompanyInfo();

    /**
     * 根据ID获取企业信息
     * @param id
     * @return
     */
    Json getCompanyInfoById(int id);

    /**
     * 增加企业信息
     * @param companyInfo
     * @return
     */
    Json registerCompanyInfo(CompanyInfo companyInfo);

    /**
     * 根据ID删除企业信息
     * @param id
     * @return
     */
    String deleteCompanyInfo(int id);

    /**
     * 修改企业信息
     * @param companyInfo
     * @return
     */
    Json updateCompanyInfo(CompanyInfo companyInfo);

    /**
     * 根据ID判断是否存在该企业信息
     * @param id
     * @return
     */
    int isHasCompanyInfo(int id);
}
