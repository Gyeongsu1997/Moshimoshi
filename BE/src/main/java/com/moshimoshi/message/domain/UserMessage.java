package com.moshimoshi.message.domain;

import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class UserMessage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private Message message;

    public static UserMessage of(User user, Message message) {
        UserMessage userMessage = new UserMessage();
        userMessage.user = user;
        userMessage.message = message;
        return userMessage;
    }
}
