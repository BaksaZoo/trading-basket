package hu.baksa.trading.basket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Basket {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "basket_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;
    @OneToMany(mappedBy = "basket")
    private List<BasketItem> basketItems;
    @OneToOne
    private User user;
}
