package hu.baksa.trading.basket.service;

import hu.baksa.trading.basket.model.BasketItem;
import hu.baksa.trading.basket.repository.BasketItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketItemService {

    private final BasketItemRepository basketItemRepository;

    public List<BasketItem> getAllBasketItems() {
        return basketItemRepository.findAll();
    }

    @Transactional
    public BasketItem saveBasketItemAsNew(BasketItem newBasketItem) {
        newBasketItem.setId(null);
        return saveBasketItem(newBasketItem);
    }

    @Transactional
    private BasketItem saveBasketItem(BasketItem basketItem) {
        return basketItemRepository.save(basketItem);
    }
}
