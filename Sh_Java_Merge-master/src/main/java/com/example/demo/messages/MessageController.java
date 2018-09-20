package com.example.demo.messages;

import java.io.IOException;
import java.io.Writer;
//import java.util.List;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//import com.example.demo.message.Message;
import com.example.demo.messages.MessageListModel;
import com.example.demo.messages.MessageModel;
import com.example.demo.messages.MessageSendModel;
import com.example.demo.messages.MessageSortModel;
//import com.example.demo.message.dao.MessageRepository;
import com.example.demo.messages.MessageService;
import com.example.demo.messages.ServiceErrorException;

@RestController
@RequestMapping(value = "/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public void handleException(final ServiceErrorException e, Writer writer) throws IOException {
        logger.error(e.getMessage(), e);
        writer.write(String.format("{\"error\":\"%s\"}", e.getMessage()));
    }

//    @GetMapping()
//    public String message(@RequestBody MessageSortModel messageSortModel) throws ServiceErrorException {
//        return messageService.getMessage(messageSortModel); // Test to return the word messages
//    }
//    @GetMapping()
//    public List<Message> getUserMessages(Message message, MessageSortModel messageSortModel) throws ServiceErrorException {
//    return messageService.getUserMessages(messageSortModel);
//    } 	
    	
    @GetMapping("/list")
    @ResponseBody
    public MessageListModel getUserMessages(@RequestBody MessageSortModel messageSortModel) throws ServiceErrorException {
        return messageService.getUserMessages(messageSortModel);
    }
    
    
    @GetMapping("/{id}")
    @ResponseBody
    public MessageModel message(@PathVariable("id") Long id) throws ServiceErrorException {
        return messageService.getMessage(id);
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void send(@RequestBody MessageSendModel messageSendModel) throws ServiceErrorException {
        messageService.send(messageSendModel);
    }

    @DeleteMapping("/delete/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public void deleteFromList(@PathVariable("id") Long id) throws ServiceErrorException {
        messageService.deleteMessage(id);
    }
}
