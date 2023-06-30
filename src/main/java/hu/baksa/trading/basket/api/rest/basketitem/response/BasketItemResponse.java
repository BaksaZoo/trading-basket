package hu.baksa.trading.basket.api.rest.basketitem.response;

import hu.baksa.trading.core.api.rest.product.response.ProductResponse;

public record BasketItemResponse(Long id, ProductResponse product, int amount) {
}
