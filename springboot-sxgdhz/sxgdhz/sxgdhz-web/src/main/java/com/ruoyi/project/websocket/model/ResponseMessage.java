package com.ruoyi.project.websocket.model;

public class ResponseMessage {
    private String responseMessage;

    public ResponseMessage() {
    }

    public ResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
