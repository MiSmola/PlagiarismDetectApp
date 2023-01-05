package polsl.plagiarismdetect.demo.infrastructure;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

public interface CustomGlobalExceptionHandler {
    ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                        HttpHeaders headers,
                                                        HttpStatus status, WebRequest request);

    // error handler for Exception, code INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<?> exception(Exception e, WebRequest request);

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleMaxSizeException(Exception e, WebRequest request);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, WebRequest request);

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<?> handleAuthenticationException(AuthenticationException e, WebRequest request);
}
