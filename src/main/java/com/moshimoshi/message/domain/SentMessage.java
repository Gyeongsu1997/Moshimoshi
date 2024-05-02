package com.moshimoshi.message.domain;

import com.moshimoshi.user.domain.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@DiscriminatorValue("S")
@Getter
public class SentMessage extends Message {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private User sendUser;
}
