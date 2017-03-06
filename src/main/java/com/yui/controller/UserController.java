package com.yui.controller;

import com.yui.util.DataUtil;
import com.yui.util.GsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value="/insert", produces = "application/json; charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String insert(int id, String name, String passwd, String realname) throws SQLException {
        String result = null;
        try{
            String sql = "insert into user (id, name, passwd, realname) values (" + id + ",'" + name + "','" + passwd + "','" + realname + "')";
            DataUtil dataUtil = new DataUtil();
            Connection connection = dataUtil.getConn();
            Statement statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);

            if(rs == 1){
                result = "插入成功";
            }else{
                result = "插入失败";
            }
        }catch(Exception e){
            result = "插入异常";
        }
        return result;
    }

    @RequestMapping(value="/register", produces = "application/json; charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String register(String adminUserId, String adminName, String adminPasswd) throws SQLException {
        String result = null;
        try{
            String sql = "insert into admin (admin_user_id, admin_name, admin_passwd) values (" +"'" + adminUserId +"','"+ adminName + "','" + adminPasswd + "')";
            DataUtil dataUtil = new DataUtil();
            Connection connection = dataUtil.getConn();
            Statement statement = connection.createStatement();
            int rs = statement.executeUpdate(sql);

            if(rs == 1){
                result = "插入成功";
            }else{
                result = "插入失败";
            }
        }catch(Exception e){
            result = "插入异常";
        }
        return result;
    }

    @RequestMapping(value="/login", produces = "application/json; charset=utf-8", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String login(String username, String password) throws SQLException {
        String result = null;
        try{
            String sql = "select * from admin";
            String id = null;
            String pwd = null;
            String name = null;
            DataUtil dataUtil = new DataUtil();
            Connection connection = dataUtil.getConn();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                id = rs.getString("admin_user_id");
                pwd = rs.getString("admin_passwd");
                name = rs.getString("admin_name");
            }
            if(username.equals(id) && password.equals(pwd)){
                result="登录成功，欢迎回来" + name + " SAMA";
            }else{
                result="登录失败";
            }
        }catch(Exception e){
            result = "登录异常";
        }
        Map resultMap = new HashMap();
        resultMap.put("result", result);
        resultMap.put("key", 1);
        result = GsonUtil.mapToJson(resultMap);
        return result;
    }
}
