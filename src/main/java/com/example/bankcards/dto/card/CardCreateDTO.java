package com.example.bankcards.dto.card;


import com.example.bankcards.dto.user.UserDTO;
import com.example.bankcards.entity.card.CardStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardCreateDTO {
    @NotEmpty(message = "The masked card number must be not empty")
    @Pattern(regexp = "^\\d{4} \\d{4} \\d{4} \\d{4}$", message = "Wrong card number")
    private String cardNumber;

    @NotEmpty(message = "The expiration date must be not empty")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$", message = "Wrong expiration date")
    private String expiryDate;

    @NotNull(message = "The card must have a user")
    private UserDTO owner;

    @NotNull(message = "Status must be not empty")
    private CardStatus status;

    @NotNull(message = "Balance must be not empty")
    private Double balance;
}
