package com.example.lingol;

public class Message {
    private String messageId;
    private String messageText;
    private String senderId;
    private String timestamp;

    // Construtor vazio necessário para Firebase
    public Message() {
    }

    // Construtor personalizado
    public Message(String messageId, String messageText, String senderId, String timestamp) {
        this.messageId = messageId;
        this.messageText = messageText;
        this.senderId = senderId;
        this.timestamp = timestamp;
    }

    // Getters e Setters
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getText() {
        return 0;
    }
}





