package hu.baksa.trading.basket.api.rest.basketitem;

import hu.baksa.trading.basket.api.rest.basketitem.request.SaveBasketItemRequest;
import hu.baksa.trading.basket.api.rest.basketitem.response.BasketItemResponse;
import hu.baksa.trading.basket.api.rest.basketitem.response.SaveBasketItemResponse;
import hu.baksa.trading.basket.model.BasketItem;
import hu.baksa.trading.basket.service.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketItemController {

    private final BasketItemService basketItemService;

    // TODO: 2023. 06. 30. remove, this is only for testing
    @GetMapping(value = "/api/basket-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BasketItemResponse>> getAll(){
        return ResponseEntity.ok(basketItemService.getAllBasketItems());
    }

    @PostMapping(value = "/api/basket-item", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveBasketItemResponse> post(@RequestBody SaveBasketItemRequest request){
        SaveBasketItemResponse response = basketItemService.saveBasketItem(request);
        return ResponseEntity.created(response.getCreatedUri()).build();
    }

    @DeleteMapping("/api/basket-item/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        basketItemService.deleteBasketItem(id);
        return ResponseEntity.noContent().build();
    }

}
