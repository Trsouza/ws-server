package com.trs.wsserver.controller;

import com.trs.wsserver.model.Message;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
//@CrossOrigin(origins = "http://127.0.0.1:5173")
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        System.out.println(message.toString()+ " rec");
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        return message;
    }

    @MessageMapping("/message")
    @SendTo("/chat-room/messages")
    public Message sendMessage(@Payload Message message) {
            System.out.println(message.toString()+ " send");
        return message;
    }
}
