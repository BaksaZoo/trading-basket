package hu.baksa.trading.basket.api.rest.basket.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import hu.baksa.trading.basket.api.rest.basketitem.response.BasketItemResponse;

import java.util.List;

public record BasketResponse(List<BasketItemResponse> items) {

    @JsonProperty
    public float total(){
        return (float)items.stream().mapToDouble(value -> value.amount() * value.product().price()).sum();
    }
}
