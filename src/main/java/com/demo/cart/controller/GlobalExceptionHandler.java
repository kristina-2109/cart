package com.demo.cart.controller;

import com.demo.cart.controller.dto.ErrorResponseDto;
import com.demo.cart.exception.CartNotFoundException;
import com.demo.cart.exception.InvalidItemException;
import com.demo.cart.exception.InvalidModificationTypeException;
import com.demo.cart.exception.SubscriptionAlreadyExistsException;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(CartNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleCartNotFound(CartNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(ErrorResponseDto.builder().timestamp(Instant.now().toEpochMilli()).message(ex.getMessage()).build());
  }

  @ExceptionHandler(InvalidItemException.class)
  public ResponseEntity<ErrorResponseDto> handleInvalidItem(InvalidItemException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponseDto.builder().timestamp(Instant.now().toEpochMilli()).message(ex.getMessage()).build());
  }

  @ExceptionHandler(SubscriptionAlreadyExistsException.class)
  public ResponseEntity<ErrorResponseDto> handleSubscriptionAlreadyExists(SubscriptionAlreadyExistsException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponseDto.builder().timestamp(Instant.now().toEpochMilli()).message(ex.getMessage()).build());
  }

  @ExceptionHandler(InvalidModificationTypeException.class)
  public ResponseEntity<ErrorResponseDto> handleInvalidModification(InvalidModificationTypeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponseDto.builder().timestamp(Instant.now().toEpochMilli()).message(ex.getMessage()).build());
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponseDto> handleRuntimeExcaptions(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(ErrorResponseDto.builder().timestamp(Instant.now().toEpochMilli()).message(ex.getMessage()).build());
  }
}
