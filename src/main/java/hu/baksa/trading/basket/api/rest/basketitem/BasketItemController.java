package hu.baksa.trading.basket.api.rest.basketitem;

import hu.baksa.trading.basket.api.rest.basketitem.request.SaveBasketItemRequest;
import hu.baksa.trading.basket.api.rest.basketitem.response.BasketItemResponse;
import hu.baksa.trading.basket.api.rest.basketitem.response.SaveBasketItemResponse;
import hu.baksa.trading.basket.service.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketItemController {

    private final BasketItemService basketItemService;

    // TODO: 2023. 06. 30. remove, this is only for testing
    @GetMapping(value = "/api/basket-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BasketItemResponse>> getAll(Authentication auth){
        return ResponseEntity.ok(basketItemService.getAllBasketItems(auth.getName()));
    }

    @PostMapping(value = "/api/basket-item", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveBasketItemResponse> post(@RequestBody SaveBasketItemRequest request, Authentication auth){
        SaveBasketItemResponse response = basketItemService.saveBasketItem(request, auth.getName());
        return ResponseEntity.created(response.getCreatedUri()).build();
    }

    @DeleteMapping("/api/basket-item/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id, Authentication auth){
        basketItemService.deleteBasketItem(id, auth.getName());
        return ResponseEntity.noContent().build();
    }

}
