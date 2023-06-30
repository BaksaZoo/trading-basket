package hu.baksa.trading.basket.service;

import hu.baksa.trading.basket.api.rest.basketitem.mapper.BasketItemMapper;
import hu.baksa.trading.basket.api.rest.basketitem.request.SaveBasketItemRequest;
import hu.baksa.trading.basket.api.rest.basketitem.response.BasketItemResponse;
import hu.baksa.trading.basket.api.rest.basketitem.response.SaveBasketItemResponse;
import hu.baksa.trading.basket.model.BasketItem;
import hu.baksa.trading.basket.repository.BasketItemRepository;
import hu.baksa.trading.core.model.Product;
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

    public List<BasketItemResponse> getAllBasketItems() {
        return basketItemRepository.findAll()
                .stream().map(BasketItemMapper.INSTANCE::toBasketItemResponse)
                .toList();
    }

    @Transactional
    public SaveBasketItemResponse saveBasketItem(SaveBasketItemRequest request) {
        BasketItem basketItem = BasketItemMapper.INSTANCE.toBasketItem(request);

        // if basket item with a product already exists, then don't create new basket item, just modify the amount
        var maybeBasketItem = basketItemRepository.findByProductId(request.productId());
        if (maybeBasketItem.isPresent()) {
            basketItem.setId(maybeBasketItem.get().getId());
        }
        
        basketItem.setProduct(Product.builder().id(request.productId()).build());
        basketItem = basketItemRepository.save(basketItem);

        return BasketItemMapper.INSTANCE.toSaveBasketItemResponse(basketItem);
    }
}
