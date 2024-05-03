package com.moshimoshi.message.domain;

import com.moshimoshi.common.domain.BaseTimeEntity;
import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
@Getter
public class Message extends BaseTimeEntity {
    private static final AtomicLong roomSequence = new AtomicLong();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    private Long roomId;
    private Long sendUserId;
    private Long recvUserId;
    private String content;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL)
    private List<UserMessage> userMessages = new ArrayList<>();

    public static Message createMessage(User sendUser, User recvUser) {
        Message message = new Message();

        message.roomId = roomSequence.getAndIncrement();
        message.sendUserId = sendUser.getId();
        message.recvUserId = recvUser.getId();

        message.addUserMessages(UserMessage.of(sendUser, message));
        message.addUserMessages(UserMessage.of(recvUser, message));
        return message;
    }

    private void addUserMessages(UserMessage userMessage) {
        userMessages.add(userMessage);
    }
}
