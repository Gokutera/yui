package com.yui.model;

import com.yui.util.GsonUtil;

import java.util.HashMap;

/**
 * Created by amosli on 16/3/31.
 */
public class ResultInfo {
    public final String MSG = "msg";
    public final String SUCCESS = "success";
    private HashMap<String, Object> resultInfoMap = new HashMap<>();
    private boolean isSuccess;
    private String msg;

    public ResultInfo() {
        setMsgAndSuccess();
    }

    public ResultInfo(Boolean isSuccess, String msg) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        setMsgAndSuccess();
    }

    private void setMsgAndSuccess() {
        resultInfoMap.put(SUCCESS, isSuccess);
        resultInfoMap.put(MSG, msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        resultInfoMap.put(MSG, msg);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
        resultInfoMap.put(SUCCESS, isSuccess);
    }

    /**
     * Associates the specified value with the specified key in this resultInfoMap.
     * If the resultInfoMap previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the resultInfoMap
     * previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    public void add(String key, Object value) {
        resultInfoMap.put(key, value);
    }

    public String getJsonResult() {
        return GsonUtil.mapToJson(resultInfoMap);
    }
}
