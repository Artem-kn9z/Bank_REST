package com.example.bankcards.controller.card.user;

import com.example.bankcards.dto.card.CardResponseDTO;
import com.example.bankcards.entity.card.Card;
import com.example.bankcards.service.card.CardService;
import com.example.bankcards.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/cards")
public class UserCardController {
    private final CardService cardService;

    public UserCardController(final CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getAllUserCards() throws EntityNotFoundException {
            List<Card> cards = cardService.findAll();
            List<CardResponseDTO> cardResponseDTO = cards
                    .stream()
                    .map(this::mapToResponseDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{cardNumber}")
    public ResponseEntity<CardResponseDTO> getCardsByCardNumber(@PathVariable String cardNumber) throws EntityNotFoundException {
        Card card = cardService.findByCardNumber(cardNumber);
        return new ResponseEntity<>(mapToResponseDTO(card), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable int id) throws EntityNotFoundException {
        Card card = cardService.findById(id);
        return new ResponseEntity<>(mapToResponseDTO(card), HttpStatus.OK);
    }


    private CardResponseDTO mapToResponseDTO(final Card card) {
        return new CardResponseDTO(
                card.getEncryptedCardNumber(),
                card.getMaskedCardNumber(),
                card.getExpiryDate(),
                card.getStatus(),
                card.getBalance()
        );
    }
}
