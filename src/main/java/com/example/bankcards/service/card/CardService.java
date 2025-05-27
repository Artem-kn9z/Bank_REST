package com.example.bankcards.service.card;

import com.example.bankcards.dto.card.CardCreateDTO;
import com.example.bankcards.dto.card.CardStatusDTO;
import com.example.bankcards.entity.card.Card;
import com.example.bankcards.entity.user.User;
import com.example.bankcards.repository.card.CardRepository;
import com.example.bankcards.service.transfer.TransferService;
import com.example.bankcards.service.user.UserService;
import com.example.bankcards.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository cardRepository;

    private final TransferService transferService;

    private final UserService userService;

    @Autowired
    public CardService(CardRepository cardRepository, TransferService transferService, UserService userService) {
        this.cardRepository = cardRepository;
        this.transferService = transferService;
        this.userService = userService;
    }

    public List<Card> findAll() throws EntityNotFoundException {
        List<Card> cards = cardRepository.findAll();

        if (cards.isEmpty()) {
            throw new EntityNotFoundException("Card not found");
        }

        return cards;
    }

    public List<Card> findByUserId(int ownerId) throws EntityNotFoundException {
        List<Card> cards = cardRepository.findByOwnerId(ownerId);

        if (cards.isEmpty()) {
            throw new EntityNotFoundException("Card not found");
        }

        return cards;
    }

    public Card findById(int id) throws EntityNotFoundException {
        Optional<Card> card = cardRepository.findById(id);

        return card.orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }

    public Card findByCardNumber(String cardNumber) throws EntityNotFoundException {
        Optional<Card> card = cardRepository.findByEncryptedCardNumber(cardNumber);
        return card.orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }

    @Transactional
    public void save(CardCreateDTO cardDto) throws EntityNotFoundException {
        Card card = new Card();

        User user;

        try {
            user = userService.getUserByFullName(cardDto.getOwner().getFullName());
        }catch (EntityNotFoundException e){
            user = new User();
            user.setFullName(cardDto.getOwner().getFullName());
            user.setUsername(cardDto.getOwner().getUsername());
            user.setPassword(cardDto.getOwner().getPassword());
            user.setEmail(cardDto.getOwner().getEmail());
            user.setRole(cardDto.getOwner().getRole());
            userService.saveUser(user);
        }

        card.setOwner(user);

        try {
            card.setCardNumber(cardDto.getCardNumber());
        }catch (Exception e){
            throw new EntityNotFoundException("Card not found");
        }

        card.setExpiryDate(cardDto.getExpiryDate());
        card.setStatus(cardDto.getStatus());
        card.setBalance(cardDto.getBalance());

        cardRepository.save(card);
    }

    @Transactional
    public void updateCardStatus(int id, CardStatusDTO newStatusDTO) throws EntityNotFoundException {
        Card card = findById(id);

        card.setStatus(newStatusDTO.getStatus());

        cardRepository.save(card);
    }

    @Transactional
    public void updateById(int cardId, CardCreateDTO cardDto) throws EntityNotFoundException {
        Card card = findById(cardId);

        try {
            card.setCardNumber(cardDto.getCardNumber());
        } catch (Exception e){
            throw new EntityNotFoundException("Card not found");
        }

        card.setExpiryDate(cardDto.getExpiryDate());
        card.setStatus(cardDto.getStatus());
        card.setBalance(cardDto.getBalance());

        User user;

        try {
            user = userService.getUserByFullName(cardDto.getOwner().getFullName());
        }catch (EntityNotFoundException e){
            user = new User();
            user.setFullName(cardDto.getOwner().getFullName());
            user.setUsername(cardDto.getOwner().getUsername());
            user.setPassword(cardDto.getOwner().getPassword());
            user.setEmail(cardDto.getOwner().getEmail());
            user.setRole(cardDto.getOwner().getRole());
            userService.saveUser(user);
        }

        card.setOwner(user);

        cardRepository.save(card);
    }

    public void deleteById(int cardId) throws EntityNotFoundException {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new EntityNotFoundException("Card with id: "+cardId+" not found"));
        cardRepository.delete(card);
    }

}
