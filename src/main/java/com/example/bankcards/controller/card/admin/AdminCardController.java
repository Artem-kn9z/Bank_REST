package com.example.bankcards.controller.card.admin;

import com.example.bankcards.dto.card.CardCreateDTO;
import com.example.bankcards.dto.card.CardResponseDTO;
import com.example.bankcards.dto.card.CardStatusDTO;
import com.example.bankcards.entity.card.Card;
import com.example.bankcards.service.card.CardService;
import com.example.bankcards.exception.EntityNotFoundException;
import com.example.bankcards.exception.EntityValidateException;
import com.example.bankcards.util.validation.card.CardValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/cards")
public class AdminCardController {
    private final CardService cardService;

    @Autowired
    public AdminCardController(final CardService cardService) { this.cardService = cardService; }

    @GetMapping
    public ResponseEntity<List<CardResponseDTO>> getAllCards() throws EntityNotFoundException {
            List<Card> cards = cardService.findAll();
            List<CardResponseDTO> cardResponseDTO = cards
                    .stream()
                    .map(this::mapToResponseDTO)
                    .collect(Collectors.toList());
        System.out.println(cardResponseDTO.stream().toString());
            return new ResponseEntity<>(cardResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDTO> getCardById(@PathVariable int id) throws EntityNotFoundException {
        Card card = cardService.findById(id);
        return new ResponseEntity<>(mapToResponseDTO(card), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createCard(@RequestBody @Valid CardCreateDTO cardDto) throws EntityNotFoundException, EntityValidateException {
        cardService.save(cardDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateCard(@PathVariable int id, @RequestBody CardCreateDTO cardDto) throws EntityNotFoundException, EntityValidateException {
        cardService.updateById(id, cardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<HttpStatus> updateCardStatus(@PathVariable int id, @RequestBody CardStatusDTO status) throws EntityNotFoundException, EntityValidateException {
        cardService.updateCardStatus(id, status);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable int id) throws EntityNotFoundException {
        cardService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private CardResponseDTO mapToResponseDTO(Card card){
            return new CardResponseDTO(
                    card.getCardNumber(),
                    card.getMaskedCardNumber(),
                    card.getExpiryDate(),
                    card.getStatus(),
                    card.getBalance()
            );
    }
}
