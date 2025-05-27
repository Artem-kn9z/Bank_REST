package com.example.bankcards.entity.user;

import com.example.bankcards.entity.card.Card;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    @NotEmpty(message = "Full name must be not empty")
    @Pattern(regexp = "^[А-ЯЁ][а-яё]+(?: [А-ЯЁ][а-яё]+){1,2}$", message = "Wrong fullName format")
    private String fullName;

    @Column(name = "username")
    @NotEmpty(message = "Username must be not empty")
    private String username;

    @Column(name = "email")
    @Email(message = "Email must have email format")
    @NotEmpty(message = "Email must be not empty")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Password must be not empty")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 symbols")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must be not empty")
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Card> cards;
}
