package com.example.bankcards.util.validation.card;

import com.example.bankcards.entity.card.Card;
import com.example.bankcards.exception.EntityNotFoundException;
import com.example.bankcards.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CardValidator implements Validator {

    private final CardService cardService;

    @Autowired
    public CardValidator(CardService cardService) { this.cardService = cardService; }

    @Override
    public boolean supports(Class<?> clazz) { return Card.class.equals(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        Card card = (Card) target;

        try {
            Card tempCard = cardService.findById(card.getId());
            if ((tempCard != null) && (tempCard.getId() != card.getId())) {
                errors.rejectValue("id", "card.id.exist");
            }
        } catch (EntityNotFoundException e) {
            //TO DO: add log here
        }
    }


}
