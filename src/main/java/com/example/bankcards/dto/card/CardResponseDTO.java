package com.example.bankcards.dto.card;


import com.example.bankcards.entity.card.CardStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity of a response card")
public class CardResponseDTO {
    @NotEmpty(message = "The card number must be not empty")
    @Pattern(regexp = "^[A-Za-z0-9+/=]{16,255}$", message = "Wrong card number")
    @Schema(description = "Card's encrypted number", example = "9dzFM67sKxUWE19dBp3mjQpK+wRg/269uDHMgG5oZLc=")
    private String encryptedCardNumber;

    @NotEmpty(message = "The masked card number must be not empty")
    @Pattern(regexp = "^\\*{4} \\*{4} \\*{4} \\d{4}$", message = "Wrong card number")
    @Schema(description = "Card's masked number", example = "**** **** **** 1111")
    private String maskedCardNumber;

    @NotEmpty(message = "The expiration date must be not empty")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$", message = "Wrong expiration date")
    @Schema(description = "Card's expiration date", example = "11/27")
    private String expiryDate;

    @NotNull(message = "Status must be not empty")
    @Schema(description = "Card's status", example = "ACTIVE")
    private CardStatus status;

    @NotNull(message = "Balance must be not empty")
    @Schema(description = "Card's balance", example = "2000.00")
    private Double balance;
}
