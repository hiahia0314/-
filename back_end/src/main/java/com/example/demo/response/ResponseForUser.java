package com.example.demo.response;

public class ResponseForUser <T> {
    private String isSuccess;
    private String msg;
    private T data;

    public static ResponseForUser newFailure(String msg){
        ResponseForUser responseForUser = new ResponseForUser();
        responseForUser.setIsSuccess("fail");
        responseForUser.setMsg(msg);
        return responseForUser;
    }

    public static <T> ResponseForUser newSuccess(T data){
        ResponseForUser responseForUser = new ResponseForUser();
        responseForUser.setIsSuccess("success");
        responseForUser.setMsg("successfully");
        responseForUser.setData(data);
        return responseForUser;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
