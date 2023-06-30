package hu.baksa.trading.basket.api.rest.basketitem.mapper;

import hu.baksa.trading.basket.api.rest.basketitem.request.SaveBasketItemRequest;
import hu.baksa.trading.basket.api.rest.basketitem.response.BasketItemResponse;
import hu.baksa.trading.basket.api.rest.basketitem.response.SaveBasketItemResponse;
import hu.baksa.trading.basket.model.BasketItem;
import hu.baksa.trading.core.api.rest.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ProductMapper.class)
public interface BasketItemMapper {
    BasketItemMapper INSTANCE = Mappers.getMapper(BasketItemMapper.class);

    BasketItemResponse toBasketItemResponse(BasketItem basketItem);

    // TODO: 2023. 06. 30. maybe productId mapping needs to be considered carefully
    BasketItem toBasketItem(SaveBasketItemRequest saveBasketItemRequest);

    SaveBasketItemResponse toSaveBasketItemResponse(BasketItem basketItem);
}
