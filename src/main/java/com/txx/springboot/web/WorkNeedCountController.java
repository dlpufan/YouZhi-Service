package com.txx.springboot.web;

import com.txx.springboot.entity.WorkNeedCount;
import com.txx.springboot.json.Json;
import com.txx.springboot.service.WorkNeedCountUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 淘米水浇花
 */
@RestController
@RequestMapping(value = "/workNeedCount")
public class WorkNeedCountController {
    private final WorkNeedCountUserService service;

    public WorkNeedCountController(WorkNeedCountUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getAllWorkNeedCount" , method = RequestMethod.GET)
    public Json getAllWorkNeedCount(){
        return service.getAllWorkNeedCount();
    }
    @RequestMapping(value = "/getWorkNeedCountById" , method = RequestMethod.GET)
    public Json getWorkNeedCountById(int id){
        return service.getWorkNeedCountById(id);
    }
    @RequestMapping(value = "/registerWorkNeedCount" , method = RequestMethod.POST)
    public Json registerWorkNeedCount(WorkNeedCount workNeedCount){
        return service.registerWorkNeedCount(workNeedCount);
    }
    @RequestMapping(value = "/deleteWorkNeedCount" , method = RequestMethod.POST)
    public String deleteWorkNeedCount(int id){
        return service.deleteWorkNeedCount(id);
    }
    @RequestMapping(value = "/updateWorkNeedCount" , method = RequestMethod.POST)
    public Json updateWorkNeedCountController(WorkNeedCount workNeedCount){
        System.out.println(workNeedCount.getWorkNeedPeople());
        int isHas = service.isHasWorkNeedCount(workNeedCount.getWorkNeedPeople());
        if(isHas == 1){
            return service.updateWorkNeedCount(workNeedCount);
        }
        return null;
    }
}
