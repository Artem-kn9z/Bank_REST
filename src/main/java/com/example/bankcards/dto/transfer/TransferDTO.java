package com.example.bankcards.dto.transfer;

import com.example.bankcards.entity.card.Card;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {
    @NotNull(message = "fromCard must be not empty")
    private Card fromCard;

    @NotNull(message = "toCard must be not empty")
    private Card toCard;

    @NotNull(message = "Amount must be not empty")
    private double amount;
}
