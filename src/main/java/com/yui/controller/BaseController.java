package com.yui.controller;

import com.yui.util.DataUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Administrator on 2016/11/9.
 */
@Controller
@RequestMapping("/")
public class BaseController {

//    @RequestMapping(value="/welcome", method= RequestMethod.GET)
//    public String welcome(){
//        return "index";
//    }
//
//    @RequestMapping(value="/gohome", method=RequestMethod.GET)
//    public String home(){
//        return "welcome";
//    }
//
//    @RequestMapping(value="/chat", method=RequestMethod.GET)
//    public String chat(){
//        return "chat";
//    }

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
        String sql = "select realname from user where id="+id;
        DataUtil dataUtil = new DataUtil();
        Connection connection = dataUtil.getConn();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        String name = null;
        while(rs.next()){
            name = rs.getString("realname");
        }
        return name;
    }
}
