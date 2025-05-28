package com.example.bankcards.dto.transfer;

import com.example.bankcards.entity.card.Card;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity of a transfer")
public class TransferDTO {
    @NotNull(message = "fromCard must be not empty")
    @Schema(description = "The card debiting from")
    private Card fromCard;

    @NotNull(message = "toCard must be not empty")
    @Schema(description = "The card put it on")
    private Card toCard;

    @NotNull(message = "Amount must be not empty")
    @Schema(description = "Transfer amount", example = "500.00")
    private double amount;
}
