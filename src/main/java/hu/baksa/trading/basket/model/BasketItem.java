package hu.baksa.trading.basket.model;

import hu.baksa.trading.core.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BasketItem {

    @Id
    @SequenceGenerator(name = "pk_sequence", sequenceName = "basket_item_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pk_sequence")
    private Long id;
    @OneToOne
    private Product product;
    private int amount;
    @ManyToOne
    private Basket basket;
}
