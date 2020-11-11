package com.txx.springboot.web;

import com.txx.springboot.entity.UserLogin;
import com.txx.springboot.json.Json;
import com.txx.springboot.json.MessageCode;
import com.txx.springboot.service.UserLoginUserService;
import com.txx.springboot.tool.Tools;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author 淘米水浇花
 */
@RestController
@RequestMapping(value = "/user")
public class UserLoginController {
    private final UserLoginUserService service;

    public UserLoginController(UserLoginUserService service) {
        this.service = service;
    }
    @RequestMapping(value = "/getUserLoginById" , method = RequestMethod.GET)
    public Json getUserLoginById(int id){
        return service.getUserLoginById(id);
    }
    @RequestMapping(value = "/registerUserLogin" , method = RequestMethod.POST)
    public Json registerUserLogin(UserLogin userLogin){
        return service.userLoginRegister(userLogin);
    }
    @RequestMapping(value = "/deleteUserLogin" , method = RequestMethod.POST)
    public String deleteUserLogin(int id){
        return service.deleteUserLogin(id);
    }
    @RequestMapping(value = "/updateUserLogin" , method = RequestMethod.POST)
    public Json updateUserLogin(UserLogin userLogin,HttpServletRequest httpServletRequest){
        int isHas = service.isHasUserLogin(userLogin.getId());
        if(isHas == 1){
            return service.updateUserLogin(userLogin,httpServletRequest.getHeader("token"));
        }
        return null;
    }
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public Json login(UserLogin userLogin){
        return service.userLogin(userLogin);
    }
    @RequestMapping(value = "/isCompany" , method = RequestMethod.GET)
    public Json isCompany(HttpServletRequest httpServletRequest){
        return service.isCompany(httpServletRequest.getHeader("token"));
    }
    @RequestMapping(value = "/loginByToken" , method = RequestMethod.GET)
    public Json loginByToken(HttpServletRequest httpServletRequest){
        return service.loginByToken(httpServletRequest.getHeader("token"));
    }
}
