package com.txx.springboot.web;

import com.txx.springboot.entity.UserResume;
import com.txx.springboot.json.Json;
import com.txx.springboot.service.UserResumeUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 淘米水浇花
 */
@RestController
@RequestMapping(value = "/user")
public class UserResumeController {
    private final UserResumeUserService service;

    public UserResumeController(UserResumeUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getAllUserResume" , method = RequestMethod.GET)
    public Json getAllUserResume(){
        return service.getAllUserResume();
    }
    @RequestMapping(value = "/getUserResumeById" , method = RequestMethod.GET)
    public Json getUserResumeById(int id , UserResume userResume){
        return service.getUserResumeById(id , userResume);
    }
    @RequestMapping(value = "/addUserResume" , method = RequestMethod.POST)
    public Json addUserResume(UserResume userResume, HttpServletRequest httpServletRequest){
        return service.addUserResume(userResume,httpServletRequest.getHeader("token"));
    }
    @RequestMapping(value = "/deleteUserResume" , method = RequestMethod.POST)
    public String deleteUserResume(int id){
        return service.deleteUserResume(id);
    }
    @RequestMapping(value = "/updateUserResume" , method = RequestMethod.POST)
    public Json updateUserResume(UserResume userResume,HttpServletRequest httpServletRequest){
        int isHas = service.isHasUserResume(userResume.getId());
        if(isHas == 1){
            return service.updateUserResume(userResume,httpServletRequest.getHeader("token"));
        }
        return null;
    }
}
