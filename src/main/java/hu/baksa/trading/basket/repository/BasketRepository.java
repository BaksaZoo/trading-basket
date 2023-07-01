package hu.baksa.trading.basket.repository;

import hu.baksa.trading.basket.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByUserId(UUID userId);
}
