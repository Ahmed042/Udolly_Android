package com.example.datingpro.Model;

public class chatModel {
    String id,userSendId,message;

    public chatModel() {
    }

    public chatModel(String id, String userSendId, String message) {
        this.id = id;
        this.userSendId = userSendId;
        this.message = message;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserSendId(String userSendId) {
        this.userSendId = userSendId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getUserSendId() {
        return userSendId;
    }

    public String getMessage() {
        return message;
    }
}
