package hu.baksa.trading.basket.repository;

import hu.baksa.trading.basket.model.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    Optional<BasketItem> findByProductId(Long productId);
}
