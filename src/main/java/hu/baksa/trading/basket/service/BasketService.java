package hu.baksa.trading.basket.service;

import hu.baksa.trading.basket.api.rest.basket.BasketMapper;
import hu.baksa.trading.basket.api.rest.basket.response.BasketResponse;
import hu.baksa.trading.basket.model.Basket;
import hu.baksa.trading.basket.model.User;
import hu.baksa.trading.basket.repository.BasketRepository;
import hu.baksa.trading.basket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;

    public BasketResponse getUserBasketResponse(String username) {
        return BasketMapper.INSTANCE.toBasketResponse(getUserBasket(username));
    }

    public Basket getUserBasket(String username){
        return userRepository.findByUsername(username).getBasket();
    }
}
