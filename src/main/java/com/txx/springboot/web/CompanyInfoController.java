package com.txx.springboot.web;

import com.txx.springboot.entity.CompanyInfo;
import com.txx.springboot.json.Json;
import com.txx.springboot.service.CompanyInfoUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 淘米水浇花
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyInfoController {
    private final CompanyInfoUserService service;

    public CompanyInfoController(CompanyInfoUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getAllCompanyInfo" , method = RequestMethod.GET)
    public Json getAllCompanyInfo(){
        return service.getAllCompanyInfo();
    }
    @RequestMapping(value = "/getCompanyInfoById" , method = RequestMethod.GET)
    public Json getCompanyInfoById(int id){
        return service.getCompanyInfoById(id);
    }
    @RequestMapping(value = "/registerCompanyInfo" , method = RequestMethod.POST)
    public Json registerCompanyInfo(CompanyInfo companyInfo){
        return service.registerCompanyInfo(companyInfo);
    }
    @RequestMapping(value = "/deleteCompanyInfo" , method = RequestMethod.POST)
    public String deleteCompanyInfo(int id){
        return service.deleteCompanyInfo(id);
    }
    @RequestMapping(value = "/updateCompanyInfo" , method = RequestMethod.POST)
    public Json updateCompanyInfoController(CompanyInfo companyInfo){
        System.out.println(companyInfo.getId());
        int isHas = service.isHasCompanyInfo(companyInfo.getId());
        if(isHas == 1){
            return service.updateCompanyInfo(companyInfo);
        }
        return null;
    }
}
