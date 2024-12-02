package com.example.demo.response;

public class ResponseForAuth {
    private String isSuccess;
    private String msg;

    public static ResponseForAuth newSuccess(){
        ResponseForAuth responseForAuth = new ResponseForAuth();
        responseForAuth.setIsSuccess("success");
        responseForAuth.setMsg("successfully");
        return responseForAuth;
    }

    public static ResponseForAuth newFailure(String msg){
        ResponseForAuth responseForAuth = new ResponseForAuth();
        responseForAuth.setIsSuccess("fail");
        responseForAuth.setMsg(msg);
        return responseForAuth;
    }

    public void setIsSuccess(String success) {
        this.isSuccess = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
