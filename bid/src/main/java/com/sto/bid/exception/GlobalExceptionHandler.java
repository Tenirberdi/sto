package com.sto.bid.exception;

import com.sto.bid.model.rest.response.RestResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

import static com.sto.bid.model.rest.response.RestStatus.INTERNAL_SERVER_ERROR;
import static com.sto.bid.model.rest.response.RestStatus.NOT_FOUND;
import static com.sto.bid.model.rest.response.RestStatus.UNIQUE_ERROR;

@Slf4j
@Hidden
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public RestResponse<Void> handleEntityNotFoundException(EntityNotFoundException e) {
        return new RestResponse<>(NOT_FOUND, messageSource.getMessage(NOT_FOUND.getKey(), null, LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse<Void> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new RestResponse<>(INTERNAL_SERVER_ERROR, messageSource.getMessage(INTERNAL_SERVER_ERROR.getKey(), null, LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler(UniqueConstraintError.class)
    @ResponseBody
    public RestResponse<Void> handleUniqueConstraintError(UniqueConstraintError e) {
        return new RestResponse<>(UNIQUE_ERROR, messageSource.getMessage(UNIQUE_ERROR.getKey(), null, LocaleContextHolder.getLocale()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
