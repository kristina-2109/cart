package com.demo.cart.repository;

import com.demo.cart.model.Item.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
  List<Item> findAll();
  List<Item> findByItemIdIn(List<Long> ids);
  @Query("{ 'itemId': ?0, 'offers.offerId': ?1 }")
  Optional<Item> findByItemIdAndOfferId(Long itemId, Long offerId);
}
