package hu.baksa.trading.basket.service;

import hu.baksa.trading.basket.api.rest.basketitem.mapper.BasketItemMapper;
import hu.baksa.trading.basket.api.rest.basketitem.request.SaveBasketItemRequest;
import hu.baksa.trading.basket.api.rest.basketitem.response.BasketItemResponse;
import hu.baksa.trading.basket.api.rest.basketitem.response.SaveBasketItemResponse;
import hu.baksa.trading.basket.model.BasketItem;
import hu.baksa.trading.basket.repository.BasketItemRepository;
import hu.baksa.trading.basket.repository.UserRepository;
import hu.baksa.trading.core.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketItemService {

    private final BasketItemRepository basketItemRepository;
    private final BasketService basketService;

    public List<BasketItemResponse> getAllBasketItems(String username) {
        List<BasketItem> basketItems = basketService.getUserBasket(username).getBasketItems();

        return basketItems.stream()
                .map(BasketItemMapper.INSTANCE::toBasketItemResponse)
                .toList();
    }

    @Transactional
    public SaveBasketItemResponse saveBasketItem(SaveBasketItemRequest request, String username) {
        // TODO: 2023. 06. 30. basket_item.product_id and basket_item.user_id should be unique together
        BasketItem basketItem = BasketItemMapper.INSTANCE.toBasketItem(request);
        basketItem.setBasket(basketService.getUserBasket(username));

        // if basket item with a product already exists, then don't create new basket item, just update the amount
        var maybeBasketItem = basketItemRepository
                .findByProductIdAndBasketId(request.productId(), basketItem.getBasket().getId());
        if (maybeBasketItem.isPresent()) {
            basketItem.setId(maybeBasketItem.get().getId());
        }

        basketItem.setProduct(Product.builder().id(request.productId()).build());
        basketItem = basketItemRepository.save(basketItem);

        return BasketItemMapper.INSTANCE.toSaveBasketItemResponse(basketItem);
    }

    @Transactional
    public void deleteBasketItem(Long id, String username) throws NoSuchElementException{
        Optional<BasketItem> basketItem = basketItemRepository.findById(id);
        if (basketItem.isEmpty() || !basketItem.get().getBasket().getUser().getUsername().equals(username)) {
            throw new NoSuchElementException(String.format("No basket item with id: %d", id));
        }
        basketItemRepository.deleteById(id);
    }
}
