package ru.kolpakovee.apigateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AuthValidationFilter extends AbstractGatewayFilterFactory<AuthValidationFilter.Config> {

    private final WebClient webClient;

    public AuthValidationFilter(WebClient.Builder webClientBuilder) {
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
                    .uri("http://localhost:8080/token/validate")
                    .header(HttpHeaders.AUTHORIZATION, authToken)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .flatMap(valid -> {
                        if (valid) {
                            return chain.filter(exchange);
                        } else {
                            return Mono.error(new RuntimeException("Invalid token."));
                        }
                    });
        };
    }

    public static String extractAuthToken(HttpHeaders headers) {
        return headers.getFirst(HttpHeaders.AUTHORIZATION);
    }

    public static class Config {
    }
}
