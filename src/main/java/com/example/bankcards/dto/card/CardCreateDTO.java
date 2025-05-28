package com.example.bankcards.dto.card;


import com.example.bankcards.dto.user.UserDTO;
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
@Schema(description = "Entity of a create card")
public class CardCreateDTO {
    @NotEmpty(message = "The masked card number must be not empty")
    @Pattern(regexp = "^\\d{4} \\d{4} \\d{4} \\d{4}$", message = "Wrong card number")
    @Schema(description = "Card's number", example = "1111 1111 1111 1111")
    private String cardNumber;

    @NotEmpty(message = "The expiration date must be not empty")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$", message = "Wrong expiration date")
    @Schema(description = "Card's expiration date", example = "11/28")
    private String expiryDate;

    @NotNull(message = "The card must have a user")
    @Schema(description = "Card's owner")
    private UserDTO owner;

    @NotNull(message = "Status must be not empty")
    @Schema(description = "Card's status", example = "ACTIVE")
    private CardStatus status;

    @NotNull(message = "Balance must be not empty")
    @Schema(description = "Card's balance", example = "2000.00")
    private Double balance;
}
