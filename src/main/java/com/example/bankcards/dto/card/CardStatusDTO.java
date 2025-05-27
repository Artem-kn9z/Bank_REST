package com.example.bankcards.dto.card;

import com.example.bankcards.entity.card.CardStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardStatusDTO {

    @NotNull
    private CardStatus status;

}
