//package polsl.plagiarismdetect.demo.infrastructure.security;
//
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import polsl.plagiarismdetect.demo.model.domain.Users;
//import polsl.plagiarismdetect.demo.model.repository.UserRepository;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.security.sasl.AuthenticationException;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.security.Key;
//import java.util.Base64;
//import java.util.Optional;
//
//@Log
//@Component
//@RequiredArgsConstructor
//public class JWTTokenNeededFilter extends OncePerRequestFilter {
//    private final UserRepository authorizationRepository;
//
//    @Override
//    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                 FilterChain filterChain) throws ServletException, IOException {
//        String token = null;
//        if (request.getRequestURI().contains("/api/") && !request.getRequestURI().contains("/register")) {
//            try {
//                String authorizationHeader = Optional.ofNullable(request.getHeader("Authorization")).orElseThrow(() -> new AuthenticationException("Invalid Authorization Header"));
//                String authorizationLogin = Optional.ofNullable(request.getHeader("Email")).orElseThrow(() -> new AuthenticationException("Invalid Authorization Header"));
//
//                // Extract the token from the HTTP Authorization header
//                token = authorizationHeader.substring("Bearer".length()).trim();
//                // Extract private key for user
//                Users user = authorizationRepository.findByEmail(authorizationLogin).orElseThrow();
//                // Mapping a secretKey from the String to java.security.Key
//                Key key = decodeKey(user.getPrivateKey(), "DES");
//                // Validate the token
//                Jwts.parser().setSigningKey(key).parseClaimsJws(token);
//            } catch (Exception e) {
//                log.severe("Invalid token: " + token);
//                throw new AuthenticationException("Invalid token: " + token);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//
//    private Key decodeKey(String encodedKey, String algorithm) {
//        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
//        return new SecretKeySpec(decodedKey, 0, decodedKey.length, algorithm);
//    }
//}

