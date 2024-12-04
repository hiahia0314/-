package com.example.demo.RES;

public class Response2 {
    private String msg;
    private String isSuccess;

    public static Response2 newSuccess(String Msg) {
        Response2 res = new Response2();
        res.setMsg(Msg);
        res.setIsSuccess("success");
        return res;
    }
    public static Response2 newFailure(String Msg) {
        Response2 res = new Response2();
        res.setMsg(Msg);
        res.setIsSuccess("failure");
        return res;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }
}
