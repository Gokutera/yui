package com.yoi.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * 封装的GSON解析工具类  提供泛型参数
 * Created by echo on 2016/11/24.
 */
public class GsonUtil {
    private GsonUtil(){
        throw new IllegalAccessError("GsonUtil class");
    }

    /**
     * 将Json数据解析成相应映射对象
     * @param jsonData
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String jsonData, Class<T> type){
        Gson gson = new Gson();
        T result = gson.fromJson(jsonData, type);
        return result;
    }

    /**
     * 将Json数组解析成相应映射对象列表
     * @param jsonData
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> parseJsonArray(String jsonData, Class<T> type){
        Gson gson = new Gson();
        List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>(){}.getType());
        return result;
    }

    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }
}
