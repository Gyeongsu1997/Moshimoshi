package com.moshimoshi.message.dto;

import lombok.Getter;

@Getter
public class MessageRequest {
    private Long threadId;
    private Long commentId;
    private Long sendUserID;
    private Long recvUserID;
    private String content;
}
