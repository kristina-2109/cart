package com.demo.cart.exception;

public class SubscriptionAlreadyExistsException extends RuntimeException {

  public SubscriptionAlreadyExistsException(String message) {
    super(message);
  }
}
