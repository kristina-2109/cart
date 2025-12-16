package com.demo.cart.repository;

import com.demo.cart.model.cart.ShoppingCart;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<ShoppingCart, String> {
  public Optional<ShoppingCart> findByCustomerId(String customerId);

}
