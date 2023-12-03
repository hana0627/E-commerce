//package msa.hana.userservice.global.config.security.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import msa.hana.userservice.api.domain.UserAccount;
//import msa.hana.userservice.api.dto.request.UserLogin;
//import msa.hana.userservice.api.dto.response.UserResponse;
//import msa.hana.userservice.api.service.UserService;
//import org.springframework.core.env.Environment;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityNotFoundException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@RequiredArgsConstructor
//public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    private final UserService userService;
//    private final Environment env;
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//
//        try {
//            ObjectMapper om = new ObjectMapper();
//            UserLogin userLogin = om.readValue(request.getInputStream(), UserLogin.class);
//
//            UsernamePasswordAuthenticationToken authenticationToken
//                    = new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password());
//
//            return getAuthenticationManager().authenticate(authenticationToken);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        Object principal = authResult.getPrincipal();
//        String email = authResult.getPrincipal().toString();
//        UserAccount user = userService.findUserByEmail(email).orElseThrow(EntityNotFoundException::new);
//
//
//        String token = Jwts.builder()
//                .setSubject(user.getUserId())
//                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("jwt.expiration_time"))))
//                .signWith(SignatureAlgorithm.ES512, env.getProperty("jwt.secret"))
//                .compact();
//
//        response.addHeader("token",token);
//        response.addHeader("userId", user.getUserId());
//    }
//
//}
