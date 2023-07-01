package hu.baksa.trading.basket.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPISecurityConfig {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;
    @Value("${keycloak.realm}")
    private String realm;

    private static final String OPENID_SCHEME_NAME = "OpenId";
    private static final String OPENID_CONFIG_FORMAT = "%s/auth/realms/%s/.well-known/openid-configuration";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().components(new Components()
                        .addSecuritySchemes(OPENID_SCHEME_NAME, createOpenIdScheme()))
                .addSecurityItem(new SecurityRequirement()
                        .addList(OPENID_SCHEME_NAME))
                .info(new Info().title("Store")
                        .description("A store app.")
                        .version("0.1"));
    }

    private SecurityScheme createOpenIdScheme() {
        String connectUrl = String.format(OPENID_CONFIG_FORMAT, authServerUrl, realm);

        return new SecurityScheme()
                .type(SecurityScheme.Type.OPENIDCONNECT)
                .openIdConnectUrl(connectUrl);
    }
}
