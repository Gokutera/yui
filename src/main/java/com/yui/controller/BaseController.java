package com.yui.controller;

import com.yui.dao.UserMapper;
import com.yui.model.ResultInfo;
import com.yui.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
@RequestMapping("/")
public class BaseController {
    @Resource
    private UserMapper userMapper;

    @RequestMapping("/home")
    public String ad() {
        return "redirect:/home/";
    }

    @RequestMapping(value="/home/", produces = "application/json; charset=utf-8", method={RequestMethod.POST, RequestMethod.GET})
    public String home(){
        return "home.html";
    }

    @RequestMapping(value="/getName", produces = "application/json; charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String getName(int id) throws SQLException{
        ResultInfo resultInfo = new ResultInfo();
        try{
            User user = userMapper.selectByPrimaryKey(id);
            String name = user.getRealname();
            resultInfo.setSuccess(true);
            resultInfo.add("name", name);
        }catch(Exception e){
            resultInfo.setMsg("系统异常!");
            resultInfo.setSuccess(false);
        }
        return resultInfo.getJsonResult();
    }
}
