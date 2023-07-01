package hu.baksa.trading.basket.api.rest.basket;

import hu.baksa.trading.basket.api.rest.basket.response.BasketResponse;
import hu.baksa.trading.basket.api.rest.basketitem.mapper.BasketItemMapper;
import hu.baksa.trading.basket.model.Basket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BasketItemMapper.class)
public interface BasketMapper {

    BasketMapper INSTANCE = Mappers.getMapper(BasketMapper.class);


    @Mapping(source = "basketItems", target = "items")
    BasketResponse toBasketResponse(Basket basket);
}
