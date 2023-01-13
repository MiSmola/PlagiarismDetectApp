
//package polsl.plagiarismdetect.demo.controller.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RestController;
//import polsl.plagiarismdetect.demo.controller.SecurityControllerApi;
//import polsl.plagiarismdetect.demo.infrastructure.security.AuthorizationService;
//import polsl.plagiarismdetect.demo.model.domain.Users;
//
//import javax.naming.AuthenticationException;
//import java.security.NoSuchAlgorithmException;
//
//@RestController
//@RequiredArgsConstructor
//public class SecurityController implements SecurityControllerApi {
//    private final AuthorizationService authorizationService;
//
//    @Override
//    public ResponseEntity<String> register(Users users) throws AuthenticationException, NoSuchAlgorithmException {
//        return ResponseEntity.ok(authorizationService.authorize(users.getEmail(), users.getPassword(), true));
//    }
//
//    @Override
//    public ResponseEntity<String> authorize(Users users) throws AuthenticationException, NoSuchAlgorithmException {
//        return ResponseEntity.ok(authorizationService.authorize(users.getEmail(), users.getPassword(), false));
//    }
//
//    @Override
//    public ResponseEntity authenticate(Users users) throws AuthenticationException {
//        authorizationService.authenticate(users.getEmail(), users.getPassword());
//        return ResponseEntity.ok().build();
//    }
//}

