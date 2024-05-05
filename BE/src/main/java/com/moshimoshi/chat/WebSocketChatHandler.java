package com.moshimoshi.chat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moshimoshi.chat.dto.ChatMessageRequest;
import com.moshimoshi.chat.dto.ChatMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    public static final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet(); //현재 연결된 세션들
    private final Queue<WebSocketSession> ready = new ConcurrentLinkedQueue<>();
    public static final Set<WebSocketSession> running = ConcurrentHashMap.newKeySet();
    public static final Map<String, Set<WebSocketSession>> chatRoomMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("[{}] Connection Established", session.getId());
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ChatMessageRequest chatMessageRequest = objectMapper.readValue(payload, ChatMessageRequest.class);

        if (chatMessageRequest.getType() == ChatMessageType.ENTER) {
            if (!running.contains(session)) {
                ready.add(session);
                dispatch(session);
            } else {
                //이미 매칭이 된 경우 어떻게 처리할 지 고민
            }
        } else if (chatMessageRequest.getType() == ChatMessageType.TEXT) {
            //텍스트 전송
        } else if (chatMessageRequest.getType() == ChatMessageType.EXIT) {
            exit(chatMessageRequest.getChatRoomId());
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    private synchronized void dispatch(WebSocketSession session) {
        ready.remove(session);
        if (ready.isEmpty()) {
            ready.add(session);
            //매칭 실패 메시지
        }
        WebSocketSession partner = ready.poll();
        if (partner == null) {
            ready.add(session);
            //매칭 실패 메시지
        }

        String chatRoomId = UUID.randomUUID().toString();
        Set<WebSocketSession> chatRoom = ConcurrentHashMap.newKeySet();
        chatRoom.add(session);
        chatRoom.add(partner);
        chatRoomMap.put(chatRoomId, chatRoom);

        running.add(session);
        running.add(partner);

        //매칭 성공 메시지 to session
        //매칭 성공 메시지 to partner
    }

    private void exit(String chatRoomId) {
        Set<WebSocketSession> chatRoom = chatRoomMap.get(chatRoomId);
        for (WebSocketSession session : chatRoom) {
//            session.sendMessage(); //종료 메시지
        }
        chatRoom.clear();
        chatRoomMap.remove(chatRoomId);
    }
}
