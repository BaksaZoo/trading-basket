package hu.baksa.trading.basket.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Basket {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "basket_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;
    @OneToMany
    private List<BasketItem> basketItems;
}
