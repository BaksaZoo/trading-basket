package hu.baksa.trading.basket.api.rest.basketitem.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public record SaveBasketItemResponse(Long id) {

    @JsonIgnore
    public URI getCreatedUri(){
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id())
                .toUri();
    }
}
