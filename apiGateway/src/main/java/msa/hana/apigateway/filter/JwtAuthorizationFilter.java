package msa.hana.apigateway.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j//AbstractGatewayFilterFactory<GlobalFilter.Config>
public class JwtAuthorizationFilter extends AbstractGatewayFilterFactory<JwtAuthorizationFilter.Config> {

    Environment env;

    public JwtAuthorizationFilter(Environment env) {
        super(Config.class);
        this.env = env;
    }

    public static class Config {

    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if(!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No authorization Header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String username = request.getHeaders().get("username").get(0);
            String jwt = authorizationHeader.replace("Bearer ", "");

            if(!isJwtValid(jwt, username)) {
                return onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });
    }

    private boolean isJwtValid(String jwt, String username) {
        boolean returnValue = true;

        String subject = null;
        try{
            subject = JWT.require(Algorithm.HMAC512(env.getProperty("jwt.secret"))).build().verify(jwt).getClaim("username").asString();
        } catch (Exception ex) {
            returnValue = false;
        }

        if(!StringUtils.hasText(subject)) {
            returnValue = false;
        }

        if(!username.equals(subject)) {
            returnValue = false;
        }

        return returnValue;

    }
    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpstatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpstatus);

        log.error(err);
        return response.setComplete();
    }
}
