package hu.baksa.trading.basket.service;

import hu.baksa.trading.basket.model.Basket;
import hu.baksa.trading.basket.model.User;
import hu.baksa.trading.basket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean exists(UUID id) {
        return userRepository.existsById(id);
    }

    @Transactional
    public User registerUser(UUID userId, String username) {
        return userRepository.save(
                User.builder()
                        .id(userId)
                        .username(username)
                        .basket(Basket.builder()
                                .user(User.builder().id(userId).build())
                                .build())
                        .build()
        );
    }
}
