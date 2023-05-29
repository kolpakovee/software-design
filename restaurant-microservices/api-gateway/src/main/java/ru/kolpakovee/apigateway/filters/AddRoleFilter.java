package ru.kolpakovee.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.kolpakovee.apigateway.pojo.UserInfoResponse;

import static ru.kolpakovee.apigateway.filters.AuthValidationFilter.extractAuthToken;

@Component
public class AddRoleFilter extends AbstractGatewayFilterFactory<AddRoleFilter.Config> {

    private final WebClient webClient;

    public AddRoleFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String authToken = extractAuthToken(exchange.getRequest().getHeaders());

            if (authToken == null) {
                return Mono.error(new RuntimeException("Invalid token."));
            }

            return webClient.get()
                    .uri("http://localhost:8080/token/user-info")
                    .header(HttpHeaders.AUTHORIZATION, authToken)
                    .retrieve()
                    .bodyToMono(UserInfoResponse.class)
                    .flatMap(userInfoResponse -> {
                        String r = userInfoResponse.getRole();
                        String email = userInfoResponse.getEmail();
                        return chain.filter(exchange.mutate()
                                .request(builder -> builder
                                        .header("Role", r)
                                        .header("Email", email))
                                .build());
                    });

        };
    }

    public static class Config {
    }
}

