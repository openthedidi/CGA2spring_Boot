package com.cj.cga101g1.chatRoom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private MessageRepository messageRepository;


    @MessageMapping("/chat")
    @SendTo("/topic/channelA")
    public Message sendMessage(Message message) {
        // 將消息存儲到 Redis
        messageRepository.save(message);

        // 將消息發送給所有訂閱者
        return message;
    }
}
