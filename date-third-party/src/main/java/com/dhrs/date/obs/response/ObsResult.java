package com.dhrs.date.obs.response;

import com.alibaba.fastjson.JSONObject;

public class ObsResult {

    private String msg;
    private JSONObject imgInfo;

    public ObsResult(String msg, JSONObject imgInfo) {
        this.msg = msg;
        this.imgInfo = imgInfo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getImgInfo() {
        return imgInfo;
    }

    public void setImgInfo(JSONObject imgInfo) {
        this.imgInfo = imgInfo;
    }
}
