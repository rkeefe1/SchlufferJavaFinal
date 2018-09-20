package com.example.demo.messages;

import java.util.List;

public class MessageListModel {


    private List<MessageModel> messageModels;

    public MessageListModel(List<MessageModel> messageModels) {
    
        this.messageModels = messageModels;
    }

    public List<MessageModel> getMessageModels() {
        return messageModels;
    }

    public void setMessageModels(List<MessageModel> messageModels) {
        this.messageModels = messageModels;
    }
}
