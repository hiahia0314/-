package com.example.demo.response;

public class ResponseForFriendships {
    private String isSuccess;
    private String msg;

    public static ResponseForFriendships newSuccess(){
        ResponseForFriendships response = new ResponseForFriendships();
        response.setIsSuccess("success");
        response.setMsg("successfully");
        return response;
    }

    public static ResponseForFriendships newFailure(String msg){
        ResponseForFriendships response = new ResponseForFriendships();
        response.setIsSuccess("fail");
        response.setMsg(msg);
        return response;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
