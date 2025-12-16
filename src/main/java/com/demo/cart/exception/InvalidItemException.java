package com.demo.cart.exception;

public class InvalidItemException extends RuntimeException {

  public InvalidItemException(String message) {
    super(message);
  }
}
