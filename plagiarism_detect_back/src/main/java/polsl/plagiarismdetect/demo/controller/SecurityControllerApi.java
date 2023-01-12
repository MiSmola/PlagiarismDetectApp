//TODO: JR NEW! - from
package polsl.plagiarismdetect.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import polsl.plagiarismdetect.demo.model.domain.Users;

import javax.naming.AuthenticationException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(value = "/api/security", produces = MediaType.APPLICATION_JSON_VALUE)
public interface SecurityControllerApi {
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<String> register(@RequestBody Users users) throws AuthenticationException, NoSuchAlgorithmException;

    @PostMapping(value = "/authorize", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> authorize(@RequestBody Users users) throws AuthenticationException, NoSuchAlgorithmException;

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity authenticate(@RequestBody Users users) throws AuthenticationException, NoSuchAlgorithmException;
}
//TODO: JR NEW! - to
