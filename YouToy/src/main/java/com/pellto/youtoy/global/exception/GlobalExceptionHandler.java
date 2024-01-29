package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.global.error.ErrorResponse;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private <T extends CustomRuntimeException> ResponseEntity<ErrorResponse> postProcess(T ex) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  // Request Validation
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex
  ) {
    log.error(ex.getMessage(), ex);
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors()
        .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<Map<String, String>> handleIllegalArgumentException(
      IllegalArgumentException ex
  ) {
    log.error(ex.getMessage(), ex);
    Map<String, String> errors = new HashMap<>();
    errors.put("message", ex.getMessage());
    return ResponseEntity.badRequest().body(errors);
  }

//  // Community Post Interest
//  @ExceptionHandler(NotExistPostInterestException.class)
//  public ResponseEntity<ErrorResponse> handleNotExistPostInterestException(
//      NotExistPostInterestException ex
//  ) {
//    return this.postProcess(ex);
//  }

}
