package com.cj.cga101g1.chatRoom;

import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, String> {
}
