package com.example.bankcards.repository.card;

import com.example.bankcards.entity.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByEncryptedCardNumber(String encryptedCardNumber);
    List<Card> findByOwnerId(int ownerId);
}
