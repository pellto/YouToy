package com.pellto.youtoy.global.error;

import com.pellto.youtoy.domain.channel.exception.NotExistChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscribedChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscriberChannelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotExistSubscriberChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistSubscriberChannelException(
      NotExistSubscriberChannelException ex) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  @ExceptionHandler(NotExistSubscribedChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistSubscribedChannelException(
      NotExistSubscribedChannelException ex) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }

  @ExceptionHandler(NotExistChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistChannelException(
      NotExistChannelException ex) {
    log.error(ex.getHandleMsg(), ex);
    ErrorResponse response = new ErrorResponse(ex.getErrorCode());
    return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
  }
}
