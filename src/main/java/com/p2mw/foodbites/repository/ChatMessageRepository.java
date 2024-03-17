package com.p2mw.foodbites.repository;

import com.p2mw.foodbites.model.ChatMessage;
import com.p2mw.foodbites.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findBySenderOrReceiver(User user1, User user2);
}
