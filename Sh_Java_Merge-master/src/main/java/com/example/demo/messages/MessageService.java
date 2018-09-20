package com.example.demo.messages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.lang3.StringUtils;
import com.example.demo.users.UserModel;
import com.example.demo.users.UserRepository;
import com.example.demo.messages.Message;
import com.example.demo.messages.MessageListModel;
import com.example.demo.messages.MessageModel;
import com.example.demo.messages.MessageSendModel;
import com.example.demo.messages.MessageSortModel;
import com.example.demo.messages.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public MessageListModel getUserMessages(MessageSortModel messageSortModel) throws ServiceErrorException {
		final String sort = messageSortModel.getSort();
		if (StringUtils.isNotEmpty(sort) && !Message.isSortFieldValid(sort)) {
			throw new ServiceErrorException("This Field is not present in the table!");
		}
		final List<Message> messages = messageRepository.findByToUser(messageSortModel);
		List<MessageModel> messageModels = new ArrayList<>();
		MessageListModel messageListModel = new MessageListModel(messageModels);
		if (messages.size() == 0) {
			return messageListModel;
		}
		for (Message message : messages) {
			MessageModel messageModel = getMessageModel(message);
			messageModels.add(messageModel);
		}
		return messageListModel;

	}

	private MessageModel getMessageModel(Message message) {
		MessageModel messageModel = new MessageModel();
		messageModel.setId(message.getId());
		messageModel.setFrom(message.getAuthor().getUsername());
		messageModel.setSubject(message.getSubject());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		messageModel.setDate(dateFormat.format(message.getCreateDate()));
		return messageModel;
	}

	public void deleteMessage(Long id) throws ServiceErrorException {
		final Message userMessage = messageRepository.findById(id).orElse(null);
		if (userMessage == null) {
			throw new ServiceErrorException("Message not found!");
		}
		messageRepository.deleteById(id);
	}

    public MessageModel getMessage(Long id) throws ServiceErrorException {
        final List<Message> messageByIdAndUser = messageRepository.findMessageByIdAndUser(id);
        if (messageByIdAndUser.size() != 1) {
            throw new ServiceErrorException("Message not found!");
        }
        final Message message = messageByIdAndUser.get(0);
        final MessageModel messageModel = getMessageModel(message);
        messageModel.setMessage(message.getMessage());
        return messageModel;
    }
    
	public void send(MessageSendModel messageSendModel) throws ServiceErrorException {
//		final List<UserModel> usersByUserId = userRepository.getUsersByUserId(messageSendModel.getToId().intValue());
		UserModel author = userRepository.findById(messageSendModel.getFromId()).orElse(null);
		UserModel toUser = userRepository.findById(messageSendModel.getToId()).orElse(null);
//		if (usersByUserId.size() != 1) {;
//			throw new ServiceErrorException("User not found!");
//		}
		Message message = new Message();
		message.setToUser(toUser);
		message.setAuthor(author);
		message.setCreateDate(new Date());
		message.setSubject(messageSendModel.getSubject());
		message.setMessage(messageSendModel.getMessage());
		messageRepository.save(message);
	}
}
