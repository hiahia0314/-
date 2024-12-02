package com.example.demo.response;

public class ResponseForAuth {
    private String isSuccess;
    private int status;
    private String msg;

    public static ResponseForAuth newSuccess(){
        ResponseForAuth responseForAuth = new ResponseForAuth();
        responseForAuth.setIsSuccess("success");
        responseForAuth.setMsg("successfully");
        responseForAuth.setStatus(200);
        return responseForAuth;
    }

    public static ResponseForAuth newFailure(String msg, int status){
        ResponseForAuth responseForAuth = new ResponseForAuth();
        responseForAuth.setIsSuccess("fail");
        responseForAuth.setMsg(msg);
        responseForAuth.setStatus(status);
        return responseForAuth;
    }

    public void setIsSuccess(String success) {
        this.isSuccess = success;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
