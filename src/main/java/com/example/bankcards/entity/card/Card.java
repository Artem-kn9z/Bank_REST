package com.example.bankcards.entity.card;

import com.example.bankcards.entity.user.User;
import com.example.bankcards.util.AesEncryptionUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "encrypted_card_number")
    @NotEmpty(message = "The card number must be not empty")
    @Pattern(regexp = "^[A-Za-z0-9+/=]{16,255}$", message = "Wrong card number")
    private String encryptedCardNumber;

    @Column(name = "masked_card_number")
    @NotEmpty(message = "The masked card number must be not empty")
    @Pattern(regexp = "^\\*{4} \\*{4} \\*{4} \\d{4}$", message = "Wrong card number")
    private String maskedCardNumber;

    @NotNull(message = "The card must have a user")
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "expiry_date")
    @NotEmpty(message = "The expiration date must be not empty")
    @Pattern(regexp = "^(0[1-9]|1[0-2])\\/\\d{2}$", message = "Wrong expiration date")
    private String expiryDate;

    @Column(name = "status", nullable = false)
    @NotNull(message = "Status must be not empty")
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @Column(name = "balance")
    @NotNull(message = "Balance must be not empty")
    private Double balance;

    public static String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        return "**** **** **** " + last4;
    }

    public void setCardNumber(String cardNumber) throws Exception {
        this.encryptedCardNumber = AesEncryptionUtil.encrypt(cardNumber);
        this.maskedCardNumber = maskCardNumber(cardNumber);
    }

    public String getCardNumber() {
        try {
            return AesEncryptionUtil.decrypt(this.encryptedCardNumber);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

