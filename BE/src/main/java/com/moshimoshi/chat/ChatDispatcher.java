package com.moshimoshi.chat;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.socket.WebSocketSession;

import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.moshimoshi.chat.WebSocketChatHandler.chatRoomMap;
import static com.moshimoshi.chat.WebSocketChatHandler.running;

public class ChatDispatcher {
    private final Queue<WebSocketSession> ready = new ConcurrentLinkedQueue<>();

    @Async
    public boolean dispatch(WebSocketSession session) {
        ready.remove(session);
        if (ready.isEmpty()) {
            ready.add(session);
            return false;
            //handler에서 매칭 실패 메시지
        }
        WebSocketSession partner = ready.poll();
        if (partner == null) {
            ready.add(session);
            return false;
            //handler에서 매칭 실패 메시지
        }
        //내가 파트너를 찾았는데, 다른 사람이 나를 찾은 경우?

        String chatRoomId = UUID.randomUUID().toString();
        Set<WebSocketSession> chatRoom = ConcurrentHashMap.newKeySet();
        chatRoom.add(session);
        chatRoom.add(partner);
        chatRoomMap.put(chatRoomId, chatRoom);

        running.add(session);
        running.add(partner);

        return true;
        //handler에서 매칭 성공 메시지 to session
        //handler에서 매칭 성공 메시지 to partner
    }

}
