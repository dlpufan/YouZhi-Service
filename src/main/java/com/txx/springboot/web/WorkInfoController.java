package com.txx.springboot.web;

import com.txx.springboot.entity.WorkInfo;
import com.txx.springboot.json.Json;
import com.txx.springboot.service.WorkInfoUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 淘米水浇花
 */
@RestController
@RequestMapping(value = "/work")
public class WorkInfoController {
    private final WorkInfoUserService service;

    public WorkInfoController(WorkInfoUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getAllWorkInfo" , method = RequestMethod.GET)
    public Json getAllWorkInfo(int pageNum , int pageSum){
        return service.getAllWorkInfo(pageNum , pageSum);
    }
    @RequestMapping(value = "/getWorkInfoById" , method = RequestMethod.GET)
    public Json getWorkInfoById(int id){
        return service.getWorkInfoById(id);
    }
    @RequestMapping(value = "/registerWorkInfo" , method = RequestMethod.POST)
    public Json registerWorkInfo(WorkInfo workInfo, HttpServletRequest httpServletRequest){
        return service.registerWorkInfo(workInfo,httpServletRequest.getHeader("token"));
    }
    @RequestMapping(value = "/deleteWorkInfo" , method = RequestMethod.POST)
    public String deleteWorkInfo(int id){
        return service.deleteWorkInfo(id);
    }
    @RequestMapping(value = "/searchWorkInfo" , method = RequestMethod.POST)
    public Json searchWork(String value){
        return service.searchWork(value);
    }
    @RequestMapping(value = "/updateWorkInfo" , method = RequestMethod.POST)
    public Json updateWorkInfoController(WorkInfo workInfo, HttpServletRequest httpServletRequest){
        System.out.println(workInfo.getId());
        int isHas = service.isHasWorkInfo(workInfo.getId());
        if(isHas == 1){
            return service.updateWorkInfo(workInfo,httpServletRequest.getHeader("token"));
        }
        return null;
    }
}
