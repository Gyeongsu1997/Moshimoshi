package com.moshimoshi.message.dto;

import lombok.Getter;

@Getter
public class MessageRequest {
    private Long threadId;
    private Long commentId;
    private String sendUserID;
    private String recvUserID;
    private String content;
}
