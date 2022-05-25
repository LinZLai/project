package com.system.common.lang;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Result implements Serializable {

    //    200正常
    private int code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(int code, String msg, Object data) {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static Result fail(String msg) {
        return fail(400, msg);
    }

    public static Result fail(int code, String msg) {
        Result res = new Result();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    public static Result success(String meg, Map<Object, Object> id) {
        return success(200, meg, id);
    }
}
