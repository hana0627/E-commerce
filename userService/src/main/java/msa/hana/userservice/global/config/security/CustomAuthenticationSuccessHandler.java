//package msa.hana.userservice.global.config.security;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@RequiredArgsConstructor
//@Component
//@Slf4j
//public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    private final CustomUserDetailsService userDetailsService;
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//        if (authentication.isAuthenticated()) {
//            log.info("인증성공!");
//            CustomUserDetails u = userDetailsService.loadUserByUsername(authentication.getName());
//            System.out.println("u = " + u);
//            // 비밀번호가 같다면 jwt 토큰 생성
//
//
//
//
//        }
//    }
//}
