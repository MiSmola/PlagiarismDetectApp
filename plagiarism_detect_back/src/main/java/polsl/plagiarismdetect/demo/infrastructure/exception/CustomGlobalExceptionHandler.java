package polsl.plagiarismdetect.demo.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import polsl.plagiarismdetect.demo.infrastructure.dto.ExceptionDto;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler implements polsl.plagiarismdetect.demo.infrastructure.CustomGlobalExceptionHandler {
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                               HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        Map<String, Object> body = prepareResponseMap(e, status);
        return new ResponseEntity<>(body, headers, status);
    }

    // error handler for Exception, code INTERNAL_SERVER_ERROR
    @Override
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> exception(Exception e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(e.getMessage()),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMaxSizeException(Exception e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage() + " File to large");
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.INTERNAL_SERVER_ERROR, Collections.singletonList(e.getMessage() + " File to large"),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.BAD_REQUEST, Collections.singletonList(e.getMessage()),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e, WebRequest request) {
        log.error(getEndpointData(request) + e.getLocalizedMessage());
        return new ResponseEntity<>(prepareCustomExceptionDto(e, HttpStatus.UNAUTHORIZED, Collections.singletonList(e.getMessage()),
                request.getDescription(false), ((ServletWebRequest) request).getHttpMethod().toString()),
                HttpStatus.UNAUTHORIZED);
    }

    private String getEndpointData(WebRequest request) {
        String endpData = "CustomGlobalExceptionHandler: " + ((ServletWebRequest) request).getHttpMethod().toString() + request.getDescription(false);
        return endpData;
    }

    private Map prepareResponseMap(Exception e, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = new ArrayList<>();
        if (e instanceof MethodArgumentNotValidException)
            errors = ((MethodArgumentNotValidException) e).getBindingResult()
                    .getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
        else
            errors.add(e.getMessage());
        body.put("errors", errors);
        body.put("stackTrace", e.getStackTrace());
        return body;
    }

    private ExceptionDto prepareCustomExceptionDto(Exception e, HttpStatus status, List<String> messages, String path, String method) {
        return ExceptionDto.builder()
                .timestamp(new Date())
                .status(status.value())
                .messages(messages)
                .path(path)
                .method(method)
                .build();
    }
}
