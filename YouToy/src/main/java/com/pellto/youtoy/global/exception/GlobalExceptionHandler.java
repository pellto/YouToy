package com.pellto.youtoy.global.exception;

import com.pellto.youtoy.domain.channel.exception.NotExistChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscribedChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscriberChannelException;
import com.pellto.youtoy.domain.post.exception.NotExistPostInterestException;
import com.pellto.youtoy.domain.user.exception.WrongRepeatPasswordException;
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

  // Community Post Interest
  @ExceptionHandler(NotExistPostInterestException.class)
  public ResponseEntity<ErrorResponse> handleNotExistPostInterestException(
      NotExistPostInterestException ex
  ) {
    return this.postProcess(ex);
  }

  // Comment Interest
  @ExceptionHandler(NotExistCommentInterestException.class)
  public ResponseEntity<ErrorResponse> handleNotExistCommentInterestException(
      NotExistCommentInterestException ex
  ) {
    return this.postProcess(ex);
  }

  // Reply Interest
  @ExceptionHandler(NotExistReplyCommentInterestException.class)
  public ResponseEntity<ErrorResponse> handleNotExistReplyCommentInterestException(
      NotExistReplyCommentInterestException ex
  ) {
    return this.postProcess(ex);
  }

  // Contents Interest
  @ExceptionHandler(NotExistContentsInterestException.class)
  public ResponseEntity<ErrorResponse> handleNotExistContentsInterestException(
      NotExistContentsInterestException ex
  ) {
    return this.postProcess(ex);
  }

  // Comment
  @ExceptionHandler(NotExistCommentException.class)
  public ResponseEntity<ErrorResponse> handleNotExistCommentException(
      NotExistCommentException ex
  ) {
    return this.postProcess(ex);
  }

  // User
  @ExceptionHandler(WrongRepeatPasswordException.class)
  public ResponseEntity<ErrorResponse> handleWrongRepeatPasswordException(
      WrongRepeatPasswordException ex
  ) {
    return this.postProcess(ex);
  }

  // Channel
  @ExceptionHandler(NotExistSubscriberChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistSubscriberChannelException(
      NotExistSubscriberChannelException ex
  ) {
    return this.postProcess(ex);
  }

  @ExceptionHandler(NotExistSubscribedChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistSubscribedChannelException(
      NotExistSubscribedChannelException ex
  ) {
    return this.postProcess(ex);
  }

  @ExceptionHandler(NotExistChannelException.class)
  public ResponseEntity<ErrorResponse> handleNotExistChannelException(
      NotExistChannelException ex
  ) {
    return this.postProcess(ex);
  }
}
