package com.example.bankcards.entity.transfer;

import com.example.bankcards.entity.card.Card;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transfer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fromCardId")
    @NotNull(message = "fromCard must be not empty")
    private Card from_card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "toCard")
    @NotNull(message = "toCard must be not empty")
    private Card to_card;

    @Column(name = "amount")
    @NotNull(message = "Amount must be not empty")
    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Status must be not empty")
    private TransferStatus status;
}
