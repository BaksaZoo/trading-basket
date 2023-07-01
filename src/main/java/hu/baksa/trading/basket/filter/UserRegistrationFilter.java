package hu.baksa.trading.basket.filter;


import hu.baksa.trading.basket.service.UserService;
import jakarta.servlet.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

// TODO: 2023. 06. 30. make sure that this filter will run after spring security filters
@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegistrationFilter implements Filter {

    private final UserService userService;

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof JwtAuthenticationToken) || !authentication.isAuthenticated()){
            filterChain.doFilter(req, resp);
            return;
        }

        UUID userId = UUID.fromString(
                ((JwtAuthenticationToken) authentication)
                        .getTokenAttributes()
                        .get("sub").toString()
        );

        String username = authentication.getName();
        if (!userService.exists(userId)){
            log.info("Registering user: " + username);
            userService.registerUser(userId, username);
        }
        filterChain.doFilter(req, resp);
    }
}
