package com.example.demo.services;

import com.example.demo.model.Message;
import com.example.demo.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getDialog(Long user1, Long user2) {
        return messageRepository.getDialog(user1, user2);
    }

    public List<Message> saveMessage(String content, Long user1, Long user2) {
        List<Message> messageList = messageRepository.getDialog(user1, user2);
        Message message = new Message();
        message.setContent(content);
        messageList.add(message);
        return messageList;
    }

}
