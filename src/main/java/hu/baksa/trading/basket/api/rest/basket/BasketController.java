package hu.baksa.trading.basket.api.rest.basket;

import hu.baksa.trading.basket.api.rest.basket.response.BasketResponse;
import hu.baksa.trading.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BasketResponse> get(Authentication auth){
        return ResponseEntity.ok(basketService.getUserBasketResponse(auth.getName()));
    }

}
