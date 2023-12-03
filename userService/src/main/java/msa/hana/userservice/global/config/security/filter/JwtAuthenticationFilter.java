package msa.hana.userservice.global.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import msa.hana.userservice.api.domain.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final Environment env;

    private String userId;
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper om = new ObjectMapper();

        try{
            UserAccount user = om.readValue(request.getInputStream(), UserAccount.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            Authentication auth = authenticationManager.authenticate(token);
            response.addHeader("username", user.getEmail());
            response.addHeader("userId",user.getUserId());
            return token;
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String jwtToken = JWT.create()
                .withSubject("JWT토큰") //이름
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("jwt.expiration_time")))) //만료시간
                .withClaim("username", authResult.getName())
                .withClaim("password", authResult.getPrincipal().toString())
                .sign(Algorithm.HMAC512(env.getProperty("jwt.secret")));
        response.addHeader("Authorization","Bearer "+ jwtToken);


        super.successfulAuthentication(request, response, chain, authResult);
    }
}
