package com.mate.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mate.mate.controller.response.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class WebSocketMessageSender extends TextWebSocketHandler {

    @Autowired
    private WebSocketSessionManager sessionManager;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessageToRecipient(String recipientId, ChatResponse.chatMessageOne message) {
        WebSocketSession recipientSession = sessionManager.getSession(recipientId);
        if (recipientSession != null && recipientSession.isOpen()) {
            try {
                String jsonMessage = objectMapper.writeValueAsString(message);
                recipientSession.sendMessage(new TextMessage(jsonMessage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("상대방의 세션이 없거나 연결이 닫혀있습니다 - 수신자가 로그인 안한듯?");
        }
    }
}
