package com.demo.cart.repository;

import com.demo.cart.model.statistics.StatisticsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<StatisticsEntity, String> {
}
