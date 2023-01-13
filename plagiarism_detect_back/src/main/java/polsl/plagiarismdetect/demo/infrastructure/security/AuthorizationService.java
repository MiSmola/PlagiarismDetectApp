//package polsl.plagiarismdetect.demo.infrastructure.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import polsl.plagiarismdetect.demo.model.domain.Users;
//import polsl.plagiarismdetect.demo.model.repository.UserRepository;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.naming.AuthenticationException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.util.Base64;
//import java.util.Date;
//
//@Service
//@RequiredArgsConstructor
//public class AuthorizationService {
//    private final UserRepository authorizationRepository;
//
//    private KeyGenerator keyGenerator;
//
//    public String authorize(String login, String password, boolean isNew) throws NoSuchAlgorithmException, AuthenticationException {
//        // Authenticate the user using the credentials provided
//        if (!isNew) authenticate(login, password);
//
//        // Issue a token for the user
//        String token = issueToken(login);
//
//        // Return the token on the response
//        return "Bearer " + token;
//    }
//
//    public void authenticate(String login, String password) throws AuthenticationException {
//        if (authorizationRepository.findByEmailAndPassword(login, password).isEmpty())
//            throw new AuthenticationException();
//    }
//
//    private String issueToken(String login) throws NoSuchAlgorithmException {
//        this.keyGenerator = KeyGenerator.getInstance("DES");
//        SecretKey secretKey = this.keyGenerator.generateKey();
//
//        persistAuthorizationData(login, secretKey);
//
//        String jwtToken = Jwts.builder()
//                .setSubject(login)
//                .setIssuedAt(new Date())
//                .setExpiration(convertToDateViaInstant(LocalDateTime.now().plusYears(10L).toLocalDate()))
//                .signWith(SignatureAlgorithm.HS512, secretKey)
//                .compact();
//        return jwtToken;
//    }
//
//    private void persistAuthorizationData(String email, Key key) {
//        Users authorization = new Users();
//        authorization.setEmail(email);
//        authorization.setPrivateKey(encodeKey(key));
//        this.authorizationRepository.save(authorization);
//    }
//
//    private Date convertToDateViaInstant(LocalDate dateToConvert) {
//        return java.util.Date.from(dateToConvert.atStartOfDay()
//                .atZone(ZoneId.systemDefault())
//                .toInstant());
//    }
//
//    private String encodeKey(Key key) {
//        return Base64.getEncoder().encodeToString(key.getEncoded());
//    }
//}
//
