package hu.baksa.trading.basket.repository;

import hu.baksa.trading.basket.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
