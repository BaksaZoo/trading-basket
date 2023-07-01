package hu.baksa.trading.basket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    private UUID id;
    private String username;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Basket basket;
}
