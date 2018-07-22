package com.mghostl.education.stepik.webservices.chat.services;

import com.mghostl.education.stepik.webservices.chat.servlets.ChatWebSocket;

public interface ChatService {
    void add(ChatWebSocket socket);

    void sendMessage(String message);

    void remove(ChatWebSocket socket);
}
