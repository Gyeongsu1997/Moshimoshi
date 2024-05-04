package com.moshimoshi.chat;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ChatDispatcher {
    @Async
    public boolean dispatch() {
        WebSocketSession session1 = ready.poll();
        WebSocketSession session2 = ready.poll();
        if (session1 == null && session2 == null) {
            return false;
        } else if (session1 == null) {
            ready.add(session2);
            return false;
        } else if (session2 == null) {
            ready.add(session1);
            return false;
        }
        String chatRoomId = UUID.randomUUID().toString();
        Set<WebSocketSession> chatRoom = ConcurrentHashMap.newKeySet();
        chatRoom.add(session1);
        chatRoom.add(session2);
        chatRoomMap.put(chatRoomId, chatRoom);
        return true;
    }

}
