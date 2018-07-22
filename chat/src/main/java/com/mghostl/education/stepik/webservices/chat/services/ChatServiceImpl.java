package com.mghostl.education.stepik.webservices.chat.services;

import com.mghostl.education.stepik.webservices.chat.servlets.ChatWebSocket;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServiceImpl implements ChatService {
    private Set<ChatWebSocket> webSockets;

    public ChatServiceImpl() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    @Override
    public void add(ChatWebSocket socket) {
        webSockets.add(socket);
    }

    @Override
    public void sendMessage(String message) {
        webSockets.forEach(user -> user.sendString(message));
    }

    @Override
    public void remove(ChatWebSocket socket) {
        webSockets.remove(socket);
    }
}
