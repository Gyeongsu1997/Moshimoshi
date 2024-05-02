package com.moshimoshi.message.domain;

import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@DiscriminatorValue("R")
@Getter
public class ReceivedMessage extends Message {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User recvUser;
}
