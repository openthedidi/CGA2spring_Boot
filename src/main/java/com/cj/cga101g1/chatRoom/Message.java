package com.cj.cga101g1.chatRoom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@RedisHash("Message")
@Getter
@Setter
public class Message implements Serializable {
    @Id
    private String id;
    private String username;
    private String message;

    public Message() {
    }

    public Message( String username, String message) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.message = message;
    }
}