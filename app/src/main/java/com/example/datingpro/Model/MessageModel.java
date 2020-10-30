package com.example.datingpro.Model;

public class MessageModel {
   public String id,chatId,name,message,time,sendFrom;
   public String image;

    public MessageModel() {
    }

    public MessageModel(String id, String chatId, String name, String message, String time, String sendFrom, String image) {
        this.id = id;
        this.chatId = chatId;
        this.name = name;
        this.message = message;
        this.time = time;
        this.sendFrom = sendFrom;
        this.image = image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getChatId() {
        return chatId;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public String getImage() {
        return image;
    }
}
