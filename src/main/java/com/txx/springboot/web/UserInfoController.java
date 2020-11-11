package com.txx.springboot.web;

import com.txx.springboot.entity.UserInfo;
import com.txx.springboot.json.Json;
import com.txx.springboot.service.UserInfoUserService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 淘米水浇花
 */
@RestController
@RequestMapping(value = "/user")
public class UserInfoController {
    private final UserInfoUserService service;

    public UserInfoController(UserInfoUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getAllUser" , method = RequestMethod.GET)
    public Json getAllUserInfo(){
        return service.getAllUserInfo();
    }
    @RequestMapping(value = "/getUserById" , method = RequestMethod.GET)
    public Json getUserInfoById(int id){
        return service.getUserInfoById(id);
    }
    @RequestMapping(value = "/registerUser" , method = RequestMethod.POST)
    public Json registerUserInfo(UserInfo userInfo){
        return service.registerUserInfo(userInfo);
    }
    @RequestMapping(value = "/deleteUser" , method = RequestMethod.POST)
    public String deleteUserInfo(int id){
        return service.deleteUserInfo(id);
    }
    @RequestMapping(value = "/updateUser" , method = RequestMethod.POST)
    public Json updateUserInfoController(UserInfo userInfo, HttpServletRequest httpServletRequest){
        System.out.println(userInfo.getId());
        int isHas = service.isHasUser(userInfo.getId());
        if(isHas == 1){
            return service.updateUserInfo(userInfo,httpServletRequest.getHeader("token"));
        }
        return null;
    }
}
