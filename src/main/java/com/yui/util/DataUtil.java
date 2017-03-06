package com.yui.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2016/11/16.
 */
public class DataUtil {
    public static Connection getConn(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://192.168.100.231/yoi","oldboy","123456");
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
