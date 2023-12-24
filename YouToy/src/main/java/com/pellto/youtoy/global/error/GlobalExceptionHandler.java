package com.pellto.youtoy.global.error;

import com.pellto.youtoy.domain.channel.exception.NotExistChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscribedChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscriberChannelException;
import com.pellto.youtoy.domain.community.exception.NotExistCommentException;
import com.pellto.youtoy.domain.user.exception.WrongRepeatPasswordException;
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

  // Community Comment
  @ExceptionHandler(NotExistCommentException.class)
  public ResponseEntity<ErrorResponse> handleNotExistCommentException(
      NotExistCommentException ex
  ) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  // User
  @ExceptionHandler(WrongRepeatPasswordException.class)
  public ResponseEntity<ErrorResponse> handleWrongRepeatPasswordException(
      WrongRepeatPasswordException ex
  ) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  // Channel
  @ExceptionHandler(NotExistSubscriberChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistSubscriberChannelException(
      NotExistSubscriberChannelException ex
  ) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  @ExceptionHandler(NotExistSubscribedChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistSubscribedChannelException(
      NotExistSubscribedChannelException ex
  ) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  @ExceptionHandler(NotExistChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistChannelException(
      NotExistChannelException ex
  ) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }
}
