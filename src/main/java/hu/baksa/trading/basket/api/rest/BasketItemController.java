package hu.baksa.trading.basket.api.rest;

import hu.baksa.trading.basket.model.BasketItem;
import hu.baksa.trading.basket.service.BasketItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketItemController {

    private final BasketItemService basketItemService;

    // TODO: 2023. 06. 30. remove, this is only for testing
    @GetMapping(value = "/api/basket-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BasketItem>> getAll(){
        return ResponseEntity.ok(basketItemService.getAllBasketItems());
    }

    @PostMapping(value = "/api/basket-item", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasketItem> post(@RequestBody BasketItem newBasketItem){
        BasketItem savedbasketItem = basketItemService.saveBasketItemAsNew(newBasketItem);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/")
                        .buildAndExpand(savedbasketItem.getId())
                        .toUri()
        ).build();
    }

}
