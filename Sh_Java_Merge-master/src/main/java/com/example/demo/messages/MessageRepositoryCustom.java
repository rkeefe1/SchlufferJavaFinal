package com.example.demo.messages;

import java.util.List;


import com.example.demo.messages.Message;
import com.example.demo.messages.MessageSortModel;

public interface MessageRepositoryCustom {

    List<Message> findByToUser(MessageSortModel messageSortModel);

}
