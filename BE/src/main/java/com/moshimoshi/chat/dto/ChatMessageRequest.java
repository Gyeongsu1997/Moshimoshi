package com.moshimoshi.chat.dto;

import lombok.Getter;

@Getter
public class ChatMessageRequest {
    private ChatMessageType type;
    private String chatRoomId;
    private String message;
}
